package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJ_BRICK_WALL extends LivingEntity {
    public OBJ_BRICK_WALL(MainGame gpp) {
        super(gpp);
        name = "BRIC WALL";
        getImageInstance();
        defenceValue = 4;
        Value = 1;
        description = "a "+name+" that is created for \nwalls, made in the forges of \namogus town";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/brickwallbroken", gp.tilesize, gp.tilesize);
    }
}
