package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJboots extends LivingEntity {
    public OBJboots(MainGame gp){
        super(gp);
        name = "boots";
        down1 = BufferedRenderer("objects/boots", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
        EntityType = 3;
        description = "fast boots that are usefull when escaping the undead";
        LightSource = false;
    }
    @Override
    public void update() {}
    public LivingEntity replicate() {
        return new OBJboots(gp);
    }
}
