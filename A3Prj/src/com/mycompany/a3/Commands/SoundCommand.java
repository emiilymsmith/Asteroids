package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Sound;

public class SoundCommand extends Command {
	private GameWorld gw;
	private Sound sound;
	private boolean pauseB;
	
	public SoundCommand(GameWorld gw){
		super("Sound");
		this.gw = gw;
		sound = new Sound("background.mp3");
	}
	/**
	 * Calls the sound method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.sound();
			pauseB = !pauseB;
			if (pauseB)
				sound.play();
			else
				sound.pause();
		}
	}
}
