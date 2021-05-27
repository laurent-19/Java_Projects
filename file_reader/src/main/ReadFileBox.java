package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFileBox extends JFrame {

    JLabel fileLabel;
    JTextField textBoxFile;
    JTextArea textArea;
    JButton buttonFile;


    public static void read(String fileName, JTextArea textArea) throws IOException {

        Path filePath = Paths.get("C:\\Development\\Java Projects\\file_reader\\src\\files\\" + fileName);
        if (Files.exists(filePath)) {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                textArea.append("\n" + line);
            }
        } else {
            textArea.append("\nThis file doesn't exist in the folder files!");
        }

    }


    ReadFileBox() {

        setTitle("Read from a file");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setSize(500, 400);
        setVisible(true);
    }

    public void init() {

        setLayout(null);
        int width = 150;
        int height = 30;

        fileLabel = new JLabel("File Name ");
        fileLabel.setBounds(20, 30, width, height);

        textBoxFile = new JTextField();
        textBoxFile.setBounds(90, 30, width, height);

        textArea = new JTextArea();
        textArea.setBounds(90, 90, 300, 150);

        buttonFile = new JButton("Press read the file");
        buttonFile.setBounds(130, 300, width, height);

        buttonFile.addActionListener(new ImplementFileButton());

        add(fileLabel);
        add(textBoxFile);
        add(buttonFile);
        add(textArea);
    }

    public static void main(String[] args) {
        new ReadFileBox();
    }

    class ImplementFileButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String fileName = ReadFileBox.this.textBoxFile.getText();

            try {
                ReadFileBox.this.textArea.setText("");
                ReadFileBox.read(fileName, ReadFileBox.this.textArea);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
