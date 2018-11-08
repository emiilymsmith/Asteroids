package com.mycompany.a3.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
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
	public MissileLauncher(Point2D location,int heading,int speed,int width,int height) {
		super(width, height);
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
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		//g.setColor(ColorUtil.BLACK);
		//g.drawRect((int)(pCmpRelPrnt.getX() + this.getX() - size/2), (int)(pCmpRelPrnt.getY()+this.getY()), size, size);	
	}

}
