package hmm;

import java.util.HashMap;
import java.util.Map;

public class ProcessusTransition {

	/**
	 * matrice de transition qui associe à un état de départ la densite
	 * d'arriver sur l'etat d'arrivee
	 * 
	 * <p>
	 * clef :etat de depart s_t<br>
	 * valeur : densite sur s_t+1 = P(S_t+1/S_t) <br>
	 * 
	 */
	protected Map<State, Distribution<State>> transition;

	/**
	 * creation des transitions correspondant au hamster
	 */
	public ProcessusTransition() {
		this.transition = new HashMap<>();

		// creer la table de transition
		//throw new Error(); // ** A COMPLETER **
	}

	/**
	 * matrice de transition P(arrivee / depart)
	 * 
	 * @param sArrivee
	 *            etat d'arrivée
	 * @param sDepart
	 *            etat de depart
	 * @return probabilité d'arrivée en sArrivée etant donné qu'on se trouve en
	 *         sDepart
	 */
	public double probaTransition(State sDepart, State sArrivee) {
		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * retourne un etat d'arrivee a partir d'un etat de depart en
	 * echantillonnant le modele de transition
	 * 
	 * @param s
	 *            etat de depart
	 * @return etat d'arrivee
	 */
	public State evoluerEtat(State depart) {
		throw new Error(); // ** A COMPLETER **
	}

}
