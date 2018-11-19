package com.mycompany.a3.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;

import java.util.Random;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * GameObject has 2 kinds of abstract objects: FixedObjects and MovableObjects.
 * 
 */

public abstract class GameObject implements IDrawable, ICollider{
	private Point location;
	private int color, width, height, size;
	protected Random r = new Random();
	private boolean exploded;
	
	/* GameObject constructor */
	public GameObject(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		/* Do nothing here */
		// This is for testing
		//System.out.println("WRONG DRAW");
	}
	
	/* getters and setters */
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point point) {
		this.location = point;
	}
	
	public double getX() {
		return location.getX();
	}
	
	public void setX(double x) {
		if (x >= 0 && x <= this.getWidth()) {
			location.setX((int) x);
		} else {
			System.out.println("Input is out of bounds!");
		}
	}
	
	public double getY() {
		return location.getY();
	}
	
	public void setY(double y) {
		if (y >= 0 && y <= this.getHeight()) {
			location.setY((int) y);
		} else {
			System.out.println("Input is out of bounds!");
		}
	}
	
	@Override
	public boolean collisionWith(ICollider obj) {
		
		GameObject otherObject = (GameObject)obj;
		
		boolean collide = false;
		int thisCenterX = (int)this.getX() + (this.getSize()/2); // find centers
		int thisCenterY = (int)this.getY() + (this.getSize()/2);
		int otherCenterX = (int)otherObject.getX() + (this.getSize()/2);
		int otherCenterY = (int)otherObject.getY() + (this.getSize()/2);
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		+ otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) {
			collide = true ;
		}
		return collide;
	}
	
	public void isExploded() {
		this.exploded = true;
	}
	
	public boolean getExploded() {
		return this.exploded;
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
		Point point = new Point(0,0);
		point.setX((int) (r.nextFloat()*this.getWidth()-(size/2)));
		point.setY((int) (r.nextFloat()*this.getHeight()-(size/2)));
		this.location = point;
	}

}