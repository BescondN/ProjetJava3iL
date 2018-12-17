package banditmanchot;

import java.util.ArrayList;
import java.util.List;

public class Casino {

	private int nb_machines;

	private int nb_visiteurs;

	public List<Machine> liste_machine;

	public List<Utilisateur> liste_user;


	public Casino (int nbMachines, int nbVisiteurs)
	{
		nb_machines = nbMachines;
		nb_visiteurs = nbVisiteurs;
		liste_machine = new ArrayList<Machine>();
		liste_user = new ArrayList<Utilisateur>();

	}


	public int getNb_machines() {
		return nb_machines;
	}

	public void setNb_machines(int nb_machines) {
		this.nb_machines = nb_machines;
	}

	public int getNb_visiteurs() {
		return nb_visiteurs;
	}

	public void setNb_visiteurs(int nb_visiteurs) {
		this.nb_visiteurs = nb_visiteurs;
	}

	/**
	 * Lance la simulation du casino
	 */
	public void simulation()
	{
		int i =1;
		for(Utilisateur user : liste_user)
		{
			System.out.println("*********** JOUEUR " + i + " ***********");
			System.out.println("CE JOUEUR CONSERVE " + user.comportement.nb_symbol_lock + " SYMBOLE(S) ET S'ARRETE A " + user.comportement.stop_play + " JETONS");
			while(user.nb_jeton > 0 && user.nb_jeton < user.comportement.getStop_play())
			{
				user.jouer();
			}
             user.resultatJoueur();
			i++;
		}
		resultatCasino();
	}
	/**
	 * Affiche le resultat final du casino
	 */
	private void resultatCasino()
	{
		System.out.println("*********** RESULTAT CASINO ***********");
		int total_jeton=0;
		int  i = 1;
		for(Machine machine : liste_machine)
		{
			System.out.println("Machine " + i + " : " + machine.solde_jeton + " jeton(s).");
			total_jeton += machine.solde_jeton;
			i++;
		}

		if(total_jeton-getNb_machines()*Machine.solde_jeton_init < 0)
		{
			System.out.println("Le casino a perdu " + (getNb_machines()*Machine.solde_jeton_init-total_jeton) + " jeton(s)");

		}
		else
		{
			System.out.println("Le casino a gagné " + (total_jeton-getNb_machines()*Machine.solde_jeton_init) + " jeton(s)");
		}
	}
	/**
	 * Creation des machines et joueurs, affectation de chaque joueurs aux machines
	 */
	public void creation()
	{
		for(int i = 0; i< getNb_machines();i++)
		{
			liste_machine.add(new Machine());
		}
		Utilisateur user;
		for(int i = 0; i<getNb_visiteurs();i++)
		{
			user = new Utilisateur(Comportement.randomComportement());
			user.setMachine(liste_machine.get(i));
			liste_user.add(user);
		}
	}
}
