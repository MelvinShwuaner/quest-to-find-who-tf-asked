package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.*;
import net.questfor.thepersonwhoasked.World.Light;
import net.questfor.thepersonwhoasked.entities.AI.Path;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static net.questfor.thepersonwhoasked.GlobalProperties.gp;
import static net.questfor.thepersonwhoasked.Maingam.MainGame.currentmap;

//is the parent class for all entities including player
public class LivingEntity extends Data {
    static final long serialVersionUID = -6942014L;
    //WORLD//
    public MainGame gp;
    public transient Path path;
    public boolean attacking = false;
    public boolean hasattacked = false;
    public boolean LightSource, burning;
    public int Lightposition = 0;
    public boolean foundposition = false;
    public Light light;
    public  double worldx;
    public  double worldy;
    public boolean goingup;
    public int defaultspeed;
    public int knockbackcounter = 0;
    public  double worldz = 4;
    public boolean high = true;
    public double speed;
    //HEALTH//
    public int maxhealth;
    public int health;
    public int burningcounter = 0;
    public int isred = 1;
    public boolean alive = true;
    public boolean dying = false;
    public int regenerationcooldown = 0;
    public int tile;
    //RENDERER//
    public transient BufferedImage image, image2, image3;
    public transient  BufferedImage up1, up2, down1, down2, down3, left1, left2, right1, right2;
    public transient BufferedImage attackup1, attackup2, attackup3, attackdown1, attackdown2, attackdown3,
            attackleft1, attackleft2, attackleft3, attackright1, attackright2, attackright3;
    boolean drawingpath = false;
    //MOVEMENT AND ANIMATION//
    public String direction = "down";
    public String frozendirection = direction;
    public int spritecounter = 0;
    public int spritenumber = 1;
    int animationLength = 0;
    //HITBOX//
    public Rectangle hitbox;
    public int i = 0;
    public int jumpstate = 0;
    public int jumpaction = 0;
    public Rectangle attackHitbox = new Rectangle(0, 0, 0, 0);
    public int hitboxdefaultx, hitboxdefaulty;
    public boolean collision = false;
    public boolean hitboxe = false;
    //AI//
    public int actionLock = 0;
    public int objindex;

    public  boolean isup = false;
    public boolean Hostile = false;
    public boolean frozen = false;
    public double taskx = 0, tasky = 0;
    public boolean forgiveondeath = true;
    public boolean onpath = false;
    public LivingEntity target;
    public double distancex = 0, distancey = 0;
    public double previoustaskx = 1, previoustasky = 1;
    public int HostileTime = 0;
    //ATTACK//
    public boolean invincible = false;
    public int hitTime = 0;
    //DATA//
    public int primepowercool = 0;
    public int EntityType; //0 = player, 1 = monster, 2 = NPC, 3 = Item, 4 = Object.
    public String name = "";
    public ArrayList<LivingEntity> inventory = new ArrayList<>();
    public int MaxMana;
    public int Mana;
    public int Ammo;
    public Projectile projectile;
    public int cantalk = 0;
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;
    public int level;
    public int strength;
    public int hunger;
    public int defence;
    public int dexterity;
    public int attackspeed;
    public int XP;
    public int MaxXP;
    public int bobux;

    public LivingEntity currentweapon;
    public LivingEntity currentshield;
    public int TrueAttackDamage;
    /**OBJECT DATA**/
    public int AttackValue = 1;
    public int stacksize = 1;
    public int maxstacksize = 1;
    public int defenceValue = 1;
    public int Value = 1;
    public boolean first, secound;
    public int inventorysize = 20;
    public String description = "";
    public int Type = 0;
    public int frames = 2;
    public int Type_sword = 1, Type_constumable = 2, Type_pickaxe = 3, Type_armor = 4, Type_projectile = 5, Type_Current = 6, Type_axe = 7, Type_shovel = 8;
    public int UseCost;
    //SMELTING
    public boolean fuel; public boolean smeltable; public LivingEntity Outcome; public boolean smelting = false; public int cool = 0; public int maxcool = 50; public int colspeed = 1; public int hasfinushedcol = 0;
    //RECIPE
    public boolean[] slot =  new boolean[9];
    public boolean NBTDATA = false;
    public boolean jumping = false, canjump = true;

