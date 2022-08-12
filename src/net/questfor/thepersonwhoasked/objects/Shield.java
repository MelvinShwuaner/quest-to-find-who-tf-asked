package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Shield extends LivingEntity {
    public Shield(MainGame gp){
        super(gp);

        getImageInstance();
        description = "a simple "+name;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 3;
        maxstacksize = 64;
        LightSource = false;
        
    }
    @Override
    public void update() {}

    @Override
    public void Place(double x, double y, double z, int i) {
        boolean canplace;
        switch (GlobalGameThreadConfigs.player.direction) {
            case "down" -> tile = 60;
            case "up" -> tile = 61;
            case "left" -> tile = 62;
            case "right" -> tile = 63;
        }
        if(!KeyHandler.CROUCH && !KeyHandler.sprint) {
            switch (GlobalGameThreadConfigs.player.direction) {
                case "down" -> y += 48;
                case "up" -> y -= 48;
                case "left" -> x -= 48;
                case "right" -> x += 48;
            }
        }else if(KeyHandler.CROUCH){
            z--;
        }else if(KeyHandler.sprint){
            z++;
        }
        canplace = (gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z));
        if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)) {
            switch (GlobalGameThreadConfigs.player.direction) {
                case "down" -> y += 48;
                case "up" -> y -= 48;
                case "left" -> x -= 48;
                case "right" -> x += 48;
            }
            canplace = (gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z, GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z));
        }
        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)+1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)-1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z))) {
            boolean ptile = false;
            for(int index = 0; index < GlobalGameThreadConfigs.Vehicles[0].length; index++){
                if(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index] != null){
                    if(((Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].width)
                    && ((Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].height)
                    && (z >= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz && z <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].depth)
                    ){
                    GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].tiles
                    [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize))]
                    [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize))]
                    [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz)] = tile;
                    ptile = true;
                    }
                }
            }
            if(!ptile) {
                gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = tile;
            }
            if(!GlobalGameThreadConfigs.Buildmode){
                GlobalGameThreadConfigs.player.currentshield.stacksize--;
                if (GlobalGameThreadConfigs.player.currentshield.stacksize <= 0) {
                    GlobalGameThreadConfigs.player.inventory.remove(GlobalGameThreadConfigs.player.currentshield);

                    GlobalGameThreadConfigs.player.currentshield = null;
                }
            }
        }
    }

    @Override
    public LivingEntity replicate() {
        return new Shield(gp);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("shieldup", GlobalGameThreadConfigs.tilesize-2, GlobalGameThreadConfigs.tilesize-5);
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
