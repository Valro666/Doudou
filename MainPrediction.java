import java.util.ArrayList;
import java.util.List;

import hmm.Distribution;
import hmm.HMM;
import hmm.Observation;
import hmm.ProcessusTransition;
import hmm.State;

public class MainPrediction {

	public static void main(String[] args) {

		ProcessusTransition pt = new ProcessusTransition();



		HMM m = new HMM();
		List<State> all = State.getAll();
		System.out.println("Matrice transition initiale");
		System.out.println(m.matriceTransition.toString());

		Distribution d = new Distribution();

		 Distribution init = new Distribution(m.matriceTransition.transition.get(all.get(0)));
		//
		 init.setProba(all.get(0), 0.5);
		 init.setProba(all.get(1), 0.5);
		//
		
		Distribution zed = m.maJTransition(init);
		
		System.out.println(all);
		System.out.println("Proba des positions de Doudou");
		System.out.println(zed+"<- resultat obtenu");
		System.out.println("S1->0.449, S2->0.35, S3->0.0, S4->0.0, S5->0.0, S6->0.0, S7->0.05, S0->0.15, <- resultat attendu");

	}

}
