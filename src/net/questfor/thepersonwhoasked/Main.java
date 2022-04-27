package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalDataStorage;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.swing.*;


public abstract class Main{
    public static MainGame mainGame = new MainGame();
    public static JFrame window = new JFrame();
    public static GlobalDataStorage globalDataStorage = new GlobalDataStorage();
    public static ImageIcon urmom = new ImageIcon(Main.class.getClassLoader().getResource("shipgame/logo.png"));
    public static void main(String[] args) {
        try {
            window.setResizable(false);
            window.setTitle("the quest to find out who asked");
            window.setIconImage(urmom.getImage());
            window.add(mainGame);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            MainGame.setupOBJ();
            mainGame.startgamethread();
        }catch(Exception e){
            crash.main(e);
        }
    }

    }