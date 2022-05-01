package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
//is the parent class for all entities including player
public class LivingEntity {
    //WORLD//
    public MainGame gp;
    public double worldx;
    public double worldy;
    public double worldz = 0;
    public double speed;
    //HEALTH//
    public int maxhealth;
    public int health;
    public int isred = 1;
    public boolean alive = true;
    public boolean dying = false;
    public int regenerationcooldown = 0;
    //RENDERER//
    public BufferedImage image, image2, image3;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackup1, attackup2, attackdown1, attackdown2,
            attackleft1, attackleft2, attackright1, attackright2;
    //MOVEMENT AND ANIMATION//
    public String direction = "down";
    public int spritecounter = 0;
    public int spritenumber = 1;
    int animationLength = 0;
    //HITBOX//
    public Rectangle hitbox;
    public Rectangle attackHitbox = new Rectangle(0, 0, 0, 0);
    public int hitboxdefaultx, hitboxdefaulty;
    public boolean collision = false;
    public boolean hitboxe = false;
    //AI//
    public int actionLock = 0;
    public boolean Hostile = false;
    public int HostileTime = 0;
    //ATTACK//
    public boolean invincible = false;
    public int hitTime = 0;
    //DATA//
    public int primepowercool = 0;
    public int EntityType; //0 = player, 1 = monster, 2 = NPC, 3 = Item, 4 = Object.
    public String name;
    public ArrayList<LivingEntity> inventory = new ArrayList<>();
    public int MaxMana;
    public int Mana;
    public Projectile projectile;

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
    public int defenceValue = 1;
    public int Value = 1;
    public int inventorysize = 20;
    public String description = "";
    public int Type;
    public int Type_sword = 1, Type_constumable = 2, Type_tool = 3, Type_object = 4, Type_armor = 5, Type_shield = 6, Type_projectile = 7;
    public int SLOTTYPE; // 1 for mainhand, 2 for lefthand, 3 for helmet, 4 for chestplate, 5 for leggings, 6 for boots
    public int UseCost;

    //FUNCTIONS//
    public LivingEntity(MainGame gpp){
        this.gp = gpp;
    }
    public void setAction(){}
    public void getImageInstance(){}
    public void Angry(){}
    public void speak(){
        //dialogue functions

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        UI.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case "up" -> {direction = "down";worldy += MainGame.tilesize;}
            case "down" -> {direction = "up"; worldy -= MainGame.tilesize;}
            case "right" -> {direction = "left"; worldx += MainGame.tilesize;}
            case "left" -> {direction = "right"; worldx -= MainGame.tilesize;}
        }
    }
    public void update(){
        Regenerate();
        /*AI for Monsters And NPCS*/
        setAction();
        hitboxe = false;
        gp.hregister.checkTile(this);
        gp.hregister.checkObject(this, false);
        gp.hregister.EntityColide(this, GlobalGameThreadConfigs.NPCS);
        gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
        boolean ContactPLayer = gp.hregister.PlayerColide(this);
        if(EntityType == 2 && ContactPLayer){
            AttackPLayer(TrueAttackDamage);
        }
        if(primepowercool < 30){
            primepowercool++;
        }
        if (!hitboxe) {
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
        }
        spritecounter++;
        if (spritecounter > 12) {
            if (spritenumber == 1) {
                spritenumber = 2;
            } else if (spritenumber == 2) {
                spritenumber = 1;
            }
            spritecounter = 0;
        }
        if(invincible){
            hitTime++;
            if(hitTime > 40){
                invincible = false;
                hitTime = 0;
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
        //RENDERER
        try {
            double screenX = (worldx - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldy - MainGame.player.worldy + MainGame.player.screenY;
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
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
                //HP BAR
                if (EntityType == 2 && Hostile) {
                    double onescale = gp.tilesize / maxhealth;
                    double HPValue = onescale * health;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect((int) (screenX - 1), (int) screenY - 16, gp.tilesize + 2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect((int) screenX, (int) screenY - 15, (int) HPValue, 10);
                    HostileTime++;
                    if (HostileTime > 600) {
                        HostileTime = 0;
                        Hostile = false;
                    }

                }

                if (invincible) {
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
                if (dying) {
                    DieAnimation(g2);
                }
                if (isred == 2) {
                    if (invincible == false) {
                        getImageInstance();
                        isred = 1;
                    }
                }
                g2.setFont(g2.getFont().deriveFont(Font.BOLD));
                g2.setColor(Color.BLACK);
                if (EntityType != 3) {
                    if(EntityType != 4){
                    g2.drawString("level: " + level, (int) screenX, (int) (screenY - 30));
            }
            }
                g2.drawImage(image, (int) screenX, (int) screenY, MainGame.tilesize, MainGame.tilesize, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
        }catch (Exception e){
            crash.main(e);
        }
    }
    //DIE ANIMATION
    private void DieAnimation(Graphics2D g2d) {
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
}
