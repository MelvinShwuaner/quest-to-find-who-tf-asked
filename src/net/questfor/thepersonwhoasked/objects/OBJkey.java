package net.questfor.thepersonwhoasked.objects;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
public class OBJkey extends LivingEntity {
    public OBJkey(MainGame gp){
        super(gp);
        name = "key";
        down1 = BufferedRenderer("/entities/objects/key.png");
    }
}
