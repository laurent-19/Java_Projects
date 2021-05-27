package events;

public abstract class Event {

    EventType type;

    Event(EventType type) {
        this.type = type;
    }

    EventType getType() {
        return type;
    }

}

