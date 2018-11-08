package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AICommand extends Command {
	private GameWorld gw;

	public AICommand(GameWorld gw) {
		super("Asteroid impact NPS");
		this.gw = gw;
	}
	
	/**
	 * Calls the impact in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.impact();
		}
	}
}
