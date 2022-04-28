package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.Maingam.UtilityTool;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
//is the parent class for all entities including player
public class LivingEntity {
    public MainGame gp;
    public double worldx;
    public double worldy;
    public double worldz = 0;
    public double speed;
    public int maxhealth;
    public int health;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spritecounter = 0;
    public int spritenumber = 1;
    public Rectangle hitbox;
    public int hitboxdefaultx, hitboxdefaulty;

    public boolean hitboxe = false;
    public int actionLock = 0;
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;
    public LivingEntity(MainGame gpp){
        this.gp = gpp;
    }
    public void setAction(){}
    public void speak(){
        //dialogue functions

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        UI.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case "up" -> {direction = "down";worldy += MainGame.tilesize;}
            case "down" -> {direction = "up"; worldy -= MainGame.tilesize;}
            case "right" -> {direction = "left"; worldx += MainGame.tilesize;}
            case "left" -> {direction = "right"; worldx -= MainGame.tilesize;}
        }
    }
    public void update(){
        //AI for Monsters And NPCS
        setAction();
        hitboxe = false;
        gp.hregister.checkTile(this);
        gp.hregister.checkObject(this, false);
        gp.hregister.PlayerColide(this);
        if (!hitboxe) {
            switch (direction){
                case"up":
                    worldy = worldy - speed;
                    break;
                case "down":
                    worldy = worldy + speed;
                    break;

                case "right":
                    worldx = worldx + speed;
                    break;
                case "left":
                    worldx = worldx - speed;
                    break;
            }
        }
        spritecounter++;
        if (spritecounter > 12) {
            if (spritenumber == 1) {
                spritenumber = 2;
            } else if (spritenumber == 2) {
                spritenumber = 1;
            }
            spritecounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        //RENDERER
        try {
            double screenX = (worldx - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldy - MainGame.player.worldy + MainGame.player.screenY;
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                BufferedImage image = null;
                switch (direction) {
                    case "up":
                        if (spritenumber == 1) {
                            image = up1;
                        } else if (spritenumber == 2) {
                            image = up2;
                        }
                        break;
                    case "down":
                        if (spritenumber == 1) {
                            image = down1;
                        } else if (spritenumber == 2) {
                            image = down2;
                        }
                        break;
                    case "right":
                        if (spritenumber == 1) {
                            image = right1;
                        } else if (spritenumber == 2) {
                            image = right2;
                        }
                        break;
                    case "left":
                        if (spritenumber == 1) {
                            image = left1;
                        } else if (spritenumber == 2) {
                            image = left2;
                        }
                        break;
                }
                g2.drawImage(image, (int) screenX, (int) screenY, MainGame.tilesize, MainGame.tilesize, null);
            }
        }catch (Exception e){
            crash.main(e);
        }
    }
    public BufferedImage BufferedRenderer(String imagePath){
        //OPTIMIZES THE RENDERER TO MAKE IT MORE EFFICIENT
        UtilityTool utool = new UtilityTool();
        BufferedImage ScaledImage = null;
        try{
            ScaledImage = ImageIO.read(getClass().getResourceAsStream("/entities/"+imagePath+".png"));
            ScaledImage = utool.scaleimage(ScaledImage, MainGame.tilesize, MainGame.tilesize);
        }catch (Exception e) {
            crash.main(e);
        }
        return ScaledImage;
    }
}
