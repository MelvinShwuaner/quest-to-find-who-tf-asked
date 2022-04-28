package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
//MANAGES HITBOX'S AND COLLISIONS
public class hitboxregister {
    static MainGame gp;
    hitboxregister(MainGame gpp){
        this.gp = gpp;
    }
    public void checkTile(LivingEntity entity) {
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
            int tileNUM1, tileNUM2;
            switch (entity.direction) {
                case "up":
                    var10000 = (int) (entitytopworldy - entity.speed);
                    entitytoprow = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[entityleftcol][entitytoprow];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[entityrightcol][entitytoprow];
                    if (MainGame.tilemanager.tile[tileNUM1].collision || MainGame.tilemanager.tile[tileNUM2].collision) {
                        if(entity.worldz == 0) {
                            entity.hitboxe = true;
                        }
                    }else{
                        entity.worldz = 0;
                    }
                    break;
                case "down":
                    var10000 = (int) (entitybottomworldy + entity.speed);
                    entitybottomrow = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[entityleftcol][entitybottomrow];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[entityrightcol][entitybottomrow];
                    if (MainGame.tilemanager.tile[tileNUM1].collision || MainGame.tilemanager.tile[tileNUM2].collision) {
                        if(entity.worldz == 0) {
                            entity.hitboxe = true;
                        }
                    }else{
                        entity.worldz = 0;
                    }
                    break;
                case "left":
                    var10000 = (int) (entityleftworldx - entity.speed);
                    entityleftcol = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[entityleftcol][entitytoprow];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[entityleftcol][entitybottomrow];
                    if (MainGame.tilemanager.tile[tileNUM1].collision || MainGame.tilemanager.tile[tileNUM2].collision) {
                        if(entity.worldz == 0) {
                            entity.hitboxe = true;
                        }
                    }else{
                        entity.worldz = 0;
                    }
                    break;
                case "right":
                    var10000 = (int) (entityrightworldx + entity.speed);
                    entityrightcol = var10000 / MainGame.tilesize;
                    tileNUM1 = MainGame.tilemanager.mapRendererID[entityrightcol][entitytoprow];
                    tileNUM2 = MainGame.tilemanager.mapRendererID[entityrightcol][entitybottomrow];
                    if (MainGame.tilemanager.tile[tileNUM1].collision || MainGame.tilemanager.tile[tileNUM2].collision) {
                        if(entity.worldz == 0) {
                            entity.hitboxe = true;
                        }
                    }else {
                        entity.worldz = 0;
                    }
                    break;
            }
        }catch(Exception e){
            crash.main(e);
        }
    }
    public int checkObject(LivingEntity entity, boolean player) {
        //checks collision between objects and entities
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null && gp.obj[i].hitbox != null){
                entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                gp.obj[i].hitbox.x = (int) (gp.obj[i].worldx + gp.obj[i].hitbox.x);
                gp.obj[i].hitbox.y = (int) (gp.obj[i].worldy + gp.obj[i].hitbox.y);
                switch(entity.direction){
                    case "up":
                        entity.hitbox.y -= entity.speed;
                        if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                            if(gp.obj[i].collision){
                                entity.hitboxe = true;
                            }if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitbox.y += entity.speed;
                        if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                            if(gp.obj[i].collision){
                                entity.hitboxe = true;
                            }if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitbox.x -= entity.speed;
                        if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                            if(gp.obj[i].collision){
                                entity.hitboxe = true;
                            }if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitbox.x += entity.speed;
                        if(entity.hitbox.intersects(gp.obj[i].hitbox)){
                            if(gp.obj[i].collision){
                                entity.hitboxe = true;
                            }if(player){
                                index = i;
                            }
                        }
                        break;

                }
                entity.hitbox.x = entity.hitboxdefaultx;
                entity.hitbox.y = entity.hitboxdefaulty;
                gp.obj[i].hitbox.x = gp.obj[i].hitboxdefaultx;
                gp.obj[i].hitbox.y = gp.obj[i].hitboxdefaulty;
            }
        }

        return index;
    }
    public int EntityColide(LivingEntity entity, LivingEntity[] target){
        //checks collision between player and entity
        int index = 999;
        for(int i = 0; i < target.length; i++){
            if(target[i] != null){
                entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
                entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
                target[i].hitbox.x = (int) (target[i].worldx + target[i].hitbox.x);
                target[i].hitbox.y = (int) (target[i].worldy + target[i].hitbox.y);
                switch(entity.direction){
                    case "up":
                        entity.hitbox.y -= entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)) {
                            if (entity.worldz == target[i].worldz) {
                                entity.hitboxe = true;
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitbox.y += entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)){
                                entity.hitboxe = true;
                                index = i;

                        }
                        break;
                    case "left":
                        entity.hitbox.x -= entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)){
                                entity.hitboxe = true;
                                index = i;
                        }
                        break;
                    case "right":
                        entity.hitbox.x += entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)) {
                                entity.hitboxe = true;
                                index = i;
                        }
                        break;

                }
                entity.hitbox.x = entity.hitboxdefaultx;
                entity.hitbox.y = entity.hitboxdefaulty;
                target[i].hitbox.x = target[i].hitboxdefaultx;
                target[i].hitbox.y = target[i].hitboxdefaulty;
            }
        }

        return index;
    }
    public void PlayerColide(LivingEntity entity){
        //checks collision between entity and player
        entity.hitbox.x = (int) (entity.worldx + entity.hitbox.x);
        entity.hitbox.y = (int) (entity.worldy + entity.hitbox.y);
        gp.player.hitbox.x = (int) (gp.player.worldx + gp.player.hitbox.x);
        gp.player.hitbox.y = (int) (gp.player.worldy + gp.player.hitbox.y);
        switch(entity.direction){
            case "up":
                entity.hitbox.y -= entity.speed;
                if(entity.hitbox.intersects(gp.player.hitbox)) {
                    if (entity.worldz == gp.player.worldz) {
                        entity.hitboxe = true;

                    }
                }
                break;
            case "down":
                entity.hitbox.y += entity.speed;
                if(entity.hitbox.intersects(gp.player.hitbox)){
                    entity.hitboxe = true;


                }
                break;
            case "left":
                entity.hitbox.x -= entity.speed;
                if(entity.hitbox.intersects(gp.player.hitbox)){
                    entity.hitboxe = true;

                }
                break;
            case "right":
                entity.hitbox.x += entity.speed;
                if(entity.hitbox.intersects(gp.player.hitbox)) {
                    entity.hitboxe = true;
                }
                break;

        }
        entity.hitbox.x = entity.hitboxdefaultx;
        entity.hitbox.y = entity.hitboxdefaulty;
        gp.player.hitbox.x = gp.player.hitboxdefaultx;
        gp.player.hitbox.y = gp.player.hitboxdefaulty;
    }

}