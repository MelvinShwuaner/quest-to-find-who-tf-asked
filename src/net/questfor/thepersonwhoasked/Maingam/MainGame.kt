package net.questfor.thepersonwhoasked.Maingam

import net.questfor.thepersonwhoasked.Main
import net.questfor.thepersonwhoasked.MultiRenderer
import net.questfor.thepersonwhoasked.entities.LivingEntity
import net.questfor.thepersonwhoasked.entities.Player
import net.questfor.thepersonwhoasked.tile.tilemanager
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
    var FPS = 59.0
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
        GlobalGameThreadConfigs.gameThread = Thread(this)
        GlobalGameThreadConfigs.gameThread!!.start()
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
        while (GlobalGameThreadConfigs.gameThread != null) {
            CurrentFrame = System.nanoTime()
            nextframe += (CurrentFrame - LastFrame) / drawinterval
            ticks += CurrentFrame - LastFrame
            LastFrame = CurrentFrame
            if (nextframe >= 1) {
                converttofullscreen()
                drawtoscreen()
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
        if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState) {
            //PLAY STATE RENDERING//
            /**PLAYER**/
            player.update()
            /**NPCS**/
            for (i in GlobalGameThreadConfigs.NPCS.indices) {
                if (GlobalGameThreadConfigs.NPCS[i] != null) {
                    GlobalGameThreadConfigs.NPCS[i].update()
                }
            }
            /**MOBS**/
            for(i in GlobalGameThreadConfigs.Monsters.indices){
                if(GlobalGameThreadConfigs.Monsters[i] != null){
                    if(!GlobalGameThreadConfigs.Monsters[i].dying) {
                        GlobalGameThreadConfigs.Monsters[i].update()
                    }
                    if(!GlobalGameThreadConfigs.Monsters[i].alive){
                        GlobalGameThreadConfigs.Monsters[i].HandleItems()
                        GlobalGameThreadConfigs.Monsters[i] = null
                    }}
            }
            /**TILE ENTITIES**/
            for(i in GlobalGameThreadConfigs.Tentity.indices){
                if(GlobalGameThreadConfigs.Tentity[i] != null){
                    GlobalGameThreadConfigs.Tentity[i].update()
                }
            }
        }
        //PAUSE STATE RENDERING//
        if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.pauseState) {
            stopmusic()
        }
    }
    fun converttofullscreen(){
        var drawStart: Long = 0

        /*display FPS*/
        if (KeyHandler.checkFPS) {
            drawStart = System.nanoTime()
        }
        tilemanager.draw(GlobalGameThreadConfigs.g2)


        /*DISPLAYS ENTITYS AND OBJECTS*/
        GlobalGameThreadConfigs.entitylist.add(player)
        for(i in GlobalGameThreadConfigs.NPCS.indices){
            if(GlobalGameThreadConfigs.NPCS[i] != null){
                GlobalGameThreadConfigs.entitylist.add(GlobalGameThreadConfigs.NPCS[i])
            }
        }
        for(i in obj.indices){
            if(obj[i] != null){
                GlobalGameThreadConfigs.entitylist.add(obj[i])
            }
        }
        for(i in GlobalGameThreadConfigs.Monsters.indices){
            if(GlobalGameThreadConfigs.Monsters[i] != null){
                GlobalGameThreadConfigs.entitylist.add(GlobalGameThreadConfigs.Monsters[i])
            }
        }
        for (i in GlobalGameThreadConfigs.projectilelist.indices) {
            if (GlobalGameThreadConfigs.projectilelist[i] != null) {
                GlobalGameThreadConfigs.entitylist.add(GlobalGameThreadConfigs.projectilelist[i])
            }
        }
        for (i in GlobalGameThreadConfigs.particleList.indices) {
            if (GlobalGameThreadConfigs.particleList[i] != null) {
                GlobalGameThreadConfigs.entitylist.add(GlobalGameThreadConfigs.particleList[i])
            }
        }
        /**TILE ENTITY RENDERING**/
        for(i in GlobalGameThreadConfigs.Tentity.indices){
            if(GlobalGameThreadConfigs.Tentity[i] != null){
                GlobalGameThreadConfigs.Tentity[i].draw(GlobalGameThreadConfigs.g2)
            }
        }
        /*SORT ENTITYS IN POSITIONS*/
        GeneralHandler.main(GlobalGameThreadConfigs.g2)

        /*HANDLES FPS AND DRAW TIME FUNC*/
        if (KeyHandler.checkFPS && GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState) {
            var drawEND: Long = System.nanoTime()
            var passed: Long = drawEND - drawStart
            GlobalGameThreadConfigs.g2.color = Color.white
            GlobalGameThreadConfigs.g2.drawString("draw Time: $passed", 10, 400)
            GlobalGameThreadConfigs.g2.drawString("FPS: $realFPS", 10, 300)
            GlobalGameThreadConfigs.g2.drawString("X: ${player.worldx}", 10, 200)
            GlobalGameThreadConfigs.g2.drawString("Y: ${player.worldy}", 10, 220)
            GlobalGameThreadConfigs.g2.drawString("Z: ${player.worldz}", 10, 180)
            GlobalGameThreadConfigs.g2.drawString("WORLD ROW: ${(player.worldy + player.hitbox.y)/tilesize}", 10, 210)
            GlobalGameThreadConfigs.g2.drawString("WORLD COL: ${(player.worldx + player.hitbox.x)/tilesize}", 10, 190)
        }
        //UIS//
        UI.draw(GlobalGameThreadConfigs.g2)
        //GlobalGameThreadConfigs.g2.color = Color(0, 0, 0, 220)
        //GlobalGameThreadConfigs.g2.fillRect(0, 0, screenwidth, screenheight)
        }
    fun drawtoscreen() {
        val g = graphics
        g.drawImage(GlobalGameThreadConfigs.tempscreen, 0, 0, screenwidth2, screenheight2, null)
        g.dispose()
    }



     companion object {
         //SETS ADVANCED AND CONFIG VALUES//
         //TILES
        const val originalTileSize = 16
        const val scale = 3
        @JvmField
        var tilesize = originalTileSize * scale
        var maxscreencol = 20
        var maxscreenrow = 12

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
         var MultiRender = MultiRenderer()
         @JvmField
         var music = SoundHandler()
         @JvmField
        var sound = SoundHandler()
        var amogus = 0L;

         @JvmField
         //EVENTS//
        var ehandler = EventHandler(MainGame())
        @JvmField
        //PLAYER//
        var player = Player(keyM, MainGame())
         @JvmField
         //OBJECT//
        var obj = arrayOfNulls<LivingEntity>(30)
         @JvmField
         //WORLD RENDERER//
        var tilemanager = tilemanager()
        const val maxworldcol = 50
        const val maxworldrow = 50
         @JvmStatic
         var screenwidth2 = screenwidth
         @JvmStatic
         var screenheight2 = screenheight
         @JvmField
         var FullscreenON = false
         @JvmStatic
        fun setupOBJ() {
            MultiRender.Render(MainGame())
            MultiRender.setObjectRenderer()
            MultiRender.setNPCrenderers()
            MultiRender.setMonsterRenderers()
            MultiRender.setTileEntityRenderers()
            MultiRender.setScreenRenderer()
            GlobalSaveManager.loadconfigs()
            playmusic(0)
            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState
        }
         @JvmStatic
         fun togglefullscreen(){
             if(FullscreenON){
                 setFullScreen()
             }else{
                 screenheight2 = screenheight
                 screenwidth2 = screenwidth
                 GlobalGameThreadConfigs.gd.fullScreenWindow = null

             }

         }
         @JvmStatic
          fun setFullScreen() {
             GlobalGameThreadConfigs.gd.fullScreenWindow = Main.window
             screenwidth2 = Main.window.width;
             screenheight2 = Main.window.height
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
