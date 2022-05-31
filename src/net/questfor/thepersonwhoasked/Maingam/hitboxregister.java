package net.questfor.thepersonwhoasked.Maingam;

import com.sun.jdi.InconsistentDebugInfoException;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
//MANAGES HITBOX'S AND COLLISIONS
public class hitboxregister {
    static MainGame gp;

    hitboxregister(MainGame gpp) {
        this.gp = gpp;
    }

    public void checkTile(LivingEntity entity) {
        int knockback = 1;
        boolean hasfinushed = false;
        //checks collision between entity and world
        try {
            int entityleftworldx = (int) (entity.worldx + entity.hitbox.x);
            int entityrightworldx = (int) (entity.worldx + entity.hitbox.x + entity.hitbox.width);
            int entitytopworldy = (int) (entity.worldy + entity.hitbox.y);
            int entitybottomworldy = (int) (entity.worldy + entity.hitbox.y + entity.hitbox.height);
            int entityleftcol = entityleftworldx / MainGame.tilesize;
            int entityrightcol = entityrightworldx / MainGame.tilesize;
            int entitytoprow = entitytopworldy / MainGame.tilesize;
            int entitybottomrow = entitybottomworldy / MainGame.tilesize;
            int var10000;
            int worldz1 = (int) entity.worldz, worldz2 = (int) (entity.worldz+1), worldz3 = (int) (entity.worldz+2);
            int tileNUM1, tileNUM2, tileNUM3, tileNUM4, tileNUM5, tileNUM6;
            switch (entity.direction) {
                case "up":
                    var10000 = (int) (entitytopworldy - entity.speed);
                    entitytoprow = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz1];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz2];
                    tileNUM3 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz1];
                    tileNUM4 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz2];
                    tileNUM5 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz3];
                    tileNUM6= MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz3];
                    if(((tileNUM1 != 46 && tileNUM2 != 46 )|| (tileNUM3 != 46 && tileNUM4 != 46)) || (tileNUM5 != 46 && tileNUM6 != 46)){
                        entity.hitboxe = true;
                    }
                    break;
                case "down":
                    var10000 = (int) (entitybottomworldy + entity.speed);
                    entitybottomrow = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz1];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz2];
                    tileNUM3 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz1];
                    tileNUM4 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz2];
                    tileNUM5 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz3];
                    tileNUM6 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz3];
                    if(((tileNUM1 != 46 && tileNUM2 != 46 )|| (tileNUM3 != 46 && tileNUM4 != 46)) || (tileNUM5 != 46 && tileNUM6 != 46)){
                        entity.hitboxe = true;
                    }
                    break;
                case "left":
                    var10000 = (int) (entityleftworldx - entity.speed);
                    entityleftcol = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz1];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz2];
                    tileNUM3 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz1];
                    tileNUM4 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz2];
                    tileNUM5 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitybottomrow][worldz3];
                    tileNUM6 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityleftcol][entitytoprow][worldz3];
                    if(((tileNUM1 != 46 && tileNUM2 != 46 )|| (tileNUM3 != 46 && tileNUM4 != 46)) || (tileNUM5 != 46 && tileNUM6 != 46)){
                        entity.hitboxe = true;
                    }
                    break;
                case "right":
                    var10000 = (int) (entityrightworldx + entity.speed);
                    entityrightcol = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz1];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz2];
                    tileNUM3 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz1];
                    tileNUM4 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz2];
                    tileNUM5 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitybottomrow][worldz3];
                    tileNUM6 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][entityrightcol][entitytoprow][worldz3];
                    if(((tileNUM1 != 46 && tileNUM2 != 46 )|| (tileNUM3 != 46 && tileNUM4 != 46)) || (tileNUM5 != 46 && tileNUM6 != 46)){
                        entity.hitboxe = true;
                    }

                    break;
            }
        } catch (Exception e) {
            if (knockback <= 2){
                switch (entity.direction) {
                    case "up" -> entity.worldy = entity.worldy + knockback;
                    case "down" -> entity.worldy = entity.worldy - knockback;
                    case "right" -> entity.worldx = entity.worldx - knockback;
                    case "left" -> entity.worldx = entity.worldx + knockback;
                }
        }
            while (!hasfinushed){
                knockback++;
                if(knockback > 2){
                    if(!entity.hitboxe){
                        switch (entity.direction) {
                            case "up" -> entity.worldy = entity.worldy + knockback;
                            case "down" -> entity.worldy = entity.worldy - knockback;
                            case "right" -> entity.worldx = entity.worldx - knockback;
                            case "left" -> entity.worldx = entity.worldx + knockback;
                        }
                    }
                }
                if(knockback >= 7){
                    hasfinushed = true;
                }
            }

        }
    }


    public int checkObject(LivingEntity entity, boolean player) {
        //checks collision between objects and entities
        int index = 999;
        try {
            for (int i = 0; i < GlobalGameThreadConfigs.obj[1].length; i++) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i] != null && GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox != null) {
                    if(entity !=  GlobalGameThreadConfigs.obj[MainGame.currentmap][i]){
                    entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                    entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.x = (int) (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx + GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.x);
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.y = (int) (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy + GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.y);
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
                    if (entity.hitbox.intersects(GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox)) {
                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].collision) {
                            if (entity.worldz >= GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz-16 && entity.worldz < GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz) {
                                entity.hitboxe = true;
                            }
                            entity.hascolided = true;
                        }
                        if (player) {
                            index = i;
                        }

                    }
                    entity.hitbox.x = entity.hitboxdefaultx;
                    entity.hitbox.y = entity.hitboxdefaulty;
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.x = GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitboxdefaultx;
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.y = GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitboxdefaulty;
                }}
            }

        }catch (Exception e){
            crash.main(e);
        }
        return index;
    }

    public int EntityColide(LivingEntity entity, LivingEntity[][] target) {
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
                        if (entity.worldz >= target[MainGame.currentmap][i].worldz-16 && entity.worldz <= target[MainGame.currentmap][i].worldz) {
                            if (target[MainGame.currentmap][i] != entity) {
                                entity.hitboxe = true;
                                index = i;
                            }
                        }
                        entity.hascolided = true;
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

    public boolean PlayerColide(LivingEntity entity) {
        boolean ContactPlayer = false;
        //checks collision between entity and player
        entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
        entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
        gp.player.hitbox.x = (int) (gp.player.worldx + gp.player.hitbox.x);
        gp.player.hitbox.y = (int) (gp.player.worldy + gp.player.hitbox.y);
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
        if (entity.hitbox.intersects(gp.player.hitbox)) {
            if (entity.worldz == gp.player.worldz) {
                entity.hitboxe = true;
                ContactPlayer = true;
            }
        }
        entity.hitbox.x = entity.hitboxdefaultx;
        entity.hitbox.y = entity.hitboxdefaulty;
        gp.player.hitbox.x = gp.player.hitboxdefaultx;
        gp.player.hitbox.y = gp.player.hitboxdefaulty;

        return ContactPlayer;
    }

    public boolean worldzobjectreturn(LivingEntity entity) {
        boolean worldz = false;
        for (int i = 0; i < GlobalGameThreadConfigs.obj[1].length; i++) {
            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i] != null && GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox != null) {
                entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.x = (int) (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx + GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.x);
                GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.y = (int) (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy + GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.y);
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
                if (entity.hitbox.intersects(GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox)) {
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].collision) {
                        if (entity.worldz > GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz+1) {
                            worldz = true;
                        }
                    }

                }
                entity.hitbox.x = entity.hitboxdefaultx;
                entity.hitbox.y = entity.hitboxdefaulty;
                GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.x = GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitboxdefaultx;
                GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitbox.y = GlobalGameThreadConfigs.obj[MainGame.currentmap][i].hitboxdefaulty;
            }
        }
        return worldz;
    }
    public boolean worldzentityreturn(LivingEntity entity, LivingEntity[][] target) {
        //checks collision between a entity and another entity
        boolean index = false;
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
                        if (entity.worldz > target[MainGame.currentmap][i].worldz+1) {
                            index = true;
                        }
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
    public boolean checkEntityWorld(double x, double y, LivingEntity[][] target) {
        //checks collision between a entity and another entity
        boolean index = true;
        int targetx, targety;
        for (int i = 0; i < target[1].length; i++) {
            if (target[MainGame.currentmap][i] != null) {
                targetx = (int) Math.round(target[MainGame.currentmap][i].worldx/gp.tilesize);
                targety = (int) Math.round(target[MainGame.currentmap][i].worldy/gp.tilesize);
                if(targetx == x && targety == y){
                    index = false;
                }
        }
    }
        return index;}
    public boolean checktileworld(int x, int y, int z) {
        //checks collision between entity and world
        boolean index = true;
        if(MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][y][z] == 40){
                index = false;
            }
        return index;
    }


    public boolean returntileworldz(LivingEntity entity) {
        //checks collision between entity and world
        boolean index = false;
        boolean hasfinushed = false;
        int knockback = 1;
        boolean shouldtrigger = !checkEntity(gp.player, GlobalGameThreadConfigs.Tentity) && !checkEntity(gp.player, GlobalGameThreadConfigs.Monsters) && !checkEntity(gp.player, GlobalGameThreadConfigs.obj) && !checkEntity(gp.player, GlobalGameThreadConfigs.NPCS);

try {
    int TileNum1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(entity.worldx/gp.tilesize)][(int) Math.round(entity.worldy/gp.tilesize)][(int) (entity.worldz)];
    if (TileNum1 == 46){
        if(shouldtrigger)
            if(entity.worldz > 0)
              index = true;
    }
}catch (Exception e) {

    if (knockback <= 2){
        switch (entity.direction) {
            case "up" -> entity.worldy = entity.worldy + knockback;
            case "down" -> entity.worldy = entity.worldy - knockback;
            case "right" -> entity.worldx = entity.worldx - knockback;
            case "left" -> entity.worldx = entity.worldx + knockback;
        }
    }
    while (!hasfinushed){
        knockback++;
        if(knockback > 2){
            if(!entity.hitboxe){
                switch (entity.direction) {
                    case "up" -> entity.worldy = entity.worldy + knockback;
                    case "down" -> entity.worldy = entity.worldy - knockback;
                    case "right" -> entity.worldx = entity.worldx - knockback;
                    case "left" -> entity.worldx = entity.worldx + knockback;
                }
            }
        }
        if(knockback >= 7){
            hasfinushed = true;
        }
    }

}
        return index;
    }
    public boolean checkWALL(LivingEntity entity){
        boolean index = false;
        int TileNum1 = MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(entity.worldx/gp.tilesize)][(int) Math.round(entity.worldy/gp.tilesize)][(int) entity.worldz];
        if(TileNum1 == 40 && entity.worldz < MainGame.tilemanager.tile[TileNum1].worldz-16){
            index = true;
        }
        return index;
    }
}