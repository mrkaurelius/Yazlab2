/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author mrk1
 */
public class ThreadManager implements Runnable {

    //TODO
    // manager yeni subserverthread olusturmalı ve istekleri yeni olusan sunucuya
    // aktarmalı
    public static int threadCount;

    public ThreadManager() {
        System.out.println("ThreadManager başlatıldı");
        this.threadCount = 0;
    }

    public static synchronized void createAndStartSubServerThread(int forkRequests) {
        threadCount = threadCount + 1; 
        SubServerThread subServer = new SubServerThread(forkRequests);
        subServer.start();
        //System.out.println("SubServer subServerThreadList'e eklendi eklendi");
    }

    public static synchronized int getSubServerThreadCount() {
        // bu metod ve array list senkronizasyona ne kadar uygun 
        return threadCount;
    }

    public static synchronized void decreaseThreadCount(){
        threadCount = threadCount - 1;
    }
    
    @Override
    public void run() {
        // diğer threadleri kontrol et biri %70 gecerse yeni thread olustur
    }

}
