package com.mycompany.a1.GameObjects.MovableObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.MovableObject;

public class Asteroids extends MovableObject{
	private int size;
	
	public Asteroids(){
		super.setColor(ColorUtil.BLACK);
		size = randomSize();
		super.setSpeed(); //me asking parent what my speed is
	}
	
	//getters and setters
	public void setSize(int size) {
		this.size = size;
	}

	public int randomSize() {
		int num = rand.nextInt((30 - 6) + 1) + 6;
		return num;
	}
	
//	public void setRandomHeading(int heading) {
//		//location = setX(rand.nextFloat()*1024);
//	}
//	public int getHeading() {}
	
	public String toString() {
		String returnStr = "";
		returnStr += "Asteroid Size: "+size;
		returnStr += "___________";
		return returnStr;
	}
	
}
