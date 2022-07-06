package net.questfor.thepersonwhoasked.entities.Mobs;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;

import net.questfor.thepersonwhoasked.Maingam.UI;
import net.questfor.thepersonwhoasked.entities.AI.Path;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.*;
import net.questfor.thepersonwhoasked.objects.Projectiles.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs.player;


public class GunMan extends LivingEntity {

    public GunMan(MainGame gpp) {
        super(gpp);
        EntityType = 1;
        name = "GunMan";
        defaultspeed = 6;
        speed = defaultspeed;
        maxhealth = 20;
        TrueAttackDamage = 5;
        defence = 20;
        health = maxhealth;
        hitbox = new Rectangle(3, 18, 42, 30);
        hitbox.height = 30;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        XP = 7;
        level = 5;
        projectile = new Bullet(gp);
        currentweapon = new OBJ_IRON_SWORD(gp);
Hostile = true;
target = player;
LightSource = true;
    }

    public void getImageInstance() {
        up1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_up_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        up2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_up_2", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        down1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_down_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        down2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_down_2", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        right1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_right_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        right2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_right_2", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        left1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_left_1", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
        left2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_left_2", (int) (GlobalGameThreadConfigs.tilesize+(worldz)), (int) (GlobalGameThreadConfigs.tilesize+(worldz)));
    }

    @Override
    public void getAttackInstance() {
        attackup1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_up_1", (int) (GlobalGameThreadConfigs.tilesize + worldz), (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2);
        attackup2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_up_2", (int) (GlobalGameThreadConfigs.tilesize + worldz), (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2);
        attackdown1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_down_1", (int) (GlobalGameThreadConfigs.tilesize + worldz), (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2);
        attackdown2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_down_2", (int) (GlobalGameThreadConfigs.tilesize + worldz), (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2);
        attackright1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_right_1", (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2, (int) (GlobalGameThreadConfigs.tilesize + worldz));
        attackright2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_right_2", (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2, (int) (GlobalGameThreadConfigs.tilesize + worldz));
        attackleft1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_left_1", (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2, (int) (GlobalGameThreadConfigs.tilesize + worldz));
        attackleft2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_left_2", (int) (GlobalGameThreadConfigs.tilesize + worldz) * 2, (int) (GlobalGameThreadConfigs.tilesize + worldz));
    }

    public void setAction() {

            if (!Hostile) {
                actionLock++;
                if (actionLock == 120) {
                    Random random = new Random();
                    int I = random.nextInt(100) + 1;
                    if (I <= 25) {
                        direction = "up";
                    }
                    if (I > 25 && I <= 50) {
                        direction = "down";
                    }
                    if (I > 50 && I <= 75) {
                        direction = "left";
                    }
                    if (I > 75) {
                        direction = "right";
                    }
                    actionLock = 0;
                }
            } else {

                            if (distancex < previoustaskx) {
                                if (worldx > target.worldx) {
                                    distancex = worldx - target.worldx;
                                } else {
                                    distancex = target.worldx - worldx;
                                }
                                taskx = target.worldx;
                                previoustaskx = distancex;

                            }
                            if (distancey < previoustasky) {
                                if (worldy > target.worldy) {
                                    distancey = worldy - target.worldy;
                                } else {
                                    distancey = target.worldy - worldy;
                                }
                                previoustasky = distancey;
                                tasky = target.worldy;

                            }




                    if(distancex < 50 && distancey < 50){
                        attacking = true;
                }
                Angry();
            }
        }
    @Override
    public void update() {
        boolean ishostile = Hostile;
        super.update();
        if(ishostile){
            Hostile = true;
        }
        if
        (hasattacked) {
            getImageInstance();
            hasattacked = false;
        }
        if(attacking){
            Attack();
        }
    }

