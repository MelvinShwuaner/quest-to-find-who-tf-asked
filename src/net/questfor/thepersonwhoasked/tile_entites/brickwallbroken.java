package net.questfor.thepersonwhoasked.tile_entites;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.Brick;

import java.awt.*;

public class brickwallbroken extends LivingEntity {
    public brickwallbroken(MainGame gpp, int col, int row) {
        super(gpp);
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize * row;
        EntityType = 4;
        hitbox = new Rectangle(28, 0, 20, 48);
        collision = true;
        health = 10;
        worldz = 4;
    }
    @Override
    public boolean ItemRequirements(LivingEntity SourceEntity){
        return !SourceEntity.invincible && (SourceEntity.name.equals("Green Slime") || SourceEntity.currentweapon.Type == Type_pickaxe);
    }


    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/brickwallbroken", gp.tilesize, gp.tilesize);
    }
    public LivingEntity getDestroyedForm(){
        LivingEntity Brick = new Brick(gp);
        Brick.worldx = worldx;
        Brick.worldy = worldy;
        return Brick;}
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
}
