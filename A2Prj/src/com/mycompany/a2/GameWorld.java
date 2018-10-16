package com.mycompany.a2;

import java.util.Observable;
//import java.util.Vector;
import java.util.Vector;

import com.codename1.io.gzip.Adler32;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a2.GameObjects.GameObject;
import com.mycompany.a2.GameObjects.IMovable;
import com.mycompany.a2.GameObjects.FixedObjects.SpaceStation;
import com.mycompany.a2.GameObjects.MovableObjects.Asteroids;
import com.mycompany.a2.GameObjects.MovableObjects.MissileLauncher;
import com.mycompany.a2.GameObjects.MovableObjects.Missiles;
import com.mycompany.a2.GameObjects.MovableObjects.SteerableMissileLauncher;
import com.mycompany.a2.GameObjects.MovableObjects.Ships.NonPlayerShip;
import com.mycompany.a2.GameObjects.MovableObjects.Ships.PlayerShip;

/**
 * @author Emily Smith
 * @version 2.0
 * 
 * The GameWorld contains a Vector which aggregates objects of abstract type GameObject.
 * Holds the methods that are called to manipulate and display data when users input commands.
 * 
 * MVC role = Model
 * 
 * A2 additions:
 * 		extends observable implements IGameWorld
 * code to hold and manipulate world objects, handle observer registration,
 * invoke observer callbacks by passing a GameWorld proxy, etc.
 * 
 */

public class GameWorld extends Observable implements IGameWorld{
	/* Collection of Objects */
//	Vector<GameObject> storage = new Vector<GameObject>();
	
	/* All State Variables are stored here */
	private Vector gameObjects = new Vector();
	private GameCollection go;
	
	/* Fixed Window Dimensions */
	private int height;
	private int width;
	
	/* Fixed variables */
	private int ticks; //time
	private int score, lives;
	private boolean sound;
	
	/* Constructor */
	public void init(int w, int h){
		this.score = 0;
		this.width = w;
		this.height = h;
		this.lives = 3;
		this.ticks = 0;
		this.go = new GameCollection();
		
	}

//	/* Display objects in the collection */
//	public void displayCollection() {
//		IIterator theElements = go.getIterator();
//		while(theElements.hasNext()) {
//			GameObject go = (GameObject) theElements.getNext();
//			System.out.println(go);
//		}
//	}
	
