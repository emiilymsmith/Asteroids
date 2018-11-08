package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class JumpCommand extends Command{
	private GameWorld gw;

	public JumpCommand(GameWorld gw) {
		super("Hyperspace");
		this.gw = gw;
	}
	
	/**
	 * Calls the Increase Speed method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.jump();
			System.out.println("Hyperspace");
		}
	}
}
