package net.questfor.thepersonwhoasked.tile_entites;

import net.questfor.thepersonwhoasked.Maingam.MainGame;

public class hole extends TileEntity{
    public hole(MainGame gpp, int col, int row) {
        super(gpp, col, row);
        //hitbox.width = 0;
        //hitbox.height = 0;
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize*row;
    }

    @Override
    public void getImageInstance(String Image) {
        down1 = BufferedRenderer("TileEntity/"+image, gp.tilesize, gp.tilesize);
    }
}
