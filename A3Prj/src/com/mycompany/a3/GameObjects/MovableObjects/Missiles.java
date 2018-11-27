package com.mycompany.a3.GameObjects.MovableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.ISelectable;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.MovableObject;
import com.mycompany.a3.GameObjects.MovableObjects.Ships.NonPlayerShip;
import com.mycompany.a3.GameObjects.MovableObjects.Ships.PlayerShip;
/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Missiles
 * A Missile is an individual object because once it is fired it moves on it's own and can exist without a ship
 * 
 */
public class Missiles extends MovableObject implements ISelectable{
	private int fuelLevel;
	private boolean mType, selected; //missile type
//	private Point missileOrigin;
	
	/* Missiles Constructor */
	public Missiles(Point location, int heading, int speed,int width, int height, boolean mType){
		super(width, height);
		super.setColor(ColorUtil.rgb(75,0,130));
		this.mType = mType;
//		super.setX(x); //from Game Object //TODO ERROR
//		super.setY(y);
//		missileOrigin = new Point((int)super.getX(),(int)super.getY());
		super.setLocation(location); //uses parent location TODo this needs to change
		super.setSize(7);
		super.setHeading(heading); //from MovableObject //heading comes from ship
		super.setSpeed(speed); //from MovableObject //speed is greater than playership's
		this.setFuelLevel(30); //= getFuelLevel(); //from here
	}
	
	/* Getters and Setters */
	public int getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(int fuel) {
		this.fuelLevel = fuel;
	}
	
//	public void setLocation(int x, int y) {
//		Point p = new Point(x,y);
//		super.setLocation(p);
//	}
	
	public boolean getMType() {
		return this.mType;
	}
	
	public String toString(){
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Missile Fuel Level: "+ this.fuelLevel+"\n";
		returnStr += "Missile \n";
		return returnStr;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		g.fillRect((int)(pCmpRelPrnt.getX()+this.getX()), (int)(pCmpRelPrnt.getY()+this.getY()), super.getSize(), 2*super.getSize());
	}

	@Override
	public void handleCollision(ICollider obj) {
		GameObject gameObj = (GameObject)obj;
		if(obj instanceof PlayerShip) {
			PlayerShip pship = (PlayerShip)obj;
			if(this.mType == false) {
				this.poofed();
				pship.poofed();
			}
		} else if(obj instanceof NonPlayerShip) {
			NonPlayerShip nonPS = (NonPlayerShip)obj;
			if(this.mType == true) {
				this.poofed();
				nonPS.poofed();
			}
		} else if (obj instanceof Missiles){
			Missiles m = (Missiles)obj;
			this.poofed();
			m.poofed();
		} else {}
	}
	
	@Override
	public void setSelected(boolean select) {
		this.selected = select;
	}
	
	@Override
	public boolean isSelected() {
		return selected;
	}
	
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt){
		boolean contains = false;
		int pointx = pPtrRelPrnt.getX();
		int pointy = pPtrRelPrnt.getY();
		int locationx = this.getLocation().getX() + pCmpRelPrnt.getX();
		int locationy = this.getLocation().getY() + pCmpRelPrnt.getY();
		
		if(pointx >= locationx && pointx <= locationx+7 && pointy >= locationy && pointy <= locationy+14) {
			contains = true;
		}
		
		return contains;
	}
}
