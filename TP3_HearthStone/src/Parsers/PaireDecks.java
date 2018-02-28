package Parsers;

import java.util.ArrayList;
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

	@Override
	public String toString() {
		return "Le deck 1 est : " + deck1.toString() + "\n Le deck 2 est : " + deck2.toString();
	}
}
