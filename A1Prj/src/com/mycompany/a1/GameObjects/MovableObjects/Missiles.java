package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObject;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Missiles
 * A Missile is an individual object because once it is fired it moves on it's own and can exist without a ship
 * 
 */
// f fires Missile
public class Missiles extends MovableObject{
	private int fuelLevel;
	
	/* Missiles Constructor */
	public Missiles(Point2D location, int heading, int speed){
		super.setColor(ColorUtil.BLACK); //from GameObject
		super.setLocation(location); //from MovableObject
		super.setHeading(heading); //from MovableObject //heading comes from ship
		super.setSpeed(speed+3); //from MovableObject //speed is greater than playership's like +2
		this.setFuelLevel(10); //= getFuelLevel(); //from here
	}
	
	/* Getters and Setters */
	public int getFuelLevel() {
		return fuelLevel = 10;
	}

	public void setFuelLevel(int fuel) {
		this.fuelLevel = fuel;
	}
	
	public String toString(){
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Fuel Level: "+ this.fuelLevel+"\n";
		returnStr += "Missile \n";
		return returnStr;
	}
}
