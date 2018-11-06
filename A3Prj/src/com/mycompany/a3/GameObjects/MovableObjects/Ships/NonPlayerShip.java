package com.mycompany.a2.GameObjects.MovableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a2.GameObjects.MovableObjects.MissileLauncher;
import com.mycompany.a2.GameObjects.MovableObjects.Ship;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * NonPlayerShip is a child class of abstract class Ship - inherits MissileCount
 * uses a MissileLauncher object
 * 
 */
/* y creates a new NPS */
public class NonPlayerShip extends Ship{
	private int size;
	private MissileLauncher nonPlayerShipML;

	public NonPlayerShip(int width, int height) {
		super(width,height);
		super.setRandomLocation();
		super.setColor(ColorUtil.GREEN);
		this.size = this.getRandomSize();
		super.setSpeed(5);
		super.setRandomHeading();
		super.setFixedMissileCount(); // 2 at most
		
		/* location, heading, speed */
		nonPlayerShipML = new MissileLauncher(super.getLocation(), super.getHeading(), super.getSpeed(), width, height);
	}
	
	public MissileLauncher getML() {
        return nonPlayerShipML;
    }
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getRandomSize() {
		int num = r.nextInt(3) + 1; /*+1 so it doesn't start at 0*/
		return num;
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "NPS Size: "+this.size+"0\n";
		returnStr += "NonPlayerShip\n";		
		return returnStr;
	}
}
