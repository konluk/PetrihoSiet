


package zadanie1;

/**
 *
 * @author Lukáš
 */
public class Prechod extends Vrchol{
 
    private boolean prechodna = true;
    
    public Prechod(long id, String nazov, short x, short y) {
        super(id, nazov, x, y);
    }

    public boolean zistiPrechodna() {
        return prechodna;
    }

    public void nastavPrechodna(boolean prechodna) {
        this.prechodna = prechodna;
    }
    
    
}
