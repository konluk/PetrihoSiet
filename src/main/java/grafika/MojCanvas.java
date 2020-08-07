/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import zadanie1.HranaDoMiesta;
import zadanie1.HranaDoPrechodu;
import zadanie1.Miesto;
import zadanie1.PetrihoSiet;
import zadanie1.Prechod;

/**
 *
 * @author Lukáš
 */
public class MojCanvas extends Canvas{
    
    private int X;
    private int Y;    
    private PetrihoSiet siet;
    
     
            
    public MojCanvas(){
       // addMouseListener(this);
    }
    
    public void priradSiet(PetrihoSiet siet){
        this.siet = siet; 
        repaint();
    }
    
    public PetrihoSiet ziskajSiet(){
        return siet;
    }
    
  
    
    @Override
    public void paint(Graphics g){      
                
        Kruh kruh;
        Obdlznik obdlznik;
        ZelenyObdlznik zeleny_obdlznik;
   
        if(siet != null){
            for (Map.Entry mapElement : siet.ziskajPrechody().entrySet()) { 
                Prechod pomocny_prechod = (Prechod) mapElement.getValue();
                pomocny_prechod.nastavPrechodna(true);
            } 
                        
            siet.zistiPrechodnost();
            
            //TESTO CODE VYMAZAT
           
                                   
          //  Rectangle2D rectangle1 = new Rectangle2D.Double(1, 1, 10, 10);     
            Graphics2D g2 = (Graphics2D) g;
            Line2D ciara;            
            //g2.setColor(Color.GRAY);
          //  ciara = new Line2D.Double(10,10,50,50);
           // siet.pridajCiaru(ciara);
           // ciara.
            //System.out.println(rectangle1.intersectsLine(ciara));
            //KONIEC TEST CODE
            
            for (Map.Entry mapElement : siet.ziskajHranyDoPrechodov().entrySet()) { 
                HranaDoPrechodu prechod = (HranaDoPrechodu) mapElement.getValue();
               
                ciara = new Line2D.Double(prechod.ziskajMiesto().ziskajX()+17,prechod.ziskajMiesto().ziskajY()+17,prechod.ziskajPrechod().ziskajX()+17,prechod.ziskajPrechod().ziskajY()+17);
                siet.pridajCiaru(ciara);
                
                if(prechod.ziskajTyp().equals("regular")){
                    nakresliCiaruSoSipkou(g,prechod.ziskajMiesto().ziskajX()+17,prechod.ziskajMiesto().ziskajY()+17,prechod.ziskajPrechod().ziskajX()+17,prechod.ziskajPrechod().ziskajY()+17,45,7);
                }else{
                    g.drawLine(prechod.ziskajMiesto().ziskajX()+17,prechod.ziskajMiesto().ziskajY()+17,prechod.ziskajPrechod().ziskajX()+17,prechod.ziskajPrechod().ziskajY()+17);
                }
                 
                if(prechod.ziskajNasobnost()>1){
                    int x1 = prechod.ziskajMiesto().ziskajX();
                    int y1 = prechod.ziskajMiesto().ziskajY();
                    int x2 = prechod.ziskajPrechod().ziskajX();
                    int y2 = prechod.ziskajPrechod().ziskajY();
                
                    int sur1 = x1 + ((x2 - x1) / 2);
                    int sur2 = y1 + ((y2 - y1) / 2);
                    g.drawString(Integer.toString(prechod.ziskajNasobnost()), sur1, sur2);
                }
            } 
         
            for (Map.Entry mapElement : siet.ziskajHranyDoMiest().entrySet()) { 
                HranaDoMiesta prechod = (HranaDoMiesta) mapElement.getValue();
                
                ciara = new Line2D.Double(prechod.ziskajPrechod().ziskajX()+17,prechod.ziskajPrechod().ziskajY()+17,prechod.ziskajMiesto().ziskajX()+17,prechod.ziskajMiesto().ziskajY()+17);
                siet.pridajCiaru(ciara);
                
                if(prechod.ziskajTyp().equals("regular")){
                    nakresliCiaruSoSipkou(g,prechod.ziskajPrechod().ziskajX()+17,prechod.ziskajPrechod().ziskajY()+17,prechod.ziskajMiesto().ziskajX()+17,prechod.ziskajMiesto().ziskajY()+17,45,7);
                }else{
                    g.drawLine(prechod.ziskajPrechod().ziskajX()+17,prechod.ziskajPrechod().ziskajY()+17,prechod.ziskajMiesto().ziskajX()+17,prechod.ziskajMiesto().ziskajY()+17);   
                }   
          
                if(prechod.ziskajNasobnost()>1){
                    int x1 = prechod.ziskajMiesto().ziskajX();
                    int y1 = prechod.ziskajMiesto().ziskajY();
                    int x2 = prechod.ziskajPrechod().ziskajX();
                    int y2 = prechod.ziskajPrechod().ziskajY();
                    int sur1 = x1 + ((x2 - x1) / 2);
                    int sur2 = y1 + ((y2 - y1) / 2);
                    g.drawString(Integer.toString(prechod.ziskajNasobnost()), sur1, sur2);
                }
            } 
            
            for (Map.Entry mapElement : siet.ziskajCiary().entrySet()) { 
                Line2D ciarapomocna = (Line2D) mapElement.getValue();
                g2.draw(ciarapomocna);
            }
            
            for (Map.Entry mapElement : siet.ziskajPrechody().entrySet()) { 
                Prechod pomocny_prechod = (Prechod) mapElement.getValue();
                obdlznik = new Obdlznik(pomocny_prechod.ziskajX(),pomocny_prechod.ziskajY());
                obdlznik.draw(g);
            
                if(pomocny_prechod.zistiPrechodna()){
                    zeleny_obdlznik = new ZelenyObdlznik(pomocny_prechod.ziskajX(),pomocny_prechod.ziskajY());
                    zeleny_obdlznik.draw(g);
                } 
            } 
        
            for (Map.Entry mapElement : siet.ziskajMiesta().entrySet()) { 
                Miesto pomocne_miesto = (Miesto) mapElement.getValue();
                kruh = new Kruh(pomocne_miesto.ziskajX(),pomocne_miesto.ziskajY());
                kruh.draw(g);            
                g.setColor(new Color(255,255,0));
                g.drawString(Integer.toString(pomocne_miesto.ziskajTokeny()), pomocne_miesto.ziskajX()+12, pomocne_miesto.ziskajY()+22);
            }  
        } 
    }
    

    
    public void nakresliCiaruSoSipkou(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
    
    

}
