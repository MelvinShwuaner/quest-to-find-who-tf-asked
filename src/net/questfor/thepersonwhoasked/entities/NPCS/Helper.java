package net.questfor.thepersonwhoasked.entities.NPCS;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;
import net.questfor.thepersonwhoasked.objects.OBJHeart;
import net.questfor.thepersonwhoasked.objects.OBJ_COIN_BRONZE;
import net.questfor.thepersonwhoasked.objects.OBJ_MANA_CRYSTAL;
import net.questfor.thepersonwhoasked.objects.Projectiles.OBJ_FireBall;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Helper extends LivingEntity {
    public boolean attacking = false;
    public boolean hasattacked = false;

    public Helper(MainGame gpp) {
        super(gpp);
        EntityType = 2;
        name = "Helper";
        speed = 3;
        maxhealth = 20;
        TrueAttackDamage = 5;
        defence = 1;
        health = maxhealth;
        hitbox = new Rectangle(3, 18, 42, 30);
        hitbox.height = 30;
        hitboxdefaultx = hitbox.x;
        hitboxdefaulty = hitbox.y;
        XP = 7;
        level = 5;
        projectile = new OBJ_FireBall(gpp);

    }

    public void getImageInstance() {
        up1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_up_1", gp.tilesize, gp.tilesize);
        up2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_up_2", gp.tilesize, gp.tilesize);
        down1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_down_1", gp.tilesize, gp.tilesize);
        down2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_down_2", gp.tilesize, gp.tilesize);
        right1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_right_1", gp.tilesize, gp.tilesize);
        right2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_right_2", gp.tilesize, gp.tilesize);
        left1 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_left_1", gp.tilesize, gp.tilesize);
        left2 = BufferedRenderer("NPCS/Helper/Walking sprites/boy_left_2", gp.tilesize, gp.tilesize);
    }

    @Override
    public void getAttackInstance() {
        attackup1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_up_1", (int) (gp.tilesize + worldz), (int) (gp.tilesize + worldz) * 2);
        attackup2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_up_2", (int) (gp.tilesize + worldz), (int) (gp.tilesize + worldz) * 2);
        attackdown1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_down_1", (int) (gp.tilesize + worldz), (int) (gp.tilesize + worldz) * 2);
        attackdown2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_down_2", (int) (gp.tilesize + worldz), (int) (gp.tilesize + worldz) * 2);
        attackright1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_right_1", (int) (gp.tilesize + worldz) * 2, (int) (gp.tilesize + worldz));
        attackright2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_right_2", (int) (gp.tilesize + worldz) * 2, (int) (gp.tilesize + worldz));
        attackleft1 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_left_1", (int) (gp.tilesize + worldz) * 2, (int) (gp.tilesize + worldz));
        attackleft2 = BufferedRenderer("NPCS/Helper/Attacking sprites/boy_attack_left_2", (int) (gp.tilesize + worldz) * 2, (int) (gp.tilesize + worldz));
    }

    public void setAction() {
        if (!frozen) {
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
                    for (int i = 0; i < GlobalGameThreadConfigs.Monsters[1].length; i++) {
                        if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] != null) {
                            if (distancex < previoustaskx) {
                                if (worldx > GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx) {
                                    distancex = worldx - GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx;
                                } else {
                                    distancex = GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx - worldx;
                                }
                                taskx = GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx;
                                previoustaskx = distancex;

                            }
                            if (distancey < previoustasky) {
                                if (worldy > GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy) {
                                    distancey = worldy - GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy;
                                } else {
                                    distancey = GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy - worldy;
                                }
                                previoustasky = distancey;
                                tasky = GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy;

                            }


                        }
                        if (i == GlobalGameThreadConfigs.Monsters[1].length - 1) {
                            break;
                        }
                    }
                      attacking = distancex < 96 && distancey < 96 && !hasattacked;
                    Angry();
            }
        }
    }

    @Override
    public void update() {
        super.update();
        //if (gp.player.health < gp.player.maxhealth) {
            //Hostile = true;
        //}
        if (attacking) {
            Attack();
        } else if
        (hasattacked) {
            getImageInstance();
            hasattacked = false;
        }
    }

    public void Angry() {
        searchPath(Math.round(taskx/gp.tilesize), Math.round(tasky/gp.tilesize));
        int i = new Random().nextInt(100) + 1;
        if (i > 70 && projectile.alive == false && primepowercool == 30) {
            projectile.Set((int) worldx, (int) worldy, direction, true, this);
            GlobalGameThreadConfigs.projectilelist.add(projectile);
            primepowercool = 0;
        }
            previoustasky = 10000;
            previoustaskx = 10000;
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

    public void attackEntity(int attackindex, int dmg) {
        if (attackindex != 999) {
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible == false) {
                gp.playsound(6);
                int damage = dmg - GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].defence;
                if (damage < 0) {
                    damage = 0;
                }
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health -= damage;
                ParticleAttackManager(this, GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex]);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].makemeHostile(this);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].HostileTime = 0;
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible = true;
            }
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying == false) {
                if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health < 0) {
                    GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying = true;
                    previoustasky = 10000;
                    previoustaskx = 10000;
                    XP += GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].XP;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        try {
            BufferedImage image = null;
            double screenX = (worldx - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldy - MainGame.player.worldy + MainGame.player.screenY;
            double tempscreenx = screenX;
            double tempscreeny = screenY;
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                switch (direction) {
                    case "up":
                        if (!attacking) {
                            if (spritenumber == 1) {
                                image = up1;
                            } else if (spritenumber == 2) {
                                image = up2;
                            }
                        } else {
                            tempscreeny = screenY - gp.tilesize;
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
                            tempscreenx = screenX - gp.tilesize;
                            if (spritenumber == 1) {
                                image = attackleft1;
                            } else if (spritenumber == 2) {
                                image = attackleft2;
                            }
                        }
                        break;
                }
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
                    double onescale = gp.tilesize / maxhealth;
                    double HPValue = onescale * health;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect((int) (screenX - 1), (int) screenY - 16, gp.tilesize + 2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect((int) screenX, (int) screenY - 15, (int) HPValue, 10);
                }
                g2.drawImage(image, (int) tempscreenx, (int) (tempscreeny), null);
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
        if (spritecounter >= 25) {
            attacking = false;
            hasattacked = true;
            spritecounter = 0;
        }
    }
}