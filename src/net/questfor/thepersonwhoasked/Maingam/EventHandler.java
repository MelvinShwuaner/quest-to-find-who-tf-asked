package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.tile.tilemanager;

import java.awt.*;
import java.util.Objects;

import static net.questfor.thepersonwhoasked.Maingam.UI.g2;

public class EventHandler {
    MainGame gp;
    Rectangle eventBus;
    int eventBusDefaultx, eventbusDefaulty;
    EventHandler(MainGame gpp){
        this.gp = gpp;
        eventBus = new Rectangle();
        eventBus.x = 23;
        eventBus.y = 23;
        eventBus.width = 2;
        eventBus.height = 2;
        eventBusDefaultx = eventBus.x;
        eventbusDefaulty = eventBus.y;
    }
    public void returnEvent(){
            if (hit(27, 16, "right")) {damagepit(GlobalGameThreadConfigs.dialogueState);}
            if (hit(23, 12, "up")) {healpit(GlobalGameThreadConfigs.dialogueState);}
            if(hit(11, 10, "left")){teleport(GlobalGameThreadConfigs.dialogueState);}
    }

    private void teleport(int GameState) {
            if(gp.player.health != gp.player.maxhealth) {
                GlobalGameThreadConfigs.GameState = GameState;
                UI.currentDialogue = "this is a secret portal, press enter to go in. \n it takes you to the backrooms....";
                if(KeyHandler.enterpressed){
                    gp.player.worldy = MainGame.tilesize * 21;
                    gp.player.worldx = MainGame.tilesize * 23;
                    GlobalGameThreadConfigs.worldID = "/maps/world01.txt";
                    MainGame.tilemanager.draw(g2);
                }
        }
    }

    private void damagepit(int GameState) {
        GlobalGameThreadConfigs.GameState = GameState;
        UI.currentDialogue = "you fell into a pit! get out";
        gp.player.health--;
        gp.player.worldx -= gp.tilesize;
    }
    public void healpit(int gamestate){
        if(KeyHandler.enterpressed) {
            if(gp.player.health != gp.player.maxhealth) {
                GlobalGameThreadConfigs.GameState = gamestate;
                UI.currentDialogue = "you drink the water. \n Your health has been recovered";
                gp.player.health++;
                gp.player.worldy += MainGame.tilesize;
            }else{
                GlobalGameThreadConfigs.GameState = gamestate;
                UI.currentDialogue = "Your health is already full!";
                gp.player.worldy += MainGame.tilesize;
            }
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqdirection){
        boolean hit = false;
        gp.player.hitbox.x = (int) gp.player.worldx + gp.player.hitbox.x;
        gp.player.hitbox.y = (int) gp.player.worldy + gp.player.hitbox.y;
        eventBus.x = eventCol * gp.tilesize + eventBus.x;
        eventBus.y = eventRow * gp.tilesize + eventBus.y;
        if(gp.player.hitbox.intersects(eventBus)){
            if(gp.player.direction.contentEquals(reqdirection) || reqdirection.contentEquals("any"));
            hit = true;
        }
        gp.player.hitbox.x = gp.player.hitboxdefaultx;
        gp.player.hitbox.y = gp.player.hitboxdefaulty;
        eventBus.x = eventBusDefaultx;
        eventBus.y = eventbusDefaulty;
        return hit;
    }

}
