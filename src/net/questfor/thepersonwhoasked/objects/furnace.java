package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Objects;

public class furnace extends LivingEntity {
    public furnace(MainGame gp, int col, int row, double layer){
        super(gp);
        name = "furnace";
        LightSource = false;
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "a slow rusty furnace, is really hot!";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = GlobalGameThreadConfigs.tilesize;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        maxstacksize = 16;
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        maxcool = 100;

        worldx = col*GlobalGameThreadConfigs.tilesize;
        worldy = row*GlobalGameThreadConfigs.tilesize;
        worldz = layer;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/furnace", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down2 = BufferedRenderer("objects/furnace_on", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    public void open(int x, int y, int z, int i){
        if(KeyHandler.enterpressed){
        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.UIstate;
        UI.npc = this;
        UI.currentUI = "Furnace";
    }}
    @Override
    public void update() {
        if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] == 46){
            gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] = 47;
        }
        if(smelting){
            increasecool();
            spritenumber = 2;
            LightSource = true;
        }
        if (hasfinushedcol == 1) {
            if (inventory.get(5) == null) {
                if (inventory.get(0) != null) {
                    if (inventory.get(6) != null) {
                        inventory.get(6).stacksize--;
                        if (inventory.get(6).stacksize <= 0) {
                            inventory.set(6, null);
                            smelting = false;
                            spritenumber = 1;
                            LightSource = false;
                        }
                        LivingEntity Source = inventory.get(0).Outcome;
                        inventory.set(5, Source);
                        cool = 0;
                        hasfinushedcol = 2;
                    }

                    inventory.get(0).stacksize--;
                    if (inventory.get(0).stacksize <= 0) {
                        inventory.set(0, null);
                        smelting = false;
                        spritenumber = 1;

                    }
                }
            } else {
                if (inventory.get(0) != null) {
                    if (inventory.get(6) != null) {
                        LivingEntity Source = inventory.get(0).Outcome;
                        if (Objects.equals(inventory.get(5).name, Source.name)) {
                            inventory.get(5).stacksize++;
                            cool = 0;
                            hasfinushedcol = 2;
                            inventory.get(6).stacksize--;
                            if (inventory.get(6).stacksize <= 0) {
                                inventory.set(6, null);
                                smelting = false;
                                spritenumber = 1;
                            }
                            inventory.get(0).stacksize--;
                            if (inventory.get(0).stacksize <= 0) {
                                inventory.set(0, null);
                                smelting = false;
                                spritenumber = 1;
                            }
                        }
                    }
                }
            }
            if ((worldx + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldx - GlobalGameThreadConfigs.player.screenX &&
                    (worldx - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX))
                    && worldy + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldy - GlobalGameThreadConfigs.player.screenY &&
                    (worldy - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY)) {
                GlobalGameThreadConfigs.player.gp.playsound(10);
            }
        }
    }

    @Override
    public boolean ItemRequirements(LivingEntity SourceEntity) {
        return true;
    }
    @Override
    public LivingEntity replicate() {
        return new furnace(gp, 0, 0, 0);
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
        canplace = (gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z));
        if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)){
            switch (direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
            canplace = gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z);
        }
        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)+1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)-1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z))) {
            GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new furnace(gp, (int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), z);
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