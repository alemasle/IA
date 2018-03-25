package dataWorker;

import java.util.*;
import parsers.*;

public class Worker {
	private BiblioDeck biblioDeck;

	public Worker(BiblioDeck biblioDeck) {
		this.biblioDeck = biblioDeck;
	}

	/**
	 * Turn a list of id into a list of Cards
	 * 
	 * @param id
	 *            The set of id
	 * @return res The List of cards from the list of ID
	 */
	public List<String> idToDeck(List<Integer> id) {
		List<String> res = new ArrayList<>();

		Map<Integer, String> bib = biblioDeck.getBib();

		for (Integer i : id) {
			String s = bib.get(i);
			// if (s != null)
			res.add(s);
		}

		return res;
	}

	/**
	 * Turn a list of cards into a set of ID
	 * 
	 * @param deck
	 * @return the set of ID
	 */
	public Set<Integer> deckToID(List<String> deck) {
		SortedSet<Integer> deckForSPMF = new TreeSet<>();

		Map<Integer, String> bib = biblioDeck.getBib();

		for (String card : deck) {

			for (Integer i : bib.keySet()) {

				String bibCard = bib.get(i);

				if (bibCard.compareTo(card) == 0) {
					deckForSPMF.add(i);
				}
			}
		}
		return deckForSPMF;
	}

	public List<Set<Integer>> sequencesToID(List<ActionsPlayer> la) {
		List<Set<Integer>> lR = new ArrayList<>();

		Map<Integer, String> bib = biblioDeck.getBib();

		for (ActionsPlayer a : la) {

			Set<Integer> s = new TreeSet<>();

			for (String card : a.getActions()) {

				for (Integer i : bib.keySet()) {

					if (bib.get(i).compareTo(card) == 0) {
						s.add(i);
					}
				}
			}

			lR.add(s);

		}

		return lR;

	}

}
