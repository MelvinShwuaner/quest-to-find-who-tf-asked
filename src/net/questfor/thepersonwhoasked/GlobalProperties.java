package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.Recipe;
import net.questfor.thepersonwhoasked.entities.*;
import net.questfor.thepersonwhoasked.entities.Mobs.*;
import net.questfor.thepersonwhoasked.entities.NPCS.*;
import net.questfor.thepersonwhoasked.objects.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GlobalProperties {
    /*SETS THE LOCATION OF ENTITIES ON THERE WORLD*/
   public static MainGame gp;

    public  GlobalProperties(MainGame gpp) {
        this.gp = gpp;
    }

    public void setObjectRenderer() {
        GlobalGameThreadConfigs.obj = new LivingEntity[MainGame.maxmap][100];
        /*RENDER OBJECTS*/
        int i = 6;
        int mapID = 0;
            GlobalGameThreadConfigs.obj[mapID][1] = new OBJ_COIN_BRONZE(gp);
            GlobalGameThreadConfigs.obj[mapID][2] = new OBJkey(gp);
            GlobalGameThreadConfigs.obj[mapID][2].worldx = gp.tilesize * 125;
            GlobalGameThreadConfigs.obj[mapID][2].worldy = gp.tilesize * 119;
            GlobalGameThreadConfigs.obj[mapID][3] = new OBJkey(gp);
            GlobalGameThreadConfigs.obj[mapID][3].worldx = gp.tilesize * 123;
            GlobalGameThreadConfigs.obj[mapID][3].worldy = gp.tilesize * 119;
            GlobalGameThreadConfigs.obj[mapID][4] = new OBJ_IRON_AXE(gp);
            GlobalGameThreadConfigs.obj[mapID][4].worldx = gp.tilesize * 117;
            GlobalGameThreadConfigs.obj[mapID][4].worldy = gp.tilesize * 120;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_MANA_CRYSTAL(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 124;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 123;
        GlobalGameThreadConfigs.obj[mapID][i].set();
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJHeart(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 124;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 121;
           GlobalGameThreadConfigs.obj[mapID][i].set();
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new chest(gp, 121, 123, 4);

            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_POTION_HEALTH_1(gp));
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_POTION_HEALTH_1(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 120;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 123;
        GlobalGameThreadConfigs.obj[mapID][i] = new furnace(gp, 121, 124, 4);
            i = 0;
            mapID = 1;
        GlobalGameThreadConfigs.obj[mapID][i] = new chest(gp, 111, 108, 4);
        GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 111;
        GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 108;
        GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJkey(gp));
        GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_IRON_AXE(gp));
        GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));

    }

    /*render NPCS and MONSTERS*/
    public void setNPCrenderers() {
        GlobalGameThreadConfigs.NPCS = new LivingEntity[MainGame.maxmap][10];
        int mapID = 0;
            GlobalGameThreadConfigs.NPCS[mapID][0] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[mapID][0].worldx = gp.tilesize * 121;
        GlobalGameThreadConfigs.NPCS[mapID][0].worldy = gp.tilesize * 121;
        GlobalGameThreadConfigs.NPCS[mapID][1] = new Helper(gp);
        GlobalGameThreadConfigs.NPCS[mapID][1].worldx = gp.tilesize * 122;
        GlobalGameThreadConfigs.NPCS[mapID][1].worldy = gp.tilesize * 122;
        GlobalGameThreadConfigs.NPCS[mapID][2] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[mapID][2].worldx = gp.tilesize * 123;
        GlobalGameThreadConfigs.NPCS[mapID][2].worldy = gp.tilesize * 123;
        GlobalGameThreadConfigs.NPCS[mapID][3] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[mapID][3].worldx = gp.tilesize * 111;
        GlobalGameThreadConfigs.NPCS[mapID][3].worldy = gp.tilesize * 19;
        GlobalGameThreadConfigs.NPCS[mapID][3].dialogues[1] = "Hello. its a nice day \n outside isnt it?";
        GlobalGameThreadConfigs.NPCS[mapID][3].dialogues[2] = "You should go to the main square,\n were all going to be there";
        GlobalGameThreadConfigs.NPCS[mapID][3].dialogues[3] = "OK bye!";
        GlobalGameThreadConfigs.NPCS[mapID][3].direction = "right";
        GlobalGameThreadConfigs.NPCS[mapID][3].speed = 0;
        GlobalGameThreadConfigs.NPCS[mapID][3].frozen = true; mapID = 0;
            GlobalGameThreadConfigs.NPCS[mapID][0] = new Mysterious_trader(gp);
            GlobalGameThreadConfigs.NPCS[mapID][0].worldx = gp.tilesize * 112;
            GlobalGameThreadConfigs.NPCS[mapID][0].worldy = gp.tilesize * 106;
            GlobalGameThreadConfigs.NPCS[mapID][0].speed = 0;
            GlobalGameThreadConfigs.NPCS[mapID][0].frozen = true;
        GlobalGameThreadConfigs.NPCS[mapID][0].inventory.add(new OBJ_POTION_HEALTH_1(gp));
        GlobalGameThreadConfigs.NPCS[mapID][0].inventory.get(0).stacksize = 4;
        GlobalGameThreadConfigs.NPCS[mapID][0].inventory.add(new OBJ_IRON_SHOVEL(gp));
        GlobalGameThreadConfigs.NPCS[mapID][0].inventory.add(new OBJ_IRON_AXE(gp));
        GlobalGameThreadConfigs.NPCS[mapID][0].inventory.add(new OBJ_SHIELD_DIAMOND(gp));
        GlobalGameThreadConfigs.NPCS[mapID][0].inventory.add(new OBJ_coal(gp));
        GlobalGameThreadConfigs.NPCS[mapID][0].inventory.get(4).stacksize = 30;
}
    public  void setMonsterRenderers(){
        GlobalGameThreadConfigs.Monsters = new LivingEntity[MainGame.maxmap][20];
        int mapID = 0;
        GlobalGameThreadConfigs.Monsters[mapID][0] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][0].worldx = gp.tilesize*123;
        GlobalGameThreadConfigs.Monsters[mapID][0].worldy = gp.tilesize*136;
        GlobalGameThreadConfigs.Monsters[mapID][1] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][1].worldx = gp.tilesize*121;
        GlobalGameThreadConfigs.Monsters[mapID][1].worldy = gp.tilesize*136;
        GlobalGameThreadConfigs.Monsters[mapID][2] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][2].worldx = gp.tilesize*122;
        GlobalGameThreadConfigs.Monsters[mapID][2].worldy = gp.tilesize*136;
        GlobalGameThreadConfigs.Monsters[mapID][3] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][3].worldx = gp.tilesize*122;
        GlobalGameThreadConfigs.Monsters[mapID][3].worldy = gp.tilesize*135;
        GlobalGameThreadConfigs.Monsters[mapID][4] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][4].worldx = gp.tilesize*122;
        GlobalGameThreadConfigs.Monsters[mapID][4].worldy = gp.tilesize*134;
        GlobalGameThreadConfigs.Monsters[mapID][5] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][5].worldx = gp.tilesize*124;
        GlobalGameThreadConfigs.Monsters[mapID][5].worldy = gp.tilesize*135;
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

}
