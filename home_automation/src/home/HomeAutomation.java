package home;

import events.Event;

import java.io.IOException;
import java.util.ArrayList;

public class HomeAutomation {

    public static void main(String[] args) {

        //test using an anonymous inner class
        Home h = new Home() {
            protected void setValueInEnvironment(ArrayList<Event> events) {
                System.out.println("The list of new events in the environment which happened : " + events.toString());
            }

            protected void controlStep() {
                System.out.println("Control step executed\n");
            }
        };
        try {
            h.simulate();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}


