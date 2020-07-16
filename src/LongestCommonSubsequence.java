import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import acm.program.ConsoleProgram;

public class LongestCommonSubsequence extends ConsoleProgram {
	private List<String> contentFile1 = new ArrayList<String>();
	private List<String> contentFile2 = new ArrayList<String>();

	public void run() {
		setSize(600, 500);

		File[] files = { new File("files/Jackie.cpp"), new File("files/Jesus.cpp") };

		readFileSaveToList(files[0], contentFile1);
		readFileSaveToList(files[1], contentFile2);

		String one = "";
		String two = "";

		for (int i = 0; i < contentFile1.size(); i++) {
			one += contentFile1.get(i);
		}
		for (int i = 0; i < contentFile2.size(); i++) {
			two += contentFile2.get(i);
		}
		println(lcs(one, two));
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

}