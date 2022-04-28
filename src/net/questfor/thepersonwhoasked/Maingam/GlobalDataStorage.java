package net.questfor.thepersonwhoasked.Maingam;

import java.io.Serializable;

public class GlobalDataStorage implements Serializable {
    //the Main Manager of save and load systems, stores data when loading and saving
    int health;
    int chestplate_ID;
    int leggings_ID;
    int boots_ID;
    int helmet_ID;
    int[] inv;
    int location_ID;
    int level;
    int x;
    int y;

}
