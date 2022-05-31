package net.questfor.thepersonwhoasked.tile;
import net.questfor.thepersonwhoasked.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
public class Tilemanager {
    public  InputStream is;
    public  void set(){
        loadmap( 0);
        loadmap(1);
        loadmap(2);
        loadmap(3);
        loadmap(4);
        loadmap(5);
        loadmap(6);
        loadmap(7);
    }
    public void tileproperties(int number, String image, boolean canjumpover){
        //number -> tile
        //image is its name bacicly
        //canjumpover if you just cannot cross it, like a world border
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

    public  void loadmap(int layer) {
        try {
            is = getClass().getResourceAsStream("/maps/worldV" + layer + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < Main.maxworldcol && row < Main.maxworldrow) {
                String line = br.readLine();
                while (col < Main.maxworldcol) {
                    String IDS[] = line.split(" ");
                    int num = Integer.parseInt(IDS[col]);
                    Main.map.mapRendererID[col][row][layer] = num;
                    col++;
                }
                if (col == Main.maxworldcol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}