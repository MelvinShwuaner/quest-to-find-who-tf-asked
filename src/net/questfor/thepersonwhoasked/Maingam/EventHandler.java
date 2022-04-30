package net.questfor.thepersonwhoasked.Maingam;
import net.questfor.thepersonwhoasked.MultiRenderer;

import static net.questfor.thepersonwhoasked.Maingam.UI.g2;
public class EventHandler {
    MainGame gp;
    EventManager eventBus[][];
    //Handles all events and uses them, uses the values from eventManager to function
    int PreviousEventX, PreviousEventY;
    boolean canTriggerEvent = true;
    EventHandler(MainGame gpp){
        this.gp = gpp;
        eventBus = new EventManager[MainGame.maxworldcol][MainGame.maxworldrow];
        int col = 0;
        int row = 0;
        while(col < MainGame.maxworldcol && row < MainGame.maxworldrow){
            //LOADS AND REGISTERS ALL SPOTS IN THE WORLD THAT CAN HAVE A EVENT
            eventBus[col][row] = new EventManager();
            eventBus[col][row].x = 23;
            eventBus[col][row].y = 23;
            eventBus[col][row].width = 2;
            eventBus[col][row].height = 2;
            eventBus[col][row].eventBusDefaultx = eventBus[col][row].x;
            eventBus[col][row].eventbusDefaulty = eventBus[col][row].y;
            col++;
            if(col == MainGame.maxworldcol){
                col = 0;
                row++;
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
        if (canTriggerEvent) {
            /*Assign events*/
            if (hit(27, 16, "right")) {damagepit(27, 16, GlobalGameThreadConfigs.dialogueState);}
            if (hit(23, 19, "any")) {damagepit(23, 19, GlobalGameThreadConfigs.dialogueState);}
            if (hit(23, 12, "up")) {healpit(23, 12, GlobalGameThreadConfigs.dialogueState);}
            if (hit(11, 10, "left")) {teleport(11, 10, GlobalGameThreadConfigs.dialogueState);}
        }
    }
    private void teleport(int col, int row, int GameState) {
        //TELEPORT EVENT
            if(gp.player.health != gp.player.maxhealth) {
                GlobalGameThreadConfigs.GameState = GameState;
                UI.currentDialogue = "this is a secret portal, press enter to go in. \n it takes you to the backrooms....";
                if(KeyHandler.enterpressed){
                    gp.player.worldy = MainGame.tilesize * 21;
                    gp.player.worldx = MainGame.tilesize * 23;
                    GlobalGameThreadConfigs.worldID = "/maps/world01.txt";
                    MainGame.tilemanager.loadmap(GlobalGameThreadConfigs.worldID);
                }
        }
    }

    private void damagepit(int col, int row, int GameState) {
        //DAMAGE EVENT
        GlobalGameThreadConfigs.GameState = GameState;
        UI.currentDialogue = "you fell into a pit! get out";
        gp.player.health--;
        eventBus[col][row].eventDone = true;
        canTriggerEvent = false;
    }
    public void healpit(int col, int row, int gamestate){
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
            gp.MultiRender.setMonsterRenderers();
        }
    }

    public boolean hit(int eventCol, int eventRow, String ReqDirection){
        //HIT DETECTION AND MANAGER
        boolean hit = false;
        gp.player.hitbox.x = (int) gp.player.worldx + gp.player.hitbox.x;
        gp.player.hitbox.y = (int) gp.player.worldy + gp.player.hitbox.y;
        eventBus[eventCol][eventRow].x = eventCol * gp.tilesize + eventBus[eventCol][eventRow].x;
        eventBus[eventCol][eventRow].y = eventRow * gp.tilesize + eventBus[eventCol][eventRow].y;
        if(gp.player.hitbox.intersects(eventBus[eventCol][eventRow]) && !eventBus[eventCol][eventRow].eventDone){
            if(gp.player.direction.contentEquals(ReqDirection) || ReqDirection.contentEquals("any"));
            hit = true;
            //RECORD AND SAVE EVENT VALUES//
            PreviousEventX = (int) gp.player.worldx;
            PreviousEventY = (int) gp.player.worldy;

        }
        gp.player.hitbox.x = gp.player.hitboxdefaultx;
        gp.player.hitbox.y = gp.player.hitboxdefaulty;
        eventBus[eventCol][eventRow].x = eventBus[eventCol][eventRow].eventBusDefaultx;
        eventBus[eventCol][eventRow].y = eventBus[eventCol][eventRow].eventbusDefaulty;
        return hit;
    }

}
