package simulator;

import entities.Counter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chronometer extends JFrame {

    JLabel lCounter;
    JTextField tCounter;
    JButton bStartPause;
    JButton bReset;
    boolean flagStart = true;
    final Counter counter;

    Chronometer() {

        setTitle("Chronometer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setSize(600, 400);
        setVisible(true);
        this.counter = new Counter();
    }

    public void init() {

        setLayout(null);
        int width = 150;
        int height = 30;

        lCounter = new JLabel("Counter ");
        lCounter.setBounds(200, 50, width, height);

        tCounter = new JTextField();
        tCounter.setBounds(270, 50, width / 4, height);
        tCounter.setEditable(false);

        bStartPause = new JButton("Start/Pause");
        bStartPause.setBounds(90, 120, width, height);

        bReset = new JButton("Reset");
        bReset.setBounds(300, 120, width, height);

        bStartPause.addActionListener(new ImplementStartPauseButton());
        bReset.addActionListener(new ImplementResetButton());

        add(lCounter);
        add(tCounter);
        add(bStartPause);
        add(bReset);
    }

    public static void main(String[] args) {
        Chronometer chronometer = new Chronometer();
        while (true) {
            chronometer.tCounter.setText("   " + chronometer.counter.getCounter()); // keep updating counter
        }

    }

    class ImplementStartPauseButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (flagStart) { // flag used to know is this is the first button push
                counter.start();
                flagStart = false;
            } else { // if counter started, ignore the start flag
                counter.setPaused(!counter.isPaused());
            }
        }
    }


    class ImplementResetButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            counter.setCounter(0); // set counter to 0 when reset pressed
        }
    }

}




