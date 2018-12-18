package banditmanchot;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub




		Machine machine = new Machine();
		Comportement c = Comportement.randomComportement();
		Utilisateur user = new Utilisateur(c, machine);



		while(user.nb_jeton < user.comportement.getStop_play() && user.nb_jeton>0)
		{
			user.jouer();

		}




	}

}
