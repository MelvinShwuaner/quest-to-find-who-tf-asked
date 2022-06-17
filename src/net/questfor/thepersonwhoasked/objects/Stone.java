package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
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
        tile = 50;
    }
    @Override
    public void update() {}
    public LivingEntity replicate() {
        return new Stone(gp);
    }
    @Override
    public void Place(double x, double y, double z, int i) {
        boolean canplace;
        if(!KeyHandler.CROUCH && !KeyHandler.sprint) {
            switch (direction) {
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
        canplace = (gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z));
        if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)) {
            switch (direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
            canplace = gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z, GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z);
        }
        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)+1, (int) Math.round(y / gp.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)-1, (int) Math.round(y / gp.tilesize), (int) z))) {
            gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(x/gp.tilesize)][(int) Math.round(y/gp.tilesize)][(int) z] = tile;
            if(!GlobalGameThreadConfigs.Buildmode){
                gp.player.currentshield.stacksize--;
                if (gp.player.currentshield.stacksize <= 0) {
                    gp.player.inventory.remove(gp.player.currentshield);
                    gp.player.currentshield = null;
                }
            }
        }
    }
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/stone", gp.tilesize-2, gp.tilesize-5);
    }
}
