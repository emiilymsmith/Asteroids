package com.mycompany.a3.GameObjects.MovableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.MovableObjects.MissileLauncher;
import com.mycompany.a3.GameObjects.MovableObjects.Ship;
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
		super.setColor(ColorUtil.BLUE);
		super.setSize(this.setRandomSize());
		super.setSpeed(5);
		super.setRandomHeading();
		super.setFixedMissileCount(); // 2 at most
		
		/* location, heading, speed */
		nonPlayerShipML = new MissileLauncher(super.getLocation(), super.getHeading(), super.getSpeed(), width, height);
	}
	
	public MissileLauncher getML() {
        return nonPlayerShipML;
    }
	
	public int getSize() {
		return this.size;
	}
	
	public int setRandomSize() {
		int rsize = r.nextInt(15) + 1; /*+1 so it doesn't start at 0*/
		if(rsize > 7) {
			rsize = 15;
		} else
			rsize = 11;
		return rsize;
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "NPS Size: "+this.size+"0\n";
		returnStr += "NonPlayerShip\n";		
		return returnStr;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		g.fillTriangle((int)(pCmpRelPrnt.getX()+this.getX()+0), (int)(pCmpRelPrnt.getY()+this.getY()+super.getSize()),
				(int)(pCmpRelPrnt.getX()+this.getX()-super.getSize()), (int)(pCmpRelPrnt.getY()+this.getY()-super.getSize()),
				(int)(pCmpRelPrnt.getX()+this.getX()+super.getSize()), (int)(pCmpRelPrnt.getY()+this.getY()-super.getSize()));
		nonPlayerShipML.draw(g, pCmpRelPrnt);
	}
	/**
	 *  fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) 
	 *  x1 - the x coordinate of the first vertex of the triangle
	 *  y1 - the y coordinate of the first vertex of the triangle
	 *  x2 - the x coordinate of the second vertex of the triangle
	 *  y2 - the y coordinate of the second vertex of the triangle
	 *  x3 - the x coordinate of the third vertex of the triangle
	 *  y3 - the y coordinate of the third vertex of the triangle
	 *  */

	@Override
	public void handleCollision(ICollider obj) {
		// TODO Auto-generated method stub
		
	}

}
