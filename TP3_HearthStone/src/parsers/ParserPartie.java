package parsers;

import java.io.*;
import java.util.*;

public class ParserPartie {

	/**
	 * Fichier a parser
	 */
	private File f2s;
	private BiblioDeck bib;

	/**
	 * Nb elements par ligne de donnees
	 */
	public static final int SIZE_LINE = 3;

	/**
	 * Constructeur de la classe
	 * 
	 * @param f2s
	 */
	public ParserPartie(File f2s) {
		this.f2s = f2s;
		this.bib = new BiblioDeck();
	}

	public Map<Integer, PaireActions> parseFileToSeq() {

		Map<Integer, PaireActions> map = new HashMap<>();

		try {
			Scanner scan = new Scanner(f2s);

			List<ActionsPlayer> act1 = new ArrayList<>();
			List<ActionsPlayer> act2 = new ArrayList<>();
			ActionsPlayer a = new ActionsPlayer();
			int oldIndicePArtie = 0;
			char joueur = 'B';
			char joueur1 = 'B';
			char joueur2 = 'B';

			while (scan.hasNextLine()) { // File reading line per line
				String line = scan.nextLine();
				String[] lineComputed = line.split(" ");
				String sub = lineComputed[1].substring(1);
				int indicePartie = Integer.parseInt(lineComputed[0]);

				if (joueur == 'B') {
					joueur = lineComputed[1].charAt(0);
					joueur1 = joueur;
					joueur2 = joueur1 == 'M' ? 'O' : 'M';
				}

				if (oldIndicePArtie != indicePartie) {
					PaireActions p = new PaireActions(act1, act2);
					map.put(oldIndicePArtie, p);
					act1 = new ArrayList<>();
					act2 = new ArrayList<>();
					oldIndicePArtie = indicePartie;
				}

				if (joueur != lineComputed[1].charAt(0)) {
					if (joueur == joueur1) {
						bib.addCard(sub);
						act1.add(a);
					} else if (joueur == joueur2) {
						bib.addCard(sub);
						act2.add(a);
					}
					joueur = lineComputed[1].charAt(0);
					a = new ActionsPlayer();
				} else {
					a.addAction(sub);
				}
			}

			PaireActions p = new PaireActions(act1, act2);
			map.put(oldIndicePArtie, p);
			
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("\nScan error");
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		return map;
	}

	/**
	 * Retourne la map ayant pour clef l'id de la partie
	 * 
	 * @return mapDeck
	 */
	public Map<Integer, PaireDecks> parseFileToDecks() {
		Scanner scan;
		Map<Integer, PaireDecks> mapDeck = new HashMap<Integer, PaireDecks>();
		try {

			scan = new Scanner(f2s);
			List<String> newDeck1 = new ArrayList<String>();
			List<String> newDeck2 = new ArrayList<String>();
			int oldIndicePArtie = 0;

			while (scan.hasNextLine()) { // File reading line per line
				String line = scan.nextLine();
				String[] lineComputed = line.split(" ");
				int indicePartie = Integer.parseInt(lineComputed[0]);

				if (oldIndicePArtie != indicePartie) { // If we change to a new
														// party
					PaireDecks decksPartie = new PaireDecks(newDeck1, newDeck2);
					mapDeck.put(oldIndicePArtie, decksPartie);
					oldIndicePArtie = indicePartie;
					newDeck1 = new ArrayList<>(); // Deck reset
					newDeck2 = new ArrayList<>();
				}

				String sub = lineComputed[1].substring(1);
				switch (lineComputed[1].charAt(0)) { // First letter define if
														// the player was M or
														// O, if B it's a new
														// party
				case 'M':
					bib.addCard(sub);
					newDeck1.add(sub);
					break;
				case 'O':
					bib.addCard(sub);
					newDeck2.add(sub);
					break;
				default: // Case 'B'
					break;
				}
			}

			PaireDecks decksPartie = new PaireDecks(newDeck1, newDeck2);
			mapDeck.put(oldIndicePArtie, decksPartie);

		} catch (FileNotFoundException e) {
			System.out.println("\nScan error");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		return mapDeck;
	}

	// GETTER ET SETTER

	public File getFile() {
		return f2s;
	}

	public void setFile(File f2s) {
		this.f2s = f2s;
	}

	public BiblioDeck getBiblioDeck() {
		return bib;
	}

	/**
	 * Return the bib turned into a String
	 */
	@Override
	public String toString() {
		return bib.toString();
	}
}
