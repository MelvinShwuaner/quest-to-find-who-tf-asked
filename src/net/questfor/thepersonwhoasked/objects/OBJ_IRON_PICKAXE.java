package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;

public class OBJ_IRON_PICKAXE extends LivingEntity {
    public OBJ_IRON_PICKAXE(MainGame gpp) {
        super(gpp);
        name = "Iron pickaxe";
        AttackValue = 2;
        Value = 3;
        knockbackpower = 8;
        attackHitbox.width = 30;
        attackHitbox.height = 36;
        EntityType = 3;
        LightSource = false;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        Type = Type_pickaxe;
        frames = 3;
        description = "A good pickaxe, thats it";
        getImageInstance();
    }
    @Override
    public void update() {}
    public LivingEntity replicate() {
        return new OBJ_IRON_PICKAXE(gp);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/iron_pickaxe", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }

    @Override
    public void Destroy(double x, double y, double z) {
        boolean ptile = false;
        int ID = gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / GlobalGameThreadConfigs.tilesize))][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z];
        int vehicle=0;
        for (int index = 0; index < GlobalGameThreadConfigs.Vehicles[0].length; index++) {
            if (GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index] != null) {
                if (((Math.round(x / GlobalGameThreadConfigs.tilesize) - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx / GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(x / GlobalGameThreadConfigs.tilesize) - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx / GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].width)
                        && ((Math.round(y / GlobalGameThreadConfigs.tilesize) - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy / GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(y / GlobalGameThreadConfigs.tilesize) - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy / GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].height)
                        && (z >= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz && z <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz + GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].depth)
                ) {
                    ID = GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].tiles
                            [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx/GlobalGameThreadConfigs.tilesize))]
                            [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy/GlobalGameThreadConfigs.tilesize))]
                            [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz)];
                    ptile = true;
                    vehicle = index;
                }}}
        if (!GlobalGameThreadConfigs.Buildmode) {
                    if (ID != 47) {
                        switch (ID) {
                            case 49 -> {
                                if (!ptile){
                                    gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;
                                    for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null) {
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new OBJ_coal(gp);
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx = x;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = y;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz = z;
                                            ii = GlobalGameThreadConfigs.obj[1].length;
                                        }
                                    }
                            }else{
                                    System.out.println("deez");
                                    GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].tiles
                                            [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldx/GlobalGameThreadConfigs.tilesize))]
                                            [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldy/GlobalGameThreadConfigs.tilesize))]
                                            [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldz)] = 46;
                                    for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null) {
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new OBJ_coal(gp);
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx =  ((Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = ((Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz = z;
                                            ii = GlobalGameThreadConfigs.obj[1].length;
                                        }
                                    }
                                }
                            }
                            case 50 -> {if (!ptile){
                                gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;
                                for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null) {
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new Stone(gp);
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx = x;
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = y;
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz = z;
                                        ii = GlobalGameThreadConfigs.obj[1].length;
                                    }
                                }
                            }else{
                                GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].tiles
                                        [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldx/GlobalGameThreadConfigs.tilesize))]
                                        [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldy/GlobalGameThreadConfigs.tilesize))]
                                        [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldz)] = 46;
                                for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null) {
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new Stone(gp);
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx =  ((Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx;
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = ((Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy;
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz = z;
                                        ii = GlobalGameThreadConfigs.obj[1].length;
                                    }
                                }
                            }}
                            case 48 -> {
                                if (!ptile){
                                    gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;
                                    for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null) {
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new OBJ_BRICK_WALL(gp);
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx = x;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = y;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz = z;
                                            ii = GlobalGameThreadConfigs.obj[1].length;
                                        }
                                    }
                                }else{
                                    GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].tiles
                                            [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldx/GlobalGameThreadConfigs.tilesize))]
                                            [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldy/GlobalGameThreadConfigs.tilesize))]
                                            [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldz)] = 46;
                                    for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null) {
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new OBJ_BRICK_WALL(gp);
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx =  ((Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldx;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = ((Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy/GlobalGameThreadConfigs.tilesize))*48)+GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehindex].worldy;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz = z;
                                            ii = GlobalGameThreadConfigs.obj[1].length;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] != null) {
                                if (Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldx / GlobalGameThreadConfigs.tilesize) == Math.round(x / GlobalGameThreadConfigs.tilesize) && Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldy / GlobalGameThreadConfigs.tilesize) == Math.round(y / GlobalGameThreadConfigs.tilesize) && GlobalGameThreadConfigs.obj[currentmap][ii].worldz == z) {
                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].ItemRequirements(this)) {
                                        if (!GlobalGameThreadConfigs.obj[currentmap][ii].invincible) {
                                            GlobalGameThreadConfigs.obj[currentmap][ii].health -= TrueAttackDamage;
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].playSE();
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].invincible = true;
                                            ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii], GlobalGameThreadConfigs.obj[MainGame.currentmap][ii]);
                                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].health <= 0) {
                                                if(!ptile){
                                                MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx / GlobalGameThreadConfigs.tilesize)][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy / GlobalGameThreadConfigs.tilesize)][(int) GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz] = 46;
                                                }else{
                                                    GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].tiles
                                                            [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldx/GlobalGameThreadConfigs.tilesize))]
                                                            [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldy/GlobalGameThreadConfigs.tilesize))]
                                                            [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldz)] = 46;
                                                }
                                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].getDestroyedForm();
                                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].HandleItems();
                                            }
                                            ii = GlobalGameThreadConfigs.obj[1].length;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
         else {
             if(ID != 47){
            if (!ptile){
                gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;
            }else{
                if(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].tiles
                        [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldx/GlobalGameThreadConfigs.tilesize))]
                        [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldy/GlobalGameThreadConfigs.tilesize))]
                        [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldz)] == 54){
                    GlobalGameThreadConfigs.Vehicles[currentmap][vehicle].engines--;
                }
                GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].tiles
                        [(int) (Math.round(x/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldx/GlobalGameThreadConfigs.tilesize))]
                        [(int) (Math.round(y/GlobalGameThreadConfigs.tilesize)-Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldy/GlobalGameThreadConfigs.tilesize))]
                        [(int) (z-GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][vehicle].worldz)] = 46;
            }}else {
                for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] != null) {
                        if (Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldx / GlobalGameThreadConfigs.tilesize) == Math.round(x / GlobalGameThreadConfigs.tilesize) && Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldy / GlobalGameThreadConfigs.tilesize) == Math.round(y / GlobalGameThreadConfigs.tilesize) && GlobalGameThreadConfigs.obj[currentmap][ii].worldz == z) {
                            if (!GlobalGameThreadConfigs.obj[currentmap][ii].invincible) {
                                GlobalGameThreadConfigs.obj[currentmap][ii].health -= TrueAttackDamage;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].playSE();
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].invincible = true;
                                ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii], GlobalGameThreadConfigs.obj[MainGame.currentmap][ii]);
                                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].health <= 0) {
                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx / GlobalGameThreadConfigs.tilesize)][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy / GlobalGameThreadConfigs.tilesize)][(int) GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz] = 46;
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].getDestroyedForm();
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].HandleItems();
                                }
                                ii = GlobalGameThreadConfigs.obj[1].length;
                            }
                        }
                    }
                }
            }
        }
    }}