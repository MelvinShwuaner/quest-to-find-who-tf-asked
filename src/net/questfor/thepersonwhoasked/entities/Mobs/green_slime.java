package net.questfor.thepersonwhoasked.entities.Mobs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import java.awt.*;
import java.util.*;
public class green_slime extends LivingEntity {


    public green_slime(MainGame gpp) {
        super(gpp);
        EntityType = 2;
        name = "Green Slime";
        speed = 1;
        maxhealth = 8;
        health = maxhealth;
        hitbox = new Rectangle();
        hitbox.x = 3;
        hitbox.y = 18;
        hitbox.width = 42;
        hitbox.height = 30;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;

        getImageInstance();

    }
    public void getImageInstance(){
        up1 = BufferedRenderer("Monsters/greenslime_down_1");
        up2 = BufferedRenderer("Monsters/greenslime_down_2");
        down1 = BufferedRenderer("Monsters/greenslime_down_1");
        down2 = BufferedRenderer("Monsters/greenslime_down_2");
        right1 = BufferedRenderer("Monsters/greenslime_down_1");
        right2 = BufferedRenderer("Monsters/greenslime_down_2");
        left1 = BufferedRenderer("Monsters/greenslime_down_1");
        left2 = BufferedRenderer("Monsters/greenslime_down_2");
    }
    public void setAction(){
        actionLock++;
        Random random = new Random();
        int I = random.nextInt(100) + 1;
        if (actionLock == 120) {
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