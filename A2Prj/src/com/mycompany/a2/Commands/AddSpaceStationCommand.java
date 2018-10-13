package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddSpaceStationCommand extends Command{
	private GameWorld gw;

	public AddSpaceStationCommand(GameWorld gw) {
		super("Add Blinking Space Station");
		this.gw = gw;
	}
	/**
	 * Calls the turnLeft method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.addSpaceStation();
		System.out.println("Add Space Station Command");
	}
}