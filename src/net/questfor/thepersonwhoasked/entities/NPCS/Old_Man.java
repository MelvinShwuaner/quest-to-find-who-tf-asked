package net.questfor.thepersonwhoasked.entities.NPCS;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;

public class Old_Man extends LivingEntity {
    boolean player;
    public Old_Man(MainGame gpp) {
        super(gpp);
        direction = "down";
        speed = 1;
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
        up1 = BufferedRenderer("NPCS/old man/oldman_up_1", gp.tilesize, gp.tilesize);
        up2 = BufferedRenderer("NPCS/old man/oldman_up_2", gp.tilesize, gp.tilesize);
        down1 = BufferedRenderer("NPCS/old man/oldman_down_1", gp.tilesize, gp.tilesize);
        down2 = BufferedRenderer("NPCS/old man/oldman_down_2", gp.tilesize, gp.tilesize);
        right1 = BufferedRenderer("NPCS/old man/oldman_right_1", gp.tilesize, gp.tilesize);
        right2 = BufferedRenderer("NPCS/old man/oldman_right_2", gp.tilesize, gp.tilesize);
        left1 = BufferedRenderer("NPCS/old man/oldman_left_1", gp.tilesize, gp.tilesize);
        left2 = BufferedRenderer("NPCS/old man/oldman_left_2", gp.tilesize, gp.tilesize);
    }

    public void setAction() {
        if (!frozen){
                if(onpath){
                    if(goingup){
                        taskx = Math.round(1107/gp.tilesize);
                        tasky = Math.round(588/gp.tilesize);
                        goup();
                    }else if(!Hostile){
                        Scared();
                    }else{
                    taskx = Math.round(target.worldx/gp.tilesize);
                    tasky = Math.round(target.worldy/gp.tilesize);
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
            } else if (goingup) {
                goup();
            } else {
                Scared();
            }
    }


    public void goup() {
        searchPath(taskx, tasky);
                if (speed > 0) {
                    dialogues[0] = "Take a wish. will you?";
                    dialogues[2] = ""; dialogues[3] = "";
                    speed = 0;
                }
    }

    public void Scared() {
        actionLock++;
        Random random = new Random();
        int I = random.nextInt(100) + 1;
        if (actionLock > 30) {
            if(I > 50) {
                if (worldy < 1932) {
                    direction = "down";
                } else {
                    direction = "up";
                }
            }
            if(I < 50){
                if (worldx < 1107) {
                    direction = "right";
                } else {
                    direction = "left";
                }
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
        onpath = true;
        Hostile = true;
        target = gp.player;
        }
    }
