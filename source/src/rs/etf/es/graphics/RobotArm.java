/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rs.etf.es.graphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author MB
 */
public class RobotArm {
    public boolean holdingBlock;

    private int x1, y1;

    public final static int height = 15,
            width = 108;

    public RobotArm(boolean holdingBlock, int x1, int y1) {
        this.holdingBlock = holdingBlock;
        this.x1 = x1;
        this.y1 = y1;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.drawLine(x1, y1, x1, y1 + height);
        g.drawLine(x1 - width/2, y1 + height, x1 + width/2, y1 + height);
        g.drawLine(x1 - width/2, y1 + height, x1 - width/2, y1 + 2*height);
        g.drawLine(x1 + width/2, y1 + height, x1 + width/2, y1 + 2*height);
    }
}
