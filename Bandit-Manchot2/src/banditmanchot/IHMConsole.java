package banditmanchot;

import java.util.Scanner;

public class IHMConsole extends IHMInterface {

	//Scanner pour demander les param�tres � l'utilisateur
	static Scanner saisie = new Scanner(System.in);

	@Override
	public  void afficheMessage(String message) {

			System.out.println(message);
		
	}

	@Override
	/**
	 * Affiche un menu et retourne le choix de l'utilisateur
	 * @param choix du menu
	 * @return L'index du choix de l'utilisateur
	 */
	public int menu(String[] choixPossible) {
		
		for(int i = 0; i < choixPossible.length; i++)
		{
			afficheMessage(i+1 + ". " + choixPossible[i]);
		}
		return (int) demanderNombreEntreBornes("Quel est votre choix ? : ",1,choixPossible.length);
	}

	@Override

	/**
	 * Demander un nombre � l'utilisateur entre 2 bornes
	 * @param phrase � afficher pour demander le nombre
	 * @param min - Borne inf�rieure
	 * @param max - Borne sup�rieure
	 * @return Le nombre saisie par l'utilisateur
	 */
	public double demanderNombreEntreBornes(String phrase, int min, int max) {

		double resultat;
		do
		{
			if(max >=0)
			{
				afficheMessage(phrase + "( entre " + min + " et " + max + " )");
			}
			else
			{
				afficheMessage(phrase + "( sup�rieur � " + min + " )");
			}
			
			resultat = saisie.nextDouble();
 		}
		while(resultat < min || (resultat > max && max != -1));


		//saisie.close();
		return resultat;
	}

	@Override
	/**
	 * Demander une chaine de caratere � l'utilisateur
	 * @param phrase � afficher
	 * @return La chaine saisie par l'utilisateur
	 */
	public String demanderString(String phrase) {

		afficheMessage(phrase);
		String res =  saisie.nextLine();
		return res;
	}

}
