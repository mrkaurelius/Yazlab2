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
    private final int subServerThreadID;
    private final int capacity;
    private ThreadMonitorPanel tm;
    private int subServerRequests;
    ReentrantLock lock = new ReentrantLock();
    public final Object dummyLock;

    public volatile boolean alive;

    public SubServerThread(int forkRequests) {
        this.alive = true;
        subServerThreadID = 1 + nextID.incrementAndGet();
        this.capacity = 5000;
        this.tm = SwingGui.addThreadMonitorPanel("Thread: " + String.valueOf(subServerThreadID), subServerThreadID);
        ThreadMonitor.addtoThreadMetricList(subServerThreadID);
        this.subServerRequests = forkRequests;
        dummyLock = new Object();
        System.out.println("SubServer ID: " + this.subServerThreadID + " baslatildı");
    }

    private synchronized int getRandRequest() {
        Random r = new Random();
        return r.nextInt(700) + 1;
    }

    private synchronized int getRandRespond() {
        Random r = new Random();
        return r.nextInt(500) + 1;
    }

    @Override
    public void run() {
        System.out.println("subServer ThreadID: " + this.subServerThreadID + " Kosmaya basladı");

        while (this.alive) {

            int newResquests = getRandRequest();

            lock.lock();
            //MainServerThread.requests = MainServerThread.requests - newResquests;
            if (newResquests > MainServerThread.requests) {
                newResquests = MainServerThread.requests;
                MainServerThread.requests = MainServerThread.requests - newResquests;

            } else {
                MainServerThread.requests = MainServerThread.requests - newResquests;
            }

            lock.unlock();

            subServerRequests = subServerRequests + newResquests;
            if (subServerRequests > 5000) {
                subServerRequests = 5000;
            }
            updateRequestGui(newResquests);
            if (subServerRequests >= 3500) {

                lock.lock();
                ThreadManager.createAndStartSubServerThread(subServerRequests / 2);
                lock.unlock();

                subServerRequests = subServerRequests / 2;
            }
            ThreadMonitor.setLoad(this.subServerThreadID, subServerRequests, this.capacity);

            wait(300);

            int newResponds = getRandRespond();

            lock.lock();
            subServerRequests = subServerRequests - newResponds;
            lock.unlock();

            if (subServerRequests <= 0) {
                // thread managerden thread sayisini al eger thread sayisi 2 den 
                // azsa sil

                synchronized (dummyLock) {
                    if (ThreadManager.getSubServerThreadCount() > 2) {
                        System.out.println("Thread ID: " + subServerThreadID + " silindi");
                        ThreadMonitor.removeFromThreadMetricList(this.subServerThreadID);
                        ThreadManager.decreaseThreadCount();
                        updateRespondGui(newResponds);
                        SwingGui.removeThreadMonitorPanel(tm);
                        return;
                    } else {
                        subServerRequests = 0;
                    }
                }

            }

            updateRespondGui(newResponds);

            ThreadMonitor.setLoad(this.subServerThreadID, subServerRequests, this.capacity);
            //updateGui(newResquests, newResponds);

            wait(500);
        }
    }

    private void updateRequestGui(int newRequests) {
        tm.setRequest(subServerRequests, newRequests);
    }

    private void updateRespondGui(int newResponds) {
        tm.setRespond(subServerRequests, newResponds);
    }

    private synchronized void updateGui(int newRequests, int newResponds) {
        tm.setLoad(subServerRequests, newRequests, newResponds);
    }

    public synchronized void killSubServerThread() {
        this.alive = false;
        // asagidaki metoların cıktısını verbose yap
        SwingGui.removeThreadMonitorPanel(tm);
        ThreadMonitor.removeFromThreadMetricList(this.subServerThreadID);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
