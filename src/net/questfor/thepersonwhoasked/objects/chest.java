package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.imageio.ImageIO;

public class chest extends OBJObject{
    public chest(){
        name = "chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            utool.scaleimage(image, MainGame.tilesize, MainGame.tilesize);
            collision = true;
        }catch(Exception exx){
            crash.main(exx);
        }
    }
}
