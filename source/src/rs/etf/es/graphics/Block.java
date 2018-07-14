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
public class Block {
    public String name;
    public boolean black;

    public int x1, y1;

    private final static int height = 30,
            width = 100;

    public Block(String name, boolean black, int x1, int y1) {
        this.name = name;
        this.black = black;
        this.x1 = x1;
        this.y1 = y1;
    }

    public void paint(Graphics g){
        if(black) {
            g.setColor(Color.BLACK);
            g.fillRect(x1, y1, width, height);
            g.setColor(Color.WHITE);
            g.drawString(name, x1 + width/2, y1 + height/2);
        }
        else {
            g.setColor(Color.BLACK);
            g.drawRect(x1, y1, width, height);
            g.drawString(name, x1 + width/2, y1 + height/2);
        }
    }
}
