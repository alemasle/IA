
/**
 * Joueur IA utilisant l'algorithme AlphaBeta
 * 
 * @author Alexis LE MASLE et Gwendal DIDOT
 *
 */
public class JoueurAlphaBeta implements Joueur {

	public int DEPTH;

	public JoueurAlphaBeta(int depth) {
		this.DEPTH = depth;
	}

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
		AlphaBeta mmE = new AlphaBeta(joueur);
		int col = mmE.getBestCoup(grille, DEPTH);
		return new Resultat(col, eval.evaluation(grille, joueur));
	}
}
