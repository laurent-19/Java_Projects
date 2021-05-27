package units;

import events.FireEvent;
import sensors.FireSensor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AlarmUnit {
    private final GsmUnit gsmUnit;
    private final ArrayList<FireSensor> fireSensors;

    public AlarmUnit(GsmUnit gsmUnit, ArrayList<FireSensor> fireSensors) {
        this.gsmUnit = gsmUnit;
        this.fireSensors = fireSensors;
    }

    public ArrayList<FireEvent> fireAlarm() throws IOException {
        ArrayList<FireEvent> fireEvents = new ArrayList<>();
        for (FireSensor fireSensor : fireSensors) {
            FireEvent newFireEvent = (FireEvent) fireSensor.generateEvent();
            fireEvents.add(newFireEvent);
            if (newFireEvent.isSmoke()) {
                String filePath = "C:\\Development\\Java Projects\\home_automation\\src\\home\\system_logs.txt";
                write(filePath,newFireEvent.toString());
                System.out.println(newFireEvent.toString());
                gsmUnit.callOwner();
            }
        }
        return fireEvents;

    }

    private static void write(String filePath, String msg) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(msg);
        writer.close();
    }

}
