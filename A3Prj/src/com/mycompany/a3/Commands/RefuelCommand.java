package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RefuelCommand extends Command{
	private GameWorld gw;

	public RefuelCommand(GameWorld gw) {
		super("Refuel Missile");
		this.gw = gw;
	}
	
	/**
	 * Calls the turnLeft method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getKeyEvent() != -1) {
			gw.refuelMissiles();
		}
	}
}
