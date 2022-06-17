package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class Stone extends LivingEntity {
    public Stone(MainGame gp){
        super(gp);
        name = "Stone";
        getImageInstance();
        description = "a simple "+name;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        maxstacksize = 64;
        NBTDATA = false;
        tile = 50;
    }
    @Override
    public void update() {}
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/stone", gp.tilesize-2, gp.tilesize-5);
    }
}
