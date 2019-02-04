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
		State[] ss = new State[8];

		for (int i = 0; i < 8; i++) {
			ss[i] = new State(i);
		}

		// Distribution d = new Distribution<>();
		//
		// d.setProba(ss[1], 0.70);
		int z = 0;
		for (int i = 0; i < 8; i++) {
			ss[i] = new State(i);
			Distribution d = new Distribution<>();
			for (int j = 0; j < 8; j++) {
				// ss[i] = new State(i);
				// Distribution d = new Distribution<>();

				int p = (j + i) % 8;
				// System.out.print(p + " ");
				// System.out.print(ss[p].toString()+" ");
				switch (j) {
				default:
					d.setProba(ss[p], 0);
					break;
				case 0:
					// d.setProba(ss[(Math.abs(i+j) % 8)], 0.70);
					// d.setProba(ss[(Math.abs(i+j) % 8)], 0.70);
					d.setProba(ss[p], 0.20);
					break;
				case 1:
					// d.setProba(ss[(Math.abs(i+j) % 8)], 0.70);
					// d.setProba(ss[(Math.abs(i+j) % 8)], 0.70);
					d.setProba(ss[p], 0.70);
					break;
				case 7:
					// d.setProba(ss[(Math.abs(i+j) % 8)], 0.70);
					// d.setProba(ss[(Math.abs(i+j) % 8)], 0.70);
					d.setProba(ss[p], 0.10);

					break;

				}
				// d.setProba(ss[j % 8], 0.70);

			}

			// System.out.println();
			transition.put(ss[(i)], d);
		}

		for (State sd : ss) {
			System.out.println(sd.toString() + " " + transition.get(sd).toString());
		}

		// creer la table de transition
		// throw new Error(); // ** A COMPLETER **
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
		// this.transition = new HashMap<>();
		// this.transition.put(sDepart, new Distribution());
		// this.transition.put(sArrivee, new Distribution());
		// sDepart.retournerCoord();
		// Distribution d = transition.get(sDepart);
		// return d.getProba(sArrivee);
		Distribution<State> t = transition.get(sDepart);
		double g = t.getProba(sArrivee);
		return g;
		// throw new Error(); // ** A COMPLETER **
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
		State win = null;
		// int num = 0;
		double kom = 0;
		for (int i = 0; i < State.getAll().size(); i++) {
			if (kom < probaTransition(depart, State.getAll().get(i))) {
				kom = probaTransition(depart, State.getAll().get(i));
				win = State.getAll().get(i);
			}
		}
		// for (State s : State.getAll()){

		// }

		// throw new Error(); // ** A COMPLETER **
		return win;
	}

}
