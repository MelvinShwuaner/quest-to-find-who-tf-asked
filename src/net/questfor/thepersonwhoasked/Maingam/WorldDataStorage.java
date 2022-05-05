package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.tile_entites.TileEntity;

import java.io.Serializable;

public class WorldDataStorage implements Serializable {
    LivingEntity NPCS[];
    LivingEntity Monsters[];
    TileEntity TileEntitys[];

}
