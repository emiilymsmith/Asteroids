package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RightTurnCommand extends Command{
	private GameWorld gw;

	public RightTurnCommand(GameWorld gw) {
		super("TurnRight");
		this.gw = gw;
	}
	
	/**
	 * Calls the turnRight method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.turnRight();
			//System.out.println("Turning Right");
		}
	}
}
