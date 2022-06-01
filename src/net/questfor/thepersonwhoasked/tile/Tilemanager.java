package net.questfor.thepersonwhoasked.tile;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UtilityTool;
import net.questfor.thepersonwhoasked.Maingam.crash;
import net.questfor.thepersonwhoasked.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static net.questfor.thepersonwhoasked.GlobalProperties.gp;

public class Tilemanager {
    public int worldcol, worldrow, worldlayer;
    public tile[] tile;
    public int[][][][] mapRendererID;
    public  String is;
    public BufferedImage earthright1, earthleft1, earthup1, earthdown1, earth;
    boolean drawpath = true;

    public Tilemanager()  {
        tile = new tile[999];
        mapRendererID = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow][MainGame.maxworldlayer];
        try {
            getTileImage();
            loadmap(0);
            //loadmap(4,  1);
        }catch(Exception e){
            crash.main(e);
        }
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
        tileproperties(46,"air", true);
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
            earth = ImageIO.read(getClass().getResourceAsStream("/tiles/earth" + ".png"));
            earth = scaleimage(earth, gp.tilesize, gp.tilesize);
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
            tile[index].canjumpover = canjumpover;

        }catch(Exception e){
            crash.main(e);
        }
    }
    public void loadmap(int mapID){
        try {
                is = "maps/worldV" + mapID + ".amogusdababymilkfilestoragethingyidk";
            FileInputStream fis = new FileInputStream(is);
            ObjectInputStream ois = new ObjectInputStream(fis);
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
                int worldX = worldcol * MainGame.tilesize;
                int worldY = worldrow * MainGame.tilesize;
                double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
                double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
                if ((worldX + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                        (worldX - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                        && worldY + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                        (worldY - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                    if (tileID != 46){
                        if(mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer+1] == 46){
                        g2.drawImage(tile[tileID].image, (int) screenX, (int) screenY, null);
                        update(worldcol, worldrow, worldlayer, screenX, screenY, g2);
                }
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
                }
                worldcol++;
                if (worldcol == MainGame.maxworldcol) {
                    worldcol = 0;
                    worldrow++;
                }
                if(worldrow == MainGame.maxworldrow){
                    worldrow = 0;
                    worldlayer++;
                }
            }

    }
    public void update(int col, int row, int layer, double x, double y, Graphics2D g2){
        try {
            if(mapRendererID[MainGame.currentmap][col][row][layer] == 0) {
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
            }else  if(mapRendererID[MainGame.currentmap][col][row][layer] == 40){
                if(gp.player.worldz < 0) {
                    float Xdistance;
                    float Ydistance;
                    if (col > gp.player.worldx / gp.tilesize) {
                        Xdistance = (float) (col - (gp.player.worldx / gp.tilesize));
                    }else{
                        Xdistance = (float) ((gp.player.worldx / gp.tilesize) - col);
                    }
                    if (row > gp.player.worldy / gp.tilesize) {
                        Ydistance = (float) (row - (gp.player.worldy / gp.tilesize));
                    }else{
                        Ydistance = (float) ((gp.player.worldy / gp.tilesize) - row);
                    }
                    if (Ydistance < 5 && Xdistance < 5){
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Xdistance/5));
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
}