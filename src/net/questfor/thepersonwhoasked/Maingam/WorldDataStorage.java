package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Player;
import net.questfor.thepersonwhoasked.tile_entites.TileEntity;
public class WorldDataStorage extends Data {
    LivingEntity NPCS[][];
    private static final long serialVersionUID = -694201445L;

    LivingEntity Monsters[][];
    TileEntity TileEntitys[][];
    LivingEntity obj[][];
    int raidcount;
    Player player;
    int mapdata[][][][];
    int currentmap;

}
