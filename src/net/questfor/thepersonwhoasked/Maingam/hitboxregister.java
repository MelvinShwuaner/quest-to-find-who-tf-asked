package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Player;
import net.questfor.thepersonwhoasked.entities.Vehicles.Vehicle;
import net.questfor.thepersonwhoasked.objects.FlyingTile;
import net.questfor.thepersonwhoasked.objects.nulitem;

import java.util.Random;

import static net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs.Vehicles;
import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;

//MANAGES HITBOX'S AND COLLISIONS
public class hitboxregister {
    static MainGame gp;

    hitboxregister(MainGame gpp) {
        this.gp = gpp;
    }
//COLLISIONS
    /**collison between entity and world, doesnt return anything**/
    public void TileColide(LivingEntity entity) {
        //checks collision between entity and world
        try {
            int entityleftworldx = (int) (entity.worldx + entity.hitbox.x);
            int entityrightworldx = (int) (entity.worldx + entity.hitbox.x + entity.hitbox.width);
            int entitytopworldy = (int) (entity.worldy + entity.hitbox.y);
            int entitybottomworldy = (int) (entity.worldy + entity.hitbox.y + entity.hitbox.height);
            int entityleftcol = entityleftworldx / GlobalGameThreadConfigs.tilesize;
            int entityrightcol = entityrightworldx / GlobalGameThreadConfigs.tilesize;
            int entitytoprow = entitytopworldy / GlobalGameThreadConfigs.tilesize;
            int entitybottomrow = entitybottomworldy / GlobalGameThreadConfigs.tilesize;
            int var10000;
            int worldz1 = (int) entity.worldz;
            int tileNUM1, tileNUM2;
            switch (entity.direction) {
                case "up":
                    var10000 = (int) (entitytopworldy - entity.speed);
                    entitytoprow = var10000 / GlobalGameThreadConfigs.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz1];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz1];
                    if(!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air){
                        entity.hitboxe = true;
                    }
                    if(MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot){
                        entity.burning = true;
                    }
                    break;
                case "down":
                    var10000 = (int) (entitybottomworldy + entity.speed);
                    entitybottomrow = var10000 / GlobalGameThreadConfigs.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz1];
                    tileNUM2= MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz1];
                    if(!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air){
                        entity.hitboxe = true;
                    }
                    if(MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot){
                        entity.burning = true;
                    }
                    break;
                case "left":
                    var10000 = (int) (entityleftworldx - entity.speed);
                    entityleftcol = var10000 / GlobalGameThreadConfigs.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz1];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz1];
                    if(!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air){
                        entity.hitboxe = true;
                    }
                    if(MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot){
                        entity.burning = true;
                    }
                    break;
                case "right":
                    var10000 = (int) (entityrightworldx + entity.speed);
                    entityrightcol = var10000 / GlobalGameThreadConfigs.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz1];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz1];
                    if(!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air){
                        entity.hitboxe = true;
                    }
                    if(MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot){
                        entity.burning = true;
                    }
                    break;
            }
        } catch (Exception e) {
            Random random = new Random();
            int I = random.nextInt(50);
            entity.worldx = I*GlobalGameThreadConfigs.tilesize;
            entity.worldy = I*GlobalGameThreadConfigs.tilesize;
            entity.worldz = 7;}
    }
    /**collison between Vehicle and world, doesnt return anything**/
    public void colideonTile(Vehicle vehicle){
        if(vehicle.on){
            try {
                switch (vehicle.direction) {
                    case "down" -> {
                        for (int h = 0; h <= vehicle.height; h++) {
                            for (int i = 0; i <= vehicle.width; i++) {
                                for (int z = 0; z < vehicle.depth; z++) {
                                    boolean front = true;
                                    for (int index = 1; index <= vehicle.height - h; index++) {

                                            if (!gp.tilemanager.tile[vehicle.tiles[i][h + index][z]].air) {
                                                front = false;

                                                break;
                                            }

                                    }
                                    if (front) {
                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {
                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                if (vehicle.speed > 8) {
                                                    if (vehicle.tiles[i][h][z] != 56) {
                                                        if (vehicle.tiles[i][h][z] != 60)
                                                            GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                        else {
                                                            vehicle.tiles[i][h][z] = 46;
                                                        }
                                                        vehicle.speed = 0;
                                                    } else {
                                                        GeneralHandler.Explode((int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 6), 1, true, vehicle.Vindex);
                                                        vehicle.speed = vehicle.speed - 5;
                                                        vehicle.tiles[i][h][z] = 46;
                                                    }
                                                } else if (vehicle.speed > 4) {
                                                    if (vehicle.tiles[i][h][z] != 56)
                                                        vehicle.speed -= 4;
                                                    else
                                                        vehicle.speed -= 1;
                                                    for (i = 0; i <= vehicle.width; i++) {
                                                        for (z = 0; z < vehicle.depth; z++) {
                                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                                if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {
                                                                    int zmap = (int) (z + vehicle.worldz);
                                                                    int xvol = 0;
                                                                    try {
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h + 1)][(int) (z + vehicle.worldz)]].air) {
                                                                            zmap++;
                                                                        }
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h + 1)][(int) (z + vehicle.worldz + 1)]].air) {
                                                                            xvol = 5;
                                                                        }
                                                                    } catch (Exception ignored) {
                                                                    }
                                                                    if(vehicle.tiles[i][h][z] != 64){
                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                    gp,
                                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)],
                                                                                    gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name,
                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                    zmap,
                                                                                    (int) vehicle.speed,
                                                                                    xvol,
                                                                                    (int) (vehicle.speed + 9)
                                                                            );
                                                                            break;
                                                                        }
                                                                    }}else{

                                                                        for(int obj = 0; obj < GlobalGameThreadConfigs.obj[1].length; obj++){
                                                                            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][obj] != null){
                                                                                LivingEntity object =GlobalGameThreadConfigs.obj[MainGame.currentmap][obj];
                                                                                if(object.worldx== (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize))
                                                                                &&object.worldy ==  (vehicle.worldy + ((h-1) * GlobalGameThreadConfigs.tilesize))
                                                                                &&object.worldz == (z + vehicle.worldz)){

                                                                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][obj].name.equals("chest")){
                                                                                    UI.additemtoentity(GlobalGameThreadConfigs.obj[MainGame.currentmap][obj], new nulitem
                                                                                    (gp,
                                                                                     MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)],
                                                                                     gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name
                                                                                    ));
                                                                                }}
                                                                            }
                                                                        }
                                                                    }
                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)] = 46;
                                                                    if (vehicle.tiles[i][h][z] != 56 && vehicle.tiles[i][h][z] != 60 && vehicle.tiles[i][h][z] != 64) {
                                                                        if (vehicle.tiles[i][h][z] == 54) {
                                                                            vehicle.engines--;
                                                                        }
                                                                        for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                        gp,
                                                                                        vehicle.tiles[i][h][z], gp.tilemanager.tile[vehicle.tiles[i][h][z]].name,
                                                                                        (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                        (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                        zmap,
                                                                                        (int) vehicle.speed,
                                                                                        xvol,
                                                                                        (int) -(vehicle.speed + 9)
                                                                                );
                                                                                break;
                                                                            }
                                                                        }
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    vehicle.speed = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    case "up" -> {
                        for (int h = 0; h <= vehicle.height; h++) {
                            for (int i = 0; i <= vehicle.width; i++) {
                                for (int z = 0; z < vehicle.depth; z++) {
                                    boolean front = true;
                                    for (int index = h; index > 0; index--) {
                                            if (!gp.tilemanager.tile[vehicle.tiles[i][h - index][z]].air) {
                                                front = false;

                                                break;

                                        }
                                    }
                                    if (front) {

                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {
                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {

                                                if (vehicle.speed > 8) {
                                                    if (vehicle.tiles[i][h][z] != 57) {
                                                        if (vehicle.tiles[i][h][z] != 61)
                                                            GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                        else {
                                                            vehicle.tiles[i][h][z] = 46;
                                                        }
                                                        vehicle.speed = 0;
                                                    } else {
                                                        GeneralHandler.Explode((int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 6), 1, true, vehicle.Vindex);
                                                        vehicle.speed = vehicle.speed - 5;
                                                        vehicle.tiles[i][h][z] = 46;
                                                    }
                                                } else if (vehicle.speed > 4) {
                                                    if (vehicle.tiles[i][h][z] != 57)
                                                        vehicle.speed -= 4;
                                                    else
                                                        vehicle.speed -= 1;
                                                    for (i = 0; i <= vehicle.width; i++) {
                                                        for (z = 0; z < vehicle.depth; z++) {
                                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                                if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {
                                                                    int zmap = (int) (z + vehicle.worldz);
                                                                    int xvol = 0;
                                                                    try {
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h - 1)][(int) (z + vehicle.worldz)]].air) {
                                                                            zmap++;
                                                                        }
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h - 1)][(int) (z + vehicle.worldz + 1)]].air) {
                                                                            xvol = 5;
                                                                        }
                                                                    } catch (Exception ignored) {
                                                                    }
                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                    gp,
                                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)],
                                                                                    gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name,
                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                    zmap,
                                                                                    (int) vehicle.speed,
                                                                                    xvol,
                                                                                    (int) (vehicle.speed + 9)
                                                                            );
                                                                            break;
                                                                        }
                                                                    }
                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)] = 46;
                                                                    if (vehicle.tiles[i][h][z] != 57 && vehicle.tiles[i][h][z] != 61) {
                                                                        if (vehicle.tiles[i][h][z] == 54) {
                                                                            vehicle.engines--;
                                                                        }
                                                                        for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                        gp,
                                                                                        vehicle.tiles[i][h][z], gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name,
                                                                                        (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                        (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                        zmap,
                                                                                        (int) vehicle.speed,
                                                                                        xvol,
                                                                                        (int) -(vehicle.speed + 9)
                                                                                );
                                                                                break;
                                                                            }
                                                                        }
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    vehicle.speed = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    case "left" -> {
                        for (int i = 0; i <= vehicle.width; i++) {
                            for (int h = 0; h <= vehicle.height; h++) {
                                for (int z = 0; z < vehicle.depth; z++) {
                                    boolean front = true;
                                    for (int index = i; index > 0; index--) {
                                            if (!gp.tilemanager.tile[vehicle.tiles[i-index][h][z]].air) {
                                                front = false;

                                                break;
                                            }
                                    }
                                    if (front) {
                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {

                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                if (vehicle.speed > 8) {
                                                    if (vehicle.tiles[i][h][z] != 58) {
                                                        if (vehicle.tiles[i][h][z] != 62)
                                                            GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                        else {
                                                            vehicle.tiles[i][h][z] = 46;
                                                        }
                                                        vehicle.speed = 0;
                                                    } else {
                                                        GeneralHandler.Explode((int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 6), 1, true, vehicle.Vindex);
                                                        vehicle.speed = vehicle.speed - 5;
                                                        vehicle.tiles[i][h][z] = 46;
                                                    }
                                                } else if (vehicle.speed > 4) {
                                                    if (vehicle.tiles[i][h][z] != 58)
                                                        vehicle.speed -= 4;
                                                    else
                                                        vehicle.speed -= 1;
                                                    for (i = 0; i <= vehicle.width; i++) {
                                                        for (z = 0; z < vehicle.depth; z++) {
                                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                                if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {
                                                                    int zmap = (int) (z + vehicle.worldz);
                                                                    int xvol = 0;
                                                                    try {
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h + 1)][(int) (z + vehicle.worldz)]].air) {
                                                                            zmap++;
                                                                        }
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h + 1)][(int) (z + vehicle.worldz + 1)]].air) {
                                                                            xvol = 5;
                                                                        }
                                                                    } catch (Exception ignored) {
                                                                    }
                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                    gp,
                                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)],
                                                                                    gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name,
                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                    zmap,
                                                                                    (int) vehicle.speed,
                                                                                    xvol,
                                                                                    (int) (vehicle.speed + 9)
                                                                            );
                                                                            break;
                                                                        }
                                                                    }
                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)] = 46;
                                                                    if (vehicle.tiles[i][h][z] != 58 && vehicle.tiles[i][h][z] != 62) {
                                                                        if (vehicle.tiles[i][h][z] == 54) {
                                                                            vehicle.engines--;
                                                                        }
                                                                        for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                        gp,
                                                                                        vehicle.tiles[i][h][z], gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name,
                                                                                        (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                        (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                        zmap,
                                                                                        (int) vehicle.speed,
                                                                                        xvol,
                                                                                        (int) -(vehicle.speed + 9)
                                                                                );
                                                                                break;
                                                                            }
                                                                        }
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    vehicle.speed = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    case "right" -> {
                        for (int i = 0; i <= vehicle.width; i++) {
                            for (int h = 0; h <= vehicle.height; h++) {
                                for (int z = 0; z < vehicle.depth; z++) {
                                    boolean front = true;
                                    for (int index = 1; index <= vehicle.width - i; index++) {

                                            if (!gp.tilemanager.tile[vehicle.tiles[i + index][h][z]].air) {
                                                front = false;

                                                break;
                                            }

                                    }
                                    if (front) {
                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {
                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                if (vehicle.speed > 8) {
                                                    if (vehicle.tiles[i][h][z] != 59) {
                                                        if (vehicle.tiles[i][h][z] != 63)
                                                            GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                        else {
                                                            vehicle.tiles[i][h][z] = 46;
                                                        }
                                                        vehicle.speed = 0;
                                                    } else {
                                                        GeneralHandler.Explode((int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (vehicle.speed - 5), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 6), 1, true, vehicle.Vindex);
                                                        vehicle.speed = vehicle.speed - 5;
                                                        vehicle.tiles[i][h][z] = 46;
                                                    }
                                                } else if (vehicle.speed > 4) {
                                                    if (vehicle.tiles[i][h][z] != 59)
                                                        vehicle.speed -= 4;
                                                    else
                                                        vehicle.speed -= 1;
                                                    for (i = 0; i <= vehicle.width; i++) {
                                                        for (z = 0; z < vehicle.depth; z++) {
                                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                                if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].air) {
                                                                    int zmap = (int) (z + vehicle.worldz);
                                                                    int xvol = 0;
                                                                    try {
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h + 1)][(int) (z + vehicle.worldz)]].air) {
                                                                            zmap++;
                                                                        }
                                                                        if (!MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h + 1)][(int) (z + vehicle.worldz + 1)]].air) {
                                                                            xvol = 5;
                                                                        }
                                                                    } catch (Exception ignored) {
                                                                    }
                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                    gp,
                                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)],
                                                                                    gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name,
                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                    zmap,
                                                                                    (int) vehicle.speed,
                                                                                    xvol,
                                                                                    (int) (vehicle.speed + 9)
                                                                            );
                                                                            break;
                                                                        }
                                                                    }
                                                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)] = 46;
                                                                    if (vehicle.tiles[i][h][z] != 59 && vehicle.tiles[i][h][z] != 63) {
                                                                        if (vehicle.tiles[i][h][z] == 54) {
                                                                            vehicle.engines--;
                                                                        }
                                                                        for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                        gp,
                                                                                        vehicle.tiles[i][h][z], gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)][(int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h)][(int) (z + vehicle.worldz)]].name,
                                                                                        (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                        (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                        zmap,
                                                                                        (int) vehicle.speed,
                                                                                        xvol,
                                                                                        (int) -(vehicle.speed + 9)
                                                                                );
                                                                                break;
                                                                            }
                                                                        }
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    vehicle.speed = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            } catch (Exception e){

            }
        }
    }


    /**collision between a vehicle and another vehicle, doesnt return anything**/
    public void colideonVehicle(Vehicle vehicle){
        if(vehicle.on)
            {
            switch (vehicle.direction) {
            case "down" -> {
                                    for (int h = 0; h <= vehicle.height; h++) {
                                        for (int i = 0; i <= vehicle.width; i++) {
                                            for (int z = 0; z < vehicle.depth; z++) {
                                                boolean front = true;
                                                for (int index = 1; index <= vehicle.height - h; index++) {

                                                        if (!gp.tilemanager.tile[vehicle.tiles[i][h + index][z]].air) {
                                                            front = false;

                                                            break;

                                                    }
                                                }
                                                if (front) {
                                                    try{
                                                    for(int n = 0; n < Vehicles[0].length; n++){
                                                    if(Vehicles[MainGame.currentmap][n] != null){
                                                        if(Vehicles[MainGame.currentmap][n] != vehicle){

                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)-Math.round(Vehicles[MainGame.currentmap][n].worldx/GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h)-(Math.round(Vehicles[MainGame.currentmap][n].worldy/GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz-Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                        if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {

                                                            if (vehicle.speed > 8) {
                                                                if (vehicle.tiles[i][h][z] != 56) {
                                                                    if (vehicle.tiles[i][h][z] != 60)
                                                                        GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                                    else {
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                    vehicle.speed = 0;
                                                                } else {
                                                                    GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, vehicle.Vindex);
                                                                    vehicle.speed = vehicle.speed - 5;
                                                                    vehicle.tiles[i][h][z] = 46;
                                                                }
                                                            } else if (vehicle.speed > 4) {
                                                                if (vehicle.tiles[i][h][z] != 56)
                                                                    vehicle.speed -= 4;
                                                                else
                                                                    vehicle.speed -= 1;
                                                                for (i = 0; i <= vehicle.width; i++) {
                                                                    for (z = 0; z < vehicle.depth; z++) {
                                                                        try{
                                                                        if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {

                                                                            if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {

                                                                                int zmap = (int) (z + vehicle.worldz);
                                                                                int xvol = 0;
                                                                                try {
                                                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h + 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                        zmap++;
                                                                                    }
                                                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h + 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz + 1 - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                        xvol = 5;
                                                                                    }
                                                                                } catch (Exception ignored) {
                                                                                }
                                                                                if (vehicle.tiles[i][h][z] != 64) {
                                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                    gp,
                                                                                                    Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                    gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name,
                                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                    zmap,
                                                                                                    (int) vehicle.speed,
                                                                                                    xvol,
                                                                                                    (int) (vehicle.speed + 9)
                                                                                            );
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                } else {

                                                                                    for (int obj = 0; obj < GlobalGameThreadConfigs.obj[1].length; obj++) {
                                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj] != null) {
                                                                                            LivingEntity object = GlobalGameThreadConfigs.obj[MainGame.currentmap][obj];
                                                                                            if (object.worldx == (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize))
                                                                                                    && object.worldy == (vehicle.worldy + ((h - 1) * GlobalGameThreadConfigs.tilesize))
                                                                                                    && object.worldz == (z + vehicle.worldz)) {

                                                                                                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj].name.equals("chest")) {
                                                                                                    UI.additemtoentity(GlobalGameThreadConfigs.obj[MainGame.currentmap][obj], new nulitem
                                                                                                            (gp,
                                                                                                                    Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                                    gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name));
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)] = 46;
                                                                                if (vehicle.tiles[i][h][z] == 54) {
                                                                                    vehicle.engines--;
                                                                                }
                                                                                for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                gp,
                                                                                                vehicle.tiles[i][h][z], gp.tilemanager.tile[vehicle.tiles[i][h][z]].name,
                                                                                                (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                zmap,
                                                                                                (int) vehicle.speed,
                                                                                                xvol,
                                                                                                (int) -(vehicle.speed + 9)
                                                                                        );
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                vehicle.tiles[i][h][z] = 46;
                                                                            }
                                                                        }
                                                                    }catch (Exception e) {
                                                                        }}
                                                                }
                                                            } else {
                                                                vehicle.speed = 0;
                                                            }
                                                        }
                                                        }
                                                    }
                                                }
                                            }
                                    }catch (Exception ignored){

                                                    }
                                                }

                            }
                        }
                    }
                }
            case "up" -> {
                for (int h = 0; h <= vehicle.height; h++) {
                    for (int i = 0; i <= vehicle.width; i++) {
                        for (int z = 0; z < vehicle.depth; z++) {
                            boolean front = true;
                            for (int index = h; index > 0; index--) {
                                if (!gp.tilemanager.tile[vehicle.tiles[i][h - index][z]].air) {
                                    front = false;

                                    break;

                                }
                            }
                                if (front) {
                                    try{
                                        for(int n = 0; n < Vehicles[0].length; n++){
                                            if(Vehicles[MainGame.currentmap][n] != null){
                                                if(Vehicles[MainGame.currentmap][n] != vehicle){

                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)-Math.round(Vehicles[MainGame.currentmap][n].worldx/GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h)-(Math.round(Vehicles[MainGame.currentmap][n].worldy/GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz-Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                        if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {

                                                            if (vehicle.speed > 8) {
                                                                if (vehicle.tiles[i][h][z] != 56) {
                                                                    if (vehicle.tiles[i][h][z] != 60)
                                                                        GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                                    else {
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                    vehicle.speed = 0;
                                                                } else {
                                                                    GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, vehicle.Vindex);
                                                                    vehicle.speed = vehicle.speed - 5;
                                                                    vehicle.tiles[i][h][z] = 46;
                                                                }
                                                            } else if (vehicle.speed > 4) {
                                                                if (vehicle.tiles[i][h][z] != 56)
                                                                    vehicle.speed -= 4;
                                                                else
                                                                    vehicle.speed -= 1;
                                                                for (i = 0; i <= vehicle.width; i++) {
                                                                    for (z = 0; z < vehicle.depth; z++) {
                                                                        try{
                                                                        if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                                            if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                int zmap = (int) (z + vehicle.worldz);
                                                                                int xvol = 0;
                                                                                try {
                                                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h - 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                        zmap++;
                                                                                    }
                                                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h - 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz + 1 - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                        xvol = 5;
                                                                                    }
                                                                                } catch (Exception ignored) {
                                                                                }
                                                                                if (vehicle.tiles[i][h][z] != 64) {
                                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                    gp,
                                                                                                    Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                    gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name,
                                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                    zmap,
                                                                                                    (int) vehicle.speed,
                                                                                                    xvol,
                                                                                                    (int) (vehicle.speed + 9)
                                                                                            );
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                } else {

                                                                                    for (int obj = 0; obj < GlobalGameThreadConfigs.obj[1].length; obj++) {
                                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj] != null) {
                                                                                            LivingEntity object = GlobalGameThreadConfigs.obj[MainGame.currentmap][obj];
                                                                                            if (object.worldx == (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize))
                                                                                                    && object.worldy == (vehicle.worldy + ((h - 1) * GlobalGameThreadConfigs.tilesize))
                                                                                                    && object.worldz == (z + vehicle.worldz)) {

                                                                                                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj].name.equals("chest")) {
                                                                                                    UI.additemtoentity(GlobalGameThreadConfigs.obj[MainGame.currentmap][obj], new nulitem
                                                                                                            (gp,
                                                                                                                    Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                                    gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name));
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)] = 46;
                                                                                if (vehicle.tiles[i][h][z] == 54) {
                                                                                    vehicle.engines--;
                                                                                }
                                                                                for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                gp,
                                                                                                vehicle.tiles[i][h][z], gp.tilemanager.tile[vehicle.tiles[i][h][z]].name,
                                                                                                (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                zmap,
                                                                                                (int) vehicle.speed,
                                                                                                xvol,
                                                                                                (int) -(vehicle.speed + 9)
                                                                                        );
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                vehicle.tiles[i][h][z] = 46;
                                                                            }
                                                                        }
                                                                    }catch (Exception ignored) {

                                                                        }}
                                                                }
                                                            } else {
                                                                vehicle.speed = 0;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }catch (Exception ignored){

                                    }
                                }

                            }
                        }
                    }
                }
            case "left" -> {
                for (int i = 0; i <= vehicle.width; i++) {
                    for (int h = 0; h <= vehicle.height; h++) {
                        for (int z = 0; z < vehicle.depth; z++) {
                            boolean front = true;
                            for (int index = i; index > 0; index--) {
                                if (!gp.tilemanager.tile[vehicle.tiles[i-index][h][z]].air) {
                                    front = false;

                                    break;
                                }
                            }
                                if (front) {
                                    try{
                                        for(int n = 0; n < Vehicles[0].length; n++){
                                            if(Vehicles[MainGame.currentmap][n] != null){
                                                if(Vehicles[MainGame.currentmap][n] != vehicle){

                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)-Math.round(Vehicles[MainGame.currentmap][n].worldx/GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h)-(Math.round(Vehicles[MainGame.currentmap][n].worldy/GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz-Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                        if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {

                                                            if (vehicle.speed > 8) {
                                                                if (vehicle.tiles[i][h][z] != 56) {
                                                                    if (vehicle.tiles[i][h][z] != 60)
                                                                        GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                                    else {
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                    vehicle.speed = 0;
                                                                } else {
                                                                    GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, vehicle.Vindex);
                                                                    vehicle.speed = vehicle.speed - 5;
                                                                    vehicle.tiles[i][h][z] = 46;
                                                                }
                                                            } else if (vehicle.speed > 4) {
                                                                if (vehicle.tiles[i][h][z] != 56)
                                                                    vehicle.speed -= 4;
                                                                else
                                                                    vehicle.speed -= 1;
                                                                for (i = 0; i <= vehicle.width; i++) {
                                                                    for (z = 0; z < vehicle.depth; z++) {
                                                                        try{
                                                                        if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                                            if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                int zmap = (int) (z + vehicle.worldz);
                                                                                int xvol = 0;
                                                                                try {
                                                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h + 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                        zmap++;
                                                                                    }
                                                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h + 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz + 1 - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                        xvol = 5;
                                                                                    }
                                                                                } catch (Exception ignored) {
                                                                                }
                                                                                if (vehicle.tiles[i][h][z] != 64) {
                                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                    gp,
                                                                                                    Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                    gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name,
                                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                    zmap,
                                                                                                    (int) vehicle.speed,
                                                                                                    xvol,
                                                                                                    (int) (vehicle.speed + 9)
                                                                                            );
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                } else {

                                                                                    for (int obj = 0; obj < GlobalGameThreadConfigs.obj[1].length; obj++) {
                                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj] != null) {
                                                                                            LivingEntity object = GlobalGameThreadConfigs.obj[MainGame.currentmap][obj];
                                                                                            if (object.worldx == (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize))
                                                                                                    && object.worldy == (vehicle.worldy + ((h - 1) * GlobalGameThreadConfigs.tilesize))
                                                                                                    && object.worldz == (z + vehicle.worldz)) {

                                                                                                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj].name.equals("chest")) {
                                                                                                    UI.additemtoentity(GlobalGameThreadConfigs.obj[MainGame.currentmap][obj], new nulitem
                                                                                                            (gp,
                                                                                                                    Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                                    gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name));
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)] = 46;
                                                                                if (vehicle.tiles[i][h][z] == 54) {
                                                                                    vehicle.engines--;
                                                                                }
                                                                                for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                gp,
                                                                                                vehicle.tiles[i][h][z], gp.tilemanager.tile[vehicle.tiles[i][h][z]].name,
                                                                                                (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                zmap,
                                                                                                (int) vehicle.speed,
                                                                                                xvol,
                                                                                                (int) -(vehicle.speed + 9)
                                                                                        );
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                vehicle.tiles[i][h][z] = 46;
                                                                            }
                                                                        }}catch (Exception e) {

                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                vehicle.speed = 0;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }catch (Exception ignored){

                                    }
                                }

                            }
                        }
                    }
                }
            case "right" -> {
                for (int i = 0; i <= vehicle.width; i++) {
                    for (int h = 0; h <= vehicle.height; h++) {
                        for (int z = 0; z < vehicle.depth; z++) {
                            boolean front = true;
                            for (int index = 1; index <= vehicle.width - i; index++) {

                                if (!gp.tilemanager.tile[vehicle.tiles[i + index][h][z]].air) {
                                    front = false;

                                    break;
                                }

                            }
                                if (front) {
                                    try{
                                        for(int n = 0; n < Vehicles[0].length; n++){
                                            if(Vehicles[MainGame.currentmap][n] != null){
                                                if(Vehicles[MainGame.currentmap][n] != vehicle){

                                                    if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i)-Math.round(Vehicles[MainGame.currentmap][n].worldx/GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h)-(Math.round(Vehicles[MainGame.currentmap][n].worldy/GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz-Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                        if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {

                                                            if (vehicle.speed > 8) {
                                                                if (vehicle.tiles[i][h][z] != 56) {
                                                                    if (vehicle.tiles[i][h][z] != 60)
                                                                        GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, 999);
                                                                    else {
                                                                        vehicle.tiles[i][h][z] = 46;
                                                                    }
                                                                    vehicle.speed = 0;
                                                                } else {
                                                                    GeneralHandler.Explode((int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (vehicle.speed - 4), (int) (Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i), (int) (Math.round((vehicle.worldy) / GlobalGameThreadConfigs.tilesize) + h), (int) (z + vehicle.worldz), (int) (vehicle.speed - 5), 1, false, vehicle.Vindex);
                                                                    vehicle.speed = vehicle.speed - 5;
                                                                    vehicle.tiles[i][h][z] = 46;
                                                                }
                                                            } else if (vehicle.speed > 4) {
                                                                if (vehicle.tiles[i][h][z] != 56)
                                                                    vehicle.speed -= 4;
                                                                else
                                                                    vehicle.speed -= 1;
                                                                for (i = 0; i <= vehicle.width; i++) {
                                                                    for (z = 0; z < vehicle.depth; z++) {
                                                                        try{
                                                                            if (!MainGame.tilemanager.tile[vehicle.tiles[i][h][z]].air) {
                                                                                if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                    int zmap = (int) (z + vehicle.worldz);
                                                                                    int xvol = 0;
                                                                                    try {
                                                                                        if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h + 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                            zmap++;
                                                                                        }
                                                                                        if (!MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h + 1) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz + 1 - Vehicles[MainGame.currentmap][n].worldz)]].air) {
                                                                                            xvol = 5;
                                                                                        }
                                                                                    } catch (Exception ignored) {
                                                                                    }
                                                                                    if (vehicle.tiles[i][h][z] != 64) {
                                                                                        for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                                GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                        gp,
                                                                                                        Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                        gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name,
                                                                                                        (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                        (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                        zmap,
                                                                                                        (int) vehicle.speed,
                                                                                                        xvol,
                                                                                                        (int) (vehicle.speed + 9)
                                                                                                );
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                    } else {

                                                                                        for (int obj = 0; obj < GlobalGameThreadConfigs.obj[1].length; obj++) {
                                                                                            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj] != null) {
                                                                                                LivingEntity object = GlobalGameThreadConfigs.obj[MainGame.currentmap][obj];
                                                                                                if (object.worldx == (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize))
                                                                                                        && object.worldy == (vehicle.worldy + ((h - 1) * GlobalGameThreadConfigs.tilesize))
                                                                                                        && object.worldz == (z + vehicle.worldz)) {

                                                                                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][obj].name.equals("chest")) {
                                                                                                        UI.additemtoentity(GlobalGameThreadConfigs.obj[MainGame.currentmap][obj], new nulitem
                                                                                                                (gp,
                                                                                                                        Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)],
                                                                                                                        gp.tilemanager.tile[Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)]].name));
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    Vehicles[MainGame.currentmap][n].tiles[(int) ((Math.round(vehicle.worldx / GlobalGameThreadConfigs.tilesize) + i) - Math.round(Vehicles[MainGame.currentmap][n].worldx / GlobalGameThreadConfigs.tilesize))][(int) ((Math.round(vehicle.worldy / GlobalGameThreadConfigs.tilesize) + h) - (Math.round(Vehicles[MainGame.currentmap][n].worldy / GlobalGameThreadConfigs.tilesize)))][(int) (z + vehicle.worldz - Vehicles[MainGame.currentmap][n].worldz)] = 46;
                                                                                    if (vehicle.tiles[i][h][z] == 54) {
                                                                                        vehicle.engines--;
                                                                                    }
                                                                                    for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                                                                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                                                                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                                                                    gp,
                                                                                                    vehicle.tiles[i][h][z], gp.tilemanager.tile[vehicle.tiles[i][h][z]].name,
                                                                                                    (int) (vehicle.worldx + (i * GlobalGameThreadConfigs.tilesize)),
                                                                                                    (int) (vehicle.worldy + (h * GlobalGameThreadConfigs.tilesize)),
                                                                                                    zmap,
                                                                                                    (int) vehicle.speed,
                                                                                                    xvol,
                                                                                                    (int) -(vehicle.speed + 9)
                                                                                            );
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    vehicle.tiles[i][h][z] = 46;
                                                                                }
                                                                            }}catch (Exception e) {

                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                vehicle.speed = 0;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }catch (Exception ignored){

                                    }
                                }

                            }
                        }
                    }
                }
            }

    }}

    /**collison between entity and Vehicle, doesnt return anything**/
    public void VehicleColide(LivingEntity entity){
        try {
            for (int i = 0; i < Vehicles[0].length; i++) {
                if (Vehicles[MainGame.currentmap][i] != null) {
                        int entityleftworldx = (int) ((entity.worldx - Vehicles[MainGame.currentmap][i].worldx) + entity.hitbox.x);
                        int entityrightworldx = (int) ((entity.worldx - Vehicles[MainGame.currentmap][i].worldx) + entity.hitbox.x + entity.hitbox.width);
                        int entitytopworldy = (int) ((entity.worldy - Vehicles[MainGame.currentmap][i].worldy) + entity.hitbox.y);
                        int entitybottomworldy = (int) ((entity.worldy - Vehicles[MainGame.currentmap][i].worldy) + entity.hitbox.y + entity.hitbox.height);

                        int entityleftcol = entityleftworldx / GlobalGameThreadConfigs.tilesize;
                        int entityrightcol = entityrightworldx / GlobalGameThreadConfigs.tilesize;
                        int entitytoprow = entitytopworldy / GlobalGameThreadConfigs.tilesize;
                        int entitybottomrow = entitybottomworldy / GlobalGameThreadConfigs.tilesize;
                        int var10000;
                        int worldz1 = (int) (entity.worldz - Vehicles[MainGame.currentmap][i].worldz);
                        int tileNUM1, tileNUM2;

                        switch (entity.direction) {
                            case "up":
                                var10000 = (int) (entitytopworldy - entity.speed);
                                entitytoprow = var10000 / GlobalGameThreadConfigs.tilesize;
                                tileNUM1 = Vehicles[MainGame.currentmap][i].tiles[entityleftcol][entitytoprow][worldz1];
                                tileNUM2 = Vehicles[MainGame.currentmap][i].tiles[entityrightcol][entitytoprow][worldz1];
                                if (!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air) {
                                    if(checkEntity(entity, Vehicles)){
                                        entity.hitboxe = true;
                                    }
                                }
                                if (MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot) {
                                    if(checkEntity(entity, Vehicles)){
                                        entity.burning = true;
                                    }
                                }
                                break;
                            case "down":
                                var10000 = (int) (entitybottomworldy + entity.speed);
                                entitybottomrow = var10000 / GlobalGameThreadConfigs.tilesize;
                                tileNUM1 = Vehicles[MainGame.currentmap][i].tiles[entityleftcol][entitybottomrow][worldz1];
                                tileNUM2 = Vehicles[MainGame.currentmap][i].tiles[entityrightcol][entitybottomrow][worldz1];
                                if (!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air) {
                                    if(checkEntity(entity, Vehicles)){
                                        entity.hitboxe = true;
                                    }
                                }
                                if (MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot) {
                                    if(checkEntity(entity, Vehicles)){
                                        entity.burning = true;
                                    }
                                }
                                break;
                            case "left":
                                var10000 = (int) (entityleftworldx - entity.speed);
                                entityleftcol = var10000 / GlobalGameThreadConfigs.tilesize;
                                tileNUM1 = Vehicles[MainGame.currentmap][i].tiles[entityleftcol][entitytoprow][worldz1];
                                tileNUM2 = Vehicles[MainGame.currentmap][i].tiles[entityleftcol][entitybottomrow][worldz1];
                                if (!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air) {
                                    if(checkEntity(entity, Vehicles)){
                                    entity.hitboxe = true;
                                }}
                                if (MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot) {
                                    if(checkEntity(entity, Vehicles)){
                                        entity.burning = true;
                                    }
                                }
                                break;
                            case "right":
                                var10000 = (int) (entityrightworldx + entity.speed);
                                entityrightcol = var10000 / GlobalGameThreadConfigs.tilesize;
                                tileNUM1 = Vehicles[MainGame.currentmap][i].tiles[entityrightcol][entitytoprow][worldz1];
                                tileNUM2 = Vehicles[MainGame.currentmap][i].tiles[entityrightcol][entitybottomrow][worldz1];
                                if (!MainGame.tilemanager.tile[tileNUM1].air || !MainGame.tilemanager.tile[tileNUM2].air) {
                                    if(checkEntity(entity, Vehicles)){
                                        entity.hitboxe = true;
                                    }
                                }
                                if (MainGame.tilemanager.tile[tileNUM1].hot || MainGame.tilemanager.tile[tileNUM2].hot) {
                                    if(checkEntity(entity, Vehicles)){
                                        entity.burning = true;
                                    }
                                }
                                break;
                        }}
            }
        } catch (Exception ignored){

        }
    }
    /**collison between entity/player and a entity in a specified array (monsters/NPCS/OBJECTS), returns the position of the entity in the array**/
    public int EntityColide(LivingEntity entity, LivingEntity[][] target) {
        //checks collision between an entity and another entity
        int index = 999;
        try {
            for (int i = 0; i < target[1].length; i++) {
                if (target[MainGame.currentmap][i] != null) {
                    entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                    entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                    target[MainGame.currentmap][i].hitbox.x = (int) (target[MainGame.currentmap][i].worldx + target[MainGame.currentmap][i].hitbox.x);
                    target[MainGame.currentmap][i].hitbox.y = (int) (target[MainGame.currentmap][i].worldy + target[MainGame.currentmap][i].hitbox.y);
                    switch (entity.direction) {
                        case "up" -> entity.hitbox.y -= entity.speed;
                        case "down" -> entity.hitbox.y += entity.speed;
                        case "left" -> entity.hitbox.x -= entity.speed;
                        case "right" -> entity.hitbox.x += entity.speed;
                    }
                    if (entity.hitbox.intersects(target[MainGame.currentmap][i].hitbox)) {
                        if (entity.worldz ==  target[MainGame.currentmap][i].worldz) {
                            if (target[MainGame.currentmap][i] != entity) {
                                entity.hitboxe = true;
                                index = i;
                            }
                        }
                    }
                    entity.hitbox.x = entity.hitboxdefaultx;
                    entity.hitbox.y = entity.hitboxdefaulty;
                    target[MainGame.currentmap][i].hitbox.x = target[MainGame.currentmap][i].hitboxdefaultx;
                    target[MainGame.currentmap][i].hitbox.y = target[MainGame.currentmap][i].hitboxdefaulty;
                }
            }
        }catch (Exception e){
            crash.main(e);
        }
        return index;
    }
    /**collison between entity and the player, returns true if the entity did colide**/

    public boolean PlayerColide(LivingEntity entity) {
        boolean ContactPlayer = false;
        //checks collision between entity and player
        entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
        entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
        GlobalGameThreadConfigs.player.hitbox.x = (int) (GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.hitbox.x);
        GlobalGameThreadConfigs.player.hitbox.y = (int) (GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.hitbox.y);
        switch (entity.direction) {
            case "up":
                entity.hitbox.y -= entity.speed;
                break;
            case "down":
                entity.hitbox.y += entity.speed;
                break;
            case "left":
                entity.hitbox.x -= entity.speed;
                break;
            case "right":
                entity.hitbox.x += entity.speed;
                break;
        }
        if (entity.hitbox.intersects(GlobalGameThreadConfigs.player.hitbox)) {
            if (entity.worldz == GlobalGameThreadConfigs.player.worldz) {
                entity.hitboxe = true;
                ContactPlayer = true;
            }
        }
        entity.hitbox.x = entity.hitboxdefaultx;
        entity.hitbox.y = entity.hitboxdefaulty;
        GlobalGameThreadConfigs.player.hitbox.x = GlobalGameThreadConfigs.player.hitboxdefaultx;
        GlobalGameThreadConfigs.player.hitbox.y = GlobalGameThreadConfigs.player.hitboxdefaulty;

        return ContactPlayer;
    }


