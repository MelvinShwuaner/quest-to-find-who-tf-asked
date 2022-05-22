package net.questfor.thepersonwhoasked;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.GlobalSaveManager;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.swing.*;
import java.io.*;
/*
just like its name, all classes and functions are built around this class.
it creates a new window to store all data inside
*/

public abstract class Main{
    public static MainGame mainGame = new MainGame();
    public static JFrame window;
    public static ByteArrayOutputStream baos = new ByteArrayOutputStream();
    public static PrintStream ps = new PrintStream(baos);
    public static BufferedWriter concole;

    static {
        try {
            concole = new BufferedWriter(new FileWriter("latestcrashreport.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon urmom = new ImageIcon(Main.class.getClassLoader().getResource("shipgame/logo.png"));
    public static void main(String[] args) {
        //System.setOut(ps);
        createnewwindow();
    }
    public static void createnewwindow(){
        try {
            window = new JFrame();
            window.setResizable(false);
            window.setTitle("the quest to find out who asked");
            window.setIconImage(urmom.getImage());


            //THE MOST IMPORTANT LINE IN THIS ENTIRE GAME //

            window.add(mainGame); //<------//

            //------------------------------------//

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            MainGame.setupOBJ();
            mainGame.startgamethread();
            mainGame.MultiRender.setScreenRenderer();
            GlobalSaveManager.loadconfigs();
            mainGame.playmusic(0);
            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
            System.out.println("Successfully created new window");
        }catch(Exception e){
            crash.main(e);
        }
    }
}