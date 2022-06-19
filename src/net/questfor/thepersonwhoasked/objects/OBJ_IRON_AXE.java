package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJ_IRON_AXE extends LivingEntity {
    public OBJ_IRON_AXE(MainGame gpp) {
        super(gpp);
        name = "WoodCutter's axe";
        AttackValue = 5;
        LightSource = false;
        attackHitbox.width = 30;
        attackHitbox.height = 36;
        EntityType = 3;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        Type = Type_axe;
        Value = 3;
        description = "Any WoodCutter would adore a sturdy \n rusty iron axe?";
        getImageInstance();
    }
    @Override
    public void update() {}
    @Override
    public LivingEntity replicate() {
        return new OBJ_IRON_AXE(gp);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/axe", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
}
