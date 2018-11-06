package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddPSCommand extends Command{
	private GameWorld gw;

	public AddPSCommand(GameWorld gw){
		super("Add Player Ship");
		this.gw = gw;
	}
	/**
	 * Calls the addPS method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.addPS();
			System.out.println("Add Player Ship Command");
		}
	}
}
