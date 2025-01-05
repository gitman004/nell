package tlaprojet;


import javax.swing.*;
import java.awt.*;

public class PlotPanel extends JPanel {

    private Plot plot;

    public PlotPanel(Plot plot) {
        this.plot = plot;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        plot.paint((Graphics2D)g, this.getWidth(), this.getHeight());
    }
}

