package com.mycompany.a1.GameObjects;

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
		returnStr += "Space Station ID: 1"+this.getId()+"\n";
		return returnStr;
	}
}
