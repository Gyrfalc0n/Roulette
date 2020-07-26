import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Roulette {
	//variables pour les pourcentages
	public static int compte = 0;
	public static int positif = 0;
	public static int nombre = 0;
	//variables pour un jeu
	public static int tirage;
	public static int gain;
	public static int gain_algebrique;
	public static int mise;
	public static int multiplier;
	public static int count;
	public static int mise_finale;
	public static int depense;
	//variables pour le tout 
	public static int but;
	public static int solde;
	public static int total_gain_algebrique;
	public static int total_gain;
	public static int total_depense;
	public static int total_count;
	

	public static void main(String[] args) {
		for (int i = 0; i<5000000; i++) {
			game_limit();
			compte++;
		}
		double dpositif = (double)positif;
		double dcompte = (double)compte;
		double pourcent = (dpositif/dcompte)*100.0;
		System.out.print("\t\tDONNEES\n"+"Mise initiale : "+mise+"€"+"\nMultiplicateur : "+multiplier+"\nBenefice voulu : "+but+"€"+"\nSolde : "+solde+"€"+"\n");
		System.out.print("Essais : "+compte+"\nPositifs : "+positif);
		System.out.print("\nPourcentage de reussite avec solde : " + pourcent+"%\n");
	}
	
	public static void game() {
		initialize();
		//menu();
		do {
			jeu();
			affiche_jeu(count);
			reset();
		} while (total_gain_algebrique < but);
		affiche_total();
	}
	
	public static void game_limit() {
		initialize();
		//menu();
		do {
			jeu();
			//affiche_jeu(count);
			
		} while (total_gain_algebrique < but);
		//System.out.print(total_depense+"\n");
		if (solde >= total_depense) {
			positif++;
		}
		nombre += total_count;
		reset();
		//affiche_total();
	}
	
	public static void initialize() {
		mise = 3; //Mise de base
		multiplier = 2; //Par combien est multipliée la mise de rang n pour déterminer la mise de rang n+1
		count = 0;
		mise_finale = mise;
		depense = mise;
		//totale
		but = 50;
		total_gain = 0;
		total_depense = 0;
		total_count = 0;
		//pourcentage
		solde = 100;
	}
	
	public static void reset() {
		count = 0;
		depense = mise;
		mise_finale = mise;
		total_gain = 0;
		total_depense = 0;
		total_count = 0;
	}
	
	public static void menu() {
		System.out.print("Roulette statistique tirages\n\n");
		System.out.print("Modifier valeurs par défaut ? (0/1) : ");
		Scanner clavier = new Scanner(System.in);
		int entry = clavier.nextInt();
		
		switch (entry) {
		case 1:
			System.out.print("\nMise initiale : ");
			mise = clavier.nextInt();
			System.out.print("\nMultiplicateur de mise : ");
			multiplier = clavier.nextInt();
			System.out.print("\nGain à atteindre : ");
			but = clavier.nextInt();
			System.out.print("\nSolde d'entrée : ");
			solde = clavier.nextInt();
			break;
		default:
			break;
		}
		clavier.close();
	}
	
	public static void affiche_jeu(int count) {
		System.out.printf("Depense : "+depense+"\tGain : "+gain+"\tBénéfices : "+gain_algebrique+"\tCompteur : "+count+"\n");
	}
	
	public static void affiche_total() {
		System.out.println("\n\t\tTOTAL\n");
		System.out.printf("Compteur : " + total_count+"\nDepense : "+total_depense+"\nGain : "+total_gain+"\nBénéfices : "+total_gain_algebrique);
	}
	
	public static void jeu() {
		do {
			tirage = tirage();
			count++;
		} while (!is_win(tirage));
		for (int i = 1; i<count; i++) {
			mise_finale = mise_finale * multiplier;
			depense += mise_finale;
		}
		gain = mise_finale * 2;
		gain_algebrique = gain - depense;
		total_count += count;
		total_gain += gain;
		total_depense += depense;
		total_gain_algebrique += gain_algebrique;
	}
	
	
	public static int tirage() {
		int tirage = ThreadLocalRandom.current().nextInt(0, 36);
		return tirage;
	}
	
	public static boolean is_win(int value) {
		if (value % 2 == 0) { //win calculée sur un chiffre pair
			return true;
		}
		return false;
	}

}
