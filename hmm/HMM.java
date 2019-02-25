package hmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		if (r.nextDouble() >= 0.5)
			this.matriceTransition = new ProcessusTransition(42);
		else
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
	 * méthode qui calcule la densite de probabilité aprés t pas de temps en
	 * prenant en compte la liste des observations reçues
	 * 
	 * @param d
	 *            la densite initiale
	 * @param l
	 *            la liste des obsrevations (la taille correspond au nombre de
	 *            pas de temps)
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
	 * deux phases --> une phase aller pour propagare les probabilités --> une
	 * phase retour pour remonter le meilleur chemin
	 * 
	 * @param obs
	 *            la séquence d'observations
	 * @return la séquence d'états expliquant au mieux la séquence
	 *         d'observations
	 */
	public ArrayList<State> viterbi(ArrayList<Observation> obs, Distribution<State> initiale) {
		// **********************************************************************************
		// **********************************************************************************
		// ArrayList<ArrayList<Pair<State, Double>>> viterbiTable = new
		// ArrayList<>();
		//Distribution<State> ds = new Distribution<>();
		ArrayList<Distribution<State>> ads = new ArrayList<>();
		ads.add(new Distribution<>(initiale));
		for (State s : State.getAll()) {
			Double prob = initiale.getProba(s);
			
			ads.get(0).setProba(s, prob);
			// viterbiTable.add(new ArrayList<>());
			// viterbiTable.get(s.num_etat).add(0, new Pair<>(null, prob));
			//ds.setProba(s, prob);
			
			
		}

		// propagation des probabilitées
		for (int index_obs = 0; index_obs < obs.size(); index_obs++) {
			for (State eArrivee : State.getAll()) {
				int p = 0 ;
				double max = -1d;
				State max_state = null;
				for (State eDepart : State.getAll()) {
					/*
					 * double tmp_prob =
					 * viterbiTable.get(eDepart.num_etat).get(index_obs).
					 * getValue() matriceTransition.probaTransition(eDepart,
					 * eArrivee)
					 * matriceObservation.probaObservation(obs.get(index_obs),
					 * eArrivee);
					 */
					double tmp = ads.get(index_obs).getProba(eDepart)* matriceTransition.probaTransition(eDepart, eArrivee)
							* matriceObservation.probaObservation(obs.get(index_obs), eArrivee);
					
					if (tmp > max) {
						max = tmp;
						max_state = eDepart;
						
					}
				}
				//viterbiTable.get(eArrivee.num_etat).add(new Pair<>(max_state, max));
				//ds.setProba(max_state, max);
				ads.get(index_obs).setProba(eArrivee, max);

			}
		}

		// trouver le max de la derniere colonne de la table viterbi
		ArrayList<State> serie_etat = new ArrayList<>();
		Distribution<State> dist = new Distribution<>(initiale);
		double max = -1;
		int index_max_prob_state = -1;
		for (State s : State.getAll()) {
			/*if (viterbiTable.get(s.num_etat).get(obs.size()).getValue() > max) {
				index_max_prob_state = s.num_etat;
				max = viterbiTable.get(s.num_etat).get(obs.size() - 1).getValue();
			}*/
			
			
		}

		// se servire de ce max pour construire la serie des etats
		serie_etat.add(0, State.getAll().get(index_max_prob_state));
		// construction de la serie des etats
		for (int col = obs.size(); col > 0; col--) {

			//State etat = viterbiTable.get(index_max_prob_state).get(col).getKey();
			serie_etat.add(0, etat);
			index_max_prob_state = etat.num_etat;

		}

		return serie_etat;
	}

}
