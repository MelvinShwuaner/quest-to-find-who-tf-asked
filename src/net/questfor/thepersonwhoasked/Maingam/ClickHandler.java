package net.questfor.thepersonwhoasked.Maingam;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class ClickHandler implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
                KeyHandler.attack = true;
        }
    }
    //USELESS//
    //-------------------//
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
