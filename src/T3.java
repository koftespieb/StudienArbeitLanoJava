import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import acm.program.ConsoleProgram;

public class T3 extends ConsoleProgram {
	private File[] files;
	private File dir;
	private List<String> content = new ArrayList<String>();

	public void run() {
		setSize(600, 500);
		fillFileArray();
		if (files != null) {
			output();
		} else {
			println("Please enter vaild directory path");
		}
	}

	private void output() {
		// Output for File names in the Entered Directory
		for (int i = 0; i < files.length; i++) {
			println(i + ": " + files[i].toString());
			readFileContent(files[i]);
		}
		println("\n...................................................\n");
		// Output for the content of the Files in the Directory
		for (int i = 0; i < content.size(); i++) {
			println(content.get(i));
		}

	}

	// fills the file array with the filenames of the directory Entered
	private void fillFileArray() {
		dir = new File(readLine("Enter directory to scan (e.g. files): "));
		files = dir.listFiles();
	}

	// reads Content From file and saves it to an ArrayList
	public void readFileContent(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				content.add(line);
			}

		} catch (IOException e) {
			println("File not found!");
			e.printStackTrace();
		}
	}
}
