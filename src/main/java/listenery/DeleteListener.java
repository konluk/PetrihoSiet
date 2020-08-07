/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listenery;

import static com.sun.scenario.Settings.set;
import grafika.Kruh;
import grafika.MojCanvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static java.lang.reflect.Array.set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import zadanie1.HranaDoMiesta;
import zadanie1.HranaDoPrechodu;
import zadanie1.Miesto;
import zadanie1.Prechod;

/**
 *
 * @author Lukáš
 */
public class DeleteListener implements MouseListener {
    
    MojCanvas canvas = new MojCanvas();
    private int X;
    private int Y;
        
    public DeleteListener(MojCanvas canvas){
     super();
     this.canvas = canvas;
    }

      
    @Override
    public void mouseClicked(MouseEvent e) {        
    X = e.getX();
    Y = e.getY();  
        
    HashMap<Long, HranaDoPrechodu> vymazatHranyDoPrechodu = new HashMap<>();
    long ID = 1;    
    HashMap<Long, HranaDoMiesta> vymazatHranyDoMiest = new HashMap<>();
    long ID2 = 1;
    HashMap<Long, HranaDoPrechodu> vymazatHranyDoPrechodu2 = new HashMap<>();
    long ID3 = 1;
    HashMap<Long, HranaDoMiesta> vymazatHranyDoMiest2 = new HashMap<>();
    long ID4 = 1;
    
    for (Map.Entry mapElement : canvas.ziskajSiet().ziskajPrechody().entrySet()) { 
        Prechod pomocny_prechod = (Prechod) mapElement.getValue();
        if(pomocny_prechod.ziskajX()<X && pomocny_prechod.ziskajX()+35>X){
            if(pomocny_prechod.ziskajY()<Y && pomocny_prechod.ziskajY()+35>Y){
                
                for (Map.Entry mapElement2 : canvas.ziskajSiet().ziskajHranyDoPrechodov().entrySet()) { 
                    HranaDoPrechodu pomocna_hrana = (HranaDoPrechodu) mapElement2.getValue();
                    if(pomocna_hrana.ziskajPrechod() == pomocny_prechod){
                       vymazatHranyDoPrechodu.put(ID,pomocna_hrana);
                       ID++;                        
                   }
                }
                
                for (Map.Entry mapElement2 : vymazatHranyDoPrechodu.entrySet()) { 
                    HranaDoPrechodu pomocna = (HranaDoPrechodu) mapElement2.getValue();
                    canvas.ziskajSiet().vymazHranuDoPrechodu(pomocna);
                }
                
                for (Map.Entry mapElement2 : canvas.ziskajSiet().ziskajHranyDoMiest().entrySet()) { 
                    HranaDoMiesta pomocna_hrana = (HranaDoMiesta) mapElement2.getValue();
                    if(pomocna_hrana.ziskajPrechod() == pomocny_prechod){
                       vymazatHranyDoMiest.put(ID2,pomocna_hrana);
                       ID2++;                        
                   }
                }
               
                for (Map.Entry mapElement2 : vymazatHranyDoMiest.entrySet()) { 
                    HranaDoMiesta pomocna = (HranaDoMiesta) mapElement2.getValue();
                    canvas.ziskajSiet().vymazHranuDoMiesta(pomocna);
                }
                
      
                canvas.ziskajSiet().vymazPrechod(pomocny_prechod);
                canvas.ziskajSiet().vymazCiary();
                canvas.repaint();
                return;
            }
        }
    }

    for (Map.Entry mapElement : canvas.ziskajSiet().ziskajMiesta().entrySet()) { 
                Miesto pomocne_miesto = (Miesto) mapElement.getValue();
                if(pomocne_miesto.ziskajX()<X && pomocne_miesto.ziskajX()+35>X){
                    if(pomocne_miesto.ziskajY()<Y && pomocne_miesto.ziskajY()+35>Y){
                
                for (Map.Entry mapElement2 : canvas.ziskajSiet().ziskajHranyDoPrechodov().entrySet()) { 
                    HranaDoPrechodu pomocna_hrana = (HranaDoPrechodu) mapElement2.getValue();
                    if(pomocna_hrana.ziskajMiesto() == pomocne_miesto){
                       vymazatHranyDoPrechodu2.put(ID3,pomocna_hrana);
                       ID3++;                        
                   }
                }
               
                for (Map.Entry mapElement2 : vymazatHranyDoPrechodu2.entrySet()) { 
                    HranaDoPrechodu pomocna = (HranaDoPrechodu) mapElement2.getValue();
                    canvas.ziskajSiet().vymazHranuDoPrechodu(pomocna);
                }
                
                for (Map.Entry mapElement2 : canvas.ziskajSiet().ziskajHranyDoMiest().entrySet()) { 
                    HranaDoMiesta pomocna_hrana = (HranaDoMiesta) mapElement2.getValue();
                    if(pomocna_hrana.ziskajMiesto() == pomocne_miesto){
                       vymazatHranyDoMiest2.put(ID4,pomocna_hrana);
                       ID4++;                        
                   }
                }
                
                for (Map.Entry mapElement2 : vymazatHranyDoMiest2.entrySet()) { 
                    HranaDoMiesta pomocna = (HranaDoMiesta) mapElement2.getValue();
                    canvas.ziskajSiet().vymazHranuDoMiesta(pomocna);
                }
                
      
                canvas.ziskajSiet().vymazMiesto(pomocne_miesto);
                canvas.ziskajSiet().vymazCiary();
                canvas.repaint();
                return;
            }
        }
    }
    
//        System.out.println(canvas.ziskajSiet().ziskajCiary());
//        long x=5;
//        Line2D pomocnaciara = canvas.ziskajSiet().ziskajCiary().get(x);  
//        canvas.ziskajSiet().ziskajCiary().values().remove(pomocnaciara);
//        canvas.repaint();
//        System.out.println(canvas.ziskajSiet().ziskajCiary());

    //Rectangle2D obdlznik3 = new Rectangle2D.Double(X, Y, 2, 2);
    
    for (Map.Entry mapElement : canvas.ziskajSiet().ziskajCiary().entrySet()) { 
        Line2D pomocnaciara = (Line2D) mapElement.getValue();
        Rectangle2D obdlznik = new Rectangle2D.Double(X-6, Y-6, 12, 12);  
       
        if(obdlznik.intersectsLine(pomocnaciara)){            
                        
            for (Map.Entry mapElement2 : canvas.ziskajSiet().ziskajHranyDoPrechodov().entrySet()) { 
                HranaDoPrechodu prechod = (HranaDoPrechodu) mapElement2.getValue();
                if(prechod.ziskajMiesto().ziskajX() == (pomocnaciara.getX1()-17) && prechod.ziskajMiesto().ziskajY() == (pomocnaciara.getY1()-17)){
                    if(prechod.ziskajPrechod().ziskajX() == (pomocnaciara.getX2()-17) && prechod.ziskajPrechod().ziskajY() == (pomocnaciara.getY2()-17)){
                        System.out.println("NASIEL SOM PRECHOD:" +prechod);
                        canvas.ziskajSiet().vymazHranuDoPrechodu(prechod);
                       // canvas.ziskajSiet().ziskajCiary().values().remove(pomocnaciara);
                        canvas.ziskajSiet().vymazCiary();
                        canvas.repaint();
                        return;
                    }
                }
            }
             
            for (Map.Entry mapElement2 : canvas.ziskajSiet().ziskajHranyDoMiest().entrySet()) { 
                HranaDoMiesta prechod = (HranaDoMiesta) mapElement2.getValue();
                if(prechod.ziskajPrechod().ziskajX() == (pomocnaciara.getX1()-17) && prechod.ziskajPrechod().ziskajY() == (pomocnaciara.getY1()-17)){
                    if(prechod.ziskajMiesto().ziskajX() == (pomocnaciara.getX2()-17) && prechod.ziskajMiesto().ziskajY() == (pomocnaciara.getY2()-17)){
                        System.out.println("NASIEL SOM PRECHOD:" +prechod);
                        canvas.ziskajSiet().vymazHranuDoMiesta(prechod);
                       // canvas.ziskajSiet().ziskajCiary().values().remove(pomocnaciara);
                        canvas.ziskajSiet().vymazCiary();
                        canvas.repaint();
                        return;
                    }
                }
            }
           // canvas.ziskajSiet().ziskajCiary().values().remove(pomocnaciara);
            
          //  return;
        }  
    }
        
//        canvas.ziskajSiet().ziskajCiary().values().remove(pomocnaciara);
//        System.out.println("CIARY VYMAZANE");
//        canvas.repaint();
//        
//        
//        if(obdlznik3.contains(pomocnaciara.getP1())){
//            canvas.ziskajSiet().ziskajCiary().values().remove(pomocnaciara);
//            System.out.println("NACHADZA SA V CIARE");
//            canvas.repaint();
//            return;
//        }else{
//            System.out.println("nenachadza sa");
//        }
        
       
    }
    
    //    
    //   System.out.println(rectangle1.intersectsLine(ciara));    
    
    
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
