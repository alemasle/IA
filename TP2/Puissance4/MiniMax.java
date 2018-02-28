import java.util.TreeMap;

/**
 * Classe implementant l'algorithme MiniMax
 * 
 * @author Alexis LE MASLE et Gwendal DIDOT
 *
 */
public class MiniMax {

	/**
	 * Joueur 1
	 */
	private final int J1 = -1;

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
	public MiniMax(int joueur) {
		this.joueur = joueur;
	}

	/**
	 * Permet de transformer le joueur en numero de joueur joueur -1 est joueur 1;
	 * joueur 1 est joueur 2
	 * 
	 * @return le numero du joueur
	 */
	private String getJoueur() {
		return joueur == -1 ? "1" : "2";
	}

	/**
	 * Permet de retourner sous forme de String le numero de l'adversaire
	 * 
	 * @return le numero de l'adversaire
	 */
	private String getJoueurNext() {
		return joueur == -1 ? "2" : "1";
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

		TreeMap<Integer, Double> map = new TreeMap<Integer, Double>(); // Map<Integer, Double> (Colonne, eval)
		double tmp = 0;
		int coups[] = g.generateurCoups();

		for (int i = 0; i < coups.length; i++) {
			Grille grille = new Grille(g);

			if (grille.peutJouerEn(coups[i])) {
				if (g.coupGagnant(joueur, coups[i])) {
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("Joueur " + getJoueur() + " dit : Je gagne en colonne " + coups[i]
							+ " ! Il Fallait etre plus attentif!");
					return coups[i];
				}
				if (g.coupGagnant(-joueur, coups[i])) {
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("Joueur " + getJoueur() + " dit : Dommage joueur " + getJoueurNext()
							+ " tu ne gagnera pas en colonne " + coups[i] + " !");
					return coups[i];

				} else {
					grille.joueEn(joueur, coups[i]);
					tmp = miniMax(grille, depth - 1);
					map.put(coups[i], tmp);
				}
			}
		}
		return bestCol(map);
	}

	/**
	 * Recupere la meilleure colonne parmis les 7 potentielles possibles
	 * 
	 * @param m
	 *            La map contenant les evaluations par coups
	 * @return La colonne a jouer selon l'evaluation
	 */
	private int bestCol(TreeMap<Integer, Double> m) {

		if (m.isEmpty()) {
			return 0;
		}

		int coup = m.firstKey();

		for (Integer i : m.keySet()) {
			coup = m.get(i) > m.get(coup) ? i : coup;
		}

		return coup;
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

		int coups[] = g.generateurCoups(); // Le tableau des coups possibles

		if (depth >= 0) { // Quand il reste encore des profondeurs a explorer
			Grille grille = g.copie();
			depth--;

			for (int i = 0; i < coups.length; i++) { // Pour chacun des coups possible on prend le max

				if (grille.coupGagnant(joueur, coups[i])) {
					return FonctionEvaluationProf.MAX;
				}

				grille.joueEn(joueur, coups[i]);
				m = Math.max(m, miniMax(grille, depth - 1));
			}

		} else {
			m = eval.evaluation(g, J1);
		}
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

		int coups[] = g.generateurCoups(); // Le tableau des coups possibles

		if (depth >= 0) { // Quand il reste encore des profondeurs a explorer
			Grille grille = g.copie();
			depth--;

			for (int i = 0; i < coups.length; i++) { // Pour chacun des coups possible on prend le min

				if (grille.coupGagnant(-joueur, coups[i])) {
					return FonctionEvaluationProf.MIN;
				}

				grille.joueEn(-joueur, coups[i]);
				m = Math.min(m, maxiMin(grille, depth - 1));
			}

		} else {
			m = eval.evaluation(g, J1); // Profondeur atteinte
		}
		return m;

	}

}
