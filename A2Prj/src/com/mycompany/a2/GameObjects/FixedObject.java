package com.mycompany.a2.GameObjects;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * FixedObject is an abstract class that one objects that extends it.
 * Blinking Space Station
 * It has a Fixed location.
 * 
 */
public class FixedObject extends GameObject {
	private int id; // counter
	private static int bsid;
	
	public FixedObject() {
		
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
}
