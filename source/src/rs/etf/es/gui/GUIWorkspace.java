/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GUIWorkspace.java
 *
 * Created on 23.05.2011., 15.42.40
 */
package rs.etf.es.gui;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;
import rs.etf.es.graphics.Block;
import rs.etf.es.graphics.RobotArm;
import rs.etf.es.logic.StripsLogic;

/**
 *
 * @author MB
 */
public class GUIWorkspace extends javax.swing.JPanel {

    private RobotArm arm;
    private LinkedList<Block> blocks;
    private LinkedList<String> states;
    public int xAxis, yAxis;

    /** Creates new form GUIWorkspace */
    public GUIWorkspace() {
        initComponents();

        blocks = new LinkedList<Block>();
        states = new LinkedList<String>();

        xAxis = 0;
        yAxis = 295 - 30;
    }

    public LinkedList<Block> getBlocks() {
        return blocks;
    }

    public LinkedList<String> getStates() {
        return states;
    }

    public void clear() {
        blocks.clear();
        states.clear();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        arm.paint(g);
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).paint(g);
        }
    }

    public void addBlock(Block b, boolean onAnotherBlock, String onBlock) {
        states.add("clear(" + b.name + ")");
        states.add(b.black ? "black(" + b.name + ")" : "white(" + b.name + ")");
        if (onAnotherBlock) {
            states.remove("clear(" + onBlock + ")");
            states.add("on(" + b.name + "," + onBlock + ")");
        } else {
            states.add("onTable(" + b.name + ")");
        }

        blocks.add(b);

        repaint();
    }

    public void makeRobotArm(int x, int y) {
        arm = new RobotArm(false, x, y);
    }

    public String makeStringState() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < states.size(); i++) {
            sb.append(states.get(i));
            sb.append("\n");
        }
        sb.append("armEmpty");

        return sb.toString();
    }

    public void recalculatePlaceOfBlock(StripsLogic strips) {
        String blockName,
                action = strips.getLastActionInPlan();
        boolean assimilateAction = false,
                getAction = false,
                stackAction = false,
                putAction = false;
        int[] param = new int[2];

        if (action != null) {
            if (action.substring(0, action.indexOf("(")). // only name of the action is of concern
                    equalsIgnoreCase("ASSIMILATE")) {
                blockName = action.substring(
                        action.indexOf(",") + 1,
                        action.indexOf(")"));
                assimilateAction = true;
            } else {
                blockName = action.substring(
                        action.indexOf("(") + 1,
                        action.indexOf(",") != -1 ? action.indexOf(",") : action.indexOf(")"));

                if (action.substring(0, action.indexOf("(")). // only name of the action is of concern
                        equalsIgnoreCase("UNSTACK")
                        || action.substring(0, action.indexOf("(")). // only name of the action is of concern
                        equalsIgnoreCase("PICKUP")) {
                    getAction = true;
                }
                if (action.substring(0, action.indexOf("(")). // only name of the action is of concern
                        equalsIgnoreCase("STACK")) {
                    stackAction = true;
                }
                if (action.substring(0, action.indexOf("(")). // only name of the action is of concern
                        equalsIgnoreCase("PUTDOWN")) {
                    putAction = true;
                }
            }

            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i).name.equalsIgnoreCase(blockName)) {
                    if (assimilateAction) {
                        // change only block color
                        blocks.get(i).black = true;
                    } else if (getAction) {
                        // change position of block
                        // place the block in arm
                        blocks.get(i).x1 = arm.getX1() - arm.width / 2 + 4;
                        blocks.get(i).y1 = arm.getY1() + arm.height + 2;

                        arm.holdingBlock = true;
                    } else if (putAction) {
                        // change position of block
                        // remove block from arm
                        // put on table
                        param = calculateParam(false, "");
                        blocks.get(i).x1 = param[0];
                        blocks.get(i).y1 = param[1];

                        arm.holdingBlock = false;
                    } else if (stackAction) {
                        // change position of block
                        // remove block from arm
                        // put on another block
                        param = calculateParam(true, action.substring(action.indexOf(",") + 1, action.indexOf(")")));
                        blocks.get(i).x1 = param[0];
                        blocks.get(i).y1 = param[1];

                        arm.holdingBlock = false;
                    }

                    break;
                }
            }
        }
    }

    // calculates x and y for drawing blocks
    public int[] calculateParam(boolean onAnotherBlock, String underBlockName) {
        int[] param = new int[2];

        if (onAnotherBlock) {
            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i).name.equalsIgnoreCase(underBlockName)) {
                    param[0] = blocks.get(i).x1;
                    param[1] = blocks.get(i).y1 - 31;

                    break;
                }
            }
        } else {
            int[] xs = new int[blocks.size()];
            int num  = 0;
            for (int i = 0; i < blocks.size(); i++) {
                if(blocks.get(i).y1 == yAxis){
                    xs[num++] = blocks.get(i).x1;
                }
            }

            Arrays.sort(xs, 0, num);

            int axis = 0;
            for (int i = 0; i < num; i++) {
                if(xs[i] == axis){
                    axis+=101;
                } else{
                    break;
                }
            }


//            for (int i = 0; i < blocks.size(); i++) {
//                if (blocks.get(i).x1 > param[0] && blocks.get(i).y1 == yAxis) {
//                    param[0] = blocks.get(i).x1;
//                }
//            }
//            param[0] += blocks.size() == 0 ? 0 : 101;
            param[0] = axis;
            param[1] = yAxis;
        }

        return param;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 821, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
