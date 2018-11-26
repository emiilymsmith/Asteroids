package com.mycompany.a3;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	private Media m;
	
	public BGSound(String fileName){
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			m = MediaManager.createMedia(is, "audio/mp3", this);
			System.out.println("sound is : " + fileName);
			//System.exit(1);
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
		m.play();
	}
}