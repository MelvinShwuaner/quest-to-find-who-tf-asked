package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class OBJ_COIN_BRONZE extends LivingEntity {
    public OBJ_COIN_BRONZE(MainGame gpp) {
        super(gpp);
        Type = Type_Current;
        LightSource = false;
        EntityType = 3;
        name = "Bronze coin";
        getImageInstance();
        Value = 1;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
    }
    public void Use(LivingEntity sourceentity){
        gp.playsound(1);
        GlobalGameThreadConfigs.player.bobux += Value;
        UI.addMessages("bronze coin picked up! value now "+GlobalGameThreadConfigs.player.bobux);
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/coin_bronze", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    @Override
    public void update() {}
    @Override
    public LivingEntity replicate() {
        return new OBJ_COIN_BRONZE(gp);
    }
}
