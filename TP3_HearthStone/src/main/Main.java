package main;

import java.util.*;

import dataWorker.Format;
import dataWorker.Worker;
import parsers.BiblioDeck;

public class Main {

	/**
	 * Execute the commands given in parameter
	 * 
	 * @param cmd
	 * @throws Exception
	 */
	private static void execCmd(String cmd) throws Exception {
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);
			p.waitFor();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Command failed.");
		}
	}

	/**
	 * Turn a List of array of int into a String in order to print it.
	 * 
	 * @param sol
	 * @return print The String to prompt
	 */
	private static String print(List<int[]> sol) {
		String print = "{";
		for (int a = 0; a < sol.size(); a++) {
			print += " [";
			int[] tab = sol.get(a);
			for (int i = 0; i < tab.length; i++) {
				if (i == tab.length - 1)
					print += tab[i];
				else
					print += tab[i] + " ";
			}
			if (a == sol.size() - 1)
				print += "] }";
			else
				print += "] ";
		}
		return print;
	}

	private static String printCards(List<int[]> li, BiblioDeck bib) {
		String res = "";
		Worker w = new Worker(bib);

		for (int[] i : li) {
			List<Integer> tmp = new ArrayList<>();
			for (int e = 0; e < i.length - 1; e++) {
				tmp.add(i[e]);
			}

			List<String> r = w.idToDeck(tmp);

			res += "[ " + listP(r) + " ] ";

		}

		return res;
	}

	private static String listP(List<String> l) {
		String res = "";
		for (int i = 0; i < l.size(); i++) {
			if (i == l.size() - 1)
				res += l.get(i);
			else
				res += l.get(i) + " ";
		}

		return res;
	}

	/**
	 * Represent the first part of the practice lab.
	 * 
	 * @throws Exception
	 */
	private static void partieA() throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("Charm Mode:");
		System.out.println("Please, enter the path to the raw data file: (Examples in \"data/\")\n");

		String in = sc.nextLine();

		Format f = new Format();

		f.toCharm(in); // Function to format raw data into formated input file for SPMF

		System.out.println("\nYour formated input file is in the \"inputs/\" folder.");
		System.out.println("\nEnter a threshold (pourcentage) :\n"); // absolute+.txt a 412 transactions donc 15% de 412
																		// = 61,9 donc l'output n'affichera que les
																		// supports
																		// >= 62 ( dans ce cas ci il y'a 9 itemsets
																		// frequent
																		// fermes correspondant )
		String p100 = sc.nextLine();

		// Function to use SPMF
		String cmd = "java -jar spmf.jar run Charm_bitset inputs/input-" + f.getRawFile() + " outputs/output-"
				+ f.getRawFile() + " " + p100 + "%";

		System.out.println("\nRawData file : " + f.getRawFile() + "\nInput file : inputs/input-" + f.getRawFile()
				+ "\nOutput file : outputs/outputCharm-" + f.getRawFile() + "\nThreshold : " + p100 + "%\n");

		execCmd(cmd);
		System.out.println("Output file has been stored in \"outputs/\"\n");
		// Function to read the output

		List<int[]> lInt = f.fromOutput("outputs/output-" + f.getRawFile());

		System.out.println(print(lInt));
		System.out.println(printCards(lInt, f.getBib()));

		sc.close();

	}

	/**
	 * Represent the second part of the practice lab. (Bonus)
	 * 
	 * @throws Exception
	 */
	public static void partieB() throws Exception {

		// format data
		Scanner sc = new Scanner(System.in);

		System.out.println("CloSpan Mode:");
		System.out.println("Please, enter the path to the raw data file: (Examples in \"data/\")\n");

		String in = sc.nextLine();

		Format f = new Format();

		f.toCloSpan(in); // Function to format raw data into formated input file for SPMF
		
		System.out.println("Done");

		// Use cmd

		// Read results

	}

	public static void main(String[] args) throws Exception {

		// partieA();

		partieB(); // Bonus

	}

}
