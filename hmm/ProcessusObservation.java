package hmm;

import java.util.HashMap;
import java.util.Map;

public class ProcessusObservation {

	/**
	 * les donnees du probleme
	 */
	static double VOIR_SI_PRESENT = 0.9;
	static double VOIR_SI_ABSENT = 0.2;

	/**
	 * matrice d'observation qui associe à un état et une distribution sur les
	 * observations obtenues dans cet état
	 * <p>
	 * <ul>
	 * <li>clef: etat s_t
	 * <li>valeur : distribution sur O_t => P(O_t/S_t)
	 * </ul>
	 * <p>
	 */
	protected Map<State, Distribution<Observation>> observation;

	/**
	 * construction de la matrice d'observation
	 */
	public ProcessusObservation() {
		throw new Error(); // ** A COMPLETER ** 

	}

	// TODO

	/**
	 * 
	 * matrice d'observation retourne la probabilité d'observer o dans l'état s
	 * 
	 * @param o
	 *            observation possible
	 * @param s
	 *            état reel
	 * @return probabilité d'observer o sachant s
	 */
	public double probaObservation(Observation o, State s) {
		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * permet d'observer a partir d'un etat
	 * 
	 * @param depart
	 *            etat a partir duquel on observer
	 * @return observation faite
	 */
	public Observation observer(State depart) {
		throw new Error(); // ** A COMPLETER **
	}

}
