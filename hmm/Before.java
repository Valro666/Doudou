package hmm;

public class Before {

	public Before pred = null;

	public State actu = null;

	public double valeur = 0;

	public Before(State s, double val) {
		actu = s;
		valeur = val;
	}

	public Before(State s, double val, Before b) {
		actu = s;
		valeur = val;
	}

}
