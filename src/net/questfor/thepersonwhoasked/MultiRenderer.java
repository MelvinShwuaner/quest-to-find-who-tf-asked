package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.Mobs.green_slime;
import net.questfor.thepersonwhoasked.entities.NPCS.Old_Man;
import net.questfor.thepersonwhoasked.objects.OBJdoor;

public class MultiRenderer {
    static MainGame gp;
    public void Render(MainGame gpp) {
        this.gp = gpp;
    }
    public void setObjectRenderer(){
        /*RENDER OBJECTS*/
        gp.obj[0] = new OBJdoor(gp);
        gp.obj[0].worldx = gp.tilesize*21;
        gp.obj[0].worldy = gp.tilesize*22;
        gp.obj[1] = new OBJdoor(gp);
        gp.obj[1].worldx = gp.tilesize*23;
        gp.obj[1].worldy = gp.tilesize*25;

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
    public void setMonsterRenderers(){
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
}
