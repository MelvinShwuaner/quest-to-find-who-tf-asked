package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class Brick extends LivingEntity {
    public Brick(MainGame gpp) {
        super(gpp);
        name = "Brick";
        getImageInstance();
        description = "a "+name+" that is used for making walls";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        maxstacksize = 64;
    }
    @Override
    public void update() {}
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/brick", gp.tilesize, gp.tilesize);
    }
}
