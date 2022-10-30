import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Dictionnaire {

    private final HTNaive dico;

    public Dictionnaire(int m) {
        this.dico = new HTNaive(m);
    }

    public Dictionnaire(String fileName, int m) {
        this(m);
        rempliListe(fileName);
    }

    public Dictionnaire(String fileName, float d) {
        this.dico = new HTNaive(calculeTailleListe(fileName), d);
        rempliListe(fileName);
    }

    private static BigInteger stringToBigInteger2(String s) {
        BigInteger res = BigInteger.ZERO;
        for (int i = 0; i < s.length(); i++) {
            long result = (long) (s.charAt(i) * Math.pow(255, s.length() - i - 1));
            res = res.add(BigInteger.valueOf(result));
        }
        return res;
    }

    private static BigInteger stringToBigInteger(String s) {
        BigInteger res = BigInteger.ZERO;
        if (s.isEmpty()) return res;
        res = res.add(BigInteger.valueOf(s.charAt(0)));
        for (int i = s.length() - 2; i >= 0; i--) {
            res = res.multiply(BigInteger.valueOf(255));
            res = res.add(BigInteger.valueOf(s.charAt(i)));
        }
        return res;
    }

    public boolean ajout(String s) {
        final BigInteger valeur = Dictionnaire.stringToBigInteger(s);
        if (this.dico.contient(valeur)){
            return false;
        } else {
            this.dico.ajoutForce(valeur);
            return true;
        }
    }
    public boolean contient(String s){
        final BigInteger valeur = Dictionnaire.stringToBigInteger(s);
        return this.dico.contient(valeur);
    }

    private int calculeTailleListe(String fileName) {
        final File file = new File(fileName);
        Scanner sc;
        int size = 0;
        try {
            sc = new Scanner(file);
            sc.useDelimiter("[ \\n,;:.!?\\-]");
            while (sc.hasNext()) {
                sc.next();
                size++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : fichier non trouvé.\n" + e.getMessage());
        }
        return size;
    }

    private void rempliListe(String fileName) {
        final File file = new File(fileName);
        final Scanner sc;
        try {
            sc = new Scanner(file);
            sc.useDelimiter("[ \\n,;:.!?\\-]");
            while (sc.hasNext()) {
                //final String s = sc.next();
                //final BigInteger valeur = Dictionnaire.stringToBigInteger(s);
                ajout(sc.next());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : fichier non trouvé.\n" + e.getMessage());
        }
    }

    public int getMaxSize() {
        return this.dico.getMaxSize();
    }

    public int getCardinal() {
        return this.dico.getCardinal();
    }

    public int getNbListes() {
        return this.dico.getNbListes();
    }

    public void resetTotalTimeContient() {
        this.dico.resetTotalTimeContient();
    }

    public void resetTotalTimeH() {
        this.dico.resetTotalTimeH();
    }

    public long getTotalTimeContient() {
        return dico.getTotalTimeContient();
    }

    public long getTotalTimeH() {
        return dico.getTotalTimeH();
    }

}
