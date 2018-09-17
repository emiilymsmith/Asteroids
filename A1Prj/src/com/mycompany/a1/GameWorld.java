package com.mycompany.a1;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.GameObject;

public class GameWorld{
	Vector<GameObject> store = new Vector<GameObject>();
	
	// public void init(){}
	//public void play(){}
//	public void addAsteroid() {
//		//create Asteroid object
//		Asteroid asteroid = new Asteroid();
//		//add asteroid to storage vector
//		store.add(asteroid);
//		//feedback for creation
//		System.out.println("A new ASTEROID has been created");
//	}
	
	public void quitGW() {
		System.exit(0);
	}
	
}
