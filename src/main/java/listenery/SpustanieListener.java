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
import zadanie1.PetrihoSiet;
import zadanie1.Prechod;

/**
 *
 * @author Lukáš
 */
public class SpustanieListener implements MouseListener {
    
    MojCanvas canvas = new MojCanvas();
       
    private int X;
    private int Y;
    
    public SpustanieListener(MojCanvas canvas){
     super();
     this.canvas = canvas;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        
        if(canvas.ziskajSiet() != null){
            for (Map.Entry mapElement : canvas.ziskajSiet().ziskajPrechody().entrySet()) { 
                Prechod pomocny_prechod = (Prechod) mapElement.getValue();
          
                if(pomocny_prechod.ziskajX()<X && pomocny_prechod.ziskajX()+35>X){
                    if(pomocny_prechod.ziskajY()<Y && pomocny_prechod.ziskajY()+35>Y){
                        if(pomocny_prechod.zistiPrechodna()){
                            canvas.ziskajSiet().spustiPrechod((int)pomocny_prechod.ziskajId());
                            canvas.repaint();
                        }
                    }
                }
            } 
        } 
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
