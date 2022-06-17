package net.questfor.thepersonwhoasked.objects.Projectiles;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Projectile;

import java.awt.*;
import java.util.Random;

public class OBJ_ROCK extends Projectile {
    MainGame gp;
    public OBJ_ROCK(MainGame gpp) {
        super(gpp);
        this.gp  = gpp;
        name = "Rock";
        speed = 8;
        maxhealth = 80;
        health = maxhealth;
        AttackValue = 4;
        UseCost = 1;
        alive = false;
        getImageInstance();
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        Type = Type_projectile;
        hitboxdefaulty = hitbox.y;
    }
    public void getImageInstance(){
        up1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        up2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        down1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        down2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        left1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        left2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        right1 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        right2 = BufferedRenderer("objects/Projectile/rock_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
    }
    public boolean haveresource(LivingEntity sourceEntity){
        boolean haveresource = false;
        if(sourceEntity.Ammo >= UseCost){
            haveresource = true;
        }
        return haveresource;
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
        if(SourceEntity == gp.player || SourceEntity.EntityType == 2){
            int monsterindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
            if(monsterindex != 999){
                attackEntity(monsterindex, AttackValue);
                ParticlePropertyManager(SourceEntity.projectile, GlobalGameThreadConfigs.Monsters[MainGame.currentmap][monsterindex]);
                alive = false;
            }
        }if(SourceEntity != gp.player && SourceEntity.EntityType == 1){
            boolean contactPlayer = gp.hregister.PlayerColide(this);
            if(!gp.player.invincible && contactPlayer){
                ParticlePropertyManager(SourceEntity.projectile, gp.player);
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
        if(health == 40 || health == 20){
            worldz--;
            image = scaleimage(image, (int) (image.getWidth() - worldz), (int) (image.getHeight() - worldz));
        }

        if(!gp.hregister.checktileworld((int) Math.round(worldx / gp.tilesize), (int) Math.round(worldy / gp.tilesize), (int) worldz)){
            alive = false;
        }

        if(health <= 0){
            alive = false;
        }
        spritecounter++;
    }
}
