package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
public class chest extends LivingEntity {
    public chest(MainGame gp){
        super(gp);
        name = "chest";
        down1 = BufferedRenderer("objects/chest.png");
    }
}
