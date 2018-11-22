package com.mycompany.a3.GameObjects;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * MovableObject is an abstract class that has many objects that extends it.
 * Ships, Missiles, and Asteroids.
 * They all have changeable locations.
 * 
 */
public abstract class MovableObject extends GameObject implements IMovable{
	private int speed,heading;
	private boolean oppositeX, oppositeY;
	
	public MovableObject(int width, int height) {
		super(width,height);
		//this.heading = getHeading();
	}

	public void move(int ticks) {
		double x,y,newX,newY;
		/*gets the new location based on current parameters*/
		newX = Math.cos(Math.toRadians(90 - this.getHeading())) * this.getSpeed();
		newY = Math.sin(Math.toRadians(90 - this.getHeading())) * this.getSpeed();
		
		/* bounce */
		if (oppositeX) { x = super.getX() - newX; } 
		else { x = super.getX() + newX; }
		
		if (oppositeY) { y = super.getY() - newY; } 
		else { y = super.getY() + newY; }
		 
		/* keeps within boundaries */
	    if ((x >= super.getWidth()) || (x < 0)) {
	    	oppositeX =  !oppositeX;
	    }

	    if ((y >= super.getHeight()) || (y < 0)) {
	    	oppositeY =  !oppositeY;
	    }
		super.setX(x);
		super.setY(y);
	}

	public int getHeading() {
		return this.heading;
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public void setRandomHeading() {
		this.heading = r.nextInt(359)+1;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setRandomSpeed() {
		this.speed = r.nextInt(15)+1;
	}
	
	public void increaseSpeed() {
		if(this.speed < 15 && this.speed >= 0) {
			this.speed++;
		} else {}
	}
	
	public void decreaseSpeed() {
		if(this.speed < 15 && this.speed >= 0) {
			this.speed--;
		} else {}
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Speed: "+this.getSpeed()+"\n"; /* getSpeed() according to the object it's printing */
		returnStr += "Heading: "+this.getHeading()+"\n";
		return returnStr;
	}
}
