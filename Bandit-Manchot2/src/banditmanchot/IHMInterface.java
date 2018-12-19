package banditmanchot;

public abstract class IHMInterface {
	
	public abstract void afficheMessage(String message);
	
	public abstract int menu(String[] choixPossible);
	
	public abstract double demanderNombreEntreBornes(String phrase, int min, int max);
	
	public abstract String demanderString(String phrase);

}
