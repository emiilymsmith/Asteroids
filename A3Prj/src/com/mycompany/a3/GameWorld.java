package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.ui.geom.Point;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.IMovable;
import com.mycompany.a3.GameObjects.FixedObjects.SpaceStation;
import com.mycompany.a3.GameObjects.MovableObjects.Asteroids;
import com.mycompany.a3.GameObjects.MovableObjects.MissileLauncher;
import com.mycompany.a3.GameObjects.MovableObjects.Missiles;
import com.mycompany.a3.GameObjects.MovableObjects.SteerableMissileLauncher;
import com.mycompany.a3.GameObjects.MovableObjects.Ships.NonPlayerShip;
import com.mycompany.a3.GameObjects.MovableObjects.Ships.PlayerShip;

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
 * 		code to hold and manipulate world objects, handle observer registration,
 * 		invoke observer callbacks by passing a GameWorld proxy, etc.
 * 
 * A3 additions:
 */

public class GameWorld extends Observable implements IGameWorld{
	/* Collection of Objects */
//	Vector<GameObject> storage = new Vector<GameObject>();
	
	private GameCollection go;
	
	/* Fixed Window Dimensions */
	private int height;
	private int width;
	
	/* Fixed variables */
	private int ticks; //time
	private int score, lives;
	private boolean pause = false;
	private boolean sound = true;
	
	/* Sound Variables */
	private static final BGSound bgSound = new BGSound("background.mp3");
	private Sound fireSound;
	private Sound shootAsteroidSound;
	private Sound gameOverSound;
	private Sound shipExplodeSound;
	
	/* Constructor */
	public void init(int w, int h){
		this.score = 0;
		this.width = w;
		this.height = h;
		this.lives = 3;
		this.ticks = 0;
		this.go = new GameCollection();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		bgSound.play();
		fireSound = new Sound("fireMissile.wav");
		shootAsteroidSound = new Sound("asteroidExplosion.wav");
		gameOverSound = new Sound("gameOver.wav");
		shipExplodeSound = new Sound("shipExplode.wav");
	}
	
	public void pause() {
		pause = pause;
	}
	
