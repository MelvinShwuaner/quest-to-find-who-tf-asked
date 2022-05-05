package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.OBJ_IRON_SWORD;

import java.io.Serializable;
import java.util.ArrayList;

public class GlobalDataStorage extends Data implements Serializable {
    //the Main Manager of save and load systems, stores data when loading and saving
    int health;
    int chestplate_ID;
    int leggings_ID;
    int boots_ID;
    int helmet_ID;
    ArrayList inventory;
    String world;
    int level;
    int x;
    int y;
    LivingEntity currentweapon;
    LivingEntity currentshield;

}
