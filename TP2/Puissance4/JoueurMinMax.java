import java.util.ArrayList;
import java.util.List;

public class JoueurMinMax implements Joueur {


    public List<Grille> genFille(Grille grille, int joueur){
        List<Grille> listFille = new ArrayList<>();
        int [] coupPossible = grille.generateurCoups();
        for (int i = 0; i < coupPossible.length - 1; i++) {
            Grille grilleFille = new Grille(grille);
            grilleFille.joueEn(joueur, coupPossible[i]);
        }
        return listFille;
    }


    @Override
    public Resultat coup(Grille grille, int joueur) {
        Resultat res = new Resultat();
        List<Grille> listeFilles = genFille(grille, joueur);
        List<Double> listEval = new ArrayList<>();
        FonctionEvaluation eval = new FonctionEvaluationProf();
        for (int i = 0; i < listeFilles.size() - 1; i++) {
            listEval.add(eval.evaluation(listeFilles.get(i), joueur));
        }
        return res;
    }
}