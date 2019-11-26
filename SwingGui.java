/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author mrk1
 */
public class SwingGui extends JFrame implements Runnable {

    private JPanel mainPanel;
    private ThreadMonitorPanel mainThreadMonitorPanel;
    public static JPanel ThreadMonitorContainer;

    //public static ArrayList<ThreadMonitorPanel> dynamicSubThreads = new ArrayList<>();
    // belki dynamicSubThreadlerle gerek kalmaz
    private TweakPanel tweakPanel;

    public SwingGui() {
        initGui();
    }

    public void initGui() {
        this.setSize(860, 650);
        this.setLocationRelativeTo(null);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setLocation(xPos, yPos);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LisenForChange cl = new LisenForChange();
        tweakPanel = new TweakPanel();
        tweakPanel.getSlider().addChangeListener(cl);

        ThreadMonitorContainer = new JPanel();
        ThreadMonitorContainer.setLayout(new GridLayout(0, 3, 10, 10));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainThreadMonitorPanel = new ThreadMonitorPanel("Ana thread", 10000, 0, 0);

        ThreadMonitorContainer.add(mainThreadMonitorPanel);

        //mainPanel.add(tweakPanel, BorderLayout.NORTH);
        mainPanel.add(ThreadMonitorContainer, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public static ThreadMonitorPanel addThreadMonitorPanel(String labelString, int id) {
        // sloppy method
        ThreadMonitorPanel tw = new ThreadMonitorPanel(labelString, 5000, 0, id);
        ThreadMonitorContainer.add(tw);
        //dynamicSubThreads.add(tw);
        return tw;
        //revalidate();

    }

    public static void removeThreadMonitorPanel(ThreadMonitorPanel tw) {
        // sloppy method
        //dynamicSubThreads.remove(tw);
        ThreadMonitorContainer.remove(tw);
        //revalidate();
    }

    /*
    public ArrayList<ThreadMonitorPanel> getDynamicSubThreads() {
        return dynamicSubThreads;
    }
    */
    
    public ThreadMonitorPanel getMainThreadMonitorPanel() {
        return mainThreadMonitorPanel;
    }

    @Override
    public void run() {
        // burada neler olarbilir
        // javanın bu thread hakkında neyi bilmesi
    }

    private class LisenForChange implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            JSlider sl = (JSlider) ce.getSource();
            tweakPanel = (TweakPanel) sl.getParent();
            tweakPanel.setTweakInfo("Rast. Çarpanı: " + sl.getValue());
            System.out.println("slider state changed " + sl.getValue());

        }
    }

    public TweakPanel getTweakPanel() {
        return tweakPanel;
    }

    public void setTweakInfoGui(String ti) {
        tweakPanel.setTweakInfo(ti);
    }

    public int getTweakSliderValueGui() {
        return tweakPanel.getTweakSliderValue();
    }

}
