package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;


public class OBJ_IRON_SWORD extends LivingEntity {

    public OBJ_IRON_SWORD(MainGame gpp) {
        super(gpp);
        name = "Iron sword";
        down1 = BufferedRenderer("objects/sword_normal", gpp.tilesize, gpp.tilesize);
        AttackValue = 2;
        Value = 3;
    }
}
