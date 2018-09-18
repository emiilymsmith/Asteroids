package com.mycompany.a1.GameObjects.MovableObjects;

import com.mycompany.a1.GameObjects.MovableObject;

public class Missiles extends MovableObject{
	private int speed;
	private int fuelLevel;
	
	//Missiles Constructor
	public Missiles(){
		
	}

	public int getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	
	//public void move() {}
//	public int getSpeed() {}
//	public void setSpeed(int speed) {}
//	public void setDefaultSpeed(int defaultSpeed) {}
//	public int getHeading() {}
//	public void setHeading(int heading) {}
	
	//public String toString(){}
}
