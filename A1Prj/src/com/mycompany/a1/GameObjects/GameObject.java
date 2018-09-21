package com.mycompany.a1.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import java.util.Random;

public abstract class GameObject{
	
	private Point2D location;
	private int color;
	protected Random rand = new Random();
	
	//GameObject constructors
	public GameObject(){

	}
	//getters and setters
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void getLocation() {
		
	}
	
	public void setLocation() {
		
	}
	
	public double getX() {
		return location.getX();
	}
	
	public void setX(double x) {
		location.setX(x);
	}
	
	public double getY() {
		return location.getY();
	}
	
	public void setY(double y) {
		location.setY(y);
	}
	
	public String toString(){
		double x = Math.round(this.getX());
		double y = Math.round(this.getY());
		String returnStr = "";
		returnStr += "___________________________";
		returnStr += "Location: ( "+x+" , "+y+" )";
		returnStr += "___________________________";
		returnStr += " " + "[" + ColorUtil.red(getColor()) + ","
                + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) + "] ";
		return returnStr;
	}

}