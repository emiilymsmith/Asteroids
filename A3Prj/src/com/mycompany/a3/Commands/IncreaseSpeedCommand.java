package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class IncreaseSpeedCommand extends Command{
	private GameWorld gw;

	public IncreaseSpeedCommand(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	
	/**
	 * Calls the Increase Speed method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.increaseSpeed();
			System.out.println("Increasing Speed");
		}
	}
}
