package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
public class OBJboots extends LivingEntity {
    public OBJboots(MainGame gp){
        super(gp);
        name = "boots";
        down1 = BufferedRenderer("objects/boots", gp.tilesize, gp.tilesize);
        EntityType = 3;
        description = "fast boots that are usefull when escaping the undead";
    }
}