	/** Create a new Asteroid Object 
	 *  Adds it to vector: 'storage'
	 * a */
	public void addAsteroid() {
		/*create Asteroid object*/
		Asteroids asteroid = new Asteroids(this.width, this.height);
		/*add asteroid to storage vector*/
		go.add(asteroid);
		/*feedback for creation*/
		//System.out.println("An ASTEROID has been created.");	
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
		//System.out.println("A NON-PLAYERSHIP has been created.");
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
		//System.out.println("A SPACE STATION has been created.");
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
			//System.out.println("A PLAYER SHIP has been created.");
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
            	GameObject gameObj = (GameObject) theElements.getNext(); //this casts it to a game object
            	if (gameObj instanceof PlayerShip) {
                    int currentSpeed = ((PlayerShip) gameObj).getSpeed();
                    if (currentSpeed > 10) {
                        System.out.println("Already at MAX PLAYERSHIP speed.");
                    } else if (currentSpeed >= 0 ) {
                        ((PlayerShip) gameObj).increaseSpeed();
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
				GameObject gameObj = (GameObject) theElements.getNext();
                if (gameObj instanceof PlayerShip) {
                    int currentSpeed = ((PlayerShip) gameObj).getSpeed();
                    if (currentSpeed == 0) {
                        System.out.println("Already at MIN PLAYERSHIP speed.");
                    } else if (currentSpeed < 10) {
                        ((PlayerShip) gameObj).decreaseSpeed();
                        //System.out.println("PLAYERSHIP speed DECREASED.");
                        //System.out.println(GameObject);
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
				GameObject gameObj = (GameObject) theElements.getNext();
				if (gameObj instanceof PlayerShip) {
					((PlayerShip) gameObj).changeHeading(-15); 
                    //System.out.println("PLAYERSHIP LEFT.");
                }
            }
        } else {}
            //System.err.println("Cannot turn PLAYERSHIP LEFT.");
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
				GameObject gameObj = (GameObject) theElements.getNext();
				if (gameObj instanceof PlayerShip) {
                    ((PlayerShip) gameObj).changeHeading(15); //changeHeading from Steerable
                    //System.out.println("PLAYERSHIP RIGHT.");
                }
            }
        } else {}
            //System.err.println("Cannot turn PLAYERSHIP RIGHT.");
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
				GameObject gameObj = (GameObject) theElements.getNext();
				if(gameObj instanceof PlayerShip) {
					SteerableMissileLauncher sml = ((PlayerShip) gameObj).getPSML();
					sml.changeAngle(-20);
					//System.out.println("PLAYERSHIP has aimed MISSILE LAUNCHER.");
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
				GameObject gameObj = (GameObject) theElements.getNext();
				if(gameObj instanceof PlayerShip) {
		            /* Gets the number of missiles and checks if there's enough to fire one*/
		            int numMissiles = ((PlayerShip) gameObj).getMissileCount();
		            if (numMissiles < 1)
		            	System.err.println("PLAYERSHIP is out of MISSILES.");
		            else {
		            	/* decrements number of missiles*/
		            	((PlayerShip) gameObj).setMissileCount(numMissiles - 1);
		                /* creates object Missiles called missile, uses playership's params*/
		            	Missiles missile = ((PlayerShip) gameObj).firePlayerSMissile();
		            	if(this.sound)
		                    fireSound.play();
		                go.add(missile);
		                //System.out.println("PLAYERSHIP missile FIRED.");
		            }
	            }
			} else {}
//	            System.err.println("Cannot fire a PLAYERSHIP MISSILE, a PLAYERSHIP does not exist.");
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
		while(theElements.hasNext()) {
			if(nonPSExists()) {
				GameObject gameObj = (GameObject) theElements.getNext();
				if(gameObj instanceof NonPlayerShip) {
//					int numMissiles = ((NonPlayerShip) gameObj).getMissileCount();
//					((NonPlayerShip) gameObj).setMissileCount(numMissiles - 1);
//					MissileLauncher ml = ((NonPlayerShip) gameObj).getML();
					/* creates object Missiles called missile, uses NONplayership's params*/
//					Missiles missile = new Missiles(((NonPlayerShip) gameObj).getX(), ((NonPlayerShip) gameObj).getY(), 
//							((NonPlayerShip) gameObj).getHeading(),((NonPlayerShip) gameObj).getSpeed(), width, height, false);
					//iterate to find specific NPS
					Missiles npsmissile = ((NonPlayerShip) gameObj).fireNPSMissile();
					go.add(npsmissile);
					//System.out.println("NONPLAYERSHIP MISSILE FIRED.");
				} else
					System.err.println("Cannot fire a NONPLAYERSHIP Missile, a NONPLAYERSHIP does not exist.");
			}
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void spawnRandomNPS(){
		Random r = new Random();
		int rInt = r.nextInt(50);
        
        if(rInt == 5){
            addNPS();
        }
        if(rInt == 7 || rInt == 8){
            IIterator theElements = go.getIterator();
            while(theElements.hasNext()){
                GameObject object = (GameObject) theElements.getNext();
                if(object instanceof NonPlayerShip) {
                    launchNPSMissile();
                    break;
                }
            }
        }
    }//end randomNPS
	
	/** Jump through Hyperspace
	 *  PlayerShip jumps back to default position center screen
	 *  
	 * j */
	public void jump() {
		Point point = new Point(this.width/2, this.height/2); /* original starting location */
		IIterator theElements = go.getIterator();
		if(psExists()) {
			while(theElements.hasNext()) {
				GameObject gameObj = (GameObject) theElements.getNext();
				if(gameObj instanceof PlayerShip) {
					((PlayerShip) gameObj).setLocation(point);
					((PlayerShip) gameObj).setHeading(0);
					((PlayerShip) gameObj).setSpeed(0);
					SteerableMissileLauncher sml = ((PlayerShip) gameObj).getPSML();
            		sml.setLocation(point); //gets the location of the PlayerShip to match
            		//System.out.println("PLAYERSHIP JUMPED through hyperspace.");
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
				GameObject gameObj = (GameObject) theElements.getNext();
				if (gameObj instanceof PlayerShip) {
					((PlayerShip) gameObj).setMissileCount(10); /* resets missile count to 10 */
					//System.out.println("PLAYERSHIP missile reload.");
				}
			}
		} else
			System.err.println("Cannot reload PLAYERSHIP missiles.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** Destroy Asteriod (killed asteroid)
	 *  PlayerShip Missile hit an asteroid and destroyed it
	 *  Awarded 4 points
	 * k */
	public void destroyAsteroid() {
		if( asteroidExists() & missileExists()) {
			if(this.sound)
                shootAsteroidSound.play();
			removeAsteroid();
			removeMissile();
			score += 4;
			//System.out.println("Asteroid was destroyed by PLAYERSHIP MISSILE.");
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
			score += 6;
			//System.out.println("NonPlayerShip was destroyed by PLAYERSHIP MISSILE.");
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
			removeMissile();
			score-=5;
			if(lives>=1) {
				if(this.sound)
					shipExplodeSound.play();
				lives--;
				//System.out.println("EXPLOSION! NonPlayerShip hit PLAYERSHIP.");
			} else if ( lives == 0 ){
				System.out.println("You lost all your lives, try again!");
				System.exit(1);
			}
		} else
			System.err.println("NonPlayerShip did not hit PLAYERSHIP with a MISSILE.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** PlayerShip Crashed into an Asteroid
	 * Lose a life
	 * c */
	public void crash() {
		if( asteroidExists() & psExists()) {
            removeAsteroid();
            removePS();
            if(lives>=1) {
            	if(this.sound)
					shipExplodeSound.play();
            	lives--;
    			//System.out.println("PLAYERSHIP crashed into an ASTEROID, lost a life!");
    		} else if ( lives == 0 ){
    			System.out.println("You lost all your lives, try again!");
    			System.exit(1);
    		}
        } else {}
            //System.err.println("A CRASH did not happen.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** PlayerShip hit a NonPlayerShip
	 * PS Lose a life
	 * h */
	public void hit() {
		if(nonPSExists() & psExists()) {
			removeNPS();
			if(lives>=1) {
				if(this.sound)
					shipExplodeSound.play();
				lives--;
				//System.out.println("PLAYER SHIP hit a NonPlayerShip, lost a life!");
			} else if ( lives == 0 ){
				System.out.println("You lost all your lives, try again!");
				System.exit(1);
			} 
		} else {}
			//System.err.println("Did not hit PLAYERSHIP with NonPlayerShip.");
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
                //System.out.println("Two ASTEROIDS collided and EXTERMINATED each other.");
            } else
            	System.err.println("Only one ASTEROID was removed...");
		} else
            System.err.println("Cannot collide two ASTEROIDS together.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/** 
	 * Asteroid hits NonPlayerShip
	 *  
	 * I */
	public void impact() {
		if( nonPSExists() & asteroidExists()) {
            removeNPS();
            removeAsteroid();
            //System.out.println("ASTEROID impact with a NONPLAYERSHIP");
        }
        else
            System.err.println("Could not hit a NONPLAYERSHIP with an ASTEROID");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/* Each 'tick' does the following, split into 4 functions: 
	 * 1. updateFuel() - missiles fuel level is reduced, those empty are removed
	 * 2. blinkSS() - space station blinks
	 * 3. clearPoofs() - removes objects that have collided
	 * 4. ticked() - (main) calls the above methods and elapses game time by 1 tick
	 * 
	 * */

	public void updateFuel() {
		IIterator theElements = go.getIterator();
		ArrayList<Integer> poofBucket = new ArrayList<Integer>();
		int index = 0;
		if(missileExists()){
            while(theElements.hasNext()) {
            	GameObject gameObj = (GameObject) theElements.getNext(); //this casts it to a game object
            	if(gameObj instanceof Missiles) {
	            	Missiles missile = ((Missiles) gameObj);
	            	int fuelLevel = missile.getFuelLevel();
	            	if (fuelLevel < 1) {
	            		//add placeholder to poofBucket to be removed after while loop
	            		poofBucket.add(index);
	            	} else
	            		missile.setFuelLevel(fuelLevel - 1);
            	} //end if game object
            	index++;
            }//end while
            /* Empty Bucket */
            for(int i = 0; i < poofBucket.size(); i++) {
            	go.remove(poofBucket.get(i)-i);
            }
        } else {} /* end exists */
		
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	private void blinkSS(){
		IIterator theElements = go.getIterator();
		if (spaceStationExists()) {
        	while(theElements.hasNext()) {
        		GameObject gameObj = (GameObject) theElements.getNext(); //this casts it to a game object
                if (gameObj instanceof SpaceStation) {
                	if ((this.ticks % ((SpaceStation) gameObj).getBlinkRate()*10) == 0) {
                		((SpaceStation) gameObj).toggleLight();
                	}
                }
            }
        	//System.out.println("SPACESTATION light was triggered.");
        } else {}
        	//System.out.println("No SPACESTATIONS exist.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	/* t */
	public void ticked() {
		/* Check if something moves, if it does move it */
		IIterator theElements = go.getIterator();
		boolean movable = false;
		while(theElements.hasNext()){
			GameObject gameObj = (GameObject) theElements.getNext();
            if(gameObj instanceof IMovable){
                ((IMovable) gameObj).move(ticks);
                movable = true;
                //System.out.println("TICKTOCK: Moved all the movable objects.");
            } else
                System.out.println("TICKTOCK: No movable objects exist!");
        
    		/* Handle Collision of Objects */
    		IIterator elements1;
    		IIterator elements2;

    		elements1 = go.getIterator();
    		while(elements1.hasNext()) {
    			ICollider currentObject = (ICollider)elements1.getNext();
    			elements2 = go.getIterator();
    			while(elements2.hasNext()) {
    				ICollider otherObject = (ICollider)elements2.getNext(); 
    				if(otherObject!=currentObject) {
    					if(currentObject.collisionWith(otherObject)) {
    						currentObject.handleCollision(otherObject);
    						
    					}//end collision handle
    				}//end if same object check
    			}//end parse through ele2
    		}//end parse through ele1
		} //end "parent" while
		clearPoofs();
		updateFuel();
		blinkSS();
		spawnRandomNPS();
		
		ticks++;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}//end ticked
	
	public void clearPoofs() {
        for (int i = 0; i < go.iteratorSize(); i++) {
    		GameObject gameObj = (GameObject)go.elementAt(i);
    		if(gameObj.getPoof()) {
    			if(gameObj instanceof Asteroids) {
    				go.remove(i);
    			}
    			if(gameObj instanceof NonPlayerShip){
    				go.remove(i);
    			}
    			if(gameObj instanceof Missiles){
    				go.remove(i);
    			}
    			if (gameObj instanceof PlayerShip) {
    				go.remove(i);
    			}
    		}
    	}//end for
    }//end clear poofs
	
	/* Print display gives the following: 
	 * 1. current score 
	 * 2. number of missiles
	 * 3. elapsed time
	 * 
	 * p */
	public void printDisplay() {
		System.out.println("--------------------------------------------------------------------------------\n"
	                         + "---------------------------- Current Game States: ------------------------------\n"
	                         + "--------------- Points: "+score+" -------- Lives: "+lives+" -------- Time: "+ticks
	                         +"----------------\n"
	                         + "--------------------------------------------------------------------------------\n");
	}
	
	/* Map of current world state
	 * m */
	public void map() {
		/* prints out a list of all the objects */
		IIterator theElements = go.getIterator();
//		System.out.println("---------------------------------------\n"
//                + "-------------- Game Map ----------------\n"
//                + "----------------------------------------");
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			System.out.println(gameObj);
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
		if(this.sound)
            gameOverSound.play();
		System.exit(0);
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
		if(this.sound) {
			bgSound.play();
		} else {
			bgSound.pause();
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	@Override
	public int getMissileCount( ) {
		int missileCount = 0;
		IIterator theElements = go.getIterator();
		/* Remove all objects from GameWorld */
		while (theElements.hasNext()) {
			GameObject gameObj = (GameObject)theElements.getNext();
			if (gameObj instanceof PlayerShip) {
				missileCount = ((PlayerShip) gameObj).getMissileCount();
			} 
		}
		return missileCount;
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
	
	@Override
	public IIterator getGWIterator(){
		return go.getIterator();
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
//        if(!aexists)
//            System.out.println("No ASTEROIDS exist.");
        return aexists;
    }

	public boolean psExists() {
		IIterator theElements = go.getIterator();
		boolean psexists = false;
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			if(gameObj instanceof PlayerShip) {
				psexists = true;
            }
        }
//        if(psexists == false)
//            System.out.println("Creating PLAYER SHIP....");/*does not exist, sends to add PS*/
        return psexists;
	}
	
	private boolean nonPSExists() {
		IIterator theElements = go.getIterator();
		boolean npsexists = false;
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			if(gameObj instanceof NonPlayerShip) {
                npsexists = true;
            }
        }
//        if(!npsexists)
//            System.out.println("No NON-PLAYER SHIPS exist.");
        return npsexists;
    }
	
	private boolean missileExists() {
		IIterator theElements = go.getIterator();
		boolean mexists = false;
        while(theElements.hasNext()) {
        	GameObject gameObj = (GameObject) theElements.getNext();
        	if(gameObj instanceof Missiles) {
                mexists = true;
            }
        }
//        if(!mexists)
//            System.out.println("A MISSILE does not exist.");
        return mexists;
    }

	private boolean spaceStationExists(){
		IIterator theElements = go.getIterator();
		boolean ssexists = false;
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			if(gameObj instanceof SpaceStation) {
                ssexists = true;
            }
        }
//        if(!ssexists)
//            System.out.println("A SPACESTATION does not exist.");
        return ssexists;
	}
	
	/* The following methods remove objects from the GameWorld*/
	private boolean removeAsteroid(){
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			if(gameObj instanceof Asteroids) {
				go.remove(gameObj);
				System.out.println("Removed ASTEROID.");
                return true;
            }
        }
        //System.err.println("Did not remove ASTEROID.");
        return false;
    }
	
	private boolean removeMissile() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			if(gameObj instanceof Missiles) {
                go.remove(gameObj);
                //TODO make this work for item at index i
                //System.out.println("Removed MISSILE.");
                return true;
            }
        }
        //System.err.println("Did not remove MISSILE.");
        return false;
    }
	
	private boolean removeNPS() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
        	GameObject gameObj = (GameObject) theElements.getNext();
            if(gameObj instanceof NonPlayerShip) {
                go.remove(gameObj);
                //System.out.println("Removed NonPlayerShip from GAMEWORLD.");
                return true;
            }
        }
        //System.err.println("Did not remove NonPlayerShip.");
        return false;
	}
	
	private boolean removePS() {
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject gameObj = (GameObject) theElements.getNext();
			if(gameObj instanceof PlayerShip) {
                go.remove(gameObj);
                //System.out.println("Removed PLAYERSHIP from GAMEWORLD.");
                return true;
            }
        }
        //System.err.println("Did not remove PLAYERSHIP.");
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
