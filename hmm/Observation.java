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

	public boolean cpt1 = false;
	public boolean cpt2 = false;

	int num_o;

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

	public Observation(int i) {
		// throw new Error(); // ** A COMPLETER **
		this.num_o = (i + 8) % 8;
	}

	/**
	 * constructeur observation
	 */
	public Observation(boolean b1, boolean b2) {

		cpt1 = b1;
		cpt2 = b2;
		// throw new Error(); // ** A COMPLETER **
	}

	public Observation(int i, boolean b1, boolean b2) {
		this.num_o = (i + 8) % 8;
		cpt1 = b1;
		cpt2 = b2;
		// throw new Error(); // ** A COMPLETER **
	}

	/**
	 * retourne toutes les observations patron singleton.
	 */
	public static List<Observation> getAll() {
		// si pas cree, on cree
		if (allO == null) {
			// creation des etats
			allO = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				Observation o = new Observation(i);
				switch (i) {
				// default:
				case 1:
					o.cpt1 = false;
					o.cpt2 = false;
					break;
				case 2:
					o.cpt1 = false;
					o.cpt2 = true;
					break;
				case 3:
					o.cpt1 = true;
					o.cpt2 = false;
					break;
				default:
				case 0:
					o.cpt1 = true;
					o.cpt2 = true;
					break;
				}
				allO.add(o);
			}
		}

		// retourne une copie
		return (List<Observation>) allO.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num_o;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (num_o != other.num_etat)
			return false;
		return true;
	}

	public String toString() {
		String s = "";

		if (this.cpt1)
			s = s + "1";
		else
			s = s + "0";

		if (this.cpt2)
			s = s + "1";
		else
			s = s + "0";

		return "(O" + this.num_o + " " + s+")";
	}
	// TODO

}
