/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;

import java.awt.Graphics;


public abstract class Tvar {
    
    protected int x,y;
          
    public Tvar(int x, int y){
    this.x = x;
    this.y = y;
   
    }
    
    abstract void draw(Graphics g);
}
