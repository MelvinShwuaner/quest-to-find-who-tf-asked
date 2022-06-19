package net.questfor.thepersonwhoasked.objects.Projectiles;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Projectile;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJ_FireBall extends Projectile {
    MainGame gp;
    public OBJ_FireBall(MainGame gpp) {
        super(gpp);
        this.gp  = gpp;
        name = "FireBall";
        speed = 5;
        maxhealth = 80;
        health = maxhealth;
        AttackValue = 5;
        UseCost = 1;
        alive = false;
        getImageInstance();
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 16;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        Type = Type_projectile;

    }
    public void getImageInstance(){
        up1 = BufferedRenderer("objects/Projectile/fireball_up_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        up2 = BufferedRenderer("objects/Projectile/fireball_up_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down1 = BufferedRenderer("objects/Projectile/fireball_down_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        down2 = BufferedRenderer("objects/Projectile/fireball_down_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left1 = BufferedRenderer("objects/Projectile/fireball_left_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        left2 = BufferedRenderer("objects/Projectile/fireball_left_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right1 = BufferedRenderer("objects/Projectile/fireball_right_1", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        right2 = BufferedRenderer("objects/Projectile/fireball_right_2", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    public boolean haveresource(LivingEntity sourceEntity){
        boolean haveresource = false;
        if(sourceEntity.Mana >= UseCost){
            haveresource = true;
        }
        return haveresource;
    }
    public void RemoveResource(LivingEntity source){
        source.Mana -= UseCost;
    }
    public Color getparticleColor(){return new Color(0xF03200);}
    public int getparticleSize(){return 10;}
    public int getparticlespeed(){return 2;}
    public int getparticleMaxHealth(){return 20;}
    }

