package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

public class GeneralHandler {
    public static void main(Graphics2D g2) {
        //is the lead Renderer to handle all the different entites and objects.
        for (int i = 0; i < GlobalGameThreadConfigs.projectilelist.size(); i++) {
            if (GlobalGameThreadConfigs.projectilelist.get(i) != null) {
                if (GlobalGameThreadConfigs.projectilelist.get(i).alive) {
                    GlobalGameThreadConfigs.projectilelist.get(i).update();
                }
                if (!GlobalGameThreadConfigs.projectilelist.get(i).alive) {
                    GlobalGameThreadConfigs.projectilelist.remove(i);
                }
            }
        }
        for (int i = 0; i < GlobalGameThreadConfigs.particleList.size(); i++) {
            if (GlobalGameThreadConfigs.particleList.get(i) != null) {
                if (GlobalGameThreadConfigs.particleList.get(i).alive) {
                    GlobalGameThreadConfigs.particleList.get(i).update();
                }
                if (!GlobalGameThreadConfigs.particleList.get(i).alive) {
                    GlobalGameThreadConfigs.particleList.remove(i);
                }
            }
        }

        Collections.sort(GlobalGameThreadConfigs.entitylist, new Comparator<LivingEntity>() {
            @Override
            public int compare(LivingEntity e1, LivingEntity e2) {
                int result = Integer.compare((int) e1.worldy, (int) e2.worldy);
                return result;
            }
        });
        for(int i = 0; i < GlobalGameThreadConfigs.entitylist.size(); i++){
            GlobalGameThreadConfigs.entitylist.get(i).draw(g2);
        }
        GlobalGameThreadConfigs.entitylist.clear();
    }
}
