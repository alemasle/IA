package main;

import java.util.Scanner;

import DataWorker.Format;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("Please, enter the path to the raw data file: (Examples in \"data/\")\n");

		String in = sc.nextLine();
		sc.close();

		Format f = new Format();

		f.toInput(in); // Function to format raw data into formated input file for SPMF

		System.out.println("\nYour formated input file is in the \"inputs/\" folder.");

		// Function to use SPMF TODO

		// Function to read the output TODO

	}

}
