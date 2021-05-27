package entities;

public class Train {
    String destination;
    String name;

    public Train(String destination, String name) {
        super();
        this.destination = destination;
        this.name = name;
    }

    String getDestination() {
        return destination;
    }
}
