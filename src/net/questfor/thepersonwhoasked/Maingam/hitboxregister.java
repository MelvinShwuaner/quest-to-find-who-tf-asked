package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.util.Random;

//MANAGES HITBOX'S AND COLLISIONS
public class hitboxregister {
    static MainGame gp;

    hitboxregister(MainGame gpp) {
        this.gp = gpp;
    }

    public void checkTile(LivingEntity entity) {
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
                        if (entity.worldz == target[MainGame.currentmap][i].worldz+1) {
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
    public boolean checktileworld(int x, int y, int z) {
        //checks collision between entity and world
        return MainGame.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][y][z]].air;
    }


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
}}