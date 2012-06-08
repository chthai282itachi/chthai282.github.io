package ch.unibas.medizin.osce.client.a_nonroo.client.place;


import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.requestfactory.shared.RequestFactory;

public class StudentsPlace extends OsMaPlace {

	private String token;

	public StudentsPlace(){
		Log.debug("StudentsPlace.StudentsPlace");
		this.token = "StudentsPlace";
	}

	public StudentsPlace(String token){
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
	public static class Tokenizer implements PlaceTokenizer<StudentsPlace> {
		private final RequestFactory requests;

		public Tokenizer(RequestFactory requests) {
			Log.debug("StudentsPlace.Tokenizer");
			this.requests = requests;
		}

		public StudentsPlace getPlace(String token) {
			Log.debug("StudentsPlace.Tokenizer.getPlace");
			return new StudentsPlace(token);
		}

		public String getToken(StudentsPlace place) {
			Log.debug("StudentsPlace.Tokenizer.getToken");
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
