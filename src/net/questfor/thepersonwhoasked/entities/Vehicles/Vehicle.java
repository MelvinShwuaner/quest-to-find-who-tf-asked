package net.questfor.thepersonwhoasked.entities.Vehicles;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.AI.Path;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;

public class Vehicle extends LivingEntity{
    Graphics2D g2;
    public int tiles[][][];
    public int width, height, depth, enterX, enterY, controlX, controlY;
    public ArrayList<LivingEntity> passengers = new ArrayList<>();
    public boolean on;
    public ArrayList<LivingEntity> controller = new ArrayList<>();
    public Vehicle(MainGame gpp, int map, int startcol, int endcol, int startrow, int endrow, int startlayer, int endlayer, int speed, boolean onn, int enterx, int entery, int controlx, int controly) {
        super(gpp);
        width = (endcol-startcol)+1;
        Vehicle = 0;
        height = (endrow-startrow)+1;
        depth = (endlayer-startlayer)+1;
        tiles = new int[width+1][height+1][depth];
        for(int i = 0; i <= width; i++){
            for(int e = 0; e <= height; e++){
                for(int f = 0; f < depth; f++){
                   tiles[i][e][f] = MainGame.tilemanager.mapRendererID[map][startcol+i][startrow+e][startlayer+f];
                }
            }
        }
        this.speed = speed;
        worldx = (startcol)*GlobalGameThreadConfigs.tilesize;
        worldz = startlayer;
        worldy = startrow*GlobalGameThreadConfigs.tilesize;
        hitbox = new Rectangle(0, 0, (endcol-startcol)*GlobalGameThreadConfigs.tilesize, (endrow-startrow)*GlobalGameThreadConfigs.tilesize);
        direction = "left";
        getImageInstance();
        on = onn;
        enterX = enterx;
        enterY = entery;
        controlX = controlx;
        controlY = controly;
    }
    @Override
    public void update() {

        updateimage();
        if (health <= 0){
            dying = true;
        }
        if(LightSource){
            if(!foundposition){
                for(int i = 0; i < GlobalGameThreadConfigs.lights[1].length; i++){
                    if(GlobalGameThreadConfigs.lights[currentmap][i] == null){
                        foundposition = true;
                        updateLight(i);
                        break;
                    }
                }
            }
        }
        if (MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Monsters) && MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.NPCS) && MainGame.hregister.returntileworldz(this)) {
            if (!isup) {
                worldz--;
                getImageInstance();
                updatehitbox();
                getAttackInstance();
            }
        }
        /*AI for Monsters And NPCS*/
        if(frozen){
            checkCollision();
            if(hitboxe){
                knockbackcounter = 0;
                frozen = false;
                speed = defaultspeed;
            }else{
                if(frozendirection == null){
                    frozendirection = direction;
                    frozen = false;
                }

                switch (frozendirection) {
                    case "up" -> {worldy = worldy - speed;}
                    case "down" -> worldy = worldy + speed;
                    case "right" -> worldx = worldx + speed;
                    case "left" -> worldx = worldx - speed;
                }
                for(int i = 0; i < passengers.size(); i++){
                    if(passengers.get(i) != null){
                        switch (frozendirection) {
                            case "up" -> passengers.get(i).worldy = passengers.get(i).worldy - speed;
                            case "down" -> passengers.get(i).worldy = passengers.get(i).worldy + speed;
                            case "right" -> passengers.get(i).worldx = passengers.get(i).worldx + speed;
                            case "left" -> passengers.get(i).worldx = passengers.get(i).worldx - speed;
                        }
                    }
                }

                knockbackcounter++;
                if(knockbackcounter == 2){
                    speed--;
                    knockbackcounter = 0;
                }
                if(speed == defaultspeed){
                    frozen = false;
                }
            }
        }
        if(worldz == 8){
            worldz = 7;
        }
        setAction();
        checkCollision();
        if(primepowercool < 30){
            primepowercool++;
        }
        if(dialogues != null){
            if(dialogues[dialogueIndex] == null){
                cantalk++;
            }
        }
        if(cantalk > 30){
            cantalk = 0;
            dialogueIndex = 0;
        }

            if(!frozen && on){

                switch (direction) {
                    case "up" -> worldy = worldy - speed;
                    case "down" -> worldy = worldy + speed;
                    case "right" -> worldx = worldx + speed;
                    case "left" -> worldx = worldx - speed;
                }
                for(int i = 0; i < passengers.size(); i++) {
                    if (passengers.get(i) != null) {
                        switch (direction) {
                            case "up" -> passengers.get(i).worldy = passengers.get(i).worldy - speed;
                            case "down" -> passengers.get(i).worldy = passengers.get(i).worldy + speed;
                            case "right" -> passengers.get(i).worldx = passengers.get(i).worldx + speed;
                            case "left" -> passengers.get(i).worldx = passengers.get(i).worldx - speed;
                        }
                    }
                }
                if(LightSource)
                    updateLight(Lightposition);
            }
        if(!attacking){
            spritecounter++;
            if (spritecounter > 12) {
                if (spritenumber == 1) {
                    spritenumber = 2;
                } else if (spritenumber == 2) {
                    spritenumber = 1;
                }
                spritecounter = 0;
            }}
        if(invincible){
            hitTime++;
            if(hitTime > 40){
                invincible = false;
                hitTime = 0;
            }
        }
        if(health <= 0){
            dying = true;
        }
    }

    @Override
    public void getImageInstance() {
        image = new BufferedImage(width*GlobalGameThreadConfigs.tilesize, height*GlobalGameThreadConfigs.tilesize, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) image.getGraphics();
    }

    public void draw(Graphics2D g2d){
        double screenX = (worldx - GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX);
        double screenY = worldy - GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY;
        if(path == null){
            path = new Path();
        }
        if(jumping){
            jumpaction++;
            if(jumpaction < 25){
                if(isup) {
                    if (gp.hregister.checkWALL(this) && gp.hregister.checkentitywall(Math.round(worldx/GlobalGameThreadConfigs.tilesize), Math.round(worldy/GlobalGameThreadConfigs.tilesize), worldz, GlobalGameThreadConfigs.NPCS, this) && gp.hregister.checkentitywall(Math.round(worldx/GlobalGameThreadConfigs.tilesize), Math.round(worldy/GlobalGameThreadConfigs.tilesize), worldz,  GlobalGameThreadConfigs.Monsters, this) && gp.hregister.checkentitywall(Math.round(worldx/GlobalGameThreadConfigs.tilesize), Math.round(worldy/GlobalGameThreadConfigs.tilesize), worldz, GlobalGameThreadConfigs.obj, this) ) {
                        if (jumpaction == 1) {
                            worldz++;
                        }
                        jumpstate++;
                        try {
                            image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                        }catch (Exception e){

                        }                            }
                }
            }else{
                isup = false;
                jumpstate--;
                try {
                    image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                }catch (Exception e){

                }
                if(jumpstate < 0) {
                    jumpaction = 0;
                    jumping = false;
                    getAttackInstance();
                    getImageInstance();
                    updatehitbox();
                }

            }
        }
        //RENDERER
        try {
            if ((worldx + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldx - GlobalGameThreadConfigs.player.screenX &&
                    (worldx - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX))
                    && worldy + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldy - GlobalGameThreadConfigs.player.screenY &&
                    (worldy - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY)) {
                //HP BAR
                if ((EntityType == 2 || EntityType == 1) && Hostile) {
                    double onescale = GlobalGameThreadConfigs.tilesize / maxhealth;
                    double HPValue = onescale * health;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect((int) (screenX - 1), (int) screenY - 16, GlobalGameThreadConfigs.tilesize + 2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect((int) screenX, (int) screenY - 15, (int) HPValue, 10);
                }
                if (dying) {
                    DieAnimation(g2);
                }
                if(worldz > GlobalGameThreadConfigs.player.worldz){
                    float Xdistance;
                    float Ydistance;
                    int x = (int) Math.round(worldx/GlobalGameThreadConfigs.tilesize);
                    int y = (int) Math.round(worldy/GlobalGameThreadConfigs.tilesize);
                    if (x > GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize) {
                        Xdistance = (float) (x - (GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize));
                    }else{
                        Xdistance = (float) ((GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize) - x);
                    }
                    if (y > GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize) {
                        Ydistance = (float) (y - (GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize));
                    }else{
                        Ydistance = (float) ((GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize) - y);
                    }
                    if (Ydistance < 2 && Xdistance < 2){
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    }
                }
               int worldcol = 0;
               int  worldrow = 0;
               int  worldlayer = 0;
                while (worldcol <= width && worldrow <= height && worldlayer < depth) {
                    int tileID = tiles[worldcol][worldrow][worldlayer];
                    int worldX = (int) ((worldcol * GlobalGameThreadConfigs.tilesize));
                    int worldY = (int) ((worldrow * GlobalGameThreadConfigs.tilesize));
                    if (tileID != 46 && tileID != 53 && tileID != 47) {
                        g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                        g2.drawImage(gp.tilemanager.tile[tileID].image, (worldX),  worldY, null);
                        g2.fillRect((worldX), worldY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
                    }
                    worldcol++;
                    if (worldcol > width) {
                        worldcol = 0;
                        worldrow++;
                    }
                    if (worldrow > height) {
                        worldrow = 0;
                        worldlayer++;
                    }
                }

                g2d.drawImage(image, (int) screenX, (int) screenY, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            }
        }
        catch (Exception e){}
    }
}
