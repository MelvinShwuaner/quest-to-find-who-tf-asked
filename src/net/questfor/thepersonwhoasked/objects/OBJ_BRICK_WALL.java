package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJ_BRICK_WALL extends LivingEntity {
    public OBJ_BRICK_WALL(MainGame gpp) {
        super(gpp);
        LightSource = false;
        name = "BRIC WALL";
        getImageInstance();
        defenceValue = 4;
        Value = 1;
        description = "a "+name+" that is created for \nwalls, made in the forges of \namogus town";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        maxstacksize = 64;
        tile = GlobalGameThreadConfigs.tilesize;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/brickwall", GlobalGameThreadConfigs.tilesize-2, GlobalGameThreadConfigs.tilesize-5);
    }

    @Override
    public void update() {}
    @Override
    public LivingEntity replicate() {
        return new OBJ_BRICK_WALL(gp);
    }
    @Override
    public void Place(double x, double y, double z, int i) {
        boolean canplace;
        if(!KeyHandler.CROUCH && !KeyHandler.sprint) {
            switch (GlobalGameThreadConfigs.player.direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
        }else if(KeyHandler.CROUCH){
            z--;
        }else if(KeyHandler.sprint){
            z++;
        }
        canplace = (gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z));
        if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)) {
            switch (GlobalGameThreadConfigs.player.direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
            canplace = gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z, GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z);
        }
        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)+1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)-1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z))) {
            gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(x/GlobalGameThreadConfigs.tilesize)][(int) Math.round(y/GlobalGameThreadConfigs.tilesize)][(int) z] = tile;
            if(!GlobalGameThreadConfigs.Buildmode){
                GlobalGameThreadConfigs.player.currentshield.stacksize--;
                if (GlobalGameThreadConfigs.player.currentshield.stacksize <= 0) {
                    GlobalGameThreadConfigs.player.inventory.remove(GlobalGameThreadConfigs.player.currentshield);
                    GlobalGameThreadConfigs.player.currentshield = null;
                }
            }
        }
    }
}
