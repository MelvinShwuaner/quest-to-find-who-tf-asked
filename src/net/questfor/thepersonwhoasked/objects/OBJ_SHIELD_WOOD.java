package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

public class OBJ_SHIELD_WOOD extends LivingEntity {
    public OBJ_SHIELD_WOOD(MainGame gpp) {
        super(gpp);
        name = "Wooden Shield";
        down1 = BufferedRenderer("objects/shield_wood", gp.tilesize, gp.tilesize);
        defenceValue = 1;
        Value = 1;
    }

}
