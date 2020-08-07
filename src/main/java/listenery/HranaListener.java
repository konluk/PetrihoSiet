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
import javax.sql.rowset.serial.SerialArray;
import zadanie1.Miesto;
import zadanie1.Prechod;

/** -1 pociatocna hodnota suradnic, ak obe dvojice nemaju ani jedno -1 tak skontrolujeme ci sa da vytvorit a vytvoríme
 *
 * @author Lukáš
 */
public class HranaListener implements MouseListener {
    
    MojCanvas canvas = new MojCanvas();
    
   
    private int X;
    private int Y;
    
    private Miesto miesto = null;
    private Prechod prechod = null;
    
    private boolean MiestoDoPrechodu = true;
    
    public HranaListener(MojCanvas canvas){
     super();
     this.canvas = canvas;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        X = e.getX();
        Y = e.getY();
        
        for (Map.Entry mapElement : canvas.ziskajSiet().ziskajPrechody().entrySet()) { 
            Prechod pomocny_prechod = (Prechod) mapElement.getValue();
            if(pomocny_prechod.ziskajX()<X && pomocny_prechod.ziskajX()+35>X){
                if(pomocny_prechod.ziskajY()<Y && pomocny_prechod.ziskajY()+35>Y){
                       if(prechod != null){ZlyPrechod();
                       }else
                       {
                       prechod = pomocny_prechod;
                       if(miesto == null)MiestoDoPrechodu = false;
                       }
                }
            }
        }         
        
        for (Map.Entry mapElement : canvas.ziskajSiet().ziskajMiesta().entrySet()) { 
            Miesto pomocne_miesto = (Miesto) mapElement.getValue();
            if(pomocne_miesto.ziskajX()<X && pomocne_miesto.ziskajX()+35>X){
                if(pomocne_miesto.ziskajY()<Y && pomocne_miesto.ziskajY()+35>Y){
                        if(miesto != null){ZlyPrechod();}
                        else{
                        miesto = pomocne_miesto;    
                        }
                }
            }        
        }
        
        if(miesto!=null && prechod!=null){
           if(MiestoDoPrechodu){
             canvas.ziskajSiet().vytvorHranu(miesto,prechod,1,"regular");
           }else{
             canvas.ziskajSiet().vytvorHranu(prechod,miesto,1,"regular");
           }
           
           miesto = null;
           prechod = null;
           MiestoDoPrechodu = true;
          
           canvas.repaint();
        }
       
    }
    
    private void ZlyPrechod(){
        miesto = null;
        prechod = null;
        MiestoDoPrechodu = true;
        System.out.println("NEPLATNA HRANA"); //SPRAVIT EXCEPTION
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
