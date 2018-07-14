/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.etf.es.logic;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author MB
 */
public class StripsLogic {

    Stack<StackElement> goalStack;  //stack
    LinkedList<ActionElem> currState; // current state
    LinkedList<ActionElem> goalState; // goal state
    LinkedList<Action> actionList; // action list
    LinkedList<Action> cloneActions; // clones
    LinkedList<String> plan; // action plan
    LinkedList<String> whiteBlocksForAssimilation;

    public StripsLogic(String[] stackGoals, String[] currStates) {

        whiteBlocksForAssimilation = new LinkedList<String>();
        plan = new LinkedList<String>();
        cloneActions = new LinkedList<Action>();
        goalState = new LinkedList<ActionElem>();
        goalStack = new Stack<StackElement>();
        currState = new LinkedList<ActionElem>();

        fillActionList();
        fillGoalStack(stackGoals);
        fillCurrentState(currStates);
        fillBlocksColor(currStates, stackGoals);
    }

    public Stack<StackElement> getGoalStack() {
        return goalStack;
    }

    public String getLastActionInPlan() {
        return plan.getLast();
    }

    public void work() {
        /*
         * 1.skidaj sa steka sve dok ne dodjes do necega sto ne mozes da skines
         *   (skida se sa steka ukoliko ima isti takav predikat u tekucem stanju)
         * 2. kada naidjes na predikat koji ne mozes da skines idi u listu akcija
         *    i trazi NAJPOVOLJNIJU akciju koja u svom DODAJ delu ima navedeni
         *    predikat
         * 3. na stek stavi tu akciju (prethodno izvrsi unifikaciju), stavi nejgov
         *    preduslov, i svaku komponentu preduslova
         * 4. nastavi isto kao 1. samo ukoliko naidjes na akciju tada iz tekuceg
         *    stanja  obrisi ono sto se nalazi u UKLONI delu akcije, a dopisi
         *    ono sto se nalazi u DODAJ delu akcije
         *
         */

        while (!goalStack.empty()) {
            singleStep();
        }

        // for testing purpose
        System.out.println(currState.toString());
        System.out.println(plan.toString());
        System.out.println(goalStack.toString());
        ///////////////
    }

    public boolean singleStep() {
        StackElement e = goalStack.peek();
        ActionElem goal = new ActionElem(e.goal);

        // for painting in graphics
        boolean isAction = false;

        if (e.isAction) { //if action on stack
            actionWork(e, goal);

            isAction = true;
        } else if (e.isComposite) { // if composite goal on stack
            compositeGoalWork(e);
        } else { // if trivial goal on stack
            while (canAssimilate()) {
            }
            e = goalStack.peek();
            trivialGoalWork(e, goal);
        }

        // for testing purpose
        System.out.println(goalStack.toString());

        return isAction;
    }

    private void deleteOldAndAddNew(LinkedList<ActionElem> curr, Action act) {

        // iterate over strings in delList and delete its occurence in currentState
        for (int i = 0; i < act.deleteList.size(); i++) {
            for (int j = 0; j < curr.size(); j++) {
                if ((act.deleteList.get(i).toString()).equalsIgnoreCase(curr.get(j).toString())) {
                    curr.remove(j);
                    break;
                }
            }
        }

        // add from addList of action in currentState
        for (int i = 0; i < act.addList.size(); i++) {
            curr.add(new ActionElem(act.addList.get(i).toString()));
        }
    }

    private void replaceBlockNames(Action act, String target, String replacement) {
        // replace all occurence in action target with replecemant in:

        // precondition list
        for (int i = 0; i < act.preconditionList.size(); i++) {
            String[] blo = act.preconditionList.get(i).blocks;
            for (int j = 0; blo != null && j < blo.length; j++) {
                if (blo[j].equalsIgnoreCase(target)) {
                    blo[j] = replacement;
                    break;
                }
            }
        }

        // add list
        for (int i = 0; i < act.addList.size(); i++) {
            String[] blo = act.addList.get(i).blocks;
            for (int j = 0; blo != null && j < blo.length; j++) {
                if (blo[j].equalsIgnoreCase(target)) {
                    blo[j] = replacement;
                    break;
                }
            }
        }

        // delete list
        for (int i = 0; i < act.deleteList.size(); i++) {
            String[] blo = act.deleteList.get(i).blocks;
            for (int j = 0; blo != null && j < blo.length; j++) {
                if (blo[j].equalsIgnoreCase(target)) {
                    blo[j] = replacement;
                    break;
                }
            }
        }

        // name
        String[] blo = act.actionName.blocks;
        for (int j = 0; blo != null && j < blo.length; j++) {
            if (blo[j].equalsIgnoreCase(target)) {
                blo[j] = replacement;
                break;
            }
        }
    }

    private void fillActionList() {
        actionList = new LinkedList<Action>();

        Action act;
        act = new Action("ASSIMILATE(x,y)", "black(x) white(y) on(x,y)", "white(y)", "black(y)");
        actionList.add(act);

        act = new Action("STACK(x,y)", "clear(y) holding(x)", "clear(y) holding(x)", "armEmpty on(x,y) clear(x)");
        actionList.add(act);

        act = new Action("UNSTACK(x,y)", "on(x,y) clear(x) armEmpty", "on(x,y) clear(x) armEmpty", "clear(y) holding(x)");
        actionList.add(act);

        act = new Action("PICKUP(x)", "clear(x) onTable(x) armEmpty", "clear(x) onTable(x) armEmpty", "holding(x)");
        actionList.add(act);

        act = new Action("PUTDOWN(x)", "holding(x)", "holding(x)", "onTable(x) clear(x) armEmpty");
        actionList.add(act);
    }

    public void fillGoalStack(String[] goals) {
        //put composite goal on stack
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < goals.length; i++) {
            sb.append(goals[i]);
            sb.append(i != goals.length - 1 ? " " : "");
        }
        goalStack.push(new StackElement(sb.toString(), true, false));

        //put trivial goals on stack
        for (int i = 0; i < goals.length; i++) {
            goalStack.add(new StackElement(goals[i], false, false));
        }

