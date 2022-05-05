package net.questfor.thepersonwhoasked.tile_entites;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import java.awt.*;
public class IT_tree extends TileEntity{
    public IT_tree(MainGame gpp, int col, int row) {
        super(gpp, col, row);
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize*row;

        distructuble = true;
        health = 2;
    }
    public boolean ItemRequirements(LivingEntity SourceEntity){
        boolean iscorrectItem = SourceEntity.currentweapon.Type == Type_axe;
        return iscorrectItem;
    }
    public void playSE(){gp.playsound(11);}
    public TileEntity getDestroyedForm(){return new IT_Tree_Trunk(gp, (int) (worldx/gp.tilesize), (int) worldy/gp.tilesize);}
    public Color getparticleColor(){return new Color(0x41321E);}
    public int getparticleSize(){return 6;}
    public int getparticlespeed(){return 1;}
    public int getparticleMaxHealth(){return 20;}

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/drytree", gp.tilesize, gp.tilesize);
    }
}
