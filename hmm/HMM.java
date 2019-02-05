package hmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * classe qui permet de mod�liser un HMM
 * 
 * un HMM est d�crit par - un nombre d'�tats - un nombre d'observations
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
		// this.matriceObservation = new ProcessusObservation();
		this.matriceTransition = new ProcessusTransition();
	}

	/**
	 * faire evoluer etat en fonction de l'�tat de d�part
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
	 *            � instant t-1
	 * @return densite � instant ts
	 */
	public Distribution<State> maJTransition(Distribution<State> d) {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************

		// this.matriceTransition.transition;

		ArrayList<Double> ald = new ArrayList<>();

		List<State> s = State.getAll();

		List<State> all = State.getAll();

		// d.getProba(null);
		Distribution ert = new Distribution(d);

		for (int i = 0; i < all.size(); i++) {
			double res = 0.0;
			Object tmp = null;
			for (State ll : all) {
				tmp = ll;
				// ald.add(d.getProba(ll));

				// System.out.println(this.matriceTransition.probaTransition(ll,
				// all.get(i)) + " " + d.getProba(ll));
				// System.out.println();
				res = res + (this.matriceTransition.probaTransition(ll, all.get(i)) * d.getProba(ll));
			}

			ert.setProba(tmp, res);
			// System.out.println();
			ald.add(res);
		}

		// Object tmp = d.clone();

		Distribution trib = new Distribution<>();

		for (int i = 0; i < all.size(); i++) {
			trib.setProba(all.get(i), ald.get(i));
		}
		// trib.setProba(element, p);

		// System.out.println(ald);
		return trib;

		// throw new Error(); // ** A COMPLETER **
	}

	/**
	 * permet de mettre � jour un belief par rapport � une observation
	 * 
	 * @param d
	 *            la densite actuelle
	 * @param o
	 *            l'observation percue
	 * @return la densite mise � jour
	 */
	public Distribution<State> MaJObservation(Distribution<State> d, Observation o) {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************

		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * m�thode qui propage la densite en prenant en compte l'observation
	 * 
	 * @param o
	 *            observation recue
	 * @return nouvelle densite
	 */
	public Distribution<State> propagation(Distribution<State> initiale, Observation o) {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************

		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * m�thode qui calcule la densite de probabilit� apr�s t pas de temps en
	 * prenant en compte la liste des observations re�ues
	 * 
	 * @param d
	 *            la densite initiale
	 * @param l
	 *            la liste des obsrevations (la taille correspond au nombre de
	 *            pas de temps)
	 * @return la nouvelle densit�
	 */
	public Distribution<State> filtrage(Distribution<State> d, ArrayList<Observation> l) {
		// **********************************************************************************
		// a faire par etudiants
		// **********************************************************************************

		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * m�thode qui retourne la probabilit� d'observer une certaine sequence
	 * d'observations
	 * 
	 * @param l
	 *            liste des observations vues
	 * @param d
	 *            densit� initiale sur les �tats
	 * 
	 * @return probabilite de voir la sequence pass�e en parametre
	 */
	public double probaSequence(Distribution<State> initiale, ArrayList<Observation> l) {
		// **********************************************************************************
		// a faire par etudiants
		// je vous conseille de commencer par �crire des commentaires
		// pour structurer votre code avant de coder reellement la m�thode
		// **********************************************************************************

		throw new Error(); // ** A COMPLETER **
	}

	/**
	 * 
	 * methode qui cherche la trajectoire expliquant au mieux les observations
	 * algorithme viterbi utilisant la programmation dynamique
	 * 
	 * deux phases --> une phase aller pour propagare les probabilit�s --> une
	 * phase retour pour remonter le meilleur chemin
	 * 
	 * @param obs
	 *            la s�quence d'observations
	 * @return la s�quence d'�tats expliquant au mieux la s�quence
	 *         d'observations
	 */
	public ArrayList<State> viterbi(ArrayList<Observation> obs, Distribution<State> initiale) {
		// **********************************************************************************
		// a faire par etudiants
		// je vous conseille de commencer par �crire des commentaires
		// pour structurer votre code avant de coder reellement la m�thode
		// **********************************************************************************

		// phase aller - propagation des probabilit�s
		// id�e evaluer max_S P(S|O)
		// approche recursive f(s)=max_{s1,s2,..sk-1} P(s1...sk-1 sk=s|O_1..o_k)
		// = P(o_k|s_k). max_s_{k-1} (T(s_k|s_k-1). f(s_k-1))
		// se souvenir du sk-1 � chaque iteration pour pouvoir remonter

		// donn�es
		// proba stock� dans un tableau qui associe � t et s une valeur
		// ancetre stocke dans un tableau qui a t et a s associe un parent

		throw new Error(); // ** A COMPLETER **
	}

}
