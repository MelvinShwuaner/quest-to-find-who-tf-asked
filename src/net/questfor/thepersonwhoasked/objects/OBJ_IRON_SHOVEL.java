package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJ_IRON_SHOVEL extends LivingEntity {
    public OBJ_IRON_SHOVEL(MainGame gpp) {
        super(gpp);
        name = "Iron shovel";
        AttackValue = 2;
        attackHitbox.width = 30;
        attackHitbox.height = 36;
        EntityType = 3;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        Type = Type_axe;
        frames = 3;
        description = "A good shovel to dig up tunnels through \nbad times.";
        getImageInstance();
    }
    @Override
    public void update() {}
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/shovel", gp.tilesize, gp.tilesize);
    }
}
