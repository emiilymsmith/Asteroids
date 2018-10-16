package com.mycompany.a2.GameObjects.MovableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a2.GameObjects.MovableObjects.ISteerable;
import com.mycompany.a2.GameObjects.MovableObjects.Ship;
import com.mycompany.a2.GameObjects.MovableObjects.SteerableMissileLauncher;
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

	private SteerableMissileLauncher playerShipML;
	
	public PlayerShip(int width, int height){
		super(width, height);
		Point2D point = new Point2D(width/2, height/2);

		super.setColor(ColorUtil.MAGENTA);
		super.setSpeed(0);
		super.setHeading(0);
		super.setLocation(point);
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
}
