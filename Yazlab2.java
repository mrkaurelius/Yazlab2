/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

/**
 *
 * @author mrk1
 */
public class Yazlab2 {

    public static void main(String[] args) {
        // TODO code application logic here
        // threadleri nasıl somutlastırmali
        // nerelerde bilemedigim senaryolar ortaya cıkabilir 
        // !!! java thread yapısı 
        // !!! kodu nasıl test etmeliyim 
        // !!! threadler nasıl senkronize edilmeli
        // suanlık 
        // !!! thread monitor nasıl implement edilmeli
        //     sub threadler kendilerini nasıl bir yolla kapatmalı
        // kilitleri kaldısam ne degisecek
        // update guiyi degistirebilirim 
        // thread monitor panele 2 tabe label ekleyebilirim
        
        
        SwingGui gui = new SwingGui();
        gui.run(); //gui thread loop burada ne yapılabilir ?

        ThreadManager threadManager = new ThreadManager();
        ThreadMonitor threadMonitor = new ThreadMonitor();

        MainServerThread mainSever = new MainServerThread(gui.getMainThreadMonitorPanel());
        //mainSever.setPriority(10);
        //SubServerThread subServer_1 = new SubServerThread(0);
        //SubServerThread subServer_2 = new SubServerThread(0);
        //SubServerThread subServer_3 = new SubServerThread(0);

        threadManager.run();
        threadMonitor.start();
        mainSever.start();
        //subServer_1.start();
        //subServer_2.start();
        //subServer_3.start();

        ThreadManager.createAndStartSubServerThread(0);
        ThreadManager.createAndStartSubServerThread(0);

    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

}
