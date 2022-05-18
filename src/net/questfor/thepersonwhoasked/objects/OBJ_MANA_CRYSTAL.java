package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;
public class OBJ_MANA_CRYSTAL extends LivingEntity {
    int I;
    public OBJ_MANA_CRYSTAL(MainGame gpp) {
        super(gpp);
        Type = Type_Current;
        EntityType = 3;
        name = "Mana Crystal";
        getImageInstance();
        Random random = new Random();
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
         I = random.nextInt(100) + 1;
}
public void set(){
    if (I > 50) {
        down1 = image;
        Value = 1;
    } else{
        down1 = image2;
        Value = -1;
    }
}
    public void Use(LivingEntity target){
        if (down1 != image2) {
            gp.playsound(2);
        } else {
            gp.playsound(5);
        }
        target.Mana += Value;
        if (target.Mana > target.MaxMana) {
            target.Mana = target.MaxMana;
        }
        if(target.Mana < 0) {
            target.Mana = 0;
        }
        if (down1 != image2){
            UI.addMessages("you have gained " + Value+"Mana" + " your Mana is now " + gp.player.Mana);
        }else{
            UI.addMessages("you have lost " + 1+"Mana" + " your Mana is now " + gp.player.Mana);
        }
    }

    @Override
    public void getImageInstance() {
        image = BufferedRenderer("objects/manacrystal_full", gp.tilesize, gp.tilesize);
        image2 = BufferedRenderer("objects/manacrystal_blank", gp.tilesize, gp.tilesize);
    }

    @Override
    public void updateimage() {
        super.updateimage();
        if (up1 == null && image == null && down1 == null) {
            set();
        }
    }
}
