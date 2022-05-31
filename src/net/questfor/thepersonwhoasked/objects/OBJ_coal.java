package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class OBJ_coal extends LivingEntity {
    public OBJ_coal(MainGame gpp) {
        super(gpp);
        name = "Basic Coal";
        getImageInstance();
        defenceValue = 4;
        Value = 1;
        description = "a "+name+" that is cheep, and does its job!";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 4;
        maxstacksize = 64;
        fuel = true;
        health = 10;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/basic_coall", gp.tilesize, gp.tilesize);
    }

    @Override
    public void update() {}
}
