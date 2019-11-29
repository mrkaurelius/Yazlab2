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
    private final int capacity;
    private ThreadMonitorPanel tm;
    ReentrantLock lock = new ReentrantLock();

    public MainServerThread(ThreadMonitorPanel tm) {
        // monitor panel eklemeyi sub threadde farklı implement ettim 
        this.tm = tm;
        capacity = 10000;
        ThreadMonitor.addtoThreadMetricList(1);
    }

    private synchronized int getRandRequest() {
        Random r = new Random();
        return r.nextInt(2000) + 1;
    }

    private synchronized int getRandRespond() {
        Random r = new Random();
        return r.nextInt(500) + 1;
    }

    @Override
    public void run() {
        while (true) {
            // neden cevap istegi hic gecemiyor
            // request manipulasyonuna daha estetik bir cozum bulunabilir 
            // thread veri iletisimini nerede lock etmeliyim yada lock etmeli miyim ?

            int newRequests = getRandRequest();

            lock.lock();

            requests = requests + newRequests;
            if (requests > 10000) {
                requests = 10000;
            }
            lock.unlock();

            ThreadMonitor.setLoad(1, requests, this.capacity);

            wait(200);

            int newResponds = getRandRespond();

            lock.lock();

            requests = requests - newResponds;
            if (requests < 0) {
                requests = 0;
            }

            lock.unlock();

            ThreadMonitor.setLoad(1, requests, this.capacity);
            updateGui(newRequests, newResponds);

            wait(500);

        }

    }

    private synchronized void updateGui(int newRequests, int newResponds) {
        tm.setLoad(requests, newRequests, newResponds);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
