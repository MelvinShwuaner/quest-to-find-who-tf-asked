package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;
public class Recipe extends Data{
    public int Type = 0; //1 for crafting, 2 for brewing
    public LivingEntity[] Recipe = new LivingEntity[9];
    public LivingEntity Result;
}
