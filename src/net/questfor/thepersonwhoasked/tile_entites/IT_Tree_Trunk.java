package net.questfor.thepersonwhoasked.tile_entites;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.objects.OBJHeart;
import net.questfor.thepersonwhoasked.objects.OBJ_COIN_BRONZE;
import net.questfor.thepersonwhoasked.objects.OBJ_MANA_CRYSTAL;

import java.awt.*;
import java.util.Random;

public class IT_Tree_Trunk extends TileEntity {
    public IT_Tree_Trunk(MainGame gpp, int col, int row) {
        super(gpp, col, row);
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize*row;
        EntityType = 3;
        hitbox = new Rectangle(0, 0, 0, 0);
        collision = false;
        distructuble = false;
    }
    public void HandleItems(){
        int I = new Random().nextInt(100)+1;
        if(I < 50){
            DropItems(new OBJ_MANA_CRYSTAL(gp));
            DropItems(new OBJHeart(gp));
        }else {
            DropItems(new OBJ_COIN_BRONZE(gp));
        }
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/trunk", gp.tilesize, gp.tilesize);
    }
    public TileEntity getDestroyedForm(){return new IT_Tree_Trunk(gp, (int) (worldx/gp.tilesize), (int) worldy/gp.tilesize);}
}
