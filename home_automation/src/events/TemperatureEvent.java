package events;


public class TemperatureEvent extends Event {

    private final int value;

    public TemperatureEvent(int value) {
        super(EventType.TEMPERATURE);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TemperatureEvent{" + "value=" + value + '}';
    }

}

