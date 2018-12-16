package banditmanchot;

import java.util.concurrent.ThreadLocalRandom;

public class Comportement {

	public int nb_symbol_lock;
	
	public Boolean lock_max_symbol;
	
	public int stop_play;
	
	public Comportement(int nb, Boolean max, int stop)
	{
		nb_symbol_lock = nb;
		lock_max_symbol = max;
		stop_play = stop;
	}
	
	public static Comportement randomComportement()
	{
		return new Comportement(randomInt(0,2), (Boolean)randomBool(), randomInt(Utilisateur.nb_jeton_init,Utilisateur.nb_jeton_init+50));
	}
	
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

	private static Boolean randomBool()
	{
		if(randomInt(0,1) == 1)
			return true;
		else
			return false;
	}
}
