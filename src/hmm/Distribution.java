package hmm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * permet de g�rer une densit� de probabilit� sur des Objets E quelconques
 * 
 * @author vthomas
 * 
 */
public class Distribution<E> {

	/**
	 * liste de probabilit� discretes Associe aux elements une densit�
	 */
	private Map<E, Double> prob;

	/**
	 * retourne la proba associee a l'element passe en parametre
	 */
	public double getProba(E element) {
		// si l'element est dans la densite, on retourne la proba
		if (this.prob.containsKey(element))
			return (this.prob.get(element));

		// element n'exite pas ==> proba nulle (matrice creuses)
		return (0);
	}

	/**
	 * modifie la proba associee a l'element passe en parametre
	 */
	public void setProba(E element, double p) {
		this.prob.put(element, p);
	}

	/**
	 * Constructeur genere une densit� vide
	 * 
	 * attention, ce n'est pas une vraie densit� (somme des proba != 1)
	 * 
	 * @liste liste des �tats de la densit�
	 */
	public Distribution() {
		prob = new HashMap<E, Double>();
	}

	/**
	 * Constructeur genere une densit� avec les memes �tats mais probabilit�s nulles
	 * 
	 * @param liste
	 *            des �tats de la densit�
	 */
	public Distribution(Distribution<E> d) {
		prob = new HashMap<E, Double>();

		// creer les probabilit�s constantes � chaque �tat
		for (E s : d.prob.keySet()) {
			prob.put(s, 0.);
		}

	}

	/**
	 * permet de normaliser une densit� de probabilite (somme des proba=1)
	 */
	public void normalise() {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************

		double tmp = 0;

		for (Double e : prob.values()) {
			// tmp = tmp + e;
			e = e / calculNorme();
		}

		// throw new Error(); // ** A COMPLETER **
	}

	/**
	 * permet de calculer la norme d'une densite en thoerie retourne 1 mais peut
	 * servir pour normaliser
	 * 
	 * @return somme des probas de la densit�
	 */
	public double calculNorme() {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************

		double tmp = 0;

		for (Double e : prob.values()) {
			tmp = tmp + e;
		}

		return tmp;
		// throw new Error(); // ** A COMPLETER **
	}

	/**
	 * fait un tirage aleatoire selon cette densit�
	 * 
	 * @return l'indice qui aura �t� selectionn�
	 */
	public E tirage() {
		// **********************************************************************************
		// TODO a faire par etudiants
		// **********************************************************************************
		Random r = new Random();
		return (E) prob.get(r.nextDouble());

		// throw new Error(); // ** A COMPLETER **
	}

	/**
	 * methode toString
	 */
	public String toString() {
		String res = "";
		for (E clef : this.prob.keySet()) {
			res += clef + "->" + (((int) (this.prob.get(clef) * 1000)) * 1. / 1000);
			res += ", ";
		}
		return (res);
	}

}