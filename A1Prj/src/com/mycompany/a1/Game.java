package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Point2D;

public class Game extends Form{
	private GameWorld gw;
	
	private int size;
	private Point2D location;
	private int color;
	
	//	Game Constructor
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
		
	}
	
	public void play(){
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				switch(sCommand.charAt(0)) 
				{
					case 'a':
						gw.addAsteroid();
						break;
					case 'y':
						gw.addNPS();
						break;
					case 'b':
						gw.addSpaceStation();
						break;
					case 's':
						gw.addPS();
						break;
					case 'i':
						//gw.increaseSpeed();
						break;
					case 'd':
						//gw.decreaseSpeed();
						break;
					case 'l':
						//gw.turnLeft();
						break;
					case 'r':
						//gw.turnRight();
						break;
					case '<':
						//gw.changeAngle();
						break;
					case 'f':
						gw.firePSMissile();
						break;
					case 'L':
						//gw.launchNPSMissile();
						break;
					case 'j':
						//gw.jump();
						break;
					case 'n':
						//gw.loadMissiles();
						break;
					case 'k':
						//gw.killedAsteroid();
						break;
					case 'e':
						//gw.eliminatedNPS();
						break;
					case 'E':
						//gw.explodePS();
						break;
					case 'c':
						//gw.crash();
						break;
					case 'h':
						//gw.hit();
						break;
					case 'x':
						//gw.exterminate();
						break;
					case 'I':
						//gw.impact();
						break;
					case 't':
						//gw.ticked();
						break;
					case 'p':
						//gw.printDisplay();
						break;
					case 'm':
						gw.map();
						break;
					case 'q':
						gw.quitGW();
						break;
				} //end switch
			}//action Performed
		} //new ActionListener()
		); //addActionListener -- lambda expressions
		}//play
	
}
