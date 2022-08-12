package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;

public class Storageunit extends LivingEntity {
    public Storageunit(MainGame gp, double col, double row, double layer){
        super(gp);
        name = "chest";
        LightSource = false;
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "basic 16*16 unit. can store 10 items";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = GlobalGameThreadConfigs.tilesize;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        maxstacksize = 16;
        maxcool = 100;
        worldx = col;
        worldy = row;
        worldz = layer;
    }
    public Storageunit(MainGame gp, int col, int row, double z){
        super(gp);
        name = "chest";
        getImageInstance();
        EntityType = 4;
        collision = true;
        description = "basic 16*16 unit. can store 10 items";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = GlobalGameThreadConfigs.tilesize;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        maxstacksize = 16;
        worldz = z;
        worldx = col*GlobalGameThreadConfigs.tilesize;
        worldy = row*GlobalGameThreadConfigs.tilesize;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/storageunit", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    @Override
    public void update() {if(!passanger) {       if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] == 46){
        gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] = 47;
    }}else{
        if(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].tiles[(int) ( Math.round(worldx/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))][(int) ( Math.round(worldy/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))][(int) ( worldz-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldz)] == 46){
            GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].tiles[(int) ( Math.round(worldx/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))][(int) ( Math.round(worldy/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))][(int) ( worldz-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldz)] = 47;    }
    }
    }
    @Override
    public void Place(double x, double y, double z, int i){
        boolean canplace;
        if(!KeyHandler.CROUCH && !KeyHandler.sprint) {
            switch (GlobalGameThreadConfigs.player.direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
        }else if(KeyHandler.CROUCH){
            z--;
        }else if(KeyHandler.sprint){
            z++;
        }
        canplace = (gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z));
        if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)){
            switch (direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
            canplace = gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z);
        }
        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)+1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)-1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z))) {
            boolean ptile = false;
            for(int index = 0; index < GlobalGameThreadConfigs.Vehicles[0].length; index++){
                if(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index] != null){
                    if(((Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].width)
                            && ((Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].height)
                            && (z >= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz && z <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].depth)
                    ){

                        GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new Storageunit(
                                gp,
                                ((Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx,
                                ((Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy,
                                z
                        );
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][i].enterVehcile(index);
                        ptile = true;
                    }
                }
            }
            if(!ptile)
            {GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new Storageunit(gp, (int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), z);}
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
    public boolean ItemRequirements(LivingEntity SourceEntity) {
        return SourceEntity.currentweapon.Type == Type_axe;
    }
    @Override
    public LivingEntity replicate() {
        return new Storageunit(gp, 0, 0, 0);
    }

    @Override
    public void open(int x, int y, int z, int i) {
        if (KeyHandler.enterpressed) {
            if (!GlobalGameThreadConfigs.inchest) {
                GlobalGameThreadConfigs.inchest = true;
                GlobalGameThreadConfigs.CharacterStats = true;

            } else {
                GlobalGameThreadConfigs.inchest = false;
                GlobalGameThreadConfigs.CharacterStats = false;
                UI.slotstate = false;
                if(UI.merger != null){
                    UI.merger = null;
                    UI.merging = false;
                }
                if(UI.mergerr != null){
                    UI.merging = false;
                    UI.mergerr = null;
                }
            }
        }
    }
    public void Move(String direction, double speed){
        try{
            if(gp.tilemanager.mapRendererID[currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] == 47){
                gp.tilemanager.mapRendererID[currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] = 46;
            }
            switch (direction) {
                case "up" -> worldy = worldy - speed;
                case "down" -> worldy = worldy + speed;
                case "right" -> worldx = worldx + speed;
                case "left" -> worldx = worldx - speed;
                case "destroy" -> health -= 10;
            }
            if(gp.tilemanager.mapRendererID[currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] == 46) {
                gp.tilemanager.mapRendererID[currentmap][(int) Math.round(worldx / GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy / GlobalGameThreadConfigs.tilesize)][(int) worldz] = 47;
            }
            if(LightSource)
                updateLight(Lightposition);
        }catch (Exception ignored){}
    }}
