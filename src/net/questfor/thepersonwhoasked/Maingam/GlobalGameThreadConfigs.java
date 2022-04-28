package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.util.ArrayList;

public class GlobalGameThreadConfigs {
    //sets default configs that configure the main game
    public static Thread gameThread = null;
    public static int GameState;
    public static boolean isinTital = true;
    public static final int PlayState = 1;
    public final static int pauseState= 2;
    public final static int dialogueState = 3;
    public final static LivingEntity NPCS[] = new LivingEntity[10];
    public static String worldID = "/maps/worldV2.txt";
    public static ArrayList<LivingEntity> entitylist = new ArrayList<>();


}
