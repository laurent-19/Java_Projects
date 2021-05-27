package units;

import events.TemperatureEvent;
import sensors.TemperatureSensor;

import java.io.FileWriter;
import java.io.IOException;

public class HeatingUnit {
    private final TemperatureSensor temperatureSensor;

    public HeatingUnit(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    public TemperatureEvent checkTemperature(int minTempValue) throws IOException {
        TemperatureEvent newTempEvent = temperatureSensor.generateEvent();
        if (newTempEvent.getValue() < minTempValue) {
            System.out.println(newTempEvent.toString() + "\nStarting to heat the house!");
            String filePath = "C:\\Development\\Java Projects\\home_automation\\src\\home\\system_logs.txt";
            write(filePath,newTempEvent.toString() + "\nStarting to heat the house!");
        }
        return newTempEvent;
    }
    private static void write(String filePath, String msg) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(msg);
        writer.close();
    }
}
