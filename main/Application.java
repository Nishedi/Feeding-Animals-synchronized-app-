package main;

import controller.Controller;
import controller.Movement;
import controller.Parameters;
import controller.Sensors;
import threads.AnimalThread;
import threads.WorkerThread;
import view.AppFrame;

import java.util.ArrayList;
import java.util.Stack;

public class Application {

    public static void main(String[] args){
        Sensors sensors = new Sensors();
        Movement movement = new Movement();
        ArrayList<WorkerThread> workerThreadslist = new ArrayList<>();
       for(int i =1; i<=10; i++){
            sensors.addanimal();
        }
        for(int i = 0; i <=  sensors.numberofanimals()-1; i++) {
            movement.addanimal();
        }

        AppFrame Frame = new AppFrame(workerThreadslist, sensors, movement);
        Controller controller = new Controller(Frame, sensors, movement);
    }
}
