package net.questfor.thepersonwhoasked.objects.Projectiles;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
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
        TrueAttackDamage = 2;
        UseCost = 1;
        alive = false;
        getImageInstance();
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
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
}
