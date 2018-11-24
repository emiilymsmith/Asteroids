package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
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
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Commands.AICommand;
import com.mycompany.a3.Commands.AboutCommand;
import com.mycompany.a3.Commands.AddAsteroidCommand;
import com.mycompany.a3.Commands.AddNPSCommand;
import com.mycompany.a3.Commands.AddPSCommand;
import com.mycompany.a3.Commands.AddSpaceStationCommand;
import com.mycompany.a3.Commands.ClockTickCommand;
import com.mycompany.a3.Commands.CollideXCommand;
import com.mycompany.a3.Commands.CrashCommand;
import com.mycompany.a3.Commands.DecreaseSpeedCommand;
import com.mycompany.a3.Commands.DestroyAsteroidCommand;
import com.mycompany.a3.Commands.EliminatedNPSCommand;
import com.mycompany.a3.Commands.ExplodedPSCommand;
import com.mycompany.a3.Commands.FireMissileCommand;
import com.mycompany.a3.Commands.HitNPSCommand;
import com.mycompany.a3.Commands.IncreaseSpeedCommand;
import com.mycompany.a3.Commands.JumpCommand;
import com.mycompany.a3.Commands.LaunchMissileCommand;
import com.mycompany.a3.Commands.LeftTurnCommand;
import com.mycompany.a3.Commands.LoadMissilesCommand;
import com.mycompany.a3.Commands.NewGameCommand;
import com.mycompany.a3.Commands.QuitCommand;
import com.mycompany.a3.Commands.RightTurnCommand;
import com.mycompany.a3.Commands.SaveCommand;
import com.mycompany.a3.Commands.SoundCommand;
import com.mycompany.a3.Commands.UndoCommand;

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
 * 		adding command classes
 * 
 * A3 additions:
 * 		adding timer to move objects shown in MapView
 */

public class Game extends Form implements Runnable{
	/* State Variables */
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private UITimer timer;
	
	/* Command Variables */
	private AddAsteroidCommand addAsteroidCommand;
	private AddNPSCommand addNPSCommand;
	private AddSpaceStationCommand addSSCommand;
	private AddPSCommand addPSCommand;
	private IncreaseSpeedCommand incSpeedCommand;
	private DecreaseSpeedCommand decSpeedCommand;
	private LeftTurnCommand ltCommand;
	private RightTurnCommand rtCommand;
	private FireMissileCommand fCommand;
	private LaunchMissileCommand lNPSCommand;
	private LoadMissilesCommand loadCommand;
	private JumpCommand jCommand;
	private DestroyAsteroidCommand daCommand;
	private EliminatedNPSCommand elimNPSCommand;
	private CrashCommand cCommand;
	private ExplodedPSCommand ePSCommand;
	private HitNPSCommand hitCommand;
	private CollideXCommand collideCommand;
	private AICommand impactCommand;
	private ClockTickCommand tickCommand;
	private QuitCommand qCommand;
	
	/* Game Constructor */
	public Game() {
		setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView(); /* view for later */
		pv = new PointsView(gw); /* view for map and score */
		
		Container topContainer = new Container();
		topContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		Container innerContainer = new Container();
		innerContainer.setLayout(new FlowLayout(Component.CENTER));
		Toolbar toolb = new Toolbar();
		this.setToolbar(toolb); /* quit save tick about sound(check) */
		toolb.setTitle(" Asteroids ");
		
		Container bottomInnerContainer = new Container();
		bottomInnerContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		bottomInnerContainer.add(pv);
		
		topContainer.add(innerContainer);
		topContainer.add(bottomInnerContainer);		
		
		/* Button bar - container for all the buttons*/
		Container buttonBar = new Container();
		buttonBar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		buttonBar.add(new Label("Commands"));
		buttonBar.getAllStyles().setBgTransparency(100);
		buttonBar.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		buttonBar.getAllStyles().setFgColor(ColorUtil.WHITE);
		buttonBar.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.WHITE));
		buttonBar.setScrollableY(true);

		/* AddAsteroid Button */
		addAsteroidCommand = new AddAsteroidCommand(gw);
		Button bAddAsteroid = new Button("Add Asteroid");
		bAddAsteroid.getAllStyles().setBgTransparency(100);
		bAddAsteroid.getAllStyles().setBgColor(ColorUtil.GREEN);
		bAddAsteroid.getAllStyles().setFgColor(ColorUtil.WHITE);
		bAddAsteroid.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bAddAsteroid.setCommand(addAsteroidCommand);
		buttonBar.add(bAddAsteroid);
		
		/* AddNonPlayerShip Button */
		addNPSCommand = new AddNPSCommand(gw);
		Button bAddNonPlayerShip = new Button("Add Non Player Ship");
		bAddNonPlayerShip.getAllStyles().setBgTransparency(100);
		bAddNonPlayerShip.getAllStyles().setBgColor(ColorUtil.GREEN);
		bAddNonPlayerShip.getAllStyles().setFgColor(ColorUtil.WHITE);
		bAddNonPlayerShip.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bAddNonPlayerShip.setCommand(addNPSCommand);
		buttonBar.add(bAddNonPlayerShip);
		
		/* AddSpaceStation Button */
		addSSCommand = new AddSpaceStationCommand(gw);
		Button bAddSpaceStation = new Button("Add Space Station");
		bAddSpaceStation.getAllStyles().setBgTransparency(100);
		bAddSpaceStation.getAllStyles().setBgColor(ColorUtil.GREEN);
		bAddSpaceStation.getAllStyles().setFgColor(ColorUtil.WHITE);
		bAddSpaceStation.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bAddSpaceStation.setCommand(addSSCommand);
		buttonBar.add(bAddSpaceStation);
		
		/* AddPlayerShip Button */
		addPSCommand = new AddPSCommand(gw);
		Button bAddPlayerShip = new Button("Add Player Ship");
		bAddPlayerShip.getAllStyles().setBgTransparency(100);
		bAddPlayerShip.getAllStyles().setBgColor(ColorUtil.GREEN);
		bAddPlayerShip.getAllStyles().setFgColor(ColorUtil.WHITE);
		bAddPlayerShip.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bAddPlayerShip.setCommand(addPSCommand);
		buttonBar.add(bAddPlayerShip);
		
		/* Accelerate Button */
//		Button bAccelerate = new Button("Accelerate");
//		bAccelerate.getAllStyles().setBgTransparency(100);
//		bAccelerate.getAllStyles().setBgColor(ColorUtil.GREEN);
//		bAccelerate.getAllStyles().setFgColor(ColorUtil.WHITE);
//		bAccelerate.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
//		bAccelerate.setCommand(incSpeedCommand);
//		buttonBar.add(bAccelerate);
		incSpeedCommand = new IncreaseSpeedCommand(gw);
		addKeyListener(-91, incSpeedCommand);
		
		/* Decelerate Button */
//		Button bDecelerate = new Button("Decelerate");
//		bDecelerate.getAllStyles().setBgTransparency(100);
//		bDecelerate.getAllStyles().setBgColor(ColorUtil.GREEN);
//		bDecelerate.getAllStyles().setFgColor(ColorUtil.WHITE);
//		bDecelerate.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
//		buttonBar.add(bDecelerate);
		decSpeedCommand = new DecreaseSpeedCommand(gw);
		addKeyListener(-92, decSpeedCommand);

		/* Left Turn Button */
//		Button bLeft = new Button("TurnLeft");
//		bLeft.getAllStyles().setBgTransparency(200);
//		bLeft.getAllStyles().setBgColor(ColorUtil.BLUE);
//		bLeft.getAllStyles().setFgColor(ColorUtil.WHITE);
//		bLeft.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
//		bLeft.setCommand(ltCommand);
//		buttonBar.add(bLeft);
		ltCommand = new LeftTurnCommand(gw);;
		addKeyListener(-93, ltCommand);
		
		/* Left Turn Button */
//		Button bRight = new Button("TurnLeft");
//		bRight.getAllStyles().setBgTransparency(200);
//		bRight.getAllStyles().setBgColor(ColorUtil.BLUE);
//		bRight.getAllStyles().setFgColor(ColorUtil.WHITE);
//		bRight.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
//		bRight.setCommand(rtCommand);
//		buttonBar.add(bRight);
		rtCommand = new RightTurnCommand(gw);
		addKeyListener(-94, rtCommand);
		
		/* Fire PS Missile Button */
		fCommand = new FireMissileCommand(gw);
		Button bFireMissile = new Button("Fire Missile");
		bFireMissile.getAllStyles().setBgTransparency(100);
		bFireMissile.getAllStyles().setBgColor(ColorUtil.GREEN);
		bFireMissile.getAllStyles().setFgColor(ColorUtil.WHITE);
		bFireMissile.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bFireMissile.setCommand(fCommand);
		buttonBar.add(bFireMissile);
		addKeyListener(-90, fCommand);
		
		/* Launch NPS Missile Button */
		lNPSCommand = new LaunchMissileCommand(gw);
		Button bLaunchMissile = new Button("Launch NPS Missile");
		bLaunchMissile.getAllStyles().setBgTransparency(100);
		bLaunchMissile.getAllStyles().setBgColor(ColorUtil.GREEN);
		bLaunchMissile.getAllStyles().setFgColor(ColorUtil.WHITE);
		bLaunchMissile.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bLaunchMissile.setCommand(lNPSCommand);
		buttonBar.add(bLaunchMissile);
		addKeyListener('L',lNPSCommand);
		
		/* Hyperspace Jump Button */
		jCommand = new JumpCommand(gw);
		Button bJump = new Button("Hyperspace Jump");
		bJump.getAllStyles().setBgTransparency(100);
		bJump.getAllStyles().setBgColor(ColorUtil.GREEN);
		bJump.getAllStyles().setFgColor(ColorUtil.WHITE);
		bJump.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bJump.setCommand(jCommand);
		buttonBar.add(bJump);
		addKeyListener('j', jCommand);
		
		/* Reload Missiles */
		loadCommand = new LoadMissilesCommand(gw);
		Button bloadMissiles = new Button("Reload Missiles");
		bloadMissiles.getAllStyles().setBgTransparency(100);
		bloadMissiles.getAllStyles().setBgColor(ColorUtil.GREEN);
		bloadMissiles.getAllStyles().setFgColor(ColorUtil.WHITE);
		bloadMissiles.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bloadMissiles.setCommand(loadCommand);
		buttonBar.add(bloadMissiles);
		addKeyListener('n', loadCommand);
		
		/* Missile Destroys Asteroid Button */
		daCommand = new DestroyAsteroidCommand(gw);
		Button bDestroyAsteroid = new Button("Missile Destroyed Asteroid");
		bDestroyAsteroid.getAllStyles().setBgTransparency(100);
		bDestroyAsteroid.getAllStyles().setBgColor(ColorUtil.GREEN);
		bDestroyAsteroid.getAllStyles().setFgColor(ColorUtil.WHITE);
		bDestroyAsteroid.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bDestroyAsteroid.setCommand(daCommand);
		buttonBar.add(bDestroyAsteroid);
		addKeyListener('k', daCommand);
		
		/* (Eliminated) Missile Strikes Non Player Ship Button */
		elimNPSCommand = new EliminatedNPSCommand(gw);
		Button bElim= new Button("Missile Hit NonPlayerShip");
		bElim.getAllStyles().setBgTransparency(100);
		bElim.getAllStyles().setBgColor(ColorUtil.GREEN);
		bElim.getAllStyles().setFgColor(ColorUtil.WHITE);
		bElim.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bElim.setCommand(elimNPSCommand);
		buttonBar.add(bElim);
		addKeyListener('e',elimNPSCommand);
		
		/* NPS Missile Strikes Player Ship Button */
		ePSCommand = new ExplodedPSCommand(gw);
		Button bExplode= new Button("Missile Hit PlayerShip");
		bExplode.getAllStyles().setBgTransparency(100);
		bExplode.getAllStyles().setBgColor(ColorUtil.GREEN);
		bExplode.getAllStyles().setFgColor(ColorUtil.WHITE);
		bExplode.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bExplode.setCommand(ePSCommand);
		buttonBar.add(bExplode);
		addKeyListener('E',ePSCommand);
		
		/* PlayerShip crashes into Asteroid Button */
		cCommand = new CrashCommand(gw);
		Button bCrash = new Button("PlayerShip Crash into Asteroid");
		bCrash.getAllStyles().setBgTransparency(100);
		bCrash.getAllStyles().setBgColor(ColorUtil.GREEN);
		bCrash.getAllStyles().setFgColor(ColorUtil.WHITE);
		bCrash.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bCrash.setCommand(cCommand);
		buttonBar.add(bCrash);
		addKeyListener('c', cCommand);
		
		/* PlayerShip hits NonPlayerShip Button */
		hitCommand = new HitNPSCommand(gw);
		Button bHit = new Button("PlayerShip Hits NonPlayerShip");
		bHit.getAllStyles().setBgTransparency(100);
		bHit.getAllStyles().setBgColor(ColorUtil.GREEN);
		bHit.getAllStyles().setFgColor(ColorUtil.WHITE);
		bHit.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bHit.setCommand(hitCommand);
		buttonBar.add(bHit);
		addKeyListener('h',hitCommand);
		
		/* Asteroids Collide/Exterminate Button */
		collideCommand = new CollideXCommand(gw);
