package com.mycompany.a1.GameObjects.FixedObjects;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.FixedObject;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Blinking Space Station
 * 
 */
public class SpaceStation extends FixedObject {
	private int blinkRate;
	
	/* SpaceStation Constructor */
	public SpaceStation() {
		super.setId();
		this.blinkRate = getBlinkRate(); //from here
		super.setColor(ColorUtil.BLUE); //from GameObject
		super.setRandomLocation(); //from GameObject
	}

	public void toggleLight() {
		
	}
	
	public void setBlinkRate() {
		this.blinkRate = r.nextInt(5)+1; //+1 so it doesn't start at 0

	}
	
	public int getBlinkRate(){
		return this.blinkRate;
	}
	
	public String toString() {
		String returnStr = "";
		returnStr += super.toString();
		returnStr += "Blink Rate: "+this.blinkRate+"\n";
		return returnStr;
	}
}
