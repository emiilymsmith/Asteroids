package com.mycompany.a3.GameObjects;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * IMovable is an interface that MovableObject provides to 
 * all movable objects to tell them to move.
 * 
 * */
public interface IMovable {
	public void move(int ticks);
	
	public int getSpeed();
	public void setSpeed(int speed);
	
	public int getHeading();
	public void setHeading(int heading);
	
}
