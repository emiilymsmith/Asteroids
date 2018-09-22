package com.mycompany.a1.GameObjects;

public abstract class MovableObject extends GameObject implements IMovable{
	private int speed; //HERE IS THE SPEED
	private int heading; //HERE IS THE HEADING
	
	public MovableObject() {

	}
	
//	abstract void move() {}
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public void setRandomSpeed() {
		this.speed = r.nextInt(10)+1; //"this" means HERE in this file
	}
	
	public void setRandomHeading() {
		this.heading = r.nextInt(359)+1;
		//something with angles
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Speed: "+this.getSpeed()+"\n"; /* getSpeed() according to the object it's printing */
		returnStr += "Heading: "+this.getHeading()+"\n";
		return returnStr;
	}
}
