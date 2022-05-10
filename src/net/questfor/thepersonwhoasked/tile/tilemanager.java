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
    public int[][][] mapRendererID;
    public static InputStream is;

    public tilemanager()  {
        tile = new tile[100];
        mapRendererID = new int[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow];
        try {
            getTileImage();

            loadmap(GlobalGameThreadConfigs.worldID, 1, 0);
            loadmap(1, 2, 1);

        }catch(Exception e){
            crash.main(e);
        }
    }
    public void getTileImage() {
        BootUpRenderers(0,"grass00", false, true, 0);
        BootUpRenderers(1,"grass00", false, true, 0);
        BootUpRenderers(2,"earthdug1", false, true, -2);
        BootUpRenderers(3,"grass00", false, true, 0);
        BootUpRenderers(4,"grass00", false, true, 0);
        BootUpRenderers(5,"grass00", false, true, 0);
        BootUpRenderers(6,"grass00", false, true, 0);
        BootUpRenderers(7,"grass00", false, true, 0);
        BootUpRenderers(8,"grass00", false, true, 0);
        BootUpRenderers(9,"grass00", false, true, 0);

        BootUpRenderers(10,"grass00", false, true, -1);
        BootUpRenderers(11,"grass01", false, true, -1);
        BootUpRenderers(12,"water00", true, true, 1);
        BootUpRenderers(13,"water01", true, true, 1);
        BootUpRenderers(14,"water02", true, true, 1);
        BootUpRenderers(15,"water03", true, true, 1);
        BootUpRenderers(16,"water04", true, true, 1);
        BootUpRenderers(17,"water05", true, true, 1);
        BootUpRenderers(18,"water06", true, true, 1);
        BootUpRenderers(19,"water07", true, true, 1);
        BootUpRenderers(20,"water08", true, true, 1);
        BootUpRenderers(21,"water09", true, true, 1);
        BootUpRenderers(22,"water10", true, true, 1);
        BootUpRenderers(23,"water11", true, true, 1);
        BootUpRenderers(24,"water12", true, true, 1);
        BootUpRenderers(25,"water13", true, true, 1);
        BootUpRenderers(26,"road00", false, true, -1);
        BootUpRenderers(27,"road01", false, true, -1);
        BootUpRenderers(28,"road02", false, true, -1);
        BootUpRenderers(29,"road03", false, true, -1);
        BootUpRenderers(30,"road04", false, true, -1);
        BootUpRenderers(31,"road05", false, true, -1);
        BootUpRenderers(32,"road06", false, true, -1);
        BootUpRenderers(33,"road07", false, true, -1);
        BootUpRenderers(34,"road08", false, true, -1);
        BootUpRenderers(35,"road09", false, true, -1);
        BootUpRenderers(36,"road10", false, true, -1);
        BootUpRenderers(37,"road11", false, true, -1);
        BootUpRenderers(38,"road12", false, true, -1);
        BootUpRenderers(39,"earth", false, true, -1);
        BootUpRenderers(40,"wall", true, true, 2);
        BootUpRenderers(41,"tree", true, true, 1);
        BootUpRenderers(42,"hut", true, true, 2);
        BootUpRenderers(43,"floor01", false, true, -1);
        BootUpRenderers(44,"table01", true, true, 1);
    }
    public void BootUpRenderers(int index, String imagePath, boolean collision, boolean canjumpover, int worldz){
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
    public void loadmap(int s, int worldtype, int mapID){
        try {
            if (worldtype == 1){
                is = getClass().getResourceAsStream("/maps/worldV" + s + ".txt");
        }else if(worldtype == 2){
                is = getClass().getResourceAsStream("/structures/interior" + s + ".txt");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
             int col = 0;
             int row = 0;
             while(col < MainGame.maxworldcol && row < MainGame.maxworldrow){
                 String line = br.readLine();
                 while(col < MainGame.maxworldcol){
                        String IDS[] = line.split(" ");
                        int num = Integer.parseInt(IDS[col]);
                        ///if(tile[mapRendererID[mapID][col][row]]. null) {
                            mapRendererID[mapID][col][row] = num;
                        //}
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
                int tileID = mapRendererID[MainGame.currentmap][worldcol][worldrow];
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