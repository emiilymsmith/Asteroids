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
	Vector<GameObject> store = new Vector<GameObject>();
	private int height;
	private int width;
	
	public void init(){
	}
	
	public void addAsteroid() {
		//create Asteroid object
		Asteroids asteroid = new Asteroids();
		//add asteroid to storage vector
		store.add(asteroid);
		//feedback for creation
		System.out.println("An ASTEROID has been created.");	
		System.out.println(asteroid);
	}
	
	public void addNPS() {
		//create NonPlayerShip object
		NonPlayerShip nps = new NonPlayerShip();
		//add NonPlayerShip to storage vector
		store.add(nps);
		//feedback for creation
		System.out.println("A new NON-PLAYERSHIP has been created.");
		System.out.println(nps);

	}
	
	public void addSpaceStation() {
		SpaceStation bs = new SpaceStation();
		store.add(bs);
		System.out.println("A new SPACE STATION has been created.");
		//System.out.println(bs);
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
	
//	public void decreaseSpeed() {}
//	public void turnLeft() {}
//	public void turnRight() {}
//	public void changeAngle() {}
	public void firePSMissile() {
		Missiles m = new Missiles();
		store.add(m);
		System.out.println("A new MISSILE has been created.");
		System.out.println(m);
	}
//	public void launchNPSMissile() {}
//	public void jump() {}
//	public void loadMissiles() {}
//	public void killedAsteroid() {}
//	public void eliminatedNPS() {}
//	public void explodePS() {}
//	public void crash() {}
//	public void hit() {}
//	public void exterminate() {}
//	public void impact() {}
//	public void ticked() {}
//	public void printDisplay() {}
	public void map() {
		//prints out a list of all the objects
		for (GameObject go: store)
			System.out.println(go.toString());
	}
	
	public void quitGW() {
		System.exit(0);
	}
	
	public String toString() {return null;}
	
}
