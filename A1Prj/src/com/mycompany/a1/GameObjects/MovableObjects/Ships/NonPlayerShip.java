package com.mycompany.a1.GameObjects.MovableObjects.Ships;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.MovableObjects.Ship;

// y creates NPS
public class NonPlayerShip extends Ship{
	private int size;

	public NonPlayerShip() {
		super.setColor(ColorUtil.GREEN);
		this.size = this.getRandomSize();
		super.setSpeed(15);
		super.setRandomHeading();
		super.setFixedMissileCount(); //random
	}
	
	public void setSize(int size) {
//		this.size = rand.nextInt(3)+1; //includes 0, discludes 2
		this.size = size;
	}
	
	public int getRandomSize() {
		int num = r.nextInt(3) + 1; //+1 so it doesn't start at 0
		return num;
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Set NPS Size to: "+this.size+"0\n";
		//returnStr += " Set NPS Speed: "+speed;
		return returnStr;
	}
}
