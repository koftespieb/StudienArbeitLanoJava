import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/* Quellenangaben
 * public String padString und private void output: zum Formatieren der "Tabelle". Hilfe beim Code von Nico Mayer bekommen.
 * public int lcs: Code aus PDF "Studienarbeit" von Herrn Lano
 * private int groeßterInt und private int kleinsterInt: von https://www.geeksforgeeks.org/java-program-for-longest-common-subsequence/
 * 
 */

public class LongestCommonSubsequence extends ConsoleProgram {
	List<ArrayList<String>> inhalt = new ArrayList<ArrayList<String>>();
	private final int X_ACHSE = 1920;
	private final int Y_ACHSE = 1080;

	public void run() {
		setFont("Consolas-14");
		setSize(X_ACHSE, Y_ACHSE);
		File dir = new File("C:\\Users\\Daniel\\eclipse-workspace\\Studienarbeit\\files");
		File[] data = dir.listFiles();

		// wandelt jede Datei in dem Verzeichnis in einen String um und fügt ihn in
		// einen Array
		for (int i = 0; i < data.length; i++) {
			inhalt.add(new ArrayList<String>());

			// liest den Inhalt des Arrays "inhalt" aus und speichert ihn
			readFileSaveToList(data[i], inhalt.get(i));
		}
		output(data);
	}

	// for schleife für die oberste Zeile
	private void output(File[] dateien) {
		for (int i = 0; i < dateien.length; i++) {
			if (i == 0) {
				print("\t\t|" + padString(dateien[i].getName()) + "|");
			} else {
				print("\t|" + padString(dateien[i].getName()) + "|");
			}
		}
		println();

		// for schleife für unterstreichung der obersten Zeile
		for (int i = 0; i < 200; i++) {
			print("_");
		}
		println();

		// for schleife um Daten in Tabelle auszugeben
		for (int i = 0; i < dateien.length; i++) {
			for (int j = 0; j <= dateien.length; j++) {
				if (j == 0 && i == 0) {
					print(padString(dateien[i].getName()) + "\t|");
				} else if (j == 0 && i != 0) {
					print(padString(dateien[i].getName()) + "\t|");
				} else {
					print("  " + lcs(inhalt.get(i).toString(), inhalt.get(j - 1).toString()) + "\t\t");
				}
			}
			println();
		}
	}

	// Code aus PDF "Studienarbeit" von Herrn Lano
	public int lcs(String s1, String s2) {
		char[] X = s1.toCharArray();
		char[] Y = s2.toCharArray();
		String r1 = prepareCode(s1);
		String r2 = prepareCode(s1);
		int m = X.length;
		int n = Y.length;
		int L[][] = new int[m + 1][n + 1];
		/*
		 * Following steps build L[m+1][n+1] in bottom up fashion. Note that L[i][j]
		 * contains length of LCS of X[0..i-1] and Y[0..j-1]
		 */
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					L[i][j] = 0;
				} else if (X[i - 1] == Y[j - 1]) {
					L[i][j] = L[i - 1][j - 1] + 1;
				} else {
					L[i][j] = groeßterInt(L[i - 1][j], L[i][j - 1]);
				}
			}
		}
		// Übereinstimmung in Prozent wird ermittelt.
		double uebereinstimmung = ((double) L[m][n]) / (double) kleinerInt(s1.length(), s2.length());
		return (int) (uebereinstimmung * 100);
	}

	// Quelle:
	// https://www.geeksforgeeks.org/java-program-for-longest-common-subsequence/
	// ermittlung des größeren int
	private int groeßterInt(int i, int j) {
		return (i > j) ? i : j;
	}

	// Quelle:
	// https://www.geeksforgeeks.org/java-program-for-longest-common-subsequence/
	// ermittlung des kleineren int
	private int kleinerInt(int i, int j) {
		return (i < j) ? i : j;
	}

	/*
	 * Funktioniert noch nicht richtig Versuch die beiden int "kleinerInt" und
	 * "groeßterInt" zusammenzufassen
	 *
	 * private int groesserKleiner(int i, int j) { if (i > j) { return i; } else {
	 * return j; } };
	 */

	// Quelle: Variationen zum Thema Algorithmen von Herrn Prof. Ralph P. Lano
	// liest die Datei aus und speichert es in einer list
	public void readFileSaveToList(File file, List<String> list) {
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
			println("404 - File not found!");
			e.printStackTrace();
		}
	}

	// Länge einer "Zelle" - damit die Tabelle optisch schöner aussieht.
	// Eine "Zelle" in der Tabelle ist 15 Zeichen lang
	public String padString(String zelle) {
		final int L = 15;
		if (zelle.length() < L) {
			while (zelle.length() < L) {
				zelle += " ";
			}
			return (zelle);
		} else {
			zelle = zelle.substring(0, L);
			return zelle;
		}
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
