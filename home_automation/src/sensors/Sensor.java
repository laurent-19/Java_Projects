package sensors;

import events.Event;

public abstract class Sensor {
    public abstract int readValue();

    public abstract <T> Event generateEvent();
}
