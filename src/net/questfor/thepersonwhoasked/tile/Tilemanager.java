package net.questfor.thepersonwhoasked.tile;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UtilityTool;
import net.questfor.thepersonwhoasked.Maingam.crash;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static net.questfor.thepersonwhoasked.GlobalProperties.gp;

public class Tilemanager {
    public int worldcol, worldrow;
    public tile[] tile;
    public int[][][] mapRendererID;
    public static InputStream is;
    public BufferedImage earthright1, earthleft1, earthup1, earthdown1, earth;

    public Tilemanager()  {
        tile = new tile[999];
        mapRendererID = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow];
        try {
            getTileImage();

            loadmap(3,  0);
            loadmap(4,  1);
        }catch(Exception e){
            crash.main(e);
        }
    }
    public void getTileImage() {
        setexternaltextures();
        tileproperties(0,"earthdug1", false, true, -8);
        tileproperties(1,"earth", false, true, -1);
        tileproperties(2,"earth", false, true, -1);
        tileproperties(3,"earth", false, true, -1);
        tileproperties(4,"earth", false, true, -1);
        tileproperties(5,"earth", false, true, -1);
        tileproperties(6,"grass00", false, true, 0);
        tileproperties(7,"grass00", false, true, 0);
        tileproperties(8,"grass00", false, true, 0);
        tileproperties(9,"grass00", false, true, 0);
        tileproperties(10,"grass00", false, true, -1);
        tileproperties(11,"grass01", false, true, -1);
        tileproperties(12,"water00", true, true, 1);
        tileproperties(13,"water01", true, true, 1);
        tileproperties(14,"water02", true, true, 1);
        tileproperties(15,"water03", true, true, 1);
        tileproperties(16,"water04", true, true, 1);
        tileproperties(17,"water05", true, true, 1);
        tileproperties(18,"water06", true, true, 1);
        tileproperties(19,"water07", true, true, 1);
        tileproperties(20,"water08", true, true, 1);
        tileproperties(21,"water09", true, true, 1);
        tileproperties(22,"water10", true, true, 1);
        tileproperties(23,"water11", true, true, 1);
        tileproperties(24,"water12", true, true, 1);
        tileproperties(25,"water13", true, true, 1);
        tileproperties(26,"road00", false, true, -1);
        tileproperties(27,"road01", false, true, -1);
        tileproperties(28,"road02", false, true, -1);
        tileproperties(29,"road03", false, true, -1);
        tileproperties(30,"road04", false, true, -1);
        tileproperties(31,"road05", false, true, -1);
        tileproperties(32,"road06", false, true, -1);
        tileproperties(33,"road07", false, true, -1);
        tileproperties(34,"road08", false, true, -1);
        tileproperties(35,"road09", false, true, -1);
        tileproperties(36,"road10", false, true, -1);
        tileproperties(37,"road11", false, true, -1);
        tileproperties(38,"road12", false, true, -1);
        tileproperties(39,"earth", false, true, -1);
        tileproperties(40,"wall", true, true, 16);
        tileproperties(41,"tree", true, true, 8);
        tileproperties(42,"hut", true, true, 16);
        tileproperties(43,"floor01", false, true, -1);
        tileproperties(44,"table01", true, true, 1);
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

    public void tileproperties(int index, String imagePath, boolean collision, boolean canjumpover, int worldz){
        UtilityTool utool = new UtilityTool();
        try{
            tile[index] = new tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath + ".png"));
            tile[index].image = utool.scaleimage(tile[index].image, MainGame.tilesize, MainGame.tilesize);
            tile[index].collision = collision;
            tile[index].canjumpover = canjumpover;
            tile[index].worldz = worldz;
        }catch(Exception e){
            crash.main(e);
        }
    }
    public void loadmap(int s,  int mapID){
        try {
                is = getClass().getResourceAsStream("/maps/worldV" + s + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
             int col = 0;
             int row = 0;
             while(col < MainGame.maxworldcol && row < MainGame.maxworldrow){
                 String line = br.readLine();
                 while(col < MainGame.maxworldcol){
                        String IDS[] = line.split(" ");
                        int num = Integer.parseInt(IDS[col]);
                            mapRendererID[mapID][col][row] = num;
                        col++;
                 }
                 if(col == MainGame.maxworldcol){
                     col = 0;
                     row++;
                 }
             }
             br.close();
        }catch(Exception e){
            crash.main(e);
        }
    }
    public void drawground(Graphics2D g2) {
             worldcol = 0;
             worldrow = 0;
            while (worldcol < MainGame.maxworldcol && worldrow < MainGame.maxworldrow) {
                int tileID = mapRendererID[MainGame.currentmap][worldcol][worldrow];

                int worldX = worldcol * MainGame.tilesize;
                int worldY = worldrow * MainGame.tilesize;
                double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
                double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
                if ((worldX + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                        (worldX - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                        && worldY + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                        (worldY - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                    if (tileID != 40){
                        g2.drawImage(tile[tileID].image, (int) screenX, (int) screenY, null);
                        update(worldcol, worldrow, screenX, screenY, g2);
                }else{
                        g2.drawImage(earth, (int) screenX, (int) screenY, null);
                    }


                }
                worldcol++;
                if (worldcol == MainGame.maxworldcol) {
                    worldcol = 0;
                    worldrow++;
                }
            }
    }
    public void update(int col, int row, double x, double y, Graphics2D g2){
        try {
            if(mapRendererID[MainGame.currentmap][col][row] == 0) {
                if (mapRendererID[MainGame.currentmap][col + 1][row] == 0) {
                    if (mapRendererID[MainGame.currentmap][col][row] == 0)
                        g2.drawImage(earthright1, (int) (x), (int) (y), null);
                }
                if (mapRendererID[MainGame.currentmap][col - 1][row] == 0) {
                    if (mapRendererID[MainGame.currentmap][col][row] == 0)
                        g2.drawImage(earthleft1, (int) (x), (int) (y), null);
                }
                if (mapRendererID[MainGame.currentmap][col][row - 1] == 0) {
                    if (mapRendererID[MainGame.currentmap][col][row] == 0)
                        g2.drawImage(earthup1, (int) (x), (int) (y), null);
                }
                if (mapRendererID[MainGame.currentmap][col][row + 1] == 0) {
                    if (mapRendererID[MainGame.currentmap][col][row] == 0)
                        g2.drawImage(earthdown1, (int) (x), (int) (y), null);
                }
            }else  if(mapRendererID[MainGame.currentmap][col][row] == 40){
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
                    if (Ydistance < 10 && Xdistance < 5){
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
    public void drawwalls(Graphics2D g2) {
        worldcol = 0;
        worldrow = 0;
        while (worldcol < MainGame.maxworldcol && worldrow < MainGame.maxworldrow) {
            int tileID = mapRendererID[MainGame.currentmap][worldcol][worldrow];

            int worldX = worldcol * MainGame.tilesize;
            int worldY = worldrow * MainGame.tilesize;
            double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
            if ((worldX + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldX - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldY + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldY - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                if (tileID == 40) {
                    update(worldcol, worldrow, screenX, screenY, g2);
                    g2.drawImage(tile[tileID].image, (int) screenX, (int) screenY, null);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
            }
            worldcol++;
            if (worldcol == MainGame.maxworldcol) {
                worldcol = 0;
                worldrow++;
            }
        }
    }
}