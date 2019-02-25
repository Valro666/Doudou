import java.util.ArrayList;
import java.util.List;

import hmm.Distribution;
import hmm.HMM;
import hmm.Observation;
import hmm.State;

public class MainFiltrage {

	public static void main(String[] args) {

		// TODO
		HMM m = new HMM();

		List<Observation> o = Observation.getAll();
		List<State> all = State.getAll();
		Distribution<State> init = new Distribution<State>(m.matriceTransition.transition.get(all.get(0)));
		Distribution<State> init2 = new Distribution<State>(m.matriceTransition.transition.get(all.get(0)));
		init2.setProba(all.get(0), 1);
		System.out.println("fitrage");

		ArrayList<Observation> alo = new ArrayList<>();
		alo.add(o.get(3));
		alo.add(o.get(2));
		alo.add(o.get(3));

		Distribution<State> azerty2 = m.filtrage(init2, alo);
		System.out.println(init2 + "<- initial2");
		System.out.println(azerty2 + " <- obtenu2");
		System.out.println(
				"S1->0.519, S2->0.109, S3->0.331, S4->0.0, S5->0.0, S6->0.001,S7->0.012, S0->0.026, <- a obtenir");
		// System.out.println(m.matriceObservation.observation.values());
		System.out.println(m.matriceTransition.transition);
		System.out.println(m.matriceObservation.observation);
	}

}
