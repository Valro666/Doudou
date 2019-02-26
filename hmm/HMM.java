package hmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.util.Pair;

/**
 * classe qui permet de modéliser un HMM
 * 
 * un HMM est décrit par - un nombre d'états - un nombre d'observations
 * possibles - une matrice de transition - une matrice d'observation
 * 
 * @author vthomas
 * 
 */
public class HMM {

	/**
	 * la classe ou est stockee la matrice de transition P( S_t / S_t-1)
	 */
	public ProcessusTransition matriceTransition;

	/**
	 * la classe ou est stockee la matrice d'observation P(O_t / O_t-1)
	 */
	public ProcessusObservation matriceObservation;

	/**
	 * constructeur vide
	 */
	public HMM() {
		// creation des lois de probabilite
		this.matriceObservation = new ProcessusObservation();
		Random r = new Random();
		// if (r.nextDouble() >= 0.5)
		// this.matriceTransition = new ProcessusTransition(42);
		// else
		this.matriceTransition = new ProcessusTransition();
	}

	/**
	 * faire evoluer etat en fonction de l'état de départ
	 * 
	 * @return etat d'arrivee
	 */
	public State evoluerEtat(State depart) {
		return (this.matriceTransition.evoluerEtat(depart));
	}

	/**
	 * tire une observation en fonction de l'etat
	 * 
	 * @param depart
	 *            etat a partir duquel on observe
	 * @return Observation faite
	 */
	public Observation observer(State depart) {
		return (this.matriceObservation.observer(depart));
	}

	/**
	 * predire un belief
	 * 
	 * @param densite
	 *            à instant t-1
	 * @return densite à instant ts
	 */
	public Distribution<State> maJTransition(Distribution<State> d) {

		Distribution<State> trib = new Distribution<>();
		List<State> all = State.getAll();
		for (int i = 0; i < all.size(); i++) {
			double res = 0.0;
			for (State ll : all) {
				res = res + (this.matriceTransition.probaTransition(ll, all.get(i)) * d.getProba(ll));
			}
			trib.setProba(all.get(i), res);
		}
		trib.normalise();

		return trib;
	}

	/**
	 * permet de mettre à jour un belief par rapport à une observation
	 * 
	 * @param d
	 *            la densite actuelle
	 * @param o
	 *            l'observation percue
	 * @return la densite mise à jour
	 */
	public Distribution<State> MaJObservation(Distribution<State> d, Observation o) {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************
		Distribution<State> tmp = new Distribution<State>(d);
		List<State> all = State.getAll();
		for (State ll : all) {
			tmp.setProba(ll, d.getProba(ll) * this.matriceObservation.probaObservation(o, ll));
		}
		tmp.normalise();
		return tmp;

	}

	/**
	 * méthode qui propage la densite en prenant en compte l'observation
	 * 
	 * @param o
	 *            observation recue
	 * @return nouvelle densite
	 */
	public Distribution<State> propagation(Distribution<State> initiale, Observation o) {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************
		Distribution<State> ds = MaJObservation(maJTransition(initiale), o);
		return ds;

	}

	/**
	 * méthode qui calcule la densite de probabilité aprés t pas de temps en prenant
	 * en compte la liste des observations reçues
	 * 
	 * @param d
	 *            la densite initiale
	 * @param l
	 *            la liste des obsrevations (la taille correspond au nombre de pas
	 *            de temps)
	 * @return la nouvelle densité
	 */
	public Distribution<State> filtrage(Distribution<State> d, ArrayList<Observation> l) {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************

		Distribution<State> tmp = this.propagation(d, l.get(0));
		for (int i = 1; i < l.size(); i++) {
			tmp = this.propagation(tmp, l.get(i));
		}

		return tmp;

	}

	/**
	 * méthode qui retourne la probabilité d'observer une certaine sequence
	 * d'observations
	 * 
	 * @param l
	 *            liste des observations vues
	 * @param d
	 *            densité initiale sur les états
	 * 
	 * @return probabilite de voir la sequence passée en parametre
	 */
	public double probaSequence(Distribution<State> initiale, ArrayList<Observation> l) {
		// **********************************************************************************
		// a faire par etudiants
		// je vous conseille de commencer par écrire des commentaires
		// pour structurer votre code avant de coder reellement la méthode
		// **********************************************************************************

		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * 
	 * methode qui cherche la trajectoire expliquant au mieux les observations
	 * algorithme viterbi utilisant la programmation dynamique
	 * 
	 * deux phases --> une phase aller pour propagare les probabilités --> une phase
	 * retour pour remonter le meilleur chemin
	 * 
	 * @param obs
	 *            la séquence d'observations
	 * @return la séquence d'états expliquant au mieux la séquence d'observations
	 */

	public ArrayList<State> viterbi(ArrayList<Observation> obs, Distribution<State> initiale) {

		ArrayList<Distribution> ald = new ArrayList<>();

		ArrayList<State> als = new ArrayList<>();

		ArrayList<State[]> ali = new ArrayList<State[]>();

		als.add(initiale.maxS());

		ald.add(initiale.dedouble());
		double val = 0;
		State t = null;
		for (int i = 0; i < obs.size(); i++) {
			// for(Observation o : obs) {
			ald.add(new Distribution<>(initiale));
			State[] ert = { null, null, null, null, null, null, null, null };
			ali.add(ert);
			State Smax = null;
			double max = 0;

			for (State actu : State.getAll()) {
				/* if (ald.get(i).getProba(actu) > 0) */ {
					ArrayList<State> alt = new ArrayList<>();
					alt.add(actu);
					alt.add(actu.suivant());
					alt.add(actu.precedant());
					for (State suivant : State.getAll()) {
						double tmp = ald.get(i).getProba(actu) * this.matriceTransition.probaTransition(actu, suivant)
								* this.matriceObservation.probaObservation(obs.get(i), suivant);

						if (ald.get(i + 1).getProba(suivant) < tmp) {
							ald.get(i + 1).setProba(suivant, tmp);
							ali.get(i)[suivant.num_etat] = actu;
						}
					}
				}
			}
			ald.get(i + 1).normalise();
			als.add(Smax);
			Smax = null;
		}
		// System.out.println(als);

		// back tracking

		System.out.println(ald);

		for (State[] a : ali) {
			for (State i : a) {
				System.out.print(" " + i);
			}
			System.out.println();
		}

		for (int i = ali.size(); i > 0; i--) {
			State sd = (State) ald.get(i).maxS();
			System.out.println(sd);
		}

		return als;
	}

}
