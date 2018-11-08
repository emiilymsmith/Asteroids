package com.mycompany.a3;
/**
 * This class's purpose is to make Game World Proxy a mirror of Game World
 * 
 * */

//TODO finish method names bruh
public interface IGameWorld {
	//specifications here for all GameWorld methods
	
	/* Returns the player score of the GameWorld */
	public int getPlayerScore();
	public boolean getSound();
	public int getLives();
	public int getMissileCount();
	public int getTime();
	public void setWidth(int w);
	public void setHeight(int h);
	public void map();
	IIterator getIterator();

}
