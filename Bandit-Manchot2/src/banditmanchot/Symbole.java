package banditmanchot;

public class Symbole {
	
	private String nom;
	private float rarete;
	private float tauxGain;

	public Symbole(String nom, float rarete, float tauxGain) {
		
		this.nom = nom;
		this.rarete = rarete;
		this.tauxGain = tauxGain;
	}

	public String getNom() 
	{
		return this.nom;
	}
	public float getRarete() 
	{
		return this.rarete;
	}
	public float gettauxGain() 
	{
		return this.tauxGain;
	}

}
