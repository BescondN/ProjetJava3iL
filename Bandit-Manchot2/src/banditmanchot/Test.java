package banditmanchot;

public class Test {

	public static void main(String[] args) {
		
		Casino casino;
		IHMConsole ihm = new IHMConsole();

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
			choix = ihm.menu(menu);
			if(choix == 1)
			{
				Configuration.configMachines();
				Configuration.configJoueurs();
				Machine machine = new Machine();
				Utilisateur user = new Utilisateur(machine);
				String rejouer="N";
				Boolean ok;
				do
				{
					ok = user.jouer();
					if(ok)
					rejouer = ihm.demanderString("Vous avez : " +user.nb_jeton + " jeton(s). Presser la touche Entrée pour continuer ou 'N' pour arreter : \n");
				}
				while(ok && !rejouer.equals("N") && !rejouer.equals("n"));
			}
			if(choix == 2)
			{
				casino = Configuration.configCasino();
				Configuration.configMachines();
				Configuration.configJoueurs();

				casino.creation();

				//configSymboles();

				casino.simulation();
			}
		}
		while(choix != 3 );


	}

}
