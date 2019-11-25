/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

/**
 *
 * @author mrk1
 */
class TweakPanel extends JPanel {

    private JSlider slider;
    private JLabel tweakInfo;
    private JLabel randInfo;
    private int randInt;

    // sliderin davranısını test et
    // sunucuların performanslarını tweak edecek widgetleri ekle 
    public TweakPanel() {
        Border tweakBorder = BorderFactory.createTitledBorder("tweak");
        int numRow = 0; //many rows as needed
        int numCol = 3;
        this.setLayout(new GridLayout(numRow, numCol, 10, 10));
        slider = new JSlider();
        tweakInfo = new JLabel();
        randInfo = new JLabel();

        slider.setMaximum(10);
        slider.setMinimum(1);
        tweakInfo.setText("Rast. Çarpanı: " + slider.getValue());
        randInfo.setText("Rast Deger: ?");

        this.add(slider);
        this.add(tweakInfo);
        this.add(randInfo);
        this.setBorder(tweakBorder);
    }

    public void setTweakInfo(String ti) {
        tweakInfo.setText(ti);
    }

    public JSlider getSlider() {
        return slider;
    }

}
