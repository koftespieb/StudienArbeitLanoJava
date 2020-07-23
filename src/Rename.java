import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

public class Rename extends ConsoleProgram {
	private final int X_SIZE = 1460;
	private final int Y_SIZE = 600;
	private final String SEPERATION_CHARS = " \n+-*/=<>.;[](){}";
	List<ArrayList<String>> content = new ArrayList<ArrayList<String>>();

	public void run() {
		setSize(X_SIZE, Y_SIZE);
		// stürzt ab bei falscher namenseingabe
		File dir = new File(readLine("Enter directory to scan (e.g. files): "));
		File[] files = dir.listFiles();
		
		// creates a ArrayList<String> for every file in dir
		for (int i = 0; i < files.length; i++) {
			content.add(new ArrayList<String>());
			// reads file safed in files[] and safes it to content
			readFileSaveToList(files[i], content.get(i));
		}

		output(files);

	}

	private void output(File[] files) {
		// for loop to get the top row of the tabble
		for (int i = 0; i < files.length; i++) {
			if (i == 0) {
				print("\t\t|" + padString(files[i].getName()) + "|");
			} else {
				print("\t|" + padString(files[i].getName()) + "|");
			}
		}
		println();
		// for loop to get first row underlined
		for (int i = 0; i < 100; i++) {
			print("--");
		}
		println(); // new line
		// for loop to draw table with values
		for (int i = 0; i < files.length; i++) {
			for (int j = 0; j <= files.length; j++) {
				if (j == 0 && i == 0) {
					print(padString(files[i].getName()) + "\t|");
				} else if (j == 0 && i != 0) {
					print(padString(files[i].getName()) + "\t|");
				} else {
					print("  " + lcs(content.get(i).toString(), content.get(j - 1).toString()) + "\t\t");
				}
			}
			println(); // new line
		}
	}

	// returns the accordance of two strings in percent
	public int lcs(String inpud1, String inpud2) {
		String s1 = prepareCode(inpud1);
		String s2 = prepareCode(inpud2);
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
		double percentage = ((double) L[m][n]) / (double) min(s1.length(), s2.length());
		return (int) (percentage * 100);

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

	// reads Content From file and saves it to an ArrayList
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
			println("File not found!");
			e.printStackTrace();
		}
	}

	// Padds or Cuts a String with spaces until it reaches length L
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
	// Quelle Aufgabenblatt
	private String prepareCode(String s) {
		StringTokenizer toki = new StringTokenizer(s,SEPERATION_CHARS,true);
		StringBuffer sb = new StringBuffer();
		while(toki.hasMoreTokens()) {
			String token = toki.nextToken();
			if(SEPERATION_CHARS.contains(token)) {
				sb.append(token);
			}else {
				sb.append("_");
			}
			
		}
		return sb.toString();
	}
}
