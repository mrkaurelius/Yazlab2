/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.util.concurrent.atomic.AtomicInteger;
import static yazlab2.MainServerThread.requests;
import static yazlab2.MainServerThread.wait;
import java.util.Random;

/**
 *
 * @author mrk1
 */
public class SubServerThread extends Thread {

    static AtomicInteger nextID = new AtomicInteger();    // this will guarantee that if two objects are created at exactly same time
    private int subServerThreadID;
    private int capacity;
    private ThreadMonitorPanel tm; // cok akıllıca degil ama is gorecek gibi
    private int requests;
    private int newRequests;

    public SubServerThread(int requests) {
        subServerThreadID = 1 + nextID.incrementAndGet();
        this.capacity = 5000;
        this.tm = SwingGui.addThreadMonitorPanel("deneme 1", subServerThreadID);
        ThreadMonitor.addtoThreadMetricList(subServerThreadID);
        this.requests = 1000;
        System.out.println("Subserver ID: " + this.subServerThreadID + " baslatildı");

    }

    private synchronized int getRandRequest() {
        Random r = new Random();
        return r.nextInt(490) + 1;
    }

    private synchronized int getRandRespond() {
        Random r = new Random();
        return r.nextInt(490) + 1;
    }

    @Override
    public void run() {
        while (true) {
            newRequests = getRandRequest();
            requests += newRequests;
            ThreadMonitor.setLoad(this.subServerThreadID, requests, this.capacity);
            wait(500);
            requests -= getRandRespond();
            ThreadMonitor.setLoad(this.subServerThreadID, requests, this.capacity);
            updateGui();

        }

    }

    private void updateGui() {
        tm.setLoad(requests, newRequests);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
