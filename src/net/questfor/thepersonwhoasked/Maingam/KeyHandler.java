package net.questfor.thepersonwhoasked.Maingam;
import net.questfor.thepersonwhoasked.entities.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler extends Data implements KeyListener {
    MainGame gp;
    KeyHandler(MainGame gpp){
        this.gp = gpp;
    }
    //manages and handles key bindings

    @Override
    public void keyTyped(KeyEvent e) {
    }
    //movement keys
    public static boolean upPressed, downPressed, rightPressed, leftPressed;
    //general keys
    public static boolean pickup, attack, jump, moveitem, moving, sprint;
    public static boolean use = false;
    //advanced keys
    public static boolean enterpressed;
    public static boolean primepowera, secpowera;
    public static int primepowerc = KeyEvent.VK_Q, secpowerc = KeyEvent.VK_E;
    public static boolean checkFPS;
    public static boolean CROUCH;
    public static int FPSC = KeyEvent.VK_T;
    //DATA VALUS
    public int pause = 0;
    public int option = 0;
    public static int UP = KeyEvent.VK_W, DOWN = KeyEvent.VK_S, RIGHT = KeyEvent.VK_D, LEFT = KeyEvent.VK_A, SHIFT = KeyEvent.VK_SHIFT;
    public static int PAUSE = KeyEvent.VK_P;
    public static int INVENTORY = KeyEvent.VK_TAB, OPEN = KeyEvent.VK_ENTER;


    @Override
    public void keyPressed(KeyEvent e) {
        try {
            //MANAGES KEY BINDINGS WHEN YOU PRESS THEM//
            int code = e.getKeyCode();

            if(GlobalGameThreadConfigs.isinTital) {
                if (code == KeyEvent.VK_UP) {
                    UI.commandnum--;
                    if (UI.commandnum < 0) {
                        UI.commandnum = 2;
                    }
                }
                if (code == KeyEvent.VK_DOWN) {
                    UI.commandnum++;
                    if (UI.commandnum > 2) {
                        UI.commandnum = 0;
                    }
                }
                if (code == OPEN) {
                    if (UI.commandnum == 0) {
                        gp.setupOBJ();
                        gp.player = new Player(gp.keyM, gp);
                        GlobalGameThreadConfigs.isinTital = false;

                    }
                    if(UI.commandnum == 1){
                        GlobalGameThreadConfigs.isinTital = false;
                        GlobalGameThreadConfigs.filepath = JOptionPane.showInputDialog(null, "what is the name of the save file to load?  if you exit this window it will set the save file name to null");
                        GlobalSaveManager.loadplayerworlddata();
                    }
                    if(UI.commandnum == 2){
                        System.exit(0);
                    }
                }
            }else {
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState) {
                    if(code == primepowerc){
                        primepowera = true;
                    }
                    if (code == UP) {
                        if (upPressed == false) {
                            upPressed = true;

                        }
                    }
                    if (code == DOWN) {
                        if (downPressed == false) {
                            downPressed = true;
                        }
                    }
                    if (code == LEFT) {
                        if (leftPressed == false) {
                            leftPressed = true;
                        }
                    }
                    if (code == RIGHT) {
                        if (rightPressed == false) {
                            rightPressed = true;
                        }
                    }
                    if (code == PAUSE) {
                        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.pauseState;

                    }
                    if (code == FPSC) {
                        if (!checkFPS) {
                            checkFPS = true;
                        } else if (checkFPS) {
                            checkFPS = false;
                        }
                    }
                    if (code == KeyEvent.VK_SPACE) {
                        if (!jump) {
                            Player.isup = true;
                            jump = true;
                        }
                    }
                    if (GlobalGameThreadConfigs.CharacterStats) {
                        if (!sprint){
                            if (code == KeyEvent.VK_UP) {
                                if (UI.slotRow != 0) {
                                    UI.slotRow--;
                                }
                                gp.playsound(9);
                            }
                        if (code == KeyEvent.VK_DOWN) {
                            if (UI.slotRow != 3) {
                                UI.slotRow++;
                            }
                            gp.playsound(9);
                        }
                        if (code == KeyEvent.VK_RIGHT) {
                            if (UI.SlotCol != 4) {
                                UI.SlotCol++;
                            } else {
                                if (GlobalGameThreadConfigs.inchest) {
                                    UI.slotstate = false;
                                    UI.SlotCol = 0;
                                }
                            }
                            gp.playsound(9);
                        }
                        if (code == KeyEvent.VK_LEFT) {
                            if (UI.SlotCol != 0) {
                                UI.SlotCol--;
                            } else {
                                if (GlobalGameThreadConfigs.inchest) {
                                    UI.slotstate = true;
                                    UI.SlotCol = 4;
                                }
                            }
                            gp.playsound(9);
                        }
                    }else{
                            if(code == KeyEvent.VK_RIGHT){
                                UI.right = true;
                            }
                            if(code == KeyEvent.VK_LEFT){
                                UI.left = true;
                            }
                        }

                    }
                    if (code == INVENTORY) {
                        if (!GlobalGameThreadConfigs.CharacterStats) {
                            GlobalGameThreadConfigs.CharacterStats = true;
                        } else {
                            GlobalGameThreadConfigs.CharacterStats = false;
                            UI.slotstate = false;
                            if(UI.merger != null){
                                UI.merger.first = false;
                            }
                            if(UI.mergerr != null){
                                UI.mergerr = null;
                            }
                        }
                    }
                    if (GlobalGameThreadConfigs.inchest) {
                    if (code == KeyEvent.VK_M) {
                            moveitem = true;
                            moving = true;
                        }
                    }
                    if (GlobalGameThreadConfigs.CharacterStats){
                        if (code == KeyEvent.VK_1) {
                            int codenum = 1;
                            gp.player.convertItem(codenum);
                        } else if (code == KeyEvent.VK_2) {
                            int codenum = 2;
                            gp.player.convertItem(codenum);
                        } else if (code == KeyEvent.VK_3) {
                            int codenum = 3;
                            gp.player.convertItem(codenum);
                        } else if (code == KeyEvent.VK_4) {
                            int codenum = 4;
                            gp.player.convertItem(codenum);
                        } else if (code == KeyEvent.VK_5) {
                            int codenum = 5;
                            gp.player.convertItem(codenum);
                        } else if (code == KeyEvent.VK_6) {
                            int codenum = 6;
                            gp.player.convertItem(codenum);
                        }
                }
                    if(code == KeyEvent.VK_ESCAPE){
                        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.optionsstate;
                    }
            }
                if (code == OPEN) {
                    enterpressed = true;
                }
                if(code == SHIFT){
                    CROUCH = true;
                }
                if(code == KeyEvent.VK_CONTROL){
                    sprint = true;
                }
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.pauseState) {
                    if (code == PAUSE) {
                        pause++;
                        if (pause == 2) {
                            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                            MainGame.playmusic(0);
                            pause = 0;
                        }
                    }
                }
                if(GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.GameOverState){
                    if (code == KeyEvent.VK_UP) {
                        UI.commandnum--;
                        gp.playsound(9);
                        if (UI.commandnum < 0) {
                            UI.commandnum = 1;
                        }
                    }
                    else if (code == KeyEvent.VK_DOWN) {
                        UI.commandnum++;
                        gp.playsound(9);
                        if (UI.commandnum > 1) {
                            UI.commandnum = 0;
                        }
                    }
                }
                if(GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.UIstate){
                    displayGUI(code);
                }
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.optionsstate) {
                    if (code == KeyEvent.VK_ESCAPE) {
                        option++;
                        if (option == 2) {
                            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                            option = 0;
                            UI.optionstate = 0;
                        }
                    }
                    int maxcommandnum = 0;
                    switch (UI.optionstate){
                        case 0 -> maxcommandnum = 6;
                        case 1 -> maxcommandnum = 10;
                        case 2 -> maxcommandnum = 2;
                    }
                    if (code == KeyEvent.VK_UP) {
                        UI.commandnum--;
                        gp.playsound(9);
                        if (UI.commandnum < 0) {
                            UI.commandnum = maxcommandnum;
                        }
                    }
                    else if (code == KeyEvent.VK_DOWN) {
                        UI.commandnum++;
                        gp.playsound(9);
                        if (UI.commandnum > maxcommandnum) {
                            UI.commandnum = 0;
                        }
                    }
                    else if(code == KeyEvent.VK_LEFT){
                        if(UI.optionstate == 0){
                            if(UI.commandnum == 1 && MainGame.music.volumescale > 0){
                                MainGame.music.volumescale--;
                                gp.music.ControlVolume();
                                gp.playsound(9);
                            }else if(UI.commandnum == 2 && MainGame.sound.volumescale > 0){
                                MainGame.sound.volumescale--;
                                gp.playsound(9);
                            }
                        }
                    }
                    else if(code == KeyEvent.VK_RIGHT){
                        if(UI.optionstate == 0){
                            if(UI.commandnum == 1 && MainGame.music.volumescale < 5){
                                MainGame.music.volumescale++;
                                gp.music.ControlVolume();
                                gp.playsound(9);
                            }else if(UI.commandnum == 2 && MainGame.sound.volumescale < 5){
                                MainGame.sound.volumescale++;
                                gp.playsound(9);
                            }
                        }
                    }
                    if(code != KeyEvent.VK_DOWN && code != OPEN && code != KeyEvent.VK_UP && UI.optionstate == 1){
                        switch (UI.commandnum){
                            case 1 -> UP = code;
                            case 2 -> DOWN = code;
                            case 3 -> RIGHT = code;
                            case 4 -> LEFT = code;
                            case 5 -> PAUSE = code;
                            case 6 -> primepowerc = code;
                            case 7 -> secpowerc = code;
                            case 8 -> FPSC = code;
                            case 9 -> INVENTORY = code;
                            case 10 -> OPEN = code;
                        }
                    }
                }
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.dialogueState) {
                    if (code == OPEN) {
                        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                    }
                }
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.tradestate) {
                    tradestate(code);
                }
            }
        }catch(Exception er){
            crash.main(er);
        }
    }

    public void displayGUI(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
            UI.currentUI = "";
        }
        if (!sprint) {
            if (code == KeyEvent.VK_UP) {
                if (UI.slotRow != 0) {
                    UI.slotRow--;
                }
                gp.playsound(9);
            }
            if (code == KeyEvent.VK_DOWN) {
                if (UI.slotRow != 3) {
                    UI.slotRow++;
                }
                gp.playsound(9);
            }
            if (code == KeyEvent.VK_RIGHT) {
                if (UI.SlotCol != 4) {
                    UI.SlotCol++;
                } else {
                    UI.SlotCol = 0;
                }
                gp.playsound(9);
            }
            if (code == KeyEvent.VK_LEFT) {
                if (UI.SlotCol != 0) {
                    UI.SlotCol--;
                } else {
                    UI.SlotCol = 4;
                }
                gp.playsound(9);
            }
        }
        if (sprint) {
            if (code == KeyEvent.VK_RIGHT) {
                UI.right = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                UI.left = true;
            }
        }
            if(UI.currentUI.equals("crafting")){
        if(code == KeyEvent.VK_1){
            UI.code = 0;
        }
        if(code == KeyEvent.VK_2){
            UI.code = 1;
        }
        if(code == KeyEvent.VK_3){
            UI.code = 2;
        }
        if(code == KeyEvent.VK_4){
            UI.code = 3;
        }
        if(code == KeyEvent.VK_5){
            UI.code = 4;
        }
        if(code == KeyEvent.VK_6){
            UI.code = 5;
        }
        if(code == KeyEvent.VK_7){
            UI.code = 6;
        }
        if(code == KeyEvent.VK_8){
            UI.code = 7;
        }
        if(code == KeyEvent.VK_9) {
            UI.code = 8;
        }}else if(UI.currentUI.equals("Furnace")){
                if(code == KeyEvent.VK_1){
                    UI.code = 0;
                }
                if(code == KeyEvent.VK_2){
                    UI.code = 7;
                }
            }
    }

    public void tradestate(int code) {
        if(sprint){
            if(code == KeyEvent.VK_RIGHT){
                UI.right = true;
            }
            if(code == KeyEvent.VK_LEFT){
                UI.left = true;
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            UI.commandnum = 0;
            switch (UI.tradestate) {
                case 0:
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                    break;
                case 1, 2:
                    UI.tradestate = 0;
                    break;
            }
        }
        if (UI.tradestate == 0) {
            if (code == KeyEvent.VK_UP) {
                UI.commandnum--;
                if (UI.commandnum < 0) {
                    UI.commandnum = 3;
                }
                gp.playsound(9);
            }
            if (code == KeyEvent.VK_DOWN) {
                UI.commandnum++;
                if (UI.commandnum > 2) {
                    UI.commandnum = 0;
                }
                gp.playsound(9);
            }

        } else if (UI.tradestate == 1) {
            if(!sprint){
                if (code == KeyEvent.VK_UP) {
                    if (UI.npcslotrow != 0) {
                        UI.npcslotrow--;
                    }
                    gp.playsound(9);
                }
                if (code == KeyEvent.VK_DOWN) {
                    if (UI.npcslotrow != 3) {
                        UI.npcslotrow++;
                    }
                    gp.playsound(9);
                }
                if (code == KeyEvent.VK_RIGHT) {
                    if (UI.npcslotcol != 4) {
                        UI.npcslotcol++;
                    } else {
                        UI.npcslotcol = 0;
                    }
                    gp.playsound(9);
                }
                if (code == KeyEvent.VK_LEFT) {
                    if (UI.npcslotcol != 0) {
                        UI.npcslotcol--;
                    } else {
                        UI.npcslotcol = 4;
                    }
                    gp.playsound(9);
                }}
            }else {
            if (!sprint) {
                if (code == KeyEvent.VK_UP) {
                    if (UI.slotRow != 0) {
                        UI.slotRow--;
                    }
                    gp.playsound(9);
                }
                if (code == KeyEvent.VK_DOWN) {
                    if (UI.slotRow != 3) {
                        UI.slotRow++;
                    }
                    gp.playsound(9);
                }
                if (code == KeyEvent.VK_RIGHT) {
                    if (UI.SlotCol != 4) {
                        UI.SlotCol++;
                    } else {
                        UI.SlotCol = 0;
                    }
                    gp.playsound(9);
                }
                if (code == KeyEvent.VK_LEFT) {
                    if (UI.SlotCol != 0) {
                        UI.SlotCol--;
                    } else {
                        UI.SlotCol = 4;
                    }
                    gp.playsound(9);
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //MANAGES KEY BINDINGS WHEN YOU RELEASE THEM//
        try {
            int code = e.getKeyCode();
            if(code == primepowerc){
                primepowera = false;
            }
            if (code == UP) {
                upPressed = false;
            }
            if (code == DOWN) {
                downPressed = false;
            }
            if (code == LEFT) {
                leftPressed = false;
            }
            if (code == RIGHT) {
                rightPressed = false;
            }
            if(code == SHIFT){
                CROUCH = false;
            }
            if(code == KeyEvent.VK_CONTROL){
                sprint = false;
            }
            if(sprint) {
                if (code == KeyEvent.VK_RIGHT) {
                    UI.right = false;
                }
                if (code == KeyEvent.VK_LEFT) {
                    UI.left = false;
                }
            }
        }catch(Exception err){
            crash.main(err);
        }
    }
}

