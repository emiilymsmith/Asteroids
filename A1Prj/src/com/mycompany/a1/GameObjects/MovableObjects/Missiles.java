package com.mycompany.a1.GameObjects.MovableObjects;

import com.mycompany.a1.GameObjects.MovableObject;

public class Missiles extends MovableObject{
	private int speed;
	private int fuelLevel;
	
	//Missilesconstructor
	public Missiles(){
		
	}

	public int getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	//public String toString(){}
}
