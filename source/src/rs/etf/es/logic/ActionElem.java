/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.etf.es.logic;

/**
 *
 * @author MB
 */
public class ActionElem {

    public String name;
    public String[] blocks;

    public ActionElem(String ev) {
        int openBracket = ev.indexOf('('),
                closeBracket = ev.indexOf(')');

        if (openBracket != -1) {
            name = ev.substring(0, openBracket);
            blocks = ev.substring(openBracket + 1, closeBracket).split(",");
        } else {
            name = ev;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(name);

        if (blocks != null) {
            sb.append('(');
            for (int i = 0; i < blocks.length; i++) {
                sb.append(blocks[i]);
                sb.append(i != blocks.length - 1 ? "," : "");
            }
            sb.append(')');
        }
        
        return sb.toString();
    }
}
