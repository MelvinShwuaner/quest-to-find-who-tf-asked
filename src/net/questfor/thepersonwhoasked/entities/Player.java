package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.*;
import net.questfor.thepersonwhoasked.objects.*;
import net.questfor.thepersonwhoasked.objects.Projectiles.OBJ_FireBall;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;
import static net.questfor.thepersonwhoasked.Maingam.MainGame.sound;

//basic player
public class Player extends LivingEntity {
     KeyHandler keyHandler;
    public int screenX;
    public int screenY;
    public int jumpstate = 0;
    public int jumpaction = 0;
    public boolean attacking = false;
    public boolean hasattacked = false;
    public  boolean hasweapon = true;
    public int i = 0, ii = 0;
    public int d = 0;
    boolean hasfound = false;
    int counter = 0;
    public Player(KeyHandler keyHandler, MainGame gpp) {
        super(gpp);
        this.keyHandler = keyHandler;
        screenX = MainGame.screenwidth / 2 - (MainGame.tilesize / 2);
        screenY = MainGame.screenheight / 2 - (MainGame.tilesize / 2);
        hitbox = new Rectangle();
        hitbox.x = 8;
        hitbox.y = 16;
        hitbox.width = 32;
        hitbox.height = 32;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        EntityType = 0;
        Ammo = 10;
        name = "Player";
        inventorysize = 20;
        worldx = gp.tilesize * 12;
        worldy = gp.tilesize * 14;
        defaultspeed = 4;
        speed = defaultspeed;
        direction = "right";
        worldz = 4;
        maxhealth = 10;
        health = maxhealth;
        invincible = false;
        attackHitbox.width = 36;
        attackHitbox.height = 36;
        level = 1;
        strength = 1;
        dexterity = 1;
        XP = 0;
        MaxXP = 4;
        bobux = 50;
        currentweapon = new OBJ_IRON_SHOVEL(gp);
        currentshield = new OBJ_SHIELD_WOOD(gp);
        defence = getDefenceValues();
        TrueAttackDamage = getAttackValues();
        projectile = new OBJ_FireBall(gp);
        MaxMana = 4;
        Mana = MaxMana;
        GetItems();
        getAttackInstance();
        getImageInstance();
    }
    public int getDefenceValues() {
        if(currentshield != null) {
            return defence = dexterity * currentshield.defenceValue;
        }else{
            return defence = (int) (dexterity * 1.5);
        }
    }

    public int getAttackValues() {
        if (currentweapon != null) {
            attackHitbox = currentweapon.attackHitbox;
            return TrueAttackDamage = strength * currentweapon.AttackValue;
        }else{
            attackHitbox = hitbox;
            return TrueAttackDamage = strength * 1;
        }
    }

