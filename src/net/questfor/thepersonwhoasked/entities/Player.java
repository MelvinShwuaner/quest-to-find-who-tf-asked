package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.*;
import net.questfor.thepersonwhoasked.objects.*;
import net.questfor.thepersonwhoasked.objects.Projectiles.OBJ_FireBall;
import net.questfor.thepersonwhoasked.tile_entites.TileEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
//basic player
public class Player extends LivingEntity {
    KeyHandler keyHandler;
    public int screenX;
    public int screenY;
    public int jumpstate = 0;
    public int jumpaction = 0;
    public static boolean isup = false;
    public boolean attacking = false;
    public boolean hasattacked = false;
    public boolean isattacking = false;

    public static int objindex;
    public static boolean hasweapon = true;
    public Player(KeyHandler keyHandler, MainGame gpp) {
        super(gpp);
        this.keyHandler = keyHandler;
        setdefaultvalues();
        getImageInstance();
        getAttackInstance();
        GetItems();
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
    }

    public void setdefaultvalues() {
        try {
            //DEFAULT VALUES
            Ammo = 10;
            inventorysize = 20;
            worldx = MainGame.tilesize * 10;
            worldy = MainGame.tilesize * 9;
            speed = 4;
            direction = "right";
            worldz = 1;
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
            bobux = 0;
            currentweapon = new OBJ_IRON_SWORD(gp);
            currentshield = new OBJ_SHIELD_WOOD(gp);
            defence = getDefenceValues();
            TrueAttackDamage = getAttackValues();
            projectile = new OBJ_FireBall(gp);
            MaxMana = 4;
            Mana = MaxMana;

        } catch (Exception e) {
            crash.main(e);
        }
    }

    private int getDefenceValues() {
        return defence = dexterity * currentshield.defenceValue;
    }

    private int getAttackValues() {
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
        up1 = BufferedRenderer("player/boy_up_1", gp.tilesize, gp.tilesize);
        up2 = BufferedRenderer("player/boy_up_2", gp.tilesize, gp.tilesize);
        down1 = BufferedRenderer("player/boy_down_1", gp.tilesize, gp.tilesize);
        down2 = BufferedRenderer("player/boy_down_2", gp.tilesize, gp.tilesize);
        right1 = BufferedRenderer("player/boy_right_1", gp.tilesize, gp.tilesize);
        right2 = BufferedRenderer("player/boy_right_2", gp.tilesize, gp.tilesize);
        left1 = BufferedRenderer("player/boy_left_1", gp.tilesize, gp.tilesize);
        left2 = BufferedRenderer("player/boy_left_2", gp.tilesize, gp.tilesize);
    }

