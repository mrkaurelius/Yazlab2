/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mrk1
 */
public class MainServerThread extends Thread {

    // TODO 
    public static int requests;
    private int newRequests;
    private int capacity;


    private ThreadMonitorPanel tm; // cok akıllıca degil ama is gorecek gibi

    public MainServerThread(ThreadMonitorPanel tm) {
        this.tm = tm;
        capacity = 10000;
        ThreadMonitor.addtoThreadMetricList(1);
    }

    public int getRequestCount() {
        return MainServerThread.requests;
    }

    public int getNewRequestCount() {
        return this.newRequests;
    }


    private synchronized int getRandRequest() {
        Random r = new Random();
        return r.nextInt(990) + 1;
    }

    private synchronized int getRandRespond() {
        Random r = new Random();
        return r.nextInt(490) + 1;
    }

    @Override
    public void run() {
        while (true) {
            // neden cevap istegi hic gecemiyor
            newRequests = getRandRequest();
            requests += newRequests;
            if(requests >= 10000){
                requests = 10000;
            }
            ThreadMonitor.setLoad(1, requests, this.capacity);
            wait(500);
            requests -= getRandRespond();
            ThreadMonitor.setLoad(1, requests, this.capacity);
            updateGui();

            //
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
