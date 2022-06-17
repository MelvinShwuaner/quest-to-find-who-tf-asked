package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class chest extends LivingEntity {
    public chest(MainGame gp, int col, int row, double z){
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
        worldz = z;
        worldx = col*gp.tilesize;
        worldy = row*gp.tilesize;
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
    public void Place(double x, double y, double z, int i){
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
        if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)){
            switch (direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
            canplace = gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z);
        }
        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)+1, (int) Math.round(y / gp.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)-1, (int) Math.round(y / gp.tilesize), (int) z))) {
            GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new chest(gp, (int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), z);
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
    public boolean ItemRequirements(LivingEntity SourceEntity) {
        return SourceEntity.currentweapon.Type == Type_axe;
    }
    @Override
    public LivingEntity replicate() {
        return new chest(gp, 0, 0, 0);
    }

    @Override
    public void open(int x, int y, int z, int i) {
        if (KeyHandler.enterpressed) {
            if (!GlobalGameThreadConfigs.inchest) {
                GlobalGameThreadConfigs.inchest = true;
                GlobalGameThreadConfigs.CharacterStats = true;

            } else {
                GlobalGameThreadConfigs.inchest = false;
                GlobalGameThreadConfigs.CharacterStats = false;
                UI.slotstate = false;
                if(UI.merger != null){
                    UI.merger = null;
                    UI.merging = false;
                }
                if(UI.mergerr != null){
                    UI.merging = false;
                    UI.mergerr = null;
                }
            }
        }
    }
}
