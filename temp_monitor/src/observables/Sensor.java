package observables;

import java.util.Random;

public class Sensor extends Observable {

    public void checkTemp() {

        this.changeState("Checking temperature ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random r = new Random();
        int temperature = r.nextInt(40);
        this.changeState("Temperature has changed!" + "\nReceived Temperature: " + temperature);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


