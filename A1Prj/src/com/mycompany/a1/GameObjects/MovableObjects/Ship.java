package com.mycompany.a1.GameObjects.MovableObjects;

import com.mycompany.a1.GameObjects.MovableObject;

public abstract class Ship extends MovableObject{
	private int missileCount;
	
	// Ship Constructor
	public Ship() {
		
	}
	
	//getters and setters
	public int getMissileCount() {
		return this.missileCount;
	}
	
	public void setMissileCount(int missile) {
		this.missileCount = missile;
	}
	
	public void setFixedMissileCount() {
		this.missileCount = 1000;
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Count: "+this.getMissileCount()+"\n";
		return returnStr;
	}
}
