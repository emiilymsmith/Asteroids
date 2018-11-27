package com.mycompany.a3.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameObjects.MovableObjects.Ships.PlayerShip;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Steerable Missile Launcher is extended from Missile Launcher and assigned to the PlayerShip.
 * It's only difference is that its heading rotates independently of the PlayerShip.
 */
public class SteerableMissileLauncher extends MissileLauncher implements ISteerable{
	private Point missileOrigin;
	public SteerableMissileLauncher(Point location, int heading, int speed, int width, int height){
		super(location, heading, speed, width, height);
		super.setColor(ColorUtil.MAGENTA);
		missileOrigin = new Point((int)super.getX(),(int)super.getY());
	}
	
	/* This method's purpose is so that it will be able to rotate independently(left) of the PlayerShip */
	public void changeAngle(int degChange) {
		this.setHeading(this.getHeading() + degChange);
	}
		
	public Missiles fireMissile() {
//		Point p = new Point(0,0);
//		p = this.getLocation();
		return new Missiles(missileOrigin, this.getHeading(), this.getSpeed()+7, this.getWidth(), this.getHeight(), true);
		/* true if the missile is a playerships */
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
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		g.drawLine((int)(pCmpRelPrnt.getX() + this.getX()),
				(int)(pCmpRelPrnt.getY()+this.getY()),
				(int)((pCmpRelPrnt.getX() + this.getX() + 4)+super.getSpeed()*(Math.cos(Math.toRadians(90-this.getHeading())))),
				(int)((pCmpRelPrnt.getY() + this.getY() + 4)+super.getSpeed()*(Math.sin(Math.toRadians(90-this.getHeading())))));
		
	}
}
/*
 * Tests:
 * turn playership without turning steerable missile launcher
 * 
 * */
