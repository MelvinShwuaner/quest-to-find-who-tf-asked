package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;

public class OBJ_IRON_SHOVEL extends LivingEntity {
    public OBJ_IRON_SHOVEL(MainGame gpp) {
        super(gpp);
        name = "Iron shovel";
        AttackValue = 2;
        attackHitbox.width = 30;
        attackHitbox.height = 36;
        EntityType = 3;
        hitbox = new Rectangle();
        hitbox.x = 0;
        LightSource = false;
        hitbox.y = 16;
        hitbox.width = GlobalGameThreadConfigs.tilesize;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        Type = Type_shovel;
        Value = 3;
        frames = 3;
        description = "A good shovel to dig up tunnels through \nbad times.";
        getImageInstance();
    }
    @Override
    public void update() {}
    @Override
    public LivingEntity replicate() {
        return new OBJ_IRON_SHOVEL(gp);
    }
    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("objects/shovel", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    @Override
    public void Destroy(double x, double y, double z){
        if(!GlobalGameThreadConfigs.Buildmode){
        if(gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / GlobalGameThreadConfigs.tilesize))][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] != 47){
            switch (gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / GlobalGameThreadConfigs.tilesize))][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z]) {
            case 39, 0 -> gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;
            case 45 -> {
                gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;
                for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null) {
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new Clay(gp);
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx = x;
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = y;
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz = z;
                        ii = GlobalGameThreadConfigs.obj[1].length;
                    }
                }
            }
            case 10, 11 -> gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / GlobalGameThreadConfigs.tilesize)][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 39;
        }
        }else{
            for (int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] != null) {
                    if (Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldx / GlobalGameThreadConfigs.tilesize) == Math.round(x / GlobalGameThreadConfigs.tilesize) && Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldy / GlobalGameThreadConfigs.tilesize) == Math.round(y / GlobalGameThreadConfigs.tilesize) && GlobalGameThreadConfigs.obj[currentmap][ii].worldz == z) {
                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].ItemRequirements(this)) {
                            if (!GlobalGameThreadConfigs.obj[currentmap][ii].invincible) {
                                GlobalGameThreadConfigs.obj[currentmap][ii].health -= TrueAttackDamage;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].playSE();
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].invincible = true;
                                ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii], GlobalGameThreadConfigs.obj[MainGame.currentmap][ii]);
                                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].health <= 0) {
                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx / GlobalGameThreadConfigs.tilesize)][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy / GlobalGameThreadConfigs.tilesize)][(int) GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz] = 46;
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].getDestroyedForm();
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].HandleItems();
                                }
                                ii = GlobalGameThreadConfigs.obj[1].length;
                            }
                        }
                    }
                }
            }
        }}else{
            if(gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / GlobalGameThreadConfigs.tilesize))][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] != 47){
                gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / GlobalGameThreadConfigs.tilesize))][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;
            }else{
                for(int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++){
                    if(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] != null){
                        if(Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldx/GlobalGameThreadConfigs.tilesize) == Math.round(x/GlobalGameThreadConfigs.tilesize) && Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldy/GlobalGameThreadConfigs.tilesize) == Math.round(y/GlobalGameThreadConfigs.tilesize) && GlobalGameThreadConfigs.obj[currentmap][ii].worldz==z){
                            if (!GlobalGameThreadConfigs.obj[currentmap][ii].invincible) {
                                GlobalGameThreadConfigs.obj[currentmap][ii].health -= TrueAttackDamage;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].playSE();
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].invincible = true;
                                ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii], GlobalGameThreadConfigs.obj[MainGame.currentmap][ii]);
                                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].health <= 0) {
                                    MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx / GlobalGameThreadConfigs.tilesize)][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy / GlobalGameThreadConfigs.tilesize)][(int) GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz] = 46;
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].getDestroyedForm();
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].HandleItems();
                                    gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / GlobalGameThreadConfigs.tilesize))][(int) Math.round(y / GlobalGameThreadConfigs.tilesize)][(int) z] = 46;

                                }
                                ii = GlobalGameThreadConfigs.obj[1].length;}
                        }
                    }}
            }
        }}
}
