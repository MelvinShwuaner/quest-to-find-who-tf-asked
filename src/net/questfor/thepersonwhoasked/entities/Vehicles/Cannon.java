package net.questfor.thepersonwhoasked.entities.Vehicles;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Projectile;

import java.awt.*;

public class Cannon extends LivingEntity {
    public Cannon(MainGame gpp, int x, int y, int z, Projectile projectile1, String direction, int ammo) {
        super(gpp);
        worldx = x * GlobalGameThreadConfigs.tilesize;
        worldy = y * GlobalGameThreadConfigs.tilesize;
        worldz = z;
        projectile = projectile1;
        this.direction = direction;
Cannon = true;

hitbox = new Rectangle(0, 0, 48, 48);
Mana = ammo;
Ammo = ammo;
    }

    @Override
    public void update() {
        try{
        if(!passanger) {       if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] == 46){
            gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] = 47;
        }}else{
            if(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].tiles[(int) ( Math.round(worldx/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))][(int) ( Math.round(worldy/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))][(int) ( worldz-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldz)] == 46){
                GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].tiles[(int) ( Math.round(worldx/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))][(int) ( Math.round(worldy/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))][(int) ( worldz-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldz)] = 47;    }
        }
    }catch (Exception ignored){}}

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/axe", 48, 48);
    }

    public void Fire() {

        if (projectile.alive == false && projectile.haveresource(this)) {
            System.out.println(worldy/GlobalGameThreadConfigs.tilesize);

            projectile.Set((int) worldx, (int) worldy, (int) worldz, direction, true, this);
            projectile.RemoveResource(this);
            for (int i = 0; i < GlobalGameThreadConfigs.projectilelist[1].length; i++) {
                if (GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] == null) {
                    GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] = projectile;
                    break;
                }
            }
        }
    }

}