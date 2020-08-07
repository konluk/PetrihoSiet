/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;

import java.awt.Color;
import java.awt.Graphics;


public class Kruh extends Tvar{
     
    private final int velkost = 35;
     
    public Kruh(int x , int y) {
        super(x,y);
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(new Color(111,111,111));
        g.fillOval(this.x ,this.y, velkost, velkost);
    }

   
}
