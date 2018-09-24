package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.GameObject;
import com.mycompany.a1.GameObjects.MovableObjects.Ships.PlayerShip;
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
	}
	
	public void changeAngle(int degChange) {
		this.setHeading(this.getHeading() + degChange);
	}
	
//	public String toString() {}
	@Override
	public void turnLeft() {
		//SteerableMissileLauncher.changeHeading(-15); //changeHeading from Steerable
		System.out.println("PLAYERSHIP LEFT");
	}
	
	@Override
	public void turnRight() {
		System.out.println("PLAYERSHIP RIGHT");
	}
}
/*
 * Tests:
 * turn playership without turning steerable missile launcher
 * 
 * */
