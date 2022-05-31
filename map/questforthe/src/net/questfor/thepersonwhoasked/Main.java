package net.questfor.thepersonwhoasked;
import net.questfor.thepersonwhoasked.tile.Tilemanager;

import javax.swing.*;
import java.io.*;
/*
just like its name, all classes and functions are built around this class.
it creates a new window to store all data inside
*/

public abstract class Main{
    public static JFrame window;
    public static ByteArrayOutputStream baos = new ByteArrayOutputStream();
    public static Tilemanager tilemanager = new Tilemanager();
    public static BufferedWriter concole;
    public static int maxworldcol = 50;
    public static int maxworldrow = 50;
    public static int MaxLayer = 30;
    public static Map map = new Map();
    public static String filepath = "worldV0";

    static {
        try {
            concole = new BufferedWriter(new FileWriter("latestcrashreport.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        map.mapRendererID = new int[maxworldcol][maxworldrow][MaxLayer];
        tilemanager.set();
        GlobalSaveManager.saveplayerworlddata();
    }

}