package Parsers;

import java.util.ArrayList;
import java.util.List;

public class PaireDecks {

	/**
	 * Les deux listes de cartes ( les decks )
	 */
	private List<String> deck1 = new ArrayList<>();

	private List<String> deck2 = new ArrayList<>();

	public PaireDecks(ArrayList<String> deck1, ArrayList<String> deck2) {
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

}
