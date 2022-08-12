package net.questfor.thepersonwhoasked.tile;

import java.awt.image.BufferedImage;

public class tile {
        //DEFAULT VALUES ALL TILES AND BLOCKS IN WORLD USE
    public BufferedImage image;
    public BufferedImage[] down = new BufferedImage[8];
    public boolean canjumpover, fluid, air, transparent, hot;
    public String name;

}
