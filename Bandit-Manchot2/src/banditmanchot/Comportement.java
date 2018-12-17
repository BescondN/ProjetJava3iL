package banditmanchot;

import java.util.concurrent.ThreadLocalRandom;

public class Comportement {

	public int nb_symbol_lock; // Nombre de symbole que l'utilisateur va conserver

	public Boolean lock_max_symbol; // Le symbole conserver est le plus rare ( true ) ou le moins rare ( false )

	public int stop_play; // Montant de jeton auquel le joueur arr�te de jouer

	public Comportement(int nb, Boolean max, int stop)
	{
		nb_symbol_lock = nb;
		lock_max_symbol = max;
		stop_play = stop;
	}

	/**
	 * G�nere un comportement al�atoire
	 * @return Un comportement al�atoire
	 */
	public static Comportement randomComportement()
	{
		return new Comportement(randomInt(0,2), (Boolean)randomBool(), randomInt(Utilisateur.nb_jeton_init,Utilisateur.nb_jeton_init+50));
	}

	/**
	 * D�termine un entier al�atoire entre 2 bornes
	 * @param min Bornes inf�rieure
	 * @param max Bornes sup�rieure
	 * @return Un entier al�atoire entre min et max
	 */
	private static int randomInt(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public int getStop_play() {
		return stop_play;
	}

	public void setStop_play(int stop_play) {
		this.stop_play = stop_play;
	}

	/**
	 * Retourne un boolean al�atoire true ou false
	 * @return True ou False al�atoirement
	 */
	private static Boolean randomBool()
	{
		if(randomInt(0,1) == 1)
			return true;
		else
			return false;
	}
}
