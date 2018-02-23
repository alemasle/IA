package Parsers;

import java.util.*;

public class BiblioDeck {

	/*
	 * Map <Integer id Carte, String nom carte>
	 */
	private Map<Integer, String> bib = new HashMap<>();

	/**
	 * Id de depart des cartes
	 */
	private int id = 1;

	/**
	 * Ajoute si possible une carte dans la bibliotheque des cartes
	 * @param card
	 */
	public void addCard(String card) {
		if (!bib.containsValue(card)) {
			bib.put(id, card);
			id++;
		} else {
			System.out.println("La carte " + card + " existe deja!");
		}
	}

	/**
	 * 
	 * @return
	 */
	public Map<Integer, String> getBib() {
		return bib;
	}

	public void setBib(Map<Integer, String> bib) {
		this.bib = bib;
	}

}
