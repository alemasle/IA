
/**
 * 
 * Algorithme MiniMax.
 * 
 * @author Alexis LE MASLE et DIDOT Gwendal
 *
 */
public class MiniMaxExperimental {

	private int joueur;

	/**
	 * Constructeur de la classe {@link MiniMaxExperimental}
	 * 
	 * @param joueur
	 */
	public MiniMaxExperimental(int joueur) {
		this.joueur = joueur;
	}

	/**
	 * Partie maxiMin de l'algorithme.
	 * 
	 * @param grille
	 * @param depth
	 * @return
	 */
	public double maxiMin(Grille grille, int depth) {

		double m = -10000;
		int coupPossible[] = grille.generateurCoups();
		int cpLen = coupPossible.length;
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		Grille pred[] = new Grille[cpLen];

		if (depth == 0 || grille.estPleine() || cpLen == 0) { // si FEUILLE(R) alors alpha <- h(R)
			return eval.evaluation(grille, joueur);
		}

		for (int i = 0; i < cpLen; i++) { // maximum des evaluations des sous-arbres
			m = Math.max(m, miniMax(pred[i], depth - 1));
		}

		return m; // beta <- min (maximin (succ(R)), maximin (succ(R)), ..., maximin(succ(R)) )
	}

	/**
	 * Partie miniMax de l'algorithme.
	 * 
	 * @param grille
	 * @param depth
	 * @return
	 */
	public double miniMax(Grille grille, int depth) {
		double m = 10000;

		return m;
	}

}
