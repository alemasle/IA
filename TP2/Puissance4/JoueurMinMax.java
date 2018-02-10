import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.max;

public class JoueurMinMax implements Joueur {

	public List<Grille> genFille(Grille grille, int joueur) {
		List<Grille> listFille = new ArrayList<>();
		int[] coupPossible = grille.generateurCoups();
		for (int i = 0; i < coupPossible.length - 1; i++) {
			Grille grilleFille = new Grille(grille);
			grilleFille.joueEn(joueur, coupPossible[i]);
		}
		return listFille;
	}

	public Grille mAX(List<Grille> listeFilles, int joueur) {
		Grille grilleChoisis;
		FonctionEvaluation eval = new FonctionEvaluationProf();
		double maxEval = 0;
		int maxIndice = 0;
		for (int i = 0; i < listeFilles.size() - 1; i++) {
			double evaluation = eval.evaluation(listeFilles.get(i), joueur);
			double tmp = max(maxEval, evaluation);
			if (tmp != maxEval) {
				maxIndice = i;
				maxEval = tmp;
			}
		}
		grilleChoisis = listeFilles.get(maxIndice);
		return grilleChoisis;
	}

	@Override
	public Resultat coup(Grille grille, int joueur) {
		Resultat res = new Resultat();
		List<Grille> listeFilles = genFille(grille, joueur);
		mAX(listeFilles, joueur);
		return res;
	}
}