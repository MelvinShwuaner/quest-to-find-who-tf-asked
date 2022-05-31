package net.questfor.thepersonwhoasked.Maingam;
import net.questfor.thepersonwhoasked.entities.*;
import net.questfor.thepersonwhoasked.tile_entites.*;
public class WorldDataStorage extends Data {
    LivingEntity NPCS[][];
    private static final long serialVersionUID = -694201445L;

    LivingEntity Monsters[][];
    TileEntity TileEntitys[][];
    LivingEntity obj[][];
    int raidcount;
    net.questfor.thepersonwhoasked.entities.Player Player;
    int mapdata[][][][];
    int currentmap;
}
