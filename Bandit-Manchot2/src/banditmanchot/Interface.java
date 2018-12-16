package banditmanchot;

import java.util.Scanner;

public class Interface {
	
	//Scanner pour demander les paramétres à l'utilisateur
	static Scanner saisie = new Scanner(System.in);
	
	
	static Casino casino;

	/**
	 * Affiche un menu et retourne le choix de l'utilisateur
	 * @param choix du menu
	 * @return L'index du choix de l'utilisateur
	 */
	public static int menu(String[] choix)
	{
		for(int i = 0; i < choix.length; i++)
		{
			System.out.println(i+1 + ". " + choix[i]);
		}
		return (int) demanderNombre("Quel est votre choix ? : ",1,choix.length);
	}
	
	/**
	 * Demander un nombre à l'utilisateur entre 2 bornes
	 * @param phrase à afficher pour demander le nombre
	 * @param min - Borne inférieure
	 * @param max - Borne supérieure
	 * @return Le nombre saisie par l'utilisateur
	 */
	public static double demanderNombre(String phrase, int min, int max)
	{
		double resultat;
		do
		{
			System.out.println(phrase + "( entre " + min + " et " + max + " )");
			resultat = saisie.nextDouble();
 		}
		while(resultat < min || resultat > max);
		
		
		//saisie.close();
		return resultat;
	
	}
	
	/**
	 * Demander une chaine de caratere à l'utilisateur
	 * @param phrase à afficher 
	 * @return La chaine saisie par l'utilisateur
	 */
	public static String demanderString(String phrase)
	{
		
		System.out.println(phrase);		
		String res =  saisie.nextLine();
		return res;
	
	}
	/**
	 * Demander les informations relatives au casino
	 */
	public static void configCasino()
	{
		int nb_machine = (int) demanderNombre("Nombre de machines dans le casino ? : ",1,1000);
		int nb_joueur = (int) demanderNombre("Nombre de joueur dans la casino ? : ",1,nb_machine);
		
		casino = new Casino(nb_machine, nb_joueur);
	}
	
	/**
	 * Demande les informations relative aux machines du casino
	 */
	public static void configMachines()
	{
		int nb_jeton_init = (int) demanderNombre("Nombre de jeton dans les machines : ",1,1000);
		
		Machine.solde_jeton_init = nb_jeton_init;
		
	}
	/**
	 * Demande les informations relatives aux joueurs
	 */
	public static void configJoueurs()
	{
		int nb_jeton_init = (int) demanderNombre("Nombre de jeton par joueurs : ",1,1000);
		
		Utilisateur.nb_jeton_init = nb_jeton_init;
		
	}
	/**
	 * Demande les informations relatives aux symboles des machines
	 */
	public static void configSymboles()
	{
		int nb_symboles = (int) demanderNombre("Nombre de symboles différents : ",1,10);
		String nom;
		float rarete,tauxGain;
		for(int i =0;i<nb_symboles;i++)
		{
			nom = demanderString("Nom du symbole " +(i+1) + " : ");
			rarete = (float) demanderNombre("Rareté du symbole " + (i+1) + " : ",0,1);
			tauxGain = (float) demanderNombre("Taux gain du symbole " + (i+1) + " : ",0,1);
			
			Tirage.liste.add(new Symbole(nom,rarete,tauxGain));

		}
	}
	/**
	 * Creation des machines et joueurs, affectation de chaque joueurs aux machines
	 */
	public static void creation()
	{
		for(int i = 0; i<casino.getNb_machines();i++)
		{
			casino.liste_machine.add(new Machine());
		}
		Utilisateur user;
		for(int i = 0; i<casino.getNb_visiteurs();i++)
		{
			user = new Utilisateur(Comportement.randomComportement());
			user.setMachine(casino.liste_machine.get(i));
			casino.liste_user.add(user);
		}
	}
	/**
	 * Lancement de la simulaton du casino
	 */
	public static void lancerSimulation()
	{
		casino.simulation();
	}
	/**
	 * Affichage du résultat final d'un joueur
	 * @param user L'utilisateur 
	 */
	public static void resultatJoueur(Utilisateur user)
	{
		System.out.println("*********** RESULTAT ***********");
		
		System.out.println("Il reste " + user.getNb_jeton() + " jeton(s) au joueur");
		System.out.println("Il y a  " + user.getMachine().solde_jeton + " jeton(s) dans la machine");

	}
	/**
	 * Affichage du résultat final du casino
	 */
	public static void resultatCasino()
	{
		System.out.println("*********** RESULTAT CASINO ***********");
		int total_jeton=0;
		for(Machine machine : casino.liste_machine)
		{
			total_jeton += machine.solde_jeton;
		}
		
		if(total_jeton-casino.getNb_machines()*Machine.solde_jeton_init < 0)
		{
			System.out.println("Le casino a perdu " + (casino.getNb_machines()*Machine.solde_jeton_init-total_jeton) + " jeton(s)");

		}
		else
		{
			System.out.println("Le casino a gagné " + (total_jeton-casino.getNb_machines()*Machine.solde_jeton_init) + " jeton(s)");
		}
			

	}
	
	public static void main(String[] args) {
		
		Tirage.liste.add(new Symbole("cerise", 0.30f, 0.05f));		//30% de chance & 5% de gain
		Tirage.liste.add(new Symbole("feuille", 0.55f, 0.1f));      // 25% de chance (0.55 - 0.30) & 10% de gain
		Tirage.liste.add(new Symbole("pomme", 0.70f, 0.15f));       // 15% de chance (0.70 - 0.55) & 15% de gain
		Tirage.liste.add(new Symbole("banane", 0.83f, 0.20f));      // 13% de chance (0.83 - 0.70) & 20% de gain
		Tirage.liste.add(new Symbole("piece", 0.93f, 0.25f));       // 10% de chance (0.93 - 0.83) & 25% de gain
		Tirage.liste.add(new Symbole("couronne", 0.98f, 0.5f));     // 5% de chance (0.98 - 0.93) & 50% de gain
		Tirage.liste.add(new Symbole("7", 1f, 1f));                 // 2% de chance (1 - 0.98) & 100% de gain
		
		String[] menu = {"Jouer","Configuration / Simulation"};
		int choix = menu(menu);
		if(choix == 1)
		{
			configMachines();
			configJoueurs();
			Machine machine = new Machine();
			Utilisateur user = new Utilisateur(machine);
			String rejouer="N";
			Boolean ok;
			do
			{
				ok = user.jouer();
				if(ok)
				rejouer = demanderString("Vous avez : " +user.nb_jeton + " jeton(s). Presser la touche Entrée pour continuer ou 'N' pour arreter : \n");
			}
			while(ok && !rejouer.equals("N") && !rejouer.equals("n"));
		}
		if(choix == 2)
		{
			configCasino();
			configMachines();
			configJoueurs();
			
			creation();
			
			//configSymboles();
			
			lancerSimulation();
		}
		

	}

}
