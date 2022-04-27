package net.questfor.thepersonwhoasked;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class settingspannel extends JPanel implements ChangeListener {
    public void audioandvideo(){
        this.setSize(300, 600);
        this.setLayout(new GridLayout(20, 20));
        JSlider noisevolume = new JSlider(0, 100);
        JSlider musicvolume = new JSlider(0, 100);
        JPanel volumepanel = new JPanel(); volumepanel.setLayout(new GridLayout(10, 10));
        Font font = new Font("arial", Font.BOLD, 5);
        JLabel musictext = new JLabel();
        musictext.setText("music volume");
        musictext.setFont(font);
        JLabel soundtext = new JLabel();
        musictext.setText("sound effect volume");
        musictext.setFont(font);
        volumepanel.add(soundtext);
        soundtext.setAlignmentX(5);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
