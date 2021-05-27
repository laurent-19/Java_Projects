package main;

import observables.Sensor;
import observers.Observer;

import javax.swing.*;

public class TempMonitor extends JFrame implements Observer {

    JLabel lTemp;
    JTextField tTemp;

    TempMonitor() {
        setTitle("Temp Monitor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setSize(600, 400);
        setVisible(true);
        tTemp.setEditable(false);
    }

    public void init() {

        setLayout(null);
        int width = 150;
        int height = 30;

        lTemp = new JLabel("Monitor: ");
        lTemp.setBounds(20, 50, width, height);

        tTemp = new JTextField();
        tTemp.setBounds(100, 50, width * 2, height);

        add(lTemp);
        add(tTemp);

    }

    @Override
    public void update(Object event) {

        tTemp.setText(event.toString());
    }

    public static void main(String[] args) {
        Sensor tempSensor = new Sensor();
        TempMonitor tempMonitor = new TempMonitor();
        tempSensor.register(tempMonitor);

        while (true) {
            tempSensor.checkTemp();
        }
    }

}


