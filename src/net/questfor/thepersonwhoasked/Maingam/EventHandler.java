package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.Mobs.green_slime;
public class EventHandler {
    static MainGame gp;
    EventProperties eventBus[][][];
    //Handles all events and uses them, uses the values from eventManager to function
    int PreviousEventX, PreviousEventY;
    boolean canTriggerEvent = true;

    public static int raidcounter = 0;
    public int i;

    EventHandler(MainGame gpp) {
        this.gp = gpp;
        eventBus = new EventProperties[MainGame.maxmap][MainGame.maxworldcol][MainGame.maxworldrow];
        int mapID = 0;
        int col = 0;
        int row = 0;
        while (mapID < MainGame.maxmap && col < MainGame.maxworldcol && row < MainGame.maxworldrow) {
            //LOADS AND REGISTERS ALL SPOTS IN THE WORLD THAT CAN HAVE A EVENT
            eventBus[mapID][col][row] = new EventProperties();
            eventBus[mapID][col][row].x = 23;
            eventBus[mapID][col][row].y = 23;
            eventBus[mapID][col][row].width = 2;
            eventBus[mapID][col][row].height = 2;
            eventBus[mapID][col][row].eventBusDefaultx = eventBus[mapID][col][row].x;
            eventBus[mapID][col][row].eventbusDefaulty = eventBus[mapID][col][row].y;
            col++;
            if (col == MainGame.maxworldcol) {
                col = 0;
                row++;
            }
            if (row == MainGame.maxworldrow) {
                row = 0;
                col = 0;
                mapID++;
            }
        }

    }

