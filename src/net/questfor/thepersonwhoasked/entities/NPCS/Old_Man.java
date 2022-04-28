package net.questfor.thepersonwhoasked.entities.NPCS;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;

public class Old_Man extends LivingEntity {
    public Old_Man(MainGame gpp) {
        super(gpp);
        direction = "down";
        speed = 1;
        hitbox = new Rectangle(0, 0, 46, 46);
        getImageInstance();
        setDialogues();
        EntityType = 1;

    }
    public void getImageInstance() {
        up1 = BufferedRenderer("NPCS/old man/oldman_up_1");
        up2 = BufferedRenderer("NPCS/old man/oldman_up_2");
        down1 = BufferedRenderer("NPCS/old man/oldman_down_1");
        down2 = BufferedRenderer("NPCS/old man/oldman_down_2");
        right1 = BufferedRenderer("NPCS/old man/oldman_right_1");
        right2 = BufferedRenderer("NPCS/old man/oldman_right_2");
        left1 = BufferedRenderer("NPCS/old man/oldman_left_1");
        left2 = BufferedRenderer("NPCS/old man/oldman_left_2");
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
            if (I > 75 && I <= 100) {
                direction = "right";
            }
            actionLock = 0;
        }
    }

    public void setDialogues(){
        dialogues[0] = "Hello!";
        dialogues[1] = "please go away from me \nor i shall spank you!";
        dialogues[2] = "hello.. young man";
        dialogues[3] = "can you get some glow \nberries for me pls?";

    }
    public void speak(){
        super.speak();
        }
    }
