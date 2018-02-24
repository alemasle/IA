import java.util.TreeMap;

/**
 * Classe implementant l'algorithme MiniMax
 * 
 * @author Alexis LE MASLE et Gwendal DIDOT
 *
 */
public class MiniMaxV2 {

	/**
	 * Le joueur a qui profite l'algorithme MiniMax
	 */
	private int joueur;

	/**
	 * Constructeur de la classe MiniMax
	 * 
	 * @param joueur
	 *            Le joueur profitant de l'algorithme
	 */
	public MiniMaxV2(int joueur) {
		this.joueur = joueur;
	}

	/**
	 * 
	 * @param g
	 *            la grille initiale
	 * @param depth
	 *            la profondeur de prediction
	 * @return l'indice de la colonne a jouer
	 */
	public int getBestCoup(Grille g, int depth) {

		TreeMap<Double, Integer> map = new TreeMap<>();
		double tmp = 0;
		int coups[] = g.generateurCoups();
		int len = coups.length;

		for (int i = 0; i < len; i++) {
			Grille grille = new Grille(g);
			grille.joueEn(joueur, coups[i]);
			tmp = miniMax(grille, depth - 1);
			map.put(tmp, coups[i]);
		}

		System.out.println("treemap : " + map.toString() + "");
		return map.get(map.lastKey());
	}

	/**
	 * Partie MaxiMin de l'algorithme
	 * 
	 * @param g
	 *            La grille actuelle
	 * @param joueur
	 *            Le joueur courant
	 * @param depth
	 *            La profondeur a developper
	 * @return L'evaluation calculee a ce niveau de profondeur
	 */
	private double maxiMin(Grille g, int depth) {

		double m = FonctionEvaluationProf.MIN; // On set m a moins l'infini initialement
		FonctionEvaluation eval = new FonctionEvaluationProf();
		double tmp = eval.evaluation(g, joueur); // Evaluation initiale

		int coups[] = g.generateurCoups(); // Le tableau des coups possibles
		int len = coups.length; // La longueur du tableau

		if (tmp == FonctionEvaluationProf.MAX) { // Si l'evaluation est l'infini alors joueur gagne, on ne calcule pas
													// les noeuds suivants
			System.out.println("Coup gagnant Joueur 1: + INFINITY");
			return tmp;
		}

		if (depth != 0) { // Quand il reste encore des profondeurs a explorer
			Grille grille = new Grille(g);
			depth--;

			for (int i = 0; i < len; i++) { // Pour chacun des coups possible on prend le max
				grille.joueEn(joueur, coups[i]);
				m = Math.max(m, miniMax(grille, depth));
			}

		} else {
			m = tmp;
		}
		System.out.println("maxiMin m fin : " + m);
		return m;

	}

	/**
	 * Partie MiniMax de l'algorithme
	 * 
	 * @param g
	 *            la grille actuelle
	 * @param joueur
	 *            le joueur courant
	 * @param depth
	 *            la profondeur a developper
	 * @return L'evaluation calculee a ce niveau de profondeur
	 */
	private double miniMax(Grille g, int depth) {

		double m = FonctionEvaluationProf.MAX; // On set m a moins l'infini initialement
		FonctionEvaluation eval = new FonctionEvaluationProf();
		double tmp = eval.evaluation(g, joueur); // Evaluation initiale

		int coups[] = g.generateurCoups(); // Le tableau des coups possibles
		int len = coups.length; // La longueur du tableau

		if (tmp == FonctionEvaluationProf.MIN) { // Si l'evaluation est a moins l'infini alors joueur perd, on ne
													// calcule pas les noeuds suivants
			System.out.println("Coup gagnant Joueur 2: - INFINITY");
			return tmp;
		}

		if (depth != 0) { // Quand il reste encore des profondeurs a explorer
			Grille grille = new Grille(g);
			depth--;

			for (int i = 0; i < len; i++) { // Pour chacun des coups possible on prend le min
				grille.joueEn(-joueur, coups[i]);
				m = Math.min(m, maxiMin(grille, depth));
			}

		} else {
			m = tmp;
		}
		System.out.println("MiniMax m fin : " + m);
		return m;

	}

}
