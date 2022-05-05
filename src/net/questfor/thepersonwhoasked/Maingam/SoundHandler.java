package net.questfor.thepersonwhoasked.Maingam;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundHandler {
        //asign and handle sounds
        //IMPORTANT: THIS DOES NOT MANAGE SOUNDS,
        // THIS HANDLES THEM AND ASSIGNS EACH TYPE OF SOUND IN ITS SOUND FILE
        Clip clip;
        URL soundURL[] = new URL[30];
        FloatControl fc;
        int volumescale;
        float volume;
        public SoundHandler(){
                soundURL[0] = getClass().getResource("/Sound/Adventure.wav");
                soundURL[1] = getClass().getResource("/Sound/coin.wav");
                soundURL[2] = getClass().getResource("/Sound/powerup.wav");
                soundURL[3] = getClass().getResource("/Sound/unlock.wav");
                soundURL[4] = getClass().getResource("/Sound/fanfare.wav");
                soundURL[5] = getClass().getResource("/Sound/receivedamage.wav");
                soundURL[6] = getClass().getResource("/Sound/hitmonster.wav");
                soundURL[7] = getClass().getResource("/Sound/swingweapon.wav");
                soundURL[8] = getClass().getResource("/Sound/levelup.wav");
                soundURL[9] = getClass().getResource("/Sound/cursor.wav");
                soundURL[10] = getClass().getResource("/Sound/burning.wav");
                soundURL[11] = getClass().getResource("/Sound/cuttree.wav");
                soundURL[12] = getClass().getResource("/Sound/gameover.wav");
        }
        //THESE FUNCTIONS ARE USED BY THE MAINGAME CLASS, IT DOES NOT USE THEM BY ITS SELF//
        public void setFile(int i){
                try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                ControlVolume();
                }catch (Exception e){
                        crash.main(e);
                }
        }
        public void play(){
                try{
                clip.start();
                }catch (Exception e){
                        crash.main(e);
                }
        }
        public void loop(){
                try{
                 clip.loop(Clip.LOOP_CONTINUOUSLY);
                }catch (Exception e){
                        crash.main(e);
                }
        }
        public void stop(){
                try {
                     clip.stop();
                }catch (Exception e){
                        crash.main(e);
                }
        }
        public void ControlVolume(){
                switch (volumescale){
                        case 0 -> volume = -80f;
                        case 1 -> volume = -20f;
                        case 2 -> volume = -12f;
                        case 3 -> volume = -5f;
                        case 4 -> volume = 1f;
                        case 5 -> volume = 6f;
                }
                fc.setValue(volume);
        }
}