        fillGoalState(goals);
    }

    public void fillCurrentState(String[] states) {
        for (int i = 0; i < states.length; i++) {
            currState.add(new ActionElem(states[i]));
        }
    }

    private void fillGoalState(String[] states) {
        for (int i = 0; i < states.length; i++) {
            goalState.add(new ActionElem(states[i]));
        }
    }

    private void fillBlocksColor(String[] currState, String[] goalState) {
        LinkedList<String> goalBlockColor = new LinkedList<String>();

        // fill currBlock
        for (int i = 0; i < currState.length; i++) {
            if (currState[i].startsWith("white")
                    || currState[i].startsWith("black")) {
                whiteBlocksForAssimilation.add(currState[i]);
            }
        }

        // fill goalBlock
        for (int i = 0; i < goalState.length; i++) {
            if (goalState[i].startsWith("white")
                    || goalState[i].startsWith("black")) {
                goalBlockColor.add(goalState[i]);
            }
        }

        // delete same color in start and goal state
        for (int i = 0; i < goalBlockColor.size(); i++) {
            for (int j = 0; j < whiteBlocksForAssimilation.size(); j++) {
                if (whiteBlocksForAssimilation.get(j).equalsIgnoreCase(
                        goalBlockColor.get(i))) {
                    whiteBlocksForAssimilation.remove(j);

                    break;
                }
            }
        }
    }

    public void deleteOldGoals() {
        // clear:
        // stack
        goalStack.clear();

        // currState
        currState.clear();

        // goalState
        goalState.clear();

        //plan
        plan.clear();
    }

    private void actionWork(StackElement e, ActionElem goal) {
        for (int i = 0; i < cloneActions.size(); i++) {
            if ((goal.name).equalsIgnoreCase(cloneActions.get(i).actionName.name)
                    && (goal.blocks[0]).equalsIgnoreCase(cloneActions.get(i).actionName.blocks[0])) {
                // pop action from stack,
                // apply action (do adds and dels),
                // add action to plan
                // delete action from clones
                deleteOldAndAddNew(currState, cloneActions.get(i));
                goalStack.pop();
                plan.add(e.goal);
                cloneActions.remove(i);
                break;
            }
        }
    }

    private void compositeGoalWork(StackElement e) {
        // see if all goals are fullfilled
        String[] compGoals = e.goal.split(" ");
        boolean ok = false;

        for (int i = 0; i < compGoals.length; i++) {
            ok = false;
            for (int j = 0; j < currState.size(); j++) {
                if (compGoals[i].equalsIgnoreCase(currState.get(j).toString())) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                break;
            }
        }

        if (!ok) {
            // if not all goals are fullfiled push trivial goals
            // on stack
            for (int i = 0; i < compGoals.length; i++) {
                goalStack.push(new StackElement(compGoals[i], false, false));
            }
        } else {
            //if all goals are fullfilled
            goalStack.pop();
        }
    }

    private void trivialGoalWork(StackElement e, ActionElem goal) {
        boolean poped = false;

        for (int i = 0; i < currState.size(); i++) {
            // if there is a match
            // (if goal is achieved)
            if (currState.get(i).toString().equalsIgnoreCase(e.goal)) {
                goalStack.pop();
                poped = true;
                break;
            }
        }

        if (!poped) {
            // else if there is no match
            // find ONE BEST action that has goal in it's
            // add list to put on stack
            // put action on stack, put precondition list
            // on stack, and put trivial goals on stack


            //find BEST action
            // unifie and add it to clone action list
            Action clone = getBestActionClone(goal);
            cloneActions.add(clone);

            putActionOnStack(clone);
        }
    }

    private void putActionOnStack(Action clone) {
        //put action
        goalStack.push(new StackElement(clone.actionName.toString(), false, true));

        //put precondition list on stack
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < clone.preconditionList.size(); i++) {
            sb.append(clone.preconditionList.get(i));
            sb.append(i != clone.preconditionList.size() - 1 ? " " : "");
        }
        goalStack.push(new StackElement(sb.toString(), true, false));

        //put trivial goals on stack
        for (int i = 0; i < clone.preconditionList.size(); i++) {
            goalStack.push(new StackElement(clone.preconditionList.get(i).toString(), false, false));
        }
    }

    public String printPlan() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < plan.size(); i++) {
            sb.append(plan.get(i));
            sb.append("\n");
        }

        return sb.toString();
    }

    private Action getBestActionClone(ActionElem goal) {
        //find BEST action
        Action a = null;

        // block for unification
        String[] blocks = new String[2];

        // holidng(x)   UNSTACK/PICKUP
        if (goal.name.equalsIgnoreCase("holding")) {
            for (int i = 0; i < currState.size(); i++) {
                if (currState.get(i).name.equalsIgnoreCase("on")
                        && currState.get(i).blocks[0].equalsIgnoreCase(goal.blocks[0])) {
                    a = findAction("UNSTACK");

                    blocks[0] = currState.get(i).blocks[0];
                    blocks[1] = currState.get(i).blocks[1];
                }
            }
            if (a == null) {
                a = findAction("PICKUP");

                blocks[0] = goal.blocks[0];
            }

            // armEmpty     STACK/PUTDOWN
        } else if (goal.name.equalsIgnoreCase("armEmpty")) {
            for (int i = 0; i < goalState.size(); i++) {
                for (int j = 0; j < currState.size(); j++) {
                    if (goalState.get(i).name.equalsIgnoreCase("on")
                            && currState.get(j).name.equalsIgnoreCase("holding")
                            && goalState.get(i).blocks[0].equalsIgnoreCase(currState.get(j).blocks[0])) {
                        a = findAction("STACK");

                        blocks[0] = goalState.get(i).blocks[0];
                        blocks[1] = goalState.get(i).blocks[1];
                    }
                }
            }
            if (a == null) {
                a = findAction("PUTDOWN");

                for (int i = 0; i < currState.size(); i++) {
                    if (currState.get(i).name.equalsIgnoreCase("holding")) {
                        blocks[0] = currState.get(i).blocks[0];

                        break;
                    }
                }
            }

            // clear(x)     PUTDOWN/STACK/UNSTACK
        } else if (goal.name.equalsIgnoreCase("clear")) {
            boolean inHand = false;
            for (int i = 0; i < currState.size(); i++) {
                if (currState.get(i).name.equalsIgnoreCase("holding")
                        && currState.get(i).blocks[0].equalsIgnoreCase(goal.blocks[0])) {
                    inHand = true;
                    break;
                }
            }

            if (inHand) {
                // like armEmpty
                for (int i = 0; i < goalState.size(); i++) {
                    for (int j = 0; j < currState.size(); j++) {
                        if (goalState.get(i).name.equalsIgnoreCase("on")
                                && currState.get(j).name.equalsIgnoreCase("holding")
                                && goalState.get(i).blocks[0].equalsIgnoreCase(currState.get(j).blocks[0])) {
                            a = findAction("STACK");

                            blocks[0] = goalState.get(i).blocks[0];
                            blocks[1] = goalState.get(i).blocks[1];
                        }
                    }
                }
                if (a == null) {
                    a = findAction("PUTDOWN");

                    for (int i = 0; i < currState.size(); i++) {
                        if (currState.get(i).name.equalsIgnoreCase("holding")) {
                            blocks[0] = currState.get(i).blocks[0];

                            break;
                        }
                    }
                }

            } else {
                for (int i = 0; i < currState.size(); i++) {
                    if (currState.get(i).name.equalsIgnoreCase("on")
                            && currState.get(i).blocks[1].equalsIgnoreCase(goal.blocks[0])) {
                        a = findAction("UNSTACK");

                        blocks[0] = currState.get(i).blocks[0];
                        blocks[1] = currState.get(i).blocks[1];

                        break;
                    }
                }
            }

            if (a == null) {
                System.out.println("FATAL ERROR!");
                System.exit(0);
            }
            // black(x) and needs to be assimilated
        } else if (goal.name.equalsIgnoreCase("black")
                && hasToBeAssimilated(goal.blocks[0])) {

            Action newV = assimilateAnother(goal.blocks[0]);
            return newV;
        } else {
            for (int i = 0; i < actionList.size(); i++) {
                if (actionList.get(i).fulfillsGoal(goal.name)) {
                    a = actionList.get(i);

                    for (int j = 0; j < goal.blocks.length; j++) {
                        blocks[j] = goal.blocks[j];
                    }
                    break;
                }
            }
        }

        if (a == null) {
            // this should never happen
            System.exit(0);
        }

        // make clone of action
        Action actClone = null;
        try {
            actClone = (Action) a.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" clone err: " + ex);
        }

        // replace block names
        for (int j = 0; j < actClone.actionName.blocks.length; j++) {
            replaceBlockNames(actClone, actClone.actionName.blocks[j], blocks[j]);
        }
        return actClone;
    }

    // find action with given name, without brackets
    private Action findAction(String actionName) {
        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).actionName.name.equalsIgnoreCase(actionName)) {
                return actionList.get(i);
            }
        }

        return null;
    }

    private boolean canAssimilate() {
        boolean canAssimilate = false;

        // find if there is some block that is on block that 
        // needs to be assimilated
        for (int i = 0; i < whiteBlocksForAssimilation.size(); i++) {
            String currColor = whiteBlocksForAssimilation.get(i);

            for (int j = 0; j < currState.size(); j++) {
                if (currState.get(j).name.equalsIgnoreCase("on")
                        && currState.get(j).blocks[1].equalsIgnoreCase(
                        currColor.substring(currColor.indexOf("(") + 1, currColor.indexOf(")")))
                        && isBlack(currState.get(j).blocks[0])) {

                    canAssimilate = true;

                    // push action on stack and
                    // push compoite and trivial goal
                    Action a = findAction("ASSIMILATE"),
                            clone = null;

                    try {
                        clone = (Action) a.clone();
                    } catch (CloneNotSupportedException ex) {
                        System.out.println("clone err: ");
                    }

                    // replace block names
                    for (int k = 0; k < clone.actionName.blocks.length; k++) {
                        replaceBlockNames(clone, clone.actionName.blocks[k], currState.get(j).blocks[k]);
                    }

                    cloneActions.add(clone);
                    putActionOnStack(clone);

                    // delete assimilated
                    whiteBlocksForAssimilation.remove(i);

                    break;
                }
            }
        }
        return canAssimilate;
    }

    private boolean hasToBeAssimilated(String blockName) {
        for (int i = 0; i < whiteBlocksForAssimilation.size(); i++) {
            String block = whiteBlocksForAssimilation.get(i),
                    name = block.substring(block.indexOf("(") + 1, block.indexOf(")"));
            if (name.equalsIgnoreCase(blockName)) {
                return true;
            }
        }
        return false;
    }

    // assimilate block with blockName with another block that is black
    private Action assimilateBlockWithClearBlack(String blockName, String clearBlack) {
        Action clone = null;
        for (int i = 0; i < whiteBlocksForAssimilation.size(); i++) {

            String currColor = whiteBlocksForAssimilation.get(i);
            if (currColor.substring(currColor.indexOf("(") + 1,
                    currColor.indexOf(")")).equalsIgnoreCase(blockName)) {

                // if white that needs to be assimilated then
                // put STACK and UNSTACK/PICKUP action on stack

                // ASSIMILATE
                Action a = findAction("ASSIMILATE");
                try {
                    clone = (Action) a.clone();
                } catch (CloneNotSupportedException ex) {
                    System.out.println("clone ex:");
                }

                String[] blocks = new String[2];
                blocks[0] = clearBlack;
                blocks[1] = blockName;

                // replace block names
                for (int k = 0; k < clone.actionName.blocks.length; k++) {
                    replaceBlockNames(clone, clone.actionName.blocks[k], blocks[k]);
                }

                cloneActions.add(clone);
                putActionOnStack(clone);


                // STACK
                clone = getBestActionClone(new ActionElem("on("
                        + clearBlack
                        + ","
                        + blockName
                        + ")"));

                cloneActions.add(clone);
                putActionOnStack(clone);


                // UNSTACK/PICKUP
                clone = getBestActionClone(new ActionElem("holding("
                        + clearBlack + ")"));


                // delete assimilated
                whiteBlocksForAssimilation.remove(i);

                break;
            }
        }

        return clone;
    }

    private Action assimilateBlockWithNoClearBlock(String blockName){
        // if there is no black block
        // that is clear find black block
        Action a = null,
                clone = null;
        String blackBlock = "", onBlock = "";
        LinkedList<String> blocksTower = new LinkedList<String>();

        // get black block name
        for (int i = 0; i < currState.size(); i++) {
            if (currState.get(i).name.equalsIgnoreCase("black")) {
                blackBlock = currState.get(i).blocks[0];
                break;
            }
        }

        // search if there is another block on that block
        // if there is than search again, until there is no
        // block on that block
        for (int i = 0; i < currState.size(); i++) {
            if (currState.get(i).name.equalsIgnoreCase("on")
                    && currState.get(i).blocks[1].equalsIgnoreCase(blackBlock)) {
                onBlock = currState.get(i).blocks[0];

                blocksTower.addLast(blackBlock);
                if (isClear(onBlock)) {
                    break;
                } else {
                    blackBlock = onBlock;
                }
            }
        }

        blocksTower.addLast(onBlock);


        stackBlockTower(blocksTower);

        blackBlock = blocksTower.getFirst();

        // PUTDOWN
        a = findAction("PUTDOWN");
        try {
            clone = (Action) a.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("clone ex:");
        }

        String[] blocks = new String[2];
        blocks[0] = blackBlock;
        blocks[1] = blockName;

        // replace block names
        for (int k = 0; k < clone.actionName.blocks.length; k++) {
            replaceBlockNames(clone, clone.actionName.blocks[k], blocks[k]);
        }

        cloneActions.add(clone);
        putActionOnStack(clone);

        clone = assimilateBlockWithClearBlack(blockName, blackBlock);
        cloneActions.add(clone);
        putActionOnStack(clone);

        clone = unstackBlockTower(blocksTower);

        return clone;
    }

    private Action unstackBlockTower(LinkedList<String> blocksTower){
        Action a = null,
                clone = null;
        int size  = blocksTower.size() - 1;
        for (int i = 0; i < size; i++) {

            // PUTDOWN
            a = findAction("PUTDOWN");
            try {
                clone = (Action) a.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("clone ex:");
            }

            String[] blocks = new String[2];
            blocks[0] = blocksTower.get(i + 1);
            blocks[1] = blocksTower.get(i);

            // replace block names
            for (int k = 0; k < clone.actionName.blocks.length; k++) {
                replaceBlockNames(clone, clone.actionName.blocks[k], blocks[k]);
            }

            cloneActions.add(clone);
            putActionOnStack(clone);

            // UNSTACK
            a = findAction("UNSTACK");
            try {
                clone = (Action) a.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("clone ex:");
            }


            // replace block names
            for (int k = 0; k < clone.actionName.blocks.length; k++) {
                replaceBlockNames(clone, clone.actionName.blocks[k], blocks[k]);
            }

            if (i != size) {
                cloneActions.add(clone);
                putActionOnStack(clone);
            }
        }

        return clone;
    }

    private void stackBlockTower(LinkedList<String> blocksTower){
        Action a = null,
                clone = null;
        for (int i = blocksTower.size() - 1; i > 0; i--) {

            // STACK
            a = findAction("STACK");
            try {
                clone = (Action) a.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("clone ex:");
            }

            String[] blocks = new String[2];
            blocks[0] = blocksTower.get(i);
            blocks[1] = blocksTower.get(i-1);

            // replace block names
            for (int k = 0; k < clone.actionName.blocks.length; k++) {
                replaceBlockNames(clone, clone.actionName.blocks[k], blocks[k]);
            }

            cloneActions.add(clone);
            putActionOnStack(clone);


            //PICKUP
            a = findAction("PICKUP");
            try {
                clone = (Action) a.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("clone ex:");
            }

            // replace block names
            for (int k = 0; k < clone.actionName.blocks.length; k++) {
                replaceBlockNames(clone, clone.actionName.blocks[k], blocks[k]);
            }

            cloneActions.add(clone);
            putActionOnStack(clone);
        }
    }

    private Action assimilateAnother(String blockName) {
        String clearBlack = findClearBlack();
        

        if (!clearBlack.equalsIgnoreCase("")) {
            return assimilateBlockWithClearBlack(blockName, clearBlack);
        } else {
            return assimilateBlockWithNoClearBlock(blockName);
        }
    }

    private boolean isBlack(String blockName) {
        for (int i = 0; i < currState.size(); i++) {
            if (currState.get(i).name.equalsIgnoreCase("black")
                    && currState.get(i).blocks[0].equalsIgnoreCase(blockName)) {
                return true;
            }
        }

        return false;
    }

    private boolean isClear(String blockName) {
        for (int i = 0; i < currState.size(); i++) {
            if (currState.get(i).name.equalsIgnoreCase("clear")
                    && currState.get(i).blocks[0].equalsIgnoreCase(blockName)) {
                return true;
            }
        }

        return false;
    }

    // find black block that is clear,
    // return name of the block
    private String findClearBlack() {
        for (int i = 0; i < currState.size(); i++) {
            if (currState.get(i).name.equalsIgnoreCase("black")
                    && isBlack(currState.get(i).blocks[0])
                    && isClear(currState.get(i).blocks[0])) {
                return currState.get(i).blocks[0];
            }
        }
        return "";
    }

    public boolean isFulfilledGoal() {
        int fullGoals = 0;
        for (int i = 0; i < goalState.size(); i++) {
            for (int j = 0; j < currState.size(); j++) {
                if (goalState.get(i).toString().equalsIgnoreCase(
                        currState.get(j).toString())) {
                    fullGoals++;
                    break;
                }
            }
        }

        return fullGoals == goalState.size();
    }

    public void clear() {
        goalStack.clear();
        currState.clear();
        goalState.clear();
        cloneActions.clear();
        plan.clear();
        whiteBlocksForAssimilation.clear();
    }

    public boolean isGoodPlan(){
        boolean ok = true;

        if (plan.size() > 3) {
            for (int i = 0; i < plan.size() - 2; i++) {
                if(plan.get(i).equalsIgnoreCase(plan.get(i + 2))){
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }
}
