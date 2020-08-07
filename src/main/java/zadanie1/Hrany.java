

package zadanie1;

/**
 *
 * @author Lukáš
 */
public abstract class Hrany {
   
    private Miesto miesto;
    private Prechod prechod;
    private int nasobnost;
    private String typ;

    public Hrany(Prechod prechod, Miesto miesto,int nasobnost, String typ) {
       this.prechod = prechod;
       this.miesto = miesto;
       this.nasobnost = nasobnost;
       this.typ = typ;
    }
            
    public int ziskajNasobnost() {
        return nasobnost;
    }

    public Miesto ziskajMiesto() {
        return miesto;
    }

    public Prechod ziskajPrechod() {
        return prechod;
    }
    
    public String ziskajTyp(){
        return typ;
    }
    
    
    public void pridajTokeny(long prechodID){         
      if(this.prechod.ziskajId() == prechodID){
        this.miesto.nastavTokeny(this.miesto.ziskajTokeny() + this.nasobnost);
      }
    }
    
    public void odoberTokeny(long prechodID){         
        
        if(this.prechod.ziskajId() == prechodID){
           
           if(typ.equals("regular")){this.miesto.nastavTokeny(this.miesto.ziskajTokeny() - this.nasobnost);
              //  System.out.println("BOLA V REGULAR");
           }else{
                this.miesto.nastavTokeny(0);
             //   System.out.println("BOLA V RESET");
           }
        }
    }
    
    public void kontrolaTokenov(long IDprechod){
        
        if(this.prechod.ziskajId() ==  IDprechod){
            if(!this.typ.equals("reset")){ 
                if(this.nasobnost > miesto.ziskajTokeny())
                    throw new IllegalArgumentException("Hrana " + miesto.ziskajNazov() + " do " +  prechod.ziskajNazov() + " ma vacsiu nasobnost ako " + miesto.ziskajNazov() + " tokenov");
            }     
        }
    }
   
    public boolean spustitelnyPrechod(){
       if(!this.typ.equals("reset")){
          if(this.nasobnost > miesto.ziskajTokeny())return false;
       }
       return true;       
    }
}
