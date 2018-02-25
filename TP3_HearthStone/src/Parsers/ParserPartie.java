package Parsers;

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

			while (scan.hasNext()) {
				String line = scan.nextLine(); 
				String[] lineComputed = line.split(" ");
				String indicePartie = lineComputed[0];
				
				
				
				bib.addCard(lineComputed[1].substring(1));
				switch (lineComputed[1].charAt(0)) {
				case 'M' :
					newDeck1.add(lineComputed[1].substring(1));
					break;
				case 'O' :
					newDeck2.add(lineComputed[1].substring(1));
					break;
				default : System.out.println("Start of a new party");
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return mapDeck;
	}

	// GETTER ET SETTER

	public File getF2s() {
		return f2s;
	}

	public void setF2s(File f2s) {
		this.f2s = f2s;
	}

}