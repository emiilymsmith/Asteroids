package com.mycompany.a2;

import java.util.Observable;
//import java.util.Vector;

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
	private GameCollection go;
	
	private boolean soundOn;
	
	/* Fixed Window Dimensions */
	private int height = 768;
	private int width = 1024;
	
	/* Private Data for functionality */
	private int ticks;
	private int score;
	
	public GameWorld() {
		/* Create the Collection */
		go = new GameCollection();
		this.init(); /*Initialize the world*/
		
		/* Add some objects in the collections */
		go.add(new Asteroids());
		go.add(new NonPlayerShip());
		go.add(new SpaceStation());
		go.add(new PlayerShip());
		
	}
	
	public void init(){
		score = 0;
	}

	/* Display objects in the collection */
	public void displayCollection() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject go = (GameObject) theElements.getNext();
			System.out.println(go);
		}
	}
	
	
	/** Create a new Asteroid Object 
	 *  Adds it to vector: 'storage'
	 * a */
	public void addAsteroid() {
		/*create Asteroid object*/
		Asteroids asteroid = new Asteroids();
		/*add asteroid to storage vector*/
		go.add(asteroid);
		/*feedback for creation*/
		System.out.println("An ASTEROID has been created.");	
		System.out.println(asteroid);
	}
	/** Create a new NonPlayerShip Object
	 *  Adds it to vector: 'storage'
	 * y */
	public void addNPS() {
		NonPlayerShip nps = new NonPlayerShip();
		go.add(nps);
		System.out.println("A NON-PLAYERSHIP has been created.");
		System.out.println(nps);
	}
	/** Create a new Blinking Space Station Object
	 *  Adds it to vector: 'storage'
	 * b */
	public void addSpaceStation() {
		SpaceStation bs = new SpaceStation();
		go.add(bs);
		System.out.println("A SPACE STATION has been created.");
		System.out.println(bs);
	}
	/** Create a new Player Ship Object
	 *  Checks if a PlayerShip does not exist
	 *  Adds it to vector: 'storage'
	 * s */
	public void addPS() {
		//IIterator theElements = go.getIterator();
		if(!psExists()) {
			PlayerShip ps = new PlayerShip();
			go.add(ps);
			System.out.println("A PLAYER SHIP has been created.");
			System.out.println(ps);
		} else {
			System.err.println("A PLAYERSHIP already exists.");
		}
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
	}
	/** Turn PlayerShip Left
	 *  checks validity, for each instance (1) in storage
	 *  calls PlayerShip's changeHeading from Isteerable by -15 degrees 
	 * l (ell) */
	public void turnLeft(){
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
            	if (theElements instanceof PlayerShip) {
                	((PlayerShip) theElements).changeHeading(-15); 
                    System.out.println("PLAYERSHIP LEFT.");
                }
            }
        } else
            System.err.println("Cannot turn PLAYERSHIP LEFT.");
    }
	/** Turn PlayerShip Right
	 *  checks validity, for each instance (1) in storage
	 *  calls PlayerShip's changeHeading from Isteerable by 15 degrees 
	 * r */
	public void turnRight() {
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
                if (theElements instanceof PlayerShip) {
                    ((PlayerShip) theElements).changeHeading(15); //changeHeading from Steerable
                    System.out.println("PLAYERSHIP RIGHT.");
                }
            }
        } else
            System.err.println("Cannot turn PLAYERSHIP RIGHT.");
    	}
	/** Aim PlayerShip Missile Launcher
	 *  checks validity, for each instance (1) in storage of steerable missile launcher
	 *  changes Steerable Missile Launcher by -10 degrees 
	 * < */
	public void aimML() {
		IIterator theElements = go.getIterator();
		if (psExists()) {
			while(theElements.hasNext()) {
				if(theElements instanceof PlayerShip) {
					SteerableMissileLauncher sml = ((PlayerShip) theElements).getPSML();
					sml.changeAngle(-10);
					System.out.println("PLAYERSHIP has aimed MISSILE LAUNCHER.");
				}
			}
		} else
			System.err.println("Cannot aim PLAYERSHIP MISSILE LAUNCHER.");
	}
	/** Fire PlayerShip Missile
	 *  checks validity, for each instance (1) in storage of steerable missile launcher and PlayerShip
	 *  decrements number of missiles if successful 
	 * f */
	public void firePSMissile() {
		IIterator theElements = go.getIterator();
		if(psExists()) {
			int index = getPlayerShipIndex();
            PlayerShip ps = (PlayerShip) theElements.getNext(); // TODO WHAT DOES THIS LINE DO ?????????????????????????
            /* Gets the number of missiles and checks if there's enough to fire one*/
            int numMissiles = ps.getMissileCount();
            if (numMissiles < 1)
            	System.err.println("PLAYERSHIP is out of MISSILES.");
            else {
            	/* decrements number of missiles*/
            	ps.setMissileCount(numMissiles - 1);
            	SteerableMissileLauncher sml = ps.getPSML();
                /* creates object Missiles called missile, uses playership's params*/
                Missiles missile = new Missiles(ps.getLocation(), ps.getHeading(), ps.getSpeed());
                go.add(missile);
                System.out.println("PLAYERSHIP missile FIRED.");
            }
		} else
            System.err.println("Cannot fire a PLAYERSHIP MISSILE, a PLAYERSHIP does not exist.");
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
				Missiles missile = new Missiles(nps.getLocation(), nps.getHeading(), nps.getSpeed());
				go.add(missile);
				System.out.println("NONPLAYERSHIP MISSILE FIRED.");
            }
		} else
            System.err.println("Cannot fire a NONPLAYERSHIP Missile, a NONPLAYERSHIP does not exist.");
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
            	if(theElements instanceof PlayerShip) {
            		//setLocation(point); //ASSIGN TO PLAYER SHIP          <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            		SteerableMissileLauncher sml = ((PlayerShip) theElements).getPSML();
            		sml.setLocation(point); //gets the location of the PlayerShip to match
            		System.out.println("PLAYERSHIP JUMPED through hyperspace.");
                }
            }
		}else
            System.err.println("Could not JUMP through hyperspace.");
	}
	/** Reload new supply of PlayerShip Missiles 
	 * n */
	public void loadMissiles() {
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
				if (theElements instanceof PlayerShip) {
					((PlayerShip) theElements).setMissileCount(10); /* resets missile count to 10 */
					System.out.println("PLAYERSHIP missile reload.");
				}
			}
		} else
			System.err.println("Cannot reload PLAYERSHIP missiles.");
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
	}
	/** PlayerShip hit a NonPlayerShip
	 * Lose a life
	 * h */
	public void hit() {
		if(nonPSExists() & psExists()) {
        removeNPS();
        if(decrementPSLives())
            System.out.println("PLAYER SHIP hit a NonPlayerShip.");
    }
    else
        System.err.println("Did not hit PLAYERSHIP with NonPlayerShip.");
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
            if(theElements instanceof IMovable){
                ((IMovable) theElements).move();
                movable = true;
            }
        }
        /* If something moves, move it, meaning the PLAYERSHIP mostly */
        if(movable) {
        	if(psExists()){
        		while(theElements.hasNext()) {
                    if (theElements instanceof PlayerShip) {
                        SteerableMissileLauncher sml = ((PlayerShip) theElements).getPSML();
                        //sml.setLocation(ps.getLocation()); //wont work to find the player ships???????????????
                    }
                }
            } System.out.println("TICKTOCK: Moved all the moveable objects.");
        } else
            System.out.println("TICKTOCK: No movable objects exist!");
	}
	
	public void updateFuel() {
		IIterator theElements = go.getIterator();
		if(missileExists()){
            while(theElements.hasNext()) {
            	GameObject GameObject = (GameObject) theElements.getNext(); //this casts it to a game object
            	if(GameObject instanceof Missiles) {
	            	Missiles missile = (Missiles) theElements.getNext();
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
	}
	/* t */
	public void ticked() {
		moveAllObjects();
		updateFuel();
		blinkSS();
		ticks++;
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
		int psi, missileCount = -1;
        if (psExists()) {
            psi = getPlayerShipIndex();
//            PlayerShip ps = (PlayerShip) theElements.get(psi); //THIS DONT WORK!!!!!!!!!!!!!!!!!
//            missileCount = ps.getMissileCount();
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
			System.out.println(go.toString());
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
        
        //go.removeAllElements(); 									SHIT DONT WORKKKK
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
            if(theElements instanceof Asteroids) {
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
		GameObject GameObject = (GameObject) theElements.getNext();
		while(GameObject.hasNext()) {
            if(GameObject instanceof PlayerShip) {
                psexists = true;
            }
        }
        if(!psexists)
            System.out.println("PLAYER SHIP does not exist.");
        return psexists;
	}
	
	private boolean nonPSExists() {
		IIterator theElements = go.getIterator();
		boolean npsexists = false;
		while(theElements.hasNext()) {
            if(theElements instanceof NonPlayerShip) {
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
            if(theElements instanceof Missiles) {
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
            if(theElements instanceof SpaceStation) {
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
                if (theElements instanceof PlayerShip) {
                    numLives = ((PlayerShip) theElements).getLives();
                    if (numLives > 1) {
                    	((PlayerShip) theElements).setLives(numLives - 1);
                    	return true;
                    } else {
                    	((PlayerShip) theElements).setLives(numLives - 1);
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
            if(theElements instanceof Asteroids) {
//                theElements.remove(Asteroids);
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
            if(theElements instanceof Missiles) {
                //go.remove(theElements.missiles); <<<<<<<<<<<<<<<<<<<<<<<
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
        	GameObject GameObject = (GameObject) theElements.getNext();
            if(GameObject instanceof NonPlayerShip) {
                go.remove(GameObject);
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
            if(theElements instanceof PlayerShip) {
                //theElements.remove(ps);
                System.out.println("Removed PLAYERSHIP from GAMEWORLD.");
                return true;
            }
        }
        System.err.println("Did not remove PLAYERSHIP.");
        return false;
	}

	@Override
	public int getPlayerScore() {
		// TODO Auto-generated method stub
		return 0;
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
