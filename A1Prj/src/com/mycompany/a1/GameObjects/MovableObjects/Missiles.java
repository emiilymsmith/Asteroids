package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.MovableObject;

// f fires Missile
public class Missiles extends MovableObject{
	private int fuelLevel;
	
	//Missiles Constructor
	public Missiles(){
		super.setColor(ColorUtil.GREEN); //from GameObject
		//super.setRandomSpeed(); //from MovableObject //speed is greater than ships
		//super.setRandomHeading(); //from MovableObject //heading comes from ship
		this.setFuelLevel(10); //= getFuelLevel(); //from here
	}
	
	public int getFuelLevel() {
		//put in timer, ticks down as time goes on, 10 ticks to failure
		return fuelLevel = 10;
	}

	public void setFuelLevel(int fuel) {
		//this.fuelLevel = fuelLevel;
		this.fuelLevel = fuel;
	}
	
	public String toString(){
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Fuel Level: "+ this.fuelLevel+"\n";
		return returnStr;
	}
}
