package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJkey extends LivingEntity {
    public OBJkey(MainGame gp){
        super(gp);
        name = "key";
        getImageInstance();
        description = "a "+name+" that is always used in dungeons, \n always can pick a door";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        maxstacksize = 32;
    }
    @Override
    public void update() {}
    public LivingEntity replicate() {
        return new OBJkey(gp);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/key", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }

    @Override
    public void Place(double x, double y, double z, int i) {
        try {
            if (!KeyHandler.CROUCH && !KeyHandler.sprint) {
                switch (GlobalGameThreadConfigs.player.direction) {
                    case "down" -> y += 50;
                    case "up" -> y -= 50;
                    case "left" -> x -= 50;
                    case "right" -> x += 50;
                }
            } else if (KeyHandler.CROUCH) {
                z--;
            } else if (KeyHandler.sprint) {
                z++;
            }

            if (GlobalGameThreadConfigs.player.passanger) {
                if (GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][GlobalGameThreadConfigs.player.vehindex].tiles
                [(int) (Math.round(x / GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][GlobalGameThreadConfigs.player.vehindex].worldx/GlobalGameThreadConfigs.tilesize))]
                [(int) (Math.round(y / GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][GlobalGameThreadConfigs.player.vehindex].worldy/GlobalGameThreadConfigs.tilesize))]
                [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][GlobalGameThreadConfigs.player.vehindex].worldz)] == 54) {
                    GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][GlobalGameThreadConfigs.player.vehindex].controller.add(0, GlobalGameThreadConfigs.player);
                    GlobalGameThreadConfigs.player.controlling = true;
                }
            }

        } catch (Exception ignored){

        }
    }
}
