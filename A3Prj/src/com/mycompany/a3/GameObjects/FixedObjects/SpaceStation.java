package com.mycompany.a3.GameObjects.FixedObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.GameObjects.FixedObject;

/**
 * @author Emily Smith
 * @version 1.0
 * 
 * Blinking Space Station
 * Does not move.
 * 
 */
public class SpaceStation extends FixedObject {
	private int blinkRate;
	private int missileStock; /* unused on purpose, placeholder for future use */
	private int tickTock;
	
	/* SpaceStation Constructor */
	public SpaceStation(int width, int height) {
		super(width,height);
		super.setId();
		setBlinkRate(); //from here
		super.setColor(ColorUtil.BLUE); //from GameObject
		super.setRandomLocation(); //from GameObject
	}
	
	/* If the mod (%) of tickTock (ticks) is 0, tick the clock and trigger a different color */
	public void toggleLight() {
		tickTock ++;
        if((tickTock % this.getBlinkRate()) == 0)
        	super.setColor(ColorUtil.YELLOW);
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
		returnStr += "Space Station"+"\n";
		return returnStr;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
//		int radius = 50;
//		g.setColor(super.getColor());
//		if (go) 
//			g.drawArc((int)(pCmpRelPrnt.getX() + this.getX() - (2*radius)), (int)(pCmpRelPrnt.getY()+this.getY() - (2*radius)), 2*radius, 2*radius, 0, 360);
//		else 
//			g.fillArc((int)(pCmpRelPrnt.getX() + this.getX() - (2*radius)), (int)(pCmpRelPrnt.getY()+this.getY() - (2*radius)), 2*radius, 2*radius, 0, 360);
	}
}
