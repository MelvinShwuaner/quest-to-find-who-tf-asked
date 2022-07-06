package net.questfor.thepersonwhoasked.objects.Projectiles;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Projectile;

import java.awt.*;

public class Bullet extends Projectile {
    MainGame gp;
    public Bullet(MainGame gpp) {
        super(gpp);
        this.gp  = gpp;
        name = "Bullet";
        speed = 20;
        maxhealth = 200;
        health = maxhealth;
        AttackValue = 4;
        UseCost = 1;
        alive = false;
        getImageInstance();
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        Type = Type_projectile;
        hitboxdefaulty = hitbox.y;
    }
    public void getImageInstance(){
        up1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        up2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        down1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        down2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        left1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        left2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        right1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        right2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
    }
    public boolean haveresource(LivingEntity sourceEntity){
        return sourceEntity.Ammo >= UseCost;
    }
    public void RemoveResource(LivingEntity source){
        source.Ammo -= UseCost;
    }
    public Color getparticleColor(){return new Color(0x283200);}
    public int getparticleSize(){return 11;}
    public int getparticlespeed(){return 1;}
    public int getparticleMaxHealth(){return 23;}
    public void update(){
        if(up1 == null){
            getImageInstance();
        }
        if(SourceEntity == GlobalGameThreadConfigs.player || SourceEntity.EntityType == 2){
            int monsterindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
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
            int npcindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.NPCS);
            if(npcindex != 999){
                AttackNPC(TrueAttackDamage, npcindex);
                ParticlePropertyManager(SourceEntity.projectile, GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex]);
                alive = false;
            }
        }

        switch (direction){
            case "up": worldy -= speed; break;
            case "down": worldy += speed; break;
            case "left": worldx -= speed; break;
            case "right": worldx += speed; break;
        }
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
        health--;

        if(health <= 0){
            alive = false;
        }
        spritecounter++;
    }
}
