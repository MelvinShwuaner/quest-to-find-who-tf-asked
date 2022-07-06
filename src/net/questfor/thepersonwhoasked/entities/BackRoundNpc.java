package net.questfor.thepersonwhoasked.entities;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class BackRoundNpc extends LivingEntity {
boolean merchant;

    public BackRoundNpc(MainGame gpp, int speedd, String image, int worldxx, int worldyy, int worldzz, int taskxx, int taskyy, int healthh, int defencee, boolean inventoryy, ArrayList<LivingEntity> inventoryyy, boolean merchantt, boolean onpathh, String[] dialoguess) {
        super(gpp);
        direction = "up";
        defaultspeed = speedd;
        speed = defaultspeed;
        hitbox = new Rectangle();
        hitbox.x = 8;
        hitbox.y = 16;
        hitbox.width = 32;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        getImageInstance(image);
        EntityType = 2;
        maxhealth = healthh;
        defence = defencee;
        health = maxhealth;
        forgiveondeath = false;
        name = image;
        taskx = taskxx;
        tasky = taskyy;
        if(inventoryy){
            inventory = inventoryyy;
        }
        merchant = merchantt;
        onpath = onpathh;
        dialogues = dialoguess;

        worldx = worldxx;
        worldy = worldyy;
        worldz = worldzz;
    }

    public void getImageInstance(String image) {
        up1 = BufferedRenderer("BackRoundNpc/"+image+"_up_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        up2 = BufferedRenderer("BackRoundNpc/"+image+"_up_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down1 = BufferedRenderer("BackRoundNpc/"+image+"_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down2 = BufferedRenderer("BackRoundNpc/"+image+"_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right1 = BufferedRenderer("BackRoundNpc/"+image+"_right_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right2 = BufferedRenderer("BackRoundNpc/"+image+"_right_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left1 = BufferedRenderer("BackRoundNpc/"+image+"_left_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left2 = BufferedRenderer("BackRoundNpc/"+image+"_left_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }

    public void setAction() {
        if(onpath) {
            searchPath(taskx, tasky);
        }else {
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
    public void speak(){
        super.speak();
        if(merchant){
            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.tradestate;
            UI.npc = this;
        }
        }
    }
