package com.mycompany.a3.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.MovableObject;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Missile Launcher is assigned to NonPlayerShip and also is the parent class for Steerable Missile Launcher.
 * 
 */
public class MissileLauncher extends MovableObject{
	
	/*we need to pass through location, heading, speed*/
	public MissileLauncher(Point location,int heading,int speed,int width,int height) {
		super(width, height);
		super.setLocation(location); //get NPS location
		this.setHeading(heading); //get NPS heading
		super.setSpeed(speed); //same as NPS
		super.setColor(ColorUtil.rgb(255, 0, 0));
	}
	
	public Missiles fireMissile() {
		return new Missiles(this.getX(), this.getY(), this.getHeading(), this.getSpeed()+2, this.getWidth(), this.getHeight(), false);
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Launcher: ";
		return returnStr;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		g.drawLine((int)(pCmpRelPrnt.getX() + this.getX()),
				(int)(pCmpRelPrnt.getY()+this.getY()),
				(int)((pCmpRelPrnt.getX() + this.getX())+super.getSpeed()*(Math.cos(Math.toRadians(90-this.getHeading())))),
				(int)((pCmpRelPrnt.getY() + this.getY())+super.getSpeed()*(Math.sin(Math.toRadians(90-this.getHeading())))));
		
	}

	@Override
	public void handleCollision(ICollider obj) {
		// TODO Auto-generated method stub
		
	}

}
