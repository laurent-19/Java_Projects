package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class XOGame extends JFrame implements ActionListener {
    private final JButton[] buttons = new JButton[9];
    private final Integer[] score = new Integer[9];
    private int count = 0;

    public XOGame() {
        setTitle("X and 0 game --- Play!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setVisible(true);
    }

    private void init() {
        count = 0;
        for (int i = 0; i < 9; ++i) {
            buttons[i] = new JButton("");
            buttons[i].addActionListener(this);
            add(buttons[i]);
            score[i] = 0;
        }
    }

    public void actionPerformed(ActionEvent e) {
        String marker;
        ++count;
        if (count % 2 == 1) {
            marker = "X";
        } else {
            marker = "O";
        }

        for (int j = 0; j < buttons.length; j++)
            if (e.getSource() == buttons[j]) {
                buttons[j].setText(marker);

                if (marker.equals("X")) {
                    buttons[j].setBackground(Color.green);
                    score[j] = 1;
                } else {
                    buttons[j].setBackground(Color.red);
                    score[j] = 2;
                }
                buttons[j].setEnabled(false);
            }

        if (count == 9) { // no combination
            System.out.println("No winner");
            reset();
        } else if (score[0] != 0 && score[0].equals(score[4]) && score[4].equals(score[8])) { // diagonally
            System.out.println(buttons[0].getText() + " won");
            reset();
        } else if (score[2] != 0 && score[2].equals(score[4]) && score[4].equals(score[6])) { // diagonally
            System.out.println(buttons[2].getText() + " won");
            reset();
        } else if (score[0] != 0 && score[0].equals(score[1]) && score[1].equals(score[2])) { // horizontal
            System.out.println(buttons[0].getText() + " won");
            reset();
        } else if (score[3] != 0 && score[3].equals(score[4]) && score[4].equals(score[5])) { // horizontal
            System.out.println(buttons[3].getText() + " won");
            reset();
        } else if (score[6] != 0 && score[6].equals(score[7]) && score[7].equals(score[8])) { // horizontal
            System.out.println(buttons[6].getText() + " won");
            reset();
        } else if (score[0] != 0 && score[0].equals(score[3]) && score[3].equals(score[6])) { // vertical
            System.out.println(buttons[0].getText() + " won");
            reset();
        } else if (score[1] != 0 && score[1].equals(score[4]) && score[4].equals(score[7])) { // vertical
            System.out.println(buttons[1].getText() + " won");
            reset();
        } else if (score[2] != 0 && score[2].equals(score[5]) && score[5].equals(score[8])) { // vertical
            System.out.println(buttons[2].getText() + " won");
            reset();
        }
    }

    private void reset() {
        for (JButton button : buttons) {
            remove(button); // remove old buttons are not to create a bigger grid
        }
        init();
    }

    public static void main(String[] args) {
        new XOGame();
    }
}