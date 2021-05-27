package entities;

public class Counter extends Thread {

    private int counter;
    protected boolean paused;

    public Counter() {
        counter = 0;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {

        while (true) {
            while (paused) {
                pause(); // keep pausing 10 ms at a time until button is pressed and paused = false
            }
            synchronized (this) {
                this.notify(); // notify and keep counting
                increaseCounter();
            }
        }
    }


    private void pause() {
        synchronized (this) {
            try {
                this.wait(10); // small amount of time, so it's unobservable and stopping pausing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void increaseCounter() {

        try {
            Counter.sleep(1000); // wait 1 sec to simulate chronometer
            counter++;
            System.out.println(counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
