/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mrk1
 */
//TODO
// thread monitor Manupulates gui ?
// Collections.synchronizedList() olsun threadler buraya kapasiterlerini yazsın
// arraylist de olabilir sonucta ayrı elemanlara yazacaklar 
public class ThreadMonitor extends Thread {

    public static ArrayList<ThreadMetric> ThreadMetricList;

    public ThreadMonitor() {
        ThreadMetricList = new ArrayList<>();
        System.out.println("ThreadMonitor baslatıldı");
    }

    public synchronized static void addtoThreadMetricList(int ThreadID) {
        System.out.println("ThreadID: " + ThreadID);
        ThreadMetric tm = new ThreadMetric(ThreadID);
        ThreadMetricList.add(tm);
        System.out.println(tm.getID() + " ThreadMonitöre Eklendi");

    }

    public static void removeFromThreadMetricList(int threadID) {
        for (int i = 0; i < ThreadMetricList.size(); i++) {
            if (ThreadMetricList.get(i).getID() == threadID) {
                ThreadMetricList.remove(i);
                break;
            }
        }
    }

    public synchronized static void setLoad(int threadID, int load, int capacity) {
        //System.out.print("set load calisti ");
        //System.out.println(threadID + "," + load + "," + capacity);
        for (ThreadMetric threadMetric : ThreadMetricList) {
            if (threadID == threadMetric.getID()) {
                threadMetric.setLoadPercent((int) ((float) (load / (float) capacity) * (float) 100));
                //System.out.println("set edilen deger" + threadMetric.getLoadPercent());
                //System.out.println("threadMetric load percent degeri güncellendi");
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < ThreadMetricList.size(); i++) {
                System.out.println("Thread ID: " + ThreadMetricList.get(i).getID() + ", ThreadLoadPercent: " + ThreadMetricList.get(i).getLoadPercent());
            }
            wait(1000);
        }

    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

}

class ThreadMetric {

    private int threadID; // bunu atomik yap
    int loadPercent;

    public ThreadMetric(int threadID) {
        this.threadID = threadID;
    }

    public int getLoadPercent() {
        // implmentasyon tutarsızlıgı var ama olsun
        return loadPercent;
    }

    public void setLoadPercent(int loadPercent) {
        this.loadPercent = loadPercent;
    }

    public int getID() {
        return this.threadID;
    }

}
