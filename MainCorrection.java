import java.util.List;

import hmm.Distribution;
import hmm.HMM;
import hmm.Observation;
import hmm.State;

public class MainCorrection {

	public static void main(String[] args) {

		HMM m = new HMM();

		List<Observation> o = Observation.getAll();

		List<State> all = State.getAll();

		Distribution init = new Distribution(m.matriceTransition.transition.get(all.get(0)));
		//
		init.setProba(all.get(0), 0.5);
		init.setProba(all.get(1), 0.5);
		Observation obs = o.get(3);
		// System.out.println(z);

		Distribution g = m.maJTransition(init);

		Distribution g2 = m.MaJObservation(g, obs);

		System.out.println(g2 + "resultat obtenu");

		System.out.println(m.propagation(init, obs));
		System.out.println(
				"S1->0.786, S2->0.135, S3->0.0, S4->0.0, S5->0.0, S6->0.0, S7->0.019, S0->0.058, \nresultat attendu");
		// System.out.println(0.449 / 0.786);

	}

}
