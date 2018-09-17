package com.mycompany.a1.GameObjects;

public abstract class MovableObject extends GameObject implements IMovable{
	private int speed;
	private int heading;
	
	public MovableObject() {
		
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	//move();
	//public String toString(){}
}
