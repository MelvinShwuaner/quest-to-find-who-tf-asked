package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.entities.Mobs.GunMan;
import net.questfor.thepersonwhoasked.entities.NPCS.idontknowthenameofthisguy;

public class EventHandler {
    static MainGame gp;
    EventProperties eventBus[][][];
    //Handles all events and uses them, uses the values from eventManager to function
    int PreviousEventX, PreviousEventY;
    boolean canTriggerEvent = true;
    public int tempmap, tempcol, temprow;
    public  int raidcounter = 0;
    public boolean finushconversation = false, isFinushconversation = false, doneconversation = false;

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

        if (canTriggerEvent) {
            /*Assign events*/
            if(hit(0, 113, 109, "any")){
                if(!finushconversation){
                speak(GlobalGameThreadConfigs.NPCS[0][1]);
                if(GlobalGameThreadConfigs.NPCS[0][1].dialogueIndex == 4){
                    gp.tilemanager.mapRendererID[0][110][109][4] = 46;
                    gp.tilemanager.mapRendererID[0][110][109][5] = 46;
                    gp.tilemanager.mapRendererID[0][110][110][4] = 46;
                    gp.tilemanager.mapRendererID[0][110][110][5] = 46;
                    gp.tilemanager.mapRendererID[0][110][111][4] = 46;
                    gp.tilemanager.mapRendererID[0][110][111][5] = 46;
                    gp.tilemanager.mapRendererID[0][109][108][4] = 52;
                    gp.tilemanager.mapRendererID[0][111][111][4] = 52;
                    gp.tilemanager.mapRendererID[0][108][111][4] = 52;
                    gp.tilemanager.mapRendererID[0][109][109][4] = 52;
                    gp.tilemanager.mapRendererID[0][110][111][4] = 52;
                    gp.tilemanager.mapRendererID[0][124][110][3] = 46;
                    gp.tilemanager.mapRendererID[0][124][109][3] = 46;
                    gp.tilemanager.mapRendererID[0][123][109][3] = 39;
                    gp.tilemanager.mapRendererID[0][122][109][3] = 39;
                    gp.tilemanager.mapRendererID[0][122][108][3] = 39;
                    gp.tilemanager.mapRendererID[0][123][108][3] = 39;
                    gp.tilemanager.mapRendererID[0][122][107][4] = 46;
                    gp.tilemanager.mapRendererID[0][123][107][4] = 46;
                    gp.tilemanager.mapRendererID[0][123][107][5] = 46;
                    gp.tilemanager.mapRendererID[0][124][107][4] = 46;
                    gp.playsound(10);
                    finushconversation = true;
                }
            }}
            if(hit(0, 110, 109, "any") || hit(0, 110, 110, "any") || hit(0, 110, 111, "any")){
                if(GlobalGameThreadConfigs.NPCS[0][2] == null){
                    GlobalGameThreadConfigs.NPCS[0][2] = new idontknowthenameofthisguy(gp);
                    GlobalGameThreadConfigs.NPCS[0][2].worldx = 108 * GlobalGameThreadConfigs.tilesize;
                    GlobalGameThreadConfigs.NPCS[0][2].worldy = 110 * GlobalGameThreadConfigs.tilesize;
                }
                if(!isFinushconversation){
                    speak(GlobalGameThreadConfigs.NPCS[0][2]);
                    if(GlobalGameThreadConfigs.NPCS[0][2].dialogueIndex == 8){
                        GlobalGameThreadConfigs.NPCS[0][2].speed = 5;
                        isFinushconversation = true;

                    }
                }
            }
            if(isFinushconversation){
                raidcounter++;
                if((raidcounter == 80) ||hit(0, 109, 109, "any") || hit(0, 109, 110, "any") || hit(0, 109, 111, "any")
){
                    if(!doneconversation){
                    gp.tilemanager.mapRendererID[0][119][109][5] = 46;
                    gp.tilemanager.mapRendererID[0][119][110][5] = 46;
                        GlobalGameThreadConfigs.Monsters[0][5] = new GunMan(gp);
                        GlobalGameThreadConfigs.Monsters[0][5].worldx = GlobalGameThreadConfigs.tilesize*122;
                        GlobalGameThreadConfigs.Monsters[0][5].worldy = GlobalGameThreadConfigs.tilesize*105;
                        for(int i = 0; i < 4; i++){
                            GlobalGameThreadConfigs.Monsters[0][i].makemeHostile(GlobalGameThreadConfigs.player);
                        }

                    gp.playsound(10);
                    doneconversation = true;
                }}
            }
        }
    }

    public void speak(LivingEntity entity) {

            KeyHandler.enterpressed = false;
            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
            GlobalGameThreadConfigs.player.attacking = false;
            entity.speak();

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
            if (GlobalGameThreadConfigs.player.direction.contentEquals(ReqDirection) || ReqDirection.contentEquals("any"))
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
