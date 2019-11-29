/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author mrk1
 */
public class ThreadMonitorPanel extends JPanel {

    private JLabel threadNameLabel;
    private JLabel MonitorRequestLabel;
    private JLabel MonitorRespondLabel;

    private JProgressBar tmProgressBar;

    private int capacity;
    private int tmID;

    public ThreadMonitorPanel(String labelString, int capacity, int load, int tmID) {
        threadNameLabel = new JLabel();
        tmProgressBar = new JProgressBar();
        MonitorRequestLabel = new JLabel();
        MonitorRespondLabel = new JLabel();

        threadNameLabel.setText(labelString);

        this.capacity = capacity;
        this.tmID = tmID;
        tmProgressBar.setMinimum(0);
        tmProgressBar.setMaximum(capacity);

        this.add(threadNameLabel);
        this.add(tmProgressBar);
        this.add(MonitorRequestLabel);
        this.add(MonitorRespondLabel);

    }

    public void setLoad(int load, int newResquests, int newResponds) {
        this.tmProgressBar.setValue(load);
        MonitorRequestLabel.setText(load + "/" + String.valueOf(capacity) + ", " + "Gelen: "
                + newResquests + " Cevap: " + newResponds);
    }

    public void setRequest(int load, int newResquests) {
        this.tmProgressBar.setValue(load);
        MonitorRequestLabel.setText(load + "/" + String.valueOf(capacity) + ", " + "Gelen: "
                + newResquests);
    }

    public void setRespond(int load, int newResponds) {
        this.tmProgressBar.setValue(load);
        MonitorRespondLabel.setText(" Cevap: " + newResponds);
    }
}
