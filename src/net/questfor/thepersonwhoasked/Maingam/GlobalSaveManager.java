package net.questfor.thepersonwhoasked.Maingam;
import java.io.*;
import static net.questfor.thepersonwhoasked.Maingam.UI.gp;
public class GlobalSaveManager implements Serializable {
    public static void saveconfigs() {
        try {
            String filepath = "configs.amogusdababymilkfilestoragethingyidk";
            FileOutputStream fos = new FileOutputStream(filepath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
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
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
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
            String filepath = "data.amogusdababymilkfilestoragethingyidk";
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            GlobalDataStorage gd = new GlobalDataStorage();
            gd.health = gp.player.health;
            gd.inventory = MainGame.player.inventory;
            gd.level = gp.player.level;
            gd.x = (int) gp.player.worldx;
            gd.y = (int) gp.player.worldy;
            gd.currentshield = MainGame.player.currentshield;
            gd.currentweapon = MainGame.player.currentweapon;
            oos.writeObject(gd);
            oos.close();
            filepath = "world.amogusdababymilkfilestoragethingyidk";
            fos = new FileOutputStream(filepath);
            oos = new ObjectOutputStream(fos);
            WorldDataStorage worldDataStorage = new WorldDataStorage();
            worldDataStorage.Monsters = GlobalGameThreadConfigs.Monsters;
            worldDataStorage.NPCS = GlobalGameThreadConfigs.NPCS;
            worldDataStorage.TileEntitys = GlobalGameThreadConfigs.Tentity;
            oos.writeObject(worldDataStorage);
            oos.close();
        }catch (Exception e){
            crash.main(e);
        }
    }
    public static void loadplayerworlddata(){
        try {
            String filepath = "world.amogusdababymilkfilestoragethingyidk";
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            WorldDataStorage worldDataStorage = (WorldDataStorage) ois.readObject();
            ois.close();
            GlobalGameThreadConfigs.NPCS = worldDataStorage.NPCS;
            GlobalGameThreadConfigs.Monsters = worldDataStorage.Monsters;
            GlobalGameThreadConfigs.Tentity = worldDataStorage.TileEntitys;
             filepath = "data.amogusdababymilkfilestoragethingyidk";
             fis = new FileInputStream(filepath);
             ois = new ObjectInputStream(fis);
             GlobalDataStorage globalDataStorage = (GlobalDataStorage) ois.readObject();
             ois.close();
             gp.player.inventory = globalDataStorage.inventory;
             gp.player.health = globalDataStorage.health;
             gp.player.worldx = globalDataStorage.x;
             gp.player.worldy = globalDataStorage.y;
             gp.player.currentshield = globalDataStorage.currentshield;
             gp.player.currentweapon = globalDataStorage.currentweapon;
        } catch (Exception e){
                crash.main(e);
            }

    }
}
