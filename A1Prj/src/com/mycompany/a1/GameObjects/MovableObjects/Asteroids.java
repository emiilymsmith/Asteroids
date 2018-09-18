package com.mycompany.a1.GameObjects.MovableObjects;

import com.mycompany.a1.GameObjects.MovableObject;

public class Asteroids extends MovableObject{
	private int size;

	public Asteroids(){
		size = 1;
	}
	
	//getters and setters
	public int getSize() {
		 return super.getSize();
	}

	public void setSize(int size) {
		this.size = size;
	}
	
//	//public void move() {}
//	public int getSpeed() {}
//
//	//set speed 2 kinds setDefaultSpeed random number 1-10 and normal setter
//	public void setSpeed(int speed) {}
//	public void setDefaultSpeed(int defaultSpeed) {}
//	public int getHeading() {}
//	public void setHeading(int heading) {}
	
	
	
	public String toString() {
		String returnStr = "";
		returnStr += "___________________________";
		returnStr += "Size: "+size;
		returnStr += "___________________________";
		return returnStr;
	}
	
}
