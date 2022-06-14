package net.questfor.thepersonwhoasked.tile;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UtilityTool;
import net.questfor.thepersonwhoasked.Maingam.crash;
import net.questfor.thepersonwhoasked.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Random;

import static net.questfor.thepersonwhoasked.GlobalProperties.gp;

public class Tilemanager {
    public int worldcol, worldrow, worldlayer;
    public tile[] tile;
    public int[][][][] mapRendererID, mapspritecounter, mapspritenumber;
    public boolean[][][] LightSource;
    public  String is;


    public BufferedImage earthright1, earthleft1, earthup1, earthdown1;
    public BufferedImage[] fire = new BufferedImage[5];


    public Tilemanager()  {
        tile = new tile[999];

        try {

            getTileImage();
        }catch(Exception e){
            crash.main(e);
        }
    }
    public void set(){
        mapRendererID = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        mapspritenumber = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        mapspritecounter = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        loadmap(0);


    }
    public void getTileImage() {
        setexternaltextures();
        tileproperties(0,"earthdug1", true);
        tileproperties(1,"earth", true);
        tileproperties(2,"earth", true);
        tileproperties(3,"earth", true);
        tileproperties(4,"earth", true);
        tileproperties(5,"earth", true);
        tileproperties(6,"grass00", true);
        tileproperties(7,"grass00", true);
        tileproperties(8,"grass00", true);
        tileproperties(9,"grass00", true);
        tileproperties(10,"grass00", true);
        tileproperties(11,"grass01", true);
        tileproperties(12,"water00", true);
        tileproperties(13,"water01", true);
        tileproperties(14,"water02", true);
        tileproperties(15,"water03", true);
        tileproperties(16,"water04", true);
        tileproperties(17,"water05", true);
        tileproperties(18,"water06", true);
        tileproperties(19,"water07", true);
        tileproperties(20,"water08", true);
        tileproperties(21,"water09", true);
        tileproperties(22,"water10", true);
        tileproperties(23,"water11", true);
        tileproperties(24,"water12", true);
        tileproperties(25,"water13", true);
        tileproperties(26,"road00",true);
        tileproperties(27,"road01", true);
        tileproperties(28,"road02", true);
        tileproperties(29,"road03", true);
        tileproperties(30,"road04", true);
        tileproperties(31,"road05", true);
        tileproperties(32,"road06", true);
        tileproperties(33,"road07", true);
        tileproperties(34,"road08", true);
        tileproperties(35,"road09", true);
        tileproperties(36,"road10", true);
        tileproperties(37,"road11", true);
        tileproperties(38,"road12", true);
        tileproperties(39,"earth", true);
        tileproperties(40,"wall", true);
        tileproperties(41,"tree", true);
        tileproperties(42,"hut", true);
        tileproperties(43,"floor01", true);
        tileproperties(44,"table01", true);
        tileproperties(45,"clayblock", true);
        //AIR
        tileproperties(46,"air", true);
        //OCUPIED OBJECT
        tileproperties(47, "air", true);

        tileproperties(48, "brickwall", true);
        tileproperties(49, "coal_block", true);
        tileproperties(50, "stone", true);
        tileproperties(51, "black", true);
        //FIRE
        tileproperties(52, "air", true);

    }

