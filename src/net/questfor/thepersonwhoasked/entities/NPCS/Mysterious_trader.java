package net.questfor.thepersonwhoasked.entities.NPCS;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;

public class Mysterious_trader extends LivingEntity {
    public Mysterious_trader(MainGame gpp) {
        super(gpp);
        direction = "down";
        defaultspeed = 1;
        speed = defaultspeed;
        hitbox = new Rectangle(0, 0, 46, 46);
        getImageInstance();
        setDialogues();
        EntityType = 2;
        maxhealth = 15;
        defence = 3;
        health = maxhealth;

    }

    public void getImageInstance() {
        up1 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        up2 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down1 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down2 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right1 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right2 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left1 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left2 = BufferedRenderer("NPCS/Mysterious Trader/mysterious_trader_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }

    public void setAction() {

                actionLock++;
                if (actionLock == 120) {
                    Random random = new Random();
                    int I = random.nextInt(100) + 1;
                    if (I <= 25) {
                        direction = "up";
                    }
                    if (I > 25 && I <= 50) {
                        direction = "down";
                    }
                    if (I > 50 && I <= 75) {
                        direction = "left";
                    }
                    if (I > 75) {
                        direction = "right";
                    }
                    actionLock = 0;
                }
            }




    public void setDialogues(){
        dialogues[0] = "Hello!"+"i see you have found me... \n"+"so... what would you like?";
    }
    public void speak(){
        super.speak();
        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.tradestate;
        UI.npc = this;
    }
}
