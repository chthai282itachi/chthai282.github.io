package ch.unibas.medizin.osce.shared;

public enum Locale {
	de("Deutsch"), en("English");
	
	private final String languageName;
	
	private Locale(String languageName) {
		this.languageName = languageName;
	}
	
	public String getLanguageName() {
		return languageName;
	}
}
