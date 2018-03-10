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
	// can be the rawData file name or the output file name
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

			File input = new File("inputs/input-" + path); // File create to store input data ( raw data formated )
			FileWriter fw = new FileWriter(input); // Initialize

			Worker worker = new Worker(bib);

			for (Integer i : map.keySet()) {

				Set<Integer> s1 = worker.deckToID(map.get(i).getFirst());
				Set<Integer> s2 = worker.deckToID(map.get(i).getSecond());

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
	 * @return res An Array of Integer where the elements are cards ID and the last
	 *         element is the support
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
	 * Format frome SortedSet to String ready to input
	 * 
	 * @return the result String
	 */
	private String format(Set<Integer> set) {
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

	public BiblioDeck getBib() {
		return bib;
	}

}
