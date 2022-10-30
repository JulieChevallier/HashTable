import java.math.BigInteger;

public class Maillon {

    private BigInteger valeur;
    private Maillon suivant;

    /**
     * Constructeur vide
     */
    public Maillon() {
        suivant = null;
    }

    /**
     * Constructeur avec la valeur
     */
    public Maillon(BigInteger n) {
        valeur = n;
        suivant = null;
    }

    public BigInteger getVal() {
        return this.valeur;
    }

    public void setVal(BigInteger v) {
        this.valeur = v;
    }

    public Maillon getSuiv() {
        return this.suivant;
    }

    public void setSuiv(Maillon m) {
        this.suivant = m;
    }

    public String toString() {
        return this.valeur + "";
    }
}
