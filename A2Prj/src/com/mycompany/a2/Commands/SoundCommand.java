package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class SoundCommand extends Command {
	private GameWorld gw;
	
	public SoundCommand(GameWorld gw){
		super("Sound");
		this.gw = gw;
	}
	/**
	 * Calls the sound method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.sound();
		}
	}
}
