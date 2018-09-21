package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObject;

public abstract class Ship extends MovableObject{
	//abstract??
	private int missileCount;
	private int color;
	private Point2D location;
	private int speed;
	
	public Ship() {
		
	}
	
}
