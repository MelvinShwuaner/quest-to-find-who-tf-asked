package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FlyingTile extends LivingEntity {
    int veloxityx, velocityy;
    int maxspeed;
    boolean vehicle = true;
    public FlyingTile(MainGame gp, int tilel, String image, int x, int y, int z, int speed, int veloxityx, int velocityy){
        super(gp);
        getImageInstance(image);
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 4;
        LightSource = false;
        tile = tilel;
        worldx = x;
        worldy = y;
        worldz = z;
        this.maxspeed = speed;
        this.speed = maxspeed;
        this.veloxityx = veloxityx;
        this.velocityy = velocityy;
        if(speed < 4){
            this.speed = 4;
        }
    }
    public FlyingTile(MainGame gp, int tilel, String image, int x, int y, int z, int speed, int veloxityx, int velocityy, boolean placeonvehicle){
        super(gp);
        getImageInstance(image);
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 4;
        LightSource = false;
        tile = tilel;
        worldx = x;
        worldy = y;
        worldz = z;
        this.maxspeed = speed;
        this.speed = maxspeed;
        this.veloxityx = veloxityx;
        this.velocityy = velocityy;
        vehicle = placeonvehicle;
        if(speed < 4){
            this.speed = 4;
        }
    }
    @Override
    public void update() {

        worldx+= (veloxityx*speed);
        worldy+=(velocityy*speed);
        if(speed > 1)
          speed--;
        double x = worldx;
        double y = worldy;
        double z = worldz;
        gp.hregister.TileColide(this);

        boolean landed = false;
        if(speed < maxspeed-2){
            landed = !gp.hregister.checktileworld((int) Math.round(x/GlobalGameThreadConfigs.tilesize), (int) Math.round(y/GlobalGameThreadConfigs.tilesize), (int) z-1);
        }
        if(hitboxe || landed){
            if (speed < 4) {
            alive = false;
            boolean ptile = false;

            for(int index = 0; index < GlobalGameThreadConfigs.Vehicles[0].length; index++){
                if(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index] != null){
                    if(((Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].width)
                            && ((Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].height)
                            && (z >= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz && z <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].depth)
                    ){
if(vehicle){
                        vehindex = index;
                        GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].tiles[(int) ( Math.round(worldx/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))][(int) ( Math.round(worldy/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))][(int) ( worldz-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldz)] = tile;
                        ptile = true;

                    }}
                }
            }
            if(!ptile){
                if (Math.round(x/GlobalGameThreadConfigs.tilesize) > 0 && Math.round(x/GlobalGameThreadConfigs.tilesize) < 200 && Math.round(y/GlobalGameThreadConfigs.tilesize) > 0 && Math.round(y/GlobalGameThreadConfigs.tilesize) < 200 && z > 0 && z < 8) {
                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = tile;

            }
        }}


    }else{
            if(worldz+1 < 8){
                if(speed > maxspeed-2){
                worldz += 2;}}
        }worldz--;}




    public void getImageInstance(String image) {
        down1 = BufferedRenderer(image, GlobalGameThreadConfigs.tilesize-2, GlobalGameThreadConfigs.tilesize-5);
    }
    public BufferedImage BufferedRenderer(String imagePath, int width, int height){
        //OPTIMIZES THE RENDERER TO MAKE IT MORE EFFICIENT

        BufferedImage ScaledImage = null;
        try{
            ScaledImage = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
            ScaledImage = scaleimage(ScaledImage, width, height);
        }catch (Exception e) {
            crash.main(e);
        }
        return ScaledImage;
    }
}
