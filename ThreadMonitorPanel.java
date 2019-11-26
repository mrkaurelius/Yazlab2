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
    private JLabel MonitorInfoLabel;
    private JProgressBar tmProgressBar;
    
    private int capacity;
    private int tmID;
    
    // threadin istek alma ve istek işleme sayısını, gecirdigi zamanı sakla
    // respondları ta labellerde goster
    
    public ThreadMonitorPanel(String labelString, int capacity, int load,int tmID) {
        threadNameLabel = new JLabel();
        tmProgressBar = new JProgressBar();
        MonitorInfoLabel = new JLabel();
       
        threadNameLabel.setText(labelString);
        MonitorInfoLabel.setText("0/" + String.valueOf(capacity) + ", " + "Gelen: "
                + 0);

        
        //tmID = nextID.incrementAndGet();
        this.capacity = capacity;
        this.tmID = tmID;
        tmProgressBar.setMinimum(0);
        tmProgressBar.setMaximum(capacity);

        
        this.add(threadNameLabel);
        this.add(tmProgressBar);
        this.add(MonitorInfoLabel);
        //System.out.println(labelString + " grafik monitörü  olustruldu " + "tmID: ");
    }

    public void setLoad(int load,int incomingRequests) {
        this.tmProgressBar.setValue(load);
        MonitorInfoLabel.setText(load + "/" + String.valueOf(capacity) + ", " + "Gelen: "
                + incomingRequests);
    }

}
