package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
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
    public static boolean pickup, attack, mine, build, jump, moveitem, moving;
    public static boolean use = false;
    //advanced keys
    public static boolean enterpressed;
    public static boolean primepowera, secpowera;
    public static int primepowerc = KeyEvent.VK_Q, secpowerc = KeyEvent.VK_E;
    public static boolean checkFPS;
    public static int FPSC = KeyEvent.VK_T;
    //DATA VALUS
    public int pause = 0;
    public int option = 0;
    public static int UP = KeyEvent.VK_W, DOWN = KeyEvent.VK_S, RIGHT = KeyEvent.VK_D, LEFT = KeyEvent.VK_A;
    public static int PAUSE = KeyEvent.VK_P;
    public static int INVENTORY = KeyEvent.VK_TAB, OPEN = KeyEvent.VK_ENTER;


    @Override
    public void keyPressed(KeyEvent e) {
        try {
            //MANAGES KEY BINDINGS WHEN YOU PRESS THEM//
            int code = e.getKeyCode();
            if(code == KeyEvent.VK_R){
               mine = true;
            }
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
                        MainGame.player.worldx = MainGame.tilesize * 12;
                        MainGame.player.worldy = MainGame.tilesize * 9;
                        GlobalGameThreadConfigs.isinTital = false;

                    }
                    if(UI.commandnum == 1){
                        GlobalGameThreadConfigs.isinTital = false;
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

                    }
                    if (code == INVENTORY) {
                        if (!GlobalGameThreadConfigs.CharacterStats) {
                            GlobalGameThreadConfigs.CharacterStats = true;
                        } else {
                            GlobalGameThreadConfigs.CharacterStats = false;
                        }
                    }
                    if (code == KeyEvent.VK_M) {
                        if (GlobalGameThreadConfigs.inchest) {
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
            }
        }catch(Exception er){
            crash.main(er);
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
            if(code == KeyEvent.VK_R){
                mine = false;
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
        }catch(Exception err){
            crash.main(err);
        }
    }
}

