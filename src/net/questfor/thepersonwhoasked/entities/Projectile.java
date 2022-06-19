package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;

public class Projectile extends LivingEntity{
    protected LivingEntity SourceEntity;
    public Projectile(MainGame gpp) {
        super(gpp);

    }
    public void Set(int worldx, int worldy, int worldz, String direction, boolean alive, LivingEntity sourceentity) {
        this.worldx = worldx;
        this.worldy = worldy;
        this.worldz = worldz;
        this.alive = alive;
        this.SourceEntity = sourceentity;
        this.health = this.maxhealth;
        this.direction = direction;
    }
    public void update(){
        if(up1 == null){
            getImageInstance();
        }
        if(SourceEntity == GlobalGameThreadConfigs.player || SourceEntity.EntityType == 2){
            int monsterindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
            int projectileI = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.projectilelist);
            super.damageprojectile(projectileI);
            if(projectileI != 999){
                alive = false;
            }
            if(monsterindex != 999){
                attackEntity(monsterindex, AttackValue);
                ParticlePropertyManager(SourceEntity.projectile, GlobalGameThreadConfigs.Monsters[MainGame.currentmap][monsterindex]);
                alive = false;
            }
        }if(SourceEntity != GlobalGameThreadConfigs.player && SourceEntity.EntityType == 1){
            boolean contactPlayer = gp.hregister.PlayerColide(this);
            if(!GlobalGameThreadConfigs.player.invincible && contactPlayer){
                ParticlePropertyManager(SourceEntity.projectile, GlobalGameThreadConfigs.player);
                AttackPLayer(AttackValue);
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
    public void attackEntity(int attackindex, int dmg) {
        if (attackindex != 999) {
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible == false) {
                gp.playsound(6);
                int damage = dmg - GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].defence;
                if (damage < 0) {
                    damage = 0;
                }
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health -= damage;
                ParticleAttackManager( this, GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex]);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].makemeHostile(SourceEntity);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].HostileTime = 0;
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible = true;
            }
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying == false) {
                if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health < 0) {
                    GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying = true;
                    UI.addMessages("Killed " + GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].name);
                    XP += GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].XP;
                    levelUpAchiver();
                }
            }
        }
    }
    public boolean haveresource(LivingEntity sourceEntity){boolean haveresource = false;return haveresource;}
    public void RemoveResource(LivingEntity source){}
}
