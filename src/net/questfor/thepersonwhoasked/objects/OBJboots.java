package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.imageio.ImageIO;

public class OBJboots extends OBJObject {
    public OBJboots(){
        name = "boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            utool.scaleimage(image, MainGame.tilesize, MainGame.tilesize);
        }catch(Exception exx){
            crash.main(exx);
        }
    }
}
