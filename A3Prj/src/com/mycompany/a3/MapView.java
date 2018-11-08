package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.a3.GameObjects.GameObject;

/**
 * Map View is an Observer
 *   
 *   */

public class MapView extends Container implements Observer{
	private IGameWorld igw;
	public MapView() {
		/* container for points view */
		Container mapContainer = new Container();

		mapContainer.setLayout(new FlowLayout());
		mapContainer.getAllStyles().setBgTransparency(255);
		mapContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		Label textLabel = new Label(" Map View Area ");
		textLabel.getAllStyles().setPaddingBottom(150);
		textLabel.getAllStyles().setPaddingRight(50);
		
		mapContainer.add(textLabel);
		
		this.add(mapContainer);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		IIterator theElements = igw.getIterator();
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			if(gameObj instanceof IDrawable) {
				gameObj.draw(g, new Point(this.getX(), this.getY()));
			}
		}
	}
	
	public void update(Observable o, Object arg) {
		igw = (IGameWorld) arg;
		igw.setWidth(this.getWidth());
		igw.setHeight(this.getHeight());
		igw.map();
		this.repaint();
	}
}
