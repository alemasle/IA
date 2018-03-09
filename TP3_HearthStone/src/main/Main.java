package main;

import java.util.Scanner;

import DataWorker.Format;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Please, enter the path to the raw data file:\n");

		String in = sc.nextLine();
		sc.close();

		Format f = new Format();

		f.toInput(in);

		System.out.println("\nYour formated input file is in the inputs/ folder.");

	}

}
