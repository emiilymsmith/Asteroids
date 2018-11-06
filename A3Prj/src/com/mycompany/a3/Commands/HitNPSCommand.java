package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class HitNPSCommand extends Command{
	private GameWorld gw;

	public HitNPSCommand(GameWorld gw) {
		super("PS Hit NPS");
		this.gw = gw;
	}
	
	/**
	 * Calls the hit in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.hit();
		}
	}
}
