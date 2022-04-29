package net.questfor.thepersonwhoasked.entities.Mobs;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;

public class green_slime extends LivingEntity {
    public green_slime(MainGame gpp) {
        super(gpp);
        EntityType = 2;
        name = "Green Slime";
        speed = 2;
        maxhealth = 8;
        health = maxhealth;
        hitbox = new Rectangle(3, 18, 42, 30);
        hitbox.height = 30;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        getImageInstance();
    }

    public void getImageInstance() {
        up1 = BufferedRenderer("Monsters/greenslime_down_1", gp.tilesize, gp.tilesize);
        up2 = BufferedRenderer("Monsters/greenslime_down_2", gp.tilesize, gp.tilesize);
        down1 = BufferedRenderer("Monsters/greenslime_down_1", gp.tilesize, gp.tilesize);
        down2 = BufferedRenderer("Monsters/greenslime_down_2", gp.tilesize, gp.tilesize);
        right1 = BufferedRenderer("Monsters/greenslime_down_1", gp.tilesize, gp.tilesize);
        right2 = BufferedRenderer("Monsters/greenslime_down_2", gp.tilesize, gp.tilesize);
        left1 = BufferedRenderer("Monsters/greenslime_down_1", gp.tilesize, gp.tilesize);
        left2 = BufferedRenderer("Monsters/greenslime_down_2", gp.tilesize, gp.tilesize);
    }
    public void setAction(){
        if(!Hostile) {
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
                if (I > 75 && I <= 100) {
                    direction = "right";
                }
                actionLock = 0;
            }
        }else{
            Angry();
        }
    }
    public void Angry() {
        actionLock++;
        Random random = new Random();
        int I = random.nextInt(100) + 1;
        if (actionLock == 30) {
            if(I > 50) {
                if (worldy < MainGame.player.worldy) {
                    direction = "down";
                } else {
                    direction = "up";
                }
            }
            if(I < 50){
                if (worldx < MainGame.player.worldx) {
                    direction = "right";
                } else {
                    direction = "left";
                }
            }

            actionLock = 0;
        }

    }
}

