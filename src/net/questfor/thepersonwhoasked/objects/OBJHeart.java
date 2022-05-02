package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;

public class OBJHeart extends LivingEntity {

    public OBJHeart(MainGame gp) {
        super(gp);
        name = "heart";
        image = BufferedRenderer("objects/heart_full", gp.tilesize, gp.tilesize);
        image2 = BufferedRenderer("objects/heart_half", gp.tilesize, gp.tilesize);
        image3 = BufferedRenderer("objects/heart_blank", gp.tilesize, gp.tilesize);
        EntityType = 3;
        Type = Type_Current;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        Random random = new Random();
        int I = random.nextInt(125);
        if (I < 25) {
            down1 = image;
        } else if (I > 25 && I < 75) {
            down1 = image2;
        } else if (I > 75) {
            down1 = image3;
        }
        Type = Type_Current;
        if (down1 == image) {
            Value = 2;
        } else if (down1 == image2) {
            Value = 1;
        } else if (down1 == image3) {
            Value = -1;
        }

    }

    @Override
    public void Use(LivingEntity target) {
        if (down1 != image3) {
            gp.playsound(2);
        } else {
            gp.playsound(5);
        }
        target.health += Value;
        if (target.health > target.maxhealth) {
            target.health = target.maxhealth;
        }
        if (down1 != image3){
            UI.addMessages("you have gained " + Value+"HP" + " your health is now " + gp.player.health);
    }else{
            UI.addMessages("you have lost " + 1+"HP" + " your health is now " + gp.player.health);
        }
}
}
