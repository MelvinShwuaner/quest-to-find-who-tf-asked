package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
public class OBJHeart extends LivingEntity {

    public OBJHeart(MainGame gp){
        super(gp);
        name = "heart";
        image = BufferedRenderer("objects/heart_full", gp.tilesize, gp.tilesize);
        image2 = BufferedRenderer("objects/heart_half", gp.tilesize, gp.tilesize);
        image3 = BufferedRenderer("objects/heart_blank", gp.tilesize, gp.tilesize);
    }
}
