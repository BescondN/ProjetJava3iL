package banditmanchot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Tirage {

	/**
	 * Permet de trier des symboles en fonction de leur rareté
	 */
	public static Comparator<Symbole> comparator = new Comparator<Symbole>() {
        @Override
        public int compare(Symbole s1, Symbole s2)
        {

            return  Float.compare(s1.getRarete(),s2.getRarete());
        }
    };


	public static List<Symbole> liste = new ArrayList<Symbole>();

	/**
	 * Retourne un symbole de façon "aléatoire"
	 * @return
	 */
	public static Symbole aleatoire()
	{

		Random r = new Random();
		double random = r.nextDouble();

		DecimalFormat df = new DecimalFormat("#.###");
		random = Double.valueOf(df.format(random).replaceAll(",", "."));

		liste.sort(comparator);

		Symbole resultat=liste.get(0);

		double rare =0;
		for(Symbole symbole : liste)
		{
			if(symbole.getRarete() >= random && random > rare)
			{
				resultat = symbole;
				rare = symbole.getRarete();
			}
		}
	   return resultat;
	}

	/**
	 * Affiche la liste des symboles
	 */
	public static void afficher()
	{
		for(Symbole symbole : Tirage.liste)
		{
			System.out.println(symbole.getNom());
		}

	}

}