    public void returnEvent() {
        //REGISTERS ALL THE EVENTS AND THERE PROPERTIES

        /*check if player can trigger an event*/
        int XDistance = (int) Math.abs(gp.player.worldx - PreviousEventX);
        int YDistance = (int) Math.abs(gp.player.worldy - PreviousEventY);
        int Distance = Math.max(XDistance, YDistance);
        if (Distance > gp.tilesize) {
            canTriggerEvent = true;
        }
        if (!GlobalGameThreadConfigs.isinTital) {
            if (i < GlobalGameThreadConfigs.Monsters[1].length && GlobalGameThreadConfigs.Monsters[0][i] == null) {
                i++;
            }
            if (i == 5){
                i = 0;
                swarmpit(22, 13);
            if (raidcounter == 1) {
                UI.addMessages("Go back to spawn!");
            }
            if (raidcounter == 1) {
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][16] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][16].getDestroyedForm();
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][17] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][17].getDestroyedForm();
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][18] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][18].getDestroyedForm();
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][15] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][16].getDestroyedForm();
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][14] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][17].getDestroyedForm();
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][13] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][18].getDestroyedForm();
            }
        }
    }

        if (canTriggerEvent) {
            /*Assign events*/
            if (hit(0,27, 16, "right")) {
                damagepit(GlobalGameThreadConfigs.dialogueState);
            }
            if (hit(0,23, 19, "any")) {
                damagepit(GlobalGameThreadConfigs.dialogueState);
            }
            if (hit(0,23, 12, "up")) {
                healpit(GlobalGameThreadConfigs.dialogueState);
            }
            if (hit(0,11, 10, "left")) {
                teleport(GlobalGameThreadConfigs.dialogueState);
            }
            if(hit(0, 10, 40, "any")){
                swapworld(1, 12, 12, true);

            }
            if(hit(1, 12, 13, "any")){
                swapworld(0, 10, 40, false);
            }
        }
    }
    //EXPERIMENTAL
    public static void swarmpit(int col, int row) {
        int i = 0;
        if (raidcounter < 3) {
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = col * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = row * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].Hostile = true;
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = col * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 1) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].Hostile = true;
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 1) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = row * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].Hostile = true;
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 1) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 1) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].Hostile = true;
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 2) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 1) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].Hostile = true;
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 1) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 2) * gp.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].Hostile = true;
            i++;
            if (raidcounter == 0) {
                for (int amogus = 0; amogus < GlobalGameThreadConfigs.NPCS[1].length; amogus++) {
                    if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus] != null) {
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].Hostile = true;
                    }
                }
            }
        }
            if(raidcounter == 3) {
                raidcounter = 4;
                UI.addMessages("Talk to the NPC");
                for (int amogus = 0; amogus < GlobalGameThreadConfigs.NPCS[1].length; amogus++) {
                    if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus] != null) {
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].Hostile = true;
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].goingup = true;
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].dialogues[1] = "Thank you for saving us! follow me";
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].dialogues[2] = "Lets go to this special pond";
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].dialogues[3] = "";

                    }

                }
            }
            raidcounter++;
    }
    public void teleport(int GameState) {
        //TELEPORT EVENT
            if(gp.player.health != gp.player.maxhealth) {
                GlobalGameThreadConfigs.GameState = GameState;
                UI.currentDialogue = "this is a secret portal, press enter to go in. \n it takes you to the backrooms....";
                if(KeyHandler.enterpressed){
                    gp.player.worldy = MainGame.tilesize * 21;
                    gp.player.worldx = MainGame.tilesize * 23;
                    GlobalGameThreadConfigs.worldID = 1;
                    KeyHandler.enterpressed = false;
                    MainGame.tilemanager.loadmap(GlobalGameThreadConfigs.worldID, 1, 0);
                }

        }
    }
    public void swapworld(int newmap, int col, int row, boolean requireskey){
        if(requireskey){
            if(KeyHandler.enterpressed){
                KeyHandler.enterpressed = false;
                MainGame.currentmap = newmap;
                gp.player.worldx = gp.tilesize*col;
                gp.player.worldy = gp.tilesize*row;
                PreviousEventX = (int) gp.player.worldx;
                PreviousEventY = (int) gp.player.worldy;
                canTriggerEvent = false;
                gp.playsound(13);
                gp.tilemanager.loadmap(1, 2, 1);
            }
        }else{
            MainGame.currentmap = newmap;
            gp.player.worldx = gp.tilesize*col;
            gp.player.worldy = gp.tilesize*row;
            PreviousEventX = (int) gp.player.worldx;
            PreviousEventY = (int) gp.player.worldy;
            canTriggerEvent = false;
            gp.playsound(13);
        }
    }
    public void damagepit(int GameState) {
        //DAMAGE EVENT
        GlobalGameThreadConfigs.GameState = GameState;
        UI.currentDialogue = "you fell into a pit! get out";
        gp.player.health--;
        canTriggerEvent = false;
    }
    public void healpit(int gamestate){
        //HEAL EVENT
        if(KeyHandler.enterpressed) {
            if(gp.player.health != gp.player.maxhealth) {
                GlobalGameThreadConfigs.GameState = gamestate;
                UI.currentDialogue = "you drink the water. \n Your health has been recovered";
                gp.player.health = gp.player.maxhealth;
                canTriggerEvent = false;
            }else{
                GlobalGameThreadConfigs.GameState = gamestate;
                UI.currentDialogue = "Your health is already full!";
                canTriggerEvent = false;
            }
            if(gp.player.Mana < gp.player.MaxMana){
                gp.player.Mana++;
            }
            gp.MultiRender.setMonsterRenderers();
            gp.MultiRender.setObjectRenderer();
        }
    }
    public boolean hit(int map, int eventCol, int eventRow, String ReqDirection) {
        //HIT DETECTION AND MANAGER
        boolean hit = false;
        if (map == MainGame.currentmap){
            gp.player.hitbox.x = (int) gp.player.worldx + gp.player.hitbox.x;
        gp.player.hitbox.y = (int) gp.player.worldy + gp.player.hitbox.y;
        eventBus[map][eventCol][eventRow].x = eventCol * gp.tilesize + eventBus[map][eventCol][eventRow].x;
        eventBus[map][eventCol][eventRow].y = eventRow * gp.tilesize + eventBus[map][eventCol][eventRow].y;
        if (gp.player.hitbox.intersects(eventBus[map][eventCol][eventRow]) && !eventBus[map][eventCol][eventRow].eventDone) {
            if (gp.player.direction.contentEquals(ReqDirection) || ReqDirection.contentEquals("any")) ;
            hit = true;
            //RECORD AND SAVE EVENT VALUES//
            PreviousEventX = (int) gp.player.worldx;
            PreviousEventY = (int) gp.player.worldy;

        }
        gp.player.hitbox.x = gp.player.hitboxdefaultx;
        gp.player.hitbox.y = gp.player.hitboxdefaulty;
        eventBus[map][eventCol][eventRow].x = eventBus[map][eventCol][eventRow].eventBusDefaultx;
        eventBus[map][eventCol][eventRow].y = eventBus[map][eventCol][eventRow].eventbusDefaulty;
    }
        return hit;
    }

}
