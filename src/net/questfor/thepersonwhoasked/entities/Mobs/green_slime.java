package net.questfor.thepersonwhoasked.entities.Mobs;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.OBJHeart;
import net.questfor.thepersonwhoasked.objects.OBJ_COIN_BRONZE;
import net.questfor.thepersonwhoasked.objects.OBJ_MANA_CRYSTAL;
import net.questfor.thepersonwhoasked.objects.Projectiles.OBJ_ROCK;

import java.awt.*;
import java.util.Random;
public class green_slime extends LivingEntity {
    public green_slime(MainGame gpp) {
        super(gpp);
        EntityType = 1;
        name = "Green Slime";
        defaultspeed = 3;
        speed = defaultspeed;
        maxhealth = 10;
        TrueAttackDamage = 5;
        defence = 1;
        health = maxhealth;
        hitbox = new Rectangle(3, 18, 42, 30);
        hitbox.height = 30;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        XP = 7;
        level = 5;
        projectile = new OBJ_ROCK(gpp);
        forgiveondeath = false;
        target = GlobalGameThreadConfigs.player;
        LightSource = false;
    }
    public void getImageInstance() {
        up1 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        up2 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down1 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down2 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right1 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right2 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left1 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left2 = BufferedRenderer("Monsters/greenslime/tier1/greenslime_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    public void setAction(){
        if(up1 == null){
            getImageInstance();
        }
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

        if(target != null) {
            taskx = Math.round(target.worldx/GlobalGameThreadConfigs.tilesize);
            tasky = Math.round(target.worldy/GlobalGameThreadConfigs.tilesize);
            searchPath(taskx, tasky);
            int id = new Random().nextInt(100) + 1;

            if (id > 70 && !projectile.alive && primepowercool == 30) {
                projectile.Set((int) worldx, (int) worldy, (int) worldz, direction, true, this);
                for(int i = 0; i< GlobalGameThreadConfigs.projectilelist[1].length; i++){
                    if(GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] == null){
                        GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] = projectile;
                        break;
                    }
                }
                primepowercool = 0;
            }
                    }else{
            Hostile = false;
        }
    }
    public void HandleItems(){
        int I = new Random().nextInt(100)+1;
        if(I < 50){
            DropItems(new OBJ_MANA_CRYSTAL(gp));
            DropItems(new OBJHeart(gp));
        }else {
            DropItems(new OBJ_COIN_BRONZE(gp));
        }
    }
    public Color getparticleColor(){return new Color(0x388545);}
    public int getparticleSize(){return 11;}
    public int getparticlespeed(){return 1;}
    public int getparticleMaxHealth(){return 23;}
}

