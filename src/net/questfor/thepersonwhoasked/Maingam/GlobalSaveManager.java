package net.questfor.thepersonwhoasked.Maingam;

import javax.swing.*;
import java.io.*;

import static net.questfor.thepersonwhoasked.Maingam.UI.gp;
public class GlobalSaveManager {
    public static void saveconfigs() {
        try {
            String filepath = "configs.amogusdababymilkfilestoragethingyidk";
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ConfigStorer configStorer = new ConfigStorer();
            configStorer.downkey = KeyHandler.DOWN;
            configStorer.upkey = KeyHandler.UP;
            configStorer.rightkey = KeyHandler.RIGHT;
            configStorer.leftkey =  KeyHandler.LEFT;
            configStorer.pausekey = KeyHandler.PAUSE;
            configStorer.invkey = KeyHandler.INVENTORY;
            configStorer.openkey = KeyHandler.OPEN;
            configStorer.fpskey = KeyHandler.FPSC;
            configStorer.primepowerkey = KeyHandler.primepowerc;
            configStorer.secpowerkey = KeyHandler.secpowerc;
            configStorer.isfullscreen = gp.FullscreenON;
            configStorer.musicvolume = gp.music.volumescale;
            configStorer.soundvolume = gp.sound.volumescale;
            configStorer.screentext = UI.fullscreentext;
            oos.writeObject(configStorer);
            oos.close();
        }catch (Exception e){
            crash.main(e);
        }
    }
    public static void loadconfigs(){
        try {
            String filepath = "configs.amogusdababymilkfilestoragethingyidk";
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ConfigStorer configStorer = (ConfigStorer) ois.readObject();
            ois.close();
            KeyHandler.OPEN = configStorer.openkey;
            KeyHandler.DOWN = configStorer.downkey;
            KeyHandler.RIGHT = configStorer.rightkey;
            KeyHandler.LEFT = configStorer.leftkey;
            KeyHandler.INVENTORY = configStorer.invkey;
            KeyHandler.PAUSE = configStorer.pausekey;
            KeyHandler.FPSC = configStorer.fpskey;
            KeyHandler.primepowerc = configStorer.primepowerkey;
            KeyHandler.secpowerc = configStorer.secpowerkey;
            MainGame.FullscreenON = configStorer.isfullscreen;
            MainGame.music.volumescale = configStorer.musicvolume;
            MainGame.sound.volumescale = configStorer.soundvolume;
            UI.fullscreentext = configStorer.screentext;
        }catch (Exception e){
            crash.main(e);
        }
    }
    public  void saveplayerworlddata(){
        try {
            String filepath = "Saves/"+GlobalGameThreadConfigs.filepath+".amogusdababymilkfilestoragethingyidk";
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            WorldDataStorage worldDataStorage = new WorldDataStorage();
            worldDataStorage.Monsters = GlobalGameThreadConfigs.Monsters;
            worldDataStorage.NPCS = GlobalGameThreadConfigs.NPCS;
            worldDataStorage.TileEntitys = GlobalGameThreadConfigs.Tentity;
            worldDataStorage.obj = GlobalGameThreadConfigs.obj;
            worldDataStorage.raidcount = EventHandler.raidcounter;
            worldDataStorage.Player = gp.player;
            worldDataStorage.mapdata = gp.tilemanager.mapRendererID;
            worldDataStorage.currentmap = MainGame.currentmap;
            oos.writeObject(worldDataStorage);
            oos.close();
        }catch (Exception e){
            crash.main(e);
        }
    }
    public static void loadplayerworlddata(){
        try {
            String filepath = "Saves/"+GlobalGameThreadConfigs.filepath+".amogusdababymilkfilestoragethingyidk";
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                WorldDataStorage worldDataStorage = (WorldDataStorage) ois.readObject();
                ois.close();
                GlobalGameThreadConfigs.NPCS = worldDataStorage.NPCS;
                GlobalGameThreadConfigs.Monsters = worldDataStorage.Monsters;
                GlobalGameThreadConfigs.Tentity = worldDataStorage.TileEntitys;
                EventHandler.raidcounter = worldDataStorage.raidcount;
                GlobalGameThreadConfigs.obj = worldDataStorage.obj;
                gp.tilemanager.mapRendererID = worldDataStorage.mapdata;
                gp.player = worldDataStorage.Player;
                MainGame.currentmap = worldDataStorage.currentmap;
                for (int a = 0; a < GlobalGameThreadConfigs.obj.length; a++) {
                    for (int i = 0; i < GlobalGameThreadConfigs.obj[a].length; i++) {
                        if (GlobalGameThreadConfigs.obj[a][i] != null) {
                            for (int d = 0; d < GlobalGameThreadConfigs.obj[a][i].inventory.size(); d++) {
                                if (GlobalGameThreadConfigs.obj[a][i].inventory.get(d) != null) {
                                    GlobalGameThreadConfigs.obj[a][i].inventory.get(d).updateimage();
                                }
                            }
                        }
                    }
                }
                for (int a = 0; a < GlobalGameThreadConfigs.NPCS.length; a++) {
                    for (int i = 0; i < GlobalGameThreadConfigs.NPCS[a].length; i++) {
                        if (GlobalGameThreadConfigs.NPCS[a][i] != null) {
                            for (int d = 0; d < GlobalGameThreadConfigs.NPCS[a][i].inventory.size(); d++) {
                                if (GlobalGameThreadConfigs.NPCS[a][i].inventory.get(d) != null) {
                                    GlobalGameThreadConfigs.NPCS[a][i].inventory.get(d).updateimage();
                                }
                            }
                        }
                    }
                }
            }catch(InvalidClassException s){
                crash.main(s);
            }
        } catch (IOException | ClassNotFoundException e){
            GlobalGameThreadConfigs.filepath = null;
            JOptionPane.showMessageDialog(null, "This Save file could not be found! did you make a typo?");
        }
    }
}
