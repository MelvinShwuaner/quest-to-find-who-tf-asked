package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJdoor extends LivingEntity {
    public OBJdoor(MainGame gp, int x, int y){
        super(gp);
        name = "door";
        collision = true;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 48;
        hitbox.height = 48;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 4;
        description = "how are you holding it?";
        getImageInstance();
        this.worldx = x * gp.tilesize;
        this.worldy = y * gp.tilesize;
        worldz = 4;
        NBTDATA = true;
    }
    @Override
    public void update() {if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] == 46){
        gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] = 47;
    }}
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/door", gp.tilesize, gp.tilesize);
    }
}
