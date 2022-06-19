package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJHeart extends LivingEntity {
    int I;

    public OBJHeart(MainGame gp) {
        super(gp);
        name = "heart";
        getImageInstance();
        EntityType = 3;
        Type = Type_Current;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        LightSource = false;
        Random random = new Random();
        I = random.nextInt(125);
    }
    @Override
    public void update() {}
    public void set() {
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
        if (down1 != image3) {
            UI.addMessages("you have gained " + Value + "HP" + " your health is now " + GlobalGameThreadConfigs.player.health);
        } else {
            UI.addMessages("you have lost " + 1 + "HP" + " your health is now " + GlobalGameThreadConfigs.player.health);
        }
    }

    @Override
    public void getImageInstance() {
        image = BufferedRenderer("objects/heart_full", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        image2 = BufferedRenderer("objects/heart_half", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        image3 = BufferedRenderer("objects/heart_blank", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }

    @Override
    public void updateimage() {
        super.updateimage();
        if (up1 == null && image == null && down1 == null) {
            set();
        }
    }
    public LivingEntity replicate() {
        return new OBJHeart(gp);
    }


}
