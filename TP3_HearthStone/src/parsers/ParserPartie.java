package parsers;

import java.io.File;
import java.io.FileNotFoundException;
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

	/**
	 * Retourne la map ayant pour clef l'id de la partie
	 * 
	 * @return mapDeck
	 */
	public Map<Integer, PaireDecks> parseFile() {
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

				if (oldIndicePArtie != indicePartie) { // If we change to a new party
					PaireDecks decksPartie = new PaireDecks(newDeck1, newDeck2);
					mapDeck.put(oldIndicePArtie, decksPartie);
					oldIndicePArtie = indicePartie;
					newDeck1 = new ArrayList<>(); // Deck reset
					newDeck2 = new ArrayList<>();
				}

				String sub = lineComputed[1].substring(1);
				switch (lineComputed[1].charAt(0)) { // First letter define if the player was M or O, if B it's a new
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
			System.out.println("Scan error");
			e.printStackTrace();
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
