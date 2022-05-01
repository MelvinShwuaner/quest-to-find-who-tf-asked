package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;

public class Projectile extends LivingEntity{
    LivingEntity SourceEntity;
    public Projectile(MainGame gpp) {
        super(gpp);

    }
    public void Set(int worldx, int worldy, String direction, boolean alive, LivingEntity sourceentity){
        this.worldx = worldx;
        this.worldy = worldy;
        this.alive = alive;
        this.SourceEntity = sourceentity;
        this.health = this.maxhealth;
        this.direction = direction;
    }
    public void update(){
        if(SourceEntity == gp.player){
            int monsterindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
            if(monsterindex != 999){
                gp.player.attackEntity(monsterindex, TrueAttackDamage);
                alive = false;
            }
        }if(SourceEntity != gp.player){
            boolean contactPlayer = gp.hregister.PlayerColide(this);
            if(!gp.player.invincible && contactPlayer){
                AttackPLayer(TrueAttackDamage);
                alive = false;
            }
        }

        switch (direction){
            case "up": worldy -= speed; break;
            case "down": worldy += speed; break;
            case "left": worldx -= speed; break;
            case "right": worldx += speed; break;
        }
        health--;
        if(health <= 0){
            alive = false;
        }
        spritecounter++;
        if(spritecounter > 12){
            if(spritenumber == 1){
                spritenumber = 2;
            }
            else if(spritenumber == 2){
                spritenumber = 1;
            }
            spritecounter = 0;
        }
        switch (direction) {
            case "up":
                if (spritenumber == 1) {
                    this.image = up1;
                } else if (spritenumber == 2) {
                    this.image = up2;
                }
                break;
            case "down":
                if (spritenumber == 1) {
                    image = down1;
                } else if (spritenumber == 2) {
                    image = down2;
                }
                break;
            case "right":
                if (spritenumber == 1) {
                    image = right1;
                } else if (spritenumber == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spritenumber == 1) {
                    image = left1;
                } else if (spritenumber == 2) {
                    image = left2;
                }
                break;
        }
    }
}
