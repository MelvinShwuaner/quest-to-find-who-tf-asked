package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.crash;

import javax.imageio.ImageIO;

public class OBJHeart extends OBJObject{
    public OBJHeart(){
        name = "heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image = utool.scaleimage(image, MainGame.tilesize, MainGame.tilesize);
            image2 = utool.scaleimage(image2, MainGame.tilesize, MainGame.tilesize);
            image3 = utool.scaleimage(image3, MainGame.tilesize, MainGame.tilesize);
        }catch(Exception exx){
            crash.main(exx);
        }
    }
}
