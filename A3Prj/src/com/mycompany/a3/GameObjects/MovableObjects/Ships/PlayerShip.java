package com.mycompany.a3.GameObjects.MovableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.MovableObjects.ISteerable;
import com.mycompany.a3.GameObjects.MovableObjects.Missiles;
import com.mycompany.a3.GameObjects.MovableObjects.Ship;
import com.mycompany.a3.GameObjects.MovableObjects.SteerableMissileLauncher;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * PlayerShip inherits MissileCount from abstract class Ship
 * implements interface "ISteerable"
 * uses a steerable missile launcher
 * 
 */
public class PlayerShip extends Ship implements ISteerable{
	private int lives;
	private Point center,top, bottomLeft, bottomRight;
	private SteerableMissileLauncher playerShipML;
	
	public PlayerShip(int width, int height){
		super(width, height);
		center = new Point(width/2,height/2);
		top = new Point(0,height/2);
		bottomLeft = new Point( -width/2, -height/2);
		bottomRight = new Point( width/2, -height/2);
		
		super.setColor(ColorUtil.MAGENTA); /* set transparency*/
		super.setSize(20);
		super.setSpeed(0);
		super.setHeading(0);
		super.setLocation(center);
		super.setMissileCount(10); // has to be 10
		playerShipML = new SteerableMissileLauncher(super.getLocation(), super.getHeading(), super.getSpeed(),width,height);
	}
	
	/* getters and setters */
	public SteerableMissileLauncher getPSML() {
        return playerShipML;
    }
	
	public void moveMLLeft() {
		playerShipML.turnLeft();
	}
	
	public void moveMLRight() {
		playerShipML.turnRight();
	}
	
	public int getLives() {
		return lives = 3;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void changeHeading(int degChange){
		this.setHeading(this.getHeading() + degChange);
	}
	
	/* Have to reflect and update speed in the playership missile launcher and playership*/
	public void increasePSSpeed() {
		super.increaseSpeed();
		playerShipML.increaseSpeed();
	}
	
	public void decreasePSSpeed() {
		super.decreaseSpeed();
		playerShipML.decreaseSpeed();
	}
	
	public Missiles fireMissile() {
		return playerShipML.fireMissile();
	}
	
	public String toString(){
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Ship Lives: "+ this.getLives()+"\n";
		returnStr += "PlayerShip\n";
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
		System.out.println("PLAYERSHIP TURNED LEFT");
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
				(int)(pCmpRelPrnt.getY()+ this.getY() + super.getSize()),
				(int)(pCmpRelPrnt.getX() - super.getSize() + this.getX()),
				(int)(pCmpRelPrnt.getY() - super.getSize() + this.getY())); 
		
		g.drawLine ((int)(pCmpRelPrnt.getX() - super.getSize() + this.getX()),
				(int)(pCmpRelPrnt.getY() - super.getSize() + this.getY()),
				(int)(pCmpRelPrnt.getX() + super.getSize() + this.getX()),
				(int)(pCmpRelPrnt.getY() - super.getSize() + this.getY()));
		
		g.drawLine ((int)(pCmpRelPrnt.getX() + super.getSize() + this.getX()),
				(int)(pCmpRelPrnt.getY() - super.getSize() + this.getY()),
				(int)(pCmpRelPrnt.getX()  + this.getX()),
				(int)(pCmpRelPrnt.getY() + super.getSize() + this.getY()));
		
		playerShipML.draw(g, pCmpRelPrnt);
		
//		g.drawLine((int)(pCmpRelPrnt.getX() + top.getX()), 
//				(int)(pCmpRelPrnt.getY() + top.getY() + super.getSize()),
//				(int)(pCmpRelPrnt.getX() + bottomLeft.getX() - super.getSize()),
//				(int)(pCmpRelPrnt.getY() + bottomLeft.getY() - super.getSize())); 
//		
//		g.drawLine ((int)(pCmpRelPrnt.getX() + bottomLeft.getX() - super.getSize()),
//				(int)(pCmpRelPrnt.getY() + bottomLeft.getY() - super.getSize()),
//				(int)(pCmpRelPrnt.getX() + bottomRight.getX() + super.getSize()),
//				(int)(pCmpRelPrnt.getY() + bottomRight.getY() - super.getSize()));
//		
//		g.drawLine ((int)(pCmpRelPrnt.getX() + bottomRight.getX() + super.getSize()),
//				(int)(pCmpRelPrnt.getY() + bottomRight.getY() - super.getSize()),
//				(int)(pCmpRelPrnt.getX() + top.getX()),
//				(int)(pCmpRelPrnt.getY() + top.getY() + super.getSize()));
	}


	@Override
	public void handleCollision(ICollider obj) {
		// TODO Auto-generated method stub
		
	}

}
