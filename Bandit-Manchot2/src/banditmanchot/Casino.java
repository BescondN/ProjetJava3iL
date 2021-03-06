package banditmanchot;

import java.util.ArrayList;
import java.util.List;

public class Casino {
	
	IHMConsole ihm = new IHMConsole();

	private int nb_machines;

	private int nb_visiteurs;

	public List<MachineOrdinaire> liste_machine;

	public List<Utilisateur> liste_user;

	/**
	 * Constructeur de la classe Casino. Permet de r�cup�rer le nombre de machines et 
	 * de visiteurs.
	 * @param nbMachines
	 * @param nbVisiteurs
	 */
	public Casino (int nbMachines, int nbVisiteurs)
	{
		nb_machines = nbMachines;
		nb_visiteurs = nbVisiteurs;
		liste_machine = new ArrayList<MachineOrdinaire>();
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
			ihm.afficheMessage("*********** JOUEUR " + i + " ***********");
			ihm.afficheMessage("CE JOUEUR CONSERVE " + user.comportement.nb_symbol_lock + " SYMBOLE(S) ET S'ARRETE A " + user.comportement.stop_play + " JETONS");
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
	 * Affiche le resultat des gains et pertes du casino
	 */
	private void resultatCasino()
	{
		ihm.afficheMessage("*********** RESULTAT CASINO ***********");
		int total_jeton=0;
		int  i = 1;
		for(MachineOrdinaire machine : liste_machine)
		{
			ihm.afficheMessage("Machine " + i + " : " + machine.solde_jeton + " jeton(s).");
			total_jeton += machine.solde_jeton;
			i++;
		}

		if(total_jeton-getNb_machines()*MachineOrdinaire.solde_jeton_init < 0)
		{
			ihm.afficheMessage("Le casino a perdu " + (getNb_machines()*MachineOrdinaire.solde_jeton_init-total_jeton) + " jeton(s)");

		}
		else
		{
			ihm.afficheMessage("Le casino a gagn� " + (total_jeton-getNb_machines()*MachineOrdinaire.solde_jeton_init) + " jeton(s)");
		}
	}
	/**
	 * Creation des machines et joueurs, affectation de chaque joueurs aux machines
	 */
	public void creation()
	{
		for(int i = 0; i< getNb_machines();i++)
		{
			liste_machine.add(new MachineOrdinaire());
		}
		Utilisateur user;
		for(int i = 0; i<getNb_visiteurs();i++)
		{
			user = new Utilisateur(Comportement.randomComportement(),liste_machine.get(i));
			liste_user.add(user);
		}
	}
}
