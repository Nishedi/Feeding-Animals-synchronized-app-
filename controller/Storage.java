package controller;

import threads.WorkerThread;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Storage {
    private int flag;
    public int currentworker=0;
    public int storage;
    Random rand = new Random();
    public Storage(){
        flag=0;
    }
    public void getFood(WorkerThread workerThread) throws InterruptedException {
        workerThread.state=3;
        synchronized (this) {
            workerThread.state = 4;
            flag = 1;
            currentworker=workerThread.number+1;
            while(workerThread.storage<100) {
                sleep(workerThread.parameters.speedofgettingresource*20+ rand.nextInt(20));
                workerThread.storage+=1;
                storage=workerThread.storage;
            }
        }
        workerThread.state=1;
        storage=0;
        currentworker=0;
    }
}
