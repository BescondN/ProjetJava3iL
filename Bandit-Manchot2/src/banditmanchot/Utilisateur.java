package banditmanchot;

public class Utilisateur {

	static int nb_jeton_init = 10;

	public int nb_jeton;

    public Comportement comportement;

    IHMConsole ihm = new IHMConsole();
    
	public MachineOrdinaire machine;

    public Utilisateur(Comportement comportement, MachineOrdinaire machine)
    {
    	nb_jeton = Utilisateur.nb_jeton_init;
    	this.comportement = comportement;
    	setMachine(machine);
    }

    public Utilisateur(MachineOrdinaire machine)
    {
    	nb_jeton = Utilisateur.nb_jeton_init;
    	setMachine(machine);
     }

    public Boolean jouer()
    {
    	if(nb_jeton > 0 && this.getMachine().solde_jeton > 0)
    	{
    		nb_jeton--;
    		machine.tirage();
    		return true;
    	}
    	else
    	{
    		if(!(nb_jeton > 0))
    			ihm.afficheMessage("Le joueur n'a plus de jeton\n");
    		if(!(this.getMachine().solde_jeton > 0))
    			ihm.afficheMessage("La machine n'a plus de jeton\n");
    		return false;
    	}
    }

    public int getNb_jeton() {
		return nb_jeton;
	}

	public void setNb_jeton(int nb_jeton) {
		this.nb_jeton = nb_jeton;
	}

	public Comportement getComportement() {
		return comportement;
	}

	public void setComportement(Comportement comportement) {
		this.comportement = comportement;
	}

	public MachineOrdinaire getMachine() {
		return machine;
	}

	public void setMachine(MachineOrdinaire machine) {
		this.machine = machine;
		machine.setCurrent_player(this);
	}

	/**
	 * Affichage du résultat final d'un joueur
	 */
	public void resultatJoueur()
	{
		ihm.afficheMessage("*********** RESULTAT ***********");

		ihm.afficheMessage("Il reste " + getNb_jeton() + " jeton(s) au joueur");
		ihm.afficheMessage("Il y a  " + getMachine().solde_jeton + " jeton(s) dans la machine");

	}

}
