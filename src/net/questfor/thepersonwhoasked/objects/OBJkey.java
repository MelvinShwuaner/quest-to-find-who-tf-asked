package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.imageio.ImageIO;

public class OBJkey extends OBJObject {
    public OBJkey(){
        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            utool.scaleimage(image, MainGame.tilesize, MainGame.tilesize);
        }catch(Exception exx){
            crash.main(exx);
        }
    }
}
