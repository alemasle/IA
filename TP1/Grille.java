
/*
 * TP 1 - IA Informatique : Sudoku
 * 
 * @author Tassadit BOUADI.
 */

import java.util.ArrayList;
import java.util.List;

public class Grille {
	private Case[][] _cases;
	private int _taille;
	private int _nbCasesVides;

	public Grille(int n) {
		_taille = n;
		_nbCasesVides = n * n;
		_cases = new Case[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				_cases[i][j] = new Case(i, j, n);
			}
		}
	}

	public Grille(int n, int[][] grille) {
		_taille = n;
		_nbCasesVides = n * n;
		_cases = new Case[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				_cases[i][j] = new Case(i, j, grille[i][j], n);
				if (_cases[i][j].getVal() != 0) {
					_nbCasesVides--;
				}
			}
		}
	}

	public Grille(Grille grille) {
		_taille = grille._taille;
		_nbCasesVides = grille._nbCasesVides;
		_cases = new Case[_taille][_taille];
		for (int i = 0; i < _taille; i++) {
			for (int j = 0; j < _taille; j++) {
				_cases[i][j] = new Case(grille.getCase(i, j));
			}
		}
	}

	public Case getCase(int i, int j) {
		return _cases[i][j];
	}

	public int getNbCasesVides() {
		return _nbCasesVides;
	}

	/*
	 * Fonction qui donne la liste des cases sans valeur (peut être vide)
	 */
	public List<Case> getCasePossible() {
		// to do
		List<Case> casePoss = new ArrayList<Case>();
		for (int i = 0; i < _taille; i++) {
			for (int j = 0; j < _taille; j++) {
				if (_cases[i][j].getVal() == 0) {
					casePoss.add(_cases[i][j]);
				}
			}
		}
		return casePoss;
	}

	/*
	 * Fonction qui donne la valeur v a la case c.
	 */
	public void setCase(Case c, int v) {
		_cases[c.getI()][c.getJ()].setVal(v);
		_nbCasesVides--;
	}

	/*
	 * Fonction qui rend vrai si la valeur v peut etre donnee a la case C
	 * c'est-a-dire si la grille respecte toujours les contraintes du Sudoku.
	 */
	public boolean casePossible(Case c, int v) {
		return ligne(c, v) && colonne(c, v) && region(c, v);
	}

	/**
	 * 
	 * @param c
	 * @param v
	 * @return true si v peut etre place dans la ligne
	 */
	private boolean ligne(Case c, int v) {

		for (int a = 0; a < _taille; a++) {
			if (_cases[a][c.getJ()].getVal() == v) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param c
	 * @param v
	 * @return true si v peut etre place dans la colonne
	 */
	private boolean colonne(Case c, int v) {

		for (int a = 0; a < _taille; a++) {
			if (_cases[c.getI()][a].getVal() == v) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param c
	 * @param v
	 * @return true si v peut etre place dans la region
	 */
	private boolean region(Case c, int v) {
		int tr = (int) Math.sqrt(_taille);
		int decalLigne = c.getI() / tr;
		int decalCol = c.getJ() / tr;

		for (int i = decalLigne * tr + 1; i < decalLigne + 1 * tr; i++) {
			for (int j = decalCol * tr + 1; j < decalCol + 1 * tr; j++) {
				if (_cases[i][j].getVal() == v) {
					return false;
				}
			}
		}
		return true;
	}

	public void afficheGrille() {
		int v;
		int dim = (int) Math.sqrt(_taille);
		for (int i = 0; i < _taille; i++) {
			if (i % dim == 0) {
				System.out.print(" ");
				for (int k = 0; k <= _taille; k++)
					System.out.print("--");
				System.out.println();
			}
			for (int j = 0; j < _taille; j++) {
				if (j % dim == 0) {
					System.out.print("|");
				}
				v = _cases[i][j].getVal();
				if (v == 0) {
					System.out.print("  ");
				} else {
					System.out.print(v + " ");
				}
			}
			System.out.println("|");
		}
		System.out.print(" ");
		for (int k = 0; k <= _taille; k++)
			System.out.print("--");
		System.out.println();
	}
}
