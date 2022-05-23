package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class crafting_table extends LivingEntity {
    public crafting_table(MainGame gp){
        super(gp);
        name = "crafting table";
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "is used to craft all sorts of items";
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
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/crafting_table", gp.tilesize, gp.tilesize);
    }
    public void open(){
        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.UIstate;
        UI.npc = this;
        UI.currentUI = "crafting";
    }
    @Override
    public void update() {}
}
