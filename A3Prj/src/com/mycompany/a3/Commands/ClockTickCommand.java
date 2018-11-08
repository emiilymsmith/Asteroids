package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ClockTickCommand extends Command{
	private GameWorld gw;

	public ClockTickCommand(GameWorld gw){
		super("Tick Tock");
		this.gw = gw;
	}
	/**
	 * Calls the turnLeft method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.ticked();
			System.out.println("Tick Tock");
		}
	}
}
