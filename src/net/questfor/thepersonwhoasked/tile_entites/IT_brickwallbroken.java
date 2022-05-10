package net.questfor.thepersonwhoasked.tile_entites;

import net.questfor.thepersonwhoasked.Maingam.MainGame;

import java.awt.*;

public class IT_brickwallbroken extends TileEntity {
    public IT_brickwallbroken(MainGame gpp, int col, int row) {
        super(gpp, col, row);
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize*row;
        EntityType = 3;
        hitbox = new Rectangle(0, 0, 0, 0);
        collision = false;
        distructuble = false;
    }


    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/brickwallbroken", gp.tilesize, gp.tilesize);
    }
    public TileEntity getDestroyedForm(){return new IT_brickwallbroken(gp, (int) (worldx/gp.tilesize), (int) worldy/gp.tilesize);}
}
