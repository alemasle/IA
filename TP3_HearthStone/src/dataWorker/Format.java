package dataWorker;

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
	// can be the rawData file name or the output file name
	private String rawFile;

	/**
	 * Format from a file of raw Data to the formated Charm InputFile input.txt
	 * 
	 * @param fileData
	 * @throws Exception
	 */
	public void toCharm(String fileData) throws Exception {
		try {
			String path = parsingRaw(fileData);
			File data = new File(fileData); // File raw data

			ParserPartie pp = new ParserPartie(data);
			Map<Integer, PaireDecks> map = pp.parseFileToDecks(); // Create the
																	// bibliodeck
																	// and the
																	// map
			bib = pp.getBiblioDeck();

			File dir = new File(inputFold);
			File dirOut = new File(outputFold);

			if (!dir.exists()) { // If the folder inputs/ does not exists.
				if (!dir.mkdirs()) {
					throw new Exception("The folder " + inputFold + " does not exist and cannot be created.");
				}
				System.out.println("Folder " + inputFold + " has been created.");
			}

			if (!dirOut.exists()) { // If the folder outputs/ does not exists.
				if (!dirOut.mkdirs())
					throw new Exception("The folder " + outputFold + " does not exist and cannot be created.");
				System.out.println("Folder " + outputFold + " has been created.");
			}

			File input = new File("inputs/inputCharm-" + path); // File create
																// to store
																// input data (
																// raw data
																// formated )
			FileWriter fw = new FileWriter(input); // Initialize

			Worker worker = new Worker(bib);

			for (Integer i : map.keySet()) {

				Set<Integer> s1 = worker.deckToID(map.get(i).getFirst());
				Set<Integer> s2 = worker.deckToID(map.get(i).getSecond());

				fw.write(formatCharm(s1)); // We write the transactions in the
											// inputfile
				fw.write(formatCharm(s2));

			}

			fw.close(); // Close the FileWriter

		} catch (IOException e) {
			System.out.println("File Error");
			e.printStackTrace();
		}

	}

	/**
	 * Format a RawData file into a formated file CloSpan
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void toCloSpan(String filename) throws Exception {
		String path = parsingRaw(filename);
		File data = new File(filename);

		ParserPartie pp = new ParserPartie(data);
		Map<Integer, PaireActions> map = pp.parseFileToSeq(); // Create the
																// bibliodeck
																// and the map
		bib = pp.getBiblioDeck();

		File dir = new File(inputFold);
		File dirOut = new File(outputFold);

		if (!dir.exists()) { // If the folder inputs/ does not exists.
			if (!dir.mkdirs()) {
				throw new Exception("The folder " + inputFold + " does not exist and cannot be created.");
			}
			System.out.println("Folder " + inputFold + " has been created.");
		}

		if (!dirOut.exists()) { // If the folder outputs/ does not exists.
			if (!dirOut.mkdirs())
				throw new Exception("The folder " + outputFold + " does not exist and cannot be created.");
			System.out.println("Folder " + outputFold + " has been created.");
		}

		File input = new File("inputs/inputCloSpan-" + path); // File create to
																// store input
																// data ( raw
																// data formated
																// )
		FileWriter fw = new FileWriter(input); // Initialize

		Worker worker = new Worker(bib);

		for (Integer i : map.keySet()) {

			List<Set<Integer>> s1 = worker.sequencesToID(map.get(i).getFirst()); // All
																					// the
																					// actions
																					// for
																					// player
																					// 1
			List<Set<Integer>> s2 = worker.sequencesToID(map.get(i).getSecond()); // All
																					// the
																					// actions
																					// for
																					// player
																					// 2

			fw.write(formatCloSpan(s1)); // We write the list of sequences of
											// actions in the inputfile
			fw.write(formatCloSpan(s2));

		}

		fw.close(); // Close the FileWriter

	}

	/**
	 * Turn the output file into a data structure
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public List<int[]> fromOutput(String filename) throws Exception {
		List<int[]> res = new ArrayList<int[]>();

		File f = new File(filename);
		Scanner sc = new Scanner(f);

		while (sc.hasNextLine()) {
			res.add(formatOut(sc.nextLine()));
		}

		sc.close();
		return res;
	}

	/**
	 * Turn an output string into an array of integer concat with it's support
	 * 
	 * @param line
	 *            The line to format into an array
	 * @return res An Array of Integer where the elements are cards ID and the
	 *         last element is the support
	 */
	private int[] formatOut(String line) {
		String pars[] = line.split("#SUP:");
		int sup = Integer.parseInt(pars[1].substring(1));
		String cis[] = pars[0].split(" ");
		int res[] = new int[cis.length + 1];

		for (int i = 0; i < cis.length; i++) {
			res[i] = Integer.parseInt(cis[i]);
		}

		res[res.length - 1] = sup;

		return res;
	}

	/**
	 * Format from SortedSet to String ready to input in Charm model
	 * 
	 * @return the result String
	 */
	private String formatCharm(Set<Integer> set) {
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
	 * Format the list of actions in a String make for CloSpan input file
	 * 
	 * @param ls
	 *            The list to format in a String
	 * @return res The String formated
	 */
	private String formatCloSpan(List<Set<Integer>> ls) {
		String res = "";

		for (Set<Integer> s : ls) {

			Iterator<Integer> it = s.iterator();
			while (it.hasNext()) {
				res += it.next();
				if (it.hasNext())
					res += " ";
				else
					res += " -1 ";
			}
		}
		res += "-2\n";
		return res;
	}

	/**
	 * Select the file name in a path and change rawInput or rawOutput according
	 * to the file path
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

	public BiblioDeck getBib() {
		return bib;
	}

}
