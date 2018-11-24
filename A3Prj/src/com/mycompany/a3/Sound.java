package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound implements Runnable{
	private Media m;
	
	public Sound(String file){
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+file);
			m = MediaManager.createMedia(is, "audio/mp3", this);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void play() {
		m.play();
	}
	
	public void pause() {
		m.pause();
	}
	
	/* entered when media has finished playing */
	@Override
	public void run() {
		m.setTime(0);
		m.pause();
		
	}

}
// TODO: use encapsulation like Sound asteroidCollisionSound = new Sound("meow.mp3"); like an asteroid collision should only call one of these
//asteroidCollisionSound.play()
