package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Mobs.green_slime;
public class EventHandler {
    static MainGame gp;
    EventProperties eventBus[][][];
    //Handles all events and uses them, uses the values from eventManager to function
    int PreviousEventX, PreviousEventY;
    boolean canTriggerEvent = true;
    public int tempmap, tempcol, temprow;
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
        int XDistance = (int) Math.abs(GlobalGameThreadConfigs.player.worldx - PreviousEventX);
        int YDistance = (int) Math.abs(GlobalGameThreadConfigs.player.worldy - PreviousEventY);
        int Distance = Math.max(XDistance, YDistance);
        if (Distance > GlobalGameThreadConfigs.tilesize) {
            canTriggerEvent = true;
        }
        if (!GlobalGameThreadConfigs.isinTital) {
            if (i < GlobalGameThreadConfigs.Monsters[1].length && GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] == null) {
                i++;
            }
            if (i == 5){
                i = 0;
                swarmpit(22, 13);
            if (raidcounter == 1) {
                UI.addMessages("Go back to spawn!");
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

            if(hit(0, 10, 40, "any")){
                swapworld(1, 12, 12, true);

            }
            if(hit(1, 12, 13, "any")){
                swapworld(0, 10, 40, false);
            }
            if(hit(1,12,9,"up")){
                speak(GlobalGameThreadConfigs.NPCS[1][0]);
            }
        }
    }

    public void speak(LivingEntity entity) {
        if(KeyHandler.enterpressed){
            KeyHandler.enterpressed = false;
            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
            GlobalGameThreadConfigs.player.attacking = false;
            entity.speak();
        }
    }

    //EXPERIMENTAL
    public static void swarmpit(int col, int row) {
        int i = 0;
        if (raidcounter < 3) {
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = col * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = row * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].makemeHostile(GlobalGameThreadConfigs.player);
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = col * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 1) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].makemeHostile(GlobalGameThreadConfigs.player);
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 1) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = row * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].makemeHostile(GlobalGameThreadConfigs.player);
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 1) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 1) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].makemeHostile(GlobalGameThreadConfigs.player);
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 2) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 1) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].makemeHostile(GlobalGameThreadConfigs.player);
            i++;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] = new green_slime(gp);
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx = (col + 1) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy = (row + 2) * GlobalGameThreadConfigs.tilesize;
            GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].makemeHostile(GlobalGameThreadConfigs.player);
            i++;
            if (raidcounter == 0) {
                for (int amogus = 0; amogus < GlobalGameThreadConfigs.NPCS[1].length; amogus++) {
                    if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus] != null) {
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].onpath = true;
                    }
                }
            }
        }
            if(raidcounter == 3) {
                raidcounter = 4;
                UI.addMessages("Talk to the NPC");
                for (int amogus = 0; amogus < GlobalGameThreadConfigs.NPCS[1].length; amogus++) {
                    if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus] != null) {
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].goingup = true;
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].dialogues[1] = "Thank you for saving us! follow me";
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].dialogues[2] = "Lets go to this special pond";
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][amogus].dialogues[3] = "";

                    }

                }
            }
            raidcounter++;
    }
    public void swapworld(int newmap, int col, int row, boolean requireskey){
        if(requireskey){
            if(KeyHandler.enterpressed){
                KeyHandler.enterpressed = false;
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.transitionstate;
                tempmap = newmap;
                tempcol = col;
                temprow = row;
                canTriggerEvent = false;
                gp.playsound(13);
            }
        }else{
            MainGame.currentmap = newmap;
            GlobalGameThreadConfigs.player.worldx = GlobalGameThreadConfigs.tilesize*col;
            GlobalGameThreadConfigs.player.worldy = GlobalGameThreadConfigs.tilesize*row;
            PreviousEventX = (int) GlobalGameThreadConfigs.player.worldx;
            PreviousEventY = (int) GlobalGameThreadConfigs.player.worldy;
            canTriggerEvent = false;
            gp.playsound(13);
        }
    }
    public void damagepit(int GameState) {
        //DAMAGE EVENT
        GlobalGameThreadConfigs.GameState = GameState;
        UI.currentDialogue = "you fell into a pit! get out";
        GlobalGameThreadConfigs.player.health--;
        canTriggerEvent = false;
    }
    public void healpit(int gamestate){
        //HEAL EVENT
        if(KeyHandler.enterpressed) {
            if(GlobalGameThreadConfigs.player.health != GlobalGameThreadConfigs.player.maxhealth) {
                GlobalGameThreadConfigs.GameState = gamestate;
                UI.currentDialogue = "you drink the water. \n Your health has been recovered";
                GlobalGameThreadConfigs.player.health = GlobalGameThreadConfigs.player.maxhealth;
                canTriggerEvent = false;
            }else{
                GlobalGameThreadConfigs.GameState = gamestate;
                UI.currentDialogue = "Your health is already full!";
                canTriggerEvent = false;
            }
            if(GlobalGameThreadConfigs.player.Mana < GlobalGameThreadConfigs.player.MaxMana){
                GlobalGameThreadConfigs.player.Mana++;
            }
        }
    }
    public boolean hit(int map, int eventCol, int eventRow, String ReqDirection) {
        //HIT DETECTION AND MANAGER
        boolean hit = false;
        if (map == MainGame.currentmap){
            GlobalGameThreadConfigs.player.hitbox.x = (int) GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.hitbox.x;
        GlobalGameThreadConfigs.player.hitbox.y = (int) GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.hitbox.y;
        eventBus[map][eventCol][eventRow].x = eventCol * GlobalGameThreadConfigs.tilesize + eventBus[map][eventCol][eventRow].x;
        eventBus[map][eventCol][eventRow].y = eventRow * GlobalGameThreadConfigs.tilesize + eventBus[map][eventCol][eventRow].y;
        if (GlobalGameThreadConfigs.player.hitbox.intersects(eventBus[map][eventCol][eventRow]) && !eventBus[map][eventCol][eventRow].eventDone) {
            if (GlobalGameThreadConfigs.player.direction.contentEquals(ReqDirection) || ReqDirection.contentEquals("any")) ;
            hit = true;
            //RECORD AND SAVE EVENT VALUES//
            PreviousEventX = (int) GlobalGameThreadConfigs.player.worldx;
            PreviousEventY = (int) GlobalGameThreadConfigs.player.worldy;

        }
        GlobalGameThreadConfigs.player.hitbox.x = GlobalGameThreadConfigs.player.hitboxdefaultx;
        GlobalGameThreadConfigs.player.hitbox.y = GlobalGameThreadConfigs.player.hitboxdefaulty;
        eventBus[map][eventCol][eventRow].x = eventBus[map][eventCol][eventRow].eventBusDefaultx;
        eventBus[map][eventCol][eventRow].y = eventBus[map][eventCol][eventRow].eventbusDefaulty;
    }
        return hit;
    }

}
