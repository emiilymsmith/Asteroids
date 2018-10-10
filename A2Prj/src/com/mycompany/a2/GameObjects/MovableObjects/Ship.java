package com.mycompany.a2.GameObjects.MovableObjects;

import com.mycompany.a2.GameObjects.MovableObject;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Ship is an Abstract class that only has the 
 * missile count for both PlayerShip and NonPlayerShip
 * 
 */
public abstract class Ship extends MovableObject{
	private int missileCount;
	
	/* Ship Constructor */
	public Ship() {
		
	}
	
	/* getters and setters */
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
