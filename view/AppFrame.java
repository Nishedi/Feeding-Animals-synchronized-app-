package view;

import controller.Movement;
import controller.Sensors;
import controller.Storage;
import threads.WorkerThread;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppFrame extends JFrame{

    JPanel editpanel;
    JPanel simulationpanel;
    JPanel parameterspanel;
    public JButton removeworker;
    public JButton addworker;
    public JButton removeanimal;
    public JButton addanimal;
    public JButton increasespeedofeating;
    public JButton decreasespeedofeating;
    public JButton increasespeedofmoving;
    public JButton decreasespeedofmoving;
    public JButton increasespeedoffeeding;
    public JButton decreasespeedoffeeding;
    public JButton increasespeedofgettingfood;
    public JButton decreasespeedofgettingfood;
    public ArrayList<WorkerThread> workerThreadslist;

    Sensors sensors;
    public Storage storage;
    Movement movement;

    public AppFrame(ArrayList<WorkerThread> workerThreadslist, Sensors sensors, Movement movement){
        this.movement=movement;
        this.workerThreadslist=workerThreadslist;
        this.sensors=sensors;
        storage=new Storage();
        editpanel = new JPanel();
        removeworker=new JButton();
        removeworker.setText("removeworker");
        addworker=new JButton();
        addworker.setText("addworker");
        removeanimal = new JButton();
        removeanimal.setText("removeanimal");
        addanimal = new JButton();
        addanimal.setText("addanimal");
        editpanel.add(removeworker);
        editpanel.add(addworker);
        editpanel.add(removeanimal);
        editpanel.add(addanimal);

        simulationpanel = new DrawPanel(workerThreadslist, storage, sensors, movement);
        simulationpanel.setSize(800,500);
        simulationpanel.setPreferredSize(new Dimension(800,500));

        parameterspanel = new DrawSpeeds();
        editpanel.setBackground(Color.ORANGE);
        simulationpanel.setBackground(Color.cyan);

        parameterspanel.setBackground(Color.green);
        parameterspanel.setPreferredSize(new Dimension(500,100));
        increasespeedofeating = new JButton();
        decreasespeedofeating = new JButton();
        increasespeedofeating.setText("+");
        decreasespeedofeating.setText("-");
        increasespeedofeating.setBounds(10,10,10,10);
        increasespeedofmoving = new JButton();
        decreasespeedofmoving = new JButton();
        increasespeedofmoving.setText("+");
        decreasespeedofmoving.setText("-");
        increasespeedoffeeding = new JButton();
        decreasespeedoffeeding = new JButton();
        increasespeedoffeeding.setText("+");
        decreasespeedoffeeding.setText("-");
        increasespeedofgettingfood=new JButton();
        decreasespeedofgettingfood=new JButton();
        increasespeedofgettingfood.setText("+");
        decreasespeedofgettingfood.setText("-");
        parameterspanel.add(increasespeedofeating);
        parameterspanel.add(decreasespeedofeating);
        parameterspanel.add(increasespeedofmoving);
        parameterspanel.add(decreasespeedofmoving);
        parameterspanel.add(increasespeedoffeeding);
        parameterspanel.add(decreasespeedoffeeding);
        parameterspanel.add(increasespeedofgettingfood);
        parameterspanel.add(decreasespeedofgettingfood);

        this.add(editpanel, BorderLayout.NORTH);
        this.add(simulationpanel, BorderLayout.CENTER);
        this.add(parameterspanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ustawia domyślną akcję zamknięcia okna
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void removeworkerListener(ActionListener listenforremoveworkerbutton){removeworker.addActionListener(listenforremoveworkerbutton);}
    public void addworkerListener(ActionListener listenforaddworkerbutton){addworker.addActionListener(listenforaddworkerbutton);}
    public void removeanimallistener(ActionListener listenforremoveanimallbutton){removeanimal.addActionListener(listenforremoveanimallbutton);}
    public void addanimallistener(ActionListener listenforaddanimalbutton){addanimal.addActionListener(listenforaddanimalbutton);}
    public void increasespeedofeating(ActionListener listenforincreasespeedofeating){increasespeedofeating.addActionListener(listenforincreasespeedofeating);}
    public void decreasespeedofeating(ActionListener listenfordecreasespeedofeating){decreasespeedofeating.addActionListener(listenfordecreasespeedofeating);}
    public void increasespeedofmoving(ActionListener listenforincreasespeedofmoving){increasespeedofmoving.addActionListener(listenforincreasespeedofmoving);}
    public void decreasespeedofmoving(ActionListener listenfordecreasespeedofmoving){decreasespeedofmoving.addActionListener(listenfordecreasespeedofmoving);}
    public void increasespeedoffeeding(ActionListener listenforincreasesoffeeding){increasespeedoffeeding.addActionListener(listenforincreasesoffeeding);}
    public void decreasespeedoffeeding(ActionListener listenfordecreaseoffeeding){decreasespeedoffeeding.addActionListener(listenfordecreaseoffeeding);}
    public void increasespeedofgettingfood(ActionListener listenforincreasespeedofgettingfood){increasespeedofgettingfood.addActionListener(listenforincreasespeedofgettingfood);}
    public void decreasespeedofgettingfood(ActionListener listenfordecreasespeedofgettingfood){decreasespeedofgettingfood.addActionListener(listenfordecreasespeedofgettingfood);}

}
