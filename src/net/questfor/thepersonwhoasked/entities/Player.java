package net.questfor.thepersonwhoasked.entities;

import net.questfor.thepersonwhoasked.Maingam.*;

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
    public int isred = 1;
    public boolean maxred = false;

    public Player(KeyHandler keyHandler, MainGame gpp) {
        super(gpp);
            this.keyHandler = keyHandler;
            setdefaultvalues();
            getImageInstance();
            screenX = MainGame.screenwidth / 2 - (MainGame.tilesize / 2);
            screenY = MainGame.screenheight / 2 - (MainGame.tilesize / 2);
            hitbox = new Rectangle();
            hitbox.x = 8;
            hitbox.y = 16;
            hitbox.width = 32;
            hitbox.height = 32;
            hitboxdefaultx =  hitbox.x;
            hitboxdefaulty = hitbox.y;
            EntityType = 0;

    }

    public void setdefaultvalues() {
        try {
            //DEFAULT VALUES
            worldx = MainGame.tilesize * 23;
            worldy = MainGame.tilesize * 21;
            speed = 4;
            direction = "right";
            worldz = 1;
            maxhealth = 10;
            health = maxhealth;
            invincible = false;
        } catch (Exception e) {
            crash.main(e);
        }
    }

    public void getImageInstance() {
        //IMAGES
        up1 = BufferedRenderer("player/boy_up_1");
        up2 = BufferedRenderer("player/boy_up_2");
        down1 = BufferedRenderer("player/boy_down_1");
        down2 = BufferedRenderer("player/boy_down_2");
        right1 = BufferedRenderer("player/boy_right_1");
        right2 = BufferedRenderer("player/boy_right_2");
        left1 = BufferedRenderer("player/boy_left_1");
        left2 = BufferedRenderer("player/boy_left_2");
    }

    public void update() {
        //MOVEMENT
        try {
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
                if(invincible == true){
                    hitTime++;
                    if(hitTime > 30){
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
            if(GlobalGameThreadConfigs.isinTital == false) {
                //OBJECT COLLISIONS
                int objindex = MainGame.hregister.checkObject(this, true);
                pickupObject(objindex);

                //ENTITY COLLISIONS
                int npcindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.NPCS);
                interactNPC(npcindex);
                int Monsterindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
                interactMonster(Monsterindex);
                //CHECK EVENT
                gp.ehandler.returnEvent();
                KeyHandler.enterpressed = false;
            }
            if (GlobalGameThreadConfigs.isinTital == false){
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
        } else if (GlobalGameThreadConfigs.isinTital == true && hitboxe == false) {
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
            spritecounter++;
                if (spritecounter > 12) {
                    if (spritenumber == 1) {
                        spritenumber = 2;
                    } else if (spritenumber == 2) {
                        spritenumber = 1;
                    }
                    spritecounter = 0;
                }
            if(worldz == 1){
                speed = 2;
            }else{
                speed = 4;
            }
        } catch (Exception e) {
            crash.main(e);
        }
    }
    private void interactMonster(int monsterindex) {
        /*collision between player and monsters*/
        if(monsterindex != 999) {

            if (invincible == false){
                health -= 1;
                invincible = true;
        }
            worldx += 3;
            worldy -= 3;
        }
    }

    public void pickupObject(int i) {
        try {
            if (i != 999) {
            }
        }catch (Exception e){
            crash.main(e);
        }
    }
    public void interactNPC(int i){
        try {
            if (i != 999) {
                if (KeyHandler.enterpressed) {
                    GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.dialogueState;
                    GlobalGameThreadConfigs.NPCS[i].speak();
                }
            }
        }catch (Exception e){
            crash.main(e);
        }
    }

    public void draw(Graphics2D g2) {
        try {
            BufferedImage image = null;
            if(GlobalGameThreadConfigs.isinTital == false){
            if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed){
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
            }}else{
                switch (direction){
                    case "right" -> image = right1;
                    case "left" -> image = left1;
                    case "up" -> image = up1;
                    case "down" -> image = down1;
                }
            }
            }else{
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
                }
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
                if(invincible == false){
                    up1 = BufferedRenderer("player/boy_up_1");
                    up2 = BufferedRenderer("player/boy_up_2");
                    down1 = BufferedRenderer("player/boy_down_1");
                    down2 = BufferedRenderer("player/boy_down_2");
                    right1 = BufferedRenderer("player/boy_right_1");
                    right2 = BufferedRenderer("player/boy_right_2");
                    left1 = BufferedRenderer("player/boy_left_1");
                    left2 = BufferedRenderer("player/boy_left_2");
                    isred = 1;
                }
            }
            g2.drawImage(image, screenX, screenY, null);
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
