package net.questfor.thepersonwhoasked.tile_entites;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.OBJHeart;
import net.questfor.thepersonwhoasked.objects.OBJ_COIN_BRONZE;
import net.questfor.thepersonwhoasked.objects.OBJ_MANA_CRYSTAL;

import java.util.Random;

public class IT_tree extends TileEntity{
    public IT_tree(MainGame gpp, int col, int row) {
        super(gpp, col, row);
        this.worldx = gp.tilesize * col;
        this.worldy = gp.tilesize*row;
        down1 = BufferedRenderer("TileEntity/drytree", gp.tilesize, gp.tilesize);
        distructuble = true;
        health = 2;
    }
    public boolean ItemRequirements(LivingEntity SourceEntity){
        boolean iscorrectItem = SourceEntity.currentweapon.Type == Type_axe;
        return iscorrectItem;
    }
    public void playSE(){gp.playsound(11);}
    public TileEntity getDestroyedForm(){
        TileEntity destroyedForm = new IT_Tree_Trunk(gp, (int) (worldx/gp.tilesize), (int) worldy/gp.tilesize);
        return destroyedForm;}
}
