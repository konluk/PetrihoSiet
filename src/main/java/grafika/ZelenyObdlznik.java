/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;


import java.awt.Color;
import java.awt.Graphics;


public class ZelenyObdlznik extends Tvar{
     
    private final int velkost = 31;
     
    public ZelenyObdlznik(int x , int y) {
        super(x,y);
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(new Color(0,255,0));
        g.fillRect(this.x+2 ,this.y+2, velkost, velkost);
    }

   
}