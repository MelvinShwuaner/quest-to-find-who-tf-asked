package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;

public class GlobalGameThreadConfigs {
    public static Thread gameThread = null;
    public static int GameState;
    public static boolean isinTital = false;
    public static final int PlayState = 1;
    public final static int pauseState= 2;
    public final static int dialogueState = 3;
    public final static LivingEntity NPCS[] = new LivingEntity[10];
    public static String worldID = "/maps/worldV2.txt";


}
