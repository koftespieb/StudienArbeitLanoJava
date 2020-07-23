import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import acm.program.ConsoleProgram;

public class LCS extends ConsoleProgram {

	List<ArrayList<String>> programme = new ArrayList<ArrayList<String>>();

	public void run() {
		setSize(1600, 800);
		setFont("Times New Roman-bold-18");

		File f = new File("files");
		File[] fA = f.listFiles();

		// Q5: erzeugt ArrayList<String> für jedes file in fA
		for (int i = 0; i < fA.length; i++) {
			programme.add(new ArrayList<String>());
			// reads file safed in files[] and safes it to programme

			inListeSpeichern(fA[i], programme.get(i));
		}

		tabelle(fA);

	}

	/*
	 * Quelle: Nico Mayer
	 *
	 *
	 * Tabelle erstellen
	 */
	private void tabelle(File[] fA) {
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

	// reads programme From file and saves it to an ArrayList
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

	// returns the accordance of two strings in percent
	public int lcs(String s1, String s2) {
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
}
