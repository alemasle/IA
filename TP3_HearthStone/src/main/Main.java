package main;

import java.io.File;
import java.util.Map;

import parsers.PaireDecks;
import parsers.ParserPartie;

public class Main {

	public static void main(String[] args) {

		File f = new File("data/all_absolute+.txt");

		ParserPartie pars = new ParserPartie(f);

		Map<Integer, PaireDecks> m = pars.parseFile();

		// System.out.println(pars.toString());

		for (Integer i : m.keySet()) {

			System.out.println("\nPartie " + i + " :\n" + m.get(i).toString());

		}

	}

}
