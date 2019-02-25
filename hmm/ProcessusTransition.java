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
	public Map<State, Distribution<State>> transition; /// a repasser en protected apres les tests nulls

	/**
	 * creation des transitions correspondant au hamster
	 */
	public ProcessusTransition() {
		System.out.println("droite");
		this.transition = new HashMap<>();
		State[] ss = new State[8];

		for (int i = 0; i < 8; i++) {
			ss[i] = new State(i);
		}

		int z = 0;
		for (int i = 0; i < 8; i++) {
			ss[i] = new State(i);
			Distribution<State> d = new Distribution<State>();
			for (int j = 0; j < 8; j++) {
				int p = (j + i) % 8;
				switch (j) {
				default:
					d.setProba(ss[p], 0);
					break;
				case 0:
					d.setProba(ss[p], 0.20);
					break;
				case 1:
					d.setProba(ss[p], 0.70);
					break;
				case 7:
					d.setProba(ss[p], 0.10);
					break;
				}
			}
			transition.put(ss[(i)], d);
		}
	}
	
	public ProcessusTransition(int o) {
		System.out.println("gauche");
		this.transition = new HashMap<>();
		State[] ss = new State[8];

		for (int i = 0; i < 8; i++) {
			ss[i] = new State(i);
		}

		int z = 0;
		for (int i = 0; i < 8; i++) {
			ss[i] = new State(i);
			Distribution<State> d = new Distribution<State>();
			for (int j = 0; j < 8; j++) {
				int p = (j + i) % 8;
				switch (j) {
				default:
					d.setProba(ss[p], 0);
					break;
				case 0:
					d.setProba(ss[p], 0.20);
					break;
				case 1:
					d.setProba(ss[p], 0.10);
					break;
				case 7:
					d.setProba(ss[p], 0.70);
					break;
				}
			}
			transition.put(ss[(i)], d);
		}
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

	@Override
	public String toString() {
		return "" + transition + "";
	}
	

}
