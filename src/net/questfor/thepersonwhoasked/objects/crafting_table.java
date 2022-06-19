package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.KeyHandler;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;

public class crafting_table extends LivingEntity {
    public crafting_table(MainGame gp, int col, int row, double layer){
        super(gp);
        name = "crafting table";
        getImageInstance();
        EntityType = 4;
        LightSource = false;
        collision = true;
        description = "is used to craft all sorts of items";
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = GlobalGameThreadConfigs.tilesize;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        maxstacksize = 16;
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);
        inventory.add(null);

        worldx = col*GlobalGameThreadConfigs.tilesize;
        worldy = row*GlobalGameThreadConfigs.tilesize;
        worldz = layer;
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/crafting_table", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    public void open(int x, int y, int z, int i){
        if(KeyHandler.enterpressed){
        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.UIstate;
        UI.npc = this;
        UI.currentUI = "crafting";
    }}
    @Override
    public void update() {if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] == 46){
        gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(worldx/GlobalGameThreadConfigs.tilesize)][(int) Math.round(worldy/GlobalGameThreadConfigs.tilesize)][(int) worldz] = 47;
    }}

    @Override
    public boolean ItemRequirements(LivingEntity SourceEntity) {
        return true;
    }

    @Override
    public LivingEntity replicate() {
        return new crafting_table(gp, 0, 0, 0);
    }

    @Override
    public void Place(double x, double y, double z, int i){
        boolean canplace;
        if(!KeyHandler.CROUCH && !KeyHandler.sprint) {
            switch (GlobalGameThreadConfigs.player.direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
        }else if(KeyHandler.CROUCH){
            z--;
        }else {
            z++;
        }
        canplace = (gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z));
        if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)){
            switch (GlobalGameThreadConfigs.player.direction) {
                case "down" -> y += 50;
                case "up" -> y -= 50;
                case "left" -> x -= 50;
                case "right" -> x += 50;
            }
            canplace = gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / GlobalGameThreadConfigs.tilesize), Math.round(y / GlobalGameThreadConfigs.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z);
        }
        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)+1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / GlobalGameThreadConfigs.tilesize)-1, (int) Math.round(y / GlobalGameThreadConfigs.tilesize), (int) z))) {
           GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new crafting_table(gp, (int) Math.round(x / GlobalGameThreadConfigs.tilesize), (int) Math.round(y / GlobalGameThreadConfigs.tilesize), z);
            if(!GlobalGameThreadConfigs.Buildmode){
                GlobalGameThreadConfigs.player.currentshield.stacksize--;
                if (GlobalGameThreadConfigs.player.currentshield.stacksize <= 0) {
                    GlobalGameThreadConfigs.player.inventory.remove(GlobalGameThreadConfigs.player.currentshield);
                    GlobalGameThreadConfigs.player.currentshield = null;
                }
            }
        }
    }
}
