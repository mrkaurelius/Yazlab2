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
public class SwingGui extends JFrame {

    private JPanel mainPanel;
    private ThreadWatcherPanel mainThreadWatcherPanel;
    private ThreadWatcherPanel subThreadWatcherPanel_1;
    private ThreadWatcherPanel subThreadWatcherPanel_2;
    JPanel ThreadWatcherContainer;
    private ArrayList<ThreadWatcherPanel> dynamicSubThreads = new ArrayList<>();

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
        //this.setLayout(new BorderLayout());
        this.setLocation(xPos, yPos);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // sliderin configini ayarla
        LisenForChange cl = new LisenForChange();
        TweakPanel tp = new TweakPanel();
        tp.getSlider().addChangeListener(cl);

        ThreadWatcherContainer = new JPanel();
        ThreadWatcherContainer.setLayout(new GridLayout(0, 3, 10, 10));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainThreadWatcherPanel = new ThreadWatcherPanel("Ana thread", 1000, 0);
        subThreadWatcherPanel_1 = new ThreadWatcherPanel("Alt thread1", 500, 0);
        subThreadWatcherPanel_2 = new ThreadWatcherPanel("Alt thread2", 500, 0);

        ThreadWatcherContainer.add(mainThreadWatcherPanel);
        ThreadWatcherContainer.add(subThreadWatcherPanel_1);
        ThreadWatcherContainer.add(subThreadWatcherPanel_2);

        mainPanel.add(tp, BorderLayout.NORTH);
        mainPanel.add(ThreadWatcherContainer, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void addThreadWatcherPanel(String labelString) {
        ThreadWatcherPanel tw = new ThreadWatcherPanel(labelString, 500, 0);
        ThreadWatcherContainer.add(tw);
        dynamicSubThreads.add(tw);
        revalidate();
    }

    public void removeThreadWatcherPanel(ThreadWatcherPanel tw) {
        dynamicSubThreads.remove(tw);
        ThreadWatcherContainer.remove(tw);
        revalidate();
    }

    public ArrayList<ThreadWatcherPanel> getDynamicSubThreads() {
        return dynamicSubThreads;
    }

    public ThreadWatcherPanel getMainThreadWatcherPanel() {
        return mainThreadWatcherPanel;
    }

    public ThreadWatcherPanel getSubThreadWatcherPanel1() {
        return subThreadWatcherPanel_1;
    }

    public ThreadWatcherPanel getSubThreadWatcherPanel2() {
        return subThreadWatcherPanel_2;
    }

    private class LisenForChange implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            JSlider sl = (JSlider) ce.getSource();
            TweakPanel tw = (TweakPanel) sl.getParent();
            tw.setTweakInfo("Rast. Çarpanı: " + sl.getValue());
            System.out.println(sl.getValue());
            //tweakInfo.setText("Rast. Çarpanı: " + slider.getValue());

        }
    }

}