    public void Angry() {
        searchPath(Math.round(taskx/GlobalGameThreadConfigs.tilesize), Math.round(tasky/GlobalGameThreadConfigs.tilesize));
        int id = new Random().nextInt(100) + 1;
        if (id > 70 && projectile.alive == false && primepowercool == 30) {
            projectile.Set((int) worldx, (int) worldy, (int) worldz, direction, true, this);
            for(int i = 0; i<GlobalGameThreadConfigs.projectilelist[1].length; i++){
                if(GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] == null){
                    GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] = projectile;
                    break;
                }
            }
            primepowercool = 0;
        }
            previoustasky = 999999999;
            previoustaskx = 999999999;
    }

    public void HandleItems() {
        int I = new Random().nextInt(100) + 1;
        if (I < 50) {
            DropItems(new OBJ_MANA_CRYSTAL(gp));
            DropItems(new OBJHeart(gp));
        } else {
            DropItems(new OBJ_COIN_BRONZE(gp));
        }
    }

    public Color getparticleColor() {
        return new Color(0x388545);
    }

    public int getparticleSize() {
        return 11;
    }

    public int getparticlespeed() {
        return 1;
    }

    public int getparticleMaxHealth() {
        return 23;
    }
    @Override
    public void draw(Graphics2D g2) {
        try {
            if(path == null) {
                path = new Path();
            }
            BufferedImage image = null;
            double screenX = (worldx - GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX);
            double screenY = worldy - GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY;
            double tempscreenx = screenX;
            double tempscreeny = screenY;
            switch (direction) {
                case "up":
                    if (!attacking) {
                        if (spritenumber == 1) {
                            image = up1;
                        } else if (spritenumber == 2) {
                            image = up2;
                        }
                    } else {
                        tempscreeny = screenY - GlobalGameThreadConfigs.tilesize;
                        if (spritenumber == 1) {
                            image = attackup1;
                        } else if (spritenumber == 2) {
                            image = attackup2;
                        }
                    }
                    break;
                case "down":
                    if (!attacking) {
                        if (spritenumber == 1) {
                            image = down1;
                        } else if (spritenumber == 2) {
                            image = down2;
                        }
                    } else {
                        if (spritenumber == 1) {
                            image = attackdown1;
                        } else if (spritenumber == 2) {
                            image = attackdown2;
                        }
                    }
                    break;
                case "right":
                    if (!attacking) {
                        if (spritenumber == 1) {
                            image = right1;
                        } else if (spritenumber == 2) {
                            image = right2;
                        }
                    } else {
                        if (spritenumber == 1) {
                            image = attackright1;
                        } else if (spritenumber == 2) {
                            image = attackright2;
                        }
                    }
                    break;
                case "left":
                    if (!attacking) {
                        if (spritenumber == 1) {
                            image = left1;
                        } else if (spritenumber == 2) {
                            image = left2;
                        }
                    } else {
                        tempscreenx = screenX - GlobalGameThreadConfigs.tilesize;
                        if (spritenumber == 1) {
                            image = attackleft1;
                        } else if (spritenumber == 2) {
                            image = attackleft2;
                        }
                    }
                    break;
            }
            if(jumping){
                jumpaction++;
                if(jumpaction < 25){
                    if(isup) {
                        if (gp.hregister.checkWALL(this) && gp.hregister.checkentitywall(Math.round(worldx/GlobalGameThreadConfigs.tilesize), Math.round(worldy/GlobalGameThreadConfigs.tilesize), worldz, GlobalGameThreadConfigs.NPCS, this) && gp.hregister.checkentitywall(Math.round(worldx/GlobalGameThreadConfigs.tilesize), Math.round(worldy/GlobalGameThreadConfigs.tilesize), worldz,  GlobalGameThreadConfigs.Monsters, this) && gp.hregister.checkentitywall(Math.round(worldx/GlobalGameThreadConfigs.tilesize), Math.round(worldy/GlobalGameThreadConfigs.tilesize), worldz, GlobalGameThreadConfigs.obj, this) ) {
                            if (jumpaction == 1) {
                                worldz++;
                            }
                            jumpstate++;
                            try {
                                image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                            }catch (Exception e){

                            }                            }
                    }
                }else{
                    isup = false;
                    jumpstate--;
                    try {
                        image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                    }catch (Exception e){

                    }
                    if(jumpstate < 0) {
                        jumpaction = 0;
                        jumping = false;
                        getAttackInstance();
                        getImageInstance();
                        updatehitbox();
                    }

                }
            }
            if (drawingpath){
                g2.setColor(Color.red);
                for (int i = 0; i < path.pathlist.size(); i++) {
                    int worldx = path.pathlist.get(i).col * GlobalGameThreadConfigs.tilesize;
                    int worldy = path.pathlist.get(i).row * GlobalGameThreadConfigs.tilesize;
                    double screenx = (worldx - GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX);
                    double screeny = worldy - GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY;
                    g2.fillRect((int) screenx, (int) screeny, GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
                }
            }
            if ((worldx + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldx - GlobalGameThreadConfigs.player.screenX &&
                    (worldx - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldx + GlobalGameThreadConfigs.player.screenX))
                    && worldy + GlobalGameThreadConfigs.tilesize > GlobalGameThreadConfigs.player.worldy - GlobalGameThreadConfigs.player.screenY &&
                    (worldy - GlobalGameThreadConfigs.tilesize < GlobalGameThreadConfigs.player.worldy + GlobalGameThreadConfigs.player.screenY)) {
                if (image == null) {
                    getImageInstance();
                    getAttackInstance();
                }
                if (invincible && image != null) {
                    for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                            int p = image.getRGB(x, y);
                            int a = (p >> 24) & 0xff;
                            int r = (p >> 16) & 0xff;
                            p = (a << 24) | (r << 16) | (0 << 8) | 0;
                            image.setRGB(x, y, p);
                            isred = 2;
                        }
                    }
                }
                if (isred == 2) {
                    if (invincible == false) {
                        getImageInstance();
                        getAttackInstance();
                        isred = 1;
                    }
                }
                if (dying) {
                    DieAnimation(g2);
                }
                if ((EntityType == 2 || EntityType == 1) && Hostile) {
                    double onescale = GlobalGameThreadConfigs.tilesize / maxhealth;
                    double HPValue = onescale * health;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect((int) (screenX - 1), (int) screenY - 16, GlobalGameThreadConfigs.tilesize + 2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect((int) screenX, (int) screenY - 15, (int) HPValue, 10);
                }
                if(worldz > GlobalGameThreadConfigs.player.worldz){
                    float Xdistance;
                    float Ydistance;
                    int x = (int) Math.round(worldx/GlobalGameThreadConfigs.tilesize);
                    int y = (int) Math.round(worldy/GlobalGameThreadConfigs.tilesize);
                    if (x > GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize) {
                        Xdistance = (float) (x - (GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize));
                    }else{
                        Xdistance = (float) ((GlobalGameThreadConfigs.player.worldx / GlobalGameThreadConfigs.tilesize) - x);
                    }
                    if (y > GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize) {
                        Ydistance = (float) (y - (GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize));
                    }else{
                        Ydistance = (float) ((GlobalGameThreadConfigs.player.worldy / GlobalGameThreadConfigs.tilesize) - y);
                    }
                    if (Ydistance < 2 && Xdistance < 2){
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    }
                }
                g2.drawImage(image, (int) tempscreenx, (int) (tempscreeny), null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                drawwalls(g2, this);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Attack() {
        spritecounter++;
        if (spritecounter <= 10) {
            spritenumber = 1;
        }
        if (spritecounter > 10) {
            if(attacking){
            spritenumber = 2;
            int currentworldx = (int) worldx;
            int currentworldy = (int) worldy;

            int hitboxWidth = hitbox.width;
            int hitboxHeight = hitbox.height;
            switch (direction) {
                case "up" -> {
                    worldy -= attackHitbox.height;
                }
                case "down" -> {
                    worldy += attackHitbox.height;
                }
                case "left" -> {
                    worldx -= attackHitbox.width;
                }
                case "right" -> {
                    worldx += attackHitbox.width;
                }
            }
                boolean ContactPLayer = gp.hregister.PlayerColide(this);
                if(ContactPLayer){
                    AttackPLayer(TrueAttackDamage);
                }

            worldx = currentworldx;
            worldy = currentworldy;
            hitbox.width = hitboxWidth;
            hitbox.height = hitboxHeight;
        }
        }

        if (spritecounter >= 25) {
            attacking = false;
            hasattacked = true;
            spritecounter = 0;
        }
    }
    public void AttackPLayer(int trueAttackDamage){
        if(!GlobalGameThreadConfigs.player.invincible){
            int damage = trueAttackDamage - GlobalGameThreadConfigs.player.defence;
            if(damage < 0){
                damage = 0;
            }
            GlobalGameThreadConfigs.player.health-= damage;
            dealKnockback(player);
            UI.addMessages("You have been hit! health is now to "+GlobalGameThreadConfigs.player.health);
            GlobalGameThreadConfigs.player.invincible = true;
            gp.playsound(5);
        }
    }
}