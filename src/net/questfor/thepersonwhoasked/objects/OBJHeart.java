package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
public class OBJHeart extends LivingEntity {

    public OBJHeart(MainGame gp){
        super(gp);
        name = "heart";
        image = BufferedRenderer("objects/heart_full");
        image2 = BufferedRenderer("objects/heart_half");
        image3 = BufferedRenderer("objects/heart_blank");
    }
}
