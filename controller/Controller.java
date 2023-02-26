package controller;

import threads.AnimalThread;
import threads.WorkerThread;
import view.AppFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Controller {
    AppFrame appframe;
    Timer timer;
    ArrayList<AnimalThread> animalThreadslist = new ArrayList<>();
    ArrayList<WorkerThread> workerThreadslist;
    Parameters  parameters;
    Storage storageacces;
    Sensors sensors;
    Movement movement;
    public Controller(AppFrame appframe, Sensors sensors, Movement movement) {
        this.movement=movement;
        this.sensors=sensors;
        this.appframe = appframe;
        this.workerThreadslist=appframe.workerThreadslist;
        storageacces= appframe.storage;
        this.appframe.removeanimallistener(new removeanimallistener());
        this.appframe.addanimallistener(new addanimallistener());
        this.appframe.addworkerListener(new addworkerlistener());
        this.appframe.removeworkerListener(new removeworkerListener());
        this.appframe.increasespeedofeating(new increasespeedofeating());
        this.appframe.decreasespeedofeating(new decreasespeedofeating());
        this.appframe.increasespeedofmoving(new increasespeedofmoving());
        this.appframe.decreasespeedofmoving(new decreasespeedofmoving());
        this.appframe.increasespeedoffeeding(new increasespeedoffeeding());
        this.appframe.decreasespeedoffeeding(new decreasespeedoffeeding());
        this.appframe.increasespeedofgettingfood(new increasespeedofgettingfood());
        this.appframe.decreasespeedofgettingfood(new decreasespeedofgettingfood());
        parameters=new Parameters();
        timer = new Timer(10, new TimerListener());
        timer.start();

        for(int i =0 ; i<=sensors.numberofanimals()-1; i++) {
            AnimalThread animalThread = new AnimalThread(i, parameters, sensors);
            animalThreadslist.add(animalThread);
            animalThread.start();
        }

        for(int i = 0; i <= 3; i++){
            WorkerThread workerThread = new WorkerThread( i, storageacces, parameters, sensors, movement);
            workerThread.state=1;
            workerThreadslist.add(workerThread);
            workerThread.start();
        }
    }

    class TimerListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            appframe.repaint();
        }
    }

    class removeworkerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            WorkerThread temp = workerThreadslist.get(workerThreadslist.size()-1);
            temp.cancel();
            movement.setPossitionlist(temp.possition,0);
            workerThreadslist.remove(workerThreadslist.size()-1);
            }
    }

    class addworkerlistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            WorkerThread workerThread = new WorkerThread(workerThreadslist.size(),storageacces, parameters, sensors, movement);
            workerThreadslist.add(workerThread);
            workerThread.start();
        }
    }

    class removeanimallistener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (animalThreadslist.size() > 0) {
                animalThreadslist.get(animalThreadslist.size()-1).cancel();
                animalThreadslist.remove(animalThreadslist.size()-1);
            }
            if(sensors.numberofanimals()>0){
               sensors.removeanimal();
                movement.possitionlist.remove(movement.numberofpossitions()-1);
            }
        }
    }

    class addanimallistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sensors.addanimal();
            AnimalThread animalThread = new AnimalThread(animalThreadslist.size(), parameters, sensors);
            animalThreadslist.add(animalThread);
            movement.addanimal();
            animalThread.start();
        }
    }
    class increasespeedofeating implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parameters.decreasespeedofeating();
        }
    }
    class decreasespeedofeating implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parameters.increasespeedofeating();
        }
    }
    class increasespeedofmoving implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {parameters.decreasespeedofmoving();}
    }

    class decreasespeedofmoving implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {parameters.increasespeedofmoving();}
    }
    class increasespeedoffeeding implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {parameters.decreasespeedoffeeding();}
    }

    class decreasespeedoffeeding implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {parameters.increasespeedoffeeding();
        System.out.println(parameters);}
    }
    class increasespeedofgettingfood implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {parameters.decreasespeedofgettingresource();}
    }
    class decreasespeedofgettingfood implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {parameters.increasespeedofgettingresource();      }
    }
}
