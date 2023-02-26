package controller;

import threads.WorkerThread;

import java.util.ArrayList;

public class Movement {
    public ArrayList<Integer> possitionlist;
    public Movement(){
        possitionlist= new ArrayList<>();
    }

    public void addanimal(){
        possitionlist.add(0);
    }
    public int numberofpossitions(){
        return possitionlist.size();
    }

    public int getpossition(int i){
        return possitionlist.get(i);
    }

    public void setPossitionlist(int i, int value){
        possitionlist.set(i,value);
    }

    public synchronized void move( int newpossition, WorkerThread workerThread){
            if (getpossition(newpossition) == 0) {
                if (workerThread.possition >= 0 && workerThread.possition <= numberofpossitions() - 1)
                    setPossitionlist(workerThread.possition,0);
                workerThread.possition = newpossition;
                setPossitionlist(newpossition,workerThread.number+1);
            } else {
                workerThread.direction = -workerThread.direction;
            }
    }
}
