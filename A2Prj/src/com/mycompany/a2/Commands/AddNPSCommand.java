package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddNPSCommand extends Command{
	private GameWorld gw;

	public AddNPSCommand(GameWorld gw) {
		super("Add NonPlayerShip");
		this.gw = gw;
	}
	
	/**
	 * Calls the turnLeft method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.addNPS();
		System.out.println("Add NonPlayerShip Command");
		
	}
}