    //FUNCTIONS//
    public LivingEntity(MainGame gpp){
        this.gp = gpp;
        path = new Path();
        LightSource = true;

    }
    public void setAction(){}
    public void updateLight(int i){
        int x = 0;
        int y = 0;
        switch (direction){
            case "right" -> x = 35;
            case "left" -> x = -35;
            case "up" -> y = -35;
            case "down" -> y = 35;
        }
        light = new Light((int)worldx+x, (int) (worldy+y), 300);
        GlobalGameThreadConfigs.lights[currentmap][i] = light;
        Lightposition = i;

    }
    public void getImageInstance(){}
    public void increasecool(){
        cool += colspeed;
        if(cool == maxcool){
            hasfinushedcol = 1;
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


    public void getAttackInstance(){}
    public void Angry(){}
    public void makemeHostile(LivingEntity Target){
        Hostile = true;
        target = Target;
    }
    public void speak(){
        //dialogue functions

        UI.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "right" -> direction = "left";
            case "left" -> direction = "right";
        }
    }
    public void update() {
        updateimage();
        if (health > 0){
            Regenerate();
    }else{
            dying = true;
        }

         if(LightSource){
            if(!foundposition){
                for(int i = 0; i < GlobalGameThreadConfigs.lights[1].length; i++){
                    if(GlobalGameThreadConfigs.lights[currentmap][i] == null){
                        foundposition = true;
                        updateLight(i);
                        break;
                    }
                }
            }
        }
        if (MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Monsters) || MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.NPCS) || MainGame.hregister.returntileworldz(this)) {
            if (!isup) {
                worldz--;
                getImageInstance();
                updatehitbox();
                getAttackInstance();
            }
        }
        /*AI for Monsters And NPCS*/
        if(frozen){
            checkCollision();
            if(hitboxe){
                knockbackcounter = 0;
                frozen = false;
                speed = defaultspeed;
            }else{
                if(frozendirection == null){
                    frozendirection = direction;
                    frozen = false;
                }
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
        setAction();
        checkCollision();
        if(hitboxe){
            if(!jumping) {
                jumping = true;
                isup = true;
            }
        }
        if(primepowercool < 30){
            primepowercool++;
        }
        if(dialogues[dialogueIndex] == null){
            cantalk++;
        }
        if(cantalk > 30){
            cantalk = 0;
            dialogueIndex = 0;
        }
        if (!hitboxe)
            if(!frozen){{
            switch (direction){
                case"up":
                    worldy = worldy - speed;
                    break;
                case "down":
                    worldy = worldy + speed;
                    break;

                case "right":
                    worldx = worldx + speed;
                    break;
                case "left":
                    worldx = worldx - speed;
                    break;
            }
            if(LightSource)
                updateLight(Lightposition);
        }}
        if(!attacking){
        spritecounter++;
        if (spritecounter > 12) {
            if (spritenumber == 1) {
                spritenumber = 2;
            } else if (spritenumber == 2) {
                spritenumber = 1;
            }
            spritecounter = 0;
        }}
        if(invincible){
            hitTime++;
            if(hitTime > 40){
                invincible = false;
                hitTime = 0;
            }
        }
        if(health <= 0){
            dying = true;
        }
    }


    public void updateimage() {
        if(up1 == null && image == null && down1 == null){
            getImageInstance();
        }
    }
    public void set(){}

    public void AttackNPC(int trueAttackDamage, int npcindex) {
        if (!GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].invincible) {
            int damage = trueAttackDamage - GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].defence;
            if (damage <= 0) {
                damage = 0;
            }
            GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].health -= damage;
            if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].name.equals("Old man")) {
                UI.addMessages("Help me im being hurt!");
            } else if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].name.equals("Helper")) {
                GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].Hostile = true;
            }
            GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].invincible = true;
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                gp.playsound(5);
            }
        }
    }
    public void HandleItems(){}
    public void DropItems(LivingEntity droppedItem){
        for(int i = 0; i < GlobalGameThreadConfigs.obj[1].length; i++){
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][i] == null) {
                GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = droppedItem;
                int I = new Random().nextInt(100) + 1;
                if (I > 50){
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx = worldx + gp.tilesize/2;
            } else {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx = worldx - gp.tilesize/2;
                }
                I = new Random().nextInt(100) + 1;
                if(I > 50){
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy = worldy + gp.tilesize/2;
                }else {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy = worldy - gp.tilesize/2;
                }
                break;
            }
        }
    }
    private void Regenerate() {
        if(!Hostile) {
            regenerationcooldown++;
            if (regenerationcooldown > 200) {
                if (health < maxhealth){
                    health++;
                regenerationcooldown = 0;
            }
        }
        }
    }
    public void Use(LivingEntity target){}
    public void AttackPLayer(int trueAttackDamage){
        if(!MainGame.player.invincible){
            int damage = trueAttackDamage - gp.player.defence;
            if(damage < 0){
                damage = 0;
            }
            MainGame.player.health-= damage;
            UI.addMessages("You have been hit! health is now to "+gp.player.health);
            MainGame.player.invincible = true;
            gp.playsound(5);
        }
    }
    public void draw(Graphics2D g2){
        double screenX = (worldx - MainGame.player.worldx + MainGame.player.screenX);
        double screenY = worldy - MainGame.player.worldy + MainGame.player.screenY;
        if(path == null){
            path = new Path();
        }
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spritenumber == 1) {
                    image = up1;
                } else if (spritenumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spritenumber == 1) {
                    image = down1;
                } else if (spritenumber == 2) {
                    image = down2;
                }
                break;
            case "right":
                if (spritenumber == 1) {
                    image = right1;
                } else if (spritenumber == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spritenumber == 1) {
                    image = left1;
                } else if (spritenumber == 2) {
                    image = left2;
                }
                break;
        }
        if(jumping){
            jumpaction++;
            if(jumpaction < 25){
                if(isup) {
                    if (gp.hregister.checkWALL(this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz, GlobalGameThreadConfigs.NPCS, this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz,  GlobalGameThreadConfigs.Monsters, this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz, GlobalGameThreadConfigs.obj, this) ) {
                        if (jumpaction == 1) {
                            worldz++;
                        }
                        jumpstate++;
                        if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                                (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                                && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                                (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                            if(image != null){
                            if(image.getWidth()+jumpstate > 0 && image.getHeight()+jumpstate > 0){
                            image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                    }}}
                }}
            }else{
                isup = false;
                jumpstate--;
                try {
                    if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                            (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                            && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                            (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                        if(image != null){
                            if(image.getWidth()+jumpstate > 0 && image.getHeight()+jumpstate > 0){

                                image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                            }}                }}catch (Exception e){
                    System.out.println("Catched null pointer exeption");
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
                int worldx = path.pathlist.get(i).col * gp.tilesize;
                int worldy = path.pathlist.get(i).row * gp.tilesize;
                double screenx = (worldx - MainGame.player.worldx + MainGame.player.screenX);
                double screeny = worldy - MainGame.player.worldy + MainGame.player.screenY;
                g2.fillRect((int) screenx, (int) screeny, gp.tilesize, gp.tilesize);
            }
        }
        //RENDERER
        try {
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                //HP BAR
                if ((EntityType == 2 || EntityType == 1) && Hostile) {
                    double onescale = gp.tilesize / maxhealth;
                    double HPValue = onescale * health;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect((int) (screenX - 1), (int) screenY - 16, gp.tilesize + 2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect((int) screenX, (int) screenY - 15, (int) HPValue, 10);
                }
                if (Hostile) {
                    HostileTime++;
                    if (HostileTime > 2400) {
                        HostileTime = 0;
                        Hostile = false;
                    }
                }
                if(EntityType != 4){
                if (invincible) {
                    if(image != null){
                    for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                            int p = image.getRGB(x, y);
                            int a = (p >> 24) & 0xff;
                            int r = (p >> 16) & 0xff;
                            p = (a << 24) | (r << 16) | (0 << 8) | 0;
                            image.setRGB(x, y, p);
                            isred = 2;
                        }
                    }}
                }
                if (isred == 2) {
                    if (invincible == false) {
                        getImageInstance();
                        isred = 1;
                    }
                }
            }
                if (dying) {
                    DieAnimation(g2);
                }
                g2.setFont(g2.getFont().deriveFont(Font.BOLD));
                g2.setColor(Color.BLACK);
                if (EntityType != 3) {
                    if (EntityType != 4) {
                        if (Type != Type_projectile){
                            g2.setFont(g2.getFont().deriveFont(10F));
                            g2.drawString("level: " + level, (int) screenX, (int) (screenY - 30));
                    }
                }
            }
                if(worldz > gp.player.worldz){
                    float Xdistance;
                    float Ydistance;
                    int x = (int) Math.round(worldx/gp.tilesize);
                    int y = (int) Math.round(worldy/gp.tilesize);
                    if (x > gp.player.worldx / gp.tilesize) {
                        Xdistance = (float) (x - (gp.player.worldx / gp.tilesize));
                    }else{
                        Xdistance = (float) ((gp.player.worldx / gp.tilesize) - x);
                    }
                    if (y > gp.player.worldy / gp.tilesize) {
                        Ydistance = (float) (y - (gp.player.worldy / gp.tilesize));
                    }else{
                        Ydistance = (float) ((gp.player.worldy / gp.tilesize) - y);
                    }
                    if (Ydistance < 2 && Xdistance < 2){
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    }
                }
                g2.drawImage(image, (int) screenX, (int) screenY, null);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                drawwalls(g2, this);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            }
        }
            catch (Exception e){}
    }
    //DIE ANIMATION
    protected void DieAnimation(Graphics2D g2d) {
        animationLength++;
        int startframe = 5;
        if(animationLength <= startframe){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe && animationLength <= startframe*2){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*2 && animationLength <= startframe*3){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe*3 && animationLength <= startframe*4){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*4 && animationLength <= startframe*5){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe*5 && animationLength <= startframe*6){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*6 && animationLength <= startframe*7){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe*7 && animationLength <= startframe*8){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*8){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));alive = false;}
    }

    public BufferedImage BufferedRenderer(String imagePath, int width, int height){
        //OPTIMIZES THE RENDERER TO MAKE IT MORE EFFICIENT
        UtilityTool utool = new UtilityTool();
        BufferedImage ScaledImage = null;
        try{
            ScaledImage = ImageIO.read(getClass().getResourceAsStream("/entities/"+imagePath+".png"));
            ScaledImage = utool.scaleimage(ScaledImage, width, height);
        }catch (Exception e) {
            crash.main(e);
        }
        return ScaledImage;
    }

    /**PARTICLES**/
    public Color getparticleColor(){return null;}
    public void attackEntity(int attackindex, int dmg) {
        if (attackindex != 999) {
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible == false) {
                if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                        (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                        && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                        (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                    gp.playsound(6);
                }
                dealKnockback(GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex]);
                int damage = dmg - GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].defence;
                if (damage < 0) {
                    damage = 0;
                }
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health -= damage;
                ParticleAttackManager( this, GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex]);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].makemeHostile(this);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].HostileTime = 0;
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible = true;
            }
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying == false) {
                if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health < 0) {
                    GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying = true;
                    UI.addMessages("Killed " + GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].name);
                    XP += GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].XP;
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
            gp.playsound(8);

        }
    }
    public int getAttackValues(){return 0;}
    public int getDefenceValues(){return 0;}

    public int getparticleSize(){return 0;}
    public int getparticlespeed(){return 0;}
    public int getparticleMaxHealth(){return 0;}
    //SET PARTICLES AND THERE PROPERTIES
    public void ParticlePropertyManager(LivingEntity Source, LivingEntity target){Color texture = Source.getparticleColor(); int size = Source.getparticleSize(); int speed = Source.getparticlespeed(); int MaxHealth = Source.getparticleMaxHealth();
        Particle p1 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, -1);
        Particle p2 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, -1);
        Particle p3 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, 1);
        Particle p4 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, 1);
        GlobalGameThreadConfigs.particleList.add(p1);
        GlobalGameThreadConfigs.particleList.add(p2);
        GlobalGameThreadConfigs.particleList.add(p3);
        GlobalGameThreadConfigs.particleList.add(p4);
    }
    public void ParticleAttackManager(LivingEntity Source, LivingEntity target){Color texture = target.getparticleColor(); int size = target.getparticleSize(); int speed = target.getparticlespeed(); int MaxHealth = target.getparticleMaxHealth();
        Particle p1 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, -1);
        Particle p2 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, -1);
        Particle p3 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, 1);
        Particle p4 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, 1);
        GlobalGameThreadConfigs.particleList.add(p1);
        GlobalGameThreadConfigs.particleList.add(p2);
        GlobalGameThreadConfigs.particleList.add(p3);
        GlobalGameThreadConfigs.particleList.add(p4);
    }


    public void DestroyOBJ(int tileentityI) {
        if (tileentityI != 999 && !GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].invincible) {
            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].name.equals("Brick wall")) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].ItemRequirements(this)) {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].health-= TrueAttackDamage;
                    if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                            (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                            && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                            (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].playSE();}
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].invincible = true;
                    ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI], GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI]);
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].health <= 0) {
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI] = GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].getDestroyedForm();
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].HandleItems();
                    }
                }
            }
        }
    }

    public boolean ItemRequirements(LivingEntity SourceEntity){return false;}
    public void playSE(){}
    public LivingEntity getDestroyedForm(){return null;}
    public void open(int x, int y, int z, int i){}
    public void searchPath(double taskX, double taskY){
        if(path == null){
            path = new Path();
        }
        int startcol = (int) ((worldx+hitbox.x)/gp.tilesize);
        int startrow = (int) ((worldy+hitbox.y)/gp.tilesize);
        path.setNodes(startcol, startrow, (int) taskX, (int) taskY, (int) worldz, this);
        if(path.search()){
            int nextx = path.pathlist.get(0).col*gp.tilesize;
            int nexty = path.pathlist.get(0).row*gp.tilesize;
            int enleftx = (int) (worldx+hitbox.x);
            int enrightx = (int) (worldx+hitbox.x+hitbox.width);
            int enupy = (int) (worldy+hitbox.y);
            int endowny = (int) (worldy+hitbox.y+hitbox.height);
            if(enupy > nexty && enleftx >= nextx && enrightx < nextx +gp.tilesize){
                direction = "up";
            }else if(enupy < nexty && enleftx >= nextx && enrightx < nextx +gp.tilesize){
                direction = "down";
            } else if(enupy >= nexty && endowny < nexty + gp.tilesize){
                if(enleftx > nextx){
                    direction = "left";
                }else{
                    direction = "right";
                }
            }else if(enupy > nexty && enleftx > nextx){
                direction = "up";
                checkCollision();
                if(hitboxe){
                    direction = "left";
                }
            }else if(enupy > nexty && enleftx < nextx){
                direction = "up";
                checkCollision();
                if(hitboxe){
                    direction = "right";
                }
            }else if(enupy < nexty && enleftx > nextx){
                direction = "down";
                checkCollision();
                if(hitboxe){
                    direction = "left";
                }
            }else if(enupy < nexty && enleftx < nextx){
                direction = "down";
                checkCollision();
                if(hitboxe){
                    direction = "right";
                }
            }
            int nextcol = path.pathlist.get(0).col;
            int nextrow = path.pathlist.get(0).row;
            if(nextcol == taskX && nextrow == taskY) {
                if (forgiveondeath){
                    onpath = false;
                    Hostile = false;
            }
            }
        }else{
            actionLock++;
            Random random = new Random();
            int I = random.nextInt(100)+1;
            if (actionLock > 30) {
                    if (I > 50){
                        if (worldy < Math.round(taskY*gp.tilesize)) {
                            direction = "down";
                        } else {
                            direction = "up";
                        }
                }else{
                        if (worldx < Math.round(taskX*gp.tilesize)) {
                            direction = "right";
                        } else {
                            direction = "left";
                        }}
                    actionLock = 0;

        }}}
    public void checkCollision(){
        hitboxe = false;
        gp.hregister.checkTile(this);
        int npcindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.NPCS);
        if(EntityType == 1 && npcindex != 999){
            AttackNPC(TrueAttackDamage, npcindex);
        }
        gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);

        DestroyOBJ(gp.hregister.EntityColide(this, GlobalGameThreadConfigs.obj));
        boolean ContactPLayer = gp.hregister.PlayerColide(this);
        if(EntityType == 1 && ContactPLayer){
            AttackPLayer(TrueAttackDamage);
        }
    }
    public BufferedImage scaleimage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
    public void drawwalls(Graphics2D g2, LivingEntity entity) {
        int worldcol = (int) Math.round(entity.worldx/MainGame.tilesize);
        int worldrow = (int) Math.round(entity.worldy / MainGame.tilesize);
        int worldlayer = 0;
        while (worldlayer < MainGame.maxworldlayer) {

            int tileID = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer];
            int worldX = worldcol * MainGame.tilesize;
            int worldY = worldrow * MainGame.tilesize;
            double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
            if (tileID != 46) {
                boolean shouldrenderr = true;
                if(worldlayer+1 < MainGame.maxworldlayer) {
                    if(worldlayer > gp.player.worldz){
                        int i = (int) (worldlayer - gp.player.worldz);
                        for(int d = 1; d < i; d++){
                            if(!gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer - d]].transparent){
                                boolean up, down, right, left;
                                if(worldrow+1 < 200){
                                    up = !gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow+1][worldlayer - d]].transparent;
                                }else{
                                    up = true;
                                }
                                if(worldrow-1 > 0){
                                    down = !gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow-1][worldlayer - d]].transparent;
                                }else{
                                    down = true;
                                }
                                if(worldcol+1 < 200){
                                    right =!gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol+1][worldrow][worldlayer - d]].transparent;
                                }else{
                                    right = true;
                                }
                                if(worldcol-1 > 0){
                                    left = !gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol-1][worldrow][worldlayer - d]].transparent;
                                }else{
                                    left = true;
                                }
                                if(up && down && right && left){
                                    shouldrenderr = false;
                                }
                            }
                        }
                    }
                }
                if(shouldrenderr)  {
                    boolean shouldrender = true;
                    if(worldlayer >= entity.worldz){
                    if (worldlayer > entity.worldz) {
                            boolean up, down, right, left;
                            if(worldrow+1 < 200){
                                up = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow +1][worldlayer] != 46;
                            }else{
                                up = true;
                            }
                            if(worldrow-1 > 0){
                                down = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow -1][worldlayer] != 46;
                            }else{
                                down = true;
                            }
                            if(worldcol+1 < 200){
                                right = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol +1][worldrow][worldlayer] != 46;
                            }else{
                                right = true;
                            }
                            if(worldcol-1 > 0){
                                left = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol -1][worldrow][worldlayer] != 46;
                            }else{
                                left = true;
                            }
                            if(up && down && right && left){
                                shouldrender = false;

                            }
                            if(shouldrender){
                                int i = (int) (worldlayer - gp.player.worldz);
                                int e = 0;
                                for(int d = 1; d <= i; d++){
                                    if (gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer - d] == 46) {
                                        e++;
                                    }
                                }
                                if(e == i){
                                    shouldrender = false;
                                }
                            }}else{
                        shouldrender = false;
                    }
                            if(shouldrender){
                                g2.drawImage(gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer]].down[(int) (worldlayer -gp.player.worldz)], (int) screenX, (int)screenY, null);
                                g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                                g2.fillRect((int) screenX, (int) screenY, gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer]].down[(int) (worldlayer -gp.player.worldz)].getWidth(), gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer]].down[(int) (worldlayer -gp.player.worldz)].getHeight());
                            }else{
                                g2.drawImage(gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer]].image, (int) screenX, (int)screenY, null);
                                if (gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer] == 52) {
                                    gp.tilemanager.mapspritecounter[MainGame.currentmap][worldcol][worldrow][worldlayer]++;
                                    if (gp.tilemanager.mapspritecounter[MainGame.currentmap][worldcol][worldrow][worldlayer] >= 5) {
                                        gp.tilemanager.mapspritecounter[MainGame.currentmap][worldcol][worldrow][worldlayer] = 0;
                                        gp.tilemanager.mapspritenumber[MainGame.currentmap][worldcol][worldrow][worldlayer]++;
                                        if (gp.tilemanager.mapspritenumber[MainGame.currentmap][worldcol][worldrow][worldlayer] == 4) {
                                            gp.tilemanager.mapspritenumber[MainGame.currentmap][worldcol][worldrow][worldlayer] = 0;
                                        }
                                    }
                                    g2.drawImage(gp.tilemanager.fire[gp.tilemanager.mapspritenumber[MainGame.currentmap][worldcol][worldrow][worldlayer]], (int) (screenX), (int) (screenY), null);
                                }
                                g2.setColor(new Color(255, 255, 255, worldlayer * 8));
                                g2.fillRect((int) screenX, (int) screenY, 48, 48);
                            }
                        }

                }}
            worldlayer++;
            }

        }



    public void dealKnockback(LivingEntity entity){
        entity.frozendirection = direction;
        entity.speed = 10;
        entity.frozen = true;
    }

    public void damageprojectile(int i) {
        if(i != 999){
            ParticlePropertyManager(GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i], GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i]);
            GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i].alive = false;

        }
    }
    public LivingEntity replicate(){return null;}
    public void Place(double x, double y, double z, int i){}
}
