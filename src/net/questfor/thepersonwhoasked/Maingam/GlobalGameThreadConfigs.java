package net.questfor.thepersonwhoasked.Maingam;
public class GlobalGameThreadConfigs extends Data {
    //sets default configs that configure the main game
    public static Thread gameThread = null;
    public static int GameState;
    public static boolean isinTital = true;
    public static boolean CharacterStats;
    public static int PlayState = 1;
    public static int pauseState = 2;
    public static int dialogueState = 3;
    public static int optionsstate = 4;
    public static int GameOverState = 5;
    public static int transitionstate = 6;
    public static int tradestate = 7;
    public static int UIstate = 8;
    public static boolean inchest = false;
    public static int animationcounter = 0;
    public static int animationnumber = 0;

    public static net.questfor.thepersonwhoasked.entities.LivingEntity[][] Monsters = new net.questfor.thepersonwhoasked.entities.LivingEntity[MainGame.maxmap][20];
    public static net.questfor.thepersonwhoasked.entities.LivingEntity[][] NPCS = new net.questfor.thepersonwhoasked.entities.LivingEntity[MainGame.maxmap][10];
    public static Recipe[] Recipes = new Recipe[20];
    public static String filepath;
    public static net.questfor.thepersonwhoasked.entities.LivingEntity[][] obj = new net.questfor.thepersonwhoasked.entities.LivingEntity[MainGame.maxmap][100];
    public static java.util.ArrayList<net.questfor.thepersonwhoasked.entities.LivingEntity> entitylist = new java.util.ArrayList<>();
    public static java.util.ArrayList<net.questfor.thepersonwhoasked.entities.LivingEntity> particleList = new java.util.ArrayList<>();
    public static net.questfor.thepersonwhoasked.World.Light[][] lights = new net.questfor.thepersonwhoasked.World.Light[MainGame.maxmap][100];
    public static net.questfor.thepersonwhoasked.World.enviormentmanager Emanager = new net.questfor.thepersonwhoasked.World.enviormentmanager(net.questfor.thepersonwhoasked.GlobalProperties.gp);
    public static int LightLevel = 247;
    public static boolean dark = false;
    public static net.questfor.thepersonwhoasked.entities.LivingEntity[][] projectilelist = new net.questfor.thepersonwhoasked.entities.LivingEntity[MainGame.maxmap][20];
    public static java.awt.image.BufferedImage tempscreen;

    public static java.awt.Graphics2D g2;
    public static java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static java.awt.GraphicsDevice gd = ge.getDefaultScreenDevice();
    public static boolean Buildmode = true;
    public static int tilesize = 48;
    public static net.questfor.thepersonwhoasked.entities.Player player = new net.questfor.thepersonwhoasked.entities.Player(net.questfor.thepersonwhoasked.GlobalProperties.gp);
}