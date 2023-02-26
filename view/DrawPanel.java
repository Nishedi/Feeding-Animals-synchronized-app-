package view;

import controller.Movement;
import controller.Sensors;
import controller.Storage;
import threads.WorkerThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    ArrayList<WorkerThread> workerThreadslist;
    Storage storage;
    Sensors sensors;
    Movement movement;
    public DrawPanel(ArrayList<WorkerThread> workerThreadslist, Storage storage, Sensors sensors, Movement movement){
        this.movement=movement;
        this.workerThreadslist=workerThreadslist;
        this.storage=storage;
        this.sensors=sensors;
    }


    public void paint (Graphics G) {
        super.paint(G);
        Graphics2D G2D = (Graphics2D) G;
        G2D.drawLine(100, 50,200+ (sensors.numberofanimals()-1)*50, 50);
        G2D.drawLine(100,50,100,200);
        G2D.drawLine(100,200,200+ (sensors.numberofanimals()-1)*50,200);
        G2D.drawLine(200+ (sensors.numberofanimals()-1)*50,50,200+ (sensors.numberofanimals()-1)*50,200);

        for(int i = 0; i<= sensors.numberofanimals()-1;i++) {
            G2D.drawString(String.valueOf(sensors.getStateofFood(i)),150+i*50,125);
            paintwithcolorlines(G2D, i);
        }
        paintrefill(G2D);

        for(int i = 0; i<= movement.numberofpossitions()-1;i++) {
            if(movement.getpossition(i)!=0) {
                drawworker(G2D, i);
            }
        }

        for(int i = 0; i<= sensors.numberofanimals()-1;i++) {
            paintwithcolorlines(G2D, i);
            G2D.drawString(String.valueOf(sensors.getStateofFood(i)),150+i*50,125);
        }
    }

   /* public void drawman(Graphics2D G2D, int i, int xx){
        int x =xx;
        //670
        int y=50;
        G2D.drawLine(x,y,x,y+15);//brzuch
        G2D.drawLine(x,y+1,x-5,y+9);//rece
        G2D.drawLine(x,y+1,x+5,y+9);
        G2D.drawOval(x-3,y-6,6,6);
        System.out.println(i%3);
        if(i%3==0){
            G2D.drawLine(x,y+15,x,y+27);//nogi
            G2D.drawLine(x,y+15,x,y+27);
        }

        if(i%3==1){
            G2D.drawLine(x,y+15,x-2,y+27);//nogi
            G2D.drawLine(x,y+15,x+2,y+27);
        }

        if(i%3==2){
            G2D.drawLine(x,y+15,x-5,y+27);//nogi
            G2D.drawLine(x,y+15,x+5,y+27);
        }

    }*/
    private void paintwithlines(Graphics2D G2D, int i){
       /* for(int j =0; j<10; j++){
            G2D.setColor(Color.black);
            drawman(G2D,j,146+i+j);
            repaint();
        }*/
        G2D.fillRect(146+i*50,110,20,20);
    }

    public void drawworker(Graphics2D G2D, int i){
        int workerid = movement.getpossition(i);

        if(workerThreadslist.size()<workerid) return;

            int state = workerThreadslist.get(workerid - 1).state;
            if (state == 1)
                G2D.setColor(Color.green);
            if (state == 2)
                G2D.setColor(Color.blue);
            if (state == 3)
                G2D.setColor(Color.yellow);
            if (state == 4)
                G2D.setColor(Color.red);

        G2D.fillOval(143 + i * 50, 160,20,20);
        G2D.setColor(Color.BLACK);
        G2D.drawOval(143 + i * 50, 160,20,20);
        G2D.drawString(String.valueOf(movement.getpossition(i)), 150 + i * 50, 175);
    }

    private void paintrefill(Graphics G2D){
        G2D.drawRect(50, 50, 50, 50);
        if(storage.currentworker==0) {
            G2D.drawString("Current taker: " + "-", 45, 45);
        }else{
            G2D.drawLine(50, 50, 50 + storage.currentworker, 50);
            G2D.drawString("Current taker: " + storage.currentworker, 45, 45);
            G2D.drawRect(50, 50, 50, 50);
            G2D.setColor(Color.blue.brighter());
            G2D.fillRect(50, 100 - storage.storage/2, 50, storage.storage/2);
        }
    }
    private void drawstamina(Graphics2D G2D, int i){
        int s = sensors.getstamina(i);
        G2D.setColor(Color.RED);
        if(s!=0)
             G2D.fillRect(146+i*50,110-s*2,20,s*2);
        if(s==0){
            G2D.setColor(Color.black);
            G2D.fillRect(146+i*50,90,20,40);
        }
        G2D.setColor(Color.BLACK);
    }


    private void paintwithcolorlines(Graphics2D G2D, int i){
        int k = sensors.getStateofFood(i);
        drawstamina(G2D, i);
        G2D.drawLine(146+i*50,90,165+i*50,90);
        G2D.drawLine(146+i*50,90,146+i*50,110);
        G2D.drawLine(165+i*50,90,165+i*50,110);
        G2D.drawLine(146+i*50,109,165+i*50,109);
        if(k>=85) {
            G2D.setColor(Color.green.darker().darker());
            paintwithlines(G2D, i);
            G2D.setColor(Color.BLACK);
            return;
        }
        if(k>=70) {
            G2D.setColor(Color.green.darker());
            paintwithlines(G2D, i);
            G2D.setColor(Color.BLACK);
            return;
        }
        if(k>=55) {
            G2D.setColor(Color.green);
            paintwithlines(G2D, i);
            G2D.setColor(Color.BLACK);
            return;
        }
        if(k>=40) {
            G2D.setColor(Color.YELLOW);
            paintwithlines(G2D, i);
            G2D.setColor(Color.BLACK);
            return;
        }
        if(k>=25) {
            G2D.setColor(Color.orange);
            paintwithlines(G2D, i);
            G2D.setColor(Color.BLACK);
            return;
        }
        if(k>=10) {
            G2D.setColor(Color.RED);
            paintwithlines(G2D, i);
            G2D.setColor(Color.BLACK);
            return;
        }
        G2D.setColor(Color.WHITE);
        paintwithlines(G2D, i);
        G2D.setColor(Color.BLACK);
    }
}
