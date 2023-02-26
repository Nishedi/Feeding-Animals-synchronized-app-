package view;

import controller.Parameters;

import javax.swing.*;
import java.awt.*;

public class DrawSpeeds extends JPanel {
    Parameters parameters;

    public void paint(Graphics G){
        super.paint(G);
        Graphics2D G2D = (Graphics2D) G;
        String eating = "Speed of\n eating";
        String move = "Speed of\n moving";
        String feeding = "Speed of\n feeding";
        String getting = "Speed of\n getting food";
        int y =30;
        for (String line : eating.split("\n"))
            G2D.drawString(line, 245-400+(this.getSize().width)/2, y += G2D.getFontMetrics().getHeight());
        y=30;
        for (String line : move.split("\n"))
            G2D.drawString(line, 245+90-400+(this.getSize().width)/2, y += G2D.getFontMetrics().getHeight());
        y=30;
        for (String line : feeding.split("\n"))
            G2D.drawString(line, 245+180-400+(this.getSize().width)/2, y += G2D.getFontMetrics().getHeight());
        y=30;
        for (String line : getting.split("\n"))
            G2D.drawString(line, 245+270-400+(this.getSize().width)/2, y += G2D.getFontMetrics().getHeight());

    }
}
