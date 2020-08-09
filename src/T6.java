import java.awt.BorderLayout;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.Program;

public class T6 extends Program {
	private File dir = new File("files");
	private File[] files = dir.listFiles();
	private JComboBox filePicker1;
	private JComboBox filePicker2;

	private JPanel panel = new JPanel();
	private JTextArea area1 = new JTextArea(39, 36);
	private JTextArea area2 = new JTextArea(39, 36);

	private JScrollPane pane1 = new JScrollPane(area1);
	private JScrollPane pane2 = new JScrollPane(area2);

	public void init() {
		setSize(900, 700);
		setupFilePickerComboBoxen();
		setupTextAreas();

	}

	private void setupTextAreas() {
		add(panel, CENTER);
		panel.add(pane1);
		panel.add(pane2);
		

	}

	private void setupFilePickerComboBoxen() {
		filePicker1 = new JComboBox(files);
		filePicker2 = new JComboBox(files);
		// Quelle für ItemListeners: https://www.youtube.com/watch?v=lPJFg7BnVHk
		// if a new file gets selected via comboBox, the content of this file will be
		// displayed in the text Area
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
		add(filePicker1, NORTH);
		add(filePicker2, NORTH);

	}
	// Opens a file, saves it in a String var and displays it on a JTextArea
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
