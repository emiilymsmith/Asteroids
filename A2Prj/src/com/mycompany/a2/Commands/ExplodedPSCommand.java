package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class ExplodedPSCommand extends Command{
	private GameWorld gw;

	public ExplodedPSCommand(GameWorld gw) {
		super("NPS Explodes PS");
		this.gw = gw;
	}
	
	/**
	 * Calls the explodePS in GameWorld
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getKeyEvent() != -1) {
			gw.explodePS();
		}
	}
}
