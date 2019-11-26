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
    // class elemanlarının isimlerini daha anlasılır yap
    
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
    
    public void setRandInfo(int randInfo){
        this.randInfo.setText("Rast Deger: " + String.valueOf(randInfo));
    }

    public void setTweakInfo(String ti) {
        tweakInfo.setText(ti);
    }
    
    public int getTweakSliderValue(){
        return this.slider.getValue();
    }

    public JSlider getSlider() {
        return slider;
    }

}
