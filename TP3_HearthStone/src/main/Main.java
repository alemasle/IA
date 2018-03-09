package main;

import java.util.Scanner;
import DataWorker.Format;

public class Main {

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

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("Please, enter the path to the raw data file: (Examples in \"data/\")\n");

		String in = sc.nextLine();

		Format f = new Format();

		f.toInput(in); // Function to format raw data into formated input file for SPMF

		System.out.println("\nYour formated input file is in the \"inputs/\" folder.");

		System.out.println("\nEnter a pourcentage:\n"); // absolute+.txt a 412 transactions donc 15% de 412 = 61,9 donc
														// l'output n'affichera que les supports >= 62

		// Function to use SPMF TODO
		String cmd = "java -jar spmf.jar run Charm_bitset inputs/input-" + f.getRawFile() + " outputs/output-"
				+ f.getRawFile() + " " + sc.nextLine() + "%";

		System.out.println("Cmd : " + cmd);

		execCmd(cmd);

		sc.close();

		System.out.println("Done");

		// Function to read the output TODO
	}

}
