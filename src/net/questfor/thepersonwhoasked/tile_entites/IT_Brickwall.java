package net.questfor.thepersonwhoasked.tile_entites;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class IT_Brickwall extends TileEntity{
    public IT_Brickwall(MainGame gpp, int col, int row) {
        super(gpp, col, row);
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize*row;
        name = "Brick wall";

        distructuble = true;
        health = 13;
    }
    public boolean ItemRequirements(LivingEntity SourceEntity){
        boolean iscorrectItem = !SourceEntity.invincible && SourceEntity.name.equals("Green Slime");
        return iscorrectItem;
    }
    public void playSE(){gp.playsound(11);}
    public TileEntity getDestroyedForm(){return new IT_brickwallbroken(gp, (int) Math.round(worldx/gp.tilesize), (int) Math.round(worldy/gp.tilesize));}
    public Color getparticleColor(){return new Color(0x9D533B);}
    public int getparticleSize(){return 6;}
    public int getparticlespeed(){return 1;}
    public int getparticleMaxHealth(){return 20;}

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/brickwall", gp.tilesize, gp.tilesize);
    }
}
