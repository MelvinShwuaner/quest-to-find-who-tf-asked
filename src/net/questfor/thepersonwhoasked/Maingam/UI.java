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
    public static boolean merging = false;
    static boolean mergingg = false;
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
    public static int frameX = GlobalGameThreadConfigs.tilesize - 350;
    public static int invX = (GlobalGameThreadConfigs.tilesize * 13) + 350;
    public static String fullscreentext = "Windowed";
    public static int dframeX = invX, frameY = GlobalGameThreadConfigs.tilesize;
    public static boolean transitionfinushed = false;
    public static int SlotCol = 0, slotRow = 0;
    public static int npcslotcol = 0, npcslotrow = 0;
    public static int commandnum = 0;
    public static int frameWidth = GlobalGameThreadConfigs.tilesize * 6;
    public static int frameHeight = GlobalGameThreadConfigs.tilesize * 10;
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
                displayAnimationOnScreen();
                if (frameX != GlobalGameThreadConfigs.tilesize - 350) {
                    displaySTATS();
                    displayINV(GlobalGameThreadConfigs.player, false, false);
                }
                if (GlobalGameThreadConfigs.inchest) {
                    if(Objects.equals(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].name, "chest"))
                      DisplayChest();
                } else {
                    frameHeight = GlobalGameThreadConfigs.tilesize * 10;
                }
                if (!GlobalGameThreadConfigs.CharacterStats) {
                    drawMessages();
                }
                if (GlobalGameThreadConfigs.CharacterStats) {
                    if (!transitionfinushed) {
                        frameX += 5;
                        invX -= 5;
                        dframeX = invX - (GlobalGameThreadConfigs.tilesize * 3) - 24;
                    }
                    if (frameX == GlobalGameThreadConfigs.tilesize - 45) {
                        transitionfinushed = true;
                    }
                } else {
                    if (frameX != GlobalGameThreadConfigs.tilesize - 350) {
                        frameX -= 5;
                        invX += 5;
                        dframeX = invX - (GlobalGameThreadConfigs.tilesize * 3) - 24;
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
        int x = GlobalGameThreadConfigs.tilesize * 2;
        int y = GlobalGameThreadConfigs.tilesize * 9;
        int width = GlobalGameThreadConfigs.tilesize * 6;
        int height = GlobalGameThreadConfigs.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] back", x + 24, y + 60);
    }
    public static void DisplayTable() {
        displayUI(npc, false);
        if(code != 999){
            int ItemIndex = getItemIndex();
            if (ItemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(code) == null){
                    LivingEntity SelectedItem = GlobalGameThreadConfigs.player.inventory.get(ItemIndex);
                    if(stackamount > SelectedItem.stacksize){
                        stackamount = SelectedItem.stacksize;
                    }
                SelectedItem.stacksize -= stackamount;
                if (SelectedItem.stacksize <= 0) {
                    GlobalGameThreadConfigs.player.inventory.remove(SelectedItem);
                }
                LivingEntity newentity = createnewobject(SelectedItem);
                newentity.stacksize = stackamount;
                GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.set(code, newentity);
            }
            }else{
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(code) != null) {
                    LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(code);
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.set(code, null);

                    LivingEntity newentity = createnewobject(SelectedItem);
                    newentity.stacksize = SelectedItem.stacksize;
                    GlobalGameThreadConfigs.player.inventory.add(newentity);
                }
            }
            code = 999;
        }
        if(KeyHandler.enterpressed){
            for(int i = 0; i < GlobalGameThreadConfigs.Recipes.length; i++) {
                if (GlobalGameThreadConfigs.Recipes[i] != null) {
                    for (int d = 0; d < 9; d++){
                        if (GlobalGameThreadConfigs.Recipes[i].Recipe[d] != null) {
                            if((GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(d) != null)){
                            if (Objects.equals(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(d).name, GlobalGameThreadConfigs.Recipes[i].Recipe[d].name)) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[d] = true;
                            }
                            }
                        }else {
                            if ((GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(d) == null)) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[d] = true;
                            }
                        }
                        if(d == 8){
                            boolean cancraft = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[0] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[1] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[2] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[3] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[4] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[5] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[6] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[7] && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[8];
                            if(cancraft) {
                                LivingEntity SelectedItem = createnewobject(GlobalGameThreadConfigs.Recipes[i].Result);
                                GlobalGameThreadConfigs.player.inventory.add(SelectedItem);
                                for (int a = 0; a < 9; a++){
                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(a) != null) {
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(a).stacksize--;
                                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(a).stacksize <= 0) {
                                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.set(a, null);
                                        }
                                    }
                                }
                                i = GlobalGameThreadConfigs.Recipes.length;
                            }
                            for(int c = 0; c < 9; c++) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].slot[c] = false;
                            }
                        }
                }
            }
            }
            KeyHandler.enterpressed = false;
        }
    }
    public static void displayAnimationOnScreen(){
        if(GlobalGameThreadConfigs.player.burning){
            GlobalGameThreadConfigs.animationcounter++;
            if (GlobalGameThreadConfigs.animationcounter >= 5) {
                GlobalGameThreadConfigs.animationcounter = 0;
                GlobalGameThreadConfigs.animationnumber++;
                if (GlobalGameThreadConfigs.animationnumber == 4) {
                    GlobalGameThreadConfigs.animationnumber = 0;
                }
            }
            g2.drawImage(gp.tilemanager.firescreen[GlobalGameThreadConfigs.animationnumber], (int) 0, (int) 0, null);
        }
    }
    public static void displayAnimation(){}

    public static void DisplayFurnace() {
        displayUI(npc, false);
        if (code != 999) {
            int ItemIndex = getItemIndex();
            if (ItemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(code) == null) {
                    LivingEntity SelectedItem = GlobalGameThreadConfigs.player.inventory.get(ItemIndex);
                    if (code == 0) {
                        if (SelectedItem.smeltable) {
                            if(stackamount > SelectedItem.stacksize){
                                stackamount = SelectedItem.stacksize;
                            }
                            SelectedItem.stacksize -= stackamount;
                            if (SelectedItem.stacksize <= 0) {
                                GlobalGameThreadConfigs.player.inventory.remove(SelectedItem);
                            }
                            LivingEntity newentity = createnewobject(SelectedItem);
                            newentity.stacksize = stackamount;
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.set(code, newentity);
                        }
                    } else if (code == 6) {
                        if (SelectedItem.fuel) {
                            if(stackamount > SelectedItem.stacksize){
                                stackamount = SelectedItem.stacksize;
                            }
                            SelectedItem.stacksize -= stackamount;
                            if (SelectedItem.stacksize <= 0) {
                                GlobalGameThreadConfigs.player.inventory.remove(SelectedItem);
                            }
                            LivingEntity newentity = createnewobject(SelectedItem);
                            newentity.stacksize = stackamount;
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.set(code, newentity);
                        }
                    }

                }
            } else {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(code) != null) {
                    LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(code);
                    if(stackamount > SelectedItem.stacksize){
                        stackamount = SelectedItem.stacksize;
                    }
                    SelectedItem.stacksize -= stackamount;
                    if (SelectedItem.stacksize <= 0) {
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.remove(SelectedItem);
                    }
                    LivingEntity newentity = createnewobject(SelectedItem);
                    newentity.stacksize = stackamount;
                    GlobalGameThreadConfigs.player.inventory.add(newentity);
                }
            }
            code = 999;
        }

        int x = GlobalGameThreadConfigs.tilesize * 12;
        int y = GlobalGameThreadConfigs.tilesize * 9;
        int width = GlobalGameThreadConfigs.tilesize * 6;
        int height = GlobalGameThreadConfigs.tilesize * 2;
        drawSubWindow(x, y, width, height);

        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].smelting){
        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].cool > 1 && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].cool <= GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].maxcool) {
            double onescale = 200 / GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].maxcool;
            double HPValue = onescale * GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].cool;
            g2.setColor(Color.white);
            g2.fillRect((int) x+20, (int) y + 20, (int) HPValue, 30);
        }
    }
        if (KeyHandler.enterpressed) {
            KeyHandler.enterpressed = false;
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(0) != null && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(6) != null)
              GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].smelting = true;
            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].hasfinushedcol == 2) {
                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(5) != null) {
                    LivingEntity SelectedItem = createnewobject(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(5));
                    SelectedItem.stacksize = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(5).stacksize;
                    GlobalGameThreadConfigs.player.inventory.add(SelectedItem);
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.set(5, null);
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
        int x = GlobalGameThreadConfigs.tilesize*15;
        int y = (int) (GlobalGameThreadConfigs.tilesize*4.5);
        int width = GlobalGameThreadConfigs.tilesize*3;
        int height = (int) (GlobalGameThreadConfigs.tilesize*3.5);
        drawSubWindow(x, y, width, height);
        x+=GlobalGameThreadConfigs.tilesize;
        y+=GlobalGameThreadConfigs.tilesize;
        g2.drawString("Buy", x, y);
        if(commandnum == 0){
            g2.drawString(">", x-24, y);
            if(KeyHandler.enterpressed){
                tradestate = 1;
                KeyHandler.enterpressed = false;
            }
        }
        y+=GlobalGameThreadConfigs.tilesize;
        g2.drawString("Sell", x, y);
        if(commandnum == 1){
            g2.drawString(">", x-24, y);
            if(KeyHandler.enterpressed){
                tradestate = 2;
                KeyHandler.enterpressed = false;
            }
        }
        y+=GlobalGameThreadConfigs.tilesize;
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
        int x = GlobalGameThreadConfigs.tilesize * 2;
        int y = GlobalGameThreadConfigs.tilesize * 9;
        int width = GlobalGameThreadConfigs.tilesize * 6;
        int height = GlobalGameThreadConfigs.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] back", x + 24, y + 60);
        x = GlobalGameThreadConfigs.tilesize * 12;
        y = GlobalGameThreadConfigs.tilesize * 9;
        width = GlobalGameThreadConfigs.tilesize * 6;
        height = GlobalGameThreadConfigs.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(25f));
        g2.drawString("your bobux: " + GlobalGameThreadConfigs.player.bobux, x + 24, y + 60);
        int itemindex = getnpcItemIndex();
        if (itemindex < npc.inventory.size()) {
            x = (int) (GlobalGameThreadConfigs.tilesize * 5.5);
            y = (int) (GlobalGameThreadConfigs.tilesize * 5.5);
            width = (int) (GlobalGameThreadConfigs.tilesize * 2.5);
            height = GlobalGameThreadConfigs.tilesize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(bobux, x + 10, y + 8, 32, 32, null);
            int stack= stackamount;
            if(stack > npc.inventory.get(itemindex).stacksize){
                stack = npc.inventory.get(itemindex).stacksize;
            }
            int price = (npc.inventory.get(itemindex).Value * 2)*stack;
            String text = "" + price;
            g2.setFont(g2.getFont().deriveFont(15f));
            x += 42;
            g2.drawString(text, x, y + 30);
            if (KeyHandler.enterpressed) {
                if ((npc.inventory.get(itemindex).Value*2)*stackamount > GlobalGameThreadConfigs.player.bobux) {
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
                    currentDialogue = "You need more bobux to buy that \n broke boy";
                    drawDialogueScreen();
                } else if (GlobalGameThreadConfigs.player.inventory.size() == GlobalGameThreadConfigs.player.inventorysize) {
                    tradestate = 0;
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
                    currentDialogue = "Your inventory is full!";
                } else {
                    if(stackamount > npc.inventory.get(itemindex).stacksize){
                        stackamount = npc.inventory.get(itemindex).stacksize;
                    }
                    GlobalGameThreadConfigs.player.bobux -= (npc.inventory.get(itemindex).Value*2)*stackamount;
                    LivingEntity SelectedItem = npc.inventory.get(itemindex);
                    if(stackamount > npc.inventory.get(itemindex).stacksize){
                        stackamount = npc.inventory.get(itemindex).stacksize;
                    }
                    npc.inventory.get(itemindex).stacksize -= stackamount;
                    if (SelectedItem.stacksize <= 0) {
                        npc.inventory.remove(itemindex);
                    }
                    LivingEntity newItem = createnewobject(SelectedItem);
                    newItem.stacksize = stackamount;
                    GlobalGameThreadConfigs.player.inventory.add(newItem);
                    stackamount = 1;
                }
            }
        }
    }
    public static void trade_sell(){
        displayINV(npc,true, false);
        int x = GlobalGameThreadConfigs.tilesize * 2;
        int y = GlobalGameThreadConfigs.tilesize * 9;
        int width = GlobalGameThreadConfigs.tilesize * 6;
        int height = GlobalGameThreadConfigs.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] back", x + 24, y + 60);
        x = GlobalGameThreadConfigs.tilesize * 12;
        y = GlobalGameThreadConfigs.tilesize * 9;
        width = GlobalGameThreadConfigs.tilesize * 6;
        height = GlobalGameThreadConfigs.tilesize * 2;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(25f));
        g2.drawString("your bobux: " + GlobalGameThreadConfigs.player.bobux, x + 24, y + 60);
        int itemindex = getItemIndex();
        if (itemindex < GlobalGameThreadConfigs.player.inventory.size()) {
            x = (int) (GlobalGameThreadConfigs.tilesize * 15.5);
            y = (int) (GlobalGameThreadConfigs.tilesize * 5.5);
            width = (int) (GlobalGameThreadConfigs.tilesize * 2.5);
            height = GlobalGameThreadConfigs.tilesize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(bobux, x + 10, y + 8, 32, 32, null);
            int stack = stackamount;
            if(stack > GlobalGameThreadConfigs.player.inventory.get(itemindex).stacksize){
                stack  =GlobalGameThreadConfigs.player.inventory.get(itemindex).stacksize;
            }
            int price = (GlobalGameThreadConfigs.player.inventory.get(itemindex).Value)*stack;
            String text = "" + price;
            g2.setFont(g2.getFont().deriveFont(15f));
            x += 42;
            g2.drawString(text, x, y + 30);
            if (KeyHandler.enterpressed) {
                if(GlobalGameThreadConfigs.player.inventory.get(itemindex) == GlobalGameThreadConfigs.player.currentshield || GlobalGameThreadConfigs.player.inventory.get(itemindex) == GlobalGameThreadConfigs.player.currentweapon){
                    commandnum = 0;
                    GlobalGameThreadConfigs.GameState =GlobalGameThreadConfigs.dialogueState;
                    currentDialogue = "you can not sell a equipped item! un equip it";
                }else {
                    LivingEntity SelectedItem = GlobalGameThreadConfigs.player.inventory.get(itemindex);
                    if(stackamount > GlobalGameThreadConfigs.player.inventory.get(itemindex).stacksize){
                        stackamount = GlobalGameThreadConfigs.player.inventory.get(itemindex).stacksize;
                    }
                    GlobalGameThreadConfigs.player.inventory.get(itemindex).stacksize -= stackamount;
                    if (SelectedItem.stacksize <= 0) {
                        GlobalGameThreadConfigs.player.inventory.remove(itemindex);
                    }
                    LivingEntity newitem = createnewobject(SelectedItem);
                    newitem.stacksize = stackamount;
                    GlobalGameThreadConfigs.player.bobux += price;
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
        y = GlobalGameThreadConfigs.tilesize * 4;
        g2.drawString(text, x, y);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y);
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenterText(text);
        y += GlobalGameThreadConfigs.tilesize * 4;
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

                MainGame.setupOBJ();
            }
        }
    }

    public static void drawoptionscreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));
        int frameX = GlobalGameThreadConfigs.tilesize*6;
        int frameY = GlobalGameThreadConfigs.tilesize;
        int framewidth = GlobalGameThreadConfigs.tilesize*8;
        int frameheight = GlobalGameThreadConfigs.tilesize*10;
        drawSubWindow(frameX, frameY , framewidth, frameheight);
        switch (optionstate){
            case 0: option_top(frameX, frameY); break;
            case 1: key_control(frameX, frameY); break;
            case 2: EndGameConfirm(frameX, frameY); break;
        }
    }

    public static void EndGameConfirm(int frameX, int frameY) {
        int textx = frameX + GlobalGameThreadConfigs.tilesize;
        int texty = frameY +GlobalGameThreadConfigs.tilesize*3;
        currentDialogue = "Quit the game and return \n to title screen?";

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textx, texty);
            texty+= 40;
        }
        String text = "Yes";
        textx = getXforCenterText(text);
        texty += GlobalGameThreadConfigs.tilesize*3;
        g2.drawString(text, textx, texty);
        if(commandnum == 0) {
            g2.drawString(">", textx - 25, texty);
            if (KeyHandler.enterpressed) {
                    optionstate = 0;
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                GlobalGameThreadConfigs.isinTital = true;
                MainGame.setupOBJ();

            }
        }
        text = "No";
        textx = getXforCenterText(text);
        texty += GlobalGameThreadConfigs.tilesize;
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
        textY = framey+GlobalGameThreadConfigs.tilesize;
        g2.drawString(text, textX, textY);

        textX = framex + GlobalGameThreadConfigs.tilesize;
        textY += GlobalGameThreadConfigs.tilesize*2;
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
        textY += GlobalGameThreadConfigs.tilesize;
        g2.drawString("Music Volume", textX, textY);
        if(commandnum == 1){
            g2.drawString(">", textX-25, textY);
        }
        textY += GlobalGameThreadConfigs.tilesize;
        g2.drawString("Sound Volume", textX, textY);
        if(commandnum == 2){
            g2.drawString(">", textX-25, textY);
        }
        textY += GlobalGameThreadConfigs.tilesize;
        g2.drawString("Controls", textX, textY);
        if(commandnum == 3){
            g2.drawString(">", textX-25, textY);
            if (KeyHandler.enterpressed) {
                KeyHandler.enterpressed = false;
                optionstate = 1;
                commandnum = 1;
            }
        }
        textY += GlobalGameThreadConfigs.tilesize;
        g2.drawString("Leave Game", textX, textY);
        if(commandnum == 4){
            g2.drawString(">", textX-25, textY);
            if(KeyHandler.enterpressed){
                optionstate = 2;
                commandnum = 0;
                KeyHandler.enterpressed = false;
            }
        }
        textY += GlobalGameThreadConfigs.tilesize;
        g2.drawString("Save Data", textX, textY);
        if(commandnum == 5){
            g2.drawString(">", textX-25, textY);
            if(KeyHandler.enterpressed){
                KeyHandler.enterpressed = false;
                if(GlobalGameThreadConfigs.filepath == null) {
                    GlobalGameThreadConfigs.filepath = JOptionPane.showInputDialog(null, "what is the name of your save file?  if you exit this window it will set the save file name to null");
                }
                if(GlobalGameThreadConfigs.Buildmode)
                  GlobalSaveManager.saveworlddata();
                else
                    GlobalSaveManager.saveplayerworlddata();
            }
        }
        textY += GlobalGameThreadConfigs.tilesize;
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
        textX = framex + GlobalGameThreadConfigs.tilesize*5;

        textY = framey + GlobalGameThreadConfigs.tilesize*3 + 27;
        g2.drawRect(textX, textY, 120, 24);
        int volumewidth = 24*gp.music.volumescale;
        g2.fillRect(textX, textY, volumewidth, 24);

        textY += GlobalGameThreadConfigs.tilesize;
        g2.drawRect(textX, textY, 120, 24);
        volumewidth = 24*gp.sound.volumescale;
        g2.fillRect(textX, textY, volumewidth, 24);
    }
    public static void key_control(int framex, int framey){
        int textX;
        int textY;
        String text = "Controls";textX = getXforCenterText(text);
        g2.setFont(g2.getFont().deriveFont(15F));
        textY = framey+GlobalGameThreadConfigs.tilesize;
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
        frameHeight = GlobalGameThreadConfigs.tilesize * 5;
        //SLOTS
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        for (int i = 0; i < GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.size(); i++) {
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(i).first){
                g2.setColor(Color.red);
                g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
            }else if(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(i).secound){
                g2.setColor(Color.blue);
                g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
            }
            g2.drawImage(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(i).down1, slotX, slotY, null);
            g2.setColor(Color.white);
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(i).maxstacksize > 1)
              g2.drawString(""+GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(i).stacksize, slotX, slotY+10);
            slotX += GlobalGameThreadConfigs.tilesize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += GlobalGameThreadConfigs.tilesize;
            }
        }
        selectItem();
        if (slotstate) {
            int cursorX = slotXstart + (GlobalGameThreadConfigs.tilesize * SlotCol);
            int cursorY = slotYstart + (GlobalGameThreadConfigs.tilesize * slotRow);
            int cursorwidth = GlobalGameThreadConfigs.tilesize;
            int cursorheight = GlobalGameThreadConfigs.tilesize;
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.white);
            g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
            int dframeY = frameY + +frameHeight;
            int dframewidth = frameWidth * 2 - ((GlobalGameThreadConfigs.tilesize * 2) + 24);
            int dframeheight = GlobalGameThreadConfigs.tilesize * 3;
            int textx = (dframeX + 20);
            int texty = dframeY + GlobalGameThreadConfigs.tilesize;
            int itemIndex = getItemIndex();
            if (itemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.size()) {
                drawSubWindow(dframeX, dframeY, dframewidth, dframeheight);
                for (String line : GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textx, texty);
                    texty += 32;
                }
            }else{
                stackamount = 1;
            }
            if(KeyHandler.use){
                KeyHandler.use = false;
                if(!merging) {
                    if (itemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.size()){
                        if (!GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex).first)
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex).first = true;
                    merger = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex);
                    merging = true;
                    mergerindex = itemIndex;
                }
                }else if(!mergingg) {
                    if (itemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.size()){
                        if (!GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex).first) {
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex).secound = true;
                            if (merger.maxstacksize > 1 && GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex).maxstacksize > 1) {
                                mergingg = true;
                                mergerr = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex);
                            } else {
                                merging = false;
                                merger = null;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(itemIndex).secound = false;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(mergerindex).first = false;
                            }
                        }
                    }else{
                        if(stackamount > merger.stacksize){
                            stackamount = merger.stacksize;
                        }
                        merger.stacksize -= stackamount;
                        merger.first = false;
                        merging = false;
                        if(merger.stacksize <= 0){
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.remove(merger);
                        }
                        LivingEntity newentity = createnewobject(merger);
                        newentity.stacksize = stackamount;
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.add(newentity);
                        stackamount = 1;
                    }
                }else {
                    if(mergerr != null){

                    if(Objects.equals(merger.name, mergerr.name)){
                        if(stackamount > merger.stacksize){
                            stackamount = merger.stacksize;
                        }
                        merger.stacksize-= stackamount;
                        mergerr.stacksize += stackamount;
                        if (merger.stacksize <= 0) {
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.remove(merger);
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
                        mergingg = false;
                    }
            }}

        }
    }

    public static void displayINV(LivingEntity entity, boolean npc, boolean cursor) {
        //FRAME
        if (GlobalGameThreadConfigs.CharacterStats || transitionfinushed){
        int frameWidth = GlobalGameThreadConfigs.tilesize * 6;
        int frameHeight = GlobalGameThreadConfigs.tilesize * 5;
        drawSubWindow(invX, frameY, frameWidth, frameHeight);
        //SLOTS
       final int slotXstart = invX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        for (int i = 0; i < GlobalGameThreadConfigs.player.inventory.size(); i++) {
            if (GlobalGameThreadConfigs.player.inventory.get(i) == GlobalGameThreadConfigs.player.currentweapon || GlobalGameThreadConfigs.player.inventory.get(i) == GlobalGameThreadConfigs.player.currentshield) {
                g2.setColor(new Color(0xFFFF00));
                g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
            }
            if(GlobalGameThreadConfigs.player.inventory.get(i).first){
                g2.setColor(Color.red);
                g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
            }else if(GlobalGameThreadConfigs.player.inventory.get(i).secound){
                g2.setColor(Color.blue);
                g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
            }
            g2.drawImage(GlobalGameThreadConfigs.player.inventory.get(i).down1, slotX, slotY, null);
            g2.setColor(Color.white);
            if(GlobalGameThreadConfigs.player.inventory.get(i).maxstacksize > 1)
              g2.drawString(""+GlobalGameThreadConfigs.player.inventory.get(i).stacksize, slotX, slotY+10);
            slotX += GlobalGameThreadConfigs.tilesize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += GlobalGameThreadConfigs.tilesize;
            }
        }
        if (!slotstate || !GlobalGameThreadConfigs.inchest) {
            if (!cursor) {
                int cursorX = slotXstart + (GlobalGameThreadConfigs.tilesize * SlotCol);
                int cursorY = slotYstart + (GlobalGameThreadConfigs.tilesize * slotRow);
                int cursorwidth = GlobalGameThreadConfigs.tilesize;
                int cursorheight = GlobalGameThreadConfigs.tilesize;
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.white);
                g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                int dframeY = frameY + +frameHeight;
                int dframewidth = frameWidth * 2 - ((GlobalGameThreadConfigs.tilesize * 2) + 24);
                int dframeheight = GlobalGameThreadConfigs.tilesize * 3;
                int textx = (dframeX + 20);
                int texty = dframeY + GlobalGameThreadConfigs.tilesize;
                int itemIndex = getItemIndex();
                togglestack(itemIndex);
                if (itemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                    drawSubWindow(dframeX, dframeY, dframewidth, dframeheight);
                    for (String line : GlobalGameThreadConfigs.player.inventory.get(itemIndex).description.split("\n")) {
                        g2.drawString(line, textx, texty);
                        texty += 32;
                    }
                }else{
                    stackamount = 1;
                }
                if(KeyHandler.use){
                    KeyHandler.use = false;
                    if(!merging){

                        if(itemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                            if (!GlobalGameThreadConfigs.player.inventory.get(itemIndex).first)
                                GlobalGameThreadConfigs.player.inventory.get(itemIndex).first = true;
                            merger = GlobalGameThreadConfigs.player.inventory.get(itemIndex);
                            merging = true;
                            mergerindex = itemIndex;
                        }
                    }else if(!mergingg) {
                        if (itemIndex < GlobalGameThreadConfigs.player.inventory.size()){

                            if (!GlobalGameThreadConfigs.player.inventory.get(itemIndex).first) {
                                GlobalGameThreadConfigs.player.inventory.get(itemIndex).secound = true;
                                if (merger.maxstacksize > 1 && GlobalGameThreadConfigs.player.inventory.get(itemIndex).maxstacksize > 1) {
                                    mergingg = true;
                                    mergerr = GlobalGameThreadConfigs.player.inventory.get(itemIndex);
                                } else {
                                    merging = false;
                                    merger = null;
                                    GlobalGameThreadConfigs.player.inventory.get(itemIndex).secound = false;
                                    GlobalGameThreadConfigs.player.inventory.get(mergerindex).first = false;
                                }
                            }
                    }else{
                            if(stackamount > merger.stacksize){
                                stackamount = merger.stacksize;
                            }
                            merger.stacksize -= stackamount;
                            merger.first = false;
                            merging = false;
                            if(merger.stacksize <= 0){
                                GlobalGameThreadConfigs.player.inventory.remove(merger);
                            }
                            LivingEntity newentity = createnewobject(merger);
                            newentity.stacksize = stackamount;
                            GlobalGameThreadConfigs.player.inventory.add(newentity);
                            stackamount = 1;
                        }
                    }else if(mergerr != null){
                        if(Objects.equals(merger.name, mergerr.name)){
                            if(stackamount > merger.stacksize){
                                stackamount = merger.stacksize;
                            }
                            merger.stacksize-= stackamount;
                            mergerr.stacksize += stackamount;
                                if (merger.stacksize <= 0) {
                                    GlobalGameThreadConfigs.player.inventory.remove(merger);
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
            int framex = GlobalGameThreadConfigs.tilesize*12;
            int frameWidth = GlobalGameThreadConfigs.tilesize * 6;
            int frameHeight = GlobalGameThreadConfigs.tilesize * 5;
            drawSubWindow(framex, frameY, frameWidth, frameHeight);
            //SLOTS
            final int slotXstart = framex + 20;
            final int slotYstart = frameY + 20;
            int slotX = slotXstart;
            int slotY = slotYstart;
            for (int i = 0; i < GlobalGameThreadConfigs.player.inventory.size(); i++) {
                if (GlobalGameThreadConfigs.player.inventory.get(i) == GlobalGameThreadConfigs.player.currentweapon || GlobalGameThreadConfigs.player.inventory.get(i) == GlobalGameThreadConfigs.player.currentshield) {
                    g2.setColor(new Color(0xFFFF00));
                    g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
                }
                if(GlobalGameThreadConfigs.player.inventory.get(i).first){
                    g2.setColor(Color.red);
                    g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
                }else if(GlobalGameThreadConfigs.player.inventory.get(i).secound){
                    g2.setColor(Color.blue);
                    g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
                }
                g2.drawImage(GlobalGameThreadConfigs.player.inventory.get(i).down1, slotX, slotY, null);
                g2.setColor(Color.white);
                g2.drawString(""+GlobalGameThreadConfigs.player.inventory.get(i).stacksize, slotX, slotY);
                slotX += GlobalGameThreadConfigs.tilesize;
                if (i == 4 || i == 9 || i == 14) {
                    slotX = slotXstart;
                    slotY += GlobalGameThreadConfigs.tilesize;
                }
            }
            if (!slotstate || !GlobalGameThreadConfigs.inchest) {
                if (!cursor) {
                    int cursorX = slotXstart + (GlobalGameThreadConfigs.tilesize * SlotCol);
                    int cursorY = slotYstart + (GlobalGameThreadConfigs.tilesize * slotRow);
                    int cursorwidth = GlobalGameThreadConfigs.tilesize;
                    int cursorheight = GlobalGameThreadConfigs.tilesize;
                    g2.setStroke(new BasicStroke(3));
                    g2.setColor(Color.white);
                    g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                    int dframeY = frameY + +frameHeight;
                    int dframewidth = frameWidth * 2 - ((GlobalGameThreadConfigs.tilesize * 4));
                    int dframeheight = GlobalGameThreadConfigs.tilesize * 3;
                    framex -= GlobalGameThreadConfigs.tilesize;
                    int textx = (framex+ 20);
                    int texty = dframeY + GlobalGameThreadConfigs.tilesize;
                    int itemIndex = getItemIndex();
                    togglestack(itemIndex);
                    if (itemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                        drawSubWindow(framex, dframeY, dframewidth, dframeheight);
                        for (String line : GlobalGameThreadConfigs.player.inventory.get(itemIndex).description.split("\n")) {
                            g2.drawString(line, textx, texty);
                            texty += 32;
                        }
                    }else{
                        stackamount = 1;
                    }
                    if(KeyHandler.use){
                        KeyHandler.use = false;
                        if(!merging){

                            if(itemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                                if (!GlobalGameThreadConfigs.player.inventory.get(itemIndex).first)
                                    GlobalGameThreadConfigs.player.inventory.get(itemIndex).first = true;
                                merger = GlobalGameThreadConfigs.player.inventory.get(itemIndex);
                                merging = true;
                                mergerindex = itemIndex;
                            }
                        }else if(!mergingg) {

                            if (itemIndex < GlobalGameThreadConfigs.player.inventory.size()){
                                if (!GlobalGameThreadConfigs.player.inventory.get(itemIndex).first) {

                                    GlobalGameThreadConfigs.player.inventory.get(itemIndex).secound = true;
                                    if (merger.maxstacksize > 1 && GlobalGameThreadConfigs.player.inventory.get(itemIndex).maxstacksize > 1) {
                                        mergingg = true;
                                        mergerr = GlobalGameThreadConfigs.player.inventory.get(itemIndex);
                                    } else {
                                        merging = false;
                                        merger = null;
                                        GlobalGameThreadConfigs.player.inventory.get(itemIndex).secound = false;
                                        GlobalGameThreadConfigs.player.inventory.get(mergerindex).first = false;
                                    }
                                }
                            }else{
                                if(stackamount > merger.stacksize){
                                    stackamount = merger.stacksize;
                                }
                                merger.stacksize -= stackamount;
                                merger.first = false;
                                merging = false;
                                LivingEntity newentity = createnewobject(merger);
                                newentity.stacksize = stackamount;
                                GlobalGameThreadConfigs.player.inventory.add(newentity);
                                stackamount = 1;
                                if(merger.stacksize <= 0){
                                    GlobalGameThreadConfigs.player.inventory.remove(merger);
                                }
                            }
                        }else {

                            if(Objects.equals(merger.name, mergerr.name)){
                                if(stackamount > merger.stacksize){
                                    stackamount = merger.stacksize;
                                }
                                merger.stacksize-= stackamount;
                                mergerr.stacksize += stackamount;
                                if (merger.stacksize <= 0) {
                                    GlobalGameThreadConfigs.player.inventory.remove(merger);
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
            int frameX = GlobalGameThreadConfigs.tilesize * 2;
            int frameY = GlobalGameThreadConfigs.tilesize;
            int framewidth = GlobalGameThreadConfigs.tilesize * 6;
            int frameheight = GlobalGameThreadConfigs.tilesize * 5;
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
                slotx += GlobalGameThreadConfigs.tilesize;
                if (i == 4 || i == 9 || i == 14) {
                    slotx = slotxstart;
                    sloty += GlobalGameThreadConfigs.tilesize;
                }
            }
            if (cursor) {
                int cursorX = slotxstart + (GlobalGameThreadConfigs.tilesize * slotcol);
                int cursorY = slotystart + (GlobalGameThreadConfigs.tilesize * slotrow);
                int cursorwidth = GlobalGameThreadConfigs.tilesize;
                int cursorheight = GlobalGameThreadConfigs.tilesize;
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.white);
                g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                int dframeY = frameY +frameheight;
                int dframewidth = frameWidth * 2 - ((GlobalGameThreadConfigs.tilesize * 2) + 24);
                int dframeheight = GlobalGameThreadConfigs.tilesize * 3;
                int textx = (frameX + 20);
                int texty = dframeY + GlobalGameThreadConfigs.tilesize;
                int itemIndex = getnpcItemIndex();
                togglenpcstack(itemIndex, entity);
                if (itemIndex < entity.inventory.size()) {
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
            int framex = GlobalGameThreadConfigs.tilesize*12;
            int frameWidth = GlobalGameThreadConfigs.tilesize * 6;
            int frameHeight = GlobalGameThreadConfigs.tilesize * 5;
            drawSubWindow(framex, frameY, frameWidth, frameHeight);
            //SLOTS
            final int slotXstart = framex + 20;
            final int slotYstart = frameY + 20;
            int slotX = slotXstart;
            int slotY = slotYstart;
            for (int i = 0; i < GlobalGameThreadConfigs.player.inventory.size(); i++) {
                if (GlobalGameThreadConfigs.player.inventory.get(i) == GlobalGameThreadConfigs.player.currentweapon || GlobalGameThreadConfigs.player.inventory.get(i) == GlobalGameThreadConfigs.player.currentshield) {
                    g2.setColor(new Color(0xFFFF00));
                    g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
                }
                if(GlobalGameThreadConfigs.player.inventory.get(i).first){
                    g2.setColor(Color.red);
                    g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
                }else if(GlobalGameThreadConfigs.player.inventory.get(i).secound){
                    g2.setColor(Color.blue);
                    g2.fillRoundRect(slotX, slotY, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize, 10, 10);
                }
                g2.drawImage(GlobalGameThreadConfigs.player.inventory.get(i).down1, slotX, slotY, null);
                g2.setColor(Color.white);
                g2.drawString(""+GlobalGameThreadConfigs.player.inventory.get(i).stacksize, slotX, slotY);
                slotX += GlobalGameThreadConfigs.tilesize;
                if (i == 4 || i == 9 || i == 14) {
                    slotX = slotXstart;
                    slotY += GlobalGameThreadConfigs.tilesize;
                }
            }
            if (!slotstate || !GlobalGameThreadConfigs.inchest) {
                if (!cursor) {
                    int cursorX = slotXstart + (GlobalGameThreadConfigs.tilesize * SlotCol);
                    int cursorY = slotYstart + (GlobalGameThreadConfigs.tilesize * slotRow);
                    int cursorwidth = GlobalGameThreadConfigs.tilesize;
                    int cursorheight = GlobalGameThreadConfigs.tilesize;
                    g2.setStroke(new BasicStroke(3));
                    g2.setColor(Color.white);
                    g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                    int dframeY = frameY + +frameHeight;
                    int dframewidth = frameWidth * 2 - ((GlobalGameThreadConfigs.tilesize * 4));
                    int dframeheight = GlobalGameThreadConfigs.tilesize * 3;
                    framex -= GlobalGameThreadConfigs.tilesize;
                    int textx = (framex+ 20);
                    int texty = dframeY + GlobalGameThreadConfigs.tilesize;
                    int itemIndex = getItemIndex();
                    togglestack(itemIndex);
                    if (itemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                        if(GlobalGameThreadConfigs.player.inventory.get(itemIndex).stacksize < stackamount){
                            stackamount = GlobalGameThreadConfigs.player.inventory.get(itemIndex).stacksize;
                        }
                        drawSubWindow(framex, dframeY, dframewidth, dframeheight);
                        for (String line : GlobalGameThreadConfigs.player.inventory.get(itemIndex).description.split("\n")) {
                            g2.drawString(line, textx, texty);
                            texty += 32;
                        }
                    }else{
                        stackamount = 1;
                    }
                    if(KeyHandler.use){
                        KeyHandler.use = false;
                        if(!merging){
                            if(itemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                                if (!GlobalGameThreadConfigs.player.inventory.get(itemIndex).first)
                                    GlobalGameThreadConfigs.player.inventory.get(itemIndex).first = true;
                                merger = GlobalGameThreadConfigs.player.inventory.get(itemIndex);
                                merging = true;
                                mergerindex = itemIndex;
                            }
                        }else if(!mergingg) {
                            if (itemIndex < GlobalGameThreadConfigs.player.inventory.size()){
                                if (!GlobalGameThreadConfigs.player.inventory.get(itemIndex).first) {
                                    GlobalGameThreadConfigs.player.inventory.get(itemIndex).secound = true;
                                    if (merger.maxstacksize > 1 && GlobalGameThreadConfigs.player.inventory.get(itemIndex).maxstacksize > 1) {
                                        mergingg = true;
                                        mergerr = GlobalGameThreadConfigs.player.inventory.get(itemIndex);
                                    } else {
                                        merging = false;
                                        merger = null;
                                        GlobalGameThreadConfigs.player.inventory.get(itemIndex).secound = false;
                                        GlobalGameThreadConfigs.player.inventory.get(mergerindex).first = false;
                                    }
                                }
                            }else{
                                if(stackamount > merger.stacksize){
                                    stackamount = merger.stacksize;
                                }
                                merger.stacksize -= stackamount;
                                merger.first = false;
                                merging = false;
                                if(merger.stacksize <= 0){
                                    GlobalGameThreadConfigs.player.inventory.remove(merger);
                                }
                                LivingEntity newentity = createnewobject(merger);
                                newentity.stacksize = stackamount;
                                GlobalGameThreadConfigs.player.inventory.add(newentity);
                                stackamount = 1;
                            }
                        }else {
                            if(Objects.equals(merger.name, mergerr.name)){
                                if(stackamount > merger.stacksize){
                                    stackamount = merger.stacksize;
                                }
                                merger.stacksize-= stackamount;
                                mergerr.stacksize += stackamount;
                                if (merger.stacksize <= 0) {
                                    GlobalGameThreadConfigs.player.inventory.remove(merger);
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
            int frameX = GlobalGameThreadConfigs.tilesize * 2;
            int frameY = GlobalGameThreadConfigs.tilesize;
            int framewidth = GlobalGameThreadConfigs.tilesize * 6;
            int frameheight = GlobalGameThreadConfigs.tilesize * 6;
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
                        case 1 -> slotx = slotxstart + GlobalGameThreadConfigs.tilesize;
                        case 2 -> slotx = slotxstart + (GlobalGameThreadConfigs.tilesize * 2);
                        case 3 -> {
                            slotx = slotxstart;
                            sloty = slotystart+GlobalGameThreadConfigs.tilesize;}
                        case 4 -> {
                            slotx = slotxstart + (GlobalGameThreadConfigs.tilesize);
                            sloty = slotystart+GlobalGameThreadConfigs.tilesize;
                        }
                        case 5 -> {
                            slotx = slotxstart + (GlobalGameThreadConfigs.tilesize*2);
                            sloty = slotystart + (GlobalGameThreadConfigs.tilesize);
                        }
                        case 6 -> {
                            slotx = slotxstart;
                            sloty = slotystart  + (GlobalGameThreadConfigs.tilesize * 2);
                        }
                        case 7 -> {
                            slotx = slotxstart + GlobalGameThreadConfigs.tilesize;
                            sloty = (slotystart + GlobalGameThreadConfigs.tilesize * 2);
                        }
                        case 8 -> {
                            slotx = slotxstart + (GlobalGameThreadConfigs.tilesize*2);
                            sloty = (slotystart + GlobalGameThreadConfigs.tilesize * 2);
                        }
                    }
                    g2.drawImage(entity.inventory.get(i).down1, slotx, sloty, null);
                    g2.setColor(Color.white);
                    if (entity.inventory.get(i).maxstacksize > 1)
                        g2.drawString("" + entity.inventory.get(i).stacksize, slotx, sloty + 10);
                }
            }
            if (cursor) {
                int cursorX = slotxstart + (GlobalGameThreadConfigs.tilesize * slotcol);
                int cursorY = slotystart + (GlobalGameThreadConfigs.tilesize * slotrow);
                int cursorwidth = GlobalGameThreadConfigs.tilesize;
                int cursorheight = GlobalGameThreadConfigs.tilesize;
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.white);
                g2.drawRoundRect(cursorX, cursorY, cursorwidth, cursorheight, 10, 10);
                int dframeY = frameY +frameheight;
                int dframewidth = frameWidth * 2 - ((GlobalGameThreadConfigs.tilesize * 2) + 24);
                int dframeheight = GlobalGameThreadConfigs.tilesize * 3;
                int textx = (frameX + 20);
                int texty = dframeY + GlobalGameThreadConfigs.tilesize;
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
        g2.drawString(""+stackamount,getXforCenterText(""+stackamount), GlobalGameThreadConfigs.tilesize*5);
        if(right){
            stackamount++;
        }
        if(left){
            if(stackamount > 1)
                stackamount--;
        }
    }}
    public static void togglenpcstack(int ItemIndex, LivingEntity entity){if(KeyHandler.sprint){
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.drawString(""+stackamount,getXforCenterText(""+stackamount), GlobalGameThreadConfigs.tilesize*5);
        if(right){
            stackamount++;
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
                if (ItemIndex < GlobalGameThreadConfigs.player.inventory.size()) {
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.size() != GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventorysize) {
                        if (!KeyHandler.CROUCH) {
                            LivingEntity SelectedItem = GlobalGameThreadConfigs.player.inventory.get(ItemIndex);
                            if(stackamount > GlobalGameThreadConfigs.player.inventory.get(ItemIndex).stacksize){
                                stackamount = GlobalGameThreadConfigs.player.inventory.get(ItemIndex).stacksize;
                            }
                            GlobalGameThreadConfigs.player.inventory.get(ItemIndex).stacksize -= stackamount;
                            if (SelectedItem.stacksize <= 0) {
                                GlobalGameThreadConfigs.player.inventory.remove(ItemIndex);
                            }
                            LivingEntity newItem = createnewobject(SelectedItem);
                            newItem.stacksize = stackamount;
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.add(newItem);

                            if (SelectedItem == GlobalGameThreadConfigs.player.currentshield) {
                                GlobalGameThreadConfigs.player.currentshield = null;
                                GlobalGameThreadConfigs.player.getDefenceValues();
                            } else if (SelectedItem == GlobalGameThreadConfigs.player.currentweapon) {
                                GlobalGameThreadConfigs.player.currentweapon = null;
                                GlobalGameThreadConfigs.player.getAttackValues();
                            }
                            stackamount = 1;
                        }
                        else {
                            LivingEntity SelectedItem = GlobalGameThreadConfigs.player.inventory.get(ItemIndex);
                           GlobalGameThreadConfigs.player.inventory.remove(ItemIndex);
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.add(SelectedItem);
                            if (SelectedItem == GlobalGameThreadConfigs.player.currentshield) {
                                GlobalGameThreadConfigs.player.currentshield = null;
                                GlobalGameThreadConfigs.player.getDefenceValues();
                            } else if (SelectedItem == GlobalGameThreadConfigs.player.currentweapon) {
                                GlobalGameThreadConfigs.player.currentweapon = null;
                                GlobalGameThreadConfigs.player.getAttackValues();
                            }
                        }
                    }
                }
            } else {
                if (ItemIndex < GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.size()) {
                    if (GlobalGameThreadConfigs.player.inventory.size() != GlobalGameThreadConfigs.player.inventorysize) {
                        if (!KeyHandler.CROUCH) {
                            if(stackamount > GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(ItemIndex).stacksize){
                                stackamount = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(ItemIndex).stacksize;
                            }
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(ItemIndex).stacksize-= stackamount;
                            LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(ItemIndex);
                            if (SelectedItem.stacksize <= 0) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.remove(ItemIndex);
                            }
                            LivingEntity newItem = createnewobject(SelectedItem);
                            GlobalGameThreadConfigs.player.inventory.add(newItem);
                            GlobalGameThreadConfigs.player.inventory.get(GlobalGameThreadConfigs.player.inventory.size() - 1).stacksize = stackamount;
                            stackamount = 1;
                        }else{
                            LivingEntity SelectedItem = GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.get(ItemIndex);
                             GlobalGameThreadConfigs.obj[MainGame.currentmap][GlobalGameThreadConfigs.player.objindex].inventory.remove(ItemIndex);
                            GlobalGameThreadConfigs.player.inventory.add(SelectedItem);
                        }
                    }
                }
            }
        }
    }
    public static LivingEntity createnewobject(LivingEntity old){
        LivingEntity newentity =  old.replicate();
        newentity.inventory = old.inventory;
        return newentity;
    }

    public static void drawMessages() {

        int Messagex = GlobalGameThreadConfigs.tilesize - 30;
        int Messagey = GlobalGameThreadConfigs.tilesize * 4;
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
            int textY = frameY + GlobalGameThreadConfigs.tilesize;
            final int lineHeight = 30;
            g2.drawString("level: " + GlobalGameThreadConfigs.player.level, textX, textY);
            textY += lineHeight;
            g2.drawString("health: " + GlobalGameThreadConfigs.player.health + "/" + GlobalGameThreadConfigs.player.maxhealth, textX, textY);
            textY += lineHeight;
            g2.drawString("Mana: " + GlobalGameThreadConfigs.player.Mana + "/" + GlobalGameThreadConfigs.player.MaxMana, textX, textY);
            textY += lineHeight;
            g2.drawString("strength: " + GlobalGameThreadConfigs.player.strength, textX, textY);
            textY += lineHeight;
            g2.drawString("defence: " + GlobalGameThreadConfigs.player.defence, textX, textY);
            textY += lineHeight;
            g2.drawString("dexterity: " + GlobalGameThreadConfigs.player.dexterity, textX, textY);
            textY += lineHeight;
            g2.drawString("XP: " + GlobalGameThreadConfigs.player.XP + "/" + GlobalGameThreadConfigs.player.MaxXP, textX, textY);
            textY += lineHeight;
            g2.drawString("bobux: " + GlobalGameThreadConfigs.player.bobux, textX, textY);
            textY += lineHeight;
            if (GlobalGameThreadConfigs.player.currentweapon != null){
                g2.drawString("weapon: " + GlobalGameThreadConfigs.player.currentweapon.name, textX, textY);
            textY += lineHeight;
        }else{
                g2.drawString("weapon: " + "none", textX, textY);
                textY += lineHeight;
            }
            if(GlobalGameThreadConfigs.player.currentshield != null) {
                g2.drawString("shield: " + GlobalGameThreadConfigs.player.currentshield.name, textX, textY);
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
        int x = GlobalGameThreadConfigs.tilesize / 2;
        int y = GlobalGameThreadConfigs.tilesize / 2;
        int i = 0;
        while (i < GlobalGameThreadConfigs.player.maxhealth / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += GlobalGameThreadConfigs.tilesize;
        }
        x = GlobalGameThreadConfigs.tilesize / 2;
        y = GlobalGameThreadConfigs.tilesize / 2;
        i = 0;
        while (i < GlobalGameThreadConfigs.player.health) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < GlobalGameThreadConfigs.player.health) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += GlobalGameThreadConfigs.tilesize;

        }
        g2.setColor(Color.white);
        if (gp.keyM.checkFPS) {
            if (GlobalGameThreadConfigs.player.invincible == true) {
                g2.drawString("" + GlobalGameThreadConfigs.player.hitTime, 10, 160);
            }
        }
        x = (GlobalGameThreadConfigs.tilesize / 2)-5;
        y = (int) (GlobalGameThreadConfigs.tilesize*1.5);
        i = 0;
        while (i < GlobalGameThreadConfigs.player.MaxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }
        x = (GlobalGameThreadConfigs.tilesize / 2)-5;
        y = (int) (GlobalGameThreadConfigs.tilesize*1.5);
        i = 0;
        while (i < GlobalGameThreadConfigs.player.Mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
        if(!GlobalGameThreadConfigs.player.sprinting || KeyHandler.sprint)
        {
            x = (GlobalGameThreadConfigs.tilesize / 2)-5;
            y+= 96;
            double onescale = GlobalGameThreadConfigs.tilesize*2 / 80;
            double HPValue = onescale * GlobalGameThreadConfigs.player.breathcounter;
            g2.setColor(new Color(255, 255, 255));
            g2.fillRect((int) (x - 1), (int) y - 16, GlobalGameThreadConfigs.tilesize * 2, 12);
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect((int) x, (int) y - 15, (int) HPValue, 10);
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
            GlobalGameThreadConfigs.player.worldx = GlobalGameThreadConfigs.tilesize*gp.ehandler.tempcol;
            GlobalGameThreadConfigs.player.worldy = GlobalGameThreadConfigs.tilesize*gp.ehandler.temprow;
            gp.ehandler.PreviousEventX = (int) GlobalGameThreadConfigs.player.worldx;
            gp.ehandler.PreviousEventY = (int) GlobalGameThreadConfigs.player.worldy;
        }

    }
    //2047 bytes long -------------------//
     public static void drawTitleScreen() {try {g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));String text = "Quest To Find Who Asked";int x = getXforCenterText(text);int y = GlobalGameThreadConfigs.tilesize * 2;g2.setColor(Color.black);g2.drawString(text, x+5, y+5);g2.setColor(Color.white);g2.drawString(text, x, y);g2.setColor(Color.black);g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));text = "START NEW GAME";x = getXforCenterText(text);y += GlobalGameThreadConfigs.tilesize * 3;if(commandnum == 0){g2.drawString(">", x-GlobalGameThreadConfigs.tilesize, y);}g2.drawString(text, x, y);g2.setColor(Color.white);text = "CONTINUE FROM SAVE FILE";x = getXforCenterText(text);y += GlobalGameThreadConfigs.tilesize * 2;if(commandnum == 1){g2.drawString(">", x-GlobalGameThreadConfigs.tilesize, y);}g2.drawString(text, x, y);g2.setColor(Color.red);text = "QUIT GAME";x = getXforCenterText(text);y += GlobalGameThreadConfigs.tilesize * 2;if(commandnum == 2){g2.drawString(">", x-GlobalGameThreadConfigs.tilesize, y);}g2.drawString(text, x, y);}catch (Exception e){e.printStackTrace();}}public static void drawDialogueScreen() {int x = GlobalGameThreadConfigs.tilesize * 2;int y = GlobalGameThreadConfigs.tilesize/2;int width = MainGame.screenwidth - (GlobalGameThreadConfigs.tilesize*4);int height = GlobalGameThreadConfigs.tilesize*4;drawSubWindow(x, y, width, height);x += GlobalGameThreadConfigs.tilesize;y += GlobalGameThreadConfigs.tilesize;g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));if(currentDialogue != null)for(String line : currentDialogue.split("\n")){g2.drawString(line, x, y);y += 40;}}public static void drawSubWindow(int x, int y, int width, int height){Color c = new Color(0, 0, 0, 210);g2.setColor(c);g2.fillRoundRect(x, y, width, height, 35, 35);c = new Color(255, 255, 255);g2.setColor(c);g2.setStroke(new BasicStroke(5));g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);}public static void drawPauseScreen(){g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));String text = "Paused";int x;x = getXforCenterText(text);int y = MainGame.screenheight / 2;g2.drawString(text, x, y);}public static int getXforCenterText(String text){int Length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();int x = MainGame.screenwidth/2 - Length/2;return x;}}