package main;


import entities.Controller;
import entities.Segment;
import entities.Train;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulator extends JFrame {

    JLabel stationLabel;
    JLabel segmentLabel;
    JLabel destinationLabel;
    JTextField textBoxSegment;
    JTextField textBoxStation;
    JTextField textBoxDestination;
    JTextArea textAreaStation1;
    JTextArea textAreaStation2;
    JTextArea textAreaStation3;
    JButton buttonAddSegment;

    //build station Cluj-Napoca
    Controller c1 = new Controller("Cluj-Napoca");

    Segment s1 = new Segment(1);
    Segment s2 = new Segment(2);
    Segment s7 = new Segment(7);


    //build station Bucuresti
    Controller c2 = new Controller("Bucuresti");

    Segment s3 = new Segment(3);
    Segment s4 = new Segment(4);

    //build station Madrid
    Controller c3 = new Controller("Madrid");

    Segment s5 = new Segment(5);
    Segment s6 = new Segment(6);


    Simulator() {

        setTitle("Railway Traffic Simulator ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setSize(900, 700);
        setVisible(true);
    }

    public void init() {

        setLayout(null);
        int width = 200;
        int height = 30;

        stationLabel = new JLabel("Station name: ");
        segmentLabel = new JLabel("Segment ID: ");
        destinationLabel = new JLabel("Destination: ");
        stationLabel.setBounds(20, 150, width, height);
        segmentLabel.setBounds(20, 150 + height + 10, width, height);
        destinationLabel.setBounds(20, 150 + 2 * height + 20, width, height);

        textBoxStation = new JTextField();
        textBoxSegment = new JTextField();
        textBoxDestination = new JTextField();

        textBoxStation.setBounds(150, 150, width, height);
        textBoxSegment.setBounds(150, 150 + height + 10, width, height);
        textBoxDestination.setBounds(150, 150 + 2 * height + 20, width, height);

        buttonAddSegment = new JButton("Press to add segment");
        buttonAddSegment.setBounds(130, 150 + 3 * height + 40, width, height);

        buttonAddSegment.addActionListener(new ImplementAddButton());


        textAreaStation1 = new JTextArea();
        textAreaStation2 = new JTextArea();
        textAreaStation3 = new JTextArea();

        textAreaStation1.setBounds(500, 30, 300, 150);
        textAreaStation2.setBounds(500, 200, 300, 150);
        textAreaStation3.setBounds(500, 370, 300, 150);


        add(segmentLabel);
        add(stationLabel);
        add(destinationLabel);
        add(textBoxStation);
        add(textBoxSegment);
        add(textBoxDestination);
        add(textAreaStation1);
        add(textAreaStation2);
        add(textAreaStation3);
        add(buttonAddSegment);


        // add segments to corresponding station

        c1.addControlledSegment(s1);
        c1.addControlledSegment(s2);
        c1.addControlledSegment(s7);

        c2.addControlledSegment(s3);
        c2.addControlledSegment(s4);

        c3.addControlledSegment(s5);
        c3.addControlledSegment(s6);


        //connect the 3 controllers

        c1.setNeighbourController(c2);
        c1.setNeighbourController(c3);
        c2.setNeighbourController(c1);
        c2.setNeighbourController(c3);
        c3.setNeighbourController(c1);
        c3.setNeighbourController(c2);

        //testing

        Train t1 = new Train("Madrid", "INT-001");
        s1.arriveTrain(t1);

        Train t2 = new Train("Cluj-Napoca", "R-002");
        s4.arriveTrain(t2);

        Train t3 = new Train("Bucuresti", "IC-001");
        s2.arriveTrain(t3);

        Train t4 = new Train("Madrid", "INT-002");
        s3.arriveTrain(t4);

        Train t5 = new Train("Cluj-Napoca", "INT-003");
        s5.arriveTrain(t5);


        c1.displayStationState(this.textAreaStation1);
        c2.displayStationState(this.textAreaStation2);
        c3.displayStationState(this.textAreaStation3);

    }

    public class ImplementAddButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String stationName = Simulator.this.textBoxStation.getText();
            int segmentID = Integer.parseInt(Simulator.this.textBoxSegment.getText());
            String destinationName = Simulator.this.textBoxDestination.getText();
            Train train = new Train(destinationName, "INT -00" + segmentID);
            Segment segment = new Segment(segmentID);
            if (stationName.equals(c1.getStationName())) {
                if (c1.getFreeSegmentId() == segmentID) {
                    c1.addControlledSegment(segment);
                    segment.arriveTrain(train);
                    c1.displayStationState(Simulator.this.textAreaStation1);
                } else {
                    textAreaStation1.append("\n\nSegment occupied!");
                }
            } else if (stationName.equals(c2.getStationName())) {
                if (c2.getFreeSegmentId() == segmentID) {
                    c2.addControlledSegment(segment);
                    segment.arriveTrain(train);
                    c2.displayStationState(Simulator.this.textAreaStation2);
                } else {
                    textAreaStation2.append("\n\nSegment occupied!");
                }

            } else if (stationName.equals(c3.getStationName())) {
                if (c3.getFreeSegmentId() == segmentID) {
                    c3.addControlledSegment(segment);
                    segment.arriveTrain(train);
                    c3.displayStationState(Simulator.this.textAreaStation3);
                } else {
                    textAreaStation3.append("\n\nSegment occupied!");
                }

            } else {
                System.out.println("No station with this name!");
            }

        }
    }

    public static void main(String[] args) {
        new Simulator();

    }
}