    //IMAGES
    public void getImageInstance() {
        up1 = BufferedRenderer("player/boy_up_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        up2 = BufferedRenderer("player/boy_up_2", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        down1 = BufferedRenderer("player/boy_down_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        down2 = BufferedRenderer("player/boy_down_2", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        right1 = BufferedRenderer("player/boy_right_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        right2 = BufferedRenderer("player/boy_right_2", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        left1 = BufferedRenderer("player/boy_left_1", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
        left2 = BufferedRenderer("player/boy_left_2", (int) (gp.tilesize+(worldz)), (int) (gp.tilesize+(worldz)));
    }

    public void getAttackInstance() {
        if (hasweapon) {
            if (currentweapon.name.equals("Iron sword")) {
                attackup1 = BufferedRenderer("player/Attack/boy_attack_up_1", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackup2 = BufferedRenderer("player/Attack/boy_attack_up_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown1 = BufferedRenderer("player/Attack/boy_attack_down_1",(int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown2 = BufferedRenderer("player/Attack/boy_attack_down_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackright1 = BufferedRenderer("player/Attack/boy_attack_right_1", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackright2 = BufferedRenderer("player/Attack/boy_attack_right_2", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackleft1 = BufferedRenderer("player/Attack/boy_attack_left_1", (int) (gp.tilesize+worldz)*2, (int) (gp.tilesize+worldz));
                attackleft2 = BufferedRenderer("player/Attack/boy_attack_left_2", (int) (gp.tilesize+worldz)*2, (int) (gp.tilesize+worldz));
            }
            if(currentweapon.name.equals("Iron shovel")){
                attackright1 = BufferedRenderer("player/Attack/boy_attack_shovel_right_1", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));;
                attackright2 = BufferedRenderer("player/Attack/boy_attack_shovel_right_2", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));;
                attackright3 = BufferedRenderer("player/Attack/boy_attack_shovel_right_3", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackleft1 = BufferedRenderer("player/Attack/boy_attack_shovel_left_0", (int) (gp.tilesize+worldz)*2, (int) (gp.tilesize+worldz));
                attackleft2 = BufferedRenderer("player/Attack/boy_attack_shovel_left_1", (int) (gp.tilesize+worldz)*2, (int) (gp.tilesize+worldz));
                attackleft3 = BufferedRenderer("player/Attack/boy_attack_shovel_left_2", (int) (gp.tilesize+worldz)*2, (int) (gp.tilesize+worldz));
                attackup1 = BufferedRenderer("player/Attack/boy_attack_shovel_up_0", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackup2 = BufferedRenderer("player/Attack/boy_attack_shovel_up_1", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackup3 = BufferedRenderer("player/Attack/boy_attack_shovel_up_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown1 = BufferedRenderer("player/Attack/shoveldown1",(int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown2 = BufferedRenderer("player/Attack/shoveldown2",(int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown3 = BufferedRenderer("player/Attack/shoveldown3",(int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
            }
            if(currentweapon.name.equals("Iron pickaxe")){
                attackright1 = BufferedRenderer("player/Attack/boy_attack_right_1", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackright2 = BufferedRenderer("player/Attack/boy_attack_right_2", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackleft1 = BufferedRenderer("player/Attack/boy_attack_left_1", (int) (gp.tilesize+worldz)*2, (int) (gp.tilesize+worldz));
                attackleft2 = BufferedRenderer("player/Attack/boy_attack_left_2", (int) (gp.tilesize+worldz)*2, (int) (gp.tilesize+worldz));
                attackup1 = BufferedRenderer("player/Attack/boy_attack_up_1", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackup2 = BufferedRenderer("player/Attack/boy_attack_up_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown1 = BufferedRenderer("player/Attack/shoveldown1",(int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown2 = BufferedRenderer("player/Attack/shoveldown2",(int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown3 = BufferedRenderer("player/Attack/shoveldown3",(int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
            }
            if (currentweapon.name.equals("WoodCutter's axe")) {
                attackup1 = BufferedRenderer("player/Attack/boy_axe_up_1", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackup2 = BufferedRenderer("player/Attack/boy_axe_up_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown1 = BufferedRenderer("player/Attack/boy_axe_down_1", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackdown2 = BufferedRenderer("player/Attack/boy_axe_down_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
                attackright1 = BufferedRenderer("player/Attack/boy_axe_right_1", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackright2 = BufferedRenderer("player/Attack/boy_axe_right_2", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackleft1 = BufferedRenderer("player/Attack/boy_axe_left_1", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
                attackleft2 = BufferedRenderer("player/Attack/boy_axe_left_2", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
            }
        }else{
            attackup1 = BufferedRenderer("player/Attack/boy_axe_up_1", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
            attackup2 = BufferedRenderer("player/Attack/boy_axe_up_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
            attackdown1 = BufferedRenderer("player/Attack/boy_axe_down_1", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
            attackdown2 = BufferedRenderer("player/Attack/boy_axe_down_2", (int) (gp.tilesize+worldz), (int) (gp.tilesize+worldz) * 2);
            attackright1 = BufferedRenderer("player/Attack/boy_axe_right_1", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
            attackright2 = BufferedRenderer("player/Attack/boy_axe_right_2", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
            attackleft1 = BufferedRenderer("player/Attack/boy_axe_left_1", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
            attackleft2 = BufferedRenderer("player/Attack/boy_axe_left_2", (int) (gp.tilesize+worldz) * 2, (int) (gp.tilesize+worldz));
        }

    }
    public void updatehitbox() {
        if (up1 != null) {
            if (worldz < 0){
                hitbox.width = up1.getWidth() - 16;
            hitbox.height = up1.getHeight() - 16;
        }
        }
    }

    public void update() {
        try {
            if (up1 == null || attackup2 == null) {
                getImageInstance();
                getAttackInstance();
            }
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).down1 == null) {
                    inventory.get(i).getImageInstance();
                }
            }
            if (currentweapon == null) {
                hasweapon = false;
            } else {
                hasweapon = true;
            }
            //PLAYER ATTACK//
            if (KeyHandler.use) {
                KeyHandler.use = false;
                hasfound = false;
                if (UI.transitionfinushed || GlobalGameThreadConfigs.CharacterStats) {
                    convertItem(7);
                } else {
                    if (hasweapon) {
                        if (currentweapon.Type == Type_constumable) {
                            convertItem(8);
                        }
                    }
                }
                if (currentshield != null){
                    if (currentshield.EntityType == 4) {
                        if(currentshield.NBTDATA) {
                            while (!hasfound) {
                                if (i < 100) {
                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i] == null) {
                                        double y = worldy;
                                        double x = worldx;
                                        double z = worldz;
                                        boolean canplace;
                                        if(!KeyHandler.CROUCH && !KeyHandler.sprint) {
                                            switch (direction) {
                                                case "down" -> y += 50;
                                                case "up" -> y -= 50;
                                                case "left" -> x -= 50;
                                                case "right" -> x += 50;
                                            }
                                        }else if(KeyHandler.CROUCH){
                                                z--;
                                        }else if(KeyHandler.sprint){
                                                z++;
                                        }
                                        canplace = (gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Tentity) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z));
                                    if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)){
                                            switch (direction) {
                                                case "down" -> y += 50;
                                                case "up" -> y -= 50;
                                                case "left" -> x -= 50;
                                                case "right" -> x += 50;
                                            }
                                            canplace = gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Tentity) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z);
                                        }
                                        if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)+1, (int) Math.round(y / gp.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)-1, (int) Math.round(y / gp.tilesize), (int) z))) {
                                            switch ((currentshield.name)) {
                                                case "crafting table" -> {GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new crafting_table(gp, (int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)); GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz = z;}
                                                case "furnace" -> {GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new furnace(gp, (int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize));GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz = z;}
                                                case "Stone" -> {GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new Stone(gp, (int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize));GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz = z;}
                                            }
                                            currentshield.stacksize--;
                                            if (currentshield.stacksize <= 0) {
                                                inventory.remove(currentshield);
                                                currentshield = null;
                                            }
                                        }
                                        i = 0;
                                        hasfound = true;
                                    }
                                    i++;

                                } else {
                                    i = 0;
                                }
                            }
}else{
                            double y = worldy;
                            double x = worldx;
                            double z = worldz;
                            boolean canplace;
                            if(!KeyHandler.CROUCH && !KeyHandler.sprint) {
                                switch (direction) {
                                    case "down" -> y += 50;
                                    case "up" -> y -= 50;
                                    case "left" -> x -= 50;
                                    case "right" -> x += 50;
                                }
                            }else if(KeyHandler.CROUCH){
                                z--;
                            }else if(KeyHandler.sprint){
                                z++;
                            }
                            canplace = (gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z,GlobalGameThreadConfigs.NPCS) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize),z, GlobalGameThreadConfigs.Tentity) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z));
                            if(!canplace && (KeyHandler.sprint || KeyHandler.CROUCH)) {
                                switch (direction) {
                                    case "down" -> y += 50;
                                    case "up" -> y -= 50;
                                    case "left" -> x -= 50;
                                    case "right" -> x += 50;
                                }
                                canplace = gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z, GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z, GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z, GlobalGameThreadConfigs.NPCS) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), z, GlobalGameThreadConfigs.Tentity) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z);
                            }
                                if (canplace && (!gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z-1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) z+1) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)+1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)-1, (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)+1, (int) Math.round(y / gp.tilesize), (int) z) || !gp.hregister.checktileworld((int) Math.round(x / gp.tilesize)-1, (int) Math.round(y / gp.tilesize), (int) z))) {
                                    gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(x/gp.tilesize)][(int) Math.round(y/gp.tilesize)][(int) z] = currentshield.tile;
                                    currentshield.stacksize--;
                                    if (currentshield.stacksize <= 0) {
                                        inventory.remove(currentshield);
                                        currentshield = null;
                                    }
                                }

                        }
                    }
            }
            }
            if (MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Monsters) || MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Tentity) || MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.NPCS) || MainGame.hregister.returntileworldz(this)) {
                if (!isup) {
                    worldz--;
                    getImageInstance();
                    updatehitbox();
                    getAttackInstance();
                }
            }
            if(frozen){
                checkCollision();
                if(hitboxe){
                    knockbackcounter = 0;
                    frozen = false;
                    speed = defaultspeed;
                }else{
                    switch (frozendirection) {
                        case "up" -> worldy = worldy - speed;
                        case "down" -> worldy = worldy + speed;
                        case "right" -> worldx = worldx + speed;
                        case "left" -> worldx = worldx - speed;
                    }
                    knockbackcounter++;
                    if(knockbackcounter == 2){
                        speed--;
                        knockbackcounter = 0;
                    }
                    if(speed == defaultspeed){
                        frozen = false;
                    }
                }
            }
            if (KeyHandler.primepowera && projectile.alive == false && primepowercool == 30 && projectile.haveresource(this)) {
                projectile.Set((int) worldx, (int) worldy, (int) worldz, direction, true, this);
                projectile.RemoveResource(this);
                for(int i = 0; i<GlobalGameThreadConfigs.projectilelist[1].length; i++){
                    if(GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] == null){
                        GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] = projectile;
                        break;
                    }
                }
                primepowercool = 0;
                gp.playsound(10);
            }
            if (primepowercool < 30) {
                primepowercool++;
            }
            //MOVEMENT
            if (GlobalGameThreadConfigs.isinTital == false) {
                if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {

                    if (keyHandler.upPressed) {
                        direction = "up";
                    }
                    if (keyHandler.downPressed) {
                        direction = "down";
                    }
                    if (keyHandler.rightPressed) {
                        direction = "right";
                    }
                    if (keyHandler.leftPressed) {
                        direction = "left";
                    }
                }
                if (attacking == true) {
                    Attack();
                } else if
                (hasattacked == true) {
                    getImageInstance();
                    hasattacked = false;
                }


                if (invincible == true) {
                    hitTime++;
                    if (hitTime > 30) {
                        invincible = false;
                        hitTime = 0;
                    }
                }
            } else if (GlobalGameThreadConfigs.isinTital == true) {
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
                    if (I > 75 && I <= 100) {
                        direction = "right";
                    }
                    actionLock = 0;
                }
            }
            hitboxe = false;
            MainGame.hregister.checkTile(this);

            //OBJECT COLLISIONS
            objindex = MainGame.hregister.worldzobjectreturn(this, GlobalGameThreadConfigs.obj);
            pickupObject(objindex);

            //ENTITY COLLISIONS
            int npcindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.NPCS);
            interactNPC(npcindex);
            if (npcindex != 999) {
                if ((GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].dialogues[0] != null)){
                    if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].dialogues[0].equals("Take a wish. will you?")) {
                        speed = 0;
                        screenY++;
                        counter++;
                        if (counter == 30) {
                            UI.addMessages("Thank you for Alpha testing My Game");
                        }
                        if (counter == 180) {
                            UI.addMessages("Hope you enjoy the rest of your day, if you encountered bugs please \n go to https:discord.gg/tRva2AM2Gk and message @bruhkid2345");
                        }
                        if (counter == 360) {
                            UI.addMessages("Bye :D !!!!");
                        }
                    }
            }

        }
                int Monsterindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
                attacked(Monsterindex);
                int TentityI = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Tentity);
                //CHECK EVENT
                gp.ehandler.returnEvent();
                KeyHandler.enterpressed = false;

            if (GlobalGameThreadConfigs.isinTital == false) {
                if (!hitboxe) {
                    if(!frozen){
                    if (keyHandler.upPressed || keyHandler.downPressed) {
                        if (keyHandler.rightPressed || keyHandler.leftPressed) {
                            speed = 3;
                        }
                    }
                    if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {
                        if((!KeyHandler.rightPressed && !KeyHandler.leftPressed) || (!KeyHandler.leftPressed && KeyHandler.rightPressed)|| (KeyHandler.leftPressed && !KeyHandler.rightPressed)){
                        if (KeyHandler.upPressed) {
                            worldy = worldy - speed;
                        }
                        if (KeyHandler.downPressed) {
                            worldy = worldy + speed;
                        }}
                        if (KeyHandler.rightPressed) {
                            worldx = worldx + speed;
                        }
                        if (KeyHandler.leftPressed) {
                            worldx = worldx - speed;
                        }
                    }}
                }
            } else if (GlobalGameThreadConfigs.isinTital && hitboxe == false) {
                if(!frozen){
                switch (this.direction) {
                    case "up":
                        worldy -= this.speed;
                        break;
                    case "down":
                        worldy += this.speed;
                        break;
                    case "left":
                        worldx -= this.speed;
                        break;
                    case "right":
                        worldx += this.speed;
                }}
            }
            if (attacking == false) {
                spritecounter++;
                if (spritecounter > 12) {
                    if (spritenumber == 1) {
                        spritenumber = 2;
                    } else if (spritenumber == 2) {
                        spritenumber = 1;
                    }
                    spritecounter = 0;
                }
            }
            if(health <= 0){
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.GameOverState;
                gp.stopmusic();
                UI.commandnum = -1;
                gp.playsound(12);
            }
        } catch (Exception e) {
            crash.main(e);
        }

    }

    public void Attack() {
        spritecounter++;
        if (spritecounter <= 10) {
            spritenumber = 1;
        }
        if (spritecounter > 10) {
            spritenumber = 2;
            int currentworldx = (int) worldx;
            int currentworldy = (int) worldy;

            int hitboxWidth = hitbox.width;
            int hitboxHeight = hitbox.height;
            switch (direction) {
                case "up" -> worldy -= attackHitbox.height;
                case "down" -> worldy += attackHitbox.height;
                case "left" -> worldx -= attackHitbox.width;
                case "right" -> worldx += attackHitbox.width;
            }
            int attackindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
            attackEntity(attackindex, TrueAttackDamage);
            int TileentityI = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Tentity);
            destroyTentity(TileentityI);
            int projectileI = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.projectilelist);
            super.damageprojectile(projectileI);
            double z = worldz;
            if(KeyHandler.CROUCH){
                z--;
            }else if(KeyHandler.sprint){
                z++;
            }
            DestroyOBJ(gp.hregister.EntityColidewithz(this, z, GlobalGameThreadConfigs.obj));
            worldx = currentworldx;
            worldy = currentworldy;
            hitbox.width = hitboxWidth;
            hitbox.height = hitboxHeight;
        }
        if (currentweapon.frames == 2) {
            if (spritecounter >= 25) {
                attacking = false;
                hasattacked = true;
                spritecounter = 0;
            }
        } else {
            if (spritecounter > 25 && spritecounter < 35) {
                spritenumber = 3;
            }
            if (spritecounter >= 35) {
                attacking = false;
                hasattacked = true;
                spritecounter = 0;
            }
        }
    }

    public void destroyTentity(int tileentityI) {
        if (tileentityI != 999 && GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].distructuble && GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].ItemRequirements(this) && !GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].invincible) {
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].health--;
            GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].playSE();
            GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].invincible = true;
            ParticlePropertyManager(GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI], GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI]);
            if (GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].health <= 0) {
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].getDestroyedForm();
                GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].HandleItems();
            }

    }
    }
    public void DestroyOBJ(int tileentityI) {
        if (tileentityI != 999 && !GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].invincible) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].ItemRequirements(this)) {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].health-= TrueAttackDamage;
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].playSE();
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].invincible = true;
                    ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI], GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI]);
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].health <= 0) {
                        MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].worldx / gp.tilesize)][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].worldy / gp.tilesize)][(int) GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].worldz] = 46;
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI] = GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].getDestroyedForm();
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].HandleItems();
                  }
                }
            }}

    public void attackEntity(int attackindex, int dmg) {
        super.attackEntity(attackindex, dmg);
    }
    public void levelUpAchiver() {
       super.levelUpAchiver();
        UI.addMessages("You have unlocked a new level! your now level " + level);
    }

    public void attacked(int monsterindex) {
        /*collision between player and monsters*/
        if (monsterindex != 999) {
            int damage = GlobalGameThreadConfigs.Monsters[MainGame.currentmap][monsterindex].TrueAttackDamage - defence;
            if (damage < 0) {
                damage = 0;
            }
            if (invincible == false && GlobalGameThreadConfigs.Monsters[MainGame.currentmap][monsterindex].dying == false) {
                health -= damage;
                UI.addMessages("You have been hit! health is now to " + gp.player.health);
                gp.playsound(5);
                invincible = true;
            }
        }
    }

    public void pickupObject(int i) {
        try {

            if (i != 999) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].EntityType == 3) {
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].Type == Type_Current) {
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][i].Use(this);
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = null;
                    }
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i] != null) {
                        String text;
                        if (inventory.size() != inventorysize) {
                            inventory.add(GlobalGameThreadConfigs.obj[MainGame.currentmap][i]);
                            text = "Picked up " + GlobalGameThreadConfigs.obj[MainGame.currentmap][i].name;
                            GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = null;
                            if (gp != null){
                                gp.playsound(1);
                        }else{
                                gp = new MainGame();
                                gp.playsound(1);
                            }
                        } else {
                            text = "Your inventory is full!";
                        }

                        UI.addMessages(text);
                    }
                } else {
                    switch (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].name) {
                        case "chest" -> {
                            if (KeyHandler.enterpressed) {
                                if (!GlobalGameThreadConfigs.inchest) {
                                    GlobalGameThreadConfigs.inchest = true;
                                    GlobalGameThreadConfigs.CharacterStats = true;

                                } else {
                                    GlobalGameThreadConfigs.inchest = false;
                                    GlobalGameThreadConfigs.CharacterStats = false;
                                    UI.slotstate = false;
                                    if(UI.merger != null){
                                        UI.merger.first = false;
                                    }
                                    if(UI.mergerr != null){
                                        UI.mergerr = null;
                                    }
                                }
                            }
                        }
                        case "furnace", "crafting table" -> {
                            if (KeyHandler.enterpressed) {
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][i].open();
                            }
                        }
                        case "door" -> {
                            if (KeyHandler.enterpressed){
                                KeyHandler.enterpressed = false;
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new OBJdooropen(gp, (int) GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx, (int) GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy);
                                gp.playsound(3);
                        }
                    }
                    case "door open" -> {
                            if(KeyHandler.enterpressed){
                                gp.playsound(3);
                                GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new OBJdoor(gp, (int) GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx/gp.tilesize, (int) GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy/gp.tilesize);
                            }
                    }
                    }

                }
            } else {
                GlobalGameThreadConfigs.inchest = false;
            }
        } catch (Exception e) {
            crash.main(e);
        }
    }

    public void GetItems() {
        inventory.add(currentweapon);
        inventory.add(currentshield);
        LivingEntity brick = new OBJ_BRICK_WALL(gp); brick.stacksize = 64;
        inventory.add(new OBJ_BRICK_WALL(gp));
        inventory.add(new OBJ_IRON_PICKAXE(gp));
        inventory.add(brick);
    }

    public void interactNPC(int i) {
        try {
            if (i != 999) {
                if (KeyHandler.enterpressed) {
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
                    if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i].dialogues[GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i].dialogueIndex] != null) {
                        GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i].speak();
                        if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i].dialogues[0].equals("Take a wish. will you?")) {
                            speed = 0;
                            screenY++;
                        }
                    } else {
                        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                    }
                }
            }
            if (KeyHandler.attack == true) {
                double y = worldy;
                double x = worldx;
                double z = worldz;
                boolean canbreak;
                if(!KeyHandler.CROUCH && !KeyHandler.sprint){
                switch (direction) {
                    case "down" -> y += 50;
                    case "up" -> y -= 50;
                    case "left" -> x -= 50;
                    case "right" -> x += 50;
                }}else
                if(KeyHandler.CROUCH){
                    z--;
                }else if(KeyHandler.sprint){
                    z++;
                }
                canbreak = gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / gp.tilesize))][(int) Math.round(y / gp.tilesize)][(int) z] != 46;
                if(!canbreak && (KeyHandler.sprint || KeyHandler.CROUCH)){
                    switch (direction) {
                        case "down" -> y += 50;
                        case "up" -> y -= 50;
                        case "left" -> x -= 50;
                        case "right" -> x += 50;
                    }
                }
                attacking = true;
                if(gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / gp.tilesize))][(int) Math.round(y / gp.tilesize)][(int) z] != 47){
                if (currentweapon.Type == Type_shovel) {
                    switch (gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / gp.tilesize))][(int) Math.round(y / gp.tilesize)][(int) z]) {
                        case 39,0 -> gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / gp.tilesize)][(int) Math.round(y / gp.tilesize)][(int) z] = 46;
                        case 45 -> {gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / gp.tilesize)][(int) Math.round(y / gp.tilesize)][(int) z] = 46;
                            for(int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++){
                                if(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] == null){
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = new Clay(gp);
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx = x;
                                    GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy = y;
                                    ii = GlobalGameThreadConfigs.obj[1].length;
                                }
                            }
                        }
                        case 10, 11 -> gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / gp.tilesize)][(int) Math.round(y / gp.tilesize)][(int) z] = 39;
                    }}
                }else{
                    for(int ii = 0; ii < GlobalGameThreadConfigs.obj[1].length; ii++){
                        if(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] != null){
                           if(Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldx/gp.tilesize) == Math.round(x/gp.tilesize) && Math.round(GlobalGameThreadConfigs.obj[currentmap][ii].worldy/gp.tilesize) == Math.round(y/gp.tilesize) && GlobalGameThreadConfigs.obj[currentmap][ii].worldz==z){
                               if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].ItemRequirements(this)) {
                                   if (!GlobalGameThreadConfigs.obj[currentmap][ii].invincible) {
                               GlobalGameThreadConfigs.obj[currentmap][ii].health -= TrueAttackDamage;
                               GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].playSE();
                               GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].invincible = true;
                               ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii], GlobalGameThreadConfigs.obj[MainGame.currentmap][ii]);
                               if (GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].health <= 0) {
                                   MainGame.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldx / gp.tilesize)][(int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldy / gp.tilesize)][(int) GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].worldz] = 46;
                                   GlobalGameThreadConfigs.obj[MainGame.currentmap][ii] = GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].getDestroyedForm();
                                   GlobalGameThreadConfigs.obj[MainGame.currentmap][ii].HandleItems();
                               }
                                   ii = GlobalGameThreadConfigs.obj[1].length;}
                           }
                        }}
                    }
                }
                playsound(7);
                KeyHandler.attack = false;
            }
        } catch (Exception e) {
            crash.main(e);
        }
    }

    public void convertItem(int code) {
        int itemIndex = UI.getItemIndex();
        if (itemIndex < inventory.size()){
            LivingEntity SelectedItem = inventory.get(itemIndex);
            if (code == 1){
                currentweapon = SelectedItem;
                getAttackInstance();
            }else if (code == 2) {
                currentshield = SelectedItem;
            }
            if(code == 7) {
                if (health < maxhealth) {
                    if (SelectedItem.Type == Type_constumable){
                        SelectedItem.Use(this);
                    inventory.remove(SelectedItem);
                }
            }
            }else if(code == 8){
                currentweapon.Use(this);
                inventory.remove(currentweapon);
                currentweapon = null;
            }
            TrueAttackDamage = getAttackValues();
            defence = getDefenceValues();
    }

}
    public void draw(Graphics2D g2) {
        try {
            BufferedImage image = null;
            int tempscreenx = screenX;
            int tempscreeny = screenY;
            //Try Reading this line. I will wait ;D
            if(GlobalGameThreadConfigs.isinTital == false){if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed){switch (direction) {case "up": if (attacking == false){if (spritenumber == 1) {image = up1;} else if (spritenumber == 2) {image = up2;}}else{tempscreeny = screenY - gp.tilesize; if (spritenumber == 1) {image = attackup1;} else if (spritenumber == 2) {image = attackup2;}else if (spritenumber == 3){image = attackup3;}} break; case "down": if(attacking == false){if (spritenumber == 1) {image = down1;} else if (spritenumber == 2) {image = down2;}}else{if (spritenumber == 1) {image = attackdown1;} else if (spritenumber == 2) {image = attackdown2;}else if (spritenumber == 3){image = attackdown3;}}break; case "right": if(attacking == false){if (spritenumber == 1) {image = right1;} else if (spritenumber == 2) {image = right2;}}else{if (spritenumber == 1) {image = attackright1;} else if (spritenumber == 2) {image = attackright2;}else if (spritenumber == 3){image = attackright3;}}break; case "left": if(attacking == false){if (spritenumber == 1) {image = left1;} else if (spritenumber == 2) {image = left2;}}else{tempscreenx = screenX - gp.tilesize; if (spritenumber == 1) {image = attackleft1;} else if (spritenumber == 2) {image = attackleft2;}else if (spritenumber == 3){image = attackleft3;}} break;}}else{switch (direction){case "right" -> {if(attacking == false){image = right1;}else{if (spritenumber == 1) {image = attackright1;} else if (spritenumber == 2) {image = attackright2;}else if (spritenumber == 3){image = attackright3;}} }case "left" -> {if(attacking == false){image = left1;}else{tempscreenx = screenX - gp.tilesize; if (spritenumber == 1) {image = attackleft1;} else if (spritenumber == 2) {image = attackleft2;}else if (spritenumber == 3){image = attackleft3;}}}case "up" -> {if(attacking == false){image = up1;}else{tempscreeny = screenY - gp.tilesize;if (spritenumber == 1) {image = attackup1;} else if (spritenumber == 2) {image = attackup2;}else if (spritenumber == 3){image = attackup3;}}} case "down" -> {if(attacking == false){image = down1;}else{if (spritenumber == 1) {image = attackdown1;} else if (spritenumber == 2) {image = attackdown2;}else if (spritenumber == 3){image = attackdown3;}}}}}}else{switch (direction) {case "up": if (spritenumber == 1) {image = up1;} else if (spritenumber == 2) {image = up2;}break; case "down": if (spritenumber == 1) {image = down1;} else if (spritenumber == 2) {image = down2;}break; case "right": if (spritenumber == 1) {image = right1;} else if (spritenumber == 2) {image = right2;}break; case "left": if (spritenumber == 1) {image = left1;} else if (spritenumber == 2) {image = left2;} break;}}
            if(KeyHandler.jump){
                jumpaction++;
                if(jumpaction < 25){
                    if(isup) {
                        if (gp.hregister.checkWALL(this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz, GlobalGameThreadConfigs.NPCS, this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz,  GlobalGameThreadConfigs.Monsters, this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz,  GlobalGameThreadConfigs.Tentity, this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz, GlobalGameThreadConfigs.obj, this) ) {
                        if (jumpaction == 1) {
                                worldz++;
                            }
                            jumpstate++;
                            try {
                                image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                            }catch (Exception e){
                                System.out.println("Catched null pointer exeption");
                            }                            }
                    }
                }else{
                    isup = false;
                    jumpstate--;
                    try {
                        image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                    }catch (Exception e){
                       System.out.println("Catched null pointer exeption");
                    }
                    if(jumpstate < 0) {
                        jumpaction = 0;
                        KeyHandler.jump = false;
                        getAttackInstance();
                        getImageInstance();
                        updatehitbox();
                    }

                }
            }
            if(invincible){
                try {
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
                }catch (Exception d){
                    System.out.println("Catched null pointer exeption");
                }
            }

            if(isred == 2){
                if(invincible == false){getImageInstance(); getAttackInstance();isred = 1;}
            }
            g2.drawImage(image, tempscreenx, tempscreeny, null);
            drawwalls(g2, this);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

            if(KeyHandler.checkFPS)
              g2.drawRect((int) (tempscreenx+hitbox.x), (int) (tempscreeny+hitbox.y), hitbox.width, hitbox.height);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void playsound(int i) {
        //PLAYS SOUND EFFECTS
        sound.setFile(i);
        sound.play();
    }
    public BufferedImage scaleimage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
    public void drawwalls(Graphics2D g2, LivingEntity entity) {
        int worldcol = (int) (entity.worldx/MainGame.tilesize);
        int worldrow = (int) (entity.worldy / MainGame.tilesize);
        int worldlayer = 0;
        while (worldlayer < MainGame.maxworldlayer) {
            int tileID = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer];
            int worldX = worldcol * MainGame.tilesize;
            int worldY = worldrow * MainGame.tilesize;
            double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
            if (tileID != 46) {
                boolean shouldrender;
                if(worldlayer+1 < MainGame.maxworldlayer) {
                    shouldrender = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer+1] == 46 || gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer+1] == 41;
                }else{
                    shouldrender = true;
                }
                if(shouldrender)  {
                    if (worldlayer >= entity.worldz) {
                        if(worldlayer > gp.player.worldz){
                            float Xdistance;
                            float Ydistance;
                            if (worldcol > gp.player.worldx / gp.tilesize) {
                                Xdistance = (float) (worldcol - (gp.player.worldx / gp.tilesize));
                            }else{
                                Xdistance = (float) ((gp.player.worldx / gp.tilesize) - worldcol);
                            }
                            if (worldrow > gp.player.worldy / gp.tilesize) {
                                Ydistance = (float) (worldrow - (gp.player.worldy / gp.tilesize));
                            }else{
                                Ydistance = (float) ((gp.player.worldy / gp.tilesize) - worldrow);
                            }
                            if (Ydistance < 2 && Xdistance < 2){
                                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                            }
                        }
                        g2.drawImage(gp.tilemanager.tile[tileID].image, (int) screenX, (int) screenY, null);
                    }
                }

            }
            worldlayer++;
        }
    }


}