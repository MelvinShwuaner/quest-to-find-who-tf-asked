package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJdooropen extends LivingEntity {
    public OBJdooropen(MainGame gp, int x, int y, double z){
        super(gp);
        name = "door open";
        collision = true;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 10;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 4;
        description = "how are you holding it?";
        getImageInstance();
        worldy =y;
        worldx = x;
        worldz = z;
        LightSource = false;
    }
    @Override
    public void update() {
        if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] == 47){
            gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] = 46;
        }
    }

    @Override
    public void open(int x, int y, int z, int i) {
        if(KeyHandler.enterpressed){
            gp.playsound(3);
            GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new OBJdoor(gp, (int) GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx/gp.tilesize, (int) GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy/gp.tilesize, GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz);
        }
    }

    public LivingEntity replicate() {
        return new OBJdooropen(gp, 0, 0, 0);
    }
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/dooropen", gp.tilesize, gp.tilesize);
    }
}
