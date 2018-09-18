package com.mycompany.a1;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.GameObject;
import com.mycompany.a1.GameObjects.FixedObjects.SpaceStation;
import com.mycompany.a1.GameObjects.MovableObjects.Asteroids;
import com.mycompany.a1.GameObjects.MovableObjects.Ships.NonPlayerShip;
import com.mycompany.a1.GameObjects.MovableObjects.Ships.PlayerShip;

public class GameWorld{
	Vector<GameObject> store = new Vector<GameObject>();
	
	// public void init(){}
	//play()
	
	public void addAsteroid() {
		//create Asteroid object
		Asteroids asteroid = new Asteroids();
		//add asteroid to storage vector
		store.add(asteroid);
		//feedback for creation
		System.out.println(asteroid);
		//System.out.println("An ASTEROID has been created.");
		
	}
	
	public void addNPS() {
		NonPlayerShip nps = new NonPlayerShip();
		store.add(nps);
		System.out.println("A new NON-PLAYERSHIP has been created.");
	}
	
	public void addSpaceStation() {
		SpaceStation bs = new SpaceStation();
		store.add(bs);
		System.out.println("A new SPACE STATION has been created.");
	}
	
	public void addPS() {
		PlayerShip ps = new PlayerShip();
		store.add(ps);
		System.out.println("A new PLAYER SHIP has been created.");
	}
	
//	public void increaseSpeed() {
//		PlayerShip ps = new PlayerShip();
//		store.add(ps);
//		System.out.println("PLAYER SHIP speed has been increased to: "+speed+".");
//	}
	
	
	
	
	public void quitGW() {
		System.exit(0);
	}
	
}
