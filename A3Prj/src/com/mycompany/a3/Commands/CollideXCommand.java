package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideXCommand extends Command{
	private GameWorld gw;

	public CollideXCommand(GameWorld gw) {
		super("Asteroid Collision");
		this.gw = gw;
	}
	
	/**
	 * Calls the exterminate in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.exterminate();
		}
	}
}
