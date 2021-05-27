package observables;

import observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private final List<Observer> observers = new ArrayList<Observer>();

    public void changeState(Object event) {
        notifyAllObservers(event);
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers(Object event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}



