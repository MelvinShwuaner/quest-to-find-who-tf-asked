package net.questfor.thepersonwhoasked.tile_entites;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class TileEntity extends LivingEntity {
    public boolean distructuble = false;
    public TileEntity(MainGame gpp, int col, int row) {
        super(gpp);
        EntityType = 4;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
    }
    public boolean ItemRequirements(LivingEntity SourceEntity){return false;}
    public void playSE(){}
    public TileEntity getDestroyedForm(){return null;}
    public void update() {
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
    public void HandleItems(){}
}
