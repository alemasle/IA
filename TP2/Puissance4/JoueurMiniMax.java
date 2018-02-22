
/**
 * Joueur IA utilisant l'algorithme minimax
 * 
 * @author Alexis LE MASLE et Gwendal DIDOT
 *
 */
public class JoueurMiniMax implements Joueur {

	/**
	 * Fonction qui indique dans quelle colonne de la grille jouer, en choisissant
	 * aleatoirement une colonne vide.
	 * 
	 * @param grille
	 *            : la grille de puissance 4.
	 * @param joueur
	 *            : le joueur qui doit jouer le coup.
	 * @return l'indice de la colonne dans laquelle poser le pion ainsi que la
	 *         valeur associee a la nouvelle grille.
	 */
	public Resultat coup(Grille grille, int joueur) {
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		System.out.println("eval basique: " + eval.evaluation(grille, joueur));
		MiniMaxExperimental mmE = new MiniMaxExperimental(joueur);
		int depth = 3;
		int col = mmE.getBestCoup(grille, depth);
		return new Resultat(col, 0);
	}
}
