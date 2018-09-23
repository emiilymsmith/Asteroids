package com.mycompany.a1.GameObjects.MovableObjects;

import com.mycompany.a1.GameObjects.MovableObject;
import com.codename1.ui.geom.Point2D;

public class SteerableMissileLauncher extends MovableObject implements ISteerable{
	private int speed;
	private int heading; //not on UML???
	Point2D point = new Point2D(512,384); //not on UML???
	
	public SteerableMissileLauncher(){
		super.setLocation(point);
		super.setRandomHeading();
		super.setSpeed(0);
	}
	
	public void setHeading() {
		this.heading = super.getHeading(); //gets supers heading
		//TODO: change to be individual
	}
	
	public void changeAngle(int degChange) {
		this.setHeading(this.getHeading() + degChange);
	}
	
//	public String toString() {}
	@Override
	public void turnLeft() {
		
	}
	@Override
	public void turnRight() {
		
	}
}
