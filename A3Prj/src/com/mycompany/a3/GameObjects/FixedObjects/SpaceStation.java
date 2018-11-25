package com.mycompany.a3.GameObjects.FixedObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.GameObjects.FixedObject;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.MovableObjects.Asteroids;
import com.mycompany.a3.GameObjects.MovableObjects.Missiles;
import com.mycompany.a3.GameObjects.MovableObjects.Ships.NonPlayerShip;
import com.mycompany.a3.GameObjects.MovableObjects.Ships.PlayerShip;

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
	private boolean blink;
	
	/* SpaceStation Constructor */
	public SpaceStation(int width, int height) {
		super(width,height);
		super.setId();
		setBlinkRate(); //from here
		super.setColor(ColorUtil.YELLOW); //from GameObject
		super.setRandomLocation(); //from GameObject
		super.setSize(10);
	}
	
	/* If the mod (%) of tickTock (ticks) is 0, tick the clock and trigger a different color */
	public void toggleLight() {
//		tickTock ++;
//        if((tickTock % this.getBlinkRate()) == 0)
//        	super.setColor(ColorUtil.YELLOW);
		blink = !blink;
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
		int radius = 15;
		g.setColor(super.getColor()); /* gets the color set in constructor */
		if (blink) 
			g.drawArc((int)(pCmpRelPrnt.getX() + this.getX() - (super.getSize() * radius)), 
					(int)(pCmpRelPrnt.getY()+this.getY() - (super.getSize() * radius)), super.getSize() * radius, super.getSize() * radius, 0, 360);
		else 
			g.fillArc((int)(pCmpRelPrnt.getX() + this.getX() - (super.getSize() * radius)), 
					(int)(pCmpRelPrnt.getY()+this.getY() - (super.getSize() * radius)), super.getSize() * radius, super.getSize() * radius, 0, 360);
	}
	@Override
	public void handleCollision(ICollider obj) {
		GameObject otherObj = (GameObject)obj;
		if(obj instanceof PlayerShip){
			PlayerShip ps = (PlayerShip)obj;
			ps.setMissileCount(10);
		} else {/* do nothing */}
	}
}
