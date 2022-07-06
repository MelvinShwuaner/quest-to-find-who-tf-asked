package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.Recipe;
import net.questfor.thepersonwhoasked.entities.*;
import net.questfor.thepersonwhoasked.entities.Mobs.*;
import net.questfor.thepersonwhoasked.entities.NPCS.*;
import net.questfor.thepersonwhoasked.entities.Vehicles.Vehicle;
import net.questfor.thepersonwhoasked.objects.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GlobalProperties {
    /*SETS THE LOCATION OF ENTITIES ON THERE WORLD*/
   public static MainGame gp;

    public  GlobalProperties(MainGame gpp) {
        this.gp = gpp;
    }

    public void setObjectRenderer() {
        GlobalGameThreadConfigs.obj = new LivingEntity[MainGame.maxmap][100];
        /*RENDER OBJECTS*/
        int i = 4;
        int mapID = 0;
            GlobalGameThreadConfigs.obj[mapID][1] = new OBJ_COIN_BRONZE(gp);
            GlobalGameThreadConfigs.obj[mapID][2] = new OBJkey(gp);
            GlobalGameThreadConfigs.obj[mapID][2].worldx = GlobalGameThreadConfigs.tilesize * 125;
            GlobalGameThreadConfigs.obj[mapID][2].worldy = GlobalGameThreadConfigs.tilesize * 119;
            GlobalGameThreadConfigs.obj[mapID][3] = new OBJkey(gp);
            GlobalGameThreadConfigs.obj[mapID][3].worldx = GlobalGameThreadConfigs.tilesize * 123;
            GlobalGameThreadConfigs.obj[mapID][3].worldy = GlobalGameThreadConfigs.tilesize * 119;
            GlobalGameThreadConfigs.obj[mapID][4] = new OBJ_IRON_AXE(gp);
            GlobalGameThreadConfigs.obj[mapID][4].worldx = GlobalGameThreadConfigs.tilesize * 117;
            GlobalGameThreadConfigs.obj[mapID][4].worldy = GlobalGameThreadConfigs.tilesize * 120;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_MANA_CRYSTAL(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = GlobalGameThreadConfigs.tilesize * 124;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = GlobalGameThreadConfigs.tilesize * 123;
        GlobalGameThreadConfigs.obj[mapID][i].set();
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJHeart(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = GlobalGameThreadConfigs.tilesize * 124;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = GlobalGameThreadConfigs.tilesize * 121;
           GlobalGameThreadConfigs.obj[mapID][i].set();
            i++;

            GlobalGameThreadConfigs.obj[mapID][i] = new chest(gp, 120, 123, 4);
    }
    /*render NPCS and MONSTERS*/
    public void setNPCrenderers() {
        GlobalGameThreadConfigs.NPCS = new LivingEntity[MainGame.maxmap][10];
        int mapID = 0;
                GlobalGameThreadConfigs.NPCS[mapID][1] = new Helper(gp);
        GlobalGameThreadConfigs.NPCS[mapID][1].worldx = GlobalGameThreadConfigs.tilesize * 121;
        GlobalGameThreadConfigs.NPCS[mapID][1].worldy = GlobalGameThreadConfigs.tilesize * 109;
        GlobalGameThreadConfigs.NPCS[mapID][3] = new BackRoundNpc(gp, 4, "oldman", 106*GlobalGameThreadConfigs.tilesize, 115*GlobalGameThreadConfigs.tilesize, 4,121, 165, 20, 2, false, null, false, false, null);
        ArrayList<LivingEntity> inventory = new ArrayList<>();
        inventory.add(new OBJ_POTION_HEALTH_1(gp));
    inventory.get(0).stacksize = 4;
     inventory.add(new OBJ_IRON_SHOVEL(gp));
        inventory.add(new OBJ_IRON_AXE(gp));
     inventory.add(new OBJ_SHIELD_DIAMOND(gp));
        inventory.add(new OBJ_coal(gp));
       inventory.get(4).stacksize = 30;
       String[] dialogues = new String[9];
       dialogues[0] = "Hello";
        GlobalGameThreadConfigs.NPCS[mapID][5] = new BackRoundNpc(gp, 4, "oldman", 104*GlobalGameThreadConfigs.tilesize, 119*GlobalGameThreadConfigs.tilesize, 4,125, 165, 20, 2, false, null, false, false, null);
        GlobalGameThreadConfigs.NPCS[mapID][4] = new BackRoundNpc(gp, 4, "oldman", 116*GlobalGameThreadConfigs.tilesize, 116*GlobalGameThreadConfigs.tilesize, 4, 121, 170, 20, 2, true, inventory, true, false, dialogues);
        GlobalGameThreadConfigs.NPCS[mapID][6] = new BackRoundNpc(gp, 4, "oldman", 126*GlobalGameThreadConfigs.tilesize, 116*GlobalGameThreadConfigs.tilesize, 4,121, 180, 20, 2, false, null, false, false, null);
        GlobalGameThreadConfigs.NPCS[mapID][7] = new BackRoundNpc(gp, 4, "oldman", 127*GlobalGameThreadConfigs.tilesize, 117*GlobalGameThreadConfigs.tilesize, 4,150, 170, 20, 2, false, null, false, false, null);
        GlobalGameThreadConfigs.NPCS[mapID][8] = new BackRoundNpc(gp, 4, "oldman", 137*GlobalGameThreadConfigs.tilesize, 112*GlobalGameThreadConfigs.tilesize, 4,150, 165, 20, 2, false, null, false, false, null);
        GlobalGameThreadConfigs.NPCS[mapID][9] = new BackRoundNpc(gp, 4, "oldman", 123*GlobalGameThreadConfigs.tilesize, 118*GlobalGameThreadConfigs.tilesize, 4,150, 180, 20, 2, false, null, false, false, null);

    }
    public  void setMonsterRenderers(){
        GlobalGameThreadConfigs.Monsters = new LivingEntity[MainGame.maxmap][20];
        int mapID = 0;
        GlobalGameThreadConfigs.Monsters[mapID][0] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][0].worldx = GlobalGameThreadConfigs.tilesize*123;
        GlobalGameThreadConfigs.Monsters[mapID][0].worldy = GlobalGameThreadConfigs.tilesize*136;
        GlobalGameThreadConfigs.Monsters[mapID][1] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][1].worldx = GlobalGameThreadConfigs.tilesize*121;
        GlobalGameThreadConfigs.Monsters[mapID][1].worldy = GlobalGameThreadConfigs.tilesize*136;
        GlobalGameThreadConfigs.Monsters[mapID][2] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][2].worldx = GlobalGameThreadConfigs.tilesize*122;
        GlobalGameThreadConfigs.Monsters[mapID][2].worldy = GlobalGameThreadConfigs.tilesize*136;
        GlobalGameThreadConfigs.Monsters[mapID][3] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][3].worldx = GlobalGameThreadConfigs.tilesize*122;
        GlobalGameThreadConfigs.Monsters[mapID][3].worldy = GlobalGameThreadConfigs.tilesize*135;

    }
    public void SetRecipes(){
        GlobalGameThreadConfigs.Recipes[0] = new Recipe();
        GlobalGameThreadConfigs.Recipes[0].Recipe[0] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[1] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[2] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[3] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[4] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[5] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[6] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[7] = new Brick(gp);
        GlobalGameThreadConfigs.Recipes[0].Recipe[8] = new Brick(gp); //pls ignore the bad textures, im trash at pixel art (i will get better soon at it)
        GlobalGameThreadConfigs.Recipes[0].Type = 1;
        GlobalGameThreadConfigs.Recipes[0].Result = new OBJ_BRICK_WALL(gp);
        GlobalGameThreadConfigs.Recipes[1] = new Recipe();
        GlobalGameThreadConfigs.Recipes[1].Recipe[0] = new OBJkey(gp);
        GlobalGameThreadConfigs.Recipes[1].Recipe[1] = new OBJ_BRICK_WALL(gp);
        GlobalGameThreadConfigs.Recipes[1].Type = 1;
        GlobalGameThreadConfigs.Recipes[1].Result = new furnace(gp, 0, 0, 0);
    }

    public void setScreenRenderer() {
        GlobalGameThreadConfigs.tempscreen = new BufferedImage(MainGame.screenwidth, MainGame.screenheight, BufferedImage.TYPE_INT_ARGB);
        GlobalGameThreadConfigs.g2 = (Graphics2D)GlobalGameThreadConfigs.tempscreen.getGraphics();

    }

    public void setVehicles() {
        int MapID = 0;
        int i = 0;
     GlobalGameThreadConfigs.Vehicles[MapID][i] = new Vehicle(gp, MapID, 95, 100, 155, 161, 5, 7, 8, true, 98, 159, 98, 160);
      GlobalGameThreadConfigs.player.enterVehcile(i);
    }

}
