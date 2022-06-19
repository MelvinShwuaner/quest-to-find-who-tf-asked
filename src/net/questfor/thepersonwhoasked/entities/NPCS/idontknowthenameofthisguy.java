package net.questfor.thepersonwhoasked.entities.NPCS;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;

public class idontknowthenameofthisguy extends LivingEntity {
    boolean player;
    public idontknowthenameofthisguy(MainGame gpp) {
        super(gpp);
        direction = "right";
        defaultspeed = 1;
        speed = defaultspeed;
        hitbox = new Rectangle(0, 0, 46, 46);
        getImageInstance();
        setDialogues();
        EntityType = 2;
        maxhealth = 15;
        defence = 3;
        health = maxhealth;
        forgiveondeath = false;
        name = "idontknowthenameofthisguy";
    }

    public void getImageInstance() {
        up1 = BufferedRenderer("NPCS/old man/oldman_up_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        up2 = BufferedRenderer("NPCS/old man/oldman_up_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down1 = BufferedRenderer("NPCS/old man/oldman_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down2 = BufferedRenderer("NPCS/old man/oldman_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right1 = BufferedRenderer("NPCS/old man/oldman_right_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right2 = BufferedRenderer("NPCS/old man/oldman_right_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left1 = BufferedRenderer("NPCS/old man/oldman_left_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left2 = BufferedRenderer("NPCS/old man/oldman_left_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }

    public void setAction() {


            }




    public void setDialogues(){
        dialogues[0] = name+": Hello Bob!";
        dialogues[1] = name+": waht you saw on the news is real! \nthe world is ending";
        dialogues[2] = name+": my team is trying to find that damn dream stan";
        dialogues[3] = name+": and we are looking for someone new, \nwould you like to join?";
        dialogues[4] = "You: ehhh im not sure";
        dialogues[5] = name+": i will pay you 10 bobux! is that fair?";
        dialogues[6] = "You: i just want to leave i \nam not interested in it";
        dialogues[7] = name+": ok then, Your loss!";


    }
    public void speak(){
        super.speak();
        }
    }
