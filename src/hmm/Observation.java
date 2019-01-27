package hmm;

import java.util.ArrayList;
import java.util.List;

/**
 * classe qui permet de gerer les observations
 * 
 * devra implementer hahscode et equals pour les hashmap
 * 
 * @author vthomas
 *
 */
public class Observation {

	/**
	 * position des capteurs
	 */
	public final static int POSITION_CAPTEUR_1 = 1;
	public final static int POSITION_CAPTEUR_2 = 4;

	// TODO

	/**
	 * l'ensemble des observations
	 */
	private static ArrayList<Observation> allO;

	/**
	 * constructeur observation
	 */
	public Observation(boolean[] bs) {
		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * constructeur observation
	 */
	public Observation(boolean b1, boolean b2) {
		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * retourne toutes les observations patron singleton.
	 */
	public List<Observation> getAll() {
		// si pas cree, on cree
		// if (allO == null) {
		// // creation des etats
		// allO = new ArrayList<>();
		// for (int i = 0; i < 8; i++)
		// allO.add(new Observation(i));
		// }

		// retourne une copie
		return (List<Observation>) allO.clone();
	}

	// TODO

}
