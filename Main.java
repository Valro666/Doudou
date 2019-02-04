import gui.Interface;
import hmm.Distribution;
import hmm.HMM;
import hmm.State;
import hmm.Systeme;

public class Main {
	public static void main(String[] args) {

		// on creer une distribution initiale avec tout en s0
		Distribution<State> distributionEtatInitiale = new Distribution<>();
		distributionEtatInitiale.setProba(new State(0), 1);

		// on cree un modele
		HMM modele = new HMM();

		// on creer un systeme avec le modele et la distribution
		Systeme systeme = new Systeme(modele, distributionEtatInitiale);

		// on creer l'interface sur le systeme
		new Interface(systeme);

	}
}
