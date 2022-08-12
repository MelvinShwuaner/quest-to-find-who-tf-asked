package net.questfor.thepersonwhoasked.entities.Vehicles;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.AI.Path;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.FlyingTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;
import static net.questfor.thepersonwhoasked.Maingam.MainGame.sound;

public class Vehicle extends LivingEntity {
    Graphics2D g2;
    public int tiles[][][];
    public int width, height, depth;
    public ArrayList<LivingEntity> passengers = new ArrayList<>();
    public ArrayList<Cannon> cannons = new ArrayList<>();
    public boolean on;
    int mapspritenumber[][][];
    int mapspritecounter[][][];
    public ArrayList<LivingEntity> controller = new ArrayList<>();
    public int Vindex;
    public int engines = 0;
    public int enginex, enginey, enginez;

    public Vehicle(MainGame gpp, int index, int map, int startcol, int endcol, int startrow, int endrow, int startlayer, int endlayer, int speed, boolean onn, int worldx, int y) {
        super(gpp);
        width = (endcol - startcol) + 1;
        height = (endrow - startrow) + 1;
        depth = (endlayer - startlayer) + 1;
        tiles = new int[width + 1][height + 1][depth];
        mapspritenumber = new int[MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        mapspritecounter = new int[MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        for (int i = 0; i <= width; i++) {
            for (int e = 0; e <= height; e++) {
                for (int f = 0; f < depth; f++) {
                    tiles[i][e][f] = MainGame.tilemanager.mapRendererID[map][startcol + i][startrow + e][startlayer + f];
                    if (tiles[i][e][f] == 54) {
                        engines++;
                        enginex = i;
                        enginey = e;
                        enginez = f;
                    }
                }
            }
        }
        this.speed = speed;

        this.worldx = (worldx) * GlobalGameThreadConfigs.tilesize;
        worldz = startlayer;
        worldy = y * GlobalGameThreadConfigs.tilesize;
        hitbox = new Rectangle(0, 0, ((endcol + 1) - startcol) * GlobalGameThreadConfigs.tilesize, ((1 + endrow) - startrow) * GlobalGameThreadConfigs.tilesize);
        direction = "down";
        on = onn;
        Vindex = index;
    }

    @Override
    public void update() {
        updatetiles();
        for (int index = 0; index < GlobalGameThreadConfigs.obj[currentmap].length; index++) {
            if (GlobalGameThreadConfigs.obj[currentmap][index] != null) {
                int x = (int) GlobalGameThreadConfigs.obj[currentmap][index].worldx;
                int y = (int) GlobalGameThreadConfigs.obj[currentmap][index].worldy;
                int z = (int) GlobalGameThreadConfigs.obj[currentmap][index].worldz;
                if (((Math.round(x / GlobalGameThreadConfigs.tilesize) - Math.round(this.worldx / GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(x / GlobalGameThreadConfigs.tilesize) - Math.round(this.worldx / GlobalGameThreadConfigs.tilesize)) <= this.width)
                        && ((Math.round(y / GlobalGameThreadConfigs.tilesize) - Math.round(this.worldy / GlobalGameThreadConfigs.tilesize)) >= 0 && (Math.round(y / GlobalGameThreadConfigs.tilesize) - Math.round(this.worldy / GlobalGameThreadConfigs.tilesize)) <= this.height)
                        && (z >= this.worldz && z <= this.worldz + this.depth)
                ) {

                    if (GlobalGameThreadConfigs.obj[currentmap][index].Cannon) {
                        if (tiles[(int) (Math.round(x / GlobalGameThreadConfigs.tilesize) - Math.round(this.worldx / GlobalGameThreadConfigs.tilesize))][(int) (Math.round(y / GlobalGameThreadConfigs.tilesize) - Math.round(this.worldy / GlobalGameThreadConfigs.tilesize))][(int) (z - this.worldz)] == 46) {
                            boolean newc = true;
                            for(int i = 0; i < cannons.size(); i++){
                                if(cannons.get(i) == GlobalGameThreadConfigs.obj[currentmap][index]){
                                    newc = false;
                                    break;
                                }
                            }
                           if (newc){
                            cannons.add((net.questfor.thepersonwhoasked.entities.Vehicles.Cannon) GlobalGameThreadConfigs.obj[currentmap][index]);


                       }
                        }
                    }
                }
            }
        }

        if (engines <= 0) {
            on = false;
            speed--;
            if (controller.size() > 0) {
                controller.get(0).controlling = false;
                controller.remove(0);
            }
        }
        gp.hregister.checkentityonvehicle(this, GlobalGameThreadConfigs.NPCS);
        gp.hregister.checkentityonvehicle(this, GlobalGameThreadConfigs.Monsters);
        gp.hregister.checkentityonvehicle(this, GlobalGameThreadConfigs.obj);
        gp.hregister.checkplayeronvehicle(this);
        if (on && controller.size() > 0) {
            direction = controller.get(0).direction;
        }
        if (health <= 0) {
            dying = true;
        }
        if (LightSource) {
            if (!foundposition) {
                for (int i = 0; i < GlobalGameThreadConfigs.lights[1].length; i++) {
                    if (GlobalGameThreadConfigs.lights[currentmap][i] == null) {
                        foundposition = true;
                        updateLight(i);
                        break;
                    }
                }
            }
        }
        //if (MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Monsters) && MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.NPCS) && MainGame.hregister.returntileworldz(this)) {if (!isup) {worldz--;updatehitbox();getAttackInstance();}}
        /*AI for Monsters And NPCS*/
        if (frozen) {
            checkCollision();
            Move(frozendirection, speed);
            for (LivingEntity passenger : passengers) {
                if (passenger != null) {
                    passenger.Move(frozendirection, this.speed);
                }
            }
            if (frozendirection2 != null) {
                Move(frozendirection2, speed);
                for (LivingEntity passenger : passengers) {
                    if (passenger != null) {
                        passenger.Move(frozendirection2, this.speed);
                    }
                }
            }
            if (LightSource)
                updateLight(Lightposition);
            if (hitboxe) {
                knockbackcounter = 0;
                frozen = false;
                speed = defaultspeed;
                if (speed / 1.5 >= 0)
                    health -= speed / 1.5;
                ParticlePropertyManager(this, this);
            } else {
                knockbackcounter++;
                if (knockbackcounter == 2) {
                    speed--;
                    knockbackcounter = 0;
                }
                if (speed == defaultspeed) {
                    frozen = false;
                }
            }
        }
        if (worldz == 8) {
            worldz = 7;
        }
        setAction();
        checkCollision();
        if (primepowercool < 30) {
            primepowercool++;
        }
        if (dialogues != null) {
            if (dialogues[dialogueIndex] == null) {
                cantalk++;
            }
        }
        if (cantalk > 30) {
            cantalk = 0;
            dialogueIndex = 0;
        }

        if (!frozen && on) {
            Move(direction, speed);
            try {
                for (LivingEntity passenger : passengers) {
                    if (passenger != null) {
                        boolean shouldmove = !passenger.jumping;
                        if (!shouldmove) {
                            switch (this.direction) {
                                case "down" -> {
                                    if (!gp.tilemanager.tile[tiles
                                            [(int) (Math.round(passenger.worldx / GlobalGameThreadConfigs.tilesize) - Math.round(worldx / GlobalGameThreadConfigs.tilesize))]
                                            [(int) (Math.round(passenger.worldy / GlobalGameThreadConfigs.tilesize) - Math.round(worldy / GlobalGameThreadConfigs.tilesize) - 1)]
                                            [(int) (passenger.worldz - worldz)]].air) {
                                        shouldmove = true;
                                    }
                                }
                                case "up" -> {
                                    if (!gp.tilemanager.tile[tiles
                                            [(int) (Math.round(passenger.worldx / GlobalGameThreadConfigs.tilesize) - Math.round(worldx / GlobalGameThreadConfigs.tilesize))]
                                            [(int) (Math.round(passenger.worldy / GlobalGameThreadConfigs.tilesize) - Math.round(worldy / GlobalGameThreadConfigs.tilesize) + 1)]
                                            [(int) (passenger.worldz - worldz)]].air) {
                                        shouldmove = true;
                                    }
                                }
                                case "left" -> {
                                    if (!gp.tilemanager.tile[tiles
                                            [(int) (Math.round(passenger.worldx / GlobalGameThreadConfigs.tilesize) - Math.round(worldx / GlobalGameThreadConfigs.tilesize) + 1)]
                                            [(int) (Math.round(passenger.worldy / GlobalGameThreadConfigs.tilesize) - Math.round(worldy / GlobalGameThreadConfigs.tilesize))]
                                            [(int) (passenger.worldz - worldz)]].air) {
                                        shouldmove = true;
                                    }
                                }
                                case "right" -> {
                                    if (!gp.tilemanager.tile[tiles
                                            [(int) (Math.round(passenger.worldx / GlobalGameThreadConfigs.tilesize) - Math.round(worldx / GlobalGameThreadConfigs.tilesize) - 1)]
                                            [(int) (Math.round(passenger.worldy / GlobalGameThreadConfigs.tilesize) - Math.round(worldy / GlobalGameThreadConfigs.tilesize))]
                                            [(int) (passenger.worldz - worldz)]].air) {
                                        shouldmove = true;
                                    }
                                }
                            }
                        }

                        if (shouldmove)
                            passenger.Move(this.direction, this.speed);
                    }
                }
            } catch (Exception ignored) {

            }
        }
        if (!attacking) {
            spritecounter++;
            if (spritecounter > 12) {
                if (spritenumber == 1) {
                    spritenumber = 2;
                } else if (spritenumber == 2) {
                    spritenumber = 1;
                }
                spritecounter = 0;
            }
        }
        if (invincible) {
            hitTime++;
            if (hitTime > 40) {
                invincible = false;
                hitTime = 0;
            }
        }
        if (health <= 0) {
            dying = true;
        }

    }


    public void draw(Graphics2D g2) {
        double screenX = (worldx - GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX);
        double screenY = worldy - GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY;
        if (path == null) {
            path = new Path();
        }
        //RENDERER
        try {

            //HP BAR
            if ((EntityType == 2 || EntityType == 1) && Hostile) {
                double onescale = GlobalGameThreadConfigs.tilesize / maxhealth;
                double HPValue = onescale * health;
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect((int) (screenX - 1), (int) screenY - 16, GlobalGameThreadConfigs.tilesize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect((int) screenX, (int) screenY - 15, (int) HPValue, 10);
            }

            if (worldz > GlobalGameThreadConfigs.player.worldz) {
                float Xdistance;
                float Ydistance;
                int x = (int) Math.round(worldx / GlobalGameThreadConfigs.tilesize);
                int y = (int) Math.round(worldy / GlobalGameThreadConfigs.tilesize);
                if (x > GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize) {
                    Xdistance = (float) (x - (GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize));
                } else {
                    Xdistance = (float) ((GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize) - x);
                }
                if (y > GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize) {
                    Ydistance = (float) (y - (GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize));
                } else {
                    Ydistance = (float) ((GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize) - y);
                }
                if (Ydistance < 2 && Xdistance < 2) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                }
            }
            int worldcol = 0;
            int worldrow = 0;
            int worldlayer = 0;
            while (worldcol <= width && worldrow <= height && worldlayer < depth) {
                int tileID = tiles[worldcol][worldrow][worldlayer];
                int worldX = (int) ((worldcol * GlobalGameThreadConfigs.tilesize) + worldx);
                int worldY = (int) ((worldrow * GlobalGameThreadConfigs.tilesize) + worldy);
                double screenx = (worldX - GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX);
                double screeny = worldY - GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY;
                if (tileID != 46 && tileID != 53 && tileID != 47) {
                    updatetiles(worldcol, worldrow, worldlayer, screenx, screeny, g2);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatetiles(int col, int row, int layer, double x, double y, Graphics2D g2) {
        try {
            int worldX = (int) ((col * GlobalGameThreadConfigs.tilesize) + worldx);
            int worldY = (int) ((row * GlobalGameThreadConfigs.tilesize) + worldy);
            if ((worldX + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldx - GlobalGameThreadConfigs.player.screenX &&
                    (worldX - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX))
                    && worldY + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldy - GlobalGameThreadConfigs.player.screenY &&
                    (worldY - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY)) {
                if (tiles[col][row][layer] == 0) {
                    if (layer > 0 && layer < 4)
                        tiles[col][row][layer] = 39;
                    if (layer == 4) {
                        tiles[col][row][layer] = 10;
                    }
                    if (layer > 4)
                        tiles[col][row][layer] = 46;
                }
                if (tiles[col][row][layer] == 52) {
                    if (layer - 1 >= 0) {
                        if (gp.tilemanager.tile[tiles[col][row][layer - 1]].air) {
                            tiles[col][row][layer] = 46;
                        }
                    } else {
                        tiles[col][row][layer] = 46;
                    }
                    mapspritecounter[col][row][layer]++;
                    if (mapspritecounter[col][row][layer] >= 4) {
                        mapspritecounter[col][row][layer] = 0;
                        mapspritenumber[col][row][layer]++;
                        if (mapspritenumber[col][row][layer] == 5) {
                            mapspritenumber[col][row][layer] = 0;
                        }
                    }
                    g2.drawImage(gp.tilemanager.fire[mapspritenumber[col][row][layer]], (int) (x), (int) (y), null);
                }
                if (layer + worldz <= GlobalGameThreadConfigs.player.worldz) {
                    boolean shouldrender = true;
                    boolean up, down, right, left;
                    if (row + 1 <= height) {
                        up = !gp.tilemanager.tile[tiles[col][row + 1][layer]].transparent;
                    } else {
                        up = true;
                    }
                    if (row - 1 >= 0) {
                        down = !gp.tilemanager.tile[tiles[col][row - 1][layer]].transparent;
                    } else {
                        down = true;
                    }
                    if (col + 1 <= width) {
                        right = !gp.tilemanager.tile[tiles[col + 1][row][layer]].transparent;
                    } else {
                        right = true;
                    }
                    if (col - 1 >= 0) {
                        left = !gp.tilemanager.tile[tiles[col - 1][row][layer]].transparent;
                    } else {
                        left = true;
                    }
                    if (up && down && right && left) {
                        shouldrender = false;

                    }
                    if (shouldrender) {
                        if (layer - 1 >= 0) {
                            up = false;
                            down = false;
                            right = false;
                            left = false;
                            if (row + 1 <= height) {
                                up = !gp.tilemanager.tile[tiles[col][row + 1][layer - 1]].transparent;
                            } else {
                                up = true;
                            }
                            if (row - 1 >= 0) {
                                down = !gp.tilemanager.tile[tiles[col][row - 1][layer - 1]].transparent;
                            } else {
                                down = true;
                            }
                            if (col + 1 <= width) {
                                right = !gp.tilemanager.tile[tiles[col + 1][row][layer - 1]].transparent;
                            } else {
                                right = true;
                            }
                            if (col - 1 >= 0) {
                                left = !gp.tilemanager.tile[tiles[col - 1][row][layer - 1]].transparent;
                            } else {
                                left = true;
                            }
                            if (up && down && right && left) {
                                shouldrender = false;
                            }
                        } else {
                            shouldrender = false;
                        }
                    }
                    if (shouldrender) {
                        g2.drawImage(gp.tilemanager.tile[tiles[col][row][layer]].down[(int) (layer + worldz)], (int) x, (int) y, null);
                        g2.setColor(new Color(255, 255, 255, (int) ((layer + worldz) * 8)));
                        g2.fillRect((int) x, (int) y, gp.tilemanager.tile[tiles[col][row][layer]].down[(int) (layer + worldz)].getWidth(), gp.tilemanager.tile[tiles[col][row][layer]].down[(int) (layer + worldz)].getHeight());
                    } else {
                        g2.drawImage(gp.tilemanager.tile[tiles[col][row][layer]].image, (int) x, (int) y, null);
                        g2.setColor(new Color(255, 255, 255, (int) ((layer + worldz) * 8)));
                        g2.fillRect((int) x, (int) y, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
                    }
                }
                if (layer + worldz > GlobalGameThreadConfigs.player.worldz) {
                    boolean shouldrender = true;
                    boolean up, down, right, left;
                    if (row + 1 <= height) {
                        up = !gp.tilemanager.tile[tiles[col][row + 1][layer]].transparent;
                    } else {
                        up = true;
                    }
                    if (row - 1 >= 0) {
                        down = !gp.tilemanager.tile[tiles[col][row - 1][layer]].transparent;
                    } else {
                        down = true;
                    }
                    if (col + 1 <= width) {
                        right = !gp.tilemanager.tile[tiles[col + 1][row][layer]].transparent;
                    } else {
                        right = true;
                    }
                    if (col - 1 >= 0) {
                        left = !gp.tilemanager.tile[tiles[col - 1][row][layer]].transparent;
                    } else {
                        left = true;
                    }
                    if (up && down && right && left) {
                        shouldrender = false;
                    }
                    if (shouldrender && layer + 1 < depth) {
                        up = false;
                        down = false;
                        right = false;
                        left = false;
                        if (row + 1 <= width) {
                            up = !gp.tilemanager.tile[tiles[col][row + 1][layer + 1]].transparent;
                        } else {
                            up = true;
                        }
                        if (row - 1 > 0) {
                            down = !gp.tilemanager.tile[tiles[col][row - 1][layer + 1]].transparent;
                        } else {
                            down = true;
                        }
                        if (col + 1 <= width) {
                            right = !gp.tilemanager.tile[tiles[col + 1][row][layer + 1]].transparent;
                        } else {
                            right = true;
                        }
                        if (col - 1 > 0) {
                            left = !gp.tilemanager.tile[tiles[col - 1][row][layer + 1]].transparent;
                        } else {
                            left = true;
                        }
                        if (up && down && right && left) {
                            shouldrender = false;
                        }
                    }

                    if (shouldrender) {
                        g2.drawImage(gp.tilemanager.tile[tiles[col][row][layer]].down[(int) (layer + worldz)], (int) x, (int) y, null);
                        g2.setColor(new Color(255, 255, 255, (int) ((layer + worldz) * 8)));
                        g2.fillRect((int) x, (int) y, gp.tilemanager.tile[tiles[col][row][layer]].down[(int) (layer + worldz)].getWidth(), gp.tilemanager.tile[tiles[col][row][layer]].down[(int) (layer + worldz)].getHeight());
                    } else {
                        g2.drawImage(gp.tilemanager.tile[tiles[col][row][layer]].image, (int) x, (int) y, null);
                        g2.setColor(new Color(255, 255, 255, (int) ((layer + worldz) * 8)));
                        g2.fillRect((int) x, (int) y, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkCollision() {
        gp.hregister.colideonTile(this);
        gp.hregister.colideonVehicle(this);
    }

    public void Fire() {
        System.out.println("g");
        for (net.questfor.thepersonwhoasked.entities.Vehicles.Cannon cannon : cannons) {
            System.out.println("e");
            if (cannon != null) {

                if (Objects.equals(cannon.direction, this.direction)) {
                    System.out.println("d");
                    cannon.Fire();

                }
            }
        }
    }

    public void updatetiles() {

        int previousx = 200;
        int previousy = 200;
        int previousz = 200;
        for (int x = 0; x <= width; x++) {
            for (int y = 0; y <= height; y++) {
                for (int z = 0; z < depth; z++) {
                    if(tiles[x][y][z] != 46){
                    boolean stable = false;
                    boolean goalreached = false;
                    int truex = x;
                    int truey = y;
                    int truez = z;
                    int steps = 0;
                    while (!goalreached && steps < 100) {
                        boolean set = false;
                        if (truex - enginex != 0)
                        {
                            if (truex - enginex > 0) {
                                if (truex - 1 >= 0) {
                                    if (previousx != truex - 1) {
                                        if (!gp.tilemanager.tile[tiles[truex - 1][truey][truez]].air) {
                                            previousx = truex;
                                            truex = truex - 1;
                                            set = true;
                                            
                                        }
                                    }
                                }
                                if (!set) {
                                    if (truex + 1 <= width) {
                                        if (previousx != truex + 1) {
                                            if (!gp.tilemanager.tile[tiles[truex + 1][truey][truez]].air) {
                                                previousx = truex;
                                                truex = truex + 1;
                                                set = true;
                                                
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (truex + 1 <= width) {

                                    if (previousx != truex + 1) {
                                        if (!gp.tilemanager.tile[tiles[truex + 1][truey][truez]].air) {
                                            previousx = truex;
                                            truex = truex + 1;
                                            set = true;
                                            
                                        }
                                    }
                                }
                                if (!set) {
                                    if (truex - 1 >= 0) {
                                        if (previousx != truex - 1) {
                                            if (!gp.tilemanager.tile[tiles[truex - 1][truey][truez]].air) {
                                                previousx = truex;
                                                truex = truex - 1;
                                                set = true;
                                                
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (truey - enginey != 0 && !set)
                        {
                                if (truey - enginey > 0) {
                                    if (truey - 1 >= 0) {
                                        if (previousy != truey - 1) {
                                            if (!gp.tilemanager.tile[tiles[truex][truey - 1][truez]].air) {
                                                previousy = truey;
                                                truey = truey - 1;
                                                set = true;
                                                
                                            }
                                        }
                                    }
                                    if (!set) {
                                        if (truey + 1 <= height) {
                                            if (previousy != truey + 1) {
                                                if (!gp.tilemanager.tile[tiles[truex][truey + 1][truez]].air) {
                                                    previousy = truey;
                                                    truey = truey + 1;
                                                    set = true;
                                                    
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (truey + 1 <= height) {
                                        if (previousy != truey + 1) {
                                            if (!gp.tilemanager.tile[tiles[truex][truey + 1][truez]].air) {
                                                previousy = truey;
                                                truey = truey + 1;
                                                set = true;
                                                
                                            }
                                        }
                                    }
                                    if (!set) {
                                        if (truey - 1 >= 0) {
                                            if (previousy != truey - 1) {
                                                if (!gp.tilemanager.tile[tiles[truex][truey - 1][truez]].air) {

                                                    truey = truey - 1;
                                                    previousy = truey;
                                                    set = true;
                                                    
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        if(truez - enginez != 0 && !set)
                        {
                            if (truez - enginez >= 0) {
                                if (truez - 1 >= 0) {
                                    if (previousz != truez - 1) {
                                        if (!gp.tilemanager.tile[tiles[truex][truey][truez - 1]].air) {
                                            previousz = truez;
                                            truez = truez-1;
                                            set = true;
                                            
                                        }
                                    }
                                }
                                if(!set) {
                                    if (truez + 1 < depth) {
                                        if (previousz != truez + 1) {
                                            if (!gp.tilemanager.tile[tiles[truex][truey][truez + 1]].air) {
                                                previousz = truez;
                                                truez = truez + 1;
                                                set = true;
                                                
                                            }
                                        }
                                    }
                                }
                            } else  {
                                if (truez + 1 < depth) {
                                    if(previousz != truez+1){
                                        if (!gp.tilemanager.tile[tiles[truex][truey][truez + 1]].air) {
                                            previousz = truez;
                                            truez = truez+1;
                                            set = true;
                                            
                                        }}
                                }
                                if(!set){
                                    if (truez - 1 >= 0) {
                                        if (previousz != truez - 1) {
                                            if (!gp.tilemanager.tile[tiles[truex][truey][truez - 1]].air) {
                                                previousz = truez;
                                                truez = truez-1;
                                                set = true;
                                                
                                            }
                                        }
                                    }
                                }}
                        }
                        else if(truex != enginex || truey != enginey || truez != enginez)
                        {
                            boolean trigger = false;
                            if (truez - 1 >= 0) {
                                boolean up = false;
                                boolean down = false;
                                boolean right = false;
                                boolean left = false;
                                if (truey + 1 <= height) {
                                    up = !gp.tilemanager.tile[tiles[truex][truey + 1][truez - 1]].air;
                                } else {
                                    up = true;
                                }
                                if (truey - 1 >= 0) {
                                    down = !gp.tilemanager.tile[tiles[truex][truey - 1][truez - 1]].air;
                                } else {
                                    down = true;
                                }
                                if (truex + 1 <= width) {
                                    right = !gp.tilemanager.tile[tiles[truex + 1][truey][truez - 1]].air;
                                } else {
                                    right = true;
                                }
                                if (truex - 1 >= 0) {
                                    left = !gp.tilemanager.tile[tiles[truex - 1][truey][truez - 1]].air;
                                } else {
                                    left = true;
                                }
                                if (up || down || right || left) {
                                    truez--;
                                    previousz = truez;
                                    trigger = true;
                                }
                            }
                            if(!trigger){
                                if (truez + 1 < depth) {
                                    boolean up = false;
                                    boolean down = false;
                                    boolean right = false;
                                    boolean left = false;
                                    boolean above = false;
                                    above = !gp.tilemanager.tile[tiles[truex][truey][truez + 1]].air;
                                    if (truey + 1 <= height) {
                                        up = !gp.tilemanager.tile[tiles[truex][truey + 1][truez + 1]].air;
                                    } else {
                                        up = true;
                                    }
                                    if (truey - 1 >= 0) {
                                        down = !gp.tilemanager.tile[tiles[truex][truey - 1][truez + 1]].air;
                                    } else {
                                        down = true;
                                    }
                                    if (truex + 1 <= width) {
                                        right = !gp.tilemanager.tile[tiles[truex + 1][truey][truez + 1]].air;
                                    } else {
                                        right = true;
                                    }
                                    if (truex - 1 >= 0) {
                                        left = !gp.tilemanager.tile[tiles[truex - 1][truey][truez + 1]].air;
                                    } else {
                                        left = true;
                                    }
                                    if (up || down || right || left || above) {
                                        truez++;
                                        previousz = truez;
                                    }
                                }
                            }
                        }
                        steps++;
                        if(truex == enginex && truey == enginey && truez == enginez){
                            goalreached = true;
                            stable = true;
                        }

                    }
                    previousx = 200;
                    previousy = 200;
                    previousz = 200;
                    if(!stable){
                        for (int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++) {
                           if (GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null) {
                               GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                        gp,
                                        tiles[x][y][z], gp.tilemanager.tile[tiles[x][y][z]].name,
                                        (int) (worldx + (x * GlobalGameThreadConfigs.tilesize)),
                        (int) (worldy + (y * GlobalGameThreadConfigs.tilesize)),
                                        (int) (z+worldz),
                                      4,
                                        0,
                                       0,
                                       false
                                );
                                break;
                            }
                        }
                        tiles[x][y][z] = 46;
                    }
                }}
            }
        }
    }
}