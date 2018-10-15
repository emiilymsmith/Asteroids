package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

/**
 * Map View is an Observer
 *   
 *   */

public class MapView extends Container implements Observer{
	public MapView() {
		/* container for points view */
		Container myContainer = new Container();

		myContainer.setLayout(new FlowLayout());
		myContainer.getAllStyles().setBgTransparency(255);
		myContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		Label textLabel = new Label(" Map View Area ");
		textLabel.getAllStyles().setPaddingBottom(150);
		textLabel.getAllStyles().setPaddingRight(39);
		
		myContainer.add(textLabel);
		
		this.add(myContainer);
	}
	
	public void update(Observable o, Object arg) {
		IGameWorld igw = (IGameWorld) arg;
		igw.setHeight(this.getHeight());
		igw.setWidth(this.getWidth());
		igw.map();
		this.repaint();
		//System.out.println("Map Width: " + igw.getMapHeight() + "Map Height: " + igw.getMapWidth());
		/* Cast the observable objects as the GameWorld first to access variables */
//		GameCollection go = gw.getGameObjects();
//		IIterator gameIterator = go.getIterator();
//		while(gameIterator.hasNext()) {
//			System.out.println(gameIterator.getNext());
//		}
	}
}
