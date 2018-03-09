package DataWorker;

import parsers.*;

import java.util.*;

public class Worker {
	private BiblioDeck biblioDeck;

	public Worker(BiblioDeck biblioDeck) {
		this.biblioDeck = biblioDeck;
	}

	/**
	 * Turn a list of cards to a set of ID
	 * 
	 * @param deck
	 * @return the set of ID
	 */
	public SortedSet<Integer> deckToID(List<String> deck) {
		SortedSet<Integer> deckForSPMF = new TreeSet<>(); // On rend un set qui automatiquement trie, cela permet
															// d'eviter les carte en double
		Map<Integer, String> bib = biblioDeck.getBib();
		for (String card : deck) {
			try {
				for (Integer i : bib.keySet()) {
					String bibCard = bib.get(i);
					if (bibCard.equals(card)) {
						deckForSPMF.add(i);
					}
				}
			} catch (Exception e) {
				System.out.println("La carte \"" + card + "\" does not exist.");
			}
		}

		return deckForSPMF;
	}

	/**
	 * Turn id into Cards
	 * 
	 * @param id
	 * @return List of cards from ID
	 */
	public List<String> idToDeck(SortedSet<Integer> id) {
		List<String> res = new ArrayList<>();

		Map<Integer, String> bib = biblioDeck.getBib();

		for (Integer i : id) {
			String s = bib.get(i);
			res.add(s);
		}

		return res;
	}

}
