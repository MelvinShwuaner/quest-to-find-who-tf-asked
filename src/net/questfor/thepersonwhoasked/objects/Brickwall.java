package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.tile_entites.brickwallbroken;

import java.awt.*;

public class Brickwall extends LivingEntity{
    public Brickwall(MainGame gpp, int col, int row) {
        super(gpp);
        this.worldx = col*gp.tilesize;
        this.worldy = row*gp.tilesize;
        name = "Brick wall";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 48;
        hitbox.height = 48;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        health = 13;
        EntityType = 4;
        worldz = 3;
        collision = true;
    }
    public boolean ItemRequirements(LivingEntity SourceEntity){

        return !SourceEntity.invincible && (SourceEntity.name.equals("Green Slime") || SourceEntity.currentweapon.Type == Type_pickaxe);
    }
    public void playSE(){gp.playsound(11);}
    public LivingEntity getDestroyedForm(){return new brickwallbroken(gp, (int) Math.round(worldx/gp.tilesize), (int) Math.round(worldy/gp.tilesize));}
    public Color getparticleColor(){return new Color(0x9D533B);}
    public int getparticleSize(){return 6;}
    public int getparticlespeed(){return 1;}
    public int getparticleMaxHealth(){return 20;}
    public void update() {
        if(down1 == null){
            getImageInstance();
        }
        if(invincible){
            hitTime++;
            if(hitTime > 20){
                invincible = false;
                hitTime = 0;
            }
        }
        if(!alive){
            HandleItems();
        }
    }


    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/brickwall", gp.tilesize, gp.tilesize);
    }
}
