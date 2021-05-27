package entities;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    String stationName;

    ArrayList<Controller> neighbourControllers = new ArrayList<>();

    //storing station train track segments
    ArrayList<Segment> list = new ArrayList<>();

    public Controller(String stationName) {
        this.stationName = stationName;
    }

    public void setNeighbourController(Controller v) {
        neighbourControllers.add(v);
    }

    public void addControlledSegment(Segment s) {
        this.list.removeIf(segment -> segment.id == s.id);
        list.add(s);
    }

    public int getFreeSegmentId() {
        for (Segment s : list) {
            if (!s.hasTrain())
                return s.id;
        }
        return -1;
    }

    void controlStep() {
        //check which train must be sent
        for (Segment segment : list) {
            if (segment.hasTrain()) {
                Train t = segment.getTrain();
                for (Controller neighbourController : neighbourControllers) {
                    if (t.getDestination().equals(neighbourController.stationName)) {
                        //check if there is a free segment
                        int id = neighbourController.getFreeSegmentId();
                        if (id == -1) {
                            System.out.println("The train " + t.name + " from station " + stationName + " could not be send to " + neighbourController.stationName + ". No segment available !");
                            return;
                        }
                        //send train
                        System.out.println("The train " + t.name + " leaves the station " + stationName + " heading to " + neighbourController.stationName);
                        segment.departTrain();
                        neighbourController.arriveTrain(t, id);
                    }
                }

            }
        }//.for

    }//.


    public void arriveTrain(Train t, int idSegment) {
        for (Segment segment : list) {
            //search id segment and add train on it
            if (segment.id == idSegment)
                if (segment.hasTrain()) {
                    System.out.println("CRASH! Train " + t.name + " collided with " + segment.getTrain().name + " on segment " + segment.id + " in station " + stationName);
                    return;
                } else {
                    System.out.println("Train " + t.name + " arrived on segment " + segment.id + " in station " + stationName);
                    segment.arriveTrain(t);
                    return;
                }
        }

        //this should not happen
        System.out.println("Train " + t.name + " cannot be received " + stationName + ". Check controller logic algorithm!");

    }


    public void displayStationState(JTextArea textArea) {
        textArea.setText("");
        textArea.append("=== STATION " + stationName + " ===\n");
        System.out.println("=== STATION " + stationName + " ===");
        for (Segment s : list) {
            if (s.hasTrain()) {
                textArea.append("|----------ID=" + s.id + "__Train=" + s.getTrain().name + " to " + s.getTrain().destination + "__----------|\n");
                System.out.println("|----------ID=" + s.id + "__Train=" + s.getTrain().name + " to " + s.getTrain().destination + "__----------|");
            } else {
                textArea.append("|----------ID=" + s.id + "__Train=______ to ________----------|\n");
                System.out.println("|----------ID=" + s.id + "__Train=______ to ________----------|");
            }

        }
    }

    public String getStationName() {
        return stationName;
    }
}
