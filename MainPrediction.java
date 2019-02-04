import java.util.List;

import hmm.Distribution;
import hmm.HMM;
import hmm.Observation;
import hmm.ProcessusTransition;
import hmm.State;

public class MainPrediction {

	public static void main(String[] args) {

		ProcessusTransition pt = new ProcessusTransition();

		List<State> s = State.getAll();
		System.out.println(s);

		double d = pt.probaTransition(s.get(0), s.get(1));
		System.out.println(d);

		System.out.println(pt.evoluerEtat(s.get(0)));

	}

}
