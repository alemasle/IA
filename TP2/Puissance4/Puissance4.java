/*
 * TP 3 : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Programme principal du jeu du puissance 4.
 * 
 */
public class Puissance4 {

	public static void main(String[] args) {
		//création des joueurs et appel de la fonction jouer
		JoueurHumain joueur1 = new JoueurHumain();
		JoueurAleatoire joueur2 = new JoueurAleatoire();
		
		jouer(joueur1, joueur2);
	}
	
	
	/**
	 * Fonction qui effectue la boucle de jeu.
	 * 
	 * @param joueur1 : le premier joueur.
	 * @param joueur2 : le second joueur.
	 */
	public static void jouer(Joueur joueur1, Joueur joueur2){
		Resultat res;
		int coup;
		Grille grille = new Grille();
		
		Joueur joueurCour = joueur1;	
		int numJoueur = Grille.JOUEUR1; //le joueur 1 commence à jouer
		
		int vainqueur = 0;//match nul
		boolean jeuFini = false;
		
		
		//boucle de jeu
		while(!jeuFini){
			//affichage de la grille 
			System.out.println(grille);
			
			//faire jouer le joueur courant et passer au suivant
			//TODO...
		}//while - boucle de jeu
		
		//affichage de la grille 
		System.out.println(grille);
		
		
		//affichage du vainqueur
		switch(vainqueur){
			case Grille.JOUEUR1:
				System.out.println("Victoire du joueur 1");
				break;
			case Grille.JOUEUR2:
				System.out.println("Victoire du joueur 2");
				break;
			default:
				System.out.println("Match nul");
		}
	}

}
