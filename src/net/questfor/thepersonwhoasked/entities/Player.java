package net.questfor.thepersonwhoasked.entities;
import net.questfor.thepersonwhoasked.Maingam.*;
import net.questfor.thepersonwhoasked.objects.*;
import net.questfor.thepersonwhoasked.objects.Projectiles.OBJ_FireBall;
import net.questfor.thepersonwhoasked.objects.Brickwall;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static net.questfor.thepersonwhoasked.Maingam.MainGame.sound;

//basic player
public class Player extends LivingEntity {
     KeyHandler keyHandler;
    public int screenX;
    public int screenY;
    public int jumpstate = 0;
    public int jumpaction = 0;
    public  boolean isup = false;
    public boolean attacking = false;
    public boolean hasattacked = false;
    public int objindex;
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
        worldx = gp.tilesize * 20;
        worldy = gp.tilesize * 14;
        speed = 4;
        direction = "right";
        worldz = 3;
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
            if (MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Monsters) || MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Tentity) || MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.NPCS) || MainGame.hregister.worldzobjectreturn(this) || MainGame.hregister.returntileworldz(this)) {
                    if (!isup) {
                        worldz--;
                        getImageInstance();
                        updatehitbox();
                        getAttackInstance();
                    }
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
                        if(currentshield.NBTDATA){
                            while (!hasfound) {
                                if (i < 100) {
                                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i] == null) {
                                        double y = worldy;
                                        double x = worldx;
                                        double z = worldz+1;
                                        boolean canplace;
                                        canplace = gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), GlobalGameThreadConfigs.obj) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), GlobalGameThreadConfigs.Monsters) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), GlobalGameThreadConfigs.NPCS) && gp.hregister.checkEntityWorld(Math.round(x / gp.tilesize), Math.round(y / gp.tilesize), GlobalGameThreadConfigs.Tentity) && gp.hregister.checktileworld((int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize), (int) worldz);
                                        if(!KeyHandler.CROUCH) {
                                            switch (direction) {
                                                case "down" -> y += 50;
                                                case "up" -> y -= 50;
                                                case "left" -> x -= 50;
                                                case "right" -> x += 50;
                                            }
                                        }else{
                                            if(gp.tilemanager.mapRendererID[MainGame.currentmap][(int) Math.round(x/gp.tilesize)][(int) Math.round(y/gp.tilesize)][(int) (z-1)] == 46){
                                                z--;
                                            }else{
                                                canplace = false;
                                            }
                                        }
                                        if (canplace) {
                                            switch ((currentshield.name)) {
                                                case "BRIC WALL" -> {GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = new Brickwall(gp, (int) Math.round(x / gp.tilesize), (int) Math.round(y / gp.tilesize)); GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz = z; }
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
}
                    }
            }
            }
            if (KeyHandler.primepowera && projectile.alive == false && primepowercool == 30 && projectile.haveresource(this)) {
                projectile.Set((int) worldx, (int) worldy, direction, true, this);
                projectile.RemoveResource(this);
                GlobalGameThreadConfigs.projectilelist.add(projectile);
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
            objindex = MainGame.hregister.EntityColide(this, GlobalGameThreadConfigs.obj);
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
                    if (keyHandler.upPressed || keyHandler.downPressed) {
                        if (keyHandler.rightPressed || keyHandler.leftPressed) {
                            speed = 3;
                        }
                    }
                    if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {
                        if (KeyHandler.upPressed) {
                            worldy = worldy - speed;
                        }
                        if (KeyHandler.downPressed) {
                            worldy = worldy + speed;
                        }
                        if (KeyHandler.rightPressed) {
                            worldx = worldx + speed;
                        }
                        if (KeyHandler.leftPressed) {
                            worldx = worldx - speed;
                        }
                    }
                }
            } else if (GlobalGameThreadConfigs.isinTital && hitboxe == false) {
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
                }
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
            int attackindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
            attackEntity(attackindex, TrueAttackDamage);
            int TileentityI = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Tentity);
            destroyTentity(TileentityI);
            DestroyOBJ(gp.hregister.EntityColide(this, GlobalGameThreadConfigs.obj));
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
        inventory.add(new OBJ_BRICK_WALL(gp));
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
                switch (direction) {
                    case "down" -> y += 50;
                    case "up" -> y -= 50;
                    case "left" -> x -= 50;
                    case "right" -> x += 50;
                }
                if(z < 4){
                    z++;
                }if(KeyHandler.CROUCH){
                    z--;
                }
                attacking = true;
                if (currentweapon.Type == Type_shovel) {
                    switch (gp.tilemanager.mapRendererID[gp.currentmap][(int) (Math.round(x / gp.tilesize))][(int) Math.round(y / gp.tilesize)][(int) z]) {
                        case 39 -> gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round(x / gp.tilesize)][(int) Math.round(y / gp.tilesize)][(int) z] = 46;
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
                    }
                } else if (currentweapon.Type == Type_axe) {
                    switch (gp.tilemanager.mapRendererID[gp.currentmap][(int) (x / gp.tilesize)][(int) (y / gp.tilesize)][(int) worldz]) {
                        //case 41 -> {gp.tilemanager.mapRendererID[gp.currentmap][(int) Math.round (x / gp.tilesize)][(int) Math.round(y / gp.tilesize)] = 10;}
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
            if(GlobalGameThreadConfigs.isinTital == false){if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed){switch (direction) {case "up": if (attacking == false){if (spritenumber == 1) {image = up1;} else if (spritenumber == 2) {image = up2;}}else{tempscreeny = screenY - gp.tilesize; if (spritenumber == 1) {image = attackup1;} else if (spritenumber == 2) {image = attackup2;}} break; case "down": if(attacking == false){if (spritenumber == 1) {image = down1;} else if (spritenumber == 2) {image = down2;}}else{if (spritenumber == 1) {image = attackdown1;} else if (spritenumber == 2) {image = attackdown2;}else if (spritenumber == 3){image = attackdown3;}}break; case "right": if(attacking == false){if (spritenumber == 1) {image = right1;} else if (spritenumber == 2) {image = right2;}}else{if (spritenumber == 1) {image = attackright1;} else if (spritenumber == 2) {image = attackright2;}}break; case "left": if(attacking == false){if (spritenumber == 1) {image = left1;} else if (spritenumber == 2) {image = left2;}}else{tempscreenx = screenX - gp.tilesize; if (spritenumber == 1) {image = attackleft1;} else if (spritenumber == 2) {image = attackleft2;}} break;}}else{switch (direction){case "right" -> {if(attacking == false){image = right1;}else{if (spritenumber == 1) {image = attackright1;} else if (spritenumber == 2) {image = attackright2;}} }case "left" -> {if(attacking == false){image = left1;}else{tempscreenx = screenX - gp.tilesize; if (spritenumber == 1) {image = attackleft1;} else if (spritenumber == 2) {image = attackleft2;}}}case "up" -> {if(attacking == false){image = up1;}else{tempscreeny = screenY - gp.tilesize;if (spritenumber == 1) {image = attackup1;} else if (spritenumber == 2) {image = attackup2;}}} case "down" -> {if(attacking == false){image = down1;}else{if (spritenumber == 1) {image = attackdown1;} else if (spritenumber == 2) {image = attackdown2;}else if (spritenumber == 3){image = attackdown3;}}}}}}else{switch (direction) {case "up": if (spritenumber == 1) {image = up1;} else if (spritenumber == 2) {image = up2;}break; case "down": if (spritenumber == 1) {image = down1;} else if (spritenumber == 2) {image = down2;}break; case "right": if (spritenumber == 1) {image = right1;} else if (spritenumber == 2) {image = right2;}break; case "left": if (spritenumber == 1) {image = left1;} else if (spritenumber == 2) {image = left2;} break;}}
            if(KeyHandler.jump){
                jumpaction++;
                if(jumpaction < 25){
                    if(isup) {
                        if (!gp.hregister.checkWALL(this)) {
                        if (jumpaction == 1) {
                                worldz++;
                            }
                            jumpstate++;
                                image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                            }
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
                for(int y = 0; y < image.getHeight(); y++){
                    for(int x = 0; x < image.getWidth(); x++){
                        int p = image.getRGB(x, y);
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        p = (a << 24) | (r << 16) | (0 << 8) | 0;
                        image.setRGB(x, y, p);
                        isred = 2;
                    }
                }
            }
            if(isred == 2){
                if(invincible == false){getImageInstance(); getAttackInstance();isred = 1;}
            }
            g2.drawImage(image, tempscreenx, tempscreeny, null);
            if(KeyHandler.checkFPS)
              g2.drawRect((int) (hitbox.x), (int) (hitbox.y), hitbox.width, hitbox.height);
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
}