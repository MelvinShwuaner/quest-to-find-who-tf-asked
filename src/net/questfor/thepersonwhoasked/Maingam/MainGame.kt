package net.questfor.thepersonwhoasked.Maingam
import net.questfor.thepersonwhoasked.GlobalProperties
import net.questfor.thepersonwhoasked.Main
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs.*
import net.questfor.thepersonwhoasked.World.Light
import net.questfor.thepersonwhoasked.entities.Player
import net.questfor.thepersonwhoasked.tile.Tilemanager
import java.awt.Color
import java.awt.Dimension
import javax.swing.JPanel

class MainGame : JPanel(), Runnable {
    /*
    is the secondary most important class in hierarchy, this has all the functions,
    including drawing you and everything else in the game, it has very important values like tilesize to store data
    and also manages objects.
     */



    @JvmField
    //sets default values for the game panel
    var FPS = 60.0
    var drawcound: Long = 0
    var realFPS: Int = 0
    init {
        //assigns default values for the game panel
        addKeyListener(keyM)
        focusTraversalKeysEnabled = false;
        addMouseListener(mouseH)
        this.isFocusable = true
        this.preferredSize = Dimension(screenwidth, screenheight)
        this.isDoubleBuffered = true
    }

    fun startgamethread() {
        //starts the game thread, this is the ticks per secound of the game
        gameThread = Thread(this)
        gameThread!!.start()
    }

    override fun run() {
        //happens on every tick interval the thread passes by,
        // filters out extra ticks to make sure the CPU doesnt
        // process ticks at a million times per secound
        val drawinterval = 1000000000 / FPS
        var nextframe = 0.0
        var LastFrame = System.nanoTime()
        var CurrentFrame: Long
        var ticks: Long = 0
        while (gameThread != null) {
            CurrentFrame = System.nanoTime()
            nextframe += (CurrentFrame - LastFrame) / drawinterval
            ticks += CurrentFrame - LastFrame
            LastFrame = CurrentFrame
            displayScreen()
            drawtoscreen()
            if (nextframe >= 1) {
                updateticks()
                nextframe--
                drawcound++
            }
            if (ticks >= 1000000000) {
                realFPS = drawcound.toInt()
                drawcound = 0
                ticks = 0
            }
        }
    }

    fun updateticks() {
        //occures AFTER the filtered ticks are removed, happens on every tick that remains
        if (GameState == PlayState) {
            //PLAY STATE RENDERING//
            /**PLAYER**/
            player.update()
            /**NPCS**/
            for (i in NPCS[1].indices) {
                if (NPCS[currentmap][i] != null) {
                    NPCS[currentmap][i].update()
                    if (NPCS[currentmap][i] != null) {
                    if(!NPCS[currentmap][i].alive) {
                        NPCS[currentmap][i].HandleItems()
                        NPCS[currentmap][i] = null
                    }}
                    }

            }
            /**MOBS**/
            for(i in Monsters[1].indices){
                if(Monsters[currentmap][i] != null){
                    if(!Monsters[currentmap][i].dying) {
                        Monsters[currentmap][i].update()
                    }
                    if(Monsters[currentmap][i] != null){
                    if(!Monsters[currentmap][i].alive){
                        Monsters[currentmap][i].HandleItems()
                        Monsters[currentmap][i] = null
                    }}}
            }
            /**TILE ENTITIES**/

        }
        //PAUSE STATE RENDERING//
        if (GameState == pauseState) {
            stopmusic()
        }
        /**OBJECTS**/
        for(i in obj[1].indices){
            if(obj[currentmap][i] != null){
                obj[currentmap][i]?.updateimage()
                obj[currentmap][i]?.update()
            }
        }
    }
    fun displayScreen() {
        if (g2 != null) {
            var drawStart: Long = 0

            /*display FPS*/
            if (KeyHandler.checkFPS) {
                drawStart = System.nanoTime()
            }
            tilemanager.drawground(g2)
            /*DISPLAYS ENTITYS AND OBJECTS*/
            entitylist.add(player)

            for (i in NPCS[1].indices) {
                if (NPCS[currentmap][i] != null) {
                    entitylist.add(NPCS[currentmap][i])
                }
            }
            for (i in obj[1].indices) {
                if (obj[currentmap][i] != null) {
                    entitylist.add(obj[currentmap][i])
                }
            }
            for (i in Monsters[1].indices) {
                if (Monsters[currentmap][i] != null) {
                    entitylist.add(Monsters[currentmap][i])
                }
            }
            for (i in projectilelist[1].indices) {
                if (projectilelist[currentmap][i] != null) {
                    entitylist.add(projectilelist[currentmap][i])
                }
            }
            for (i in particleList.indices) {
                if (particleList[i] != null) {
                    entitylist.add(particleList[i])
                }
            }
            /**TILE ENTITY RENDERING**/

            /*SORT ENTITYS IN POSITIONS*/
            GeneralHandler.main(g2)
            if(dark){
            Emanager.setup()
            Emanager.draw(g2)}

            /*HANDLES FPS AND DRAW TIME FUNC*/
            if (KeyHandler.checkFPS && GameState == PlayState) {
                val drawEND: Long = System.nanoTime()
                val passed: Long = drawEND - drawStart
                g2.color = Color.white
                g2.drawString("draw Time: $passed", 10, 400)
                g2.drawString("FPS: $realFPS", 10, 300)
                g2.drawString("X: ${player.worldx}", 10, 200)
                g2.drawString("Y: ${player.worldy}", 10, 220)
                g2.drawString("World: $currentmap", 10, 240)
                g2.drawString("Z: ${player.worldz}", 10, 180)
                g2.drawString(
                    "WORLD ROW: ${(player.worldy + player.hitbox.y) / tilesize}",
                    10,
                    210
                )
                g2.drawString(
                    "WORLD COL: ${(player.worldx + player.hitbox.x) / tilesize}",
                    10,
                    190
                )

            }
            //UIS//
            UI.draw(g2)

        }
    }
    fun drawtoscreen() {
        val g = graphics
        g.drawImage(tempscreen, 0, 0, screenwidth2, screenheight2, null)
        g.dispose()
    }


