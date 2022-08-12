package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.GeneralHandler;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class throwableTNT extends LivingEntity {
    double maxspeed;
    public throwableTNT(MainGame gp, double x, double y, double z, double speed, String direction){
        super(gp);
        getImageInstance();
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 4;
        LightSource = false;

        worldx = x;
        worldy = y;
        worldz = z;
        this.maxspeed = speed;
        this.speed = maxspeed;
       this.direction = direction;

    }
    @Override
    public void update() {
        switch (direction) {
            case "up" -> worldy = worldy - speed;
            case "down" -> worldy = worldy + speed;
            case "right" -> worldx = worldx + speed;
            case "left" -> worldx = worldx - speed;
        }
        if(speed > 1)
          speed--;
        double x = worldx;
        double y = worldy;
        double z = worldz;
        gp.hregister.TileColide(this);
        boolean landed = !gp.hregister.checktileworld((int) Math.round(x/GlobalGameThreadConfigs.tilesize), (int) Math.round(y/GlobalGameThreadConfigs.tilesize), (int) z-1);

        if(hitboxe || landed){

            alive = false;
            boolean ptile = false;

            GeneralHandler.Explode(3, 3, 3, (int) Math.round(x/GlobalGameThreadConfigs.tilesize), (int) Math.round(y/GlobalGameThreadConfigs.tilesize), (int) z, 2, 1, false, 999);


        }else{
                if(worldz+1 < 8 && speed > maxspeed/2){
            worldz += 2;
                }else if(speed > maxspeed/2){
                    worldz++;
                }
        }
        worldz--;
    }




    public void getImageInstance() {
        right1 = BufferedRenderer("TileEntity/tnt", GlobalGameThreadConfigs.tilesize-2, GlobalGameThreadConfigs.tilesize-5);
        left1 = BufferedRenderer("TileEntity/tnt", GlobalGameThreadConfigs.tilesize-2, GlobalGameThreadConfigs.tilesize-5);
        up1 = BufferedRenderer("TileEntity/tnt", GlobalGameThreadConfigs.tilesize-2, GlobalGameThreadConfigs.tilesize-5);

        down1 = BufferedRenderer("TileEntity/tnt", GlobalGameThreadConfigs.tilesize-2, GlobalGameThreadConfigs.tilesize-5);
    }
}
