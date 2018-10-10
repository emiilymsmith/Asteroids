package com.mycompany.a2.GameObjects;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * IMovable is an interface that MovableObject provides to 
 * all movable objects to tell them to move.
 * 
 * */
public interface IMovable {
	public void move();
	
	public int getSpeed();
	public void setSpeed(int speed);
	
	public int getHeading();
	public void setHeading(int heading);
	
}
