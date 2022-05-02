package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.Mobs.green_slime;
import net.questfor.thepersonwhoasked.entities.NPCS.Old_Man;
import net.questfor.thepersonwhoasked.objects.*;
import net.questfor.thepersonwhoasked.tile_entites.IT_tree;

public class MultiRenderer {
    /*SETS THE LOCATION OF ENTITIES ON THERE WORLD*/
    static MainGame gp;
    public void Render(MainGame gpp) {
        this.gp = gpp;
    }
    public void setObjectRenderer(){
        /*RENDER OBJECTS*/
        gp.obj[0] = new chest(gp);
        gp.obj[0].worldx = gp.tilesize*21;
        gp.obj[0].worldy = gp.tilesize*22;
        gp.obj[0].inventory.add(new OBJkey(gp));
        gp.obj[1] = new OBJdoor(gp);
        gp.obj[1].worldx = gp.tilesize*23;
        gp.obj[1].worldy = gp.tilesize*25;
        gp.obj[2] = new OBJkey(gp);
        gp.obj[2].worldx = gp.tilesize*25;
        gp.obj[2].worldy = gp.tilesize*19;
        gp.obj[3] = new OBJkey(gp);
        gp.obj[3].worldx = gp.tilesize*23;
        gp.obj[3].worldy = gp.tilesize*19;
        gp.obj[4] = new OBJ_IRON_AXE(gp);
        gp.obj[4].worldx = gp.tilesize*33;
        gp.obj[4].worldy = gp.tilesize*21;
        gp.obj[5] = new OBJ_IRON_SWORD(gp);
        gp.obj[5].worldx = gp.tilesize*32;
        gp.obj[5].worldy = gp.tilesize*21;
        int i = 6;
        gp.obj[i] = new OBJ_IRON_SWORD(gp);
        gp.obj[i].worldx = gp.tilesize*35;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
        gp.obj[i] = new OBJdoor(gp);
        gp.obj[i].worldx = gp.tilesize*31;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
        gp.obj[i] = new OBJ_IRON_SWORD(gp);
        gp.obj[i].worldx = gp.tilesize*30;
        gp.obj[i].worldy = gp.tilesize*23;
        gp.obj[i] = new OBJdoor(gp);
        gp.obj[i].worldx = gp.tilesize*29;
        gp.obj[i].worldy = gp.tilesize*38;
        i++;
        gp.obj[i] = new OBJdoor(gp);
        gp.obj[i].worldx = gp.tilesize*40;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
        gp.obj[i] = new OBJ_MANA_CRYSTAL(gp);
        gp.obj[i].worldx = gp.tilesize*24;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
        gp.obj[i] = new OBJ_IRON_AXE(gp);
        gp.obj[i].worldx = gp.tilesize*25;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
        gp.obj[i] = new OBJ_COIN_BRONZE(gp);
        gp.obj[i].worldx = gp.tilesize*26;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
        gp.obj[i] = new OBJHeart(gp);
        gp.obj[i].worldx = gp.tilesize*24;
        gp.obj[i].worldy = gp.tilesize*21;
        i++;
        gp.obj[i] = new OBJ_SHIELD_DIAMOND(gp);
        gp.obj[i].worldx = gp.tilesize*24;
        gp.obj[i].worldy = gp.tilesize*22;
        i++;
        gp.obj[i] = new chest(gp);
        gp.obj[i].worldx = gp.tilesize*21;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
        gp.obj[i] = new OBJ_POTION_HEALTH_1(gp);
        gp.obj[i].worldx = gp.tilesize*20;
        gp.obj[i].worldy = gp.tilesize*23;
        i++;
    }
    /*render NPCS and MONSTERS*/
    public void setNPCrenderers(){
        GlobalGameThreadConfigs.NPCS[0] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[0].worldx = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[0].worldy = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[0].worldz = 0;
        GlobalGameThreadConfigs.NPCS[1] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[1].worldx = gp.tilesize * 22;
        GlobalGameThreadConfigs.NPCS[1].worldy = gp.tilesize * 22;
        GlobalGameThreadConfigs.NPCS[1].worldz = 0;
        GlobalGameThreadConfigs.NPCS[2] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[2].worldx = gp.tilesize * 23;
        GlobalGameThreadConfigs.NPCS[2].worldy = gp.tilesize * 23;
        GlobalGameThreadConfigs.NPCS[2].worldz = 0;
        GlobalGameThreadConfigs.NPCS[3] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[3].worldx = gp.tilesize * 24;
        GlobalGameThreadConfigs.NPCS[3].worldy = gp.tilesize * 24;
        GlobalGameThreadConfigs.NPCS[3].worldz = 0;
    }
    public  void setMonsterRenderers(){
        GlobalGameThreadConfigs.Monsters[0] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[0].worldx = gp.tilesize*23;
        GlobalGameThreadConfigs.Monsters[0].worldy = gp.tilesize*36;
        GlobalGameThreadConfigs.Monsters[0].worldz = 0;
        GlobalGameThreadConfigs.Monsters[1] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[1].worldx = gp.tilesize*21;
        GlobalGameThreadConfigs.Monsters[1].worldy = gp.tilesize*36;
        GlobalGameThreadConfigs.Monsters[2] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[2].worldx = gp.tilesize*22;
        GlobalGameThreadConfigs.Monsters[2].worldy = gp.tilesize*36;
        GlobalGameThreadConfigs.Monsters[3] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[3].worldx = gp.tilesize*22;
        GlobalGameThreadConfigs.Monsters[3].worldy = gp.tilesize*35;
        GlobalGameThreadConfigs.Monsters[4] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[4].worldx = gp.tilesize*22;
        GlobalGameThreadConfigs.Monsters[4].worldy = gp.tilesize*34;
        GlobalGameThreadConfigs.Monsters[5] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[5].worldx = gp.tilesize*24;
        GlobalGameThreadConfigs.Monsters[5].worldy = gp.tilesize*35;
    }
    public void setTileEntityRenderers(){int i = 0;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 27, 12);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 28, 12);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 29, 12);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 30, 12);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 31, 12);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 32, 12);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 33, 12);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 26, 20);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 26, 21);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 26, 22);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 20, 20);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 20, 21);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 20, 22);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 22, 24);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 23, 24);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 24, 24);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 22, 18);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 23, 18);i++;
        GlobalGameThreadConfigs.Tentity[i] = new IT_tree(gp, 24, 18);i++;

    }
}
