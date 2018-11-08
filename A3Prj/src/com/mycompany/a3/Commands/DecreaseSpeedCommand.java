package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class DecreaseSpeedCommand extends Command{
	private GameWorld gw;

	public DecreaseSpeedCommand(GameWorld gw) {
		super("Decelerate");
		this.gw = gw;
	}
	
	/**
	 * Calls the Increase Speed method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.decreaseSpeed();
			System.out.println("Decreasing Speed");
		}
	}
}
