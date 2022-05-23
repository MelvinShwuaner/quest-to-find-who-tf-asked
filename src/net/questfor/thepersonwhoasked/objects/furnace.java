package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Objects;

public class furnace extends LivingEntity {
    public furnace(MainGame gp){
        super(gp);
        name = "furnace";
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "a slow rusty furnace, is really hot!";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
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
        if(smelting){
            increasecool();
            spritenumber = 2;
        }
        if (hasfinushedcol == 1) {
            if (inventory.get(6) == null) {
                if (inventory.get(0) != null) {
                    if (inventory.get(7) != null) {
                        inventory.get(7).stacksize--;
                        if (inventory.get(7).stacksize <= 0) {
                            inventory.set(7, null);
                        }
                        LivingEntity Source = inventory.get(0).Outcome;
                        inventory.set(6, Source);
                        cool = 0;
                        hasfinushedcol = 0;
                    }

                    inventory.get(0).stacksize--;
                    if (inventory.get(0).stacksize <= 0) {
                        inventory.set(0, null);
                        hasfinushedcol =2;
                        smelting = false;
                        spritenumber = 1;
                    }
                }
            }else{
                if (inventory.get(0) != null) {
                    if (inventory.get(7) != null) {
                        LivingEntity Source = inventory.get(0).Outcome;
                        if (Objects.equals(inventory.get(6).name, Source.name)){
                            inventory.get(6).stacksize++;
                            cool = 0;
                            hasfinushedcol = 0;
                            inventory.get(7).stacksize--;
                            if (inventory.get(7).stacksize <= 0) {
                                inventory.set(7, null);
                            }
                            inventory.get(0).stacksize--;
                            if (inventory.get(0).stacksize <= 0) {
                                inventory.set(0, null);
                                hasfinushedcol =2;
                                smelting = false;
                                spritenumber = 1;
                            }
                    }
                    }
                }
            }
            gp.player.gp.playsound(10);
        }
    }
}
