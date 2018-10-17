package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CrashCommand extends Command {
	private GameWorld gw;

	public CrashCommand(GameWorld gw) {
		super("Playership Crashed into Asteroid");
		this.gw = gw;
	}
	/**
	 * Calls the crash method in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.eliminatedNPS();
		}
	}
}
