package sensors;

import events.Event;
import events.FireEvent;

import java.util.Random;

public class FireSensor extends Sensor {


    @Override
    public int readValue() {
        Random value = new Random();
        return value.nextInt(100);
    }

    @Override
    public Event generateEvent() {
        return new FireEvent(this.readValue() > 80);
    }
}

