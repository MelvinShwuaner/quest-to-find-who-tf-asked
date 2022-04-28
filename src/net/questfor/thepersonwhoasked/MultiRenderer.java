package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.NPCS.Old_Man;
import net.questfor.thepersonwhoasked.objects.OBJdoor;

public class MultiRenderer {
    static MainGame gp;
    public void Render(MainGame gpp) {
        this.gp = gpp;
    }
    public void setObjectRenderer(){
        gp.obj[0] = new OBJdoor(gp);
        gp.obj[0].worldx = gp.tilesize*21;
        gp.obj[0].worldy = gp.tilesize*22;
        gp.obj[1] = new OBJdoor(gp);
        gp.obj[1].worldx = gp.tilesize*23;
        gp.obj[1].worldy = gp.tilesize*25;

    }
    //render NPCS and MONSTERS
    public void setEntityRenderer(){
         GlobalGameThreadConfigs.NPCS[0] = new Old_Man(gp);
         GlobalGameThreadConfigs.NPCS[0].worldx = gp.tilesize * 21;
         GlobalGameThreadConfigs.NPCS[0].worldy = gp.tilesize * 21;
         GlobalGameThreadConfigs.NPCS[0].worldz = 0;
        GlobalGameThreadConfigs.NPCS[1] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[1].worldx = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[1].worldy = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[1].worldz = 0;
        GlobalGameThreadConfigs.NPCS[2] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[2].worldx = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[2].worldy = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[2].worldz = 0;
        GlobalGameThreadConfigs.NPCS[3] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[3].worldx = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[3].worldy = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[3].worldz = 0;
    }
}
