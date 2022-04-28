package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.NPCS.Old_Man;

public class MultiRenderer {
    static MainGame gp;
    public void Render(MainGame gpp) {
        this.gp = gpp;
    }
    //render NPCS and MONSTERS
    public void setEntityRenderer(){
         GlobalGameThreadConfigs.NPCS[0] = new Old_Man(gp);
         GlobalGameThreadConfigs.NPCS[0].worldx = gp.tilesize * 21;
         GlobalGameThreadConfigs.NPCS[0].worldy = gp.tilesize * 21;
         GlobalGameThreadConfigs.NPCS[0].worldz = 0;
    }
}
