package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJ_IRON_PICKAXE extends LivingEntity {
    public OBJ_IRON_PICKAXE(MainGame gpp) {
        super(gpp);
        name = "Iron pickaxe";
        AttackValue = 2;
        Value = 3;
        attackHitbox.width = 30;
        attackHitbox.height = 36;
        EntityType = 3;
        LightSource = false;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        Type = Type_pickaxe;
        frames = 3;
        description = "A good pickaxe, thats it";
        getImageInstance();
    }
    @Override
    public void update() {}
    public LivingEntity replicate() {
        return new OBJ_IRON_PICKAXE(gp);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/iron_pickaxe", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
}
