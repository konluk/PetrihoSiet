/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listenery;

import grafika.MojCanvas;
import grafika.Obdlznik;
import grafika.ZelenyObdlznik;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import zadanie1.HranaDoMiesta;
import zadanie1.Miesto;
import zadanie1.PetrihoSiet;
import zadanie1.Prechod;

/**
 *
 * @author Lukáš
 */
public class MiestoListener implements MouseListener {
    
    MojCanvas canvas = new MojCanvas();
    
    private short X;
    private short Y;
    private int pX;
    private int pY;
    
    public MiestoListener(MojCanvas canvas){
     super();
     this.canvas = canvas;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        pX = e.getX() - 15;
        pY = e.getY() - 20;
        
        X = (short)pX;
        Y = (short)pY;
                
        long MAXID = 0;
        
        for (Map.Entry mapElement : canvas.ziskajSiet().ziskajPrechody().entrySet()) { 
            Prechod pomocny_prechod = (Prechod) mapElement.getValue();
            if(pomocny_prechod.ziskajId() > MAXID) MAXID = pomocny_prechod.ziskajId();
        }
        
        for (Map.Entry mapElement : canvas.ziskajSiet().ziskajMiesta().entrySet()) { 
            Miesto pomocne_miesto = (Miesto) mapElement.getValue();
            if(pomocne_miesto.ziskajId() > MAXID) MAXID = pomocne_miesto.ziskajId();
        } 
        
        ///MAXIMALNE ID AJ OD HRAN ZISTIT
        
        MAXID++;
        
        Miesto miesto_pomocne = new Miesto(MAXID, " ",0,X,Y); 
        canvas.ziskajSiet().pridajMiesto(miesto_pomocne);  
        
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