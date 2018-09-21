package com.mycompany.a1.GameObjects;

import com.codename1.io.Util;

public abstract class MovableObject extends GameObject implements IMovable{
	private int speed; //HERE IS THE SPEED
	private int heading;
	
	public MovableObject() {
		//speed = Util.getRandomNumberInRange(Util.MIN_SPEED, Util.MAX_SPEED);
		//heading = Utils.getRandomNumberInRange(Utils.MIN_HEADING, Utils.MAX_HEADING);
		//this.heading = heading;
		
	}
//	abstract void move() {}
//	abstract public int getSpeed();
	public void setSpeed() {
		this.speed = rand.nextInt(10)+1; //this means here in this file
	}
//	abstract public void setDefaultSpeed(int defaultSpeed); //setters don't need a return type back
//	abstract public int getHeading();
//	abstract public void setHeading(int heading);

}
