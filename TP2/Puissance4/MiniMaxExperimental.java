
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
	 * @return m l'evaluation de la grille optimale pour une profondeur depth.
	 */
	private double maxiMin(Grille grille, int depth) {
		double m = FonctionEvaluationProf.MIN;
		int coupPossible[] = grille.generateurCoups();
		int cpLen = coupPossible.length;
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		Grille pred[] = new Grille[cpLen];

		if (cpLen == 0 || depth == 0 || grille.estPleine()) { // si FEUILLE(R) alors alpha <- h(R)
			m = eval.evaluation(grille, joueur);
			System.out.println("EvalMaxi grille pleine " + m + ", depth = " + depth);
			return m;
		}

		for (int i = 0; i < cpLen; i++) { // maximum des evaluations des sous-arbres
			Grille g = new Grille(grille);
			int cp = coupPossible[i];

			if (g.coupGagnant(joueur, cp)) {
				m = eval.evaluation(grille, joueur);
				return m; // Grille gagnante pour joueur 1
			}

 			g.joueEn(joueur, cp);
			double tmp = Math.max(m, miniMax(g, depth - 1)); // On compare le maximum avec la nouvelle valeur qui
																// remonte
			pred[i] = g;

			if (tmp < m) {
				m = tmp;
				coup = coupPossible[i];
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
		double m = FonctionEvaluationProf.MAX;
		int coupPossible[] = grille.generateurCoups();
		int cpLen = coupPossible.length;
		FonctionEvaluationProf eval = new FonctionEvaluationProf();
		Grille pred[] = new Grille[cpLen];

		if (depth == 0 || grille.estPleine() || cpLen == 0) { // si FEUILLE(R) alors alpha <- h(R)
			m = eval.evaluation(grille, joueur);
			System.out.println("EvalMini grille pleine " + m + ", depth = " + depth);
			return m;
		}

		for (int i = 0; i < cpLen; i++) { // maximum des evaluations des sous-arbres
			Grille g = new Grille(grille);
			int cp = coupPossible[i];

			if (g.coupGagnant(-joueur, cp)) {
				m = eval.evaluation(grille, joueur);
				return m; // Grille gagnante pour joueur 2
			}

			g.joueEn(-joueur, cp);
			double tmp = Math.min(m, maxiMin(g, depth - 1)); // On compare le maximum avec la nouvelle valeur qui
																// remonte
			pred[i] = g;

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
	 * @param depth
	 * @return la grille avec le meilleur coup possible joue.
	 */
	public int getBestCoup(Grille grille, int depth) {
		System.out.println("MaxiMin = " + maxiMin(grille, depth));
		System.out.println("coup = " + coup);
		return coup;
	}

}
