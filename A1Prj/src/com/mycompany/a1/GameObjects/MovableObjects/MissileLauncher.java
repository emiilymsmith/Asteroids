package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObject;

public class MissileLauncher extends MovableObject{
	
	/*we need to pass through location, heading, speed*/
	public MissileLauncher(Point2D location,int heading, int speed) {
		super.setLocation(location); //get NPS location
		this.setHeading(heading); //get NPS heading
		super.setSpeed(speed); //same as NPS
		super.setColor(ColorUtil.WHITE);

	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Launcher: ";
		return returnStr;
	}
//	@Override
//	public void move() {}
	

}
/*
 * Tests:
 * turn playership without turning missile launcher
 * 
 * */
