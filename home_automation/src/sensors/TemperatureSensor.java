package sensors;

import events.TemperatureEvent;

import java.util.Random;

public class TemperatureSensor extends Sensor {

    @Override
    public int readValue() {
        Random value = new Random();
        return value.nextInt(40);
    }

    @Override
    public TemperatureEvent generateEvent() {
        return new TemperatureEvent(this.readValue());
    }


}