	/** Create a new Asteroid Object 
	 *  Adds it to vector: 'storage'
	 * a */
	public void addAsteroid() {
		/*create Asteroid object*/
		Asteroids asteroid = new Asteroids(this.width, this.height);
		/*add asteroid to storage vector*/
		go.add(asteroid);
		/*feedback for creation*/
		System.out.println("An ASTEROID has been created.");	
		System.out.println(asteroid);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Create a new NonPlayerShip Object
	 *  Adds it to vector: 'storage'
	 * y */
	public void addNPS() {
		NonPlayerShip nps = new NonPlayerShip(this.width, this.height);
		go.add(nps);
		System.out.println("A NON-PLAYERSHIP has been created.");
		System.out.println(nps);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Create a new Blinking Space Station Object
	 *  Adds it to vector: 'storage'
	 * b */
	public void addSpaceStation() {
		SpaceStation bs = new SpaceStation(this.width, this.height);
		go.add(bs);
		System.out.println("A SPACE STATION has been created.");
		System.out.println(bs);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Create a new Player Ship Object
	 *  Checks if a PlayerShip does not exist
	 *  Adds it to vector: 'storage'
	 * s */
	public void addPS() {
		if(!psExists()) { //returning false
			PlayerShip ps = new PlayerShip(this.width, this.height);
			go.add(ps);
			System.out.println("A PLAYER SHIP has been created.");
			System.out.println(ps);
		} else {
 			System.err.println("A PLAYERSHIP already exists.");
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Increase PlayerShip's Speed
	 *  checks validity, checks boundaries, calls PlayerShip method
	 * i */
	public void increaseSpeed() {
		IIterator theElements = go.getIterator();
		if(psExists()) {
            while(theElements.hasNext()) {
            	//changed a for loop to a while to look through iterator
            	GameObject GameObject = (GameObject) theElements.getNext(); //this casts it to a game object
            	if (GameObject instanceof PlayerShip) {
                    int currentSpeed = ((PlayerShip) GameObject).getSpeed();
                    if (currentSpeed > 10) {
                        System.out.println("Already at MAX PLAYERSHIP speed.");
                    } else if (currentSpeed >= 0 ) {
                        ((PlayerShip) GameObject).increaseSpeed();
                        System.out.println("PLAYERSHIP speed INCREASED.");
                        System.out.println(GameObject);
                    }
                }
            }//end while
        }else
        	System.err.println("PLAYERSHIP speed cannot be increased.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Decrease PlayerShip's Speed
	 *  checks validity, checks boundaries, calls PlayerShip method
	 * d */
	public void decreaseSpeed() {
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
				GameObject GameObject = (GameObject) theElements.getNext();
                if (GameObject instanceof PlayerShip) {
                    int currentSpeed = ((PlayerShip) GameObject).getSpeed();
                    if (currentSpeed == 0) {
                        System.out.println("Already at MIN PLAYERSHIP speed.");
                    } else if (currentSpeed < 10) {
                        ((PlayerShip) GameObject).decreaseSpeed();
                        System.out.println("PLAYERSHIP speed DECREASED.");
                        System.out.println(GameObject);
                    }
                }
            }
        }
        else
            System.err.println("PLAYERSHIP speed cannot be decreased.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Turn PlayerShip Left
	 *  checks validity, for each instance (1) in storage
	 *  calls PlayerShip's changeHeading from Isteerable by -15 degrees 
	 * l (ell) */
	public void turnLeft(){
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
				GameObject GameObject = (GameObject) theElements.getNext();
				if (theElements instanceof PlayerShip) {
					((PlayerShip) GameObject).changeHeading(-15); 
                    System.out.println("PLAYERSHIP LEFT.");
                }
            }
        } else
            System.err.println("Cannot turn PLAYERSHIP LEFT.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
    }
	/** Turn PlayerShip Right
	 *  checks validity, for each instance (1) in storage
	 *  calls PlayerShip's changeHeading from Isteerable by 15 degrees 
	 * r */
	public void turnRight() {
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
				GameObject GameObject = (GameObject) theElements.getNext();
				if (theElements instanceof PlayerShip) {
                    ((PlayerShip) GameObject).changeHeading(15); //changeHeading from Steerable
                    System.out.println("PLAYERSHIP RIGHT.");
                }
            }
        } else
            System.err.println("Cannot turn PLAYERSHIP RIGHT.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
    }
	/** Aim PlayerShip Missile Launcher
	 *  checks validity, for each instance (1) in storage of steerable missile launcher
	 *  changes Steerable Missile Launcher by -10 degrees 
	 * < */
	public void aimML() {
		IIterator theElements = go.getIterator();
		if (psExists()) {
			while(theElements.hasNext()) {
				GameObject GameObject = (GameObject) theElements.getNext();
				if(theElements instanceof PlayerShip) {
					SteerableMissileLauncher sml = ((PlayerShip) GameObject).getPSML();
					sml.changeAngle(-10);
					System.out.println("PLAYERSHIP has aimed MISSILE LAUNCHER.");
				}
			}
		} else
			System.err.println("Cannot aim PLAYERSHIP MISSILE LAUNCHER.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Fire PlayerShip Missile
	 *  checks validity, for each instance (1) in storage of steerable missile launcher and PlayerShip
	 *  decrements number of missiles if successful 
	 * f */
	public void firePSMissile() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			if(psExists()) {
				//int index = getPlayerShipIndex();
				GameObject GameObject = (GameObject) theElements.getNext();
				if(GameObject instanceof PlayerShip) {
		            /* Gets the number of missiles and checks if there's enough to fire one*/
		            int numMissiles = ((PlayerShip) GameObject).getMissileCount();
		            if (numMissiles < 1)
		            	System.err.println("PLAYERSHIP is out of MISSILES.");
		            else {
		            	/* decrements number of missiles*/
		            	((PlayerShip) GameObject).setMissileCount(numMissiles - 1);
		                /* creates object Missiles called missile, uses playership's params*/
		                Missiles missile = new Missiles(((PlayerShip) GameObject).getLocation(), ((PlayerShip) GameObject).getHeading(), 
		                		((PlayerShip) GameObject).getSpeed(), width, height);
		                go.add(missile);
		                System.out.println("PLAYERSHIP missile FIRED.");
		            }
	            }
			} else
	            System.err.println("Cannot fire a PLAYERSHIP MISSILE, a PLAYERSHIP does not exist.");
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Launch NonPlayerShip Missile
	 *  checks validity, for each instance in storage of NonPlayerShip
	 *  decrements number of missiles if successful 
	 * L */
	public void launchNPSMissile() {
		IIterator theElements = go.getIterator();
		if(nonPSExists()) {
			int index = getNonPlayerShipIndex();
			NonPlayerShip nps = (NonPlayerShip) theElements.getNext(); //TODO WHAT DOES THIS LINE DO ?????????????????????????
			int numMissiles = nps.getMissileCount();
			if (numMissiles < 1)
				System.err.println("NONPLAYERSHIP is out of missiles, cannot fire.");
			else {
				nps.setMissileCount(numMissiles - 1);
				MissileLauncher ml = nps.getML();
				/* creates object Missiles called missile, uses NONplayership's params*/
				Missiles missile = new Missiles(nps.getLocation(), nps.getHeading(), nps.getSpeed(), width, height);
				go.add(missile);
				System.out.println("NONPLAYERSHIP MISSILE FIRED.");
            }
		} else
            System.err.println("Cannot fire a NONPLAYERSHIP Missile, a NONPLAYERSHIP does not exist.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Jump through Hyperspace
	 *  PlayerShip jumps back to default position center screen
	 *  
	 * j */
	public void jump() {
		Point2D point = new Point2D(512,384); /* original starting location */
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
				GameObject GameObject = (GameObject) theElements.getNext();
				if(theElements instanceof PlayerShip) {
					((PlayerShip) GameObject).setLocation(point);
            		SteerableMissileLauncher sml = ((PlayerShip) GameObject).getPSML();
            		sml.setLocation(point); //gets the location of the PlayerShip to match
            		System.out.println("PLAYERSHIP JUMPED through hyperspace.");
                }
            }
		}else
            System.err.println("Could not JUMP through hyperspace.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Reload new supply of PlayerShip Missiles 
	 * n */
	public void loadMissiles() {
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
				GameObject GameObject = (GameObject) theElements.getNext();
				if (theElements instanceof PlayerShip) {
					((PlayerShip) GameObject).setMissileCount(10); /* resets missile count to 10 */
					System.out.println("PLAYERSHIP missile reload.");
				}
			}
		} else
			System.err.println("Cannot reload PLAYERSHIP missiles.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Destroy Asteriod 
	 *  PlayerShip Missile hit an asteroid and destroyed it
	 *  Awarded 4 points
	 * k */
	public void destroyAsteroid() {
		if( asteroidExists() & missileExists()) {
			removeAsteroid();
			removeMissile();
			score += 4;
			System.out.println("Asteroid was destroyed by PLAYERSHIP MISSILE.");
		} else
			System.err.println("Did not hit ASTEROID with a PLAYERSHIP MISSILE.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Eliminate NonPlayerShip
	 * PlayerShip Missile hit a NonPlayerShip and destroyed it 
	 * Awarded 8 points
	 * e */
	public void eliminatedNPS() {
		if( psExists() & nonPSExists()) {
			removeNPS();
			score += 8;
			System.out.println("NonPlayerShip was destroyed by PLAYERSHIP MISSILE.");
		} else
			System.err.println("Did not hit NonPlayerShip with PLAYERSHIP MISSILE.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** NonPlayerShip missile hit PlayerShip
	 *  Remove PlayerShip 
	 * E */
	public void explodePS() {
		if( psExists() & nonPSExists()) {
			removePS();
			score += 8;
			System.out.println("EXPLOSION! NonPlayerShip hit PLAYERSHIP.");
		} else
			System.err.println("NonPlayerShip did not hit PLAYERSHIP with a MISSILE.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** PlayerShip Crashed into an Asteriod
	 * Lose a life
	 * c */
	public void crash() {
		if( asteroidExists() & psExists()) {
            removeAsteroid();
            if(decrementPSLives())
                System.out.println("PLAYERSHIP hit an ASTEROID.");
        }
        else
            System.err.println("An ASTEROID did not hit your PLAYERSHIP.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** PlayerShip hit a NonPlayerShip
	 * Lose a life
	 * h */
	public void hit() {
		if(nonPSExists() & psExists()) {
        removeNPS();
        if(decrementPSLives())
            System.out.println("PLAYER SHIP hit a NonPlayerShip.");
		} else
			System.err.println("Did not hit PLAYERSHIP with NonPlayerShip.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Asteroid Collision both get exterminated
	 * remove both objects
	 *  
	 * x */
	public void exterminate() {
		if(asteroidExists()){
			removeAsteroid();
            if(asteroidExists()){
                removeAsteroid();
                System.out.println("Two ASTEROIDS collided and EXTERMINATED each other.");
            } else
            	System.err.println("Only one ASTEROID was removed...");
		} else
            System.err.println("Cannot collide two ASTEROIDS together.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** 
	 * 
	 *  
	 * I */
	public void impact() {
		if( nonPSExists() & asteroidExists()) {
            removeNPS();
            removeAsteroid();
            System.out.println("ASTEROID impact with a NONPLAYERSHIP");
        }
        else
            System.err.println("Could not hit a NONPLAYERSHIP with an ASTEROID");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/* Each 'tick' does the following, split into 4 functions: 
	 * 1. moveAllObjects() - all movable objects update position based on speed and heading
	 * 2. updateFuel() - missiles fuel level is reduced, those empty are removed
	 * 3. blinkSS() - space station blinks
	 * 4. ticked() - (main) calls the above methods and elapses game time by 1 tick
	 * 
	 * */
	public void moveAllObjects() {
		/* Check if something moves */
		IIterator theElements = go.getIterator();
		boolean movable = false;
		while(theElements.hasNext()){
			GameObject GameObject = (GameObject) theElements.getNext();
            if(GameObject instanceof IMovable){
                ((IMovable) GameObject).move();
                movable = true;
                System.out.println("TICKTOCK: Moved all the moveable objects.");
            } else
                System.out.println("TICKTOCK: No movable objects exist!");
        }
        this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void updateFuel() {
		IIterator theElements = go.getIterator();
		if(missileExists()){
            while(theElements.hasNext()) {
            	GameObject GameObject = (GameObject) theElements.getNext(); //this casts it to a game object
            	if(GameObject instanceof Missiles) {
	            	Missiles missile = ((Missiles) GameObject);
	            	int fuelLevel = missile.getFuelLevel();
	            	if (fuelLevel <= 1) {
	            		go.remove(missile);
	            		System.out.println("Removed a MISSILE that ran out of fuel.");
	            	} else
	            		missile.setFuelLevel(fuelLevel - 1);
            	} //end if game object
            }//end while
        }//end exists
        else
            System.out.println("No MISSILES exist.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	private void blinkSS(){
		IIterator theElements = go.getIterator();
		if (spaceStationExists()) {
        	while(theElements.hasNext()) {
        		GameObject GameObject = (GameObject) theElements.getNext(); //this casts it to a game object
                if (GameObject instanceof SpaceStation) {
                    ((SpaceStation) GameObject).toggleLight();
                }
            }
        	System.out.println("SPACESTATION light was triggered.");
        } else
        	System.out.println("No SPACESTATIONS exist.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/* t */
	public void ticked() {
		moveAllObjects();
		updateFuel();
		blinkSS();
		ticks++;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		System.out.println("-------------------TICKTOCK goes the CLOCK---------------------");
	}
	
	/* Print display gives the following: 
	 * 1. current score 
	 * 2. number of missiles
	 * 3. elapsed time
	 * 
	 * p */
	public void printDisplay() {
		IIterator theElements = go.getIterator();
		int missileCount = -1;
        while(theElements.hasNext()) {
			if (psExists()) {
				GameObject GameObject = (GameObject) theElements.getNext();
				if (GameObject instanceof PlayerShip) {
                    missileCount = ((PlayerShip) GameObject).getMissileCount();
                }
	        }
        }
	        System.out.println("--------------------------------------------------------------------------------\n"
	                         + "---------------------------- Current Game States: ------------------------------\n"
	                         + "--------------- Points: "+score+" -------- Missiles: "+missileCount+" -------- Time: "+ticks
	                         +"----------------\n"
	                         + "--------------------------------------------------------------------------------\n");
	}
	
	/* Map of current world state
	 * m */
	public void map() {
		/* prints out a list of all the objects */
		IIterator theElements = go.getIterator();
		System.out.println("---------------------------------------\n"
                + "-------------- Game Map ----------------\n"
                + "----------------------------------------");
		while(theElements.hasNext()) {
			GameObject gameObject = (GameObject) theElements.getNext();
			System.out.println(gameObject);
		}
		System.out.println("---------------------------------------\n");
	}
	
	/** All subclasses write over this toString()
	 * */
	public String toString() {return null;}
	
	/* Quit to terminate
	 * confirm with user
	 * q */
	public void quitGW() {
		System.exit(0);
	}

	private void gameOver() {
		IIterator theElements = go.getIterator();
		System.out.println("---------------------------------------");
        System.err.println("GAME OVER - You ran out of Lives!");
        System.out.println("\tIf you would like to quit, type 'Y'");
        
//        go.removeAllElements(); 									SHIT DONT WORKKKK
    }
	
	@Override
	public int getPlayerScore() {
		return this.score;
	}
	
	@Override
	public int getLives() {
		return this.lives;
	}
	
	@Override
	public boolean getSound( ) {
		return this.sound;
	}
	
	public void sound() {
		this.sound = !this.getSound();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	@Override
	public int getMissileCount( ) {
		return 0;
	}
	
	@Override
	public int getTime() {
		return this.ticks;
	}
	
	@Override
	public void setWidth(int w) {
		this.width = w;
	}
	
	@Override
	public void setHeight(int h) {
		this.height = h;
	}
	
	/*
	 * 
	 * Below are helper functions to aid in streamlining the above required methods.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	/* Returns true if there is a PlayerShip in GameWorld Vector: storage */
	
	private boolean asteroidExists() {
		IIterator theElements = go.getIterator();
		boolean aexists = false;
		while(theElements.hasNext()) {
			GameObject GameObj = (GameObject) theElements.getNext();
			if(GameObj instanceof Asteroids) {
                aexists = true;
            }
        }
        if(!aexists)
            System.out.println("No ASTEROIDS exist.");
        return aexists;
    }

	public boolean psExists() {
		IIterator theElements = go.getIterator();
		boolean psexists = false;
		while(theElements.hasNext()) {
			GameObject GameObj = (GameObject) theElements.getNext();
			if(GameObj instanceof PlayerShip) {
                //System.out.println(psexists);
				psexists = true;
            }
        }
        if(psexists == false)
            System.out.println("Creating PLAYER SHIP....");/*does not exist, sends to add PS*/
        return psexists;
	}
	
	private boolean nonPSExists() {
		IIterator theElements = go.getIterator();
		boolean npsexists = false;
		while(theElements.hasNext()) {
			GameObject GameObj = (GameObject) theElements.getNext();
			if(GameObj instanceof NonPlayerShip) {
                npsexists = true;
            }
        }
        if(!npsexists)
            System.out.println("No NON-PLAYER SHIPS exist.");
        return npsexists;
    }
	
	private boolean missileExists() {
		IIterator theElements = go.getIterator();
		boolean mexists = false;
        while(theElements.hasNext()) {
        	GameObject GameObj = (GameObject) theElements.getNext();
        	if(GameObj instanceof Missiles) {
                mexists = true;
            }
        }
        if(!mexists)
            System.out.println("A MISSILE does not exist.");
        return mexists;
    }

	private boolean spaceStationExists(){
		IIterator theElements = go.getIterator();
		boolean ssexists = false;
		while(theElements.hasNext()) {
			GameObject GameObj = (GameObject) theElements.getNext();
			if(GameObj instanceof SpaceStation) {
                ssexists = true;
            }
        }
        if(!ssexists)
            System.out.println("A SPACESTATION does not exist.");
        return ssexists;
	}
	
	 /*
	  * Gets called @ crash() with Asteroid
	  * decrements lives
	  * */
	private boolean decrementPSLives() {
		IIterator theElements = go.getIterator();
		boolean quit = false;
        int numLives;
        if(psExists()) {
        	while(theElements.hasNext()) {
        		GameObject GameObj = (GameObject) theElements.getNext();
        		if (GameObj instanceof PlayerShip) {
                    numLives = ((PlayerShip) theElements).getLives();
                    if (numLives > 1) {
                    	((PlayerShip) GameObj).setLives(numLives - 1);
                    	return true;
                    } else {
                    	((PlayerShip) GameObj).setLives(numLives - 1);
                    	quit = true;
                    }
                }
            }
        }
        //Game exits if the ship is out of lives
        if(quit)
            gameOver(); /* Called when lives are 0 */
        return false;
	}
	 /*
	  * The following 3 methods are getters for object indexes
	  * */
	private int getPlayerShipIndex(){
		IIterator theElements = go.getIterator();
		int psLoc = 0; //player ship location in storage
		while(theElements.hasNext()) {
			GameObject GameObject = (GameObject) theElements.getNext(); //this casts it to a game object
			if (GameObject instanceof PlayerShip) {
//                psLoc = go.indexOf(GameObject); //write indexOf Method in GameCollection?
            }
        }
        return psLoc;
    }
	
	private int getNonPlayerShipIndex(){
		IIterator theElements = go.getIterator();
		int npsLoc = 0; //player ship location in storage
		while(theElements.hasNext()) {
            if (theElements instanceof NonPlayerShip) {
//              npsLoc = theElements.indexOf(theElements); //THIS SHIT DONT WORK EITHER DAMN
                return 13;
            }
        }
        return npsLoc;
	}
	
	public int missileIndex() {
		IIterator theElements = go.getIterator();
		int missileIndex = 0;
		while(theElements.hasNext()) {
            if (theElements instanceof Missiles) {
                //missileIndex = storage.indexOf(missile);  ///// IDDDDKKKK
            }
        }
        return missileIndex;
	}
	
	/* The following methods remove objects from the GameWorld*/
	private boolean removeAsteroid(){
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject GameObj = (GameObject) theElements.getNext();
			if(GameObj instanceof Asteroids) {
				go.remove(GameObj);
				System.out.println("Removed ASTEROID.");
                return true;
            }
        }
        System.err.println("Did not remove ASTEROID.");
        return false;
    }
	
	private boolean removeMissile() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject GameObj = (GameObject) theElements.getNext();
			if(theElements instanceof Missiles) {
                go.remove(GameObj);
                System.out.println("Removed MISSILE.");
                return true;
            }
        }
        System.err.println("Did not remove MISSILE.");
        return false;
    }
	
	private boolean removeNPS() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
        	GameObject GameObj = (GameObject) theElements.getNext();
            if(GameObj instanceof NonPlayerShip) {
                go.remove(GameObj);
                System.out.println("Removed NonPlayerShip from GAMEWORLD.");
                return true;
            }
        }
        System.err.println("Did not remove NonPlayerShip.");
        return false;
	}
	
	private boolean removePS() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject GameObj = (GameObject) theElements.getNext();
			if(theElements instanceof PlayerShip) {
                go.remove(GameObj);
                System.out.println("Removed PLAYERSHIP from GAMEWORLD.");
                return true;
            }
        }
        System.err.println("Did not remove PLAYERSHIP.");
        return false;
	}
	
}/* End Game World */

/*
 * TODO
 * 
 * 
 * 
 * 
 * 
 * */
