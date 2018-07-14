/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rs.etf.es.logic;

/**
 *
 * @author MB
 */
public class StackElement {
    public String goal;         // goal that need to be fulfilled
    public boolean isComposite; // if goal is composite (has more goals)
    public boolean isAction;    // if stack elem. is action

    public StackElement(String g, boolean comp, boolean action){
        goal = g;
        isComposite = comp;
        isAction = action;
    }

    @Override
    public String toString() {
        return goal +"\n";
    }
}
