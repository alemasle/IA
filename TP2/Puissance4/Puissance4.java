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

	/**
	 * Joueur IA
	 */
	private static int IA;

	/**
	 * Cas ou deux IA jouent l'une contre l'autre
	 */
	private static int IA2;

	/**
	 * Determine si le joueur est une IA
	 * 
	 * @param j
	 *            Le joueur
	 * @return true si j est une instance de MiniMax ou AlphaBeta
	 */
	private static boolean isIA(Joueur j) {
		boolean b = false;
		if (j instanceof JoueurMiniMax || j instanceof JoueurAlphaBeta) {
			b = true;
		}
		return b;
	}

	public static void main(String[] args) {
		// creation des joueurs et appel de la fonction jouer
		// Joueur joueur1 = new JoueurHumain();
		Joueur joueur1 = new JoueurAlphaBeta(10); // Les parametres de constructeur servent juste a choisir une
													// profondeur
		Joueur joueur2 = new JoueurMiniMax(3);
		// JoueurAleatoire joueur2 = new JoueurAleatoire();
		// JoueurHumain joueur2 = new JoueurHumain();

		jouer(joueur1, joueur2); // Joueur 1 commence avant
	}

	private static String affichageJ(int numJoueur) {
		return numJoueur == Grille.JOUEUR1 ? "1" : "2";
	}

	/**
	 * Fonction qui effectue la boucle de jeu.
	 * 
	 * @param joueur1
	 *            : le premier joueur.
	 * @param joueur2
	 *            : le second joueur.
	 */
	public static void jouer(Joueur joueur1, Joueur joueur2) {
		Resultat res;
		int coup = -1;
		Grille grille = new Grille();
		int cptTour = 0; // Compte le nombre de tours

		Joueur joueurCour = joueur1;
		int numJoueur = Grille.JOUEUR1; // le joueur 1 commence a jouer

		int vainqueur = 0;// match nul
		boolean jeuFini = false;

		if (isIA(joueur1) && isIA(joueur2)) { // Les deux joueurs sont des IA
			IA = -1;
			IA2 = 1;
		} else if (!isIA(joueur1) && isIA(joueur2)) { // L'IA est joueur 2
			IA = 1;
		} else if (isIA(joueur1) && !isIA(joueur2)) { // L'IA est joueur 1
			IA = -1;
		}

		// boucle de jeu
		while (!jeuFini) {
			// affichage de la grille
			System.out.println(grille);

			if (coup != -1) {
				System.out.println("Joueur " + affichageJ(-numJoueur) + " a joue en colonne: " + coup + "\n");
			}

			System.out.println("Au tour de Joueur " + affichageJ(numJoueur) + " joue.");
			res = joueurCour.coup(grille, numJoueur);

			coup = res.getColonne();

			jeuFini = grille.coupGagnant(numJoueur, coup);

			if (jeuFini) {
				vainqueur = numJoueur;
			}

			grille.joueEn(numJoueur, coup);

			joueurCour = numJoueur == Grille.JOUEUR1 ? joueur2 : joueur1;
			numJoueur = -numJoueur;
			if (grille.estPleine())
				jeuFini = true;

			cptTour++;

		} // while - boucle de jeu

		// affichage de la grille
		System.out.println(grille);
		// System.out.println("");

		// affichage du vainqueur
		switch (vainqueur) {
		case Grille.JOUEUR1:
			System.out.println("Victoire du joueur 1");
			break;
		case Grille.JOUEUR2:
			System.out.println("Victoire du joueur 2");

			break;
		default:
			System.out.println("Match nul");
		}

		System.out.println("");
		if (IA == vainqueur || IA2 == vainqueur) {
			if (cptTour <= 10) {
				System.out.println("Joueur " + affichageJ(vainqueur)
						+ " dit : Je t'ai battu vraiment vite, concentre toi la prochaine fois");
			} else if (cptTour >= 20) {
				System.out.println("Joueur " + affichageJ(vainqueur)
						+ " dit : Tu t'es bien defendu mais j'ai ete meilleur, mais tu peux toujours reesayer!");
			} else {
				System.out.println("Joueur " + affichageJ(vainqueur)
						+ " dit : Dommage ! Peut etre pourras-tu me vaincre une prochaine fois!");
			}
		}
	}

}
