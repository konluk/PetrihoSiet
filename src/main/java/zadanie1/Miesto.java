


package zadanie1;

/**
 *
 * @author Lukáš
 */
public class Miesto extends Vrchol{
    
    private int tokeny;
    
    public Miesto(long id, String nazov, int tokeny, short x, short y) {
        super(id, nazov, x, y);
        this.tokeny = tokeny;
    }

    public int ziskajTokeny() {
        return tokeny;
    }

    public void nastavTokeny(int tokeny) {
        this.tokeny = tokeny;
    }
    
       
}