//SAME AS COLIDE BUT DOESNT TRIGGER COLLISION
    /**checks if the entity/player colides a entity in a specified array (monsters/NPCS/OBJECTS), returns true if they did**/

    public boolean checkEntity(LivingEntity entity, LivingEntity[][] target) {
        //checks collision between a entity and another entity
        boolean index = false;
        for (int i = 0; i < target[1].length; i++) {
            if (target[MainGame.currentmap][i] != null) {
                if (entity != target[MainGame.currentmap][i]) {
                    entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                    entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                    target[MainGame.currentmap][i].hitbox.x = (int) (target[MainGame.currentmap][i].worldx + target[MainGame.currentmap][i].hitbox.x);
                    target[MainGame.currentmap][i].hitbox.y = (int) (target[MainGame.currentmap][i].worldy + target[MainGame.currentmap][i].hitbox.y);
                    switch (entity.direction) {
                        case "up":
                            entity.hitbox.y -= entity.speed;
                            break;
                        case "down":
                            entity.hitbox.y += entity.speed;
                            break;
                        case "left":
                            entity.hitbox.x -= entity.speed;
                            break;
                        case "right":
                            entity.hitbox.x += entity.speed;
                            break;
                    }
                    if (entity.hitbox.intersects(target[MainGame.currentmap][i].hitbox)) {
                        if (target[MainGame.currentmap][i] != entity) {
                            index = true;
                        }
                    }
                    entity.hitbox.x = entity.hitboxdefaultx;
                    entity.hitbox.y = entity.hitboxdefaulty;
                    target[MainGame.currentmap][i].hitbox.x = target[MainGame.currentmap][i].hitboxdefaultx;
                    target[MainGame.currentmap][i].hitbox.y = target[MainGame.currentmap][i].hitboxdefaulty;
                }

            }
        }
        return index;
    }
    //VEHICLES
    /**checks if the entity of a specified array is on top of a vehicle, if it is, the entity will become a passenger of the vehicle**/
    public void checkentityonvehicle(Vehicle vehicle, LivingEntity[][] target) {
        //checks collision between an entity and another entity
        try {
            for (int i = 0; i < target[1].length; i++) {
                if (target[MainGame.currentmap][i] != null) {
                    if((target[MainGame.currentmap][i].worldx >= vehicle.worldx && target[MainGame.currentmap][i].worldx <= vehicle.worldx+(vehicle.width*GlobalGameThreadConfigs.tilesize))
                            && (target[MainGame.currentmap][i].worldy >= vehicle.worldy && target[MainGame.currentmap][i].worldy <= vehicle.worldy+(vehicle.height*GlobalGameThreadConfigs.tilesize))
                            && (target[MainGame.currentmap][i].worldz > vehicle.worldz && target[MainGame.currentmap][i].worldz <= vehicle.worldz+(vehicle.depth*GlobalGameThreadConfigs.tilesize))){

                        if(returntileworldzonvehicle(target[MainGame.currentmap][i], vehicle.Vindex)){
                            if(!target[MainGame.currentmap][i].passanger){
                                boolean newc = true;
                                for(int f = 0; f < vehicle.passengers.size(); i++){
                                    if(vehicle.passengers.get(i) == target[MainGame.currentmap][i]){
                                        newc = false;
                                        break;
                                    }
                                }
                                if (newc){
                                target[MainGame.currentmap][i].enterVehcile(vehicle.Vindex);
                                System.out.println("s");
                            }}
                        }}else if( target[MainGame.currentmap][i].passanger){
                        if (target[MainGame.currentmap][i].vehindex == vehicle.Vindex){
                        target[MainGame.currentmap][i].exitVehicle(vehicle.Vindex);

                    }
                }}
            }
        }catch (Exception e){
        }
    }

    /**checks if the player is on top of the vehicle, if they are, the player will become a passenger of the vehicle**/
    public void checkplayeronvehicle(Vehicle vehicle) {
        try {
                    if((GlobalGameThreadConfigs.player.worldx >= vehicle.worldx && GlobalGameThreadConfigs.player.worldx <= vehicle.worldx+(vehicle.width*GlobalGameThreadConfigs.tilesize))
                            && (GlobalGameThreadConfigs.player.worldy >= vehicle.worldy && GlobalGameThreadConfigs.player.worldy <= vehicle.worldy+(vehicle.height*GlobalGameThreadConfigs.tilesize))
                            && (GlobalGameThreadConfigs.player.worldz > vehicle.worldz && GlobalGameThreadConfigs.player.worldz <= vehicle.worldz+(vehicle.depth*GlobalGameThreadConfigs.tilesize))){

                        if(returntileworldzonvehicle(GlobalGameThreadConfigs.player, vehicle.Vindex)){
                            if(!GlobalGameThreadConfigs.player.passanger){
                                GlobalGameThreadConfigs.player.enterVehcile(vehicle.Vindex);
                            }
                        }}else if( GlobalGameThreadConfigs.player.passanger) {
                        if (GlobalGameThreadConfigs.player.vehindex == vehicle.Vindex){
                            GlobalGameThreadConfigs.player.exitVehicle(vehicle.Vindex);
                    }
                    }
        }catch (Exception e){
            crash.main(e);
        }
    }
    /**CHECKS IF ENTITY IN SPECIFIED ARRAY IS IN THE CORDINATES. RETURNS TRUE IF YES**/
    public boolean checkEntityWorld(double x, double y, double z, LivingEntity[][] target) {
        //checks collision between a entity and another entity
        boolean index = true;
        int targetx, targety, targetz;
        for (int i = 0; i < target[1].length; i++) {
            if (target[MainGame.currentmap][i] != null) {
                targetx = (int) Math.round(target[MainGame.currentmap][i].worldx/GlobalGameThreadConfigs.tilesize);
                targety = (int) Math.round(target[MainGame.currentmap][i].worldy/GlobalGameThreadConfigs.tilesize);
                targetz = (int) (target[MainGame.currentmap][i].worldz);
                if(targetx == x && targety == y){
                    if(targetz == z){
                    index = false;
                }}
        }
    }
        return index;}
    /**CHECKS A TILE FROM EITHER THE WORLD OR A VEHICLE AT THE CORDINATES. RETURNS TRUE IF THE TILE IS NOT AIR**/
    public boolean checktileworld(int x, int y, int z) {
        boolean yes = false;
        if (x > 0 && x < 200 && y > 0 && y < 200 && z > 0 && z < 8){
             yes = MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][y][z]].air;
    }
        try{
            for (int i = 0; i < Vehicles[0].length; i++) {
                if (Vehicles[MainGame.currentmap][i] != null) {
                    yes = MainGame.tilemanager.tile[Vehicles[MainGame.currentmap][i].tiles[(int) (x - Math.round(Vehicles[MainGame.currentmap][i].worldx / GlobalGameThreadConfigs.tilesize))][(int) (y - Math.round(Vehicles[MainGame.currentmap][i].worldy / GlobalGameThreadConfigs.tilesize))][(int) (z - Vehicles[MainGame.currentmap][i].worldz)]].air;
                }
            }
        }catch (Exception ignored){
        }
        return yes;
    }
    /**CHECKS IF ENTITY IN SPECIFIED ARRAY IS ON TOP OF THE CORDINATES. RETURNS FALSE IF YES**/
    public boolean checkentitywall(double x, double y, double z, LivingEntity[][] target, LivingEntity entity) {
        //checks collision between a entity and another entity
        boolean index = true;
        int targetx = 0, targety, targetz;
        for (int i = 0; i < target[1].length; i++) {
            if (target[MainGame.currentmap][i] != null) {
                if(target[MainGame.currentmap][i] != entity)
                    targetx = (int) Math.round(target[MainGame.currentmap][i].worldx/GlobalGameThreadConfigs.tilesize);
                targety = (int) Math.round(target[MainGame.currentmap][i].worldy/GlobalGameThreadConfigs.tilesize);
                targetz = (int) (target[MainGame.currentmap][i].worldz);
                if(targetx == x && targety == y){
                    if(targetz == z+1){
                        index = false;
                    }}
            }
        }
        return index;}
    /**CHECKS A TILE THAT IS ON TOP OF THE ENTITY, RETURNES FALSE IF THE TILE IS NOT AIR**/
    public boolean checkWALL(LivingEntity entity){
        boolean index = true;
        try {
            int TileNum1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(entity.worldx / GlobalGameThreadConfigs.tilesize)][(int) Math.round(entity.worldy / GlobalGameThreadConfigs.tilesize)][(int) entity.worldz + 1];
            if (!MainGame.tilemanager.tile[TileNum1].air) {
                index = false;
            }

        } catch (Exception e){

        }
        return index;
}
/**CHECKS THE TILE UNDER THE ENTITY, RETURNS TRUE IF ITS AIR**/
    public boolean returntileworldz(LivingEntity entity) {
        //checks collision between entity and world
        boolean index = false;
        boolean shouldtrigger = !checkEntity(GlobalGameThreadConfigs.player, GlobalGameThreadConfigs.Monsters) && !checkEntity(GlobalGameThreadConfigs.player, GlobalGameThreadConfigs.NPCS);

        try {
            int TileNum1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(entity.worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(entity.worldy/GlobalGameThreadConfigs.tilesize)][(int) (entity.worldz-1)];
            if(shouldtrigger){
                if (MainGame.tilemanager.tile[TileNum1].air){
                    if(entity.worldz > 0)
                        index = true;
                }}
        }catch (Exception e) {

            Random random = new Random();
            int I = random.nextInt(50);
            entity.worldx = I*GlobalGameThreadConfigs.tilesize;
            entity.worldy = I*GlobalGameThreadConfigs.tilesize;
            entity.worldz = 7;

        }
        return index;
    }
    /**CHECKS THE TILE INSIDE OF A VEHICLE THAT IS UNDER A ENTITY, IF IT IS AIR IT WILL RETURN FALSE**/
    public boolean returntileworldzonvehicle(LivingEntity entity, int vehindex) {
        boolean index = true;
        try{
        int TileNum1 = Vehicles[MainGame.currentmap][vehindex].tiles[(int) (Math.round(entity.worldx / GlobalGameThreadConfigs.tilesize) - Math.round(Vehicles[MainGame.currentmap][vehindex].worldx / GlobalGameThreadConfigs.tilesize))][(int) (Math.round(entity.worldy / GlobalGameThreadConfigs.tilesize) - Math.round(Vehicles[MainGame.currentmap][vehindex].worldy / GlobalGameThreadConfigs.tilesize))][(int) (entity.worldz - Vehicles[MainGame.currentmap][vehindex].worldz)-1];

            if (MainGame.tilemanager.tile[TileNum1].air) {
                index = false;
            }}catch (Exception ignored){
        }
            return index;

    }
    /**CHECKS IF A ENTITY COLIDES WITH A OBJECT, RETURNS THE POSITION OF THE OBJECT IN THE OBJECT ARRAY**/
    public int worldzobjectreturn(LivingEntity entity, LivingEntity[][] target) {
        //checks collision between a entity and another entity
        int index = 999;
        try {
            for (int i = 0; i < target[1].length; i++) {
                if (target[MainGame.currentmap][i] != null) {
                    entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                    entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                    target[MainGame.currentmap][i].hitbox.x = (int) (target[MainGame.currentmap][i].worldx + target[MainGame.currentmap][i].hitbox.x);
                    target[MainGame.currentmap][i].hitbox.y = (int) (target[MainGame.currentmap][i].worldy + target[MainGame.currentmap][i].hitbox.y);
                    switch (entity.direction) {
                        case "up" -> entity.hitbox.y -= entity.speed;
                        case "down" -> entity.hitbox.y += entity.speed;
                        case "left" -> entity.hitbox.x -= entity.speed;
                        case "right" -> entity.hitbox.x += entity.speed;
                    }
                    if (entity.hitbox.intersects(target[MainGame.currentmap][i].hitbox)) {
                        if (entity.worldz == target[MainGame.currentmap][i].worldz) {
                            if (target[MainGame.currentmap][i] != entity) {
                                index = i;
                            }
                        }
                    }
                    entity.hitbox.x = entity.hitboxdefaultx;
                    entity.hitbox.y = entity.hitboxdefaulty;
                    target[MainGame.currentmap][i].hitbox.x = target[MainGame.currentmap][i].hitboxdefaultx;
                    target[MainGame.currentmap][i].hitbox.y = target[MainGame.currentmap][i].hitboxdefaulty;
                }
            }
        }catch (Exception e){
            crash.main(e);
        }
        return index;
    }
    /**CHECKS IF THE ENTITY COLIDES WITH A ENTITY IN A SPECIFIED ARRAY, RETURNS FALSE IF TRUE**/
    public boolean worldzentityreturn(LivingEntity entity, LivingEntity[][] target) {
        //checks collision between a entity and another entity
        boolean index = true;
        for (int i = 0; i < target[1].length; i++) {
            if (target[MainGame.currentmap][i] != null) {
                entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                target[MainGame.currentmap][i].hitbox.x = (int) (target[MainGame.currentmap][i].worldx + target[MainGame.currentmap][i].hitbox.x);
                target[MainGame.currentmap][i].hitbox.y = (int) (target[MainGame.currentmap][i].worldy + target[MainGame.currentmap][i].hitbox.y);
                switch (entity.direction) {
                    case "up":
                        entity.hitbox.y -= entity.speed;
                        break;
                    case "down":
                        entity.hitbox.y += entity.speed;
                        break;
                    case "left":
                        entity.hitbox.x -= entity.speed;
                        break;
                    case "right":
                        entity.hitbox.x += entity.speed;
                        break;
                }
                if (entity.hitbox.intersects(target[MainGame.currentmap][i].hitbox)) {
                    if (target[MainGame.currentmap][i] != entity) {
                        index = false;
                    }

                }
                entity.hitbox.x = entity.hitboxdefaultx;
                entity.hitbox.y = entity.hitboxdefaulty;
                target[MainGame.currentmap][i].hitbox.x = target[MainGame.currentmap][i].hitboxdefaultx;
                target[MainGame.currentmap][i].hitbox.y = target[MainGame.currentmap][i].hitboxdefaulty;
            }
        }

        return index;
    }

}