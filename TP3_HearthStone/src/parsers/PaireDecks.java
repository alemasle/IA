package parsers;

import java.util.List;

public class PaireDecks {

	/**
	 * Les deux listes de cartes ( les decks )
	 */
	private List<String> deck1;

	private List<String> deck2;

	public PaireDecks(List<String> deck1, List<String> deck2) {
		this.deck1 = deck1;
		this.deck2 = deck2;
	}

	public List<String> getFirst() {
		return deck1;
	}

	public void setFirst(List<String> deck1) {
		this.deck1 = deck1;
	}

	public List<String> getSecond() {
		return deck2;
	}

	public void setSecond(List<String> deck2) {
		this.deck2 = deck2;
	}

	/**
	 * toString function for deck1
	 * 
	 * @return res the string
	 */
	public String strDeck1() {
		String res = "[";

		if (deck1.isEmpty()) {
			return "[ No cards were played ]";
		}

		for (int i = 0; i < deck1.size(); i++) {

			if (i == deck1.size() - 1) {
				res += " " + deck1.get(i);
			} else {
				res += " " + deck1.get(i) + ",";
			}
		}

		return res + " ]"; // Deleting the last comma and space
	}

	/**
	 * toString function for deck2
	 * 
	 * @return res the string
	 */
	public String strDeck2() {
		String res = "[";

		if (deck2.isEmpty()) {
			return "[ No cards were played ]";
		}

		for (int i = 0; i < deck2.size(); i++) {

			if (i == deck2.size() - 1) {
				res += " " + deck2.get(i);
			} else {

				res += " " + deck2.get(i) + ",";
			}
		}

		return res + " ]"; // Deleting the last comma and space
	}

	/**
	 * ToString function
	 */
	@Override
	public String toString() {
		return "Deck 1 : " + strDeck1() + "\nDeck 2 : " + strDeck2();
	}
}
