package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class MapView extends Container implements Observer{
public void update(Observable o, Object arg) {
		//System.out.println("Map Width: " + Game.getMapHeight() + "Map Height: " + Game.getMapWidth());
		/* Cast the observable objects as the GameWorld first to access variables */
//		GameWorld gw = (GameWorld)arg; //using arg instead of o uses the proxy, initializing not instantiating
//		GameCollection go = gw.getGameObjects();
//		IIterator gameIterator = go.getIterator();
//		while(gameIterator.hasNext()) {
//			System.out.println(gameIterator.getNext());
//		}
	}
}
