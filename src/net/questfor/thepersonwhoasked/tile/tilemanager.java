package net.questfor.thepersonwhoasked.tile;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UtilityTool;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tilemanager {
    public tile[] tile;
    public int[][] mapRendererID;

    public tilemanager()  {
        tile = new tile[100];
        mapRendererID = new int[MainGame.maxworldcol][MainGame.maxworldrow];
        try {
            getTileImage();
            loadmap(GlobalGameThreadConfigs.worldID);
        }catch(Exception e){
            crash.main(e);
        }
    }
    public void getTileImage() {
        // place holder since it can have null pointer exeption//:
        BootUpRenderers(0,"grass00", false);
        BootUpRenderers(1,"grass00", false);
        BootUpRenderers(2,"grass00", false);
        BootUpRenderers(3,"grass00", false);
        BootUpRenderers(4,"grass00", false);
        BootUpRenderers(5,"grass00", false);
        BootUpRenderers(6,"grass00", false);
        BootUpRenderers(7,"grass00", false);
        BootUpRenderers(8,"grass00", false);
        BootUpRenderers(9,"grass00", false);
        //
        BootUpRenderers(10,"grass00", false);
        BootUpRenderers(11,"grass01", false);
        BootUpRenderers(12,"water00", true);
        BootUpRenderers(13,"water01", true);
        BootUpRenderers(14,"water02", true);
        BootUpRenderers(15,"water03", true);
        BootUpRenderers(16,"water04", true);
        BootUpRenderers(17,"water05", true);
        BootUpRenderers(18,"water06", true);
        BootUpRenderers(19,"water07", true);
        BootUpRenderers(20,"water08", true);
        BootUpRenderers(21,"water09", true);
        BootUpRenderers(22,"water10", true);
        BootUpRenderers(23,"water11", true);
        BootUpRenderers(24,"water12", true);
        BootUpRenderers(25,"water13", true);
        BootUpRenderers(26,"road00", false);
        BootUpRenderers(27,"road01", false);
        BootUpRenderers(28,"road02", false);
        BootUpRenderers(29,"road03", false);
        BootUpRenderers(30,"road04", false);
        BootUpRenderers(31,"road05", false);
        BootUpRenderers(32,"road06", false);
        BootUpRenderers(33,"road07", false);
        BootUpRenderers(34,"road08", false);
        BootUpRenderers(35,"road09", false);
        BootUpRenderers(36,"road10", false);
        BootUpRenderers(37,"road11", false);
        BootUpRenderers(38,"road12", false);
        BootUpRenderers(39,"earth", false);
        BootUpRenderers(40,"wall", true);
        BootUpRenderers(41,"tree", true);
    }
    public void BootUpRenderers(int index, String imagePath, boolean collision){
        UtilityTool utool = new UtilityTool();
        try{
            tile[index] = new tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath + ".png"));
            tile[index].image = utool.scaleimage(tile[index].image, MainGame.tilesize, MainGame.tilesize);
            tile[index].collision = collision;

        }catch(Exception e){
            crash.main(e);
        }
    }
    public void loadmap(String s){
        try{
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
             int col = 0;
             int row = 0;
             while(col < MainGame.maxworldcol && row < MainGame.maxworldrow){
                 String line = br.readLine();
                 while(col < MainGame.maxworldcol){
                        String IDS[] = line.split(" ");
                        int num = Integer.parseInt(IDS[col]);
                        mapRendererID[col][row] = num;
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
    public void draw(Graphics2D g2) {
        try {
            int worldcol = 0;
            int worldrow = 0;

            while (worldcol < MainGame.maxworldcol && worldrow < MainGame.maxworldrow) {
                int tileID = mapRendererID[worldcol][worldrow];
                int worldX = worldcol * MainGame.tilesize;
                int worldY = worldrow * MainGame.tilesize;
                double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
                double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
                if ((worldX + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                        (worldX - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                        && worldY + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                        (worldY - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                    g2.drawImage(tile[tileID].image, (int) screenX, (int) screenY, null);
                }
                worldcol++;
                if (worldcol == MainGame.maxworldcol) {
                    worldcol = 0;
                    worldrow++;
                }
            }
        }catch(Exception err){
            crash.main(err);
        }
    }
}