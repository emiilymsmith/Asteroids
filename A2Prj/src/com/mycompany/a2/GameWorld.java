package com.mycompany.a2;

import java.util.Observable;
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
	Vector<GameObject> storage = new Vector<GameObject>();
	
	/* Fixed Window Dimensions */
	private int height = 768;
	private int width = 1024;
	
	/* Private Data for functionality */
	private int ticks;
	private int score;
	
	public void init(){
		score = 0;
	}

	
	/** Create a new Asteroid Object 
	 *  Adds it to vector: 'storage'
	 * a */
	public void addAsteroid() {
		/*create Asteroid object*/
		Asteroids asteroid = new Asteroids();
		/*add asteroid to storage vector*/
		storage.add(asteroid);
		/*feedback for creation*/
		System.out.println("An ASTEROID has been created.");	
		System.out.println(asteroid);
	}
	/** Create a new NonPlayerShip Object
	 *  Adds it to vector: 'storage'
	 * y */
	public void addNPS() {
		NonPlayerShip nps = new NonPlayerShip();
		storage.add(nps);
		System.out.println("A NON-PLAYERSHIP has been created.");
		System.out.println(nps);
	}
	/** Create a new Blinking Space Station Object
	 *  Adds it to vector: 'storage'
	 * b */
	public void addSpaceStation() {
		SpaceStation bs = new SpaceStation();
		storage.add(bs);
		System.out.println("A SPACE STATION has been created.");
		System.out.println(bs);
	}
	/** Create a new Player Ship Object
	 *  Checks if a PlayerShip does not exist
	 *  Adds it to vector: 'storage'
	 * s */
	public void addPS() {
		if(!psExists()) {
			PlayerShip ps = new PlayerShip();
			storage.add(ps);
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
		if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    int currentSpeed = ((PlayerShip) ps).getSpeed();
                    if (currentSpeed > 10) {
                        System.out.println("Already at MAX PLAYERSHIP speed.");
                    } else if (currentSpeed >= 0 ) {
                        ((PlayerShip) ps).increaseSpeed();
                        System.out.println("PLAYERSHIP speed INCREASED.");
                        System.out.println(ps);
                    }
                }
            }
        }else
        	System.err.println("PLAYERSHIP speed cannot be increased.");
    }
	/** Decrease PlayerShip's Speed
	 *  checks validity, checks boundaries, calls PlayerShip method
	 * d */
	public void decreaseSpeed() {
		if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    int currentSpeed = ((PlayerShip) ps).getSpeed();
                    if (currentSpeed == 0) {
                        System.out.println("Already at MIN PLAYERSHIP speed.");
                    } else if (currentSpeed < 10) {
                        ((PlayerShip) ps).decreaseSpeed();
                        System.out.println("PLAYERSHIP speed DECREASED.");
                        System.out.println(ps);
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
		if(psExists()) {
			for (GameObject ps : storage) {
            	if (ps instanceof PlayerShip) {
                	((PlayerShip) ps).changeHeading(-15); 
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
		if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    ((PlayerShip) ps).changeHeading(15); //changeHeading from Steerable
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
		if (psExists()) {
			for(GameObject ps : storage) {
				if(ps instanceof PlayerShip) {
					SteerableMissileLauncher sml = ((PlayerShip) ps).getPSML();
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
		if(psExists()) {
			int index = getPlayerShipIndex();
            PlayerShip ps = (PlayerShip) storage.get(index);
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
                storage.add(missile);
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
		if(nonPSExists()) {
			int index = getNonPlayerShipIndex();
			NonPlayerShip nps = (NonPlayerShip) storage.get(index);
			int numMissiles = nps.getMissileCount();
			if (numMissiles < 1)
				System.err.println("NONPLAYERSHIP is out of missiles, cannot fire.");
			else {
				nps.setMissileCount(numMissiles - 1);
				MissileLauncher ml = nps.getML();
				/* creates object Missiles called missile, uses NONplayership's params*/
				Missiles missile = new Missiles(nps.getLocation(), nps.getHeading(), nps.getSpeed());
				storage.add(missile);
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
		if(psExists()) {
			for(GameObject ps: storage) {
            	if(ps instanceof PlayerShip) {
            		ps.setLocation(point);
            		SteerableMissileLauncher sml = ((PlayerShip) ps).getPSML();
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
		if(psExists()) {
			for (GameObject ps : storage) {
				if (ps instanceof PlayerShip) {
					((PlayerShip) ps).setMissileCount(10); /* resets missile count to 10 */
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
		boolean movable = false;
        for(GameObject moveObj: storage){
            if(moveObj instanceof IMovable){
                ((IMovable) moveObj).move();
                movable = true;
            }
        }
        /* If something moves, move it, meaning the PLAYERSHIP mostly */
        if(movable) {
        	if(psExists()){
                for (GameObject ps : storage) {
                    if (ps instanceof PlayerShip) {
                        SteerableMissileLauncher sml = ((PlayerShip) ps).getPSML();
                        sml.setLocation(ps.getLocation());
                    }
                }
            } System.out.println("TICKTOCK: Moved all the moveable objects.");
        } else
            System.out.println("TICKTOCK: No movable objects exist!");
	}
	
	public void updateFuel() {
		if(missileExists()){
            Missiles missile = (Missiles) storage.get(missileIndex());
            int fuelLevel = missile.getFuelLevel();
            if (fuelLevel <= 1) {
                storage.remove(missile);
                System.out.println("Removed a MISSILE that ran out of fuel.");
            } else
                missile.setFuelLevel(fuelLevel - 1);
        }
        else
            System.out.println("No MISSILES exist.");
	}
	
	private void blinkSS(){
        if (spaceStationExists()) {
        	for(GameObject ss: storage) {
                if (ss instanceof SpaceStation) {
                    ((SpaceStation) ss).toggleLight();
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
		int psi, missileCount = -1;
        if (psExists()) {
            psi = getPlayerShipIndex();
            PlayerShip ps = (PlayerShip) storage.get(psi);
            missileCount = ps.getMissileCount();
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
		System.out.println("---------------------------------------\n"
                + "-------------- Game Map ----------------\n"
                + "----------------------------------------");
		for(GameObject go: storage) {
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
        System.out.println("---------------------------------------");
        System.err.println("GAME OVER - You ran out of Lives!");
        System.out.println("\tIf you would like to quit, type 'Y'");
        storage.removeAllElements();
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
        boolean aexists = false;
        for(GameObject asteroid: storage) {
            if(asteroid instanceof Asteroids) {
                aexists = true;
            }
        }
        if(!aexists)
            System.out.println("No ASTEROIDS exist.");
        return aexists;
    }

	public boolean psExists() {
		boolean psexists = false;
        for(GameObject ps: storage) {
            if(ps instanceof PlayerShip) {
                psexists = true;
            }
        }
        if(!psexists)
            System.out.println("PLAYER SHIP does not exist.");
        return psexists;
	}
	
	private boolean nonPSExists() {
        boolean npsexists = false;
        for(GameObject nonPS: storage) {
            if(nonPS instanceof NonPlayerShip) {
                npsexists = true;
            }
        }
        if(!npsexists)
            System.out.println("No Non-Player Ships exist.");
        return npsexists;
    }
	
	private boolean missileExists() {
        boolean mexists = false;
        for(GameObject missile: storage) {
            if(missile instanceof Missiles) {
                mexists = true;
            }
        }
        if(!mexists)
            System.out.println("A MISSILE does not exist.");
        return mexists;
    }

	private boolean spaceStationExists(){
		boolean ssexists = false;
        for(GameObject ss: storage) {
            if(ss instanceof SpaceStation) {
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
		boolean quit = false;
        int numLives;
        if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    numLives = ((PlayerShip) ps).getLives();
                    if (numLives > 1) {
                    	((PlayerShip) ps).setLives(numLives - 1);
                    	return true;
                    } else {
                    	((PlayerShip) ps).setLives(numLives - 1);
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
        int psLoc = 0; //player ship location in storage
        for (GameObject ps : storage) {
            if (ps instanceof PlayerShip) {
                psLoc = storage.indexOf(ps);
            }
        }
        return psLoc;
    }
	
	private int getNonPlayerShipIndex(){
		int npsLoc = 0; //player ship location in storage
        for (GameObject nps : storage) {
            if (nps instanceof NonPlayerShip) {
                npsLoc = storage.indexOf(nps);
            }
        }
        return npsLoc;
	}
	
	public int missileIndex() {
		int missileIndex = 0;
        for (GameObject missile : storage) {
            if (missile instanceof Missiles) {
                missileIndex = storage.indexOf(missile);
            }
        }
        return missileIndex;
	}
	
	/* The following methods remove objects from the GameWorld*/
	private boolean removeAsteroid(){
        for(GameObject asteroid: storage) {
            if(asteroid instanceof Asteroids) {
                storage.remove(asteroid);
                System.out.println("Removed ASTEROID.");
                return true;
            }
        }
        System.err.println("Did not remove ASTEROID.");
        return false;
    }
	
	private boolean removeMissile() {
        for(GameObject missile: storage) {
            if(missile instanceof Missiles) {
                storage.remove(missile);
                System.out.println("Removed MISSILE.");
                return true;
            }
        }
        System.err.println("Did not remove MISSILE.");
        return false;
    }
	
	private boolean removeNPS() {
		for(GameObject nps: storage) {
            if(nps instanceof NonPlayerShip) {
                storage.remove(nps);
                System.out.println("Removed NonPlayerShip from GAMEWORLD.");
                return true;
            }
        }
        System.err.println("Did not remove NonPlayerShip.");
        return false;
	}
	
	private boolean removePS() {
		for(GameObject ps: storage) {
            if(ps instanceof PlayerShip) {
                storage.remove(ps);
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
 * rename fired missile
 * 
 * 
 * 
 * 
 * */
