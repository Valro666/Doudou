package hmm;

import java.util.Observable;

/**
 * un systeme represente une instance d'un systeme modelise par un HMM.
 * <p>
 * un systeme est d�fini par
 * <ul>
 * <li>le modele sous-jacent (HMM)
 * <li>l'etat reel courant du systeme
 * <li>la connaissance qu'on a sur l'etat du systeme
 * </ul>
 *
 */
public class Systeme extends Observable {

	/**
	 * attribut modele hmm
	 */
	private HMM modele;

	/**
	 * la distrubution courante
	 */
	public Distribution<State> distributionCourante;

	/**
	 * la derniere observation vue
	 */
	public Observation lastObservation;

	/**
	 * etat actuel
	 */
	public State etatCourant;

	/**
	 * construit un systeme � partir d'un modele de HMM
	 * 
	 * @param hmm
	 *            le modele a partir duquel construire un systeme
	 * @param initiale
	 *            la distribution initiale sur l'etat
	 */
	public Systeme(HMM hmm, Distribution<State> initiale) {
		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * permet de faire evoluer l'etat courant du systeme d'un pas de temps et
	 * recupere l'observation
	 * <ul>
	 * <li>effectue une transition
	 * <li>effectue une observation
	 * </ul>
	 * 
	 * @return couple etat t+1 , observation a l'instant t+1
	 */
	public Observation evoluerEtatReel() {
		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * met a jour le belief apres avoir re�u une observation
	 * 
	 * @param o
	 *            observation re�ue
	 */
	public void mettreAjourBelieef(Observation o) {
		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * permet de faire evoluer l'etat reel et la connaissance sur l'etat
	 */
	public void evoluerSysteme() {
		// throw new Error(); // ** A COMPLETER **

		// metter � jour la vue
		setChanged();
		notifyObservers();
	}

}
