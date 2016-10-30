package game;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip clip;
	
	public static Sound hitPaddle = new Sound("resources/hit-paddle.m4a", -10.5f);
	
	boolean played;
	float volume;
	
	public Sound(String fileName, float volume){
		played = false;
		this.volume = volume;
		try{
			URL url = this.getClass().getClassLoader().getResource(fileName);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		try{
			if(clip!= null){
				new Thread(){
					public void run(){
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void playOnce(){
		if(played==false){	
			try{
				if(clip!= null){
					new Thread(){
						public void run(){
							synchronized (clip) {
								clip.stop();
								clip.setFramePosition(0);
								clip.start();
							}
						}
					}.start();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			played = true;
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	
	public void loop(){
		try {
			if(clip != null){
				new Thread(){
					public void run(){
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isActive(){
		return clip.isActive();
	}
}
