package com.mycompany.a2.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a2.GameObjects.MovableObjects.Ships.PlayerShip;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Steerable Missile Launcher is extended from Missile Launcher and assigned to the PlayerShip.
 * It's only difference is that its heading rotates independently of the PlayerShip.
 */
public class SteerableMissileLauncher extends MissileLauncher implements ISteerable{
	
	public SteerableMissileLauncher(Point2D location, int heading, int speed){
		super(location, heading, speed);
		super.setColor(ColorUtil.YELLOW);
	}
	
	/* This method's purpose is so that it will be able to rotate independently(left) of the PlayerShip */
	public void changeAngle(int degChange) {
		this.setHeading(this.getHeading() + degChange);
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Steerable Missile Launcher"+"\n";
		return returnStr;
	}
	@Override
	public void turnLeft() {
		int heading = super.getHeading();
		if(heading-15 >= 0) {
			super.setHeading(heading-15);
		} else {
			super.setHeading(heading-15+360);
		}
		System.out.println("PlayerShipMissileLauncher LEFT");
	}
	
	@Override
	public void turnRight() {
		int heading = super.getHeading();
		if(heading+15 <= 0) {
			super.setHeading(heading+15);
		} else {
			super.setHeading(heading+15-360);
		}
		System.out.println("PLAYERSHIP TURNED RIGHT");
	}
}
/*
 * Tests:
 * turn playership without turning steerable missile launcher
 * 
 * */
