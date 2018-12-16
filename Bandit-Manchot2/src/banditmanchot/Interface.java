package banditmanchot;

import java.util.Scanner;

public class Interface {

	public static int menu(String[] choix)
	{
		for(int i = 0; i < choix.length; i++)
		{
			System.out.println(i+1 + ". " + choix[i]);
		}
		return (int) demanderNombre("Quel est votre choix ? : ",1,choix.length);
	}
	static Casino casino;
	
	public static double demanderNombre(String phrase, int min, int max)
	{
		Scanner saisie = new Scanner(System.in);
		double resultat;
		do
		{
			System.out.println(phrase + "( entre " + min + " et " + max + " )");
			resultat = saisie.nextDouble();
 		}
		while(resultat < min || resultat > max);
		
		
		
		return resultat;
	
	}
	
	public static String demanderString(String phrase)
	{
		System.out.println(phrase);
		Scanner saisie = new Scanner(System.in);
		
		return saisie.nextLine();
	
	}
	public static void configCasino()
	{
		int nb_machine = (int) demanderNombre("Nombre de machines dans le casino ? : ",1,20);
		int nb_joueur = (int) demanderNombre("Nombre de joueur dans la casino ? : ",1,nb_machine);
		
		casino = new Casino(nb_machine, nb_joueur);
	}
	
	public static void configMachines()
	{
		int nb_jeton_init = (int) demanderNombre("Nombre de jeton dans les machines : ",1,1000);
		
		Machine.solde_jeton_init = nb_jeton_init;
		
	}
	public static void configJoueurs()
	{
		int nb_jeton_init = (int) demanderNombre("Nombre de jeton par joueurs : ",1,1000);
		
		Utilisateur.nb_jeton_init = nb_jeton_init;
		
	}
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
	public static void lancerSimulation()
	{
		int i =1;
		for(Utilisateur user : casino.liste_user)
		{
			System.out.println("*********** JOUEUR " + i + " ***********");
			System.out.println("CE JOUEUR CONSERVE " + user.comportement.nb_symbol_lock + " SYMBOLE(S) ET S'ARRETE A " + user.comportement.stop_play + " JETONS");
			while(user.nb_jeton > 0 && user.nb_jeton < user.comportement.getStop_play())
			{
				user.jouer();
			}
             resultatJoueur(user);
			i++;
		}
		resultatCasino();
	}
	public static void resultatJoueur(Utilisateur user)
	{
		System.out.println("*********** RESULTAT ***********");
		
		System.out.println("Il reste " + user.getNb_jeton() + " jeton(s) au joueur");
		System.out.println("Il y a  " + user.getMachine().solde_jeton + " jeton(s) dans la machine");

	}
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
		
		Tirage.liste.add(new Symbole("cerise", 0.30f, 0.05f));		
		Tirage.liste.add(new Symbole("feuille", 0.55f, 0.1f));
		Tirage.liste.add(new Symbole("pomme", 0.70f, 0.15f));
		Tirage.liste.add(new Symbole("banane", 0.83f, 0.20f));
		Tirage.liste.add(new Symbole("piece", 0.93f, 0.25f));
		Tirage.liste.add(new Symbole("couronne", 0.98f, 0.5f));
		Tirage.liste.add(new Symbole("7", 1f, 1f));
		
		String[] menu = {"Jouer","Configuration / Simulation"};
		int choix = menu(menu);
		if(choix == 1)
		{
			Machine machine = new Machine();
			Utilisateur user = new Utilisateur(machine);
			String rejouer;
			do
			{
				user.jouer();
				rejouer = demanderString("Rejouer ? O/N");
			}
			while(rejouer != "N");
		}
		if(choix == 2)
		{
			configCasino();
			configMachines();
			configJoueurs();
			
			creation();
			
			if(false)
			{
				configSymboles();
			}
			
			lancerSimulation();
		}
		

	}

}
