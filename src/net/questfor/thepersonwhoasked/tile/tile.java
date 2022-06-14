package net.questfor.thepersonwhoasked.tile;

import java.awt.image.BufferedImage;

public class tile {
        //DEFAULT VALUES ALL TILES AND BLOCKS IN WORLD USE
    public BufferedImage image;
    public BufferedImage[] down = new BufferedImage[7];
    public boolean canjumpover = false;

    public boolean fluid = false;
}
