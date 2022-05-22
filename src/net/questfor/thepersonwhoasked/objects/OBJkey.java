package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJkey extends LivingEntity {
    public OBJkey(MainGame gp){
        super(gp);
        name = "key";
        getImageInstance();
        description = "a "+name+" that is always used in dungeons, \n always can pick a door";
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
        Outcome = new OBJ_POTION_HEALTH_1(gp);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/key", gp.tilesize, gp.tilesize);
    }
}
