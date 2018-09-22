package com.mycompany.a1.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.MovableObject;

//NOT ABSTRACT
public class MissileLauncher extends MovableObject{
	private int heading; //we dont need this to be independent, it should be declared in NPS object
	//speed
	
	public MissileLauncher() {
		this.setHeading(heading); //get NPS heading
		super.getLocation(); //get NPS location
		super.setRandomSpeed(); //same as NPS
		super.setColor(ColorUtil.WHITE);
//		Get NPS location and assign to that?? do in NPS??
//		for heading and speed too, needs to track
//		super.setLocation(point);
	}

	public void setHeading() {
		this.heading = super.getHeading(); //gets supers heading, assigns it to this heading, does it interfere with Steerable ML?
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Launcher";
		return returnStr;
	}
//	@Override
//	public void move() {}

}

