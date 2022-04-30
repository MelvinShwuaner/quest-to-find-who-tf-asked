package net.questfor.thepersonwhoasked.Maingam;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.OBJHeart;

import javax.management.monitor.GaugeMonitor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    //basic values
    static MainGame gp;
    public static LivingEntity heart = new OBJHeart(gp);
    static Font bit8Font, cursiveFont;
    static Graphics2D g2;
    //CHAT AND MSGS//
    static ArrayList<String> messages = new ArrayList<>();
    static ArrayList<Integer> messageID = new ArrayList<>();
    public static String currentDialogue = "";
    public static BufferedImage heart_full = heart.image;
    public static BufferedImage heart_half = heart.image2;
    public static BufferedImage heart_blank = heart.image3;
    //STATS//
    public static int frameX = gp.tilesize - 350;
    public static int invX = (gp.tilesize * 9) + 350;

    public static int dframeX = invX;
    public static int frameY = gp.tilesize;
    public static boolean transitionfinushed = false;
    public static int SlotCol = 0;
    public static int slotRow = 0;
    public static int commandnum = 0;
    public static int frameWidth = gp.tilesize * 6;
    public static int frameHeight = gp.tilesize * 10;
    public static boolean slotstate = false;

    public UI(MainGame GPP) {
        //sets the values
        try {
            this.gp = GPP;
            InputStream cursive = getClass().getResourceAsStream("/Fonts/Purisa Bold.ttf");
            InputStream bit8 = getClass().getResourceAsStream("/Fonts/x12y16pxMaruMonica.ttf");
            bit8Font = Font.createFont(Font.TRUETYPE_FONT, bit8);
            cursiveFont = Font.createFont(Font.TRUETYPE_FONT, cursive);
        } catch (Exception e) {
            crash.main(e);
        }
    }


    public static void draw(Graphics2D g2d) {
        //draws uis
        g2 = g2d;
        g2.setFont(bit8Font);
        g2.setColor(Color.white);
        if (GlobalGameThreadConfigs.isinTital) {
            drawTitleScreen();
        }
        if (!GlobalGameThreadConfigs.isinTital) {
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState) {
                drawPlayerBar();
                if (frameX != gp.tilesize - 350) {
                    displaySTATS();
                    displayINV();
                }if (GlobalGameThreadConfigs.inchest) {
                    DisplayChest();
                }else{
                    frameHeight = gp.tilesize*10;
                }
                if (!GlobalGameThreadConfigs.CharacterStats) {
                    drawMessages();
                }
            }
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.pauseState) {
                drawPlayerBar();
                drawPauseScreen();
            }
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.dialogueState) {
                drawPlayerBar();
                drawDialogueScreen();
            }
            if (GlobalGameThreadConfigs.CharacterStats) {
                if (!transitionfinushed) {
                    frameX += 5;
                    invX -= 5;
                    dframeX = invX - (gp.tilesize * 3) - 24;
                }
                if (frameX == gp.tilesize - 45) {
                    transitionfinushed = true;
                }
            } else {
                if (frameX != gp.tilesize - 350) {
                    frameX -= 5;
                    invX += 5;
                    dframeX = invX - (gp.tilesize * 3) - 24;
                } else {
                    transitionfinushed = false;
                }
            }
        }
    }

    private static void DisplayChest() {
        frameHeight = gp.tilesize * 5;
        //SLOTS
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        for (int i = 0; i < gp.obj[gp.player.objindex].inventory.size(); i++) {
            g2.drawImage(gp.obj[gp.player.objindex].inventory.get(i).down1, slotX, slotY, null);
            slotX += gp.tilesize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gp.tilesize;
            }
        }
        selectItem();
        if (slotstate) {
            int cursorX = slotXstart + (gp.tilesize * SlotCol);
            int cursorY = slotYstart + (gp.tilesize * slotRow);
            int cursorwidth = gp.tilesize;
            int cursorheight = gp.tilesize;
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.white);
            g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
            int dframeY = frameY + +frameHeight;
            int dframewidth = frameWidth * 2 - ((gp.tilesize * 2) + 24);
            int dframeheight = gp.tilesize * 3;
            int textx = (dframeX + 20);
            int texty = dframeY + gp.tilesize;
            int itemIndex = getItemIndex();
            if (itemIndex < gp.obj[gp.player.objindex].inventory.size()) {
                drawSubWindow(dframeX, dframeY, dframewidth, dframeheight);
                for (String line : gp.obj[gp.player.objindex].inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textx, texty);
                    texty += 32;
                }
            }
        }
    }

    private static void displayINV() {
        //FRAME
        int frameWidth = gp.tilesize * 6;
        int frameHeight = gp.tilesize * 5;
        drawSubWindow(invX, frameY, frameWidth, frameHeight);
        //SLOTS
        final int slotXstart = invX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            if (gp.player.inventory.get(i) == gp.player.currentweapon || gp.player.inventory.get(i) == gp.player.currentshield) {
                g2.setColor(new Color(0xFFFF00));
                g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += gp.tilesize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gp.tilesize;
            }
        }
        if (!slotstate || !GlobalGameThreadConfigs.inchest){
            int cursorX = slotXstart + (gp.tilesize * SlotCol);
        int cursorY = slotYstart + (gp.tilesize * slotRow);
        int cursorwidth = gp.tilesize;
        int cursorheight = gp.tilesize;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
        int dframeY = frameY + +frameHeight;
        int dframewidth = frameWidth * 2 - ((gp.tilesize * 2) + 24);
        int dframeheight = gp.tilesize * 3;
        int textx = (dframeX + 20);
        int texty = dframeY + gp.tilesize;
        int itemIndex = getItemIndex();
        if (itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dframeX, dframeY, dframewidth, dframeheight);
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textx, texty);
                texty += 32;
            }
        }
    }
    }

    public static int getItemIndex() {
        int itemindex = SlotCol + (slotRow * 5);
        return itemindex;
    }
    public static void selectItem() {
        int ItemIndex = UI.getItemIndex();
        if (KeyHandler.moveitem) {
            KeyHandler.moveitem = false;
            if (!slotstate) {
                if (ItemIndex < gp.player.inventory.size()) {

                    if (gp.obj[gp.player.objindex].inventory.size() != gp.obj[gp.player.objindex].inventorysize) {
                        LivingEntity SelectedItem = gp.player.inventory.get(ItemIndex);
                        gp.player.inventory.remove(SelectedItem);
                        gp.obj[gp.player.objindex].inventory.add(SelectedItem);
                    }
                }
            } else {
                if (ItemIndex < gp.obj[gp.player.objindex].inventory.size()) {
                    if (gp.player.inventory.size() != gp.player.inventorysize) {
                        LivingEntity SelectedItem = gp.obj[gp.player.objindex].inventory.get(ItemIndex);
                        gp.obj[gp.player.objindex].inventory.remove(SelectedItem);
                        gp.player.inventory.add(SelectedItem);
                    }
                }
            }
        }
    }

    private static void drawMessages() {
        int Messagex = gp.tilesize - 30;
        int Messagey = gp.tilesize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i) != null) {
                g2.setColor(Color.BLACK);
                g2.drawString(messages.get(i), Messagex + 2, Messagey);
                g2.setColor(Color.white);
                g2.drawString(messages.get(i), Messagex, Messagey);
                int ID = messageID.get(i) + 1;
                messageID.set(i, ID);
                Messagey += 50;
                if (messageID.get(i) > 180) {
                    messages.remove(i);
                    messageID.remove(i);
                }
            }
        }
    }

    public static void displaySTATS() {

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //TEXT//
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20F));
        if (!GlobalGameThreadConfigs.inchest){
        int textX = frameX + 20;
        int textY = frameY + gp.tilesize;
        final int lineHeight = 30;
        g2.drawString("level: " + gp.player.level, textX, textY);
        textY += lineHeight;
        g2.drawString("health: " + gp.player.health + "/" + gp.player.maxhealth, textX, textY);
        textY += lineHeight;
        g2.drawString("strength: " + gp.player.strength, textX, textY);
        textY += lineHeight;
        g2.drawString("defence: " + gp.player.defence, textX, textY);
        textY += lineHeight;
        g2.drawString("dexterity: " + gp.player.dexterity, textX, textY);
        textY += lineHeight;
        g2.drawString("XP: " + gp.player.XP + "/" + gp.player.MaxXP, textX, textY);
        textY += lineHeight;
        g2.drawString("bobux: " + gp.player.bobux, textX, textY);
        textY += lineHeight;
        g2.drawString("weapon: " + gp.player.currentweapon.name, textX, textY);
        textY += lineHeight;
        g2.drawString("shield: " + gp.player.currentshield.name, textX, textY);
        textY += lineHeight;
    }

}
    public static void addMessages(String text){
        messages.add(text);
        messageID.add(0);
    }
    private static void drawPlayerBar() {
        int x = MainGame.tilesize / 2;
        int y = MainGame.tilesize / 2;
        int i = 0;
        while (i < MainGame.player.maxhealth / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tilesize;
        }
        x = MainGame.tilesize / 2;
        y = MainGame.tilesize / 2;
        i = 0;
        while (i < gp.player.health) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.health) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tilesize;

        }
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
        if (gp.keyM.checkFPS) {
            if (gp.player.invincible == true) {
                g2.drawString("" + gp.player.hitTime, 10, 200);
            }
        }
        //2022 words long -------------------//
    }private static void drawTitleScreen() {try {g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));String text = "Quest To Find Who Asked";int x = getXforCenterText(text);int y = gp.tilesize * 2;g2.setColor(Color.black);g2.drawString(text, x+5, y+5);g2.setColor(Color.white);g2.drawString(text, x, y);g2.setColor(Color.black);g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));text = "START NEW GAME";x = getXforCenterText(text);y += MainGame.tilesize * 3;if(commandnum == 0){g2.drawString(">", x-MainGame.tilesize, y);}g2.drawString(text, x, y);g2.setColor(Color.white);text = "CONTINUE FROM SAVE FILE";x = getXforCenterText(text);y += MainGame.tilesize * 2;if(commandnum == 1){g2.drawString(">", x-MainGame.tilesize, y);}g2.drawString(text, x, y);g2.setColor(Color.red);text = "QUIT GAME";x = getXforCenterText(text);y += MainGame.tilesize * 2;if(commandnum == 2){g2.drawString(">", x-MainGame.tilesize, y);}g2.drawString(text, x, y);}catch (Exception e){e.printStackTrace();}}private static void drawDialogueScreen() {int x = gp.tilesize * 2;int y = gp.tilesize/2;int width = MainGame.screenwidth - (gp.tilesize*4);int height = gp.tilesize*4;drawSubWindow(x, y, width, height);x += gp.tilesize;y += gp.tilesize;g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));for(String line : currentDialogue.split("\n")){g2.drawString(line, x, y);y += 40;}}public static void drawSubWindow(int x, int y, int width, int height){Color c = new Color(0, 0, 0, 210);g2.setColor(c);g2.fillRoundRect(x, y, width, height, 35, 35);c = new Color(255, 255, 255);g2.setColor(c);g2.setStroke(new BasicStroke(5));g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);}public static void drawPauseScreen(){g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));String text = "Paused";int x;x = getXforCenterText(text);int y = MainGame.screenheight / 2;g2.drawString(text, x, y);}public static int getXforCenterText(String text){int Length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();int x = MainGame.screenwidth/2 - Length/2;return x;}}