package banditmanchot;

public abstract class Machine {
	
	public abstract void tirage();
	
	public abstract void lockSymbolSimu();
	
	public abstract void lockSymbol();
	
	public abstract void afficheResultat();
	
	public abstract void unlockSymbol();
	
	public abstract Boolean verification();
	
	public abstract void calculGains();
	
	

}