    public void getAttackInstance() {
        if (hasweapon) {
            if (currentweapon.name.equals("Iron sword")) {
                attackup1 = BufferedRenderer("player/Attack/boy_attack_up_1", gp.tilesize, gp.tilesize * 2);
                attackup2 = BufferedRenderer("player/Attack/boy_attack_up_2", gp.tilesize, gp.tilesize * 2);
                attackdown1 = BufferedRenderer("player/Attack/boy_attack_down_1", gp.tilesize, gp.tilesize * 2);
                attackdown2 = BufferedRenderer("player/Attack/boy_attack_down_2", gp.tilesize, gp.tilesize * 2);
                attackright1 = BufferedRenderer("player/Attack/boy_attack_right_1", gp.tilesize * 2, gp.tilesize);
                attackright2 = BufferedRenderer("player/Attack/boy_attack_right_2", gp.tilesize * 2, gp.tilesize);
                attackleft1 = BufferedRenderer("player/Attack/boy_attack_left_1", gp.tilesize * 2, gp.tilesize);
                attackleft2 = BufferedRenderer("player/Attack/boy_attack_left_2", gp.tilesize * 2, gp.tilesize);
            }
            if (currentweapon.name.equals("WoodCutter's axe")) {
                attackup1 = BufferedRenderer("player/Attack/boy_axe_up_1", gp.tilesize, gp.tilesize * 2);
                attackup2 = BufferedRenderer("player/Attack/boy_axe_up_2", gp.tilesize, gp.tilesize * 2);
                attackdown1 = BufferedRenderer("player/Attack/boy_axe_down_1", gp.tilesize, gp.tilesize * 2);
                attackdown2 = BufferedRenderer("player/Attack/boy_axe_down_2", gp.tilesize, gp.tilesize * 2);
                attackright1 = BufferedRenderer("player/Attack/boy_axe_right_1", gp.tilesize * 2, gp.tilesize);
                attackright2 = BufferedRenderer("player/Attack/boy_axe_right_2", gp.tilesize * 2, gp.tilesize);
                attackleft1 = BufferedRenderer("player/Attack/boy_axe_left_1", gp.tilesize * 2, gp.tilesize);
                attackleft2 = BufferedRenderer("player/Attack/boy_axe_left_2", gp.tilesize * 2, gp.tilesize);
            }
        }else{
            attackup1 = BufferedRenderer("player/Attack/boy_axe_up_1", gp.tilesize, gp.tilesize * 2);
            attackup2 = BufferedRenderer("player/Attack/boy_axe_up_2", gp.tilesize, gp.tilesize * 2);
            attackdown1 = BufferedRenderer("player/Attack/boy_axe_down_1", gp.tilesize, gp.tilesize * 2);
            attackdown2 = BufferedRenderer("player/Attack/boy_axe_down_2", gp.tilesize, gp.tilesize * 2);
            attackright1 = BufferedRenderer("player/Attack/boy_axe_right_1", gp.tilesize * 2, gp.tilesize);
            attackright2 = BufferedRenderer("player/Attack/boy_axe_right_2", gp.tilesize * 2, gp.tilesize);
            attackleft1 = BufferedRenderer("player/Attack/boy_axe_left_1", gp.tilesize * 2, gp.tilesize);
            attackleft2 = BufferedRenderer("player/Attack/boy_axe_left_2", gp.tilesize * 2, gp.tilesize);
        }
    }

