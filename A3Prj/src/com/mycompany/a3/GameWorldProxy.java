package com.mycompany.a3;

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
	
	@Override
	public boolean getSound() {
		return gw.getSound();
	}
	
	@Override
	public int getLives() {
		return gw.getLives();
	}
	
	@Override
	public int getMissileCount() {
		return gw.getMissileCount();
	}
	
	@Override
	public int getTime() {
		return gw.getTime();
	}
	
	@Override
	public void setWidth(int w) {
		gw.setWidth(w);
	}
	
	@Override
	public void setHeight(int h) {
		gw.setHeight(h);
	}
	
	@Override
	public void map() {
		gw.map();
	}
	@Override
	public IIterator getIterator(){
		return gw.getIterator();
	}
	
}
