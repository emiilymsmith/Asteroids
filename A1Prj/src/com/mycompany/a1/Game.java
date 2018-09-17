package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.geom.Point2D;

public class Game extends Form{
	private GameWorld gw;
	
	private int size;
	private Point2D location;
	private int color;
	
	//	Game Constructor
	public Game() {
		gw = new GameWorld();
		//gw.init();
		//gw.play();
	}
	
	
}
