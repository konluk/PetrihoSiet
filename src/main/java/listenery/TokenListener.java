/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listenery;

import grafika.MojCanvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import zadanie1.Miesto;
import zadanie1.Prechod;

/**
 *
 * @author Lukáš
 */
public class TokenListener implements MouseListener {
    
    MojCanvas canvas = new MojCanvas();
       
    private int X;
    private int Y;
    
    public TokenListener(MojCanvas canvas){
     super();
     this.canvas = canvas;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        
        for (Map.Entry mapElement : canvas.ziskajSiet().ziskajMiesta().entrySet()) { 
            Miesto pomocne_miesto = (Miesto) mapElement.getValue();
            if(pomocne_miesto.ziskajX()<X && pomocne_miesto.ziskajX()+35>X){
                if(pomocne_miesto.ziskajY()<Y && pomocne_miesto.ziskajY()+35>Y){
                    if(e.getButton() == MouseEvent.BUTTON1) { //LAVE TLACIDLO
                        pomocne_miesto.nastavTokeny(pomocne_miesto.ziskajTokeny() + 1);
                    }   
                    
                    if(e.getButton() == MouseEvent.BUTTON3) { //PRAVE TLACIDLO
                        if(pomocne_miesto.ziskajTokeny()>0)pomocne_miesto.nastavTokeny(pomocne_miesto.ziskajTokeny() - 1);
                    }                              
                }
            }
        }
        canvas.repaint();     
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}