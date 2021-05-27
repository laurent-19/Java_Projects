package controller;

import events.Event;
import units.AlarmUnit;
import units.CoolingUnit;
import units.HeatingUnit;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private static volatile Controller instance = null;

    private Controller() {
    }

    public ArrayList<Event> control(AlarmUnit alarmUnit, HeatingUnit heatingUnit, CoolingUnit coolingUnit) throws IOException {
        ArrayList<Event> events = new ArrayList<>();
        events.addAll(alarmUnit.fireAlarm());
        int minTempValue = 18;
        int maxTempValue = 30;
        events.add(heatingUnit.checkTemperature(minTempValue));
        events.add(coolingUnit.checkTemperature(maxTempValue));
        return events;
    }

    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) {
                instance = new Controller();
            }
        }

        return instance;
    }
}
