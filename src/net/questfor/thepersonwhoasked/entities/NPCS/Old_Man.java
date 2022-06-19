package net.questfor.thepersonwhoasked.entities.NPCS;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class Old_Man extends LivingEntity {
    boolean player;
    public Old_Man(MainGame gpp) {
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
        forgiveondeath = false;
        name = "Old man";
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

                if(onpath){
                    if(goingup){
                        taskx = Math.round(1107/GlobalGameThreadConfigs.tilesize);
                        tasky = Math.round(588/GlobalGameThreadConfigs.tilesize);
                        goup();
                    }else if(!Hostile){
                        tasky = Math.round(1932/GlobalGameThreadConfigs.tilesize);
                        taskx = Math.round(1107/GlobalGameThreadConfigs.tilesize);
                        searchPath(taskx, tasky);
                    }else{
                    taskx = Math.round(target.worldx/GlobalGameThreadConfigs.tilesize);
                    tasky = Math.round(target.worldy/GlobalGameThreadConfigs.tilesize);
                    searchPath(taskx, tasky);
                }}else {
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
            }


    public void goup() {
        searchPath(taskx, tasky);
        if(Math.round(worldx/GlobalGameThreadConfigs.tilesize) == taskx && Math.round(worldy/GlobalGameThreadConfigs.tilesize) == tasky){
                if (speed > 0) {
                    dialogues[0] = "Take a wish. will you?";
                    dialogues[2] = ""; dialogues[3] = "";
                    speed = 0;
                }}
    }


    public void setDialogues(){
        dialogues[0] = "Hello!";
        dialogues[1] = "please go away from me \nor i shall spank you!";
        dialogues[2] = "hello.. young man";
        dialogues[3] = "can you get some glow \nberries for me pls?";

    }
    public void speak(){
        super.speak();
        onpath = true;
        Hostile = true;
        target = GlobalGameThreadConfigs.player;
        }
    }