    companion object {
         //SETS ADVANCED AND CONFIG VALUES//
         //TILES
        @JvmField
        var maxscreencol = 20
        var maxscreenrow = 12
         @JvmField
         var maxmap = 2
         @JvmField
         var currentmap = 0

         @JvmField
        var screenwidth = tilesize * maxscreencol
         @JvmField
        val screenheight = tilesize * maxscreenrow
         @JvmField
         //KEY BINDINGS
        var keyM = KeyHandler(MainGame())
         //MOUSE BINDINGS
         var mouseH = ClickHandler()
         @JvmField
         //HITBOXES, RENDERING, AND SOUND
        var hregister = hitboxregister(MainGame())
         @JvmField
         var MultiRender = GlobalProperties(MainGame())
         @JvmField
         var music = SoundHandler()
         @JvmField
        var sound = SoundHandler()
        var amogus = 0L;

         @JvmField
         //EVENTS//
        var ehandler = EventHandler(MainGame())
        @JvmField

         //WORLD RENDERER//
        var tilemanager = Tilemanager()
        const val maxworldcol = 200
        const val maxworldrow = 200
        const val maxworldlayer = 8
         @JvmStatic
         var screenwidth2 = screenwidth
         @JvmStatic
         var screenheight2 = screenheight
         @JvmField
         var FullscreenON = false
         @JvmStatic
        fun setupOBJ() {
                lights = Array(maxmap) { arrayOfNulls<Light>(100) }
             tilemanager.set()
             player = Player(MainGame())
            MultiRender.setObjectRenderer()
            MultiRender.setNPCrenderers()
            MultiRender.setMonsterRenderers()
             MultiRender.SetRecipes()

             System.out.println("Set all NBT data successfully")

        }
         @JvmStatic
         fun togglefullscreen(){
             if(FullscreenON){
                 setFullScreen()
             }else{
                 screenheight2 = screenheight
                 screenwidth2 = screenwidth
                 gd.fullScreenWindow = null

             }
             System.out.println("toggled full screen!")
         }
         @JvmStatic
          fun setFullScreen() {
             gd.fullScreenWindow = Main.window
             screenwidth2 = Main.window.width;
             screenheight2 = Main.window.height
             System.out.println("successfully started screen")
         }

         @JvmStatic
        fun playmusic(i: Int) {
            //PLAYS THE MUSIC
            music.setFile(i)
            music.play()
            music.clip.microsecondPosition = amogus;
            music.loop()
        }
    }



    fun playsound(i: Int) {
        //PLAYS SOUND EFFECTS
        sound.setFile(i)
        sound.play()
    }

    fun stopmusic() {
        //STOPS MUSIC
        amogus = music.clip.microsecondPosition
        music.stop()
    }
}