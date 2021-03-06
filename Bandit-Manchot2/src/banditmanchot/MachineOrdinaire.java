package banditmanchot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MachineOrdinaire extends Machine {
	
    static int solde_jeton_init = 100;
	
	public int solde_jeton;
	
	IHMConsole ihm = new IHMConsole();
		
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
	
	/**
	 * D�termine l'index du plus ou moins rare symbole
	 * @param maxi True pour le plus rare et false pour le moins rare
	 * @return
	 */
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
	
	@Override
	/**
	 * V�rouille les symbole en fonction du comportement du joueur en mode simulation
	 */
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
	

	@Override
	/**
	 * V��rouille les symboles en mode jouer
	 */
	public void lockSymbol() {

		Scanner saisie  = new Scanner(System.in);
		String[] resultats;
		do {
			
			ihm.afficheMessage("Saisir symboles � conserver ( s'�parer par une virgule) : ");
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

	@Override
	/**
	 * Affiche le r�sultat du tirage
	 */
	public void afficheResultat() {
		
		for(Symbole symbole : symbol_combinaison)
		{
			ihm.afficheMessage(symbole.getNom() + "/");
		}
		
		ihm.afficheMessage("\nVous avez perdu " + nb_jetons_perdu  +" jeton(s) et gagner " + nb_jetons_gagne + " jeton(s) sur cette machine" );
		
	}

	@Override
	/**
	 * D�bloque les symboles
	 */
	public void unlockSymbol() {


		symbolLock1 = -1;
		symbolLock2 = -1;
		
	}

	@Override
	/**
	 * V�rifie si les 3 symboles sont identiques et retourne True si ils sont identiques sinon false
	 */
	public Boolean verification() {

		if(symbol_combinaison.get(0) == symbol_combinaison.get(1) && symbol_combinaison.get(0) == symbol_combinaison.get(2))
		{
			
			return true;
		}
		else
			return false;
	}

	@Override
	/**
	 * Calcul et met � jour les gains du joueur et de la machine
	 */
	public void calculGains() {

		double taux = 1;
		if(symbolLock1 != -1)
		{
			taux = 0.66;
		}
		if(symbolLock2 != -1)
		{
			taux = 0.33;
		}
		ihm.afficheMessage("Taux : " + taux + " * " + symbol_combinaison.get(0).gettauxGain());
		taux = taux * symbol_combinaison.get(0).gettauxGain();
		ihm.afficheMessage(" = " + taux+ " **** JETONS =  " + taux + " * " + solde_jeton);
		this.current_player.nb_jeton += solde_jeton * taux;
		nb_jetons_gagne+= solde_jeton * taux;
		ihm.afficheMessage(" = " + taux*solde_jeton + " TOTAL JOUEUR " + this.current_player.nb_jeton);
		this.solde_jeton -= taux*this.solde_jeton;
		ihm.afficheMessage("RESTE " + this.solde_jeton);
				
		unlockSymbol();
		
	}
	
	@Override
	/**
	 * Permet le lancer la machine pour effectuer un tirage
	 */
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

}
