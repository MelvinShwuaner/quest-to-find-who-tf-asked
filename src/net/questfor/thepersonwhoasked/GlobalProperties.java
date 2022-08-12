package net.questfor.thepersonwhoasked;

import static net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs.*;
import net.questfor.thepersonwhoasked.Maingam.*;
import net.questfor.thepersonwhoasked.entities.*;
import net.questfor.thepersonwhoasked.entities.Mobs.*;
import net.questfor.thepersonwhoasked.entities.NPCS.*;
import net.questfor.thepersonwhoasked.entities.Vehicles.*;
import net.questfor.thepersonwhoasked.objects.*;
import net.questfor.thepersonwhoasked.objects.Projectiles.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GlobalProperties {
    /*SETS THE LOCATION OF ENTITIES ON THERE WORLD*/
   public static MainGame gp;

    public  GlobalProperties(MainGame gpp) {
        this.gp = gpp;
    }

    public void setObjectRenderer() {
        obj = new LivingEntity[MainGame.maxmap][100];
        /*RENDER OBJECTS*/
        int i = 4;
        int mapID = 0;
            obj[mapID][1] = new OBJ_COIN_BRONZE(gp);
            obj[mapID][2] = new OBJkey(gp);
            obj[mapID][2].worldx = tilesize * 125;
            obj[mapID][2].worldy = tilesize * 119;
            obj[mapID][3] = new OBJkey(gp);
            obj[mapID][3].worldx = tilesize * 123;
            obj[mapID][3].worldy = tilesize * 119;
            obj[mapID][4] = new OBJ_IRON_AXE(gp);
            obj[mapID][4].worldx = tilesize * 117;
            obj[mapID][4].worldy = tilesize * 120;
            i++;
            obj[mapID][i] = new OBJ_MANA_CRYSTAL(gp);
            obj[mapID][i].worldx = tilesize * 124;
            obj[mapID][i].worldy = tilesize * 123;
        obj[mapID][i].set();
            i++;
            obj[mapID][i] = new OBJHeart(gp);
            obj[mapID][i].worldx = tilesize * 124;
            obj[mapID][i].worldy = tilesize * 121;
           obj[mapID][i].set();
            i++;

            obj[mapID][i] = new chest(gp, 120, 123, 4);
            i++;
            obj[mapID][i] = new Cannon(gp,103, 156, 6, new OBJ_FireBall(gp), "down", 10);
    }
    /*render NPCS and MONSTERS*/
    public void setNPCrenderers() {
        NPCS = new LivingEntity[MainGame.maxmap][10];
        int mapID = 0;
                NPCS[mapID][1] = new Helper(gp);
        NPCS[mapID][1].worldx = tilesize * 121;
        NPCS[mapID][1].worldy = tilesize * 109;
        NPCS[mapID][3] = new BackRoundNpc(gp, 4, "oldman", 106*tilesize, 115*tilesize, 4,121, 165, 20, 2, false, null, false, false, null);
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
        NPCS[mapID][5] = new BackRoundNpc(gp, 4, "oldman", 104*tilesize, 119*tilesize, 4,125, 165, 20, 2, false, null, false, false, null);
        NPCS[mapID][4] = new BackRoundNpc(gp, 4, "oldman", 116*tilesize, 116*tilesize, 4, 121, 170, 20, 2, true, inventory, true, false, dialogues);
        NPCS[mapID][6] = new BackRoundNpc(gp, 4, "oldman", 126*tilesize, 116*tilesize, 4,121, 180, 20, 2, false, null, false, false, null);
        NPCS[mapID][7] = new BackRoundNpc(gp, 4, "oldman", 127*tilesize, 117*tilesize, 4,150, 170, 20, 2, false, null, false, false, null);
        NPCS[mapID][8] = new BackRoundNpc(gp, 4, "oldman", 137*tilesize, 112*tilesize, 4,150, 165, 20, 2, false, null, false, false, null);
        NPCS[mapID][9] = new BackRoundNpc(gp, 4, "oldman", 123*tilesize, 118*tilesize, 4,150, 180, 20, 2, false, null, false, false, null);

    }
    public  void setMonsterRenderers(){
        Monsters = new LivingEntity[MainGame.maxmap][20];
        int mapID = 0;
        Monsters[mapID][0] = new green_slime(gp);
        Monsters[mapID][0].worldx = tilesize*123;
        Monsters[mapID][0].worldy = tilesize*136;
        Monsters[mapID][1] = new green_slime(gp);
        Monsters[mapID][1].worldx = tilesize*121;
        Monsters[mapID][1].worldy = tilesize*136;
        Monsters[mapID][2] = new green_slime(gp);
        Monsters[mapID][2].worldx = tilesize*122;
        Monsters[mapID][2].worldy = tilesize*136;
        Monsters[mapID][3] = new green_slime(gp);
        Monsters[mapID][3].worldx = tilesize*122;
        Monsters[mapID][3].worldy = tilesize*135;

    }
    public void SetRecipes(){
        Recipes[0] = new Recipe();
        Recipes[0].Recipe[0] = new Brick(gp);
        Recipes[0].Recipe[1] = new Brick(gp);
        Recipes[0].Recipe[2] = new Brick(gp);
        Recipes[0].Recipe[3] = new Brick(gp);
        Recipes[0].Recipe[4] = new Brick(gp);
        Recipes[0].Recipe[5] = new Brick(gp);
        Recipes[0].Recipe[6] = new Brick(gp);
        Recipes[0].Recipe[7] = new Brick(gp);
        Recipes[0].Recipe[8] = new Brick(gp); //pls ignore the bad textures, im trash at pixel art (i will get better soon at it)
        Recipes[0].Type = 1;
        Recipes[0].Result = new OBJ_BRICK_WALL(gp);
        Recipes[1] = new Recipe();
        Recipes[1].Recipe[0] = new OBJkey(gp);
        Recipes[1].Recipe[1] = new OBJ_BRICK_WALL(gp);
        Recipes[1].Type = 1;
        Recipes[1].Result = new furnace(gp, 0, 0, 0);
    }

    public void setScreenRenderer() {
        tempscreen = new BufferedImage(MainGame.screenwidth, MainGame.screenheight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempscreen.getGraphics();

    }

    public void setVehicles() {
        Vehicles = new Vehicle[MainGame.maxmap][100];
        int MapID = 0;
        int i = 0;
     Vehicles[MapID][i] = new Vehicle(gp, i, MapID, 95, 100, 155, 162, 5, 7, 8, false, 101, 155); i++;
     Vehicles[MapID][i] = new Vehicle(gp, i, MapID, 95, 100, 155, 162, 5, 7, 8, false, 111, 165);
    }

}
