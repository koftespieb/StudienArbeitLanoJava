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

public class T7 extends GraphicsProgram {
	private static final double BLOCK_SIZE = 0.06;
	private final String SEPERATION_CHARS = " \n+-*/=<>.;[](){}";
	List<ArrayList<String>> content = new ArrayList<ArrayList<String>>();

	public void run() {
		setSize(800, 1000);
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
			int x = 15;
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
		int lengthOfSameChars = 15;
		// - lengthOfSameChars to prevent index out of bounds 
		for (int i = 0; i < s1.length() - lengthOfSameChars; i++) { 
			for (int j = 0; j < s2.length() - lengthOfSameChars; j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					// test after a similar char was found if the following 15 chars are also the same
					for (int test = 0; test <= lengthOfSameChars; test++) {
						if (s1.charAt(i + test) == s2.charAt(j + test)) {
							count++;
						}
					}
					if (count == lengthOfSameChars) {
						for (int j2 = 0; j2 < lengthOfSameChars; j2++) {
							GRect pixel = new GRect(BLOCK_SIZE, BLOCK_SIZE);
							pixel.setFilled(true);
							pixel.setColor(Color.DARK_GRAY);
							add(pixel, (i-j2) * BLOCK_SIZE + x_off, (j-j2) * BLOCK_SIZE + y_off);
							count = 0;
						}
						
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
