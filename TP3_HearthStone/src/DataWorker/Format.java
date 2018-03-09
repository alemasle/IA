package DataWorker;

import java.io.*;
import java.util.*;

import parsers.*;

/**
 * Class to format data in an input file or turn data from output file to data
 * 
 * @author Alexis LE MASLE et Gwendal DIDOT
 *
 */
public class Format {

	private BiblioDeck bib;

	private String inputFold = "inputs/";

	private String outputFold = "outputs/";

	// File name without folder if needed
	private String rawFile;

	/**
	 * Format from a file of raw Data to the formated InputFile input.txt
	 * 
	 * @param fileData
	 * @throws Exception
	 */
	public void toInput(String fileData) throws Exception {
		try {
			String path = parsingRaw(fileData);
			File data = new File(fileData); // File raw data

			ParserPartie pp = new ParserPartie(data);
			Map<Integer, PaireDecks> map = pp.parseFile(); // Create the bibliodeck and the map
			bib = pp.getBiblioDeck();

			File dir = new File(inputFold);

			if (!dir.exists()) {
				if (!dir.mkdirs()) {
					throw new Exception("The folder " + inputFold + " does not exist and cannot be created.");
				}
				System.out.println("Folder " + inputFold + " has been created.");
			}

			File input = new File("inputs/input-" + path); // File create to store input data ( raw data formated )
			FileWriter fw = new FileWriter(input); // Initialize

			Worker worker = new Worker(bib);

			for (Integer i : map.keySet()) {

				SortedSet<Integer> s1 = worker.deckToID(map.get(i).getFirst());
				SortedSet<Integer> s2 = worker.deckToID(map.get(i).getSecond());

				fw.write(format(s1)); // We write the transactions in the inputfile
				fw.write(format(s2));

			}

			fw.close(); // Close the FileWriter

		} catch (IOException e) {
			System.out.println("File Error");
			e.printStackTrace();
		}

	}

	/**
	 * Turn the output file into a data structure
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void fromOutput(String filename) throws Exception {
		// String path = parsingRaw(filename);
		// TODO
	}

	/**
	 * Format frome SortedSet to String
	 * 
	 * @return the result String
	 */
	private String format(SortedSet<Integer> set) {
		String s = "";
		Iterator<Integer> it = set.iterator();

		while (it.hasNext()) {
			s += it.next();
			if (it.hasNext()) {
				s += " ";
			}
		}
		return s + "\n";
	}

	/**
	 * Select the file name in a path and change rawInput or rawOutput according to
	 * the file path
	 * 
	 * @param path
	 * @return the file name
	 */
	private String parsingRaw(String path) {
		String str[] = path.split("/");

		rawFile = str[str.length - 1];
		return rawFile;
	}

	///////////// GETTER input and output folder path /////////////

	public String getInputFold() {
		return inputFold;
	}

	public String getOutputFold() {
		return outputFold;
	}

	public String getRawFile() {
		return rawFile;
	}

}
