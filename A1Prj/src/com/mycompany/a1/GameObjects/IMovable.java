package com.mycompany.a1.GameObjects;

public interface IMovable {
	public void move();
	
	public int getSpeed();
	public void setSpeed(int speed);
	
	public int getHeading();
	public void setHeading(int heading);
	
}
