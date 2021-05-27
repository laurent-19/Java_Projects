package entities;

public class Segment {
    int id;
    Train train;

    public Segment(int id) {
        this.id = id;
    }

    boolean hasTrain() {
        return train != null;
    }

    Train departTrain() {
        Train tmp = train;
        this.train = null;
        return tmp;
    }

    public void arriveTrain(Train t) {
        train = t;
    }

    Train getTrain() {
        return train;
    }
}