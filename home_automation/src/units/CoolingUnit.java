package units;

import events.TemperatureEvent;
import sensors.TemperatureSensor;

import java.io.FileWriter;
import java.io.IOException;

public class CoolingUnit {
    private final TemperatureSensor temperatureSensor;

    public CoolingUnit(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    public TemperatureEvent checkTemperature(int maxTempValue) throws IOException {
        TemperatureEvent newTempEvent = temperatureSensor.generateEvent();
        if (newTempEvent.getValue() > maxTempValue) {
            System.out.println(newTempEvent.toString() + "\nStarting cooling the house!");
            String filePath = "C:\\Development\\Java Projects\\home_automation\\src\\home\\system_logs.txt";
            write(filePath,newTempEvent.toString() + "\nStarting cooling the house!");
        }
        return newTempEvent;
    }
    private static void write(String filePath, String msg) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(msg);
        writer.close();
    }
}

