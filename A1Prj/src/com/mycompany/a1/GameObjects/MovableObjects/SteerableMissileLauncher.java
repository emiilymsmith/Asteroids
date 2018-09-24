package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.ui.geom.Point2D;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable{
	
	public SteerableMissileLauncher(Point2D location, int heading, int speed){
		super(location, heading, speed);
	}
	
	public void changeAngle(int degChange) {
		this.setHeading(this.getHeading() + degChange);
	}
	
//	public String toString() {}
	@Override
	public void turnLeft() {
		
	}
	@Override
	public void turnRight() {
		
	}
}
/*
 * Tests:
 * turn playership without turning steerable missile launcher
 * 
 * */
