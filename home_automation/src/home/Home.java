package home;

import controller.Controller;
import events.Event;
import sensors.FireSensor;
import sensors.TemperatureSensor;
import units.AlarmUnit;
import units.CoolingUnit;
import units.GsmUnit;
import units.HeatingUnit;

import java.io.IOException;
import java.util.ArrayList;

abstract class Home {
    private final FireSensor kitchenFireSensor = new FireSensor();
    private final FireSensor livingFireSensor = new FireSensor();
    private final FireSensor bedRoomFireSensor = new FireSensor();
    private final ArrayList<FireSensor> fireSensors = new ArrayList<>() {
        {
            add(kitchenFireSensor);
            add(livingFireSensor);
            add(bedRoomFireSensor);
        }
    };
    private final TemperatureSensor temperatureSensor = new TemperatureSensor();
    private final GsmUnit gsmUnit = new GsmUnit();
    private final AlarmUnit alarmUnit = new AlarmUnit(gsmUnit, fireSensors);
    private final HeatingUnit heatingUnit = new HeatingUnit(temperatureSensor);
    private final CoolingUnit coolingUnit = new CoolingUnit(temperatureSensor);

    protected abstract void setValueInEnvironment(ArrayList<Event> events);

    protected abstract void controlStep();

    private ArrayList<Event> getHomeEvent() throws IOException {
        Controller controller = Controller.getInstance();
        return controller.control(this.alarmUnit, this.heatingUnit, this.coolingUnit);
    }

    public void simulate() throws IOException {
        int k = 0;
        int SIMULATION_STEPS = 20;
        while (k < SIMULATION_STEPS) {
            ArrayList<Event> events = this.getHomeEvent();
            setValueInEnvironment(events);
            controlStep();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            k++;
        }
    }

}

