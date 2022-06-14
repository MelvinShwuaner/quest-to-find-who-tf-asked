package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
public class IT_tree extends LivingEntity{
    public IT_tree(MainGame gpp, int col, int row) {
        super(gpp);
        name = "Dry tree";
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize*row;
        EntityType = 4;
        health = 2;
        LightSource = false;
    }
    public boolean ItemRequirements(LivingEntity SourceEntity){
        boolean iscorrectItem = SourceEntity.currentweapon.Type == Type_axe;
        return iscorrectItem;
    }
    public void playSE(){gp.playsound(11);}
    public LivingEntity getDestroyedForm(){return new IT_Tree_Trunk(gp, (int) (worldx/gp.tilesize), (int) worldy/gp.tilesize);}
    public Color getparticleColor(){return new Color(0x41321E);}
    public int getparticleSize(){return 6;}
    public int getparticlespeed(){return 1;}
    public int getparticleMaxHealth(){return 20;}

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/drytree", gp.tilesize, gp.tilesize);
    }
}
