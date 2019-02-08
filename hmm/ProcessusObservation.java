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
		// throw new Error(); // ** A COMPLETER **
		observation = new HashMap<>();
		double[][] tmp = new double[4][8];

		double[][] tmp2 = new double[4][8];

		int z = 0;

		for (int x = 0; x < tmp.length; x++) {
			for (int y = 0; y < tmp[x].length; y++) {
				if (z < 2) {

					if (y == 1) {
						tmp[x][y] = lapasactiv;
					} /*
						 * else if
						 * 
						 * (y == 4) { tmp[x][y] = paslapasactiv; }
						 */else {
						tmp[x][y] = paslapasactiv;
					}
				} else {
					if (y == 1) {
						tmp[x][y] = laactiv;
					} else {
						tmp[x][y] = paslaactiv;
					}
				}

			}
			z++;
		}
		z = 0;
		for (int x = 0; x < tmp2.length; x++) {
			for (int y = 0; y < tmp2[x].length; y++) {
				if (z % 2 == 0) {

					if (y == 4) {
						tmp2[x][y] = lapasactiv;
					} else {
						tmp2[x][y] = paslapasactiv;
					}
				} else {
					if (y == 4) {
						tmp2[x][y] = laactiv;
					} else {
						tmp2[x][y] = paslaactiv;
					}
				}

			}
			z++;
		}

		// for (int x = 0; x < tmp.length; x++) {
		// for (int y = 0; y < tmp[x].length; y++) {
		// System.out.print(tmp[x][y] + " ");
		// }
		// System.out.println();
		// }
		// System.out.println();

		for (int x = 0; x < tmp2.length; x++) {
			for (int y = 0; y < tmp2[x].length; y++) {
				tmp2[x][y] = tmp2[x][y] * tmp[x][y];

				// System.out.print(tmp2[x][y] + " ");
			}
			// System.out.println();
		}
		List<Observation> oll = Observation.getAll();
		List<State> all = State.getAll();

		Distribution d = new Distribution<>();

		// for (Observation o : oll) {
		for (int j = 0; j < tmp2[0].length; j++) {
			d = new Distribution<>();
			for (int i = 0; i < tmp2.length; i++) {
				d.setProba(oll.get(i), tmp2[i][j]);
			}
			observation.put(all.get(j), d);
		}
		// }

		// System.out.println(d);
		// System.out.println(observation);

	}

	double bidule(int a, int b) {
		if (a == 1) {
			return lapasactiv;
		} else if

		(b == 4) {
			return paslapasactiv;
		} else {
			return paslapasactiv;
		}
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

		// return 0.0;
		// throw new Error(); // ** A COMPLETER **
	}

	/**
	 * permet d'observer a partir d'un etat
	 * 
	 * @param depart
	 *            etat a partir duquel on observer
	 * @return observation faite
	 */
	public Observation observer(State depart) {
		// Random r = new Random();
		// double d = r.nextDouble();

		Distribution<Observation> ert = this.observation.get(depart);

		return ert.tirage();

		// return null;
		// throw new Error(); // ** A COMPLETER **
	}

}
