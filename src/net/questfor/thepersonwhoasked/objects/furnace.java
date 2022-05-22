package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class furnace extends LivingEntity {
    public furnace(MainGame gp){
        super(gp);
        name = "furnace";
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "a slow rusty furnace, is really hot!";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        maxstacksize = 16;
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        maxcool = 100;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/chest", gp.tilesize, gp.tilesize);
    }
    public void open(){
        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.UIstate;
        UI.npc = this;
        UI.currentUI = "Furnace";

    }
}
