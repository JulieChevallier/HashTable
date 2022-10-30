import java.math.BigInteger;

public class HTNaive {

    private final ListeBigI[] t;
    private int longeur;

    private long totalTimeH = 0;
    private long totalTimeContient = 0;

    public HTNaive(int m) {
        this.t = new ListeBigI[m];
        this.longeur = 0;
        for (int i = 0; i < m; i++)
            t[i] = new ListeBigI();
    }

    public HTNaive(ListeBigI l, int m) {
        this(m);
        this.ajoutListe(l);
    }

    public HTNaive(ListeBigI l, double f) {
        this((int) (tempSize(l) * f));
    }

    public HTNaive(int tailleListe, double f) {
        this((int) (tailleListe * f));
    }

    /*
     * Retourne la longeur de l si tous les éléments de l sont distinct sinon retourne 0
     */

    private static int tempSize(ListeBigI listeBigI) {
        final ListeBigI copyListe = new ListeBigI(listeBigI);
        final HTNaive elements = new HTNaive(1);
        for (int i = 0; i < listeBigI.longueur(); i++)
            elements.ajout(copyListe.supprTete());
        return elements.getCardinal();
    }

    /**
     * Retourne la "hauteur" de la liste de hachage
     */
    private int h(BigInteger u) {
        long start = System.currentTimeMillis();
        final BigInteger m = BigInteger.valueOf(t.length);
        int value = u.remainder(m).intValue();
        totalTimeH += System.currentTimeMillis() - start;
        return value;
    }

    /**
     * Retourne vrai si la table de hachage contient l'élement u, sinon faux
     */
    public boolean contient(BigInteger u) {
        int h = this.h(u);
        long start = System.currentTimeMillis();
        boolean value = this.t[h].contient(u);
        totalTimeContient += System.currentTimeMillis() - start;
        return value;
    }

    /**
     * Action - ajoute u dans la table de hachage
     */
    public boolean ajout(BigInteger u) {
        if (!contient(u)) {
            this.t[h(u)].ajoutTete(u);
            longeur++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Pré-requis : u ne se trouve pas dans la table de hachage
     * Action - ajoute u dans la table de hachage
     */
    public void ajoutForce(BigInteger u) {
        this.t[h(u)].ajoutTete(u);
        longeur++;
    }

    /**
     * Pré-requis : L est non null
     * Action : ajoute à la table les éléments de L qui n'y sont pas déjà. Attention, L ne doit pas être modifié
     */
    public void ajoutListe(ListeBigI L) {
        final ListeBigI copyL = new ListeBigI(L);
        Maillon curseur = copyL.getTete();
        while (curseur != null) {
            ajout(curseur.getVal());
            curseur = curseur.getSuiv();
        }
    }

    /**
     * Retourne le nombre d'élements de la table de hachage
     */
    public int getCardinal() {
        return longeur;
    }

    /**
     * Retourne la liste ayant le plus d'élements
     */
    public int getMaxSize() {
        int max = t[0].longueur();
        for (int i = 1; i < t.length; i++)
            if (t[i].longueur() > max)
                max = t[i].longueur();
        return max;
    }

    /**
     * Retourne une Liste retournant tous les éléments de la table.
     */
    public ListeBigI getElements() {
        ListeBigI listeTousElt = new ListeBigI();
        ListeBigI curListe;
        Maillon teteListe;
        for (ListeBigI listeBigI : t) {
            curListe = listeBigI;
            teteListe = curListe.getTete();
            while (teteListe != null) {
                listeTousElt.ajoutTete(teteListe.getVal());
                teteListe = teteListe.getSuiv();
            }
        }
        return listeTousElt;
    }

    /**
     * Retourne le nombre de listes (vides ou non) de la table
     */
    public int getNbListes() {
        return t.length;
    }

    /**
     * Retourne une chaine de la forme :
     * [
     *      0 - (...)
     *      1 - (...)
     *      2 - (...)
     *      ...
     * ]
     */
    @Override
    public String toString() {
        StringBuilder ch = new StringBuilder();

        ch.append("[").append("\n");
        for (int i = 0; i < t.length; i++)
            ch.append("\t").append(i).append(" - ").append(t[i]).append("\n");
        ch.append("]");

        return ch.toString();
    }

    /**
     * Retourne la table sous la forme :
     * t[3] : *****
     * t[7] : *******
     * t[10] : ***
     * Dans le cas où t[3] a une longueur de 5, t[7] a une longueur de 7 et t[10] a une longueur de 3
     */
    public String toStringV2() {
        StringBuilder ch = new StringBuilder();

        ch.append("[").append("\n");
        for (int i = 0; i < t.length; i++) {
            ch.append("\t").append(i).append(" - ").append("*".repeat(t[i].longueur())).append("\n");
        }
        ch.append("]");

        return ch.toString();
    }

    public long getTotalTimeContient() {
        return totalTimeContient;
    }

    public long getTotalTimeH() {
        return totalTimeH;
    }

    public void resetTotalTimeContient() {
        this.totalTimeContient = 0;
    }

    public void resetTotalTimeH() {
        this.totalTimeH = 0;
    }
}
