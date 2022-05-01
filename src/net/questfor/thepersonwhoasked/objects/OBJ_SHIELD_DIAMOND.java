package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJ_SHIELD_DIAMOND extends LivingEntity {
    public OBJ_SHIELD_DIAMOND(MainGame gpp) {
        super(gpp);
        name = "Diamond Shield";
        down1 = BufferedRenderer("objects/shield_diamond", gp.tilesize, gp.tilesize);
        defenceValue = 4;
        Value = 1;
        description = "a "+name+" that is created in \ndark forges of \namogus town";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
    }
}
