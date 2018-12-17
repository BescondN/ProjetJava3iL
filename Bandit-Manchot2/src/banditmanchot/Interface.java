package banditmanchot;

import java.util.Scanner;

public class Interface {

	//Scanner pour demander les param�tres � l'utilisateur
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
	 * Demander un nombre � l'utilisateur entre 2 bornes
	 * @param phrase � afficher pour demander le nombre
	 * @param min - Borne inf�rieure
	 * @param max - Borne sup�rieure
	 * @return Le nombre saisie par l'utilisateur
	 */
	public static double demanderNombre(String phrase, int min, int max)
	{
		double resultat;
		do
		{
			if(max >=0)
			{
				System.out.println(phrase + "( entre " + min + " et " + max + " )");
			}
			else
			{
				System.out.println(phrase + "( sup�rieur � " + min + " )");
			}
			
			resultat = saisie.nextDouble();
 		}
		while(resultat < min || (resultat > max && max != -1));


		//saisie.close();
		return resultat;

	}

	/**
	 * Demander une chaine de caratere � l'utilisateur
	 * @param phrase � afficher
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
		int nb_machine = (int) demanderNombre("Nombre de machines dans le casino ? : ",1,-1);
		int nb_joueur = (int) demanderNombre("Nombre de joueur dans la casino ? : ",1,nb_machine);

		casino = new Casino(nb_machine, nb_joueur);
	}

	/**
	 * Demande les informations relative aux machines du casino
	 */
	public static void configMachines()
	{
		int nb_jeton_init = (int) demanderNombre("Nombre de jeton de la machine : ",1,-1);

		Machine.solde_jeton_init = nb_jeton_init;

	}
	/**
	 * Demande les informations relatives aux joueurs
	 */
	public static void configJoueurs()
	{
		int nb_jeton_init = (int) demanderNombre("Nombre de jeton du joueur : ",1,-1);

		Utilisateur.nb_jeton_init = nb_jeton_init;

	}
	/**
	 * Demande les informations relatives aux symboles des machines
	 */
	public static void configSymboles()
	{
		int nb_symboles = (int) demanderNombre("Nombre de symboles diff�rents : ",1,10);
		String nom;
		float rarete,tauxGain;
		for(int i =0;i<nb_symboles;i++)
		{
			nom = demanderString("Nom du symbole " +(i+1) + " : ");
			rarete = (float) demanderNombre("Raret� du symbole " + (i+1) + " : ",0,1);
			tauxGain = (float) demanderNombre("Taux gain du symbole " + (i+1) + " : ",0,1);

			Tirage.liste.add(new Symbole(nom,rarete,tauxGain));

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

		String[] menu = {"Jouer","Configuration / Simulation", "Quitter"};
		int choix;
		do
		{
			choix = menu(menu);
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
					rejouer = demanderString("Vous avez : " +user.nb_jeton + " jeton(s). Presser la touche Entr�e pour continuer ou 'N' pour arreter : \n");
				}
				while(ok && !rejouer.equals("N") && !rejouer.equals("n"));
			}
			if(choix == 2)
			{
				configCasino();
				configMachines();
				configJoueurs();

				casino.creation();

				//configSymboles();

				casino.simulation();
			}
		}
		while(choix != 3 );


	}

}
