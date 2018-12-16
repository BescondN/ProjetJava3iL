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
}
