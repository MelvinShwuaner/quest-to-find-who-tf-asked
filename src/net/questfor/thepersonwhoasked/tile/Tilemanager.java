package net.questfor.thepersonwhoasked.tile;
import net.questfor.thepersonwhoasked.Maingam.*;
import net.questfor.thepersonwhoasked.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import static net.questfor.thepersonwhoasked.GlobalProperties.gp;

public class Tilemanager {
    public int worldcol, worldrow, worldlayer;
    public static int tilecount = 0;
    public tile[] tile;
    public int[][][][] mapRendererID, mapspritecounter, mapspritenumber;
    public  String is;


    public BufferedImage earthright1, earthleft1, earthup1, earthdown1;
    public BufferedImage[] fire = new BufferedImage[5], firescreen = new BufferedImage[4];


    public Tilemanager()  {
        tile = new tile[999];

        try {

            getTileImage();
        }catch(Exception e){
            crash.main(e);
        }
    }
    public void set(){
        mapRendererID = new int[MainGame.maxmap][200][200][MainGame.maxworldlayer];
        mapspritenumber = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        mapspritecounter = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        loadmap(0);


    }
    public void getTileImage() {
        setexternaltextures();
        tileproperties(0,"earthdug1", true, false, false, false);
        tileproperties(1,"earth", true, false, false, false);
        tileproperties(2,"earth", true, false, false, false);
        tileproperties(3,"earth", true, false, false, false);
        tileproperties(4,"earth", true, false, false, false);
        tileproperties(5,"earth", true, false, false, false);
        tileproperties(6,"grass00", true, false, false, false);
        tileproperties(7,"grass00", true, false, false, false);
        tileproperties(8,"grass00", true, false, false, false);
        tileproperties(9,"grass00", true, false, false, false);
        tileproperties(10,"grass00", true, false, false, false);
        tileproperties(11,"grass01", true, false, false, false);
        tileproperties(12,"water00", true, true, false, false);
        tileproperties(13,"water01", true, true, false, false);
        tileproperties(14,"water02", true, false, false, false);
        tileproperties(15,"water03", true, false, false, false);
        tileproperties(16,"water04", true, false, false, false);
        tileproperties(17,"water05", true, false, false, false);
        tileproperties(18,"water06", true, false, false, false);
        tileproperties(19,"water07", true, false, false, false);
        tileproperties(20,"water08", true, false, false, false);
        tileproperties(21,"water09", true, false, false, false);
        tileproperties(22,"water10", true, false, false, false);
        tileproperties(23,"water11", true, false, false, false);
        tileproperties(24,"water12", true, false, false, false);
        tileproperties(25,"water13", true, false, false, false);
        tileproperties(26,"road00",true, false, false, false);
        tileproperties(27,"road01", true, false, false, false);
        tileproperties(28,"road02", true, false, false, false);
        tileproperties(29,"road03", true, false, false, false);
        tileproperties(30,"road04", true, false, false, false);
        tileproperties(31,"road05", true, false, false, false);
        tileproperties(32,"road06", true, false, false, false);
        tileproperties(33,"road07", true, false, false, false);
        tileproperties(34,"road08", true, false, false, false);
        tileproperties(35,"road09", true, false, false, false);
        tileproperties(36,"road10", true, false, false, false);
        tileproperties(37,"road11", true, false, false, false);
        tileproperties(38,"road12", true, false, false, false);
        tileproperties(39,"earth", true, false, false, false);
        tileproperties(40,"wall", true, false, false, false);
        tileproperties(41,"tree", true, false, true, false);
        tileproperties(42,"hut", true, false, false, false);
        tileproperties(43,"floor01", true, false, false, false);
        tileproperties(44,"table01", true, false, true, false);
        tileproperties(45,"clayblock", true, false, false, false);
        //AIR
        tileproperties(46,"air", true, true, true, false);
        //OCUPIED OBJECT
        tileproperties(47, "air", true, false, false, false);

        tileproperties(48, "brickwall", true, false, false, false);
        tileproperties(49, "coal_block", true, false, false, false);
        tileproperties(50, "stone", true, false, false, false);
        tileproperties(51, "black", true, false, false, false);
        //FIRE
        tileproperties(52, "air", true, true, true, true);

    }

