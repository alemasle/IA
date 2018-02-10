/*
 * TP 3 : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Joueur qui choisit aleatoirement dans quelle colonne de la grille jouer.
 * 
 */
public class JoueurAleatoire implements Joueur {

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
		int col = grille.getCoupAleatoire();

		return new Resultat(col, 0);
	}

}
