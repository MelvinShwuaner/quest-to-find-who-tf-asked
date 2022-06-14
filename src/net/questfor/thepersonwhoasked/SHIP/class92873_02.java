package net.questfor.thepersonwhoasked.SHIP;

import net.questfor.thepersonwhoasked.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class class92873_02 extends JPanel implements ActionListener {
    public int Timer;
    BufferedReader timer = new BufferedReader(new FileReader("clock.txt"));
    BufferedWriter clocktimer = new BufferedWriter(new FileWriter("clock.txt"));
    JLabel stats = new JLabel();
    int[] isalive = new int[9];
    Action upAction;
    Action downAction;
    Action start;
    int xend = 330;
    int movement = 0;
    float[] xcords = new float[9];
    float[] ycords = new float[9];
    int newshipinc = 0;
    int yend = 120;

    Timer ticks = new Timer(10, this);
    Timer beams =new Timer(5000, this);
    Timer beamsinc =new Timer(2500, this);
    Timer newship = new Timer(5000, this);
    ImageIcon background = new ImageIcon(Main.class.getClassLoader().getResource("shipgame/NET-ZERO-SPACE-INITIATIVE-1.png"));
    ImageIcon ship = new ImageIcon(Main.class.getClassLoader().getResource("shipgame/external ship.png"));
    ImageIcon exship = new ImageIcon(Main.class.getClassLoader().getResource("shipgame/ship2.png"));
    Rectangle yendcords1 = new Rectangle(0, 0, ship.getIconWidth(), ship.getIconHeight());
    Rectangle yendcords2 = new Rectangle(0, 0, ship.getIconWidth(), ship.getIconHeight());
    Rectangle yendcords3 = new Rectangle(0, 0, exship.getIconWidth(), exship.getIconHeight());
    ImageIcon externalship = new ImageIcon(Main.class.getClassLoader().getResource("shipgame/beem.png"));
    ImageIcon beem = new ImageIcon(Main.class.getClassLoader().getResource("shipgame/ship.png"));
    int x = 850;
    int y = 150;
    int[] ybeem = new int[5];
    int xbeem = x;
    int xbeem2 = x;
    Rectangle hitbeem = new Rectangle();
    Rectangle hitbeem2 = new Rectangle();
    Rectangle hitbeem3 = new Rectangle();
    Rectangle hitbeem4 = new Rectangle();
    float xvelocity = 1;
    static int[] health = new int[7];
    class92873_02() throws IOException{
        ybeem[1] = -100;
        ybeem[2] = -200;
        ycords[1] = (int) (yend + (Math.random() * 10));
        ycords[2] = (int) (yend + (Math.random() * 100));
        ycords[3] = (int) (yend + (Math.random() * 200));
        hitbeem.setSize(beem.getIconWidth(), beem.getIconHeight());
        hitbeem2.setSize(beem.getIconWidth(), beem.getIconHeight());
        hitbeem3.setSize(beem.getIconWidth(), beem.getIconHeight());
        hitbeem4.setSize(beem.getIconWidth(), beem.getIconHeight());
        this.setSize(Main.window.getSize());
        this.setBackground(Color.black);
        ticks.start();
        beams.start();
        newship.start();
        upAction = new UpAction();
        downAction = new DownAction();
        start = new startAction();
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "Upaction");
        this.getActionMap().put("Upaction", upAction);
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "DOWNaction");
        this.getActionMap().put("DOWNaction", downAction);
            this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "RIGHTaction");
        this.getActionMap().put("RIGHTaction", start);
        xcords[0] = (xend + 20);
        xcords[1] = (xend + 20);
        xcords[2] = (xend + 20);
        xcords[3] = (xend + 20);
        xcords[4] = (xend + 20);
        isalive[1] = 0;
        isalive[2] = 0;
        isalive[3] = 0;
        health[1] = 2;
        health[2] = 3;
        health[3] = 5;
        health[4] = 4;
        health[5] = 6;
        health[6] = 10;
        this.add(stats);
        stats.setBackground(Color.green);
    }
    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            movement = 1;
        }
    }
    public class startAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            isalive[3] = 1;
        }
    }
    public class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            movement = -1;
        }
    }
    public void paint(Graphics g){
        Graphics2D game = (Graphics2D) g;
        game.setColor(Color.white);
        game.setStroke(new BasicStroke(20));
        game.drawLine(350, 500, 350, 50);
        game.drawLine(350, 50, 1040, 50);
        game.drawLine(1040, 50, 1040, 500);
        game.drawLine(1040, 500, 350, 500);
        game.setFont(new Font("8 Bit Operator", Font.BOLD, 20));
        game.drawImage(background.getImage(), 351, 51, 680, 440, null);
        game.drawImage(ship.getImage(), x, y,null);

        if(isalive[1] == 1) {
            game.drawImage(externalship.getImage(), (int) xcords[1], (int) ycords[1], null);
            xcords[1] =  xcords[1] + (xvelocity * 2);
        }
        if(newshipinc == 1) {
            if (isalive[2] == 1) {
                    game.drawImage(externalship.getImage(), (int) xcords[2], (int) ycords[2], null);
                    xcords[2] = xcords[2] + xvelocity;
            }
        }
            if(isalive[3] == 1){
                game.drawImage(exship.getImage(), (int) xcords[3], (int) ycords[3], null);
                xcords[3] = xcords[3] + (xvelocity / 2);
            }
        game.setStroke(new BasicStroke(2));
        game.drawImage(beem.getImage(), xbeem, ybeem[1], null);
        game.drawImage(beem.getImage(), xbeem, ybeem[2], null);
        game.drawImage(beem.getImage(), xbeem2, ybeem[3], null);
        game.drawImage(beem.getImage(), xbeem2, ybeem[4], null);
        game.drawRect((int) (xcords[1]), (int) ycords[1], (int)yendcords1.getWidth(), (int)yendcords1.getHeight());
        game.drawRect((int) (xcords[2]), (int) ycords[2], (int)yendcords1.getWidth(), (int)yendcords1.getHeight());
        game.drawRect((int) (xcords[3]), (int) ycords[3], (int)yendcords3.getWidth(), (int)yendcords3.getHeight());
        game.drawRect(xbeem, ybeem[1], (int) hitbeem.getWidth(), (int) hitbeem.getHeight());
        game.drawRect(xbeem, ybeem[2], (int) hitbeem.getWidth(), (int) hitbeem.getHeight());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == beamsinc){
                xbeem2 = (int) yendcords3.getMaxX();
                ybeem[3] = (int) yendcords3.getMaxY() + 10;
            }
            if((hitbeem.intersects(yendcords1)) || (hitbeem2.intersects(yendcords1))) {
                isalive[1] = 0;
                xcords[1] = xend;
                ycords[1] = (float) (yend + (Math.random() * 10));
            }
            if((hitbeem2.intersects(yendcords2)) || (hitbeem.intersects(yendcords2))) {
                xcords[2] = xend;
                isalive[2] = 0;
            }
        if((hitbeem2.intersects(yendcords3)) || (hitbeem.intersects(yendcords3))){
            isalive[3] = 0;
            if(health[1] <= 0) {
                JOptionPane.showMessageDialog(null,"lolz");
                isalive[3] = 0;
            }
        }
        if(movement == 1 && y > 45){
            y = y - 1;
        } else if (movement == -1 && y < 360) {
            y = y + 1;
        }
        if (xcords[1] == x || xcords[2] == x || xcords[3] + 80 == x){
            this.setVisible(false);
        }
        if(e.getSource() == beams) {
            xbeem = (x + 80);
            ybeem[1] = y;
            ybeem[2] = ybeem[1] + (ship.getIconHeight() - 50);
        }
        if(e.getSource() == beamsinc){
            if(isalive[3] == 1){
                xbeem2 = (int) yendcords3.getMaxX();
                ybeem[3] = (int) yendcords3.getMaxY();
            }
        }
        xbeem2 = (int) (xbeem2 + xvelocity);
        xbeem = (int) (xbeem + (xvelocity * -2));
        if(e.getSource() == newship) {
            newshipinc = 1;
            yendcords1.setLocation((int) (xcords[1] + (ship.getIconWidth() / 2)), (int) ycords[1]);
            yendcords2.setLocation((int) (xcords[2] + (ship.getIconWidth() / 2)), (int) (ycords[2] + 100));
            isalive[1] = 1;
            isalive[2] = 1;
            isalive[3] = 1;
        }

        hitbeem.setLocation(xbeem, ybeem[1]);
        hitbeem2.setLocation(xbeem, ybeem[2]);
        if(xbeem < (xend + 20)){
            xbeem = x;
        }
        Timer = Timer +1;
        repaint();
    }
    }