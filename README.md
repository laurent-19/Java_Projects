# Java basic projects

This is a collection of applications which are dealing with the following concepts:

- **Chronometer** : Graphic interfaces using Swing + Threads
- **FileReader** : Graphic interfaces using Swing + Working with IO streams and files
- **Home_Automation** : Singleton controller + Designing and implementing java applications + Threads
- **Railway_Traffic_Simulator** : Concept of Controller + Graphic interfaces using Swing
- **Stock_Manager** : Model-View-Controller design pattern + Graphic interfaces using Swing
- **Temperature_Monitor** : Observer-Observable design pattern + Graphic interfaces using Swing
- **Tic-tac-toe** : Graphic interfaces using Swing , GridLayout

## Chronometer

### Description
The applications containts a basic chronometer (UI) will have 2 main buttons.<br/>
The first button starts and pauses the chronometer and the second is used to reset it.<br/>
The chronometer is implemented as a java thread, using a class Counter, which extends Thread.<br/>
For the implentation of this behavior, functions like : ***wait/notify/synchrnozed*** are used.

### Visuals

This is the main interface, with the two start/pause and reset buttons. 

![chrono interface](https://github.com/laurent-19/Java_Projects/blob/master/chronometer/interfaceScreenShots/chronometer.png)

The start button calls to *start() method* of the Counter Thread, while the pause changes an attribute *boolean pause* of the Counter class.<br/>
When paused, the *synchronized pause()* method is called and when not, the *synchronized increase()* method is called<br/>
,increasing the value of the counter, which is constantly being updated in the UI.

### Project status

This is a basic implementation, but it can be the base for developing new features like:
- a better, more complex UI, which may include TextFields for the miliseconds or minutes, more buttons and more frendly graphics
- new behaviors like: counting down, programmed countdown or pop-ups to alert the user
- debouncer for the buttons


## FileReader

### Description
This is an application which let a user enter a file name in a text field and then, when a button is pressed,<br/>
displays the content of the file in a text area, basically acting like a *file reader*.<br/>
If the *file does not exists* in the respective folder, a message is dispalyed accordingly.

### Visuals

This is the main look of the applications, considering 2 chances where the files exist and another one,<br/>
where there is no file with that respective name.

***No file***
![no file](https://github.com/laurent-19/Java_Projects/blob/master/file_reader/interfaceScreeenShots/ex3_v3.png)
***Some file***
![some file](https://github.com/laurent-19/Java_Projects/blob/master/file_reader/interfaceScreeenShots/ex2_v2.png)
***some other file***
![some other file](https://github.com/laurent-19/Java_Projects/blob/master/file_reader/interfaceScreeenShots/ex3_v1.png)

### Project status
The application functions considering only a limited searching space for the files.<br/>
It can modified to search any file, from any folder, as long as the *String* used for the path is correctly build.<br/>
The content could be displayed on a different window, we just need to create a different frame to hold the information.


## Home_Automation

### Description 

The application contains 5 packages:
- controllers <br/>
The controller class, *the control unit*, implemented using ***Singleton design pattern*** <br/> 
,which take as parameters instances of the other units, controlling their functionalities.

- events <br/>
The events are children of the parent *abstract class Event*, each of them describing different situations, stored in an *enum*.

- sensors <br/>
The sensors are implementations of the *abstract class Sensor*, each of them having different functionalities.<br/>
*Fire rule*: if fire sensor is activated then alarm starts and the owner is called <br/>
*Temperature rules*: if temperature is lower than a preset value then the heating unit turns on <br/>
and if temperature is higher than a preset value, then the cooling unit is activated. <br/>
System includes one temperature sensor but multiple fire sensors.

- units <br/>
They assure the main functionalities : alarm unit, heating unit, cooling unit and gsm unit.

- home <br/>
The main class uses a Thread a simulate the functionalities. <br/>
The *simulate()* function calls the controller *control()* function and mimics the steps, also displaying the events in a file. <br/>

```java

	  //test using an anonymous inner class
        Home h = new Home() {
            protected void setValueInEnvironment(ArrayList<Event> events) {
                System.out.println("The list of new events in the environment which happened : " + events.toString());
            }
            protected void controlStep() {
                System.out.println("Control step executed\n");
            }
        };
        try {
            h.simulate();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
```

<br/>

```java

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
```

### Visuals

Here is the UML class diagram of the application :

![diagram](https://user-images.githubusercontent.com/56712307/119859396-06dcf480-bf1e-11eb-946f-0e745290b40f.png)

## Railway_Traffic_Simulator

### Description 

The application contains 3 entities classes :
- **Train** <br/>
Basic class which models a train.

- **Segment** <br/>
A class whos instances will be controlled by the controller (train station).<br/>
This class will contain an instance of the Train class, implementing different methods which simulates the flow on the transport: departures, arrivals, availability, etc.

- **Controller** <br/>
This class practically models a particular train station, <br/> 
which is unique and manages the segments, most of the times considering its neighbouring stations, <br/> 
monitoring the availability of a particualr segment and managing the flow of the transports.


```java

	  void controlStep() {
        //check which train must be sent
        for (Segment segment : list) {
            if (segment.hasTrain()) {
                Train t = segment.getTrain();
                for (Controller neighbourController : neighbourControllers) {
                    if (t.getDestination().equals(neighbourController.stationName)) {
                        //check if there is a free segment
                        int id = neighbourController.getFreeSegmentId();
                        if (id == -1) {
                            System.out.println("The train " + t.name + " from station " + stationName + " could not be send to " + neighbourController.stationName + ". No segment available !");
                            return;
                        }
                        //send train
                        System.out.println("The train " + t.name + " leaves the station " + stationName + " heading to " + neighbourController.stationName);
                        segment.departTrain();
                        neighbourController.arriveTrain(t, id);
                    }
                }

            }
        }//.for

    }//.
```

<br/>

```java

public void arriveTrain(Train t, int idSegment) {
        for (Segment segment : list) {
            //search id segment and add train on it
            if (segment.id == idSegment)
                if (segment.hasTrain()) {
                    System.out.println("CRASH! Train " + t.name + " collided with " + segment.getTrain().name + " on segment " + segment.id + " in station " + stationName);
                    return;
                } else {
                    System.out.println("Train " + t.name + " arrived on segment " + segment.id + " in station " + stationName);
                    segment.arriveTrain(t);
                    return;
                }
        }

        //this should not happen
        System.out.println("Train " + t.name + " cannot be received " + stationName + ". Check controller logic algorithm!");

    }

```

The application also contains 1 main class, the ***Simulator*** : <br/>
This class uses the graphical interfaces of *Swing* to *display the stations* and offers the user functionalities like *adding a segemnt to a station*.

### Visuals

***the inital view***
![main view](https://github.com/laurent-19/Java_Projects/blob/master/railway_traffic_simulator/interfaceScreeenShots/ex5_initial.png)

***adding segments***
![added station view](https://github.com/laurent-19/Java_Projects/blob/master/railway_traffic_simulator/interfaceScreeenShots/ex5_modified_v1.png)

***adding more segments***
![added another station view](https://github.com/laurent-19/Java_Projects/blob/master/railway_traffic_simulator/interfaceScreeenShots/ex5_modified_v2.png)


## Stock_Manager

### Description 

The application is created using the Model-View-Controller design pattern, and implicitly, it contains the following packages:

- **models** <br/>
Which contains the ***ProductModel*** class, which models the product entity and implements the methods *addProduct()*, *deleteProduct()* and other methods which change the data within the stock.

- **controllers** <br/>
Which contains the ***ProductViewController*** class, which incorporates a instance of the *productModel* and creates a graphic interface which allows the user to use the methods implemented in the model.  <br/>

An example of such graphic implemenation on a button would be:
 
```java

class ImplementAddButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if ((!tProductName.getText().equals("")) && (!tProductPrice.getText().equals("")) && (!tProductQuantity.getText().equals(""))) {
                productModel.addProduct(
                        tProductName.getText(),
                        Integer.parseInt(tProductPrice.getText()),
                        Integer.parseInt(tProductQuantity.getText()));
                textArea.setText("Product Added!");
            } else {
                textArea.setText("Failed to add! \nCheck if all inputs are filled correctly!");
            }
        }
    }

```

```java

bAddProduct.addActionListener(new ImplementAddButton());

```

- **main** <br/>
The main *StockManager* class, which contains an instance of the *ProductsViewController*, as a presentation.

### Visuals

The main interface and the implementation of the functionalies for the user are presented below:

***adding products***
![added product](https://github.com/laurent-19/Java_Projects/blob/master/stock_manager/interfaceScreenshots/addProduct.png)

***viewing all products***
![viwed products](https://github.com/laurent-19/Java_Projects/blob/master/stock_manager/interfaceScreenshots/viewAllProducts.png)

***updating products***
![updated product](https://github.com/laurent-19/Java_Projects/blob/master/stock_manager/interfaceScreenshots/updateProduct.png)



## Temperature_Monitor

## Tic-tac-toe
