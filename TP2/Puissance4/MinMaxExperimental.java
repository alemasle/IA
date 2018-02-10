
/**
 * 
 * Algorithme MiniMax.
 * 
 * @author Alexis LE MASLE et DIDOT Gwendal
 *
 */
public class MinMaxExperimental {

	public MinMaxExperimental() {

	}

	public static double maxiMin(Grille grille) {
		double m = 0;
		Grille pred[] = new Grille[7];

		if (grille.estPleine()) { // si FEUILLE(R) alors alpha <- h(R)

		}

		for (int i = 0; i < 7; i++) { // maximum des evaluations des sous-arbres
			m = Math.max(m, miniMax(pred[i]));
		}

		return m; // beta <- min (maximin (succ(R)), maximin (succ(R)), ..., maximin(succ(R)) )
	}

	public static double miniMax(Grille grille) {
		double m = 0;

		return m;
	}

}
