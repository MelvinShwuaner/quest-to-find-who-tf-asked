package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;

import java.awt.*;

public class Particle extends LivingEntity {
    public LivingEntity SourceEntity;
    public Color texture;
    int size;
    int xd;
    int yd;

    public Particle(MainGame gpp, LivingEntity sourceEntity, Color color, int size, int speed, int maxHealth, int xd, int yd) {
        super(gpp);
        this.SourceEntity = sourceEntity;
        this.texture = color;
        this.size = size;
        this.speed = speed;
        this.maxhealth = maxHealth;
        this.xd = xd;
        this.yd = yd;
        health = maxhealth;

        worldx = SourceEntity.worldx;
        worldy =SourceEntity.worldy;
}
    public void draw(Graphics2D g2){
        int screenX = (int) (worldx - GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX);
        int screenY = (int) (worldy - GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY);
        g2.setColor(texture);
        g2.fillRect(screenX, screenY, size, size);
    }
    public void update(){
        health--;
        if(health<maxhealth/3){
            yd++;
        }
       worldx += xd*speed;
       worldy += yd*speed;
       if(health < 0){
           alive = false;
       }
       if(down1 == null){
           getImageInstance();
       }
    }

}