    public void update() {
        for(int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).down1 == null){
                inventory.get(i).getImageInstance();
            }
        }
        try {
            if(currentweapon == null){
                hasweapon = false;
            }else{
                hasweapon = true;
            }
            //PLAYER ATTACK//
            if (KeyHandler.use) {
                KeyHandler.use = false;
                if (UI.transitionfinushed || GlobalGameThreadConfigs.CharacterStats){
                    convertItem(7);
                }else {
                    if (hasweapon){
                        if (currentweapon.Type == Type_constumable) {
                            convertItem(8);
                        }
                    }
                }
            }
            if(KeyHandler.primepowera && projectile.alive == false && primepowercool == 30 && projectile.haveresource(this)){
                projectile.Set((int) worldx, (int) worldy, direction, true, this);
                projectile.RemoveResource(this);
                GlobalGameThreadConfigs.projectilelist.add(projectile);
                primepowercool = 0;
                gp.playsound(10);
            }
            if(primepowercool < 30){
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
                isattacking = false;
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
                objindex = MainGame.hregister.checkObject(this, true);
                pickupObject(objindex);

                //ENTITY COLLISIONS
                int npcindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.NPCS);
                interactNPC(npcindex);
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
                            speed -= 1;
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
            if (worldz == 1) {
                speed = 2;
            } else {
                speed = 4;
            }
            if(health <= 0){
                GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.GameOverState;
                gp.stopmusic();
                UI.commandnum = 0;
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
        if (spritecounter > 10 && spritecounter <= 25) {
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
            worldx = currentworldx;
            worldy = currentworldy;
            hitbox.width = hitboxWidth;
            hitbox.height = hitboxHeight;
        }
        if (spritecounter > 25) {
            spritenumber = 1;
            spritecounter = 0;
            attacking = false;
            hasattacked = true;
        }
    }

    public void destroyTentity(int tileentityI) {
        if(tileentityI != 999 && GlobalGameThreadConfigs.Tentity[tileentityI].distructuble && GlobalGameThreadConfigs.Tentity[tileentityI].ItemRequirements(this) && !GlobalGameThreadConfigs.Tentity[tileentityI].invincible) {
            GlobalGameThreadConfigs.Tentity[tileentityI].health--;
            GlobalGameThreadConfigs.Tentity[tileentityI].playSE();
            GlobalGameThreadConfigs.Tentity[tileentityI].invincible = true;
            ParticlePropertyManager(GlobalGameThreadConfigs.Tentity[tileentityI], GlobalGameThreadConfigs.Tentity[tileentityI]);
            if (GlobalGameThreadConfigs.Tentity[tileentityI].health < 0){
                GlobalGameThreadConfigs.Tentity[tileentityI] = GlobalGameThreadConfigs.Tentity[tileentityI].getDestroyedForm();
                GlobalGameThreadConfigs.Tentity[tileentityI].HandleItems();
        }
        }
    }

    public void attackEntity(int attackindex, int dmg) {
        if (attackindex != 999) {
            if (GlobalGameThreadConfigs.Monsters[attackindex].invincible == false) {
                gp.playsound(6);
                int damage = dmg - GlobalGameThreadConfigs.Monsters[attackindex].defence;
                if (damage < 0) {
                    damage = 0;
                }
                GlobalGameThreadConfigs.Monsters[attackindex].health -= damage;
                ParticleAttackManager( this, GlobalGameThreadConfigs.Monsters[attackindex]);
                GlobalGameThreadConfigs.Monsters[attackindex].Hostile = true;
                GlobalGameThreadConfigs.Monsters[attackindex].HostileTime = 0;
                GlobalGameThreadConfigs.Monsters[attackindex].invincible = true;
            }
            if (GlobalGameThreadConfigs.Monsters[attackindex].dying == false) {
                if (GlobalGameThreadConfigs.Monsters[attackindex].health < 0) {
                    GlobalGameThreadConfigs.Monsters[attackindex].dying = true;
                    UI.addMessages("Killed " + GlobalGameThreadConfigs.Monsters[attackindex].name);
                    XP += GlobalGameThreadConfigs.Monsters[attackindex].XP;
                    levelUpAchiver();
                }
            }
        }
    }
    public void levelUpAchiver() {
        if (XP >= MaxXP) {
            level++;
            MaxXP = MaxXP * 2;
            maxhealth += 2;
            strength++;
            dexterity++;
            TrueAttackDamage = getAttackValues();
            defence = getDefenceValues();
            UI.addMessages("You have unlocked a new level! your now level " + level);
            gp.playsound(8);
        }
    }

    public void attacked(int monsterindex) {
        /*collision between player and monsters*/
        if (monsterindex != 999) {
            int damage = GlobalGameThreadConfigs.Monsters[monsterindex].TrueAttackDamage - defence;
            if (damage < 0) {
                damage = 0;
            }
            if (invincible == false && GlobalGameThreadConfigs.Monsters[monsterindex].dying == false) {
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
                if (gp.obj[i].EntityType == 3) {
                    if (gp.obj[i].Type == 8) {
                        gp.obj[i].Use(this);
                        gp.obj[i] = null;
                    }
                    if (gp.obj[i] != null) {
                        String text;
                        if (inventory.size() != inventorysize) {
                            inventory.add(gp.obj[i]);
                            text = "Picked up " + gp.obj[i].name;
                            gp.obj[i] = null;
                            gp.playsound(1);
                        } else {
                            text = "Your inventory is full!";
                        }

                        UI.addMessages(text);
                    }
                } else {
                    switch (gp.obj[i].name) {
                        case "chest" -> {
                            if (KeyHandler.enterpressed) {
                                if (!GlobalGameThreadConfigs.inchest) {
                                    GlobalGameThreadConfigs.inchest = true;
                                    GlobalGameThreadConfigs.CharacterStats = true;

                                } else {
                                    GlobalGameThreadConfigs.inchest = false;
                                    GlobalGameThreadConfigs.CharacterStats = false;
                                }
                            }
                        }
                        case "door" -> {
                            if (KeyHandler.enterpressed){
                                KeyHandler.enterpressed = false;
                                gp.obj[i] = new OBJdooropen(gp, (int) gp.obj[i].worldx, (int) gp.obj[i].worldy);
                        }
                    }
                    case "door open" -> {
                            if(KeyHandler.enterpressed){
                                gp.obj[i] = new OBJdoor(gp, (int) gp.obj[i].worldx/gp.tilesize, (int) gp.obj[i].worldy/gp.tilesize);
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

    }

    public void interactNPC(int i) {
        try {
            if (i != 999) {
                if (KeyHandler.enterpressed) {
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
                    if (GlobalGameThreadConfigs.NPCS[i].dialogues[GlobalGameThreadConfigs.NPCS[i].dialogueIndex] != null){
                        GlobalGameThreadConfigs.NPCS[i].speak();
                }else{
                        GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState;
                    }
            }
            } else {
                if (KeyHandler.attack == true) {
                    attacking = true;
                    gp.playsound(7);
                    KeyHandler.attack = false;
                }
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
                TrueAttackDamage = getAttackValues();
                getAttackInstance();
            }else if (code == 2) {
                currentshield = SelectedItem;
                defence = getDefenceValues();
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
                TrueAttackDamage = getAttackValues();
            }
    }

}
    public void draw(Graphics2D g2) {
        try {
            BufferedImage image = null;
            int tempscreenx = screenX;
            int tempscreeny = screenY;
            //Try Reading this line. I will wait ;D
            if(GlobalGameThreadConfigs.isinTital == false){if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed){switch (direction) {case "up": if (attacking == false){if (spritenumber == 1) {image = up1;} else if (spritenumber == 2) {image = up2;}}else{tempscreeny = screenY - gp.tilesize; if (spritenumber == 1) {image = attackup1;} else if (spritenumber == 2) {image = attackup2;}} break; case "down": if(attacking == false){if (spritenumber == 1) {image = down1;} else if (spritenumber == 2) {image = down2;}}else{if (spritenumber == 1) {image = attackdown1;} else if (spritenumber == 2) {image = attackdown2;}}break; case "right": if(attacking == false){if (spritenumber == 1) {image = right1;} else if (spritenumber == 2) {image = right2;}}else{if (spritenumber == 1) {image = attackright1;} else if (spritenumber == 2) {image = attackright2;}}break; case "left": if(attacking == false){if (spritenumber == 1) {image = left1;} else if (spritenumber == 2) {image = left2;}}else{tempscreenx = screenX - gp.tilesize; if (spritenumber == 1) {image = attackleft1;} else if (spritenumber == 2) {image = attackleft2;}} break;}}else{switch (direction){case "right" -> {if(attacking == false){image = right1;}else{if (spritenumber == 1) {image = attackright1;} else if (spritenumber == 2) {image = attackright2;}} }case "left" -> {if(attacking == false){image = left1;}else{tempscreenx = screenX - gp.tilesize; if (spritenumber == 1) {image = attackleft1;} else if (spritenumber == 2) {image = attackleft2;}}}case "up" -> {if(attacking == false){image = up1;}else{tempscreeny = screenY - gp.tilesize;if (spritenumber == 1) {image = attackup1;} else if (spritenumber == 2) {image = attackup2;}}} case "down" -> {if(attacking == false){image = down1;}else{if (spritenumber == 1) {image = attackdown1;} else if (spritenumber == 2) {image = attackdown2;}}}}}}else{switch (direction) {case "up": if (spritenumber == 1) {image = up1;} else if (spritenumber == 2) {image = up2;}break; case "down": if (spritenumber == 1) {image = down1;} else if (spritenumber == 2) {image = down2;}break; case "right": if (spritenumber == 1) {image = right1;} else if (spritenumber == 2) {image = right2;}break; case "left": if (spritenumber == 1) {image = left1;} else if (spritenumber == 2) {image = left2;} break;}}
            if(KeyHandler.jump == true){
                jumpaction++;
                if(jumpaction < 25){
                    if(isup == true) {
                        worldz = 1;
                        jumpstate++;
                        image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                    }
                }else{
                    isup = false;
                    jumpstate--;
                    image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                    if(jumpstate < 0) {
                        jumpaction = 0;
                        KeyHandler.jump = false;
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
            //g2.setColor(new Color(255, 232, 0, 220));
            //GlobalGameThreadConfigs.g2.fillRect(tempscreenx-24, tempscreeny-24, gp.tilesize*2, gp.tilesize*2);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public BufferedImage scaleimage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

}