    public void setexternaltextures() {
        try {
            earthright1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugright1" + ".png"));
            earthright1 = scaleimage(earthright1, gp.tilesize, gp.tilesize);
            earthup1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugup1" + ".png"));
            earthup1 = scaleimage(earthup1, gp.tilesize, gp.tilesize);
            earthleft1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugleft1" + ".png"));
            earthleft1 = scaleimage(earthleft1, gp.tilesize, gp.tilesize);
            earthdown1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugdown1" + ".png"));
            earthdown1 = scaleimage(earthdown1, gp.tilesize, gp.tilesize);
            fire[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/fire1" + ".png"));
            fire[0] = scaleimage(fire[0], gp.tilesize, gp.tilesize);
            fire[1] = ImageIO.read(getClass().getResourceAsStream("/tiles/fire2" + ".png"));
            fire[1] = scaleimage(fire[1], gp.tilesize, gp.tilesize);
            fire[2] = ImageIO.read(getClass().getResourceAsStream("/tiles/fire3" + ".png"));
            fire[2] = scaleimage(fire[2], gp.tilesize, gp.tilesize);
            fire[3] = ImageIO.read(getClass().getResourceAsStream("/tiles/fire4" + ".png"));
            fire[3]= scaleimage(fire[3], gp.tilesize, gp.tilesize);
        } catch (IOException e) {
            crash.main(e);
        }
    }

    public void tileproperties(int index, String imagePath, boolean canjumpover){
        UtilityTool utool = new UtilityTool();
        try{
            tile[index] = new tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath + ".png"));
            tile[index].image = utool.scaleimage(tile[index].image, MainGame.tilesize, MainGame.tilesize);
            tile[index].down[0] = cropimage(tile[index].image, 0, 0, MainGame.tilesize, MainGame.tilesize-6);
            tile[index].down[1] = cropimage(tile[index].image, 0, 0, MainGame.tilesize, MainGame.tilesize-12);
            tile[index].down[2] = cropimage(tile[index].image, 0, 0, MainGame.tilesize, MainGame.tilesize-18);
            tile[index].down[3] = cropimage(tile[index].image, 0, 0, MainGame.tilesize, MainGame.tilesize-24);
            tile[index].down[4] = cropimage(tile[index].image, 0, 0, MainGame.tilesize, MainGame.tilesize-30);
            tile[index].down[5] = cropimage(tile[index].image, 0, 0, MainGame.tilesize, MainGame.tilesize-36);
            tile[index].down[6] = cropimage(tile[index].image, 0, 0, MainGame.tilesize, MainGame.tilesize-42);
            tile[index].canjumpover = canjumpover;

        }catch(Exception e){
            crash.main(e);
        }
    }
    public void use(){

    }
    public void loadmap(int mapID){
        try {
            ObjectInputStream ois = new ObjectInputStream(getClass().getResourceAsStream("/maps/worldV" + mapID + ".amogusdababymilkfilestoragethingyidk"));
                Map map = (Map) ois.readObject();
            mapRendererID[mapID] = map.mapRendererID;

        }catch(Exception e){
            crash.main(e);
        }
    }
    public void drawground(Graphics2D g2) {
        LightSource = new boolean[MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        worldcol = 0;
             worldrow = 0;
             worldlayer = 0;
            while (worldcol < MainGame.maxworldcol && worldrow < MainGame.maxworldrow && worldlayer < MainGame.maxworldlayer) {
                int tileID = mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer];
                    int worldX = worldcol * MainGame.tilesize;
                    int worldY = worldrow * MainGame.tilesize;
                    double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
                    double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
                        if (tileID != 46) {
                            boolean shouldrender = true;
                            if (worldlayer + 1 < MainGame.maxworldlayer) {
                                if(worldlayer > gp.player.worldz){
                                    int i = (int) (gp.player.worldz - worldlayer);
                                    int d;
                                    for(d = 1; d < i; d++){
                                        if(mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer - d] != 46){
                                            boolean up, down, right, left;
                                            if(worldrow+1 < 200){
                                                up = mapRendererID[MainGame.currentmap][worldcol][worldrow+1][worldlayer - d] != 46;
                                            }else{
                                                up = true;
                                            }
                                            if(worldrow-1 > 0){
                                                down = mapRendererID[MainGame.currentmap][worldcol][worldrow-1][worldlayer - d] != 46;
                                            }else{
                                                down = true;
                                            }
                                            if(worldcol+1 < 200){
                                                right = mapRendererID[MainGame.currentmap][worldcol+1][worldrow][worldlayer - d] != 46;
                                            }else{
                                                right = true;
                                            }
                                            if(worldcol-1 > 0){
                                                left = mapRendererID[MainGame.currentmap][worldcol-1][worldrow][worldlayer - d] != 46;
                                            }else{
                                                left = true;
                                            }
                                            if(up && down && right && left){
                                                shouldrender = false;
                                            }



                                        }
                                    }
                                }else if(worldlayer < gp.player.worldz){
                                    int i = (int) (gp.player.worldz - worldlayer);
                                    int d;
                                    for(d = 1; d < i; d++){
                                        if(mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer + d] != 46){
                                            boolean up, down, right, left;
                                            if(worldrow+1 < 200){
                                                up = mapRendererID[MainGame.currentmap][worldcol][worldrow+1][worldlayer + d] != 46;
                                            }else{
                                                up = true;
                                            }
                                            if(worldrow-1 > 0){
                                                down = mapRendererID[MainGame.currentmap][worldcol][worldrow-1][worldlayer + d] != 46;
                                            }else{
                                                down = true;
                                            }
                                            if(worldcol+1 < 200){
                                                right = mapRendererID[MainGame.currentmap][worldcol+1][worldrow][worldlayer + d] != 46;
                                            }else{
                                                right = true;
                                            }
                                            if(worldcol-1 > 0){
                                                left = mapRendererID[MainGame.currentmap][worldcol-1][worldrow][worldlayer + d] != 46;
                                            }else{
                                                left = true;
                                            }
                                            if(up && down && right && left){
                                            shouldrender = false;
                                                    }



                                        }
                                    }
                                }
                            }
                            if (shouldrender) {
                                update(worldcol, worldrow, worldlayer, screenX, screenY, g2);
                                if ((worldX + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                                        (worldX - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                                        && worldY + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                                        (worldY - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                                    if(worldlayer <= gp.player.worldz){
                                g2.drawImage(tile[tileID].image, (int) screenX, (int) screenY, null);
                                    if (worldlayer != 4) {
                                        g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                                        g2.fillRect((int) screenX, (int) screenY, gp.tilesize, gp.tilesize);
                                    }}
                                if(GlobalGameThreadConfigs.dark) {
                                    for (int i = 0; i < GlobalGameThreadConfigs.lights[1].length; i++) {
                                        if (GlobalGameThreadConfigs.lights[MainGame.currentmap][i] != null) {

                                            if (((worldX - MainGame.tilesize * GlobalGameThreadConfigs.lights[MainGame.currentmap][i].power * 2 > GlobalGameThreadConfigs.lights[MainGame.currentmap][i].col * gp.tilesize - MainGame.player.screenX &&
                                                    (worldX + MainGame.tilesize * GlobalGameThreadConfigs.lights[MainGame.currentmap][i].power * 2 < GlobalGameThreadConfigs.lights[MainGame.currentmap][i].col * gp.tilesize + MainGame.player.screenX))
                                                    && worldY - MainGame.tilesize * GlobalGameThreadConfigs.lights[MainGame.currentmap][i].power > GlobalGameThreadConfigs.lights[MainGame.currentmap][i].row * gp.tilesize - MainGame.player.screenY &&
                                                    (worldY + MainGame.tilesize * GlobalGameThreadConfigs.lights[MainGame.currentmap][i].power < GlobalGameThreadConfigs.lights[MainGame.currentmap][i].row * gp.tilesize + MainGame.player.screenY))) {

                                                LightSource[worldcol][worldrow][worldlayer] = true;
                                            }else if(mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer] == 52){LightSource[worldcol][worldrow][worldlayer] = true;}
                                        }
                                    }
                                    }}
                                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                        }
                    }
                    worldcol++;
                    if (worldcol == MainGame.maxworldcol) {
                        worldcol = 0;
                        worldrow++;
                    }
                    if (worldrow == MainGame.maxworldrow) {
                        worldrow = 0;
                        worldlayer++;
                    }
                }
            }
    public void drawSKY(Graphics2D g2) {
        worldcol = 0;
        worldrow = 0;
        worldlayer = 0;
        while (worldcol < MainGame.maxworldcol && worldrow < MainGame.maxworldrow && worldlayer < MainGame.maxworldlayer) {
            int tileID = mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer];
            int worldX = worldcol * MainGame.tilesize;
            int worldY = worldrow * MainGame.tilesize;
            double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
            if (tileID != 46) {
                boolean shouldrender;
                if (worldlayer + 1 < MainGame.maxworldlayer) {
                    shouldrender = mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer + 1] == 46 || mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer + 1] == 41 || mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer + 1] == 47 || mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer + 1] == 52;
                } else {
                    shouldrender = true;
                }

                if (shouldrender) {

                    if ((worldX + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                            (worldX - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                            && worldY + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                            (worldY - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {

                            if(!LightSource[worldcol][worldrow][worldlayer]){

                                    GlobalGameThreadConfigs.g2.setColor(new Color(0, 0, 0, GlobalGameThreadConfigs.LightLevel));
                                    GlobalGameThreadConfigs.g2.fillRect((int) screenX, (int) screenY, gp.tilesize, gp.tilesize);
                                }}
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                        }

                }

            worldcol++;
            if (worldcol == MainGame.maxworldcol) {
                worldcol = 0;
                worldrow++;
            }
            if (worldrow == MainGame.maxworldrow) {
                worldrow = 0;
                worldlayer++;
            }
        }}



    public void update(int col, int row, int layer, double x, double y, Graphics2D g2){
        int worldX = worldcol * MainGame.tilesize;
        int worldY = worldrow * MainGame.tilesize;
        try {

            if ((worldX + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldX - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldY + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldY - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                if (mapRendererID[MainGame.currentmap][col][row][layer] == 0) {
                    if (mapRendererID[MainGame.currentmap][col + 1][row][layer] == 0) {
                        if (mapRendererID[MainGame.currentmap][col][row][layer] == 0)
                            g2.drawImage(earthright1, (int) (x), (int) (y), null);
                    }
                    if (mapRendererID[MainGame.currentmap][col - 1][row][layer] == 0) {
                        if (mapRendererID[MainGame.currentmap][col][row][layer] == 0)
                            g2.drawImage(earthleft1, (int) (x), (int) (y), null);
                    }
                    if (mapRendererID[MainGame.currentmap][col][row - 1][layer] == 0) {
                        if (mapRendererID[MainGame.currentmap][col][row][layer] == 0)
                            g2.drawImage(earthup1, (int) (x), (int) (y), null);
                    }
                    if (mapRendererID[MainGame.currentmap][col][row + 1][layer] == 0) {
                        if (mapRendererID[MainGame.currentmap][col][row][layer] == 0)
                            g2.drawImage(earthdown1, (int) (x), (int) (y), null);
                    }
                }
                if (mapRendererID[MainGame.currentmap][col][row][layer] == 52) {
                    mapspritecounter[MainGame.currentmap][col][row][layer]++;
                    if (mapspritecounter[MainGame.currentmap][col][row][layer] >= 5) {
                        mapspritecounter[MainGame.currentmap][col][row][layer] = 0;
                        mapspritenumber[MainGame.currentmap][col][row][layer]++;
                        if (mapspritenumber[MainGame.currentmap][col][row][layer] == 4) {
                            mapspritenumber[MainGame.currentmap][col][row][layer] = 0;
                        }
                    }
                    g2.drawImage(fire[mapspritenumber[MainGame.currentmap][col][row][layer]], (int) (x), (int) (y), null);
                }
                if(worldlayer > gp.player.worldz){
                        boolean shouldrender = true;

                            boolean up, down, right, left;
                            if(worldrow+1 < 200){
                                up = mapRendererID[MainGame.currentmap][col][row+1][layer] != 46;
                            }else{
                                up = true;
                            }
                            if(worldrow-1 > 0){
                                down = mapRendererID[MainGame.currentmap][col][row-1][layer] != 46;
                            }else{
                                down = true;
                            }
                            if(worldcol+1 < 200){
                                right = mapRendererID[MainGame.currentmap][col+1][row][layer] != 46;
                            }else{
                                right = true;
                            }
                            if(worldcol-1 > 0){
                                left = mapRendererID[MainGame.currentmap][col-1][row][layer] != 46;
                            }else{
                                left = true;
                            }
                            if(up && down && right && left){
                                shouldrender = false;

                            }

                    if(shouldrender){
                    g2.drawImage(tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[(int) (layer-gp.player.worldz)], (int) x, (int)y, null);
                    g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                    g2.fillRect((int) x, (int) y, tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[(int) (layer-gp.player.worldz)].getWidth(), tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[(int) (layer-gp.player.worldz)].getHeight());
                }else{

                        g2.drawImage(tile[mapRendererID[MainGame.currentmap][col][row][layer]].image, (int) x, (int)y, null);
                        g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                        g2.fillRect((int) x, (int) y, 48, 48);
                    }
                }
            }
    }catch (Exception e){
            //System.out.println("error: index out of bounds of length 50: -1");
        }
        }
    public BufferedImage scaleimage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
    public BufferedImage cropimage(BufferedImage image, int startX, int startY, int endX, int endY){
        BufferedImage img = image.getSubimage(startX, startY, endX, endY); //fill in the corners of the desired crop location here
        BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        return copyOfImage;
    }
}