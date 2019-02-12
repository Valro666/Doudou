package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hmm.Distribution;
import hmm.Observation;
import hmm.State;
import hmm.Systeme;

/**
 * classe qui permet d'afficher l'observation courante
 * 
 * @author vthomas
 * 
 */
@SuppressWarnings("unused")
public class PanelObservation extends JPanel {

	/**
	 * taille pour affichage
	 */
	public int TAILLE = 100;

	/**
	 * Observation courante
	 */
	Systeme systeme;

	/**
	 * constructeur pour lier au modele
	 * 
	 * @param m
	 *            modele hmmlaby a utiliser
	 */
	public PanelObservation(Systeme s) {
		this.systeme = s;
		setPreferredSize(new Dimension(3 * TAILLE + 20, 3 * TAILLE + 20));
	}

	/**
	 * permet d'afficher la valeur des capteurs
	 */
	public void paint(Graphics g) {
		super.paint(g);

		// on recupere la derniere observation
		Observation o = this.systeme.lastObservation;

		// throw new Error(); // ** A COMPLETER **

		// affichage du labyrinthe
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// contour
				if (((i == 1) && (j == 0)) && systeme.lastObservation != null) {
					if (systeme.lastObservation.cpt1 == true) {
						g.setColor(Color.green);
						g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
					} else {
						g.setColor(Color.red);
						g.fill3DRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE, true);
					}
				} else if (((i == 2) && (j == 2)) && systeme.lastObservation != null) {
					if (systeme.lastObservation.cpt2 == true) {
						g.setColor(Color.green);
						g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
					} else {
						g.setColor(Color.red);
						g.fill3DRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE, true);
					}
				} else {
					g.setColor(Color.black);
					g.drawRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
				}
			}
		}

	}

	private void dessinerCapteur(Graphics g, int num_capteur, boolean valeur) {
		// trouve position du cpateur
		int i, j;
		State s = new State(num_capteur);
		i = s.retournerCoord()[0];
		j = s.retournerCoord()[1];

		// si capteur actif
		if (valeur)
			g.setColor(Color.green);
		else
			g.setColor(Color.yellow);

		// dessine capteur
		g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
	}

}
