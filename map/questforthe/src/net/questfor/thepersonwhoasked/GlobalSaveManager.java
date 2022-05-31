package net.questfor.thepersonwhoasked;
import java.io.*;

public class GlobalSaveManager {
    public static void saveplayerworlddata(){
        try {
            String filepath = "Saves/"+Main.filepath+".amogusdababymilkfilestoragethingyidk";
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Main.map);
            oos.close();
        }catch (Exception e){
        }
    }

}
