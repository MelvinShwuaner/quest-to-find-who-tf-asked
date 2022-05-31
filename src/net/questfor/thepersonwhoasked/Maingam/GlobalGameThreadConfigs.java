package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.tile_entites.TileEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GlobalGameThreadConfigs extends Data {
    //sets default configs that configure the main game
    public static Thread gameThread = null;
    public static int GameState;
    public static boolean isinTital = true;
    public static boolean CharacterStats;
    public  static int PlayState = 1;
    public  static int pauseState= 2;
    public  static int dialogueState = 3;
    public  static int optionsstate = 4;
    public static int GameOverState = 5;
    public static int transitionstate = 6;
    public static int tradestate = 7;
    public static int UIstate = 8;
    public static boolean inchest = false;

    public static LivingEntity[][] Monsters = new LivingEntity[MainGame.maxmap][20];
    public static LivingEntity[][] NPCS = new LivingEntity[MainGame.maxmap][10];
    public static Recipe[] Recipes = new Recipe[20];
    public static String filepath;
    public static TileEntity[][] Tentity = new TileEntity[MainGame.maxmap][100];
    public static LivingEntity[][] obj = new LivingEntity[MainGame.maxmap][100];
    public static ArrayList<LivingEntity> entitylist = new ArrayList<>();
    public static ArrayList<LivingEntity> particleList = new ArrayList<>();
    public static ArrayList<LivingEntity> projectilelist = new ArrayList<>();
    public static BufferedImage tempscreen;

    public static Graphics2D g2;
    public static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static GraphicsDevice gd = ge.getDefaultScreenDevice();
}
