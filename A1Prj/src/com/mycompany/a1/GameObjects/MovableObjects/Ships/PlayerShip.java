package com.mycompany.a1.GameObjects.MovableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObjects.ISteerable;
import com.mycompany.a1.GameObjects.MovableObjects.Ship;
import com.mycompany.a1.GameObjects.MovableObjects.SteerableMissileLauncher;

// s creates a player ship
public class PlayerShip extends Ship implements ISteerable{
	//private int size;
	private int lives;
	private int missileCount;
	
	private SteerableMissileLauncher playerShipML;
	
	public PlayerShip(){
		Point2D point = new Point2D(512,384);
		
		super.setColor(ColorUtil.MAGENTA);
		super.setSpeed(0);
		super.setHeading(0);
		super.setLocation(point);
		this.missileCount = 10; // has to be 10
		
		playerShipML = new SteerableMissileLauncher();
	}
	
	// getters and setters
	public int getLives() {
		return lives = 3;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getMissileCount() {
		return this.missileCount;
	}
	
	public void setMissileCount(int missile) {
		this.missileCount = missile;
	}
	
	public void changeHeading(int degChange){
		this.setHeading(this.getHeading() + degChange);
	}
	
	public String toString(){
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Player Ship Missile Count: "+this.getMissileCount()+"\n";
		returnStr += "Ship Lives: "+ this.getLives();
		return returnStr;
	}
	
	@Override
	public void turnLeft() {
		
	}
	@Override
	public void turnRight() {
		
	}
}
