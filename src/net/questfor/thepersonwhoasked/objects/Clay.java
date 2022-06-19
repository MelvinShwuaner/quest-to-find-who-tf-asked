package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class Clay extends LivingEntity {
    public Clay(MainGame gp){
        super(gp);
        name = "Clay";
        getImageInstance();
        description = "a "+name+" that is used for crafting hard items, \n smelt it first!";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        maxstacksize = 32;
        smeltable = true;
        Outcome = new Brick(gp);
    }
    @Override
    public void update() {}
    public LivingEntity replicate() {
        return new Clay(gp);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/clay", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
}
