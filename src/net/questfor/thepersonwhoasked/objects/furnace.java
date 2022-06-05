package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Objects;

public class furnace extends LivingEntity {
    public furnace(MainGame gp, int col, int row){
        super(gp);
        name = "furnace";
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "a slow rusty furnace, is really hot!";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 48;
        hitbox.height = 48;
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
        worldz = 5;
        worldx = col*gp.tilesize;
        worldy = row*gp.tilesize;
        NBTDATA = true;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/furnace", gp.tilesize, gp.tilesize);
        down2 = BufferedRenderer("objects/furnace_on", gp.tilesize, gp.tilesize);
    }
    public void open(){
        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.UIstate;
        UI.npc = this;
        UI.currentUI = "Furnace";
    }
    @Override
    public void update() {
        if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] == 46){
            gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] = 47;
        }
        if(smelting){
            increasecool();
            spritenumber = 2;
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
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                gp.player.gp.playsound(10);
            }
        }
    }
}