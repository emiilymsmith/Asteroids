package com.mycompany.a1;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.GameObject;
import com.mycompany.a1.GameObjects.FixedObjects.SpaceStation;
import com.mycompany.a1.GameObjects.MovableObjects.Asteroids;
import com.mycompany.a1.GameObjects.MovableObjects.Missiles;
import com.mycompany.a1.GameObjects.MovableObjects.Ships.NonPlayerShip;
import com.mycompany.a1.GameObjects.MovableObjects.Ships.PlayerShip;

public class GameWorld{
	Vector<GameObject> storage = new Vector<GameObject>();
	private int height;
	private int width;
	
	private int ticks;
	private int score;
	
	private final int PLAYERSHIP_LIVES = 3;
	private final int ASTEROID_SCORE = 10;
	
	public void init(){
		ticks = 0;
		score = 0;
	}
	
	public void addAsteroid() {
		//create Asteroid object
		Asteroids asteroid = new Asteroids();
		//add asteroid to storage vector
		storage.add(asteroid);
		//feedback for creation
		System.out.println("An ASTEROID has been created.");	
		System.out.println(asteroid);
	}
	
	public void addNPS() {
		//create NonPlayerShip object
		NonPlayerShip nps = new NonPlayerShip();
		//add NonPlayerShip to storage vector
		storage.add(nps);
		//feedback for creation
		System.out.println("A new NON-PLAYERSHIP has been created.");
		System.out.println(nps);
	}
	
	public void addSpaceStation() {
		SpaceStation bs = new SpaceStation(); //blinking space station
		storage.add(bs);
		System.out.println("A new SPACE STATION has been created.");
		System.out.println(bs);
	}
	
	public void addPS() {
		if(!psExists()) {
			PlayerShip ps = new PlayerShip();
			storage.add(ps);
			//SteerableMissileLauncher sml = ps.getSteerableMissileLauncher();
			//storage.add(sml) //steerableMissileLauncher;
			System.out.println("A new PLAYER SHIP has been created.");
			System.out.println(ps);
		} else {
			System.err.println("A PLAYERSHIP already exists");
		}
	}
	
	public void increaseSpeed() {
		if(psExists()) {
            for (GameObject pShip : storage) {
                if (pShip instanceof PlayerShip) {
                    int currSpeed = ((PlayerShip) pShip).getSpeed();
                    if (currSpeed > 8) {
                        System.out.println("Cannot increase Player Ship speed.");
                    } else {
                        ((PlayerShip) pShip).setSpeed(currSpeed + 2);
                        System.out.println("PLAYER SHIP speed has INCREASED");
                    }
                }
            }
        }
        else
            System.err.println("PLAYER SHIP speed has not increased.");
    	}
	
//	public void decreaseSpeed() {}
//	public void turnLeft() {}
//	public void turnRight() {}
//	public void changeAngle() {}
	
	public void firePSMissile() {
		Missiles m = new Missiles();
		storage.add(m);
		System.out.println("A new MISSILE has been created.");
		System.out.println(m);
	}
	
//	public void launchNPSMissile() {}
//	public void jump() {}
//	public void loadMissiles() {}
	
	public void killedAsteroid() {
		if( asteroidExists() & missileExists()) {
			removeAsteroid();
			removeMissile();
			score += ASTEROID_SCORE;
			System.out.println("Asteroid was HIT by PS MISSILE");
		} else
			System.err.println("Could not hit Asteroid with a Player Ship Missile");
	}
	
//	public void eliminatedNPS() {}
	
	public void explodePS() {
		System.out.println("Your PLAYER SHIP has exploded.");
	}
//	public void crash() {}
//	public void hit() {}
//	public void exterminate() {}
//	public void impact() {}
//	public void ticked() {}
//	public void printDisplay() {}
	public void map() {
		//prints out a list of all the objects
		for (GameObject go: storage)
			System.out.println(go.toString());
	}
	
	/* q */
	public void quitGW() {
		System.exit(0);
	}
	
	public String toString() {return null;}
	
	private boolean asteroidExists(){
        boolean aexists = false;
        for(GameObject asteroid: storage) {
            if(asteroid instanceof Asteroids) {
                aexists = true;
            }
        }
        if(!aexists)
            System.out.println("No ASTEROIDS exist.");
        return aexists;
    }
	
	public boolean psExists() {
		boolean psexists = false;
        for(GameObject pShip: storage) {
            if(pShip instanceof PlayerShip) {
                psexists = true;
            }
        }
        if(!psexists)
            System.out.println("PLAYER SHIP does not exist.");
        return psexists;
	}
	
	private boolean missileExists() {
        boolean mexists = false;
        for(GameObject missile: storage) {
            if(missile instanceof Missiles) {
                mexists = true;
            }
        }
        if(!mexists)
            System.out.println("A MISSILE does not exist.");
        return mexists;
    }
	 /*k */
	private boolean removeAsteroid(){
        for(GameObject asteroid: storage) {
            if(asteroid instanceof Asteroids) {
                storage.remove(asteroid);
                System.out.println("Removed ASTEROID.");
                return true;
            }
        }
        System.err.println("Did not remove ASTEROID.");
        return false;
    }
	
	private boolean removeMissile(){
        for(GameObject missile: storage) {
            if(missile instanceof Missiles) {
                storage.remove(missile);
                System.out.println("Removed MISSILE.");
                return true;
            }
        }
        System.err.println("Did not remove MISSILE.");
        return false;
    }
	
	
	
	
}
