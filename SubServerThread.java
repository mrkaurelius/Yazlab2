/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

/**
 *
 * @author mrk1
 */
public class SubServerThread extends Thread {
    // TODO 
    // !!! Requestleri MainServerden al kendi requestine ekle sonra kendi 
    // requestinden cıkar

    static AtomicInteger nextID = new AtomicInteger();    // this will guarantee that if two objects are created at exactly same time
    private int subServerThreadID;
    private int capacity;
    private ThreadMonitorPanel tm;
    private int SubServerRequests;
    ReentrantLock lock = new ReentrantLock();

    public SubServerThread(int forkRequests) {
        subServerThreadID = 1 + nextID.incrementAndGet();
        this.capacity = 5000;
        this.tm = SwingGui.addThreadMonitorPanel("deneme 1", subServerThreadID);
        ThreadMonitor.addtoThreadMetricList(subServerThreadID);
        this.SubServerRequests = forkRequests;
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
            int newResquests = getRandRequest();
            
            lock.lock();
            MainServerThread.requests -= newResquests;
            SubServerRequests += newResquests;
            if (SubServerRequests > 5000) {
                SubServerRequests = 5000;
            }
            ThreadMonitor.setLoad(this.subServerThreadID, SubServerRequests, this.capacity);
            lock.unlock();
            
            wait(300);

            int newResponds = getRandRespond();
           
            lock.lock();
            SubServerRequests -= newResponds;
            if (SubServerRequests < 0) {
                SubServerRequests = 0;
            }
            ThreadMonitor.setLoad(this.subServerThreadID, SubServerRequests, this.capacity);
            lock.unlock();
            
            updateGui(newResquests, newResponds);
            wait(500);
        }
    }

    private void updateGui(int newRequests, int newResponds) {
        tm.setLoad(SubServerRequests, newRequests, newResponds);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
