package net.questfor.thepersonwhoasked.World;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
MainGame gp;
BufferedImage darknessFilter;
public Lighting(MainGame gpp){
    this.gp = gpp;
    darknessFilter = new BufferedImage(gp.screenwidth, gp.screenheight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
    Area ScreenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenwidth, gp.screenheight));
    for(int i = 0; i < GlobalGameThreadConfigs.lights[1].length; i++){
        if(GlobalGameThreadConfigs.lights[MainGame.currentmap][i] != null){
            if ((GlobalGameThreadConfigs.lights[MainGame.currentmap][i].worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (GlobalGameThreadConfigs.lights[MainGame.currentmap][i].worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && GlobalGameThreadConfigs.lights[MainGame.currentmap][i].worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (GlobalGameThreadConfigs.lights[MainGame.currentmap][i].worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
            int centerx = (int) ((GlobalGameThreadConfigs.lights[MainGame.currentmap][i].worldx - MainGame.player.worldx + MainGame.player.screenX)+(gp.tilesize/2));
            int centery = (int) ((GlobalGameThreadConfigs.lights[MainGame.currentmap][i].worldy - MainGame.player.worldy + MainGame.player.screenY)+(gp.tilesize/2));
            double x = centerx-(GlobalGameThreadConfigs.lights[MainGame.currentmap][i].size/2);
            double y = centery-(GlobalGameThreadConfigs.lights[MainGame.currentmap][i].size/2);
            Shape circleShape = new Ellipse2D.Double(x, y, GlobalGameThreadConfigs.lights[MainGame.currentmap][i].size, GlobalGameThreadConfigs.lights[MainGame.currentmap][i].size);
            Area lightarea = new Area(circleShape);
            ScreenArea.subtract(lightarea);
         Color[] color = new Color[5];
         float[] fraction = new float[5];
                color[0] = new Color(0, 0, 0, 0);
                color[1] = new Color(0, 0, 0, GlobalGameThreadConfigs.LightLevel/4);
                color[2] = new Color(0, 0, 0, GlobalGameThreadConfigs.LightLevel/3);
                color[3] = new Color(0, 0, 0, GlobalGameThreadConfigs.LightLevel/2);
                color[4] = new Color(0, 0, 0, GlobalGameThreadConfigs.LightLevel);

         fraction[0] = 0;
         fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1;
         RadialGradientPaint gradientPaint = new RadialGradientPaint(centerx, centery, GlobalGameThreadConfigs.lights[MainGame.currentmap][i].size/2, fraction, color);
         g2.setPaint(gradientPaint);
         g2.fill(lightarea);

        }}
    }
    g2.setColor(new Color(0, 0, 0, GlobalGameThreadConfigs.LightLevel));
    g2.fill(ScreenArea);
    g2.dispose();
}
public void draw(Graphics2D g2){
    g2.drawImage(darknessFilter, 0, 0, null);
}
}
