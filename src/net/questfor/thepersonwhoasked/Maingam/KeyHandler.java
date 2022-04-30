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
    //advanced keys
    public static boolean enterpressed;
    public int pause = 0;


    public boolean checkFPS;

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
                if (code == KeyEvent.VK_ENTER) {
                    if (UI.commandnum == 0) {
                        MainGame.player.worldx = MainGame.tilesize * 23;
                        MainGame.player.worldy = MainGame.tilesize * 21;
                        GlobalGameThreadConfigs.isinTital = false;

                    }
                    if(UI.commandnum == 1){
                        //coming soon in later update
                    }
                    if(UI.commandnum == 2){
                        System.exit(0);
                    }
                }
            }else {
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState) {
                    if (code == KeyEvent.VK_W) {
                        if (upPressed == false) {
                            upPressed = true;

                        }
                    }
                    if (code == KeyEvent.VK_S) {
                        if (downPressed == false) {
                            downPressed = true;
                        }
                    }
                    if (code == KeyEvent.VK_A) {
                        if (leftPressed == false) {
                            leftPressed = true;
                        }
                    }
                    if (code == KeyEvent.VK_D) {
                        if (rightPressed == false) {
                            rightPressed = true;
                        }
                    }
                    if (code == KeyEvent.VK_P) {
                        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.pauseState;

                    }
                    if (code == KeyEvent.VK_T) {
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
                    if (code == KeyEvent.VK_TAB) {
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
            }

                if (code == KeyEvent.VK_ENTER) {
                    enterpressed = true;
                }
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.pauseState) {
                    if (code == KeyEvent.VK_P) {
                        pause++;
                        if (pause == 2) {
                            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                            MainGame.playmusic(0);
                            pause = 0;
                        }
                    }
                }
                if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.dialogueState) {
                    if (code == KeyEvent.VK_ENTER) {
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

            if (code == KeyEvent.VK_W) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }
        }catch(Exception err){
            crash.main(err);
        }
    }
}

