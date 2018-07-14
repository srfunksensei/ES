/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.etf.es.logic;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author MB
 */
public class Action {

    public ActionElem actionName;
    public ArrayList<ActionElem> preconditionList;
    public ArrayList<ActionElem> addList;
    public ArrayList<ActionElem> deleteList;

    /*
     * String n - name
     * String p - preconditionList (space-delimited list)
     * String a - addList (space-delimited list)
     * String d - deleteList (space delimited list)
     */
    public Action(String n, String p, String d, String a) {
        StringTokenizer theString;

        actionName = new ActionElem(n);

        // convert space-delimited list of tokens into a arrayList
        theString = new StringTokenizer(p);
        preconditionList = new ArrayList<ActionElem>();
        while (theString.hasMoreTokens()) {
            preconditionList.add(new ActionElem(theString.nextToken()));
        }

        theString = new StringTokenizer(a);
        addList = new ArrayList<ActionElem>();
        while (theString.hasMoreTokens()) {
            addList.add(new ActionElem(theString.nextToken()));
        }

        theString = new StringTokenizer(d);
        deleteList = new ArrayList<ActionElem>();
        while (theString.hasMoreTokens()) {
            deleteList.add(new ActionElem(theString.nextToken()));
        }
    }

    /**
     * boolean fulfillsGoal(String goal)
     *
     * A function returning a boolean value which indicates
     * whether the current action object contains the specified
     * goal in its addList.  In other words: does this action
     * accomplish the goal?
     *
     * It watches only name of predicate
     *
     */
    public boolean fulfillsGoal(String goal) {
        boolean retval = false;

        // iterate over strings in addList and compare with the goal
        for (int i = 0; i < addList.size(); i++) {
            if(addList.get(i).name.equalsIgnoreCase(goal)){
                retval = true;
                break;
            }
        }

        return retval;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        StringBuffer sbp = new StringBuffer();
        for (int i = 0; i < preconditionList.size(); i++) {
            sbp.append(preconditionList.get(i).toString());
            sbp.append(i!=preconditionList.size()-1 ? " " : "");
        }

        StringBuffer sba = new StringBuffer();
        for (int i = 0; i < addList.size(); i++) {
            sba.append(addList.get(i).toString());
            sba.append(i!=addList.size()-1 ? " " : "");
        }

        StringBuffer sbd = new StringBuffer();
        for (int i = 0; i < deleteList.size(); i++) {
            sbd.append(deleteList.get(i).toString());
            sbd.append(i!=deleteList.size()-1 ? " " : "");
        }

        return new Action(actionName.toString(), sbp.toString(), sbd.toString(), sba.toString());
    }

    @Override
     public String toString() {
        return ("action: " + actionName
                + "\n  preconditionList: " + preconditionList
                + "\n  addList:          " + addList
                + "\n  deleteList:       " + deleteList);
    }
}
