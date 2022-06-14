package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class nulitem extends LivingEntity {
    public nulitem(MainGame gp, int tilel, String name){
        super(gp);
        this.name = name;
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
        LightSource = false;
        tile = tilel;
    }
    @Override
    public void update() {}
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/stone", gp.tilesize-2, gp.tilesize-5);
    }
}
