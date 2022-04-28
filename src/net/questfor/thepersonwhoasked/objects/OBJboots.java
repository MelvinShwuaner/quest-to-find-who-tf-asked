package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
public class OBJboots extends LivingEntity {
    public OBJboots(MainGame gp){
        super(gp);
        name = "boots";
        down1 = BufferedRenderer("objects/boots.png");
    }
}
