/*
 * TP 3  : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Interface pour la definition d'une fonction d'evaluation d'une grille.
 * 
 */
public interface FonctionEvaluation {
	public static final double MIN = Double.NEGATIVE_INFINITY;
	public static final double MAX = Double.POSITIVE_INFINITY;

	/**
	 * Fonction qui donne une valeur a la grille, pour le joueur, de telle facon que
	 * la valeur soit egale a MAX si joueur gagne, egale a 0 s'il y a match nul et
	 * egale a MIN si joueur perd.
	 * 
	 * @param grille
	 *            : la grille de puissance 4.
	 * @param joueur
	 *            : le joueur qui joue le coup.
	 * @return la valeur donnee a la grille, pour le joueur.
	 */
	public double evaluation(Grille grille, int joueur);
}
