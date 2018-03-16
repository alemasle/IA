package parsers;

import java.util.*;

/**
 * Object which is the list of action ( cards ) in a round
 * 
 * @author Alexis LE MASLE et Gwendal DIDOT
 *
 */
public class ActionsPlayer {

	/**
	 * The list of actions in a round
	 */
	private Set<String> actions;

	/**
	 * Constructor of ActionsPlayer
	 */
	public ActionsPlayer() {
		this.actions = new TreeSet<>();
	}

	/**
	 * Add a card in the list of actions
	 * 
	 * @param card
	 *            The card to add
	 */
	public void addAction(String card) {
		if (!isIn(card)) {
			actions.add(card);
		}
	}

	/**
	 * Return True if the card is already in the list
	 * 
	 * @param card
	 * @return
	 */
	private boolean isIn(String card) {

		for (String s : actions) {
			if (s.compareTo(card) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter
	 * 
	 * @return actions The list of actions
	 */
	public Set<String> getActions() {
		return actions;
	}

	@Override
	public String toString() {

		String res = "";

		for (String s : actions) {
			res += s + " ";
		}

		return res;
	}

}
