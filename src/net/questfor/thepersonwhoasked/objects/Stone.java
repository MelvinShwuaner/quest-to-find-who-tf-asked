package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class Stone extends LivingEntity {
    public Stone(MainGame gpp, int x, int y) {
        super(gpp);
        name = "Stone";
        Value = 2;
        description = "just any other plain old rock";
        EntityType = 4;
        collision = true;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        getImageInstance();
        this.worldx = x * gp.tilesize;
        this.worldy = y * gp.tilesize;
        worldz = 4;
    }

    @Override
    public boolean ItemRequirements(LivingEntity SourceEntity) {
        return SourceEntity.currentweapon.Type == Type_pickaxe;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/stone", gp.tilesize, gp.tilesize);
    }

    public Color getparticleColor() {
        return new Color(0x30312E);
    }

    public int getparticleSize() {
        return 8;
    }

    public int getparticlespeed() {
        return 2;
    }
    public int getparticleMaxHealth() {
        return 20;
    }

    public void update() {if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] == 46){
        gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/gp.tilesize)][(int) Math.round(worldy/gp.tilesize)][(int) worldz] = 47;
    }
        if (down1 == null) {
            getImageInstance();
        }
        if (invincible) {
            hitTime++;
            if (hitTime > 20) {
                invincible = false;
                hitTime = 0;
            }
        }
        if (!alive) {
            HandleItems();
        }
    }

    @Override
    public LivingEntity getDestroyedForm() {
        LivingEntity Stone = new Stone(gp, (int) Math.round(worldx/gp.tilesize), (int) Math.round(worldy/gp.tilesize));
        Stone.EntityType = 3;
        return Stone;
    }
}
