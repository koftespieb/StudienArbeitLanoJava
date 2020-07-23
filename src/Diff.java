import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.Program;

public class Diff extends Program {
	private File dir = new File("files");
	File[] files = dir.listFiles();
	JComboBox filePicker1;
	JComboBox filePicker2;

	JPanel panel = new JPanel();
	JTextArea area1 = new JTextArea(25, 25);
	JTextArea area2 = new JTextArea(25, 25);

	JScrollPane pane1 = new JScrollPane(area1);
	JScrollPane pane2 = new JScrollPane(area2);

	public void init() {
		setSize(700, 550);
		filePicker1 = new JComboBox(files);
		filePicker2 = new JComboBox(files);
		

		// Quelle für ItemListeners: https://www.youtube.com/watch?v=lPJFg7BnVHk
		filePicker1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (filePicker1.getSelectedItem() != "Choose") {
						openFile(filePicker1.getSelectedItem().toString(), area1);
					}
				}

			}
		});
		filePicker2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (filePicker1.getSelectedItem() != "Choose") {
						openFile(filePicker2.getSelectedItem().toString(), area2);
					}
				}

			}
		});

		add(panel, CENTER);
		add(filePicker1, NORTH);
		add(filePicker2, NORTH);
		panel.add(pane1);
		panel.add(pane2);

	}

	private void openFile(String fileName, JTextArea area) {

		try {
			// open file
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			// read from file, line by line
			String text = "";
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				text += line + "\n";
			}
			// close file
			br.close();
			fr.close();
			// show file content
			area.setText(text);
		} catch (Exception e) {
			println("File does not exist!");
		}
	}

}
