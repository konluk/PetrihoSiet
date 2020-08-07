/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafika;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import listenery.DeleteListener;
import listenery.HranaListener;
import listenery.MiestoListener;
import listenery.PrechodListener;
import listenery.ResetHranaListener;
import listenery.SpustanieListener;
import listenery.TokenListener;
import marshaller.ArcOut;
import marshaller.DocumentOut;
import marshaller.PlaceOut;
import marshaller.TransitionOut;
import sk.stuba.fei.oop.generated.Document;
import zadanie1.HranaDoMiesta;
import zadanie1.HranaDoPrechodu;
import zadanie1.Miesto;
import zadanie1.PetrihoSiet;
import zadanie1.Prechod;


public class Obrazovka extends JFrame implements ActionListener{
   
    private final MojCanvas canvas = new MojCanvas();
    PetrihoSiet siet = new PetrihoSiet();
    
    private final JButton t_import = new JButton("Import");
    private final JButton t_miesto = new JButton("Pridaj miesto");
    private final JButton t_prechod = new JButton("Pridaj prechod");
    private final JButton t_tokeny = new JButton("Tokeny +-");
    private final JButton t_hrany = new JButton("Pridaj hranu");
    private final JButton t_resethrany = new JButton("Pridaj reset hranu");
    private final JButton t_vymaz = new JButton("Vymaz");
    private final JButton t_spustanie = new JButton("Spustanie");
    private final JButton t_export = new JButton("Export");
    
    private Document document;
    private String cestasuboru;
    
    private MouseListener aktualnylistener;
    
    public Obrazovka(){
        setLayout(new BorderLayout());
        setSize(1500,1000);
        
        t_import.addActionListener(this);
        t_miesto.addActionListener(this);
        t_prechod.addActionListener(this); 
        t_hrany.addActionListener(this);
        t_resethrany.addActionListener(this);
        t_tokeny.addActionListener(this);
        t_vymaz.addActionListener(this);
        t_export.addActionListener(this);
        t_spustanie.addActionListener(this);
        
        t_miesto.setBackground(Color.GRAY);
        t_prechod.setBackground(Color.GRAY);
        t_hrany.setBackground(Color.GRAY);
        t_resethrany.setBackground(Color.GRAY);
        t_tokeny.setBackground(Color.GRAY);
        t_vymaz.setBackground(Color.GRAY);
        t_spustanie.setBackground(Color.GRAY);
          
        JPanel panelA = new JPanel();
        panelA.setLayout(new FlowLayout(1));
        panelA.add(t_import);
        panelA.add(t_export);
        panelA.add(t_miesto);
        panelA.add(t_prechod);       
        panelA.add(t_hrany);
        panelA.add(t_resethrany);
        panelA.add(t_tokeny);
        panelA.add(t_vymaz);
        panelA.add(t_spustanie);
     
        
        add(panelA,BorderLayout.PAGE_START);      
        add(canvas,BorderLayout.CENTER);
       
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Petriho siete");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == t_import) {    
            
            
            FileDialog OpDialog = new FileDialog(this, "Nacitaj petriho siet", FileDialog.LOAD);
            OpDialog.setVisible(true);
           
            if (OpDialog.getFile() != null) {
                cestasuboru = OpDialog.getDirectory() + OpDialog.getFile();
            }

            try {
                File file = new File(cestasuboru);
                JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                document = (Document) jaxbUnmarshaller.unmarshal(file);

//                System.out.println("Subor nacitany z : " + cestasuboru);
//                System.out.println("Number of places:" + document.getPlace().size());
//                System.out.println("Number of tranistions:" + document.getTransition().size());
//                System.out.println("Number of arcs:" + document.getArc().size());
              
                


            }catch (JAXBException ignored) {
                ignored.printStackTrace();
            }
            siet = new PetrihoSiet();    
                        
            for(int i = 0; i<document.getPlace().size(); i++){
                
                Miesto miesto_pomocne = new Miesto(document.getPlace().get(i).getId(), " ",document.getPlace().get(i).getTokens(),document.getPlace().get(i).getX(),document.getPlace().get(i).getY()); 
                siet.pridajMiesto(miesto_pomocne);  
            }
            
            for(int i = 0; i<document.getTransition().size(); i++){
                
                Prechod prechod_pomocne = new Prechod(document.getTransition().get(i).getId(), " ",document.getTransition().get(i).getX(),document.getTransition().get(i).getY());             
                siet.pridajPrechod(prechod_pomocne);
            }
                     
            for(int i = 0; i<document.getArc().size(); i++){
                long pomocna = document.getArc().get(i).getSourceId();
                long zdroj = document.getArc().get(i).getSourceId();
                long ciel = document.getArc().get(i).getDestinationId();
                
                if(siet.ziskajMiesta().containsKey(pomocna)){                     
                    siet.vytvorHranu(siet.ziskajMiesta().get(zdroj),siet.ziskajPrechody().get(ciel),document.getArc().get(i).getMultiplicity(),document.getArc().get(i).getType()); 
                }else{
                    siet.vytvorHranu(siet.ziskajPrechody().get(zdroj), siet.ziskajMiesta().get(ciel),document.getArc().get(i).getMultiplicity(),document.getArc().get(i).getType());
                }             
            }
          
            canvas.priradSiet(siet);
        }
        
        
        if (e.getSource() == t_miesto) {    
           
            
           vyfarby_tlacidla();
           t_miesto.setBackground(Color.GREEN);
                      
           if(aktualnylistener != null)canvas.removeMouseListener(aktualnylistener);
           
           MouseListener listener = new MiestoListener(canvas);
           canvas.addMouseListener(listener);
           aktualnylistener = listener;
         
        }
        
        
        
        if (e.getSource() == t_spustanie) {    
            
           vyfarby_tlacidla();
           t_spustanie.setBackground(Color.GREEN);
           
           if(aktualnylistener != null)canvas.removeMouseListener(aktualnylistener);
            
           MouseListener listener = new SpustanieListener(canvas);
           canvas.addMouseListener(listener);
           aktualnylistener = listener;
           
        }
        
        if (e.getSource() == t_prechod) {    
            
           vyfarby_tlacidla();
           t_prechod.setBackground(Color.GREEN);
           
           if(aktualnylistener != null)canvas.removeMouseListener(aktualnylistener);
            
           MouseListener listener = new PrechodListener(canvas);
           canvas.addMouseListener(listener);
           aktualnylistener = listener;
           
        }
        
         if (e.getSource() == t_tokeny) {    
            
           vyfarby_tlacidla();
           t_tokeny.setBackground(Color.GREEN);
           
           if(aktualnylistener != null)canvas.removeMouseListener(aktualnylistener);
            
           MouseListener listener = new TokenListener(canvas);
           canvas.addMouseListener(listener);
           aktualnylistener = listener;
           
        }
         
        if (e.getSource() == t_hrany) {    
            
           vyfarby_tlacidla();
           t_hrany.setBackground(Color.GREEN);
           
           if(aktualnylistener != null)canvas.removeMouseListener(aktualnylistener);
            
           MouseListener listener = new HranaListener(canvas);
           canvas.addMouseListener(listener);
           aktualnylistener = listener;
           
        }
        
        if (e.getSource() == t_resethrany) {    
            
           vyfarby_tlacidla();
           t_resethrany.setBackground(Color.GREEN);
           
           if(aktualnylistener != null)canvas.removeMouseListener(aktualnylistener);
            
           MouseListener listener = new ResetHranaListener(canvas);
           canvas.addMouseListener(listener);
           aktualnylistener = listener;
        }
        
        if (e.getSource() == t_vymaz) {    
            
           vyfarby_tlacidla();
           t_vymaz.setBackground(Color.GREEN);
           
           if(aktualnylistener != null)canvas.removeMouseListener(aktualnylistener);
            
           MouseListener listener = new DeleteListener(canvas);
           canvas.addMouseListener(listener);
           aktualnylistener = listener;
        }
        
        if (e.getSource() == t_export) {    
         
         
            FileDialog fileDialog = new FileDialog((Frame) this.getParent(),
                    "Choose file", FileDialog.SAVE);
            fileDialog.setFile("*.xml");
            fileDialog.setVisible(true);
            DocumentOut document = new DocumentOut();
            ArrayList<PlaceOut> placeOut = new ArrayList<>();
            ArrayList<TransitionOut> transitionOut = new ArrayList<>();
            ArrayList<ArcOut> arcOut = new ArrayList<>();
            
            for (Map.Entry mapElement : siet.ziskajMiesta().entrySet()) { 
                Miesto miesto = (Miesto) mapElement.getValue();
                PlaceOut place = new PlaceOut();
                place.setId((short) miesto.ziskajId());
                place.setX((short) miesto.ziskajX());
                place.setY((short) miesto.ziskajY());
                place.setTokens((short) miesto.ziskajTokeny());
                placeOut.add(place);
            }  
            
            for (Map.Entry mapElement : siet.ziskajPrechody().entrySet()) { 
                Prechod prechod = (Prechod) mapElement.getValue();
                TransitionOut transition = new TransitionOut();
                transition.setId((short) prechod.ziskajId());
                transition.setX((short) prechod.ziskajX());
                transition.setY((short) prechod.ziskajY());
                transitionOut.add(transition);
            }  
            
            short id = 1000;
            for (Map.Entry mapElement : siet.ziskajHranyDoPrechodov().entrySet()) { 
                HranaDoPrechodu hrana = (HranaDoPrechodu) mapElement.getValue();
                ArcOut arc = new ArcOut();              
                arc.setMultiplicity((short) hrana.ziskajNasobnost());
                arc.setType(hrana.ziskajTyp());
                arc.setSourceId((short) hrana.ziskajMiesto().ziskajId());
                arc.setDestinationId((short) hrana.ziskajPrechod().ziskajId());                
                arc.setId((short) id);
                arcOut.add(arc);
                id++;
            }
            
            for (Map.Entry mapElement : siet.ziskajHranyDoMiest().entrySet()) { 
                HranaDoMiesta hrana = (HranaDoMiesta) mapElement.getValue();
                ArcOut arc = new ArcOut();              
                arc.setMultiplicity((short) hrana.ziskajNasobnost());
                arc.setType(hrana.ziskajTyp());
                arc.setSourceId((short)hrana.ziskajPrechod().ziskajId() );        
                arc.setDestinationId((short) hrana.ziskajMiesto().ziskajId());                
                arc.setId((short) id);
                arcOut.add(arc);
                id++;
            }

            document.setPlace(placeOut);
            document.setTransition(transitionOut);
            document.setArc(arcOut);

            try {
                String fileDir = fileDialog.getDirectory() + fileDialog.getFile();
                System.out.println("Selected path: " + fileDir);
                File file = new File(fileDir);
                JAXBContext jaxbContext = JAXBContext.newInstance(DocumentOut.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(document, file);
            } catch (JAXBException ignored) {
                ignored.printStackTrace();
            }
    }
              
        
    }
    
       public void vyfarby_tlacidla(){
           t_miesto.setBackground(Color.GRAY);
           t_prechod.setBackground(Color.GRAY);
           t_hrany.setBackground(Color.GRAY);
           t_resethrany.setBackground(Color.GRAY);
           t_tokeny.setBackground(Color.GRAY);
           t_vymaz.setBackground(Color.GRAY);
           t_spustanie.setBackground(Color.GRAY);
        }
 }

