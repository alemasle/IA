
/**
 * 
 * Algorithme MiniMax.
 * 
 * @author Alexis LE MASLE et DIDOT Gwendal
 *
 */
public class MiniMaxExperimental {

	private int joueur;

	private Grille suivant;

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
	private double maxiMin(Grille grille, int depth) {
		double m = 10000;
		int coupPossible[] = grille.generateurCoups();
		int cpLen = coupPossible.length;
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		Grille pred[] = new Grille[cpLen];

		if (depth == 0 || grille.estPleine() || cpLen == 0) { // si FEUILLE(R) alors alpha <- h(R)
			return eval.evaluation(grille, joueur);
		}

		for (int i = 0; i < cpLen; i++) { // maximum des evaluations des sous-arbres
			double tmp = Math.min(m, miniMax(pred[i], depth - 1));
			if (tmp < m) {
				m = tmp;
			}
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
	private double miniMax(Grille grille, int depth) {
		double m = -10000;
		int coupPossible[] = grille.generateurCoups();
		int cpLen = coupPossible.length;
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		Grille pred[] = new Grille[cpLen];

		if (depth == 0 || grille.estPleine() || cpLen == 0) { // si FEUILLE(R) alors alpha <- h(R)
			return eval.evaluation(grille, joueur);
		}

		for (int i = 0; i < cpLen; i++) { // maximum des evaluations des sous-arbres
			double tmp = Math.max(m, maxiMin(pred[i], depth - 1));
			if (tmp > m) {
				m = tmp;
				suivant = pred[i];
			}
		}

		return m; // beta <- min (maximin (succ(R)), maximin (succ(R)), ..., maximin(succ(R)) )
	}

	public Grille getBestCoup(Grille grille, int joueur) {
		miniMax(grille, joueur);
		return suivant;
	}

}
