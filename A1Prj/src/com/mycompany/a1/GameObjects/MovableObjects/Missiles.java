package com.mycompany.a1.GameObjects.MovableObjects;

import java.util.Random;
import com.mycompany.a1.GameObjects.MovableObject;

public class Missiles extends MovableObject{
	private int speed;
	private int fuelLevel;
	
	//Missiles Constructor
	public Missiles(){
		
	}

//	public void setColor() {}
	
	public int getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
//	public void decrememntFuelLevel() {}

//	public void move() {}
	
//	public int getSpeed() {}
//	public void setSpeed() {
//		//super.setSpeed(); /////////////////////////////
//		
//	}
//	public void setDefaultSpeed() {}
//	public int getHeading() {}
//	public void setHeading(int heading) {}
	
	public String toString(){
		String returnStr = "";
		returnStr += "___________________________";
		returnStr += "Missile Speed: "+speed;
		returnStr += "___________________________";
		return returnStr;
	}
}
