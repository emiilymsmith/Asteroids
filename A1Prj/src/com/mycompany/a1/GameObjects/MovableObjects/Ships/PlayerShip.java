package com.mycompany.a1.GameObjects.MovableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObjects.ISteerable;
import com.mycompany.a1.GameObjects.MovableObjects.MissileLauncher;
import com.mycompany.a1.GameObjects.MovableObjects.Ship;
import com.mycompany.a1.GameObjects.MovableObjects.SteerableMissileLauncher;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * PlayerShip inherits MissileCount from abstract class Ship
 * implements interface "ISteerable"
 * uses a steerable missile launcher
 * 
 */
// s creates a player ship
public class PlayerShip extends Ship implements ISteerable{
	private int size;
	private int lives;

	private SteerableMissileLauncher playerShipML;
	
	public PlayerShip(){
		Point2D point = new Point2D(512,384);
		
		super.setColor(ColorUtil.MAGENTA);
		super.setSpeed(0);
		super.setHeading(0);
		super.setLocation(point);
		super.setMissileCount(10); // has to be 10
		 /*TODO: copy missile launcher and NPS*/
		playerShipML = new SteerableMissileLauncher(super.getLocation(), super.getHeading(), super.getSpeed());
	}
	
	// getters and setters
	public SteerableMissileLauncher getPSML() {
        return playerShipML;
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
		returnStr += "Player Ship Missile Count: "+super.getMissileCount()+"\n";
		returnStr += "Ship Lives: "+ this.getLives();
		returnStr += "\n";
		return returnStr;
	}
	
	@Override
	public void turnLeft() {
		
	}
	@Override
	public void turnRight() {
		
	}
}
