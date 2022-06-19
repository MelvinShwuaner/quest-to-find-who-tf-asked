package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;


public class OBJ_IRON_SWORD extends LivingEntity {

    public OBJ_IRON_SWORD(MainGame gpp) {
        super(gpp);
        name = "Iron sword";
        AttackValue = 2;
        Value = 3;
        description = "a "+name+" that is common amongst villages. \n has a attack dmg of 2 and Value of 3";
        attackHitbox.width = 36;
        attackHitbox.height = 36;
        hitbox = new Rectangle();
        LightSource = false;
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        Type = Type_sword;
        getImageInstance();

    }
    @Override
    public void update() {}
    @Override
    public LivingEntity replicate() {
        return new OBJ_IRON_SWORD(gp);
    }
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/sword_normal", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
}
