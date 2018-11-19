package com.mycompany.a3.GameObjects;

import com.mycompany.a3.ICollider;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * FixedObject is an abstract class that one objects that extends it.
 * Blinking Space Station
 * It has a Fixed location.
 * 
 */
public class FixedObject extends GameObject{
	private int id; // counter
	private static int bsid;
	
	public FixedObject(int width, int height) {
		super(width,height);
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId() {
		id = ++bsid;
	}
	
	public String toString(){
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Space Station ID: "+this.getId()+"\n";
		return returnStr;
	}

	@Override
	public boolean collisionWith(ICollider obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(ICollider obj) {
		// TODO Auto-generated method stub
		
	}
}
