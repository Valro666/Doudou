import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PrinterMessageFromOperator;

import hmm.Distribution;
import hmm.HMM;
import hmm.Observation;
import hmm.State;

public class MainViterbi {

	public static void main(String[] args) {

		// TODO
		HMM m = new HMM();
		List<State> all = State.getAll();
		Distribution<State> init = new Distribution<>(m.matriceTransition.transition.get(all.get(0)));

		init.setProba(new State(0), 1.00);
		
		Distribution<State> init2 = new Distribution<>(m.matriceTransition.transition.get(all.get(0)));

		init2.setProba(new State(0), 1.00);
		System.out.println(init2);
		
		Distribution<State> init3 = init.dedouble();
		
		ArrayList<Observation> alo = new ArrayList<>();
		
		alo.add(new Observation(true,false));
		alo.add(new Observation(false,true));
		alo.add(new Observation(true,false));
		alo.add(new Observation(false,false));
		System.out.println(alo);
		ArrayList<State> r = m.viterbi(alo, init);
		
		//ArrayList<State> r3 = m.viterbi(alo, init3);
		
		//ArrayList<State> r2 = m.viterbi2(alo, init2);
		
		
		System.out.println(r);
		
		
		//System.out.println(m.matriceObservation);
		//System.out.println(m.matriceTransition);
		//System.out.println(r2);
		///System.out.println(r2);

	}

}
