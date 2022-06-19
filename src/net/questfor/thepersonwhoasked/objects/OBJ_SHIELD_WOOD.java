package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJ_SHIELD_WOOD extends LivingEntity {
    public OBJ_SHIELD_WOOD(MainGame gpp) {
        super(gpp);
        name = "Wooden Shield";
        defenceValue = 1;
        getImageInstance();
        Value = 1;
        description = "a "+name+" that tho is weak, \n it still does its job";
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
        return new OBJ_SHIELD_WOOD(gp);
    }
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/shield_wood", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
}
