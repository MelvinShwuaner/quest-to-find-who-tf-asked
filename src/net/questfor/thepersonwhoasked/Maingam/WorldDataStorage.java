package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Player;

public class WorldDataStorage extends Data {
    LivingEntity NPCS[][];
    private static final long serialVersionUID = -694201445L;

    LivingEntity Monsters[][];

    LivingEntity obj[][];
    int raidcount;
    Player player;
    int mapdata[][][][];
    int currentmap;

}
