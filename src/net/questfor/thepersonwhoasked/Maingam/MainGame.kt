package net.questfor.thepersonwhoasked.Maingam

import net.questfor.thepersonwhoasked.Main
import net.questfor.thepersonwhoasked.MultiRenderer
import net.questfor.thepersonwhoasked.entities.LivingEntity
import net.questfor.thepersonwhoasked.entities.Player
import net.questfor.thepersonwhoasked.tile.tilemanager
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.io.*
import javax.swing.JOptionPane
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
                repaint()
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
            player.update()
            for (i in GlobalGameThreadConfigs.NPCS.indices) {
                if (GlobalGameThreadConfigs.NPCS[i] != null) {
                    GlobalGameThreadConfigs.NPCS[i].update()
                }
            }
            for(i in GlobalGameThreadConfigs.Monsters.indices){
                if(GlobalGameThreadConfigs.Monsters[i] != null){
                    if(!GlobalGameThreadConfigs.Monsters[i].dying) {
                        GlobalGameThreadConfigs.Monsters[i].update()
                    }
                    if(!GlobalGameThreadConfigs.Monsters[i].alive){
                        GlobalGameThreadConfigs.Monsters[i] = null
                    }}
            }
        }
        //PAUSE STATE RENDERING//
        if (GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.pauseState) {
            stopmusic()
        }
    }

    public override fun paintComponent(g: Graphics) {
        //draws the screen of the game
        super.paintComponent(g)
        val g2 = g as Graphics2D
        var drawStart: Long = 0

        /*display FPS*/
        if (keyM.checkFPS) {
            drawStart = System.nanoTime()
        }
            tilemanager.draw(g2)


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
        /*SORT ENTITYS IN POSITIONS*/
        GeneralHandler.main(g2)

        /*HANDLES FPS AND DRAW TIME FUNC*/
            if (keyM.checkFPS) {
                var drawEND: Long = System.nanoTime()
                var passed: Long = drawEND - drawStart
                g2.color = Color.white
                g2.drawString("draw Time: $passed", 10, 400)
                g2.drawString("FPS: $realFPS", 10, 300)
                g2.drawString("X: ${player.worldx}", 10, 200)
                g2.drawString("Y: ${player.worldy}", 10, 220)
                g2.drawString("Z: ${player.worldz}", 10, 180)
                g2.drawString("WORLD ROW: ${(player.worldy + player.hitbox.y)/tilesize}", 10, 210)
                g2.drawString("WORLD COL: ${(player.worldx + player.hitbox.x)/tilesize}", 10, 190)
        }
        //UIS//
            UI.draw(g)
            g2.dispose()
        }


     companion object {
         //SETS ADVANCED AND CONFIG VALUES//
         //TILES
        const val originalTileSize = 16
        const val scale = 3
        @JvmField
        var tilesize = originalTileSize * scale
        var maxscreencol = 16
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
        var music = SoundHandler()
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
        var obj = arrayOfNulls<LivingEntity>(20)
         @JvmField
         //WORLD RENDERER//
        var tilemanager = tilemanager()
        const val maxworldcol = 50
        const val maxworldrow = 50



        @JvmStatic
        fun setupOBJ() {
            //ENTITES RENDERERS//
            MultiRender.Render(MainGame())
            MultiRender.setObjectRenderer()
            MultiRender.setNPCrenderers()
            MultiRender.setMonsterRenderers()
            playmusic(0)

            GlobalGameThreadConfigs.GameState = GlobalGameThreadConfigs.PlayState
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

    fun load() {
        //LOAD MANAGER, LOADS SAVE FILES
        try {
            val loadf = FileInputStream("data.dat")
            val stream = BufferedInputStream(loadf)
            val ois = ObjectInputStream(stream)
            Main.globalDataStorage = ois.readObject() as GlobalDataStorage
            val amogus = Main.globalDataStorage.health
            JOptionPane.showMessageDialog(null, amogus)
        } catch (exx: Exception) {
            crash.main(exx)
        }
    }

    fun playsound(i: Int) {
        //PLAYS SOUND EFFECTS
        sound.setFile(i)
        sound.play()
    }
    fun save(){
        //SAVE MANAGER, SAVES WORLD DATA AND PLAYER DATA
        var data: FileOutputStream?
        try {
            data = FileOutputStream("data.dat")
            val datastream = BufferedOutputStream(data)
            val o = ObjectOutputStream(datastream)
            Main.globalDataStorage.health = 30
            o.writeObject(Main.globalDataStorage)
            o.close()
        } catch (er: Exception) {
            crash.main(er)
        }
    }

    fun stopmusic() {
        //STOPS MUSIC
        amogus = music.clip.microsecondPosition
        music.stop()
    }


}
