import java.math.BigInteger;

public class ListeBigI {

	private Maillon tete;
	private int longueur;

	/**
	 * Constructeur d'une liste vide
	 */
	public ListeBigI() {
		this.tete = null;
		this.longueur = 0;
	}

	/**
	 * Constructeur d'une liste a partir d'un maillon
	 */
	public ListeBigI(Maillon m) { // OBSOLETE : NE PLUS UTILISER
		this.tete = m;
		this.longueur = 1;
	}

	/**
	 * Constructeur d'une liste a un seul element
	 */
	public ListeBigI(BigInteger x) {
		this.tete = new Maillon(x); // ou bien: this(new Maillon(x));
		this.longueur = 1;
	}

	/**
	 * @param tabListe est un tableau contenant les elements de la liste
	 *                 Pre-requis : aucun
	 */
	public ListeBigI(BigInteger[] tabListe) {
		this();
		this.longueur = tabListe.length;
		if (tabListe.length > 0) {
			this.tete = new Maillon(tabListe[0]);
			Maillon curThis = this.tete;
			for (int i = 1; i < tabListe.length; i++) {
				curThis.setSuiv(new Maillon(tabListe[i])); // creation et accrochage du maillon (encore vide) suivant
				curThis = curThis.getSuiv();
			}
		}
	}

	/**
	 * Prerequis: aucun
	 * construit une liste completement disjointe de la liste l
	 */
	public ListeBigI(ListeBigI l) { // constructeur par recopie profonde
		this();
		this.longueur = l.longueur();
		if (!l.estVide()) {

			this.tete = new Maillon(l.tete.getVal());
			Maillon curThis = this.tete;
			Maillon curL = l.tete.getSuiv();

			while (curL != null) {
				curThis.setSuiv(new Maillon(curL.getVal())); // creation et accrochage du maillon suivant
				curThis = curThis.getSuiv();
				curL = curL.getSuiv();
			}
		}
	}

	public boolean estVide() {
		return (this.tete == null);
	}

	//public int valTete () {
	//	return this.tete.getVal();
	//}

	public void ajoutTete(BigInteger x) {
		Maillon m = new Maillon(x);
		m.setSuiv(this.tete);
		this.tete = m;
		this.longueur++;
	}

	public void ajoutListe(ListeBigI l) {
		Maillon cur = l.tete;
		this.longueur += l.longueur();
		while (cur != null) {
			ajoutTete(cur.getVal());
			cur = cur.getSuiv();
		}
	}

	public BigInteger supprTete() {
		//pré this non vide
		BigInteger res = tete.getVal();
		tete = tete.getSuiv();
		this.longueur--;
		return res;
	}

	public boolean contient(BigInteger x) {
		Maillon courant = this.tete;
		while (courant != null && !(courant.getVal().equals(x))) {
			courant = courant.getSuiv();
		}
		return courant != null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder("(");
		Maillon courant = this.tete;
		while (courant != null) {
			s.append(courant.getVal()).append((courant.getSuiv() != null) ? ", " : "");
			courant = courant.getSuiv();
		}
		return s + ")";
	}

	/**
	 * Longueur d'une liste
	 */
	public int longueur() {
		return this.longueur;
	}


	/**
	 * Ajoute @param en fin de liste
	 */
	public void ajoutFin(BigInteger n) {
		if (this.estVide()) {
			this.tete = new Maillon(n);
		} else {
			Maillon courant = this.tete;
			while (courant.getSuiv() != null) {
				courant = courant.getSuiv();
			}
			courant.setSuiv(new Maillon(n));
		}
		this.longueur++;
	}


	/**
	 * Supprime l'elt contenant la premiere occurrence de @param
	 */
	public void supprOcc(BigInteger n) {
		if (!this.estVide() && this.tete.getVal().equals(n)) { // suppression en tête
			this.tete = this.tete.getSuiv();
			this.longueur--;
		} else {
			Maillon prev = this.tete;
			Maillon current = prev.getSuiv();
			while (current != null && current.getVal() != n) {
				prev = current;
				current = current.getSuiv();
			}
			if (current != null) {  // current.getVal() == n
				prev.setSuiv(current.getSuiv());
				this.longueur--;
			}
		}
	}

	/**
	 * Retourne l'élement en tête
	 */
	public Maillon getTete() {
		return tete;
	}

} // end class
