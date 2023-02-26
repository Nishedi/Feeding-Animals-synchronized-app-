package threads;

import controller.Parameters;
import controller.Sensors;
import java.util.Random;

public class AnimalThread extends Thread{
    Parameters parameters;
    int number;
    int isrunning;
    Random rand;
    Sensors sensors;
    int counter;
    public AnimalThread( int number, Parameters parameters, Sensors sensors){
        this.sensors=sensors;
        this.number=number;
        rand=new Random();
        isrunning=1;
        this.parameters=parameters;
    }

    public void run(){
        System.out.println("StartAnimalThread "+number);
        counter=1;
        int counter2=1;
        while(isrunning==1){
            sensors.feedorconsume(number,0);
            if(sensors.getStateofFood(number)>=80&&sensors.getstamina(number)<=9&&counter2%10==0) {//regeneracja
                sensors.setstamina(number, sensors.getstamina(number) + 1);
            }
            counter2++;
            if(sensors.getStateofFood(number)<1){//umieranie
                counter++;
                if(counter%5==0)
                    sensors.checkorsetstamina(number,0);
            }
            try {
                sleep(parameters.speedofeating*100+10*rand.nextInt(0,6));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(sensors.numberofanimals()>number) {
                if (sensors.checkorsetstamina(number,1) <= 0) {
                    cancel();
                }
            }else{
                cancel();
            }
        }
    }
    public void cancel(){
        isrunning=0;
    }
}
