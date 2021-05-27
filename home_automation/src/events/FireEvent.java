package events;


public class FireEvent extends Event {

    private final boolean smoke;

    public FireEvent(boolean smoke) {
        super(EventType.FIRE);
        this.smoke = smoke;
    }

    public boolean isSmoke() {
        return smoke;
    }

    @Override
    public String toString() {
        return "FireEvent{" + "smoke=" + smoke + '}';
    }

}

