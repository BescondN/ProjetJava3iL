package banditmanchot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MachineOrdinaire {
	
    static int solde_jeton_init = 100;
	
	public int solde_jeton;
		
	 Utilisateur current_player;
	 
	 int nb_jetons_perdu = 0;
	 int nb_jetons_gagne = 0;
	 
	 List<Symbole> symbol_combinaison;
	 
	 int symbolLock1, symbolLock2;
	
	public MachineOrdinaire()
	{
		symbol_combinaison = new ArrayList<Symbole>();
		solde_jeton = MachineOrdinaire.solde_jeton_init;
		unlockSymbol();
	}

	public int getSolde_jeton() {
		return solde_jeton;
	}

	public void setSolde_jeton(int solde_jeton) {
		this.solde_jeton = solde_jeton;
	}

	public Utilisateur getCurrent_player() {
		return current_player;
	}

	public void setCurrent_player(Utilisateur current_player) {
		this.current_player = current_player;
		this.nb_jetons_gagne=0;
		this.nb_jetons_gagne=0;
	}
	
	public void tirage()
	{
		solde_jeton++;
		nb_jetons_perdu++;
		
		if(symbol_combinaison.size() == 0)
		{
			for(int i =0; i < 3; i++)
			{
				symbol_combinaison.add(Tirage.aleatoire());
			}
		}
		else
		{
			int i =0;
			while(i < 3)
			{
				if(i != symbolLock1 && i != symbolLock2)
				{
					symbol_combinaison.set(i, Tirage.aleatoire());
				}
				i++;
			}
		}
	
		if(!verification())
		{
			afficheResultat();

			if(this.current_player.comportement != null)
			{
				lockSymbolSimu();
			}
			else
			{
				
				lockSymbol();
			}
		}
		else
		{
			calculGains();
			afficheResultat();


		}


	}
	
	public void lockSymbolSimu()
	{
		if(this.current_player.comportement.nb_symbol_lock != 0)
		{
			if(symbolLock1 == -1)
			{
			   if(this.current_player.comportement.lock_max_symbol)
			   {
				   this.symbolLock1 = getIndexMinMaxSymbol(true);
			   }
			   else
			   {
				   this.symbolLock1 = getIndexMinMaxSymbol(false);;
			   }
			}
		}
		if(this.current_player.comportement.nb_symbol_lock == 2 && symbolLock2 == -1 && symbolLock1 != -1)
		{
			int i =0;
			while(symbolLock2 == -1 && i < symbol_combinaison.size())
			{
				if(i != symbolLock1 && symbol_combinaison.get(symbolLock1) == symbol_combinaison.get(i))
				{
					symbolLock2 = i;
				}
				i++;
			}
		}
	}
	
	private void lockSymbol()
	{
		
		Scanner saisie  = new Scanner(System.in);
		String[] resultats;
		do {
			
			System.out.print("Saisir symboles à conserver ( s'éparer par une virgule) : ");
			String result = saisie.nextLine();
			resultats = result.split(",");
		}
		while(resultats.length > 2);
		
		if(resultats.length > 0 && !resultats[0].isEmpty())
		{
			symbolLock1 = Integer.parseInt(resultats[0])-1;
		}
		if(resultats.length >1 && !resultats[1].isEmpty())
		{
			symbolLock2 = Integer.parseInt(resultats[1])-1;
		}
			
		
		 
		
	}
	
	private void afficheResultat()
	{
		for(Symbole symbole : symbol_combinaison)
		{
			System.out.print(symbole.getNom() + "/");
		}
		
		System.out.println("\nVous avez perdu " + nb_jetons_perdu  +" jeton(s) et gagner " + nb_jetons_gagne + " jeton(s) sur cette machine" );
		

	}
	private int getIndexMinMaxSymbol(Boolean maxi)
	{
		int indexMax = 0;
		int indexMin = 0;
		double max = symbol_combinaison.get(0).getRarete();
		double min = symbol_combinaison.get(0).getRarete();
		for(int i = 1; i< symbol_combinaison.size(); i++)
		{
			if(symbol_combinaison.get(i).getRarete() > max)
			{
				indexMax = i;
			}
			if(symbol_combinaison.get(i).getRarete() < min)
			{
				indexMin = i;
			}
		}
		if(maxi)
		   return indexMax;
		 else
			return indexMin;
	}
	
	private void unlockSymbol()
	{
		symbolLock1 = -1;
		symbolLock2 = -1;
	}
	
	private Boolean verification()
	{
		if(symbol_combinaison.get(0) == symbol_combinaison.get(1) && symbol_combinaison.get(0) == symbol_combinaison.get(2))
		{
			
			return true;
		}
		else
			return false;
	}
	private void calculGains()
	{
		double taux = 1;
		if(symbolLock1 != -1)
		{
			taux = 0.66;
		}
		if(symbolLock2 != -1)
		{
			taux = 0.33;
		}
		System.out.println("Taux : " + taux + " * " + symbol_combinaison.get(0).gettauxGain());
		taux = taux * symbol_combinaison.get(0).gettauxGain();
		System.out.println(" = " + taux+ " **** JETONS =  " + taux + " * " + solde_jeton);
		this.current_player.nb_jeton += solde_jeton * taux;
		nb_jetons_gagne+= solde_jeton * taux;
		System.out.println(" = " + taux*solde_jeton + " TOTAL JOUEUR " + this.current_player.nb_jeton);
		this.solde_jeton -= taux*this.solde_jeton;
		System.out.println("RESTE " + this.solde_jeton);
				
		unlockSymbol();
	}

}
