package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.MovableObject;

// a creates an Asteroid
public class Asteroids extends MovableObject{
	private int size;
	
	/* Asteroid Constructor */
	public Asteroids(){
		super.setColor(ColorUtil.BLACK); // from GameObject
		this.size = getRandomSize(); //from here
		super.setRandomHeading();// from MovableObject
		super.setRandomSpeed(); //me asking parent what my speed is
		super.setRandomLocation(); //from GameObject
	}
	
	//getters and setters
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getRandomSize() {
		int num = r.nextInt((30 - 6) + 1) + 6; //max-min +1 so it doesn't start at 0
		return num;
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Asteroid Size: "+this.size+"\n";
		return returnStr;
	}
}
