package com.mycompany.a1.GameObjects;

import com.codename1.ui.geom.Point2D;

public abstract class MovableObject extends GameObject implements IMovable{
	private int speed; //'HERE' IS THE SPEED
	private int heading; //'HERE' IS THE HEADING
	
	public MovableObject() {
		this.heading = getHeading();
	}

	public void move() {
		/*gets the new location based on current parameters*/
		double newX = Math.cos(Math.toRadians(90 - this.getHeading())) * this.getSpeed();
		double newY = Math.sin(Math.toRadians(90 - this.getHeading())) * this.getSpeed();
		
		/*gets original point*/
		Point2D originalP = super.getLocation();
		double originalX = originalP.getX();
		double originalY = originalP.getY();
		
		this.setLocation(new Point2D(originalX + newX, originalY + newY));
		
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	public void setHeading(int degChange /*Degree Change "heading"*/) {
		if(degChange > 360) {
			this.heading = degChange - 360;
		} else if(degChange < 0){
			this.heading = 360 + degChange;
		} else {
			this.heading = degChange;
		}
	}
	
	public void setRandomSpeed() {
		this.speed = r.nextInt(10)+1; //"this" means HERE in this file
	}
	
	public void setRandomHeading() {
		this.heading = r.nextInt(359)+1;
		//something with angles
	}
	
	public void increaseSpeed() {
		if(this.speed < 10 && this.speed >= 0) {
			this.speed++;
		} else {
			System.out.println("Error: Object speed could not be increased!");
		}
	}
	
	public void decreaseSpeed() {
		if(this.speed < 10 && this.speed >= 0) {
			this.speed--;
		} else {
			System.out.println("Error: Object speed could not be increased!");
		}
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Speed: "+this.getSpeed()+"\n"; /* getSpeed() according to the object it's printing */
		returnStr += "Heading: "+this.getHeading()+"\n";
		return returnStr;
	}
}
