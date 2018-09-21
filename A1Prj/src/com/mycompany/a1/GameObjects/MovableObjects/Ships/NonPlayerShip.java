package com.mycompany.a1.GameObjects.MovableObjects.Ships;

import java.util.Random;

import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObjects.Ship;

public class NonPlayerShip extends Ship{
	private int size;
	private int speed;
	private Point2D heading;
	
	public NonPlayerShip() {
		
	}
	
	public void setSpeed() {
//		this.speed = rand.nextInt(10)+1;
	}
	
	public void setSize() {
//		this.size = rand.nextInt(3)+1; //includes 0, discludes 2
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += "Set NPS Size to: " +size+"0,";
		returnStr += " Set NPS Speed: "+speed;
		return returnStr;
	}
//	public void steerHeading(); //this doesn't move, do we initialize this once and thats it?
	
}
