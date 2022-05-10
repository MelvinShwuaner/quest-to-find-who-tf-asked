package net.questfor.thepersonwhoasked.objects.Projectiles;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Projectile;
import java.awt.*;

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
        up1 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
        up2 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
        down1 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
        down2 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
        left1 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
        left2 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
        right1 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
        right2 = BufferedRenderer("objects/Projectile/rock_down_1", gp.tilesize, gp.tilesize);
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
}
