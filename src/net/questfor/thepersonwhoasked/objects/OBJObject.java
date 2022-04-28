package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UtilityTool;
import net.questfor.thepersonwhoasked.Maingam.crash;

import java.awt.*;
import java.awt.image.BufferedImage;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.tilesize;

public class OBJObject {
    //sets default values
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public double worldx, worldy;
    public Rectangle hitbox = new Rectangle(0, 0, tilesize, tilesize);
    public int hitboxdefaultx = 0;
    public int hitboxdefaulty = 0;
    UtilityTool utool = new UtilityTool();
    public void draw(Graphics2D g2d) {
        try {
            double screenX = (worldx - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldy - MainGame.player.worldy + MainGame.player.screenY;
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                g2d.drawImage(image, (int) screenX, (int) screenY, MainGame.tilesize, MainGame.tilesize, null);
            }
        }catch (Exception e){
            crash.main(e);
        }
    }
}
