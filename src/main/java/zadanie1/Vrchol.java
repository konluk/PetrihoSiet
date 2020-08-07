


package zadanie1;

/**
 *
 * @author Lukáš
 */
public abstract class Vrchol {
    
    private Long id;
    private String nazov;
    
    private short x;
    private short y;

    public Vrchol(Long id, String nazov, short x, short y) {
        this.id = id;
        this.nazov = nazov;
        this.x = x;
        this.y = y;
    }

    public long ziskajId() {
        return id;
    }

    public String ziskajNazov() {
        return nazov;
    } 

    public short ziskajX() {
        return x;
    }

    public short ziskajY() {
        return y;
    }
    
}
