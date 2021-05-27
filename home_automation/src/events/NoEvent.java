package events;

class NoEvent extends Event {

    NoEvent() {
        super(EventType.NONE);
    }

    @Override
    public String toString() {
        return "NoEvent{}";
    }
}


