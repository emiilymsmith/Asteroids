package com.mycompany.a1.GameObjects;

import com.codename1.io.Util;

public abstract class MovableObject extends GameObject implements IMovable{
	private int speed; //HERE IS THE SPEED
	private int heading; //HERE IS THE HEADING
	
	public MovableObject() {

	}
	
//	abstract void move() {}
	public int getSpeed() {
		return this.speed;
	}
	public void setSpeed() {
		this.speed = r.nextInt(10)+1; //this means here in this file
	}
//	abstract public void setDefaultSpeed(int defaultSpeed); //setters don't need a return type back
//	abstract public int getHeading();
//	abstract public void setHeading(int heading);
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	public void setRandomHeading() {
		this.heading = r.nextInt(359)+1;
		//something with angles
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Speed: "+this.getSpeed()+"\n";
		returnStr += "Heading: "+this.getHeading()+"\n";
		return returnStr;
	}
}
