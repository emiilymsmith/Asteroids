package com.mycompany.a1.GameObjects.MovableObjects.Ships;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObjects.MissileLauncher;
import com.mycompany.a1.GameObjects.MovableObjects.Ship;
import com.mycompany.a1.GameObjects.MovableObjects.SteerableMissileLauncher;
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

	public NonPlayerShip() {
		super.setRandomLocation();
		super.setColor(ColorUtil.GREEN);
		this.size = this.getRandomSize();
		super.setSpeed(5);
		super.setRandomHeading();
		super.setFixedMissileCount(); // 2 at most
		
		/* location, heading, speed */
		nonPlayerShipML = new MissileLauncher(super.getLocation(), super.getHeading(), super.getSpeed());
	}
	
	public MissileLauncher getML() {
        return nonPlayerShipML;
    }
	
	public void setSize(int size) {
//		this.size = rand.nextInt(3)+1; //includes 0, discludes 2
		this.size = size;
	}
	
	public int getRandomSize() {
		int num = r.nextInt(3) + 1; //+1 so it doesn't start at 0
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
