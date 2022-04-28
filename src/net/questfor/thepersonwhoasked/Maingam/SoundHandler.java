package net.questfor.thepersonwhoasked.Maingam;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundHandler {
        //asign and handle sounds
        //IMPORTANT: THIS DOES NOT MANAGE SOUNDS,
        // THIS HANDLES THEM AND ASSIGNS EACH TYPE OF SAND ITS SOUND FILE
        Clip clip;
        URL soundURL[] = new URL[30];
        public SoundHandler(){
                soundURL[0] = getClass().getResource("/Sound/Adventure.wav");
                soundURL[1] = getClass().getResource("/Sound/coin.wav");
                soundURL[2] = getClass().getResource("/Sound/powerup.wav");
                soundURL[3] = getClass().getResource("/Sound/unlock.wav");
                soundURL[4] = getClass().getResource("/Sound/fanfare.wav");
        }
        //THESE ARE USED BY THE MAINGAME CLASS, IT DOES NOT USE THEM BY ITS SELF//
        public void setFile(int i){
                try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
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
}