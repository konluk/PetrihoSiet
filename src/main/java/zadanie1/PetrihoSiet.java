


package zadanie1;

import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PetrihoSiet {
    
    private HashMap<Long, HranaDoMiesta> hranyDoMiest = new HashMap<>();
    private HashMap<Long, HranaDoPrechodu> hranyDoPrechodov = new HashMap<>();
    private HashMap<Long, Miesto> miesta = new HashMap<>();   
    private HashMap<Long, Prechod> prechody = new HashMap<>();
    public Set<Hrany> vsetky_hrany = new HashSet<>();
    
    private long poradieDoPrechodov = 1;
    private long poradieDoMiesta = 1;
    
    private HashMap<Long, Line2D> ciary = new HashMap<>();
    private long ID = 1;
           
    public HashMap<Long, Line2D> ziskajCiary() {
        return ciary;
    }

    public void pridajCiaru(Line2D ciara) {
        ciary.put(ID,ciara);
        ID++;
    }
    
    public void vymazCiary(){
        ciary.clear();
    }
      
      
    public HashMap<Long, Miesto> ziskajMiesta() {
        return miesta;
    }

    public HashMap<Long, Prechod> ziskajPrechody() {
        return prechody;
    }

    public HashMap<Long, HranaDoPrechodu> ziskajHranyDoPrechodov() {
        return hranyDoPrechodov;
    }
    
    public HashMap<Long, HranaDoMiesta> ziskajHranyDoMiest() {
        return hranyDoMiest;
    }
    
    public void pridajMiesto(Miesto miesto){
       miesta.put(miesto.ziskajId(),miesto);
     //  poradieMiesta++;
    }
    
    public void pridajPrechod(Prechod prechod){
       prechody.put(prechod.ziskajId(),prechod);
     //  poradiePrechody++;
    }
    
    
    public void vytvorHranu(Prechod prechod, Miesto miesto,int nasobnost, String typ) {
        
        if(nasobnost < 1)throw new IllegalArgumentException("Nasobnost hrany " + prechod.ziskajNazov()+" do " + miesto.ziskajNazov() + " je mensia ako 1");
        
        if(typ.equals("reset"))throw new IllegalArgumentException("Zaciatocny bod reset hrany nemoze byt prechod");
        
        HranaDoMiesta hrana_pomocna = new HranaDoMiesta(prechody.get(prechod.ziskajId()), miesta.get(miesto.ziskajId()),nasobnost, typ);
        hranyDoMiest.put(poradieDoMiesta, hrana_pomocna);
        poradieDoMiesta++;
        
        vsetky_hrany.add(hrana_pomocna);
    }
    
    public void vytvorHranu(Miesto miesto, Prechod prechod, int nasobnost, String typ) {
       
        if(nasobnost < 1)throw new IllegalArgumentException("Nasobnost hrany " +miesto.ziskajNazov() +" do " + prechod.ziskajNazov() + " je mensia ako 1");
        
        HranaDoPrechodu hrana_pomocna = new HranaDoPrechodu(miesta.get(miesto.ziskajId()), prechody.get(prechod.ziskajId()), nasobnost, typ);
        
        hranyDoPrechodov.put(poradieDoPrechodov, hrana_pomocna);
        poradieDoPrechodov++;
        vsetky_hrany.add(hrana_pomocna);
    }
    
    public void vytvorHranu(Prechod prechod1, Prechod prechod2,int nasobnost, String typ) {
        throw new IllegalArgumentException("Nie je mozne vytvorit hranu PRECHOD - PRECHOD");
    }
    
    public void vytvorHranu(Miesto miesto1, Miesto miesto2,int nasobnost, String typ) {
        throw new IllegalArgumentException("Nie je mozne vytvorit hranu MIESTO - MIESTO");
    }
    
    public void spustiPrechod(int IDprechod){
       //KONTROLA TOKENOV
       for (long i=1; i<=hranyDoPrechodov.size();i++){
         hranyDoPrechodov.get(i).kontrolaTokenov(IDprechod);
       }
       //ODOBER TOKENY
       for (long i=1; i<=hranyDoPrechodov.size();i++){ 
           hranyDoPrechodov.get(i).odoberTokeny(IDprechod);     
       }              
        //PRIDAJ TOKENY
       for (long i=1; i<=hranyDoMiest.size();i++){
            hranyDoMiest.get(i).pridajTokeny(IDprechod);              
       }
    }
    
    public void zistiPrechodnost(){
    for (Map.Entry mapElement : hranyDoPrechodov.entrySet()) { 
            HranaDoPrechodu pomocna_hrana = (HranaDoPrechodu) mapElement.getValue();
                     
            if(pomocna_hrana.spustitelnyPrechod() == false){
                pomocna_hrana.ziskajPrechod().nastavPrechodna(false);
            }
        } 
    }
 
    public void vymazHranuDoMiesta(HranaDoMiesta hrana){ 
        hranyDoMiest.values().remove(hrana);  
    }
    
    public void vymazHranuDoPrechodu(HranaDoPrechodu hrana){     
        hranyDoPrechodov.values().remove(hrana);  
    }
    
    public void vymazPrechod(Prechod prechod){               
        long pom = prechod.ziskajId();
         prechody.remove(pom);
     }
    
    public void vymazMiesto(Miesto miesto){
         long pom = miesto.ziskajId();
         miesta.remove(pom);
    }
}
