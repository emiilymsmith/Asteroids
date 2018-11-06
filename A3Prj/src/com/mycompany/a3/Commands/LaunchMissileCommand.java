package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class LaunchMissileCommand extends Command{
	private GameWorld gw;

	public LaunchMissileCommand(GameWorld gw) {
		super("LaunchNPSMissile");
		this.gw = gw;
	}
	
	/**
	 * Calls the Increase Speed method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.launchNPSMissile();
		}
	}
}
