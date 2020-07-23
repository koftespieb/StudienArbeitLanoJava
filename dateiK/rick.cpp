import acm.program.ConsoleProgram;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.*;



public class Renaming extends ConsoleProgram {
	
	private ArrayList<String> fileZero = new ArrayList<String>();
	private ArrayList<String> fileOne = new ArrayList<String>();
	private ArrayList<String> fileTwo = new ArrayList<String>();
	private ArrayList<String> fileThree = new ArrayList<String>();
	private ArrayList<String> fileFour = new ArrayList<String>();
	private ArrayList<String> fileFive = new ArrayList<String>();
	private ArrayList<String> fileSix = new ArrayList<String>();
	private ArrayList<String> fileSeven = new ArrayList<String>();
	private ArrayList<String> fileEight = new ArrayList<String>();
	private ArrayList<ArrayList<String>> allFiles = new ArrayList<ArrayList<String>>();
	
	private final String SEPARATION_CHARS = " \n+-*/=<>.;[](){}";

	FileReader fr;
	BufferedReader br;

	File dir = new File("files");
	File[] files = dir.listFiles(); //

	public void run() {

		listFiles();
		readFiles();
		compareFiles();

	}
//Die Datei an der Stelle i wird geöffnet und der Inhalt gelesen und ausgegeben

	private void readFiles() { // Buffered Reader: Quelle: Variationen zum Thema Java von Ralph Lano

		try {
			for (int i = 0; i < files.length; i++) {
				br = new BufferedReader(new FileReader(files[i]));

				while (true) {
					String line = br.readLine();
					// Erstellen von einzelnen ArrayLists mit dem Inhalt der Files nach "i"
					if (i == 0) {
						fileZero.add(line);
					} else if (i == 1) {
						fileOne.add(line);
					} else if (i == 2) {
						fileTwo.add(line);
					} else if (i == 3) {
						fileThree.add(line);
					} else if (i == 4) {
						fileFour.add(line);
					} else if (i == 5) {
						fileFive.add(line);
					} else if (i == 6) {
						fileSix.add(line);
					} else if (i == 7) {
						fileSeven.add(line);
					}

					if (line == null)
						break;
				}
			}

			br.close();
		} catch (IOException e) {
			println("file not found");
		}

	}

	// Hier werden die Namen der files mit .getName() ausgelesen und mit println in
	// der Console ausgegeben.

	private void listFiles() { // https://www.geeksforgeeks.org/file-getname-method-in-java-with-examples/

		if (files != null) {

			for (int i = 0; i < files.length; i++) {
				println(i + ": " + files[i].getName());
			}

		}
	}


//vergleicht zwei Werte aus einer Liste mit ArrayLists
	// wenn i nicht mehr 0 ist dann muss der vergleich anders stattfinden
	private void compareFiles() {
		arrayListToList();
		/*
		 * for (int i = 0; i < allFiles.size(); i++) { for(int j = 0; j <
		 * allFiles.size(); j++) { if ((i +1) < allFiles.size() && j+1 <
		 * allFiles.size()) { // Quelle: Nico Mayer if(i == 0 && j == 0 || j!= 0 && i ==
		 * 0 ) { println("i= "+i+" "+lcs(allFiles.get(i).toString(),
		 * allFiles.get(j+1).toString())); }else if (j!= 0 && i!= 0 ) {
		 * 
		 * 
		 * 
		 * 
		 * } } } }
		 */
		for (int i = 0; i < files.length; i++) {
			for (int j = 0; j < files.length; j++) {
				// print(" i= "+i+" j= "+j);
				
				print(" -" + lcs(allFiles.get(i).toString(), allFiles.get(j).toString()) + "- ");
			}
			println(); // zeilenumbruch
		}
	}

//fügt die ArrayLists in eine Main ArrayList rein 
	private void arrayListToList() {

		allFiles.add(fileZero);
		allFiles.add(fileOne);
		allFiles.add(fileTwo);
		allFiles.add(fileThree);
		allFiles.add(fileFour);
		allFiles.add(fileFive);
		allFiles.add(fileSix);
		allFiles.add(fileSeven);
		allFiles.add(fileEight);

	}
	
	public int lcs(String s, String ss) {
		String s1 = prepareCode(s);
		String s2 = prepareCode(ss);
		char[] X = s1.toCharArray();
		char[] Y = s2.toCharArray();
		int m = X.length;
		int n = Y.length;
		int L[][] = new int[m + 1][n + 1];
		/*
		 * Following steps build L[m+1][n+1] in bottom up fashion. Note that * L[i][j]
		 * contains length of LCS of X[0..i-1] and Y[0..j-1]
		 */
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {

					L[i][j] = 0;
				}

				else if (X[i - 1] == Y[j - 1]) {

					L[i][j] = L[i - 1][j - 1] + 1;
				}

				else {
					L[i][j] = max(L[i - 1][j], L[i][j - 1]);

				}

			}
		}

		double value = (L[m][n]) / (min(s1.length(),s2.length()));
		
		return (int) (value *100);
	}


	// maximum von zwei Integer Werten
	private int max(int x, int y) {

		if (x > y) {
			return x;
		}
		return y;

	}

	// Nico mayer
	private double min(int x, int y) {

		if (x < y) {
			return x;
		}
		return y;
	}
	
	private String prepareCode(String s1) {
		

		
		StringTokenizer st = new StringTokenizer(s1, SEPARATION_CHARS, true); 
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
		String toki = st.nextToken();
		if (SEPARATION_CHARS.contains(toki)) {
		sb.append(toki); } else {
		sb.append("_"); }
		}
		return sb.toString(); }
	
	
}
	
