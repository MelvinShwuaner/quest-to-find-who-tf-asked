package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJ_SHIELD_DIAMOND extends LivingEntity {
    public OBJ_SHIELD_DIAMOND(MainGame gpp) {
        super(gpp);
        name = "Diamond Shield";
        getImageInstance();
        defenceValue = 4;
        Value = 1;
        description = "a "+name+" that is created in \ndark forges of \namogus town";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        LightSource = false;
    }
    @Override
    public void update() {}
    @Override
    public LivingEntity replicate() {
        return new OBJ_SHIELD_DIAMOND(gp);
    }
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/shield_diamond", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
}
