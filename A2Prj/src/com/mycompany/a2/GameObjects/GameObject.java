package com.mycompany.a2.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import java.util.Random;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * GameObject has 2 kinds of abstract objects: FixedObjects and MovableObjects.
 * 
 */

public abstract class GameObject{
	
	private Point2D location;
	private int color;
	protected Random r = new Random();
	
	/* GameObject constructor */
	public GameObject(){
		
	}
	
	/* getters and setters */
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Point2D getLocation() {
		return this.location;
	}
	
	public void setLocation(Point2D point) {
		this.location = point;
	}
	
	public double getX() {
		return location.getX();
	}
	
	public void setX(double x) {
		if (x >= 0 && x <= 1024) {
			location.setX(x);
		} else {
			System.out.println("Input is out of bounds!");
		}
	}
	
	public double getY() {
		return location.getY();
	}
	
	public void setY(double y) {
		if (y >= 0 && y <= 768) {
			location.setY(y);
		} else {
			System.out.println("Input is out of bounds!");
		}
	}
	
	/*print color and location here*/
	public String toString(){
		/* Round locations to one decimal digit */
		double x = Math.round(this.getX()*10.0)/10.0;
		double y = Math.round(this.getY()*10.0)/10.0;
		
		String returnStr = "";
		returnStr += "Location: ("+x+", "+y+")\n";
		returnStr += "Color: " + "[" + ColorUtil.red(getColor()) + ","
                	+ ColorUtil.green(getColor()) + ","
                	+ ColorUtil.blue(getColor()) + "] ";
		returnStr += "\n";
		return returnStr;
		/* every game object should have a location */
	}
	
	/* Sets a new random location on the map */
	public void setRandomLocation() {
		Point2D point = new Point2D(0,0);
		point.setX(r.nextFloat()*1024);
		point.setY(r.nextFloat()*768);
		this.location = point;
	}

}