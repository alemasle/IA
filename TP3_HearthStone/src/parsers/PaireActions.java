package parsers;

import java.util.List;

public class PaireActions {

	private List<ActionsPlayer> act1;

	private List<ActionsPlayer> act2;

	public PaireActions(List<ActionsPlayer> act1, List<ActionsPlayer> act2) {
		this.act1 = act1;
		this.act2 = act2;
	}

	public List<ActionsPlayer> getFirst() {
		return act1;
	}

	public List<ActionsPlayer> getSecond() {
		return act2;
	}

}
