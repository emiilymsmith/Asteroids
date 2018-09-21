package com.mycompany.a1.GameObjects.MovableObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.MovableObject;

// f fires Missile
public class Missiles extends MovableObject{
	private int fuelLevel;
	
	//Missiles Constructor
	public Missiles(){
		super.setColor(ColorUtil.GREEN);
		super.setSpeed();
		super.setRandomHeading();
		this.fuelLevel = getFuelLevel();
	}

//	public void setColor() {}
	
	public int getFuelLevel() {
		//put in timer, ticks down as time goes on, 10 ticks to failure
		return fuelLevel = 10;
	}

	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
//	public void decrememntFuelLevel() {}s
//	public int getHeading() {}
//	public void setHeading(int heading) {}
	
	public String toString(){
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Fuel Level: "+ this.fuelLevel+"\n";
		return returnStr;
	}
}
