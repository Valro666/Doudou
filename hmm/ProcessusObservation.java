package hmm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProcessusObservation {

	/**
	 * les donnees du probleme
	 */
	static double VOIR_SI_PRESENT = 0.9;
	static double VOIR_SI_ABSENT = 0.2;

	static double lapasactiv = 0.1;
	static double laactiv = 0.9;
	static double paslaactiv = 0.2;
	static double paslapasactiv = 0.8;

	double fneg = 0.1;
	double vneg = 0.9;
	double fpos = 0.2;
	double vpos = 0.8;

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
	public Map<State, Distribution<Observation>> observation;

	/**
	 * construction de la matrice d'observation
	 */
	public ProcessusObservation() {

		observation = new HashMap<>();

		List<Observation> oll = Observation.getAll();
		List<State> all = State.getAll();

		Distribution<Observation> oo = new Distribution<Observation>();
		oo = new Distribution<>(oo);

		for (State lll : all) {
			observation.put(lll, new Distribution<>(oo));
		}

		for (int j = 0; j < all.size(); j++) {
			if ((j == 1) || (j == 4)) {
				Distribution<Observation> a = observation.get(all.get(j));
				if (j == 1) {
					for (int i = 0; i < 4; i++) {
						a.setProba(oll.get(i), i);
						if (oll.get(i).num_o == 1) {
							a.setProba(oll.get(i), lapasactiv * paslapasactiv);
						}
						if (oll.get(i).num_o == 2) {
							a.setProba(oll.get(i), lapasactiv * paslaactiv);
						}
						if (oll.get(i).num_o == 3) {
							a.setProba(oll.get(i), laactiv * paslapasactiv);
						}
						if (oll.get(i).num_o == 0) {
							a.setProba(oll.get(i), laactiv * paslaactiv);
						}
					}
				} else {
					for (int i = 0; i < 4; i++) {
						a.setProba(oll.get(i), i);
						if (oll.get(i).num_o == 1) {
							a.setProba(oll.get(i), paslapasactiv * lapasactiv);
						}
						if (oll.get(i).num_o == 2) {
							a.setProba(oll.get(i), paslapasactiv * laactiv);
						}
						if (oll.get(i).num_o == 3) {
							a.setProba(oll.get(i), paslaactiv * lapasactiv);
						}
						if (oll.get(i).num_o == 0) {
							a.setProba(oll.get(i), paslaactiv * laactiv);
						}
					}
				}
			} else {
				Distribution<Observation> a = observation.get(all.get(j));
				for (int i = 0; i < 4; i++) {
					a.setProba(oll.get(i), i);
					if (oll.get(i).num_o == 1) {
						a.setProba(oll.get(i), paslapasactiv * paslapasactiv);
					}
					if (oll.get(i).num_o == 2) {
						a.setProba(oll.get(i), paslapasactiv * paslaactiv);
					}
					if (oll.get(i).num_o == 3) {
						a.setProba(oll.get(i), paslaactiv * paslapasactiv);
					}
					if (oll.get(i).num_o == 0) {
						a.setProba(oll.get(i), paslaactiv * paslaactiv);
					}
				}
			}
		}
	}

	Distribution<Observation> dist() {
		return null;
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
		return this.observation.get(s).getProba(o);
	}

	/**
	 * permet d'observer a partir d'un etat
	 * 
	 * @param depart
	 *            etat a partir duquel on observer
	 * @return observation faite
	 */
	public Observation observer(State depart) {
		Distribution<Observation> ert = this.observation.get(depart);
		return ert.tirage();
	}
	public String toString() {
		return "" + observation + "";
	}

}
