package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class chest extends LivingEntity {
    public chest(MainGame gp){
        super(gp);
        name = "chest";
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "basic 16*16 chest. can store 10 items";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        maxstacksize = 16;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/chest", gp.tilesize, gp.tilesize);
    }
    @Override
    public void update() {}
}
