package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Player;

import java.awt.*;

public class OBJ_POTION_HEALTH_1 extends LivingEntity {
    public OBJ_POTION_HEALTH_1(MainGame gpp) {
        super(gpp);
        Value = 3;
        Type = Type_constumable;
        name = "Red Potion";
        getImageInstance();
        description = "a excellant choice for cheap low polly \npotion for a quick refill! \n heals your health by "+Value;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        LightSource = false;
    }
    public void Use(LivingEntity sourceentity){
            sourceentity.health += Value;
            Player.playsound(2);
            if(sourceentity.health > sourceentity.maxhealth){
                sourceentity.health = sourceentity.maxhealth;
            }
    }
    @Override
    public void update() {}
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/potion_red", gp.tilesize, gp.tilesize);
    }
}
