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
        hitbox.y = 0;
        hitbox.width = 48;
        hitbox.height = 48;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        maxstacksize = 16;
        worldz = 4;
        NBTDATA =true;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/chest", gp.tilesize, gp.tilesize);
    }
    @Override
    public void update() {if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] == 46){
        gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] = 47;
    }
    }

    @Override
    public boolean ItemRequirements(LivingEntity SourceEntity) {
        return SourceEntity.currentweapon.Type == Type_axe;
    }
}
