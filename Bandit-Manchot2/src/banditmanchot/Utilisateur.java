package banditmanchot;

public class Utilisateur {
	
	static int nb_jeton_init = 10;
	
	public int nb_jeton;
	
    public Comportement comportement;
    
    
	public Machine machine;
    
    public Utilisateur(Comportement comportement)
    {
    	nb_jeton = Utilisateur.nb_jeton_init;
    	this.comportement = comportement;
    }
    
    public Utilisateur(Machine machine)
    {
    	nb_jeton = Utilisateur.nb_jeton_init;
    	this.setMachine(machine);
    }
    
    public Boolean jouer()
    {
    	if(nb_jeton > 0)
    	{
    		nb_jeton--;
    		machine.tirage();
    		return true;
    	}
    	else
    	{
    		System.out.println("Plus de jeton");
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

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
		machine.setCurrent_player(this);
	}

}
