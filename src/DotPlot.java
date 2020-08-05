import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import acm.graphics.GRect;
import acm.io.IODialog;
import acm.program.GraphicsProgram;

public class DotPlot extends GraphicsProgram {
	private static final double BLOCK_SIZE = 0.09;
	private final String SEPERATION_CHARS = " \n+-*/=<>.;[](){}";
	List<ArrayList<String>> content = new ArrayList<ArrayList<String>>();

	public void run() {
		setSize(800, 800);
		// stürzt ab bei falscher namenseingabe
		IODialog dialog = new IODialog();
		File dir = new File(dialog.readLine("Enter dir: "));
		File[] files = dir.listFiles();

		// creates a ArrayList<String> for every file in dir
		for (int i = 0; i < files.length; i++) {
			content.add(new ArrayList<String>());
			// reads file safed in files[] and safes it to content
			readFileSaveToList(files[i], content.get(i));
		}
		for (int i = 0; i < files.length; i++) {
			int x = -15;
			int y = 3 + 100 * i;
			for (int j = 0; j < i; j++) {
				String s1 = removeWhiteSpaces(content.get(i).toString());
				String s2 = removeWhiteSpaces(content.get(j).toString());
				showSimilarity(s1, s2, x, y);
				x += 100;

			}
		}

	}

	private void showSimilarity(String s1, String s2, int x_off, int y_off) {
		int count = 0;
		int lengthOfSameChars = 8;
		// - lengthOfSameChars to prevent index out of bounds 
		for (int i = 0; i < s1.length() - lengthOfSameChars; i++) { 
			for (int j = 0; j < s2.length() - lengthOfSameChars; j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					// test after a similar char was found if the following 8 chars are also the same
					for (int test = 0; test <= lengthOfSameChars; test++) {
						if (s1.charAt(i + test) == s2.charAt(j + test)) {
							count++;
						}
					}
					if (count == lengthOfSameChars) {
						GRect pixel = new GRect(BLOCK_SIZE, BLOCK_SIZE);
						pixel.setFilled(false);
						pixel.setColor(Color.DARK_GRAY);
						add(pixel, (i-count) * BLOCK_SIZE + x_off, (j-count) * BLOCK_SIZE + y_off);
						count = 0;
					}
				} else {
					count = 0;
				}
			}
		}

	}

	private String removeWhiteSpaces(String s) {
		s = s.replace(" ", "");
		s = s.replace("\n", "");
		s = s.replace("\t", "");
		return s;
	}

	private String prepareCode(String s) {
		StringTokenizer toki = new StringTokenizer(s, SEPERATION_CHARS, true);
		StringBuffer sb = new StringBuffer();
		while (toki.hasMoreTokens()) {
			String token = toki.nextToken();
			if (SEPERATION_CHARS.contains(token)) {
				sb.append(token);
			} else {
				sb.append("_");
			}

		}
		return sb.toString();
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

	/*
	 * private String findLargestCommonSubstring(String s, String t) { int[][] L =
	 * new int[s.length()][t.length()]; int z = 0; int endIndex = 0; for (int i = 0;
	 * i < s.length(); i++) { for (int j = 0; j < t.length(); j++) { if (s.charAt(i)
	 * == t.charAt(j)) { if (i == 0 || j == 0) { L[i][j] = 1; } else { L[i][j] = L[i
	 * - 1][j - 1] + 1; } if (L[i][j] > z) { z = L[i][j]; endIndex = i; }
	 * 
	 * } else { L[i][j] = 0; } } } if (z > 0) { return s.substring(endIndex - z + 1,
	 * endIndex + 1); } return null; }
	 */
}
