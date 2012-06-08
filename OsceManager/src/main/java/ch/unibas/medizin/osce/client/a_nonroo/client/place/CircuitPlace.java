package ch.unibas.medizin.osce.client.a_nonroo.client.place;


import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.requestfactory.shared.RequestFactory;

public class CircuitPlace extends OsMaPlace {

	private String token;

	public CircuitPlace(){
		Log.debug("CircuitPlace.CircuitPlace");
		this.token = "CircuitPlace";
	}

	public CircuitPlace(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Tokenizer.
	 */
	public static class Tokenizer implements PlaceTokenizer<CircuitPlace> {
		private final RequestFactory requests;

		public Tokenizer(RequestFactory requests) {
			Log.debug("CircuitPlace.Tokenizer");
			this.requests = requests;
		}

		public CircuitPlace getPlace(String token) {
			Log.debug("CircuitPlace.Tokenizer.getPlace");
			return new CircuitPlace(token);
		}

		public String getToken(CircuitPlace place) {
			Log.debug("CircuitPlace.Tokenizer.getToken");
			return place.getToken();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		return true;
	}
}
