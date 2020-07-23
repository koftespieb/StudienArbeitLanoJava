import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

public class Renaming extends ConsoleProgram {

	private List<ArrayList<String>> programme = new ArrayList<ArrayList<String>>();
	private File f = new File("DateiK");
	private File[] fA = f.listFiles();

	public void run() {
		setSize(1600, 800);
		setFont("Times New Roman-bold-18");

		File f = new File("DateiK");
		File[] fA = f.listFiles();

		// Q5: erzeugt ArrayList<String> für jedes file in fA
		for (int i = 0; i < fA.length; i++) {
			programme.add(new ArrayList<String>());
			// reads file safed in files[] and safes it to programme

			inListeSpeichern(fA[i], programme.get(i));
		}

		tabelle();

	}

	// liest programme aus files aus und speichert diese in einer Liste
	public void inListeSpeichern(File file, List<String> list) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				list.add(line);
			}

		} catch (IOException e) {
			println("File not found!");
			e.printStackTrace();
		}
	}

	/*
	 * Quelle: Nico Mayer
	 *
	 *
	 * Tabelle erstellen
	 */
	private void tabelle() {
		// obere Reihe der Tabelle
		for (int i = 0; i < fA.length; i++) {
			if (i == 0) {
				print("\t\t|" + padString(fA[i].getName()) + "|"); // schreibt Namen von Dateien in oberster Reihe
			} else {
				print("\t|" + padString(fA[i].getName()) + "|");
			}
		}
		println();
		// oberste Reihe wird unterstrichen
		for (int i = 0; i < 100; i++) {
			print("--");
		}
		println();
		// die Werte werden jetzt eingesetzt

		for (int i = 0; i < fA.length; i++) {
			for (int j = 0; j <= fA.length; j++) {
				if (j == 0 && i == 0) {
					print(padString(fA[i].getName()) + "\t|");
				} else if (j == 0 && i != 0) {
					print(padString(fA[i].getName()) + "\t|");
				} else {
					print("  " + lcs(programme.get(i).toString(), programme.get(j - 1).toString()) + "\t\t");
				}
			}
			println(); // new line
		}
	}

	// lcs Algorithmus aus der aufgabenstellung
	public int lcs(String s1, String s2) {
		String r1 = prepareCode(s1);
		String r2 = prepareCode(s2);
		char[] X = s1.toCharArray();
		char[] Y = s2.toCharArray();
		int m = X.length;
		int n = Y.length;

		int L[][] = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					L[i][j] = 0;
				} else if (X[i - 1] == Y[j - 1]) {
					L[i][j] = L[i - 1][j - 1] + 1;
				} else {
					L[i][j] = max(L[i - 1][j], L[i][j - 1]);
				}
			}
		}
		double prozente = ((double) L[m][n]) / (double) min(s1.length(), s2.length());
		return (int) (prozente * 100);

	}

	/*
	 * Padds or Cuts a String with spaces until it reaches length Quelle: Nico Mayer
	 */

	public String padString(String word) {
		final int L = 11;
		if (word.length() < L) {
			while (word.length() < L) {
				word += " ";
			}
			return (word);
		} else {
			word = word.substring(0, L);
			return word;
		}

	}

	// returns the bigger value of two ints
	private int max(int i, int j) {
		if (i > j)
			return i;
		else
			return j;
	}

	// returns the smaller value of two ints
	private int min(int i, int j) {
		if (i < j)
			return i;
		else
			return j;
	}

	private final String SEPARATION_CHARS = " \n+-*/=<>.;[](){}";

	private String prepareCode(String s1) {
		StringTokenizer st = new StringTokenizer(s1, SEPARATION_CHARS, true);
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
			String toki = st.nextToken();
			if (SEPARATION_CHARS.contains(toki)) {
				sb.append(toki);
			} else {
				sb.append("_");
			}
		}
		return sb.toString();
	}

}
