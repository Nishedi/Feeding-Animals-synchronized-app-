package threads;

import controller.Movement;
import controller.Parameters;
import controller.Sensors;
import controller.Storage;
import java.util.Random;

public class WorkerThread extends Thread {
    Sensors sensors;
    public int storage;
    public int number;
    int isrunning;
    public int possition;
    public int direction; // -1 left, 1 - right
    Storage storageacces;


    public Parameters parameters;
    Movement movement;

    public int state; // 1 - moving - green, 2 - feeding - blue, 3 - trying to get food source - yellow, 4 - while getting food from source - red
    Random rand = new Random();
    public WorkerThread(int number, Storage storageacces, Parameters parameters, Sensors sensors, Movement movement){
        this.movement=movement;
        this.sensors=sensors;
        this.storageacces=storageacces;
        this.storage = 100;
        this.state=1;
        this.number=number;
        isrunning=1;
        this.parameters=parameters;
        int startpoint = rand.nextInt(0,2);
        if(startpoint==0)
            possition=-1;
        if(startpoint==1)
            possition= movement.numberofpossitions();
    }

    public void run(){
        while(isrunning==1){
                if (possition >= 0 && possition <= movement.numberofpossitions() - 1) {
                    Integer statusoffood = sensors.getStateofFood(possition);
                    int t = sensors.checkorsetstamina(possition, 1);
                    if (statusoffood < 50 &&  t > 0) {
                        state = 2;
                        int x=0;
                        do {
                            if (storage == 0) break;
                            x = sensors.feedorconsume(possition, 1);
                            storage--;
                            try {
                                sleep(parameters.speedoffeeding * 10 + rand.nextInt(6));
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } while (x < 100);
                        sensors.setfalseIsfeeded(possition);
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        state = 1;
                        if (storage == 0) {
                            try {
                                storageacces.getFood(this);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
                move();
                try {
                    sleep(parameters.speedofmoving*100+ rand.nextInt(6)*10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

        }
        System.out.println("Closed");
    }

    public void move(){
        if(possition<=0){
            direction=1;
        }
        if(possition>=movement.numberofpossitions()){
            direction=-1;
        }
        if(possition>=movement.numberofpossitions()-1){
            direction=-1;
        }

        int newpossition;

        newpossition=possition+direction;
        movement.move(newpossition, this);

    }
    public void cancel(){
        isrunning=0;
    }
}

