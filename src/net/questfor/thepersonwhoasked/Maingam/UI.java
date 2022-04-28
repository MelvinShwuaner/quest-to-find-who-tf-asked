package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.OBJHeart;

import net.questfor.thepersonwhoasked.objects.OBJkey;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    //basic values
    static MainGame gp;
    public static LivingEntity heart = new OBJHeart(gp);
    static Font bit8Font, cursiveFont;
    static Graphics2D g2;
    public static boolean messageON = false;
    public static String message = "";
    public static String currentDialogue = "";
    public static BufferedImage heart_full = heart.image;
    public static BufferedImage heart_half = heart.image2;
    public static BufferedImage heart_blank = heart.image3;
    public static int commandnum = 0;
    public UI(MainGame GPP){
        //sets the values
        try {
            this.gp = GPP;
            InputStream cursive = getClass().getResourceAsStream("/Fonts/Purisa Bold.ttf");
            InputStream bit8 = getClass().getResourceAsStream("/Fonts/x12y16pxMaruMonica.ttf");
            bit8Font = Font.createFont(Font.TRUETYPE_FONT, bit8);
            cursiveFont = Font.createFont(Font.TRUETYPE_FONT, cursive);
        }catch (Exception e){
            crash.main(e);
        }
    }
    public static void showMESSAGE(String text){
        message = text;
        messageON = true;
    }

    public static void draw(Graphics2D g2d) {
        //draws uis
        g2 = g2d;
        g2.setFont(bit8Font);
        g2.setColor(Color.white);
        if (GlobalGameThreadConfigs.isinTital) {
            drawTitleScreen();
        }
        if (!GlobalGameThreadConfigs.isinTital){
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState) {
                drawPlayerBar();
            }
        if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.pauseState) {
            drawPlayerBar();
            drawPauseScreen();
        }
        if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.dialogueState) {
            drawPlayerBar();
            drawDialogueScreen();
        }
    }
    }

    private static void drawPlayerBar() {
        int x = MainGame.tilesize/2;
        int y = MainGame.tilesize/2;
        int i = 0;
        while (i < MainGame.player.maxhealth/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tilesize;
        }
        x = MainGame.tilesize/2;
        y = MainGame.tilesize/2;
        i = 0;
        while(i < gp.player.health){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.health){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tilesize;

        }
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
        if(gp.keyM.checkFPS) {
            if (gp.player.invincible == true) {
                g2.drawString("" + gp.player.hitTime, 10, 200);
            }
        }

    }

    private static void drawTitleScreen() {
        try {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
            String text = "Adventure";
            int x = getXforCenterText(text);
            int y = gp.tilesize * 2;
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            g2.setColor(Color.black);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "START NEW GAME";
            x = getXforCenterText(text);
            y += MainGame.tilesize * 3;
            if(commandnum == 0){
                g2.drawString(">", x-MainGame.tilesize, y);
            }
            g2.drawString(text, x, y);
            g2.setColor(Color.white);
            text = "CONTINUE FROM SAVE FILE";
            x = getXforCenterText(text);
            y += MainGame.tilesize * 2;
            if(commandnum == 1){
                g2.drawString(">", x-MainGame.tilesize, y);
            }
            g2.drawString(text, x, y);
            g2.setColor(Color.red);
            text = "QUIT GAME";
            x = getXforCenterText(text);
            y += MainGame.tilesize * 2;
            if(commandnum == 2){
                g2.drawString(">", x-MainGame.tilesize, y);
            }
            g2.drawString(text, x, y);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void drawDialogueScreen() {
        int x = gp.tilesize * 2;
        int y = gp.tilesize/2;
        int width = MainGame.screenwidth - (gp.tilesize*4);
        int height = gp.tilesize*4;
        drawSubWindow(x, y, width, height);
        x += gp.tilesize;
        y += gp.tilesize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public static void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }


    public static void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "Paused";
        int x;
        x = getXforCenterText(text);
        int y = MainGame.screenheight / 2;
        g2.drawString(text, x, y);

    }
    public static int getXforCenterText(String text){
        int Length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = MainGame.screenwidth/2 - Length/2;
        return x;
    }
}
