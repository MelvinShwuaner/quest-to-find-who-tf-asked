package net.questfor.thepersonwhoasked.World;

import net.questfor.thepersonwhoasked.Maingam.MainGame;

import java.awt.*;

public class enviormentmanager {
    MainGame gp;
    Lighting lighting;
    public enviormentmanager(MainGame gpp){
        this.gp = gpp;

    }
    public void setup(){
        lighting = new Lighting(gp);
    }
    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}
