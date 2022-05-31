package net.questfor.thepersonwhoasked.Maingam;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Player;
import net.questfor.thepersonwhoasked.objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
public class UI  {

    //basic values
    static MainGame gp;
    public static LivingEntity heart = new OBJHeart(gp);
    public static LivingEntity ManaCrystal = new OBJ_MANA_CRYSTAL(gp);
    public static  LivingEntity coin = new OBJ_COIN_BRONZE(gp);
    static Font bit8Font, cursiveFont;
    static boolean merging = false, mergingg = false;
    public static LivingEntity merger, mergerr; public static int mergerindex;
    static Graphics2D g2;
    //CHAT AND MSGS//
    static ArrayList<String> messages = new ArrayList<>();
    static ArrayList<Integer> messageID = new ArrayList<>();
    public static String currentDialogue = "";
    public static String currentUI = "";
    //DATA
    public static BufferedImage heart_full = heart.image;
    public static BufferedImage heart_half = heart.image2;
    public static BufferedImage heart_blank = heart.image3;
    public static BufferedImage crystal_full = ManaCrystal.image;
    public static BufferedImage crystal_blank = ManaCrystal.image2;
    public static BufferedImage bobux = coin.down1;

    public static boolean right = false, left = false; public static int stackamount = 1, code = 999;
    //STATS//
    public static int frameX = gp.tilesize - 350;
    public static int invX = (gp.tilesize * 13) + 350;
    public static String fullscreentext = "Windowed";
    public static int dframeX = invX, frameY = gp.tilesize;
    public static boolean transitionfinushed = false;
    public static int SlotCol = 0, slotRow = 0;
    public static int npcslotcol = 0, npcslotrow = 0;
    public static int commandnum = 0;
    public static int frameWidth = gp.tilesize * 6;
    public static int frameHeight = gp.tilesize * 10;
    public static boolean slotstate = false;
    public static int optionstate = 0;
    public static int tradestate = 0;
    public static int Tcount = 0;
    public static LivingEntity npc;

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
                    displayINV(gp.player, false, false);
                }
                if (GlobalGameThreadConfigs.inchest) {
                    if(Objects.equals(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].name, "chest"))
                      DisplayChest();
                } else {
                    frameHeight = gp.tilesize * 10;
                }
                if (!GlobalGameThreadConfigs.CharacterStats) {
                    drawMessages();
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
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.pauseState) {
                drawPauseScreen();
            }
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.dialogueState) {
                drawPlayerBar();
                drawDialogueScreen();
            }

            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.optionsstate) {
                drawoptionscreen();
            }
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.GameOverState) {
                drawDeathScreen();
            }
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.transitionstate) {
                drawTransition();
            }
            if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.tradestate) {
                drawTradeScreen();
            }
            if(GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.UIstate){
                drawUI();
            }
        }
    }

    public static void drawUI() {
        switch (currentUI){
            case "Furnace" -> DisplayFurnace();
            case "crafting" -> DisplayTable();
        }
        int x = gp.tilesize * 2;
        int y = gp.tilesize * 9;
        int width = gp.tilesize * 6;
        int height = gp.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] back", x + 24, y + 60);
    }
    public static void DisplayTable() {
        displayUI(npc, false);
        if(code != 999){
            int ItemIndex = getItemIndex();
            if (ItemIndex < gp.player.inventory.size()) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(code) == null){
                    LivingEntity SelectedItem = gp.player.inventory.get(ItemIndex);
                SelectedItem.stacksize -= stackamount;
                if (SelectedItem.stacksize <= 0) {
                    gp.player.inventory.remove(SelectedItem);
                }
                LivingEntity newentity = createnewobject(SelectedItem);
                newentity.stacksize = stackamount;
                GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.set(code, newentity);
            }
            }else{
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(code) != null) {
                    LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(code);
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.set(code, null);

                    LivingEntity newentity = createnewobject(SelectedItem);
                    newentity.stacksize = SelectedItem.stacksize;
                    gp.player.inventory.add(newentity);
                }
            }
            code = 999;
        }
        if(KeyHandler.enterpressed){
            for(int i = 0; i < GlobalGameThreadConfigs.Recipes.length; i++) {
                if (GlobalGameThreadConfigs.Recipes[i] != null) {
                    for (int d = 0; d < 9; d++){
                        if (GlobalGameThreadConfigs.Recipes[i].Recipe[d] != null) {
                            if((GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(d) != null)){
                            if (Objects.equals(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(d).name, GlobalGameThreadConfigs.Recipes[i].Recipe[d].name)) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[d] = true;
                            }
                            }
                        }else {
                            if ((GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(d) == null)) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[d] = true;
                            }
                        }
                        if(d == 8){
                            boolean cancraft = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[0] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[1] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[2] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[3] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[4] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[5] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[6] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[7] && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[8];
                            if(cancraft) {
                                LivingEntity SelectedItem = createnewobject(GlobalGameThreadConfigs.Recipes[i].Result);
                                gp.player.inventory.add(SelectedItem);
                                for (int a = 0; a < 9; a++){
                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(a) != null) {
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(a).stacksize--;
                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(a).stacksize <= 0) {
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.set(a, null);
                                        }
                                    }
                                }
                                i = GlobalGameThreadConfigs.Recipes.length;
                            }
                            for(int c = 0; c < 9; c++) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].slot[c] = false;
                            }
                        }
                }
            }
            }
            KeyHandler.enterpressed = false;
        }
    }

    public static void DisplayFurnace() {
        displayUI(npc, false);
        if (code != 999) {
            int ItemIndex = getItemIndex();
            if (ItemIndex < gp.player.inventory.size()) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(code) == null) {
                    LivingEntity SelectedItem = gp.player.inventory.get(ItemIndex);
                    if (code == 0) {
                        if (SelectedItem.smeltable) {
                            SelectedItem.stacksize -= stackamount;
                            if (SelectedItem.stacksize <= 0) {
                                gp.player.inventory.remove(SelectedItem);
                            }
                            LivingEntity newentity = createnewobject(SelectedItem);
                            newentity.stacksize = stackamount;
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.set(code, newentity);
                        }
                    } else if (code == 6) {
                        if (SelectedItem.fuel) {
                            SelectedItem.stacksize -= stackamount;
                            if (SelectedItem.stacksize <= 0) {
                                gp.player.inventory.remove(SelectedItem);
                            }
                            LivingEntity newentity = createnewobject(SelectedItem);
                            newentity.stacksize = stackamount;
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.set(code, newentity);
                        }
                    }

                }
            } else {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(code) != null) {
                    LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(code);
                    SelectedItem.stacksize -= stackamount;
                    if (SelectedItem.stacksize <= 0) {
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.remove(SelectedItem);
                    }
                    LivingEntity newentity = createnewobject(SelectedItem);
                    newentity.stacksize = stackamount;
                    gp.player.inventory.add(newentity);
                }
            }
            code = 999;
        }

        int x = gp.tilesize * 12;
        int y = gp.tilesize * 9;
        int width = gp.tilesize * 6;
        int height = gp.tilesize * 2;
        drawSubWindow(x, y, width, height);

        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].smelting){
        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].cool > 1 && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].cool <= GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].maxcool) {
            double onescale = 200 / GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].maxcool;
            double HPValue = onescale * GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].cool;
            g2.setColor(Color.white);
            g2.fillRect((int) x+20, (int) y + 20, (int) HPValue, 30);
        }
    }
        if (KeyHandler.enterpressed) {
            KeyHandler.enterpressed = false;
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(0) != null && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(6) != null)
              GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].smelting = true;
            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].hasfinushedcol == 2) {
                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(5) != null) {
                    LivingEntity SelectedItem = createnewobject(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(5));
                    SelectedItem.stacksize = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(5).stacksize;
                    gp.player.inventory.add(SelectedItem);
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.set(5, null);
                }
            }
        }
    }
    public static void drawTradeScreen() {
        switch (tradestate){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        KeyHandler.enterpressed = false;
    }
    public static void trade_select(){
        drawDialogueScreen();
        int x = gp.tilesize*15;
        int y = (int) (gp.tilesize*4.5);
        int width = gp.tilesize*3;
        int height = (int) (gp.tilesize*3.5);
        drawSubWindow(x, y, width, height);
        x+=gp.tilesize;
        y+=gp.tilesize;
        g2.drawString("Buy", x, y);
        if(commandnum == 0){
            g2.drawString(">", x-24, y);
            if(KeyHandler.enterpressed){
                tradestate = 1;
                KeyHandler.enterpressed = false;
            }
        }
        y+=gp.tilesize;
        g2.drawString("Sell", x, y);
        if(commandnum == 1){
            g2.drawString(">", x-24, y);
            if(KeyHandler.enterpressed){
                tradestate = 2;
                KeyHandler.enterpressed = false;
            }
        }
        y+=gp.tilesize;
        g2.drawString("Leave", x, y);
        if(commandnum == 2){
            g2.drawString(">", x-24, y);
            if(KeyHandler.enterpressed){
                commandnum = 0;
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                KeyHandler.enterpressed = false;
            }
        }


    }
    public static void trade_buy() {
        displayINV(npc, true, true);
        int x = gp.tilesize * 2;
        int y = gp.tilesize * 9;
        int width = gp.tilesize * 6;
        int height = gp.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] back", x + 24, y + 60);
        x = gp.tilesize * 12;
        y = gp.tilesize * 9;
        width = gp.tilesize * 6;
        height = gp.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(25f));
        g2.drawString("your bobux: " + gp.player.bobux, x + 24, y + 60);
        int itemindex = getnpcItemIndex();
        if (itemindex < npc.inventory.size()) {
            x = (int) (gp.tilesize * 5.5);
            y = (int) (gp.tilesize * 5.5);
            width = (int) (gp.tilesize * 2.5);
            height = gp.tilesize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(bobux, x + 10, y + 8, 32, 32, null);
            int price = (npc.inventory.get(itemindex).Value * 2)*stackamount;
            String text = "" + price;
            g2.setFont(g2.getFont().deriveFont(15f));
            x += 42;
            g2.drawString(text, x, y + 30);
            if (KeyHandler.enterpressed) {
                if ((npc.inventory.get(itemindex).Value*2)*stackamount > gp.player.bobux) {
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
                    currentDialogue = "You need more bobux to buy that \n broke boy";
                    drawDialogueScreen();
                } else if (gp.player.inventory.size() == gp.player.inventorysize) {
                    tradestate = 0;
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
                    currentDialogue = "Your inventory is full!";
                } else {
                    gp.player.bobux -= (npc.inventory.get(itemindex).Value*2)*stackamount;
                    LivingEntity SelectedItem = npc.inventory.get(itemindex);
                    npc.inventory.get(itemindex).stacksize -= stackamount;
                    if (SelectedItem.stacksize <= 0) {
                        npc.inventory.remove(itemindex);
                    }
                    LivingEntity newItem = createnewobject(SelectedItem);
                    newItem.stacksize = stackamount;
                    gp.player.inventory.add(newItem);
                    stackamount = 1;
                }
            }
        }
    }
    public static void trade_sell(){
        displayINV(npc,true, false);
        int x = gp.tilesize * 2;
        int y = gp.tilesize * 9;
        int width = gp.tilesize * 6;
        int height = gp.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] back", x + 24, y + 60);
        x = gp.tilesize * 12;
        y = gp.tilesize * 9;
        width = gp.tilesize * 6;
        height = gp.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(25f));
        g2.drawString("your bobux: " + gp.player.bobux, x + 24, y + 60);
        int itemindex = getItemIndex();
        if (itemindex < gp.player.inventory.size()) {
            x = (int) (gp.tilesize * 15.5);
            y = (int) (gp.tilesize * 5.5);
            width = (int) (gp.tilesize * 2.5);
            height = gp.tilesize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(bobux, x + 10, y + 8, 32, 32, null);
            int price = (gp.player.inventory.get(itemindex).Value)*stackamount;
            String text = "" + price;
            g2.setFont(g2.getFont().deriveFont(15f));
            x += 42;
            g2.drawString(text, x, y + 30);
            if (KeyHandler.enterpressed) {
                if(gp.player.inventory.get(itemindex) == gp.player.currentshield || gp.player.inventory.get(itemindex) == gp.player.currentweapon){
                    commandnum = 0;
                    GlobalGameThreadConfigs.GameState =GlobalGameThreadConfigs.dialogueState;
                    currentDialogue = "you can not sell a equipped item! un equip it";
                }else {
                    LivingEntity SelectedItem = gp.player.inventory.get(itemindex);
                    gp.player.inventory.get(itemindex).stacksize -= stackamount;
                    if (SelectedItem.stacksize <= 0) {
                        gp.player.inventory.remove(itemindex);
                    }
                    LivingEntity newitem = createnewobject(SelectedItem);
                    newitem.stacksize = stackamount;
                    gp.player.bobux += price;
                    npc.inventory.add(newitem);
                    for(int i = 0; i < npc.inventory.size(); i++){
                        if(npc.inventory.get(i) != null){
                            LivingEntity falseindex = createnewobject(npc.inventory.get(i));
                            LivingEntity trueindex = createnewobject(newitem);
                            if(falseindex.name == trueindex.name && trueindex.maxstacksize > 1){
                                npc.inventory.get(i).stacksize += newitem.stacksize;
                                npc.inventory.remove(newitem);
                                stackamount = 1;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void drawDeathScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, MainGame.screenwidth, MainGame.screenheight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110));
        text = "Game Over";
        g2.setColor(Color.BLACK);
        x = getXforCenterText(text);
        y = gp.tilesize * 4;
        g2.drawString(text, x, y);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y);
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenterText(text);
        y += gp.tilesize * 4;
        g2.drawString(text, x, y);
        if (commandnum == 0) {
            g2.drawString(">", x - 40, y);
            if (KeyHandler.enterpressed) {
                KeyHandler.enterpressed = false;
                if(GlobalGameThreadConfigs.filepath == null){
                    GlobalGameThreadConfigs.filepath = JOptionPane.showInputDialog(null, "what is the name of the save file to load?  if you exit this window it will set the save file name to null");
                }
                GlobalSaveManager.loadplayerworlddata();
                MainGame.playmusic(0);
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
            }
        }
        //--------------//
        text = "Quit";
        x = getXforCenterText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandnum == 1) {
            g2.drawString(">", x - 40, y);
            if (KeyHandler.enterpressed) {
                KeyHandler.enterpressed = false;
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                GlobalGameThreadConfigs.isinTital = true;
                MainGame.player = new Player(MainGame.keyM, gp);
                MainGame.setupOBJ();
            }
        }
    }

    public static void drawoptionscreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));
        int frameX = gp.tilesize*6;
        int frameY = gp.tilesize;
        int framewidth = gp.tilesize*8;
        int frameheight = gp.tilesize*10;
        drawSubWindow(frameX, frameY , framewidth, frameheight);
        switch (optionstate){
            case 0: option_top(frameX, frameY); break;
            case 1: key_control(frameX, frameY); break;
            case 2: EndGameConfirm(frameX, frameY); break;
        }
    }

    public static void EndGameConfirm(int frameX, int frameY) {
        int textx = frameX + gp.tilesize;
        int texty = frameY +gp.tilesize*3;
        currentDialogue = "Quit the game and return \n to title screen?";

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textx, texty);
            texty+= 40;
        }
        String text = "Yes";
        textx = getXforCenterText(text);
        texty += gp.tilesize*3;
        g2.drawString(text, textx, texty);
        if(commandnum == 0) {
            g2.drawString(">", textx - 25, texty);
            if (KeyHandler.enterpressed) {
                    optionstate = 0;
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                GlobalGameThreadConfigs.isinTital = true;
                MainGame.player = new Player(MainGame.keyM, gp);
                MainGame.setupOBJ();

            }
        }
        text = "No";
        textx = getXforCenterText(text);
        texty += gp.tilesize;
        g2.drawString(text, textx, texty);
        if(commandnum == 1){
            g2.drawString(">", textx-25, texty);
            if(KeyHandler.enterpressed){
                optionstate = 0;
                commandnum = 4;
                KeyHandler.enterpressed = false;
            }
        }

    }

    public static void option_top(int framex, int framey){
        int textX;
        int textY;
        String text = "options";
        textX = getXforCenterText(text);
        textY = framey+gp.tilesize;
        g2.drawString(text, textX, textY);

        textX = framex + gp.tilesize;
        textY += gp.tilesize*2;
        g2.drawString("size: "+fullscreentext, textX, textY);
        if(commandnum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (KeyHandler.enterpressed) {
                KeyHandler.enterpressed = false;
                if (fullscreentext.equals("Windowed")){
                fullscreentext = "Fullscreen";
                MainGame.FullscreenON = (true);
            }else if (fullscreentext.equals("Fullscreen")){

                    fullscreentext = "Windowed";
                    MainGame.FullscreenON = (false);
                }
                MainGame.togglefullscreen();
        }
        }
        textY += gp.tilesize;
        g2.drawString("Music Volume", textX, textY);
        if(commandnum == 1){
            g2.drawString(">", textX-25, textY);
        }
        textY += gp.tilesize;
        g2.drawString("Sound Volume", textX, textY);
        if(commandnum == 2){
            g2.drawString(">", textX-25, textY);
        }
        textY += gp.tilesize;
        g2.drawString("Controls", textX, textY);
        if(commandnum == 3){
            g2.drawString(">", textX-25, textY);
            if (KeyHandler.enterpressed) {
                KeyHandler.enterpressed = false;
                optionstate = 1;
                commandnum = 1;
            }
        }
        textY += gp.tilesize;
        g2.drawString("Leave Game", textX, textY);
        if(commandnum == 4){
            g2.drawString(">", textX-25, textY);
            if(KeyHandler.enterpressed){
                optionstate = 2;
                commandnum = 0;
                KeyHandler.enterpressed = false;
            }
        }
        textY += gp.tilesize;
        g2.drawString("Save Data", textX, textY);
        if(commandnum == 5){
            g2.drawString(">", textX-25, textY);
            if(KeyHandler.enterpressed){
                KeyHandler.enterpressed = false;
                if(GlobalGameThreadConfigs.filepath == null) {
                    GlobalGameThreadConfigs.filepath = JOptionPane.showInputDialog(null, "what is the name of your save file?  if you exit this window it will set the save file name to null");
                }
                GlobalSaveManager globalSaveManager = new GlobalSaveManager();
                globalSaveManager.saveplayerworlddata();
            }
        }
        textY += gp.tilesize;
        if(commandnum == 6){
            g2.drawString(">", textX-25, textY);
            if(KeyHandler.enterpressed){
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                KeyHandler.enterpressed = false;
                GlobalSaveManager.saveconfigs();
            }
        }
        g2.drawString("Exit And Save", textX, textY);

        g2.setStroke(new BasicStroke(3));
        textX = framex + gp.tilesize*5;

        textY = framey + gp.tilesize*3 + 27;
        g2.drawRect(textX, textY, 120, 24);
        int volumewidth = 24*gp.music.volumescale;
        g2.fillRect(textX, textY, volumewidth, 24);

        textY += gp.tilesize;
        g2.drawRect(textX, textY, 120, 24);
        volumewidth = 24*gp.sound.volumescale;
        g2.fillRect(textX, textY, volumewidth, 24);
    }
    public static void key_control(int framex, int framey){
        int textX;
        int textY;
        String text = "Controls";textX = getXforCenterText(text);
        g2.setFont(g2.getFont().deriveFont(15F));
        textY = framey+gp.tilesize;
        g2.drawString(text, textX, textY);
        textX = framex + 96;
        textY += 24;
        g2.drawString("move up: "+ KeyEvent.getKeyText(KeyHandler.UP), textX, textY);
        if(commandnum == 1){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("move down: "+KeyEvent.getKeyText(KeyHandler.DOWN), textX, textY);
        if(commandnum == 2){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("strafe right: "+KeyEvent.getKeyText(KeyHandler.RIGHT), textX, textY);
        if(commandnum == 3){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("strafe left: "+KeyEvent.getKeyText(KeyHandler.LEFT), textX, textY);
        if(commandnum == 4){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("PAUSE: "+KeyEvent.getKeyText(KeyHandler.PAUSE), textX, textY);
        if(commandnum == 5){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("primary power: "+KeyEvent.getKeyText(KeyHandler.primepowerc), textX, textY);
        if(commandnum == 6){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("secondary power: "+KeyEvent.getKeyText(KeyHandler.secpowerc), textX, textY);
        if(commandnum == 7){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("data menu: "+KeyEvent.getKeyText(KeyHandler.FPSC), textX, textY);
        if(commandnum == 8){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("inventory: "+KeyEvent.getKeyText(KeyHandler.INVENTORY), textX, textY);
        if(commandnum == 9){
            g2.drawString(">", textX-25, textY);
        }
        textY += 24;
        g2.drawString("open: "+KeyEvent.getKeyText(KeyHandler.OPEN), textX, textY);
        if(commandnum == 10){
            g2.drawString(">", textX-25, textY);
        }
    }

    public static void DisplayChest() {
        frameHeight = gp.tilesize * 5;
        //SLOTS
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        for (int i = 0; i < GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.size(); i++) {
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(i).first){
                g2.setColor(Color.red);
                g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
            }else if(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(i).secound){
                g2.setColor(Color.blue);
                g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
            }
            g2.drawImage(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(i).down1, slotX, slotY, null);
            g2.setColor(Color.white);
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(i).maxstacksize > 1)
              g2.drawString(""+GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(i).stacksize, slotX, slotY+10);
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
            if (itemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.size()) {
                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).stacksize < stackamount){
                    stackamount = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).stacksize;
                }
                drawSubWindow(dframeX, dframeY, dframewidth, dframeheight);
                for (String line : GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textx, texty);
                    texty += 32;
                }
            }else{
                stackamount = 1;
            }
            if(KeyHandler.use){
                KeyHandler.use = false;
                if(!merging) {
                    if (itemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.size()){
                        if (!GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).first)
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).first = true;
                    merger = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex);
                    merging = true;
                    mergerindex = itemIndex;
                }
                }else if(!mergingg) {
                    if (itemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.size()){
                        if (!GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).first) {
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).secound = true;
                            if (merger.maxstacksize > 1 && GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).maxstacksize > 1) {
                                mergingg = true;
                                mergerr = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex);
                            } else {
                                merging = false;
                                merger = null;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(itemIndex).secound = false;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(mergerindex).first = false;
                            }
                        }
                    }else{
                        merger.stacksize -= stackamount;
                        merger.first = false;
                        merging = false;
                        if(merger.stacksize <= 0){
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.remove(merger);
                        }
                        LivingEntity newentity = createnewobject(merger);
                        newentity.stacksize = stackamount;
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.add(newentity);
                        stackamount = 1;
                    }
                }else {
                    if(Objects.equals(merger.name, mergerr.name)){
                        merger.stacksize-= stackamount;
                        mergerr.stacksize += stackamount;
                        if (merger.stacksize <= 0) {
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.remove(merger);
                        }
                        mergerr.secound = false;
                        merger.first = false;
                        mergerr = null;
                        merging = false;
                        mergingg = false;
                        stackamount = 1;
                    }else{
                        merger.first = false;
                        mergerr.secound = false;
                    }
                }
            }

        }
    }

    public static void displayINV(LivingEntity entity, boolean npc, boolean cursor) {
        //FRAME
        if (GlobalGameThreadConfigs.CharacterStats || transitionfinushed){
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
            if(gp.player.inventory.get(i).first){
                g2.setColor(Color.red);
                g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
            }else if(gp.player.inventory.get(i).secound){
                g2.setColor(Color.blue);
                g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            g2.setColor(Color.white);
            if(gp.player.inventory.get(i).maxstacksize > 1)
              g2.drawString(""+gp.player.inventory.get(i).stacksize, slotX, slotY+10);
            slotX += gp.tilesize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gp.tilesize;
            }
        }
        if (!slotstate || !GlobalGameThreadConfigs.inchest) {
            if (!cursor) {
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
                togglestack(itemIndex);
                if (itemIndex < gp.player.inventory.size()) {
                    if(gp.player.inventory.get(itemIndex).stacksize < stackamount){
                        stackamount = gp.player.inventory.get(itemIndex).stacksize;
                    }
                    drawSubWindow(dframeX, dframeY, dframewidth, dframeheight);
                    for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                        g2.drawString(line, textx, texty);
                        texty += 32;
                    }
                }else{
                    stackamount = 1;
                }
                if(KeyHandler.use){
                    KeyHandler.use = false;
                    if(!merging){
                        if(itemIndex < gp.player.inventory.size()) {
                            if (!gp.player.inventory.get(itemIndex).first)
                                gp.player.inventory.get(itemIndex).first = true;
                            merger = gp.player.inventory.get(itemIndex);
                            merging = true;
                            mergerindex = itemIndex;
                        }
                    }else if(!mergingg) {
                        if (itemIndex < gp.player.inventory.size()){
                            if (!gp.player.inventory.get(itemIndex).first) {
                                gp.player.inventory.get(itemIndex).secound = true;
                                if (merger.maxstacksize > 1 && gp.player.inventory.get(itemIndex).maxstacksize > 1) {
                                    mergingg = true;
                                    mergerr = gp.player.inventory.get(itemIndex);
                                } else {
                                    merging = false;
                                    merger = null;
                                    gp.player.inventory.get(itemIndex).secound = false;
                                    gp.player.inventory.get(mergerindex).first = false;
                                }
                            }
                    }else{
                            merger.stacksize -= stackamount;
                            merger.first = false;
                            merging = false;
                            if(merger.stacksize <= 0){
                                gp.player.inventory.remove(merger);
                            }
                            LivingEntity newentity = createnewobject(merger);
                            newentity.stacksize = stackamount;
                            gp.player.inventory.add(newentity);
                            stackamount = 1;
                        }
                    }else if(mergerr != null){
                        if(Objects.equals(merger.name, mergerr.name)){
                            merger.stacksize-= stackamount;
                            mergerr.stacksize += stackamount;
                                if (merger.stacksize <= 0) {
                                    gp.player.inventory.remove(merger);
                                }
                            mergerr.secound = false;
                            merger.first = false;
                            mergerr = null;
                            merging = false;
                            mergingg = false;
                            stackamount = 1;
                        }else{
                            merger.first = false;
                            mergerr.secound = false;
                        }
                    }else{
                        merger.first = false;
                        mergerr = null;
                        merging = false;
                        mergingg = false;
                        stackamount = 1;
                    }
                }
            }
        }
    }else{
            int framex = gp.tilesize*12;
            int frameWidth = gp.tilesize * 6;
            int frameHeight = gp.tilesize * 5;
            drawSubWindow(framex, frameY, frameWidth, frameHeight);
            //SLOTS
            final int slotXstart = framex + 20;
            final int slotYstart = frameY + 20;
            int slotX = slotXstart;
            int slotY = slotYstart;
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                if (gp.player.inventory.get(i) == gp.player.currentweapon || gp.player.inventory.get(i) == gp.player.currentshield) {
                    g2.setColor(new Color(0xFFFF00));
                    g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
                }
                if(gp.player.inventory.get(i).first){
                    g2.setColor(Color.red);
                    g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
                }else if(gp.player.inventory.get(i).secound){
                    g2.setColor(Color.blue);
                    g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
                }
                g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
                g2.setColor(Color.white);
                g2.drawString(""+gp.player.inventory.get(i).stacksize, slotX, slotY);
                slotX += gp.tilesize;
                if (i == 4 || i == 9 || i == 14) {
                    slotX = slotXstart;
                    slotY += gp.tilesize;
                }
            }
            if (!slotstate || !GlobalGameThreadConfigs.inchest) {
                if (!cursor) {
                    int cursorX = slotXstart + (gp.tilesize * SlotCol);
                    int cursorY = slotYstart + (gp.tilesize * slotRow);
                    int cursorwidth = gp.tilesize;
                    int cursorheight = gp.tilesize;
                    g2.setStroke(new BasicStroke(3));
                    g2.setColor(Color.white);
                    g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                    int dframeY = frameY + +frameHeight;
                    int dframewidth = frameWidth * 2 - ((gp.tilesize * 4));
                    int dframeheight = gp.tilesize * 3;
                    framex -= gp.tilesize;
                    int textx = (framex+ 20);
                    int texty = dframeY + gp.tilesize;
                    int itemIndex = getItemIndex();
                    togglestack(itemIndex);
                    if (itemIndex < gp.player.inventory.size()) {
                        if(gp.player.inventory.get(itemIndex).stacksize < stackamount){
                            stackamount = gp.player.inventory.get(itemIndex).stacksize;
                        }
                        drawSubWindow(framex, dframeY, dframewidth, dframeheight);
                        for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                            g2.drawString(line, textx, texty);
                            texty += 32;
                        }
                    }else{
                        stackamount = 1;
                    }
                    if(KeyHandler.use){
                        KeyHandler.use = false;
                        if(!merging){
                            if(itemIndex < gp.player.inventory.size()) {
                                if (!gp.player.inventory.get(itemIndex).first)
                                    gp.player.inventory.get(itemIndex).first = true;
                                merger = gp.player.inventory.get(itemIndex);
                                merging = true;
                                mergerindex = itemIndex;
                            }
                        }else if(!mergingg) {
                            if (itemIndex < gp.player.inventory.size()){
                                if (!gp.player.inventory.get(itemIndex).first) {
                                    gp.player.inventory.get(itemIndex).secound = true;
                                    if (merger.maxstacksize > 1 && gp.player.inventory.get(itemIndex).maxstacksize > 1) {
                                        mergingg = true;
                                        mergerr = gp.player.inventory.get(itemIndex);
                                    } else {
                                        merging = false;
                                        merger = null;
                                        gp.player.inventory.get(itemIndex).secound = false;
                                        gp.player.inventory.get(mergerindex).first = false;
                                    }
                                }
                            }else{
                                merger.stacksize -= stackamount;
                                merger.first = false;
                                merging = false;
                                if(merger.stacksize <= 0){
                                    gp.player.inventory.remove(merger);
                                }
                                LivingEntity newentity = createnewobject(merger);
                                newentity.stacksize = stackamount;
                                gp.player.inventory.add(newentity);
                                stackamount = 1;
                            }
                        }else {
                            if(Objects.equals(merger.name, mergerr.name)){
                                merger.stacksize-= stackamount;
                                mergerr.stacksize += stackamount;
                                if (merger.stacksize <= 0) {
                                    gp.player.inventory.remove(merger);
                                }
                                mergerr.secound = false;
                                merger.first = false;
                                mergerr = null;
                                merging = false;
                                mergingg = false;
                                stackamount = 1;
                            }else{
                                merger.first = false;
                                mergerr.secound = false;
                            }
                        }
                    }
                }
            }
        }
        if(npc) {
            int frameX = gp.tilesize * 2;
            int frameY = gp.tilesize;
            int framewidth = gp.tilesize * 6;
            int frameheight = gp.tilesize * 5;
            int slotcol = npcslotcol;
            int slotrow = npcslotrow;
            drawSubWindow(frameX, frameY, framewidth, frameheight);
            //SLOTS
            final int slotxstart = frameX + 20;
            final int slotystart = frameY + 20;
            int slotx = slotxstart;
            int sloty = slotystart;
            for (int i = 0; i < entity.inventory.size(); i++) {
                g2.drawImage(entity.inventory.get(i).down1, slotx, sloty, null);
                g2.setColor(Color.white);
                if(entity.inventory.get(i).maxstacksize > 1)
                  g2.drawString(""+entity.inventory.get(i).stacksize, slotx, sloty+10);
                slotx += gp.tilesize;
                if (i == 4 || i == 9 || i == 14) {
                    slotx = slotxstart;
                    sloty += gp.tilesize;
                }
            }
            if (cursor) {
                int cursorX = slotxstart + (gp.tilesize * slotcol);
                int cursorY = slotystart + (gp.tilesize * slotrow);
                int cursorwidth = gp.tilesize;
                int cursorheight = gp.tilesize;
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.white);
                g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                int dframeY = frameY +frameheight;
                int dframewidth = frameWidth * 2 - ((gp.tilesize * 2) + 24);
                int dframeheight = gp.tilesize * 3;
                int textx = (frameX + 20);
                int texty = dframeY + gp.tilesize;
                int itemIndex = getnpcItemIndex();
                togglenpcstack(itemIndex, entity);
                if (itemIndex < entity.inventory.size()) {
                    if(entity.inventory.get(itemIndex).stacksize < stackamount){
                        stackamount = entity.inventory.get(itemIndex).stacksize;
                    }
                    drawSubWindow(frameX, dframeY, dframewidth, dframeheight);
                    for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                        g2.drawString(line, textx, texty);
                        texty += 32;
                    }
                }else{
                    stackamount = 1;
                }
            }
        }
    }
    public static void displayUI(LivingEntity entity, boolean cursor) {
            int framex = gp.tilesize*12;
            int frameWidth = gp.tilesize * 6;
            int frameHeight = gp.tilesize * 5;
            drawSubWindow(framex, frameY, frameWidth, frameHeight);
            //SLOTS
            final int slotXstart = framex + 20;
            final int slotYstart = frameY + 20;
            int slotX = slotXstart;
            int slotY = slotYstart;
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                if (gp.player.inventory.get(i) == gp.player.currentweapon || gp.player.inventory.get(i) == gp.player.currentshield) {
                    g2.setColor(new Color(0xFFFF00));
                    g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
                }
                if(gp.player.inventory.get(i).first){
                    g2.setColor(Color.red);
                    g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
                }else if(gp.player.inventory.get(i).secound){
                    g2.setColor(Color.blue);
                    g2.fillRoundRect(slotX, slotY, gp.tilesize, gp.tilesize, 10, 10);
                }
                g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
                g2.setColor(Color.white);
                g2.drawString(""+gp.player.inventory.get(i).stacksize, slotX, slotY);
                slotX += gp.tilesize;
                if (i == 4 || i == 9 || i == 14) {
                    slotX = slotXstart;
                    slotY += gp.tilesize;
                }
            }
            if (!slotstate || !GlobalGameThreadConfigs.inchest) {
                if (!cursor) {
                    int cursorX = slotXstart + (gp.tilesize * SlotCol);
                    int cursorY = slotYstart + (gp.tilesize * slotRow);
                    int cursorwidth = gp.tilesize;
                    int cursorheight = gp.tilesize;
                    g2.setStroke(new BasicStroke(3));
                    g2.setColor(Color.white);
                    g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                    int dframeY = frameY + +frameHeight;
                    int dframewidth = frameWidth * 2 - ((gp.tilesize * 4));
                    int dframeheight = gp.tilesize * 3;
                    framex -= gp.tilesize;
                    int textx = (framex+ 20);
                    int texty = dframeY + gp.tilesize;
                    int itemIndex = getItemIndex();
                    togglestack(itemIndex);
                    if (itemIndex < gp.player.inventory.size()) {
                        if(gp.player.inventory.get(itemIndex).stacksize < stackamount){
                            stackamount = gp.player.inventory.get(itemIndex).stacksize;
                        }
                        drawSubWindow(framex, dframeY, dframewidth, dframeheight);
                        for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                            g2.drawString(line, textx, texty);
                            texty += 32;
                        }
                    }else{
                        stackamount = 1;
                    }
                    if(KeyHandler.use){
                        KeyHandler.use = false;
                        if(!merging){
                            if(itemIndex < gp.player.inventory.size()) {
                                if (!gp.player.inventory.get(itemIndex).first)
                                    gp.player.inventory.get(itemIndex).first = true;
                                merger = gp.player.inventory.get(itemIndex);
                                merging = true;
                                mergerindex = itemIndex;
                            }
                        }else if(!mergingg) {
                            if (itemIndex < gp.player.inventory.size()){
                                if (!gp.player.inventory.get(itemIndex).first) {
                                    gp.player.inventory.get(itemIndex).secound = true;
                                    if (merger.maxstacksize > 1 && gp.player.inventory.get(itemIndex).maxstacksize > 1) {
                                        mergingg = true;
                                        mergerr = gp.player.inventory.get(itemIndex);
                                    } else {
                                        merging = false;
                                        merger = null;
                                        gp.player.inventory.get(itemIndex).secound = false;
                                        gp.player.inventory.get(mergerindex).first = false;
                                    }
                                }
                            }else{
                                merger.stacksize -= stackamount;
                                merger.first = false;
                                merging = false;
                                if(merger.stacksize <= 0){
                                    gp.player.inventory.remove(merger);
                                }
                                LivingEntity newentity = createnewobject(merger);
                                newentity.stacksize = stackamount;
                                gp.player.inventory.add(newentity);
                                stackamount = 1;
                            }
                        }else {
                            if(Objects.equals(merger.name, mergerr.name)){
                                merger.stacksize-= stackamount;
                                mergerr.stacksize += stackamount;
                                if (merger.stacksize <= 0) {
                                    gp.player.inventory.remove(merger);
                                }
                                mergerr.secound = false;
                                merger.first = false;
                                mergerr = null;
                                merging = false;
                                mergingg = false;
                                stackamount = 1;
                            }else{
                                merger.first = false;
                                mergerr.secound = false;
                            }
                        }
                    }
                }
            }
            int frameX = gp.tilesize * 2;
            int frameY = gp.tilesize;
            int framewidth = gp.tilesize * 6;
            int frameheight = gp.tilesize * 6;
            int slotcol = npcslotcol;
            int slotrow = npcslotrow;
            drawSubWindow(frameX, frameY, framewidth, frameheight);
            //SLOTS
            final int slotxstart = frameX + 30;
            final int slotystart = frameY + 30;
            int slotx = slotxstart;
            int sloty = slotystart;
            for (int i = 0; i < entity.inventory.size(); i++) {
                if(entity.inventory.get(i) != null) {
                    switch (i) {
                        case 1 -> slotx = slotxstart + gp.tilesize;
                        case 2 -> slotx = slotxstart + (gp.tilesize * 2);
                        case 3 -> {
                            slotx = slotxstart;
                            sloty = slotystart+gp.tilesize;}
                        case 4 -> {
                            slotx = slotxstart + (gp.tilesize);
                            sloty = slotystart+gp.tilesize;
                        }
                        case 5 -> {
                            slotx = slotxstart + (gp.tilesize*2);
                            sloty = slotystart + (gp.tilesize);
                        }
                        case 6 -> {
                            slotx = slotxstart;
                            sloty = slotystart  + (gp.tilesize * 2);
                        }
                        case 7 -> {
                            slotx = slotxstart + gp.tilesize;
                            sloty = (slotystart + gp.tilesize * 2);
                        }
                        case 8 -> {
                            slotx = slotxstart + (gp.tilesize*2);
                            sloty = (slotystart + gp.tilesize * 2);
                        }
                    }
                    g2.drawImage(entity.inventory.get(i).down1, slotx, sloty, null);
                    g2.setColor(Color.white);
                    if (entity.inventory.get(i).maxstacksize > 1)
                        g2.drawString("" + entity.inventory.get(i).stacksize, slotx, sloty + 10);
                }
            }
            if (cursor) {
                int cursorX = slotxstart + (gp.tilesize * slotcol);
                int cursorY = slotystart + (gp.tilesize * slotrow);
                int cursorwidth = gp.tilesize;
                int cursorheight = gp.tilesize;
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.white);
                g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                int dframeY = frameY +frameheight;
                int dframewidth = frameWidth * 2 - ((gp.tilesize * 2) + 24);
                int dframeheight = gp.tilesize * 3;
                int textx = (frameX + 20);
                int texty = dframeY + gp.tilesize;
                int itemIndex = getnpcItemIndex();
                togglenpcstack(itemIndex, entity);
                if (itemIndex < entity.inventory.size()) {
                    if(entity.inventory.get(itemIndex) != null){
                        if(entity.inventory.get(itemIndex).stacksize < stackamount){
                            stackamount = entity.inventory.get(itemIndex).stacksize;
                        }
                    drawSubWindow(frameX, dframeY, dframewidth, dframeheight);
                    for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                        g2.drawString(line, textx, texty);
                        texty += 32;
                    }
                }else{
                        stackamount = 1;
                    }
        }else{
                    stackamount = 1;
                }
            }
    }


    public static int getItemIndex() {
        int itemindex = SlotCol + (slotRow * 5);
        return itemindex;
    }
    public static int getnpcItemIndex(){
        int itemindex = npcslotcol + (npcslotrow*5);
        return itemindex;
    }
    public static void togglestack(int ItemIndex){if(KeyHandler.sprint){
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.drawString(""+stackamount,getXforCenterText(""+stackamount), gp.tilesize*5);
        if(right) {
            if (!slotstate) {
                if (ItemIndex < gp.player.inventory.size()) {
                    if (stackamount < gp.player.inventory.get(ItemIndex).stacksize)
                        stackamount++;
                }
            }else {
                if (gp.player.objindex != 999){
                    if (ItemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.size()) {
                        if (stackamount < GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(ItemIndex).stacksize) {
                            stackamount++;
                        }
                    }
            }
            }
        }
        if(left){
            if(stackamount > 1)
                stackamount--;
        }
    }}
    public static void togglenpcstack(int ItemIndex, LivingEntity entity){if(KeyHandler.sprint){
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.drawString(""+stackamount,getXforCenterText(""+stackamount), gp.tilesize*5);
        if(right) {
                if (ItemIndex < entity.inventory.size()) {
                    if (stackamount < entity.inventory.get(ItemIndex).stacksize)
                        stackamount++;
                }

        }
        if(left){
            if(stackamount > 1)
                stackamount--;
        }
    }}

    public static void selectItem() {
        int ItemIndex = getItemIndex();
        togglestack(ItemIndex);
        if (KeyHandler.moveitem) {
            KeyHandler.moveitem = false;
            if (!slotstate) {
                if (ItemIndex < gp.player.inventory.size()) {
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.size() != GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventorysize) {
                        if (!KeyHandler.CROUCH) {
                            LivingEntity SelectedItem = gp.player.inventory.get(ItemIndex);
                            gp.player.inventory.get(ItemIndex).stacksize -= stackamount;
                            if (SelectedItem.stacksize <= 0) {
                                gp.player.inventory.remove(ItemIndex);
                            }
                            LivingEntity newItem = createnewobject(SelectedItem);
                            newItem.stacksize = stackamount;
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.add(newItem);

                            if (SelectedItem == gp.player.currentshield) {
                                gp.player.currentshield = null;
                                gp.player.getDefenceValues();
                            } else if (SelectedItem == gp.player.currentweapon) {
                                gp.player.currentweapon = null;
                                gp.player.getAttackValues();
                            }
                            stackamount = 1;
                        }
                        else {
                            LivingEntity SelectedItem = gp.player.inventory.get(ItemIndex);
                           gp.player.inventory.remove(ItemIndex);
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.add(SelectedItem);
                            if (SelectedItem == gp.player.currentshield) {
                                gp.player.currentshield = null;
                                gp.player.getDefenceValues();
                            } else if (SelectedItem == gp.player.currentweapon) {
                                gp.player.currentweapon = null;
                                gp.player.getAttackValues();
                            }
                        }
                    }
                }
            } else {
                if (ItemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.size()) {
                    if (gp.player.inventory.size() != gp.player.inventorysize) {
                        if (!KeyHandler.CROUCH) {
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(ItemIndex).stacksize-= stackamount;
                            LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(ItemIndex);
                            if (SelectedItem.stacksize <= 0) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.remove(ItemIndex);
                            }
                            LivingEntity newItem = createnewobject(SelectedItem);
                            gp.player.inventory.add(newItem);
                            gp.player.inventory.get(gp.player.inventory.size() - 1).stacksize = stackamount;
                            stackamount = 1;
                        }else{
                            LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.get(ItemIndex);
                             GlobalGameThreadConfigs.obj[MainGame.currentmap][gp.player.objindex].inventory.remove(ItemIndex);
                            gp.player.inventory.add(SelectedItem);
                        }
                    }
                }
            }
        }
    }
    public static LivingEntity createnewobject(LivingEntity old){
        LivingEntity newentity = new LivingEntity(gp);
        switch (old.name){
            case "chest" -> newentity = new chest(gp);
            case "furnace" -> newentity = new furnace(gp, 0, 0);
            case "BRIC WALL" -> newentity = new OBJ_BRICK_WALL(gp);
            case "Bronze coin" -> newentity = new OBJ_COIN_BRONZE(gp);
            case "WoodCutter's axe"-> newentity = new OBJ_IRON_AXE(gp);
            case "Iron shovel"-> newentity = new OBJ_IRON_SHOVEL(gp);
            case "Iron sword"-> newentity = new OBJ_IRON_SWORD(gp);
            case "Mana Crystal"-> newentity = new OBJ_MANA_CRYSTAL(gp);
            case "Red Potion" -> newentity = new OBJ_POTION_HEALTH_1(gp);
            case "Diamond Shield"-> newentity = new OBJ_SHIELD_DIAMOND(gp);
            case "Wooden Shield" -> newentity = new OBJ_SHIELD_WOOD(gp);
            case "boots" -> newentity = new OBJboots(gp);
            case "door" -> newentity = new OBJdoor(gp, 0, 0);
            case "door open" -> newentity = new OBJdooropen(gp, 0, 0);
            case "heart"-> newentity = new OBJHeart(gp);
            case "key"-> newentity = new OBJkey(gp);
            case "crafting table" -> newentity = new crafting_table(gp, 0, 0);
            case "Brick" -> newentity = new Brick(gp);
            case "Clay" -> newentity = new Clay(gp);
            case "Basic Coal" -> newentity = new OBJ_coal(gp);
        }
        newentity.inventory = old.inventory;
        return newentity;
    }

    public static void drawMessages() {

        int Messagex = gp.tilesize - 30;
        int Messagey = gp.tilesize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i) != null) {
                if(!KeyHandler.checkFPS){
                    for(String line: messages.get(i).split("\n")){
                        g2.setColor(Color.BLACK);
                        g2.drawString(line, Messagex + 2, Messagey);
                        g2.setColor(Color.white);
                        g2.drawString(line, Messagex, Messagey);
                        Messagey+= 40;
                    }
                int ID = messageID.get(i) + 1;
                messageID.set(i, ID);
                Messagey += 50;
            }
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
        if (!GlobalGameThreadConfigs.inchest) {
            int textX = frameX + 20;
            int textY = frameY + gp.tilesize;
            final int lineHeight = 30;
            g2.drawString("level: " + gp.player.level, textX, textY);
            textY += lineHeight;
            g2.drawString("health: " + gp.player.health + "/" + gp.player.maxhealth, textX, textY);
            textY += lineHeight;
            g2.drawString("Mana: " + gp.player.Mana + "/" + gp.player.MaxMana, textX, textY);
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
            if (gp.player.currentweapon != null){
                g2.drawString("weapon: " + gp.player.currentweapon.name, textX, textY);
            textY += lineHeight;
        }else{
                g2.drawString("weapon: " + "none", textX, textY);
                textY += lineHeight;
            }
            if(gp.player.currentshield != null) {
                g2.drawString("shield: " + gp.player.currentshield.name, textX, textY);
            }else{
                g2.drawString("shield: " + "none", textX, textY);
            }
        textY += lineHeight;
    }

}
    public static void addMessages(String text){
        messages.add(text);
        messageID.add(0);
    }
    public static void drawPlayerBar() {
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
        g2.setColor(Color.white);
        if (gp.keyM.checkFPS) {
            if (gp.player.invincible == true) {
                g2.drawString("" + gp.player.hitTime, 10, 160);
            }
        }
        x = (gp.tilesize / 2)-5;
        y = (int) (gp.tilesize*1.5);
        i = 0;
        while (i < gp.player.MaxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }
        x = (gp.tilesize / 2)-5;
        y = (int) (gp.tilesize*1.5);
        i = 0;
        while (i < gp.player.Mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }
    public static void drawTransition(){
        Tcount++;
        g2.setColor(new Color(0, 0, 0, Tcount*5));
        g2.fillRect(0, 0, MainGame.screenwidth, MainGame.screenheight);
        if(Tcount >= 50){
            Tcount = 0;
            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
            MainGame.currentmap = gp.ehandler.tempmap;
            gp.player.worldx = gp.tilesize*gp.ehandler.tempcol;
            gp.player.worldy = gp.tilesize*gp.ehandler.temprow;
            gp.ehandler.PreviousEventX = (int) gp.player.worldx;
            gp.ehandler.PreviousEventY = (int) gp.player.worldy;
        }

    }
    //2047 bytes long -------------------//
     public static void drawTitleScreen() {try {g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));String text = "Quest To Find Who Asked";int x = getXforCenterText(text);int y = gp.tilesize * 2;g2.setColor(Color.black);g2.drawString(text, x+5, y+5);g2.setColor(Color.white);g2.drawString(text, x, y);g2.setColor(Color.black);g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));text = "START NEW GAME";x = getXforCenterText(text);y += MainGame.tilesize * 3;if(commandnum == 0){g2.drawString(">", x-MainGame.tilesize, y);}g2.drawString(text, x, y);g2.setColor(Color.white);text = "CONTINUE FROM SAVE FILE";x = getXforCenterText(text);y += MainGame.tilesize * 2;if(commandnum == 1){g2.drawString(">", x-MainGame.tilesize, y);}g2.drawString(text, x, y);g2.setColor(Color.red);text = "QUIT GAME";x = getXforCenterText(text);y += MainGame.tilesize * 2;if(commandnum == 2){g2.drawString(">", x-MainGame.tilesize, y);}g2.drawString(text, x, y);}catch (Exception e){e.printStackTrace();}}public static void drawDialogueScreen() {int x = gp.tilesize * 2;int y = gp.tilesize/2;int width = MainGame.screenwidth - (gp.tilesize*4);int height = gp.tilesize*4;drawSubWindow(x, y, width, height);x += gp.tilesize;y += gp.tilesize;g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));if(currentDialogue != null)for(String line : currentDialogue.split("\n")){g2.drawString(line, x, y);y += 40;}}public static void drawSubWindow(int x, int y, int width, int height){Color c = new Color(0, 0, 0, 210);g2.setColor(c);g2.fillRoundRect(x, y, width, height, 35, 35);c = new Color(255, 255, 255);g2.setColor(c);g2.setStroke(new BasicStroke(5));g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);}public static void drawPauseScreen(){g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));String text = "Paused";int x;x = getXforCenterText(text);int y = MainGame.screenheight / 2;g2.drawString(text, x, y);}public static int getXforCenterText(String text){int Length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();int x = MainGame.screenwidth/2 - Length/2;return x;}}