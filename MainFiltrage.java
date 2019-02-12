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

		Distribution init = new Distribution(m.matriceTransition.transition.get(all.get(0)));
		//
		init.setProba(all.get(0), 0.5);
		init.setProba(all.get(1), 0.5);
		
		Distribution init2 = new Distribution(m.matriceTransition.transition.get(all.get(0)));
		//
		init2.setProba(all.get(0), 1);
		// Observation obs = o.get(3);
		System.out.println("fitrage");
		System.out.println(init);
		System.out.println(init2);

		ArrayList<Observation> alo = new ArrayList<>();
		alo.add(o.get(3));
		alo.add(o.get(2));
		alo.add(o.get(3));
		System.out.println(alo);

		Distribution azerty = m.filtrage(init, alo);
		Distribution azerty2 = m.filtrage(init2, alo);
		System.out.println("S1->0.519, S2->0.109, S3->0.331, S4->0.0, S5->0.0, S6->0.001, S7->0.012, S0->0.026, <- a obtenir");
		System.out.println(init+"<- initial");
		System.out.println(azerty+" <- obtenu");
		System.out.println(init2+"<- initial2");
		System.out.println(azerty2+" <- obtenu2");

		// Distribution dis = m.maJTransition(init);
		// dis = m.maJTransition(dis);
		// dis = m.maJTransition(dis);
		//
		// System.out.println(dis);
		// System.out.println(azerty.calculNorme());

		// Distribution g = m.maJTransition(init);

		// Distribution g2 = m.MaJObservation(g, obs);

		// System.out.println(g2 + "resultat obtenu");
	}

}