    public void setexternaltextures() {
        try {
            earthright1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugright1" + ".png"));
            earthright1 = scaleimage(earthright1, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            earthup1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugup1" + ".png"));
            earthup1 = scaleimage(earthup1, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            earthleft1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugleft1" + ".png"));
            earthleft1 = scaleimage(earthleft1, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            earthdown1 = ImageIO.read(getClass().getResourceAsStream("/tiles/earthdugdown1" + ".png"));
            earthdown1 = scaleimage(earthdown1, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            fire[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/1" + ".png"));
            fire[0] = scaleimage(fire[0], GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            firescreen[0] = scaleimage(fire[0], gp.screenwidth, gp.screenheight);
            fire[1] = ImageIO.read(getClass().getResourceAsStream("/tiles/2" + ".png"));
            fire[1] = scaleimage(fire[1], GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            firescreen[1] = scaleimage(fire[1], gp.screenwidth, gp.screenheight);
            fire[2] = ImageIO.read(getClass().getResourceAsStream("/tiles/3" + ".png"));
            fire[2] = scaleimage(fire[2], GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            firescreen[2] = scaleimage(fire[2], gp.screenwidth, gp.screenheight);
            fire[3] = ImageIO.read(getClass().getResourceAsStream("/tiles/4" + ".png"));
            fire[3]= scaleimage(fire[3], GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            fire[4] = ImageIO.read(getClass().getResourceAsStream("/tiles/5" + ".png"));
            fire[4]= scaleimage(fire[3], GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            firescreen[3] = scaleimage(fire[3], gp.screenwidth, gp.screenheight);

        } catch (IOException e) {
            crash.main(e);
        }
    }

    public void tileproperties(int index, String imagePath, boolean canjumpover, boolean air, boolean transparent, boolean hot){
        tilecount++;
        try{
            tile[index] = new tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath + ".png"));
            tile[index].image = scaleimage(tile[index].image, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            tile[index].down[0] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
            tile[index].down[1] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize-6);
            tile[index].down[2] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize-12);
            tile[index].down[3] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize-18);
            tile[index].down[4] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize-24);
            tile[index].down[5] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize-30);
            tile[index].down[6] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize-36);
            tile[index].down[7] = cropimage(tile[index].image, 0, 0, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize-42);
            tile[index].canjumpover = canjumpover;
            tile[index].air = air;
            tile[index].transparent = transparent;
            tile[index].hot = hot;
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
        worldcol = 0;
             worldrow = 0;
             worldlayer = 0;
            while (worldcol < MainGame.maxworldcol && worldrow < MainGame.maxworldrow && worldlayer < MainGame.maxworldlayer) {
                int tileID = mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer];
                    int worldX = worldcol * GlobalGameThreadConfigs.tilesize;
                    int worldY = worldrow * GlobalGameThreadConfigs.tilesize;
                    double screenX = (worldX - GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX);
                    double screenY = worldY - GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY;
                        if (tileID != 46) {
                            boolean shouldrender = true;
                            if (worldlayer + 1 < MainGame.maxworldlayer) {
                                if(worldlayer > GlobalGameThreadConfigs.player.worldz){
                                    int i = (int) (worldlayer - GlobalGameThreadConfigs.player.worldz);
                                    for(int d = 1; d < i; d++){
                                        if(!tile[mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer - d]].transparent){
                                            boolean up, down, right, left;
                                            if(worldrow+1 < 200){
                                                up = !tile[mapRendererID[MainGame.currentmap][worldcol][worldrow+1][worldlayer - d]].transparent;
                                            }else{
                                                up = true;
                                            }
                                            if(worldrow-1 > 0){
                                                down = !tile[mapRendererID[MainGame.currentmap][worldcol][worldrow-1][worldlayer - d]].transparent;
                                            }else{
                                                down = true;
                                            }
                                            if(worldcol+1 < 200){
                                                right =!tile[mapRendererID[MainGame.currentmap][worldcol+1][worldrow][worldlayer - d]].transparent;
                                            }else{
                                                right = true;
                                            }
                                            if(worldcol-1 > 0){
                                                left = !tile[mapRendererID[MainGame.currentmap][worldcol-1][worldrow][worldlayer - d]].transparent;
                                            }else{
                                                left = true;
                                            }
                                            if(up && down && right && left){
                                                shouldrender = false;
                                            }
                                        }
                                    }
                                }else if(worldlayer < GlobalGameThreadConfigs.player.worldz){
                                    int i = (int) (GlobalGameThreadConfigs.player.worldz - worldlayer);
                                    int d;
                                    for(d = 1; d < i; d++){
                                        if(!tile[mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer + d]].transparent){
                                            boolean up, down, right, left;
                                            if(worldrow+1 < 200){
                                                up = !tile[mapRendererID[MainGame.currentmap][worldcol][worldrow+1][worldlayer + d]].transparent;
                                            }else{
                                                up = true;
                                            }
                                            if(worldrow-1 > 0){
                                                down = !tile[mapRendererID[MainGame.currentmap][worldcol][worldrow-1][worldlayer + d]].transparent;
                                            }else{
                                                down = true;
                                            }
                                            if(worldcol+1 < 200){
                                                right =!tile[mapRendererID[MainGame.currentmap][worldcol+1][worldrow][worldlayer + d]].transparent;
                                            }else{
                                                right = true;
                                            }
                                            if(worldcol-1 > 0){
                                                left = !tile[mapRendererID[MainGame.currentmap][worldcol-1][worldrow][worldlayer + d]].transparent;
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




    public void update(int col, int row, int layer, double x, double y, Graphics2D g2){
        int worldX = worldcol * GlobalGameThreadConfigs.tilesize;
        int worldY = worldrow * GlobalGameThreadConfigs.tilesize;
        try {

            if ((worldX + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldx - GlobalGameThreadConfigs.player.screenX &&
                    (worldX - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX))
                    && worldY + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldy - GlobalGameThreadConfigs.player.screenY &&
                    (worldY - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY)) {
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
                    if (mapspritecounter[MainGame.currentmap][col][row][layer] >= 4) {
                        mapspritecounter[MainGame.currentmap][col][row][layer] = 0;
                        mapspritenumber[MainGame.currentmap][col][row][layer]++;
                        if (mapspritenumber[MainGame.currentmap][col][row][layer] == 5) {
                            mapspritenumber[MainGame.currentmap][col][row][layer] = 0;
                        }
                    }
                    g2.drawImage(fire[mapspritenumber[MainGame.currentmap][col][row][layer]], (int) (x), (int) (y), null);
                }
                if(worldlayer <= GlobalGameThreadConfigs.player.worldz){
                        boolean shouldrender = true;
                        boolean up, down, right, left;
                        if(worldrow+1 < 200){
                            up = !tile[mapRendererID[MainGame.currentmap][col][row+1][layer]].transparent;
                        }else{
                            up = true;
                        }
                        if(worldrow-1 > 0){
                            down = !tile[mapRendererID[MainGame.currentmap][col][row-1][layer]].transparent;
                        }else{
                            down = true;
                        }
                        if(worldcol+1 < 200){
                            right = !tile[mapRendererID[MainGame.currentmap][col+1][row][layer]].transparent;
                        }else{
                            right = true;
                        }
                        if(worldcol-1 > 0){
                            left = !tile[mapRendererID[MainGame.currentmap][col-1][row][layer]].transparent;
                        }else{
                            left = true;
                        }
                        if(up && down && right && left){
                            shouldrender = false;
                        }
                        if(shouldrender){
                            up = false;
                            down = false;
                            right = false;
                            left = false;
                            if(worldrow+1 < 200){
                                up = !tile[mapRendererID[MainGame.currentmap][col][row+1][layer-1]].transparent;
                            }else{
                                up = true;
                            }
                            if(worldrow-1 > 0){
                                down = !tile[mapRendererID[MainGame.currentmap][col][row-1][layer-1]].transparent;
                            }else{
                                down = true;
                            }
                            if(worldcol+1 < 200){
                                right = !tile[mapRendererID[MainGame.currentmap][col+1][row][layer-1]].transparent;
                            }else{
                                right = true;
                            }
                            if(worldcol-1 > 0){
                                left = !tile[mapRendererID[MainGame.currentmap][col-1][row][layer-1]].transparent;
                            }else{
                                left = true;
                            }
                            if(up && down && right && left){
                                shouldrender = false;
                            }
                        }

                        if(shouldrender){
                            g2.drawImage(tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[layer], (int) x, (int)y, null);
                            g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                            g2.fillRect((int) x, (int) y, tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[layer].getWidth(), tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[layer].getHeight());
                        }else{
                            g2.drawImage(tile[mapRendererID[MainGame.currentmap][col][row][layer]].image, (int) x, (int)y, null);
                            g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                            g2.fillRect((int) x, (int) y, 48, 48);
                        }
                    }
                if(worldlayer > GlobalGameThreadConfigs.player.worldz && !(Math.round(GlobalGameThreadConfigs.player.worldx/GlobalGameThreadConfigs.tilesize) == col && Math.round(GlobalGameThreadConfigs.player.worldy/GlobalGameThreadConfigs.tilesize) == row)){
                        boolean shouldrender = true;
                            boolean up, down, right, left;
                            if(worldrow+1 < 200){
                                up = !tile[mapRendererID[MainGame.currentmap][col][row+1][layer]].transparent;
                            }else{
                                up = true;
                            }
                            if(worldrow-1 > 0){
                                down = !tile[mapRendererID[MainGame.currentmap][col][row-1][layer]].transparent;
                            }else{
                                down = true;
                            }
                            if(worldcol+1 < 200){
                                right = !tile[mapRendererID[MainGame.currentmap][col+1][row][layer]].transparent;
                            }else{
                                right = true;
                            }
                            if(worldcol-1 > 0){
                                left = !tile[mapRendererID[MainGame.currentmap][col-1][row][layer]].transparent;
                            }else{
                                left = true;
                            }
                            if(up && down && right && left){
                                shouldrender = false;
                            }
                    if(shouldrender){
                        up = false;
                        down = false;
                        right = false;
                        left = false;
                        if(worldrow+1 < 200){
                            up = !tile[mapRendererID[MainGame.currentmap][col][row+1][layer+1]].transparent;
                        }else{
                            up = true;
                        }
                        if(worldrow-1 > 0){
                            down = !tile[mapRendererID[MainGame.currentmap][col][row-1][layer+1]].transparent;
                        }else{
                            down = true;
                        }
                        if(worldcol+1 < 200){
                            right = !tile[mapRendererID[MainGame.currentmap][col+1][row][layer+1]].transparent;
                        }else{
                            right = true;
                        }
                        if(worldcol-1 > 0){
                            left = !tile[mapRendererID[MainGame.currentmap][col-1][row][layer+1]].transparent;
                        }else{
                            left = true;
                        }
                        if(up && down && right && left){
                            shouldrender = false;
                        }
                    }
                    if(shouldrender){
                    g2.drawImage(tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[layer], (int) x, (int)y, null);
                    g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                    g2.fillRect((int) x, (int) y, tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[layer].getWidth(), tile[mapRendererID[MainGame.currentmap][col][row][layer]].down[layer].getHeight());
                }else{
                        g2.drawImage(tile[mapRendererID[MainGame.currentmap][col][row][layer]].image, (int) x, (int)y, null);
                        g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                        g2.fillRect((int) x, (int) y, 48, 48);
                    }
                }


    }}catch (Exception e){
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
        BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), image.getType());
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        return copyOfImage;
    }
}