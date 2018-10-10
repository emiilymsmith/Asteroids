package com.mycompany.a2;

import java.util.Observable;

/**
 * @author Emily Smith
 * @version 2.0
 * 
 * The GameWorldProxy's purpose is to accept and hold a GameWorld, 
 * provide implementations of all the public methods on a GameWorld,
 * forward allowed calls to the actual GameWorld, and reject calls to methods
 * which the outside should not be able to access in the GameWorld.
 * 
 */

public class GameWorldProxy extends Observable implements IGameWorld{
	private GameWorld gw;
	
	public GameWorldProxy(GameWorld gw) {
		this.gw = gw;
	}
	
	@Override
	public int getPlayerScore() {
		return gw.getPlayerScore();
	}
	
}
