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
class ThreadWatcherPanel extends JPanel {
    private JLabel threadNameLabel;
    private JLabel capacityInfoLabel;
    private JProgressBar twProgressBar;
    private int capacity;
    private int load;
    private int incomingRequest;
    private int answeredRequest;
    private int twID;
    static AtomicInteger nextID = new AtomicInteger();    // this will guarantee that if two objects are created at exactly same time
    // threadin istek alma ve istek işleme sayısını, gecirdigi zamanı sakla

    public ThreadWatcherPanel(String labelString, int capacity, int load) {
        threadNameLabel = new JLabel();
        twProgressBar = new JProgressBar();
        capacityInfoLabel = new JLabel();

        capacityInfoLabel.setText("0/" + String.valueOf(capacity)+", " + "Gelen: "+
        incomingRequest + " İşlenen: " + answeredRequest + " Fark: " + 
        (incomingRequest-answeredRequest) );
        threadNameLabel.setText(labelString);
        twID = nextID.incrementAndGet();
        this.capacity = capacity;
        this.load = load;

        twProgressBar.setMinimum(0);
        twProgressBar.setMaximum(capacity);

        this.add(threadNameLabel);
        this.add(twProgressBar);
        this.add(capacityInfoLabel);
        System.out.println(labelString + " olustruldu " + "twID: " + twID);
    }

    public void setLoad(int load) {
        this.twProgressBar.setValue(load);
        capacityInfoLabel.setText(String.valueOf(load) + "/" + String.valueOf(capacity));
    }

}