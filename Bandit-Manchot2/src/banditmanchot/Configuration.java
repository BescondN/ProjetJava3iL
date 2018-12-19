package banditmanchot;

public class Configuration {
	
	static IHMConsole ihm = new IHMConsole();
	
	/**
	 * Demander les informations relatives au casino
	 */
	public static Casino configCasino()
	{
		int nb_machine = (int) ihm.demanderNombreEntreBornes("Nombre de machines dans le casino ? : ",1,-1);
		int nb_joueur = (int) ihm.demanderNombreEntreBornes("Nombre de joueur dans la casino ? : ",1,nb_machine);

		return new Casino(nb_machine, nb_joueur);
	}

	/**
	 * Demande les informations relative aux machines du casino
	 */
	public static void configMachines()
	{
		int nb_jeton_init = (int) ihm.demanderNombreEntreBornes("Nombre de jeton de la machine : ",1,-1);

		MachineOrdinaire.solde_jeton_init = nb_jeton_init;

	}
	/**
	 * Demande les informations relatives aux joueurs
	 */
	public static void configJoueurs()
	{
		int nb_jeton_init = (int) ihm.demanderNombreEntreBornes("Nombre de jeton du joueur : ",1,-1);

		Utilisateur.nb_jeton_init = nb_jeton_init;

	}
	/**
	 * Demande les informations relatives aux symboles des machines
	 */
	public static void configSymboles()
	{
		int nb_symboles = (int) ihm.demanderNombreEntreBornes("Nombre de symboles différents : ",1,10);
		String nom;
		float rarete,tauxGain;
		for(int i =0;i<nb_symboles;i++)
		{
			nom = ihm.demanderString("Nom du symbole " +(i+1) + " : ");
			rarete = (float) ihm.demanderNombreEntreBornes("Rareté du symbole " + (i+1) + " : ",0,1);
			tauxGain = (float) ihm.demanderNombreEntreBornes("Taux gain du symbole " + (i+1) + " : ",0,1);

			Tirage.liste.add(new Symbole(nom,rarete,tauxGain));

		}
	}

}
