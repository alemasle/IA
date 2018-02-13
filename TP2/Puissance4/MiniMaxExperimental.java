
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

	private int coup;

	/**
	 * Constructeur de la classe {@link MiniMaxExperimental}
	 * 
	 * @param joueur
	 */
	public MiniMaxExperimental(int joueur) {
		this.joueur = joueur;
		this.coup = 0;
	}

	/**
	 * Partie maxiMin de l'algorithme.
	 * 
	 * @param grille
	 * @param depth
	 * @return
	 */
	private double maxiMin(Grille grille, int depth) {
		double m = -10000;
		int coupPossible[] = grille.generateurCoups();
		int cpLen = coupPossible.length;
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		Grille pred[] = new Grille[cpLen];

		if (depth == 0 || grille.estPleine() || cpLen == 0) { // si FEUILLE(R) alors alpha <- h(R)
			double ev = eval.evaluation(grille, joueur);
			System.out.println("eval: " + ev + " depth = " + depth);
			return ev;
		}

		for (int i = 0; i < cpLen; i++) { // maximum des evaluations des sous-arbres
			Grille g = new Grille(grille);
			int cp = coupPossible[i];
			if (g.coupGagnant(joueur, cp)) {
				coup = cp;
				suivant = pred[i];
				return 1000000; // Grille gagnante pour joueur 1
			}
			g.joueEn(-joueur, coupPossible[i]);
			pred[i] = g;
			double tmp = Math.max(m, miniMax(pred[i], depth - 1));
			if (tmp < m) {
				m = tmp;
				coup = coupPossible[i];
				System.out.println("coup: " + coup);
				suivant = pred[i];
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
		double m = 10000;
		int coupPossible[] = grille.generateurCoups();
		int cpLen = coupPossible.length;
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		Grille pred[] = new Grille[cpLen];

		if (depth == 0 || grille.estPleine() || cpLen == 0) { // si FEUILLE(R) alors alpha <- h(R)
			double ev = eval.evaluation(grille, joueur);
			System.out.println("eval: " + ev + " depth = " + depth);
			return ev;
		}

		for (int i = 0; i < cpLen; i++) { // maximum des evaluations des sous-arbres
			Grille g = new Grille(grille);
			int cp = coupPossible[i];
			if (g.coupGagnant(joueur, cp)) {
				return -1000000; // Grille gagnante pour joueur 2
			}
			g.joueEn(joueur, cp);
			pred[i] = g;
			double tmp = Math.min(m, maxiMin(pred[i], depth - 1));
			System.out.println("m=" + m + " tmp=" + tmp);
			if (tmp > m) {
				m = tmp;
			}
		}
		return m; // beta <- min (maximin (succ(R)), maximin (succ(R)), ..., maximin(succ(R)) )
	}

	/**
	 * Permet de recuperer la grille comprenant le meileur coup deja joue.
	 * 
	 * @param grille
	 * @param joueur
	 * @return la grille avec le meilleur coup possible joue.
	 */
	public int getBestCoup(Grille grille, int depth) {
		maxiMin(grille, depth);
		return coup;
	}

}
