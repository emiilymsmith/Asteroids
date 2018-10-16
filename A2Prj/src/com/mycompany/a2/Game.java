package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.Commands.AddAsteroidCommand;
import com.mycompany.a2.Commands.AddNPSCommand;
import com.mycompany.a2.Commands.AddPSCommand;
import com.mycompany.a2.Commands.AddSpaceStationCommand;
import com.mycompany.a2.Commands.LeftTurnCommand;

/**
 * @author Emily Smith
 * @version 2.0
 * 
 * A Game object contains a GameWorld that holds a collection of game objects 
 * and other state variables. It also holds a method play() to accept and 
 * execute user commands. Instance constructed in Starter().
 * 
 * MVC role = Controller
 * Enforces rules: what actions from user and the corresponding result.
 * Will also be responsible for displaying information.
 * 
 * A2 additions:
 * 		removing functionality of play() to initializing mapview and pointsview
 */

public class Game extends Form {
	/* State Variable */
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	
	private LeftTurnCommand ltCommand;
	private AddAsteroidCommand addAsteroidCommand;
	private AddNPSCommand addNPSCommand;
	private AddSpaceStationCommand addSSCommand;
	private AddPSCommand addPSCommand;
	
	/* Game Constructor */
	public Game() {
		setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView();
		pv = new PointsView(gw);
		
		gw.init(mv.getWidth(), mv.getHeight());
		
		ltCommand = new LeftTurnCommand(gw);
		addAsteroidCommand = new AddAsteroidCommand(gw);
		addNPSCommand = new AddNPSCommand(gw);
		addSSCommand = new AddSpaceStationCommand(gw);
		addPSCommand = new AddPSCommand(gw);
		
		Container topContainer = new Container();
		topContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		Container innerContainer = new Container();
		innerContainer.setLayout(new FlowLayout(Component.CENTER));
		Toolbar toolb = new Toolbar();
		this.setToolbar(toolb);
		toolb.setTitle(" Asteroids ");
		
		Container bottomContainer = new Container();
		bottomContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		bottomContainer.add(pv);
		
		topContainer.add(bottomContainer);
		
		/* Button toolbar */
		Toolbar buttonBar = new Toolbar();
		this.setToolbar(buttonBar);
		buttonBar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		buttonBar.add(new Label("Commands"));
		buttonBar.getAllStyles().setBgTransparency(255);
		buttonBar.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		buttonBar.getAllStyles().setFgColor(ColorUtil.WHITE);
		buttonBar.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.LTGRAY));

		/* AddAsteroid Button */
		Button bAddAsteroid = new Button("Add Asteroid");
		bAddAsteroid.getAllStyles().setBgTransparency(200);
		bAddAsteroid.getAllStyles().setBgColor(ColorUtil.BLUE);
		bAddAsteroid.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bAddAsteroid);
		bAddAsteroid.setCommand(addAsteroidCommand);
		
		/* AddNonPlayerShip Button */
		Button bAddNonPlayerShip = new Button("Add Non Player Ship");
		bAddNonPlayerShip.getAllStyles().setBgTransparency(200);
		bAddNonPlayerShip.getAllStyles().setBgColor(ColorUtil.BLUE);
		bAddNonPlayerShip.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bAddNonPlayerShip);
		bAddNonPlayerShip.setCommand(addNPSCommand);
		
		/* AddSpaceStation Button */
		Button bAddSpaceStation = new Button("Add Space Station");
		bAddSpaceStation.getAllStyles().setBgTransparency(200);
		bAddSpaceStation.getAllStyles().setBgColor(ColorUtil.BLUE);
		bAddSpaceStation.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bAddSpaceStation);
		bAddSpaceStation.setCommand(addSSCommand);
		
		/* AddPlayerShip Button */
		Button bAddPlayerShip = new Button("Add Player Ship");
		bAddPlayerShip.getAllStyles().setBgTransparency(200);
		bAddPlayerShip.getAllStyles().setBgColor(ColorUtil.BLUE);
		bAddPlayerShip.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bAddPlayerShip);
		bAddPlayerShip.setCommand(addPSCommand);
		
		
		/* Accelerate Button */
		Button bAccelerate = new Button("Accelerate");
		bAccelerate.getAllStyles().setBgTransparency(200);
		bAccelerate.getAllStyles().setBgColor(ColorUtil.BLUE);
		bAccelerate.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bAccelerate);
		
		/* Decelerate Button */
		Button bDecelerate = new Button("Decelerate");
		bDecelerate.getAllStyles().setBgTransparency(200);
		bDecelerate.getAllStyles().setBgColor(ColorUtil.BLUE);
		bDecelerate.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bDecelerate);

		//LEFT TURN
		/* Left Turn Button */
		Button bleft = new Button("TurnLeft");
		bleft.getAllStyles().setBgTransparency(200);
		bleft.getAllStyles().setBgColor(ColorUtil.BLUE);
		bleft.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bleft);
		bleft.setCommand(ltCommand);
		
		//RIGHT TURN KEYSTROKES
		
		/* Fire Missile Button */
		Button bFireMissile = new Button("Fire Missile");
		bFireMissile.getAllStyles().setBgTransparency(200);
		bFireMissile.getAllStyles().setBgColor(ColorUtil.BLUE);
		bFireMissile.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bFireMissile);
		
		/* Hyperspace Jump Button */
		Button bJump = new Button("Hyperspace Jump");
		bJump.getAllStyles().setBgTransparency(200);
		bJump.getAllStyles().setBgColor(ColorUtil.BLUE);
		bJump.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bJump);
		
		/* Decelerate Button */
		Button bReload = new Button("Reload Missiles");
		bReload.getAllStyles().setBgTransparency(200);
		bReload.getAllStyles().setBgColor(ColorUtil.BLUE);
		bReload.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bReload);
		
		/* Missile Destroys Asteroid Button */
		Button bDestroyAsteroid = new Button("Missile Destroy Asteroid");
		bDestroyAsteroid.getAllStyles().setBgTransparency(200);
		bDestroyAsteroid.getAllStyles().setBgColor(ColorUtil.BLUE);
		bDestroyAsteroid.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bDestroyAsteroid);
		
		/* Missile Strikes Non Player Ship Button */
		Button bHit= new Button("Missile Hits NonPlayerShip");
		bHit.getAllStyles().setBgTransparency(200);
		bHit.getAllStyles().setBgColor(ColorUtil.BLUE);
		bHit.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bHit);
		
		/* PlayerShip crashes into Asteroid Button */
		Button bCrash = new Button("PlayerShip Crash into Asteroid");
		bCrash.getAllStyles().setBgTransparency(200);
		bCrash.getAllStyles().setBgColor(ColorUtil.BLUE);
		bCrash.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bCrash);
		
		/* PlayerShip hits NonPlayerShip Button */
		Button bPSCollision = new Button("PlayerShip Hits NonPlayerShip");
		bPSCollision.getAllStyles().setBgTransparency(200);
		bPSCollision.getAllStyles().setBgColor(ColorUtil.BLUE);
		bPSCollision.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bPSCollision);
		
		/* Asteroids Collide Button */
		Button bACollision = new Button("Asteroids Collids");
		bACollision.getAllStyles().setBgTransparency(200);
		bACollision.getAllStyles().setBgColor(ColorUtil.BLUE);
		bACollision.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bACollision);
		
		/* Asteroid hits NonPlayerShip Button */
		Button bANPSCollision = new Button("Asteroid hits NonPlayerShip");
		bANPSCollision.getAllStyles().setBgTransparency(200);
		bANPSCollision.getAllStyles().setBgColor(ColorUtil.BLUE);
		bANPSCollision.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bANPSCollision);
		
		/* Game Clock Tick Button */
		Button bTick = new Button("Game Clock Tick");
		bTick.getAllStyles().setBgTransparency(200);
		bTick.getAllStyles().setBgColor(ColorUtil.BLUE);
		bTick.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		buttonBar.add(bTick);
		
		Button quit = new Button("Quit");
