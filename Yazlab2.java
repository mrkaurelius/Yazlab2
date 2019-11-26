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
        // threadlere veri girisi kendi looplari uzerinde olmalı
        // threadleri objeler olarak dusunme
        // threadin gercekten istedigim sure kadar durdugunu nerden biliyorum
        // metadayı gostermeti yaptoık implementasyon nasıl iyilestirilebilir
        // nerelerde bilemedigim senaryolar ortaya cıkabilir 
        // java thread yapısı 
        // cevap verme sayisi nasıl implement edilmeli
        // thread lock derek banas
        // elemanların hepsi thread olmalı 
        // simdilik tweak menusunu discard ettim
        // threadler kendi kapasitelerine gore thread monitordeki bir veriyi
        // manipule etsin
        // sub threadlerin kendi kendini imha edebilir
        // thread monitor ile main serveri nasıl senkronize edebilirims
        // synchronisedin anlamı ne

        SwingGui gui = new SwingGui();
        gui.run(); //gui thread loop burada ne yapılabilir ?

        ThreadMonitor threadMonitor = new ThreadMonitor();

        MainServerThread mainSever = new MainServerThread(gui.getMainThreadMonitorPanel());
        SubServerThread subServer_1 = new SubServerThread(1000);
        SubServerThread subServer_2 = new SubServerThread(2000);
        SubServerThread subServer_3 = new SubServerThread(2000);

        threadMonitor.start();
        mainSever.start();
        subServer_1.start();
        subServer_2.start();
        subServer_3.start();

    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

}
