package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.FlyingTile;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.StreamSupport;

import static net.questfor.thepersonwhoasked.Maingam.UI.gp;

public class GeneralHandler {
    static int temptile;
    /**is the lead Renderer to handle all the different entites and objects**/
    public static void main(Graphics2D g2) {
        if(GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState){

        for (int i = 0; i < GlobalGameThreadConfigs.projectilelist[1].length; i++) {
            if (GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] != null) {
                if (GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i].alive) {
                    GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i].update();
                }
                if (!GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i].alive) {
                    GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] = null;
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
    }
        Collections.sort(GlobalGameThreadConfigs.entitylist, new Comparator<LivingEntity>() {
            @Override
            public int compare(LivingEntity e1, LivingEntity e2) {
                int result;
                if (e1.worldz == e2.worldz) {
                    result = Integer.compare((int) e1.worldy, (int) e2.worldy);
                }else{
                    result = Integer.compare((int) e1.worldz, (int) e2.worldz);
                }
                return result;
            }
        });
        for(int i = 0; i < GlobalGameThreadConfigs.entitylist.size(); i++) {

            GlobalGameThreadConfigs.entitylist.get(i).draw(g2);
        }
        GlobalGameThreadConfigs.entitylist.clear();
    }
    /**creates a explosion**/
    static int v;
    public static void Explode(int w, int h, int d, int x, int y, int z, int size, int cap, boolean explodetoponly, int immunevehindex) {
        Random random = new Random();
        v = immunevehindex;
        int originalcap = cap;
        for(int depth = d; depth <= d+d+1; depth++){
            if (depth+z-d-1 < 8 && depth+z-d-1 > 0){
                for (int width = w-1; width >= cap; width--) {
                    int i = random.nextInt(100)+1;
                    int ind = destroyVehicleandentitys(width + x - w, y, depth + z - d - 1, 46, null, "left");
                    if(i > 60){
                        if (ind != 999){

                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                         gp,
                                         temptile,
                                         gp.tilemanager.tile[temptile].name,
                                            (width + x - w)*GlobalGameThreadConfigs.tilesize,
                                         y*GlobalGameThreadConfigs.tilesize,
                                         depth + z - d - 1,
                                         10,
                                         -5,
                                         0
                                    );

                                            break;
                                }}
                        }else{
                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                            gp,
                                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1],
                                            gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1]].name,
                                            (width + x - w)*GlobalGameThreadConfigs.tilesize,
                                            y*GlobalGameThreadConfigs.tilesize,
                                            depth + z - d - 1,
                                            10,
                                            -5,
                                            0
                                    );
                                    break;
                                }}
                        }}
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1] = 46;


                }
                for (int width = w+1; width < w+w+1-cap; width++) {
                    int i = random.nextInt(100)+1;
                    int ind = destroyVehicleandentitys(width + x - w, y, depth + z - d - 1, 46, null, "right");
                    if(i > 50){
                        if (ind != 999){
                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                            gp,
                                            temptile,
                                            gp.tilemanager.tile[temptile].name,
                                            (width + x - w)*GlobalGameThreadConfigs.tilesize,
                                            y*GlobalGameThreadConfigs.tilesize,
                                            depth + z - d - 1,
                                            10,
                                            5,
                                            0
                                    );

                                    break;
                                }}
                        }else{
                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                            gp,
                                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1],
                                            gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1]].name,
                                            (width + x - w)*GlobalGameThreadConfigs.tilesize,
                                            y*GlobalGameThreadConfigs.tilesize,
                                            depth + z - d - 1,
                                            10,
                                            5,
                                            0
                                    );
                                    break;
                                }}
                        }}
                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth+z-d-1] = 46;
                }
                for (int height = h-1; height >= cap; height--) {
                    int i = random.nextInt(100)+1;
                    int ind = destroyVehicleandentitys(x,height+y-h, depth + z - d - 1, 46, "up", null);
                    if(i > 60){
                        if (ind != 999){

                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                            gp,
                                            temptile,
                                            gp.tilemanager.tile[temptile].name,
                                            x*GlobalGameThreadConfigs.tilesize,
                                            (height+y-h)*GlobalGameThreadConfigs.tilesize,
                                            depth + z - d - 1,
                                            10,
                                            0,
                                            -5
                                    );

                                    break;
                                }}
                        }else{
                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                            gp,
                                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth + z - d - 1],
                                            gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth + z - d - 1]].name,
                                            x*GlobalGameThreadConfigs.tilesize,
                                            (height+y-h)*GlobalGameThreadConfigs.tilesize,
                                            depth + z - d - 1,
                                            10,
                                            0,
                                            -5
                                    );
                                    break;
                                }}
                        }}
                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth+z-d-1] = 46;
                }

                for (int height = h+1; height < h+h+1-cap; height++) {
                    int i = random.nextInt(100)+1;
                    int ind = destroyVehicleandentitys(x,height+y-h, depth + z - d - 1, 46, "down", null);
                    if(i > 40){
                        if (ind != 999){
                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                            gp,
                                            temptile,
                                            gp.tilemanager.tile[temptile].name,
                                            x*GlobalGameThreadConfigs.tilesize,
                                            (height+y-h)*GlobalGameThreadConfigs.tilesize,
                                            depth + z - d - 1,
                                            10,
                                            5,
                                            0
                                    );

                                    break;
                                }}
                        }else{
                            for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                            gp,
                                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth + z - d - 1],
                                            gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth + z - d - 1]].name,
                                            x*GlobalGameThreadConfigs.tilesize,
                                            (height+y-h)*GlobalGameThreadConfigs.tilesize,
                                            depth + z - d - 1,
                                            10,
                                            5,
                                            0
                                    );
                                    break;
                                }}
                        }}
                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth+z-d-1] = 46;
                }
            }
            cap++;
        }
        for(int depth = 0; depth <= d+d+1; depth++){
                if (depth+z-d-1 < 8 && depth+z-d-1 > 0){
                    boolean shouldexplode = !explodetoponly || depth >= d + 1;
                    if(shouldexplode) {
                        if (depth == 0) {
                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][y][depth + z - d - 1] = 52;
                           destroyVehicleandentitys(x, y, depth + z - d - 1, 52, "destroy",null);
                        } else{
                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][y][depth + z - d - 1] = 46;
                            destroyVehicleandentitys(x, y, depth + z - d - 1, 46, "destroy", null);

                    }

            }}
            }
        int wlength = size+2+w;
        int hindex = 0;
        int wstart = 0;
        //UPPER//
        for(int startz = d+1; startz < d+d+2; startz++){
            int hlength = size+2+h-hindex;
            int hstart = 1;
            //RIGHT
            for(int startw = w+2; startw < wlength; startw++){
                //BOTTOM
                for(int starth = h+2; starth < hlength; starth++){
                    if(starth+y < 200 && startw+x<200 && startz+z-d < 8 && startz+z-d-1 > 0){
                        int i = random.nextInt(100)+1;
                        int ind = destroyVehicleandentitys(startw+x-w-1,starth + y - h - 1, startz+z-d-1, 46, "down", "right");
                        if(i > 45){
                            if (ind != 999){

                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                temptile,
                                                gp.tilemanager.tile[temptile].name,
                                                (startw+x-w-1)*GlobalGameThreadConfigs.tilesize,
                                                (starth+y-h-1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                5
                                        );

                                        break;
                                    }}
                            }else{
                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1],
                                                gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1]].name,
                                                (startw+x-w-1)*GlobalGameThreadConfigs.tilesize,
                                                (starth+y-h-1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                5
                                        );
                                        break;
                                    }}
                            }}
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1] = 46;

                }}
                //TOP
                for(int starth = 0; starth < h-hstart-hindex; starth++){

                    if(starth+y < 200 && startw+x<200 && startz+z-d < 8 && startz+z-d-1 > 0){
                        int i = random.nextInt(100)+1;
                        int ind = destroyVehicleandentitys(startw+x-w-1,y-starth-1, startz+z-d-1, 46,"up", "right");
                        if(i > 40){
                            if (ind != 999){

                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                temptile,
                                                gp.tilemanager.tile[temptile].name,
                                                (startw+x-w-1)*GlobalGameThreadConfigs.tilesize,
                                                (y-starth-1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                -5
                                        );

                                        break;
                                    }}
                            }else{
                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1],
                                                gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1]].name,
                                                (startw+x-w-1)*GlobalGameThreadConfigs.tilesize,
                                                (y-starth-1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                -5
                                        );
                                        break;
                                    }}
                            }}
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][y-starth-1][startz+z-d-1] = 46;
                }}
                hlength--;
                hstart++;
            }
            hlength = size+2+h-hindex;
            hstart = 1;
            //LEFT
            for(int startw = 0; startw < w-wstart; startw++){
                //BOTTOM
                for(int starth = h+2; starth < hlength; starth++) {
                    if (starth + y < 200 && startw + x < 200 && startz + z - d < 8 && startz + z - d - 1 > 0){
                        int i = random.nextInt(100)+1;
                        int ind = destroyVehicleandentitys(x - startw - 1,starth + y - h - 1, startz+z-d-1, 46, "down", "left");
                        if(i > 50){
                            if (ind != 999){

                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                temptile,
                                                gp.tilemanager.tile[temptile].name,
                                                (x - startw - 1)*GlobalGameThreadConfigs.tilesize,
                                                (starth+y-h-1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                5
                                        );

                                        break;
                                    }}
                            }else{
                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1],
                                                gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1]].name,
                                                (x - startw - 1)*GlobalGameThreadConfigs.tilesize,
                                                (starth+y-h-1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                5
                                        );
                                        break;
                                    }}
                            }}
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][x - startw - 1][starth + y - h - 1][startz + z - d - 1] = 46;
                }
                }
                //TOP
                for(int starth = 0; starth < h-hstart-hindex; starth++) {

                    if (starth + y < 200 && startw + x < 200 && startz + z - d < 8 && startz + z - d - 1 > 0){
                        int i = random.nextInt(100)+1;
                        int ind = destroyVehicleandentitys(x - startw - 1,y - starth - 1, startz+z-d-1, 46,"up", "left");
                        if(i > 39){
                            if (ind != 999){

                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                temptile,
                                                gp.tilemanager.tile[temptile].name,
                                                (x - startw - 1)*GlobalGameThreadConfigs.tilesize,
                                                (y - starth - 1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                5
                                        );

                                        break;
                                    }}
                            }else{
                                for(int mapblock = 0; mapblock < GlobalGameThreadConfigs.obj[0].length; mapblock++){
                                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] == null){
                                        GlobalGameThreadConfigs.obj[MainGame.currentmap][mapblock] = new FlyingTile(
                                                gp,
                                                MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1],
                                                gp.tilemanager.tile[MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1]].name,
                                                (x - startw - 1)*GlobalGameThreadConfigs.tilesize,
                                                (y - starth - 1)*GlobalGameThreadConfigs.tilesize,
                                                startz+z-d-1,
                                                10,
                                                5,
                                                5
                                        );
                                        break;
                                    }}
                            }}
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][x - startw - 1][y - starth - 1][startz + z - d - 1] = 46;
                }
                }
                hlength--;
                hstart++;
            }
            wstart++;
            wlength--;
            hindex++;
        }
        //BELOW//
        if(!explodetoponly){
        cap = originalcap;
        for(int depth = d; depth >0; depth--){
            if (depth+z-d-1 < 8 && depth+z-d-1 > 0){
                for (int width = w-1; width >= cap; width--) {
                    int i = random.nextInt(100) + 1;
                    if (i > 80){
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1] = 46;
                        destroyVehicleandentitys(width + x - w, y, depth + z - d - 1, 46, null, "left");
                    }else{
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1] = 52;
                        destroyVehicleandentitys(width + x - w, y, depth + z - d - 1, 52, null, "left");
                    }
                }
                for (int width = w+1; width < w+w+1-cap; width++) {
                    int i = random.nextInt(100) + 1;
                    if (i > 80) {
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1] = 46;
                        destroyVehicleandentitys(width + x - w, y, depth + z - d - 1, 46, null, "right");
                    }else{
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][width + x - w][y][depth + z - d - 1] = 52;
                        destroyVehicleandentitys(width + x - w, y, depth + z - d - 1, 52, null, "right");

                    }
                }
                for (int height = h-1; height >= cap; height--) {
                    int i = random.nextInt(100) + 1;
                    if (i > 80) {
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth+z-d-1] = 46;
                        destroyVehicleandentitys(x, height+y-h, depth + z - d - 1, 46, "up", null);
                    }else{
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth+z-d-1] = 52;
                        destroyVehicleandentitys(x, height+y-h, depth + z - d - 1, 52, "up", null);
                    }
                }
                for (int height = h+1; height < h+h+1-cap; height++) {
                    int i = random.nextInt(100) + 1;
                    if (i > 80) {
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth+z-d-1] = 46;
                        destroyVehicleandentitys(x, height+y-h, depth + z - d - 1, 46, "dowwn", null);
                    }else{
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][x][height+y-h][depth+z-d-1] = 52;
                        destroyVehicleandentitys(x, height+y-h, depth + z - d - 1, 52, "down", null);
                    }                }
            }
            cap++;
        }
         wlength = size+2+w;
         hindex = 0;
         wstart = 0;
        for(int startz = d; startz > 0; startz--){
            int hlength = size+2+h-hindex;
            int hstart = 1;
            //RIGHT
            for(int startw = w+2; startw < wlength; startw++){
                //BOTTOM
                for(int starth = h+2; starth < hlength; starth++){
                    if(starth+y < 200 && startw+x<200 && startz+z-d < 8 && startz+z-d-1 > 0){
                        int i = random.nextInt(100) + 1;
                    if (i > 50){
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1] = 46;
                        destroyVehicleandentitys(startw+x-w-1, starth+y-h-1, startz+z-d-1, 46, "down", "right");
                }else{
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][starth+y-h-1][startz+z-d-1] = 52;
                        destroyVehicleandentitys(startw+x-w-1, starth+y-h-1, startz+z-d-1, 52,"down", "right");
                    }
                    }}
                //TOP
                for(int starth = 0; starth < h-hstart-hindex; starth++){
                    if(starth+y < 200 && startw+x<200 && startz+z-d-1 < 8 && startz+z-d-1 > 0){
                    int i = random.nextInt(100) + 1;
                    if (i > 50){
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][y-starth-1][startz+z-d-1] = 46;
                        destroyVehicleandentitys(startw+x-w-1, starth+y-h-1, startz+z-d-1, 46,"up", "right");
                    }else{
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][startw+x-w-1][y-starth-1][startz+z-d-1] = 52;
                        destroyVehicleandentitys(startw+x-w-1, starth+y-h-1, startz+z-d-1, 52,"up", "right");
                    }
                }}
                hlength--;
                hstart++;
            }
            hlength = size+2+h-hindex;
            hstart = 1;
            //LEFT
            for(int startw = 0; startw < w-wstart; startw++){
                //BOTTOM
                for(int starth = h+2; starth < hlength; starth++){
                    if(starth+y < 200 && startw+x<200 && startz+z-d-1 < 8 && startz+z-d-1 > 0){
                        int i = random.nextInt(100) + 1;
                        if (i > 50){
                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x-startw-1][starth+y-h-1][startz+z-d-1] = 46;
                            destroyVehicleandentitys(x-startw-1, starth+y-h-1, startz+z-d-1, 46,"down", "left");
                        }else{
                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x-startw-1][starth+y-h-1][startz+z-d-1] = 52;
                            destroyVehicleandentitys(x-startw-1, starth+y-h-1, startz+z-d-1, 52,"down", "left");
                        }

                }}
                //TOP
                for(int starth = 0; starth < h-hstart-hindex; starth++){
                    if(starth+y < 200 && startw+x<200 && startz+z-d-1 < 8 && startz+z-d-1 > 0){
                        int i = random.nextInt(100) + 1;
                        if (i > 50){
                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x-startw-1][y-starth-1][startz+z-d-1] = 46;
                            destroyVehicleandentitys(x-startw-1, y-starth-1, startz+z-d-1, 46,"up", "left");
                        }else{
                            MainGame.tilemanager.mapRendererID[MainGame.currentmap][x-startw-1][y-starth-1][startz+z-d-1] = 52;
                            destroyVehicleandentitys(x-startw-1, y-starth-1, startz+z-d-1, 52,"up", "left");
                        }

                    }
                }
                hlength--;
                hstart++;
            }
            wstart++;
            wlength--;
            hindex++;
        }
        }
    }
    /**destroyes a block inside of a vehicle at specified cordinates, and damages entitys if they are in those coordinates as well**/
    public static int destroyVehicleandentitys(int x, int y, int z, int tile, String direction1, String direction2){
        int vindex = 999;
        try {
            for (int index = 0; index < GlobalGameThreadConfigs.Vehicles[0].length; index++) {
                if (GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index] != null) {
                    if (((x) - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx / GlobalGameThreadConfigs.tilesize)) >= 0 && ((x) - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx / GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].width
                            && ((y - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy / GlobalGameThreadConfigs.tilesize)) >= 0 && (y - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy / GlobalGameThreadConfigs.tilesize)) <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].height)
                            && (z >= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz && z <= GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz + GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].depth)
                    ) {
                        if(index != v){

                        temptile = GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].tiles[(int) (x - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx / GlobalGameThreadConfigs.tilesize))][(int) (y - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy / GlobalGameThreadConfigs.tilesize))][(int) (z - GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz)];
                        if(                        GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].tiles[(int) (x - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx / GlobalGameThreadConfigs.tilesize))][(int) (y - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy / GlobalGameThreadConfigs.tilesize))][(int) (z - GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz)] == 54){
                            GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].engines--;
                        }
                        GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].tiles[(int) (x - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldx / GlobalGameThreadConfigs.tilesize))][(int) (y - Math.round(GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldy / GlobalGameThreadConfigs.tilesize))][(int) (z - GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][index].worldz)] = tile;
                        vindex = index;
                    }}
                    {
                    }
                }
            }
            if(Math.round(GlobalGameThreadConfigs.player.worldx/GlobalGameThreadConfigs.tilesize) == x && Math.round(GlobalGameThreadConfigs.player.worldy/GlobalGameThreadConfigs.tilesize) == y && GlobalGameThreadConfigs.player.worldz == z){
                GlobalGameThreadConfigs.player.dealKnockbacktome(direction1, direction2, 60);
                if(GlobalGameThreadConfigs.player.controlling || GlobalGameThreadConfigs.player.passanger) {
                    GlobalGameThreadConfigs.player.passanger = false;
                    GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][GlobalGameThreadConfigs.player.vehindex].passengers.remove(GlobalGameThreadConfigs.player);

                    GlobalGameThreadConfigs.player.vehindex = 999;
                    if (GlobalGameThreadConfigs.player.controlling){
                        GlobalGameThreadConfigs.player.controlling = false;
                        GlobalGameThreadConfigs.Vehicles[MainGame.currentmap][GlobalGameThreadConfigs.player.vehindex].controller.remove(0);
                    }
                }
                GlobalGameThreadConfigs.player.worldz+= 3;
                if(GlobalGameThreadConfigs.player.worldz>= 8){
                    GlobalGameThreadConfigs.player.worldz = 7;
                }
            }
        } catch (Exception ignored){

        }
        return vindex;
    }
}