//		QuitGameCommand myQuitCommand = new QuitGameCommand();
//		quit.setCommand(myQuitCommand);
//		addKeyListener('q', myQuitCommand);
		quit.getAllStyles().setBgTransparency(255);
		quit.getAllStyles().setBgColor(ColorUtil.rgb(255, 0, 0));
		quit.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
		
//		NewGameCommand ngc = new NewGameCommand();
//		SaveCommand sc = new SaveCommand();
//		UndoCommand uc = new UndoCommand();
//		SoundCommand sound = new SoundCommand(gw);
//		AboutCommand about = new AboutCommand();
//		
//		toolb.addCommandToSideMenu(ngc);
//		toolb.addCommandToSideMenu(sc);
//		toolb.addCommandToSideMenu(uc);
//		toolb.addCommandToSideMenu(sound);
//		toolb.addCommandToSideMenu(about);
		
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		add(BorderLayout.NORTH,topContainer);
		add(BorderLayout.CENTER,mv);
		
		this.show();
	}
	
	/* *
	 * The following edits replaces A1's play() method
	 * 
	 * creating interface IGameWorld
	 * extending Observable from GameWorld and implementing IGameWorld
	 * creating GameWorldProxy extending Observable from GameWorld and implementing IGameWorld
	 * creating MapView extends container implements Observer
	 * creating PointsView extends container implements Observer
	 * 
	 * */
	
//	public void play() {
//		Label myLabel = new Label("Enter a Command:");
//		this.addComponent(myLabel);
//		final TextField myTextField = new TextField();
//		this.addComponent(myTextField);
//		this.show();
//		myTextField.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				String sCommand = myTextField.getText().toString();
//				myTextField.clear();
//				switch(sCommand.charAt(0)) {
//					case 'a':
//						gw.addAsteroid();
//						break;
//					case 'y':
//						gw.addNPS();
//						break;
//					case 'b':
//						gw.addSpaceStation();
//						break;
//					case 's':
//						gw.addPS();
//						break;
//					case 'i':
//						gw.increaseSpeed();
//						break;
//					case 'd':
//						gw.decreaseSpeed();
//						break;
//					case 'l':
//						gw.turnLeft();
//						break;
//					case 'r':
//						gw.turnRight();
//						break;
//					case '<':
//						gw.aimML();
//						break;
//					case 'f':
//						gw.firePSMissile();
//						break;
//					case 'L':
//						gw.launchNPSMissile();
//						break;
//					case 'j':
//						//gw.jump();
//						break;
//					case 'n':
//						gw.loadMissiles();
//						break;
//					case 'k':
//						gw.destroyAsteroid();
//						break;
//					case 'e':
//						gw.eliminatedNPS();
//						break;
//					case 'E':
//						gw.explodePS();
//						break;
//					case 'c':
//						gw.crash();
//						break;
//					case 'h':
//						gw.hit();
//						break;
//					case 'x':
//						gw.exterminate();
//						break;
//					case 'I':
//						gw.impact();
//						break;
//					case 't':
//						gw.ticked();
//						break;
//					case 'p':
//						gw.printDisplay();
//						break;
//					case 'm':
//						gw.map();
//						break;
//					case 'q':
//						gw.quitGW();
//						break;
//					default:
//						System.out.println("Sorry, that character doesn't do anything!");
//				} /*end switch*/
//			}/*action Performed*/
//		} /*new ActionListener*/
//		); /*addActionListener -- lambda expressions*/
//	}/*end play*/
}/*end Game*/