//		Button bACollision = new Button("Asteroid Collision");
//		bACollision.getAllStyles().setBgTransparency(100);
//		bACollision.getAllStyles().setBgColor(ColorUtil.GREEN);
//		bACollision.getAllStyles().setFgColor(ColorUtil.WHITE);
//		bACollision.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
//		bACollision.setCommand(collideCommand);
//		buttonBar.add(bACollision);
//		addKeyListener('x', collideCommand);
		
		/* Asteroid hits NonPlayerShip Button */
		impactCommand = new AICommand(gw);
		Button bANPSCollision = new Button("Asteroid hits NonPlayerShip");
		bANPSCollision.getAllStyles().setBgTransparency(100);
		bANPSCollision.getAllStyles().setBgColor(ColorUtil.GREEN);
		bANPSCollision.getAllStyles().setFgColor(ColorUtil.WHITE);
		bANPSCollision.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bANPSCollision.setCommand(impactCommand);
		buttonBar.add(bANPSCollision);
		addKeyListener('I', impactCommand);
		
		/* Game Clock Tick Button */
//		tickCommand = new ClockTickCommand(gw);
//		Button bTick = new Button("Game Clock Tick");
//		bTick.getAllStyles().setBgTransparency(100);
//		bTick.getAllStyles().setBgColor(ColorUtil.GREEN);
//		bTick.getAllStyles().setFgColor(ColorUtil.WHITE);
//		bTick.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
//		bTick.setCommand(tickCommand);
//		buttonBar.add(bTick);
//		addKeyListener('t', tickCommand);
		
		qCommand = new QuitCommand(gw);
		Button bquit = new Button("Quit");
		bquit.getAllStyles().setBgTransparency(100);
		bquit.getAllStyles().setBgColor(ColorUtil.rgb(255, 0, 0));
		bquit.getAllStyles().setFgColor(ColorUtil.WHITE);
		bquit.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.WHITE));
		bquit.setCommand(qCommand);
		buttonBar.add(bquit);
		addKeyListener('q', qCommand);
		
		
		/* Check box for sound */
		CheckBox soundBox = new CheckBox("Sound");
	    soundBox.getAllStyles().setBgTransparency(100);
	    soundBox.getAllStyles().setBgColor(ColorUtil.GREEN);
	    soundBox.getAllStyles().setFgColor(ColorUtil.WHITE);
	    soundBox.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.WHITE));
	    
		
		/* Side Pop-up Menu Commands*/
		NewGameCommand ngc = new NewGameCommand();
		toolb.addCommandToSideMenu(ngc);
		SaveCommand sc = new SaveCommand();
		toolb.addCommandToSideMenu(sc);
		UndoCommand uc = new UndoCommand();
		toolb.addCommandToSideMenu(uc);
		SoundCommand sound = new SoundCommand(gw);
		soundBox.setCommand(sound);
		toolb.addComponentToSideMenu(soundBox);
		AboutCommand about = new AboutCommand();
		toolb.addCommandToSideMenu(about);
		
		/* Register observers */
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		/* Adding Components to form */
		add(BorderLayout.NORTH, topContainer);
		add(BorderLayout.CENTER, mv);
		add(BorderLayout.WEST, buttonBar);
		
		this.show();
		
		gw.init(mv.getWidth(), mv.getHeight()); /* This can't go before we get MapView coordinates */
		
		/*Initialize Timer*/
		timer = new UITimer(this);
		//timer.schedule(timeMillis, repeat, bound); :format
		timer.schedule(20, true, this);
		
		
	} //end Constructor
	@Override
	public void run() {
		gw.ticked();
	}
	
} /*end Game*/