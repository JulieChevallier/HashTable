import java.util.concurrent.ThreadLocalRandom;

public class TestHTNaive {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar Hachage.jar <file> <num_listes>");
            System.exit(1);
        } else {
            final String fileName = args[0];
            float numListes = 1;
            try {
                numListes = Float.parseFloat(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Usage: java -jar Hachage.jar <file> <num_listes>");
                System.exit(1);
            }
            long debutCreationDico = System.currentTimeMillis();
            Dictionnaire d = new Dictionnaire(fileName, numListes);
            long finCreationDico = System.currentTimeMillis();
            System.out.println("maxSize : " + d.getMaxSize());
            System.out.println("cardinal : " + d.getCardinal());
            System.out.println("nbListes : " + d.getNbListes());
            int nbRecherches = 100000;
            final ThreadLocalRandom random = ThreadLocalRandom.current();
            d.resetTotalTimeContient();
            d.resetTotalTimeH();
            long debutRecherche = System.currentTimeMillis();
            for (int i = 0; i < nbRecherches; i++) {
                int tailleMot = random.nextInt(15) + 2;
                char[] mot = new char[tailleMot];
                for (int j = 0; j < mot.length; j++)
                    mot[j] = (char) ('a' + random.nextInt(26));
                d.contient(new String(mot));
            }
            long finRecherche = System.currentTimeMillis();
            System.out.println("Temps total creation du dictionnaire : " + (finCreationDico - debutCreationDico) + " ms");
            System.out.println("Temps total methode 'h' : " + d.getTotalTimeH() + " ms");
            System.out.println("Temps total methode 'contient' : " + d.getTotalTimeContient() + " ms");
            System.out.println("Temps total de recherche : " + (finRecherche - debutRecherche) + " ms");
            System.out.println("Temps total : " + (finRecherche - debutCreationDico) + " ms");
        }
    }

}
