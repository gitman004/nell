package tlaprojet;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {

    Plot plot;

    final static int PREF_HEIGHT = 300;
    final static int PREF_WIDTH = 400;
    final static double RANGE_ADJUST = 10;

    public static void main(String[] args) {
        Main main = new Main();
        SwingUtilities.invokeLater(main::init);
    }

    public void init() {

        plot = new Plot();

        JFrame frame = new JFrame("Projet TLA 2024");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        PlotPanel widgetTrace = new PlotPanel(plot);
        widgetTrace.setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        frame.add(widgetTrace, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField inputField = new JTextField(20);
        topPanel.add(inputField);

        JButton btnOk = new JButton("Ok");
        topPanel.add(btnOk);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 100, (int)(plot.range*RANGE_ADJUST));
        slider.setPaintLabels(true);
        topPanel.add(slider);

        frame.add(topPanel, BorderLayout.NORTH);

        btnOk.addActionListener(event -> {
            String function = inputField.getText();
            plot.setFunction(function);
            widgetTrace.repaint();
        });

        slider.addChangeListener(event -> {
            plot.setRange((double)slider.getValue()/RANGE_ADJUST);
            widgetTrace.repaint();
        });

        frame.pack();
        frame.setVisible(true);
    }
}
