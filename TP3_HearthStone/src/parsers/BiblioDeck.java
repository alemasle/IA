package parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	 * Ajoute si possible une carte dans la bibliotheque
	 * 
	 * @param card
	 */
	public void addCard(String card) {
		if (!bib.containsValue(card)) {
			bib.put(id, card);
			id++;
		}
	}

	/**
	 * 
	 * @return bib la bibliotheque de carte
	 */
	public Map<Integer, String> getBib() {
		return bib;
	}

	public void setBib(Map<Integer, String> bib) {
		this.bib = bib;
	}

	@Override
	public String toString() {
		String res = "{ ";
		Set<Integer> s = bib.keySet();
		for (Integer i : s) {
			if (i == s.size() - 1) {
				res += bib.get(i);
			} else {
				res += bib.get(i) + ", ";
			}
		}
		return res.substring(2, res.length()) + " }";
	}

}
