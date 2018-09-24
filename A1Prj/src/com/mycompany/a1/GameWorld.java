package com.mycompany.a1;

import java.util.Vector;

import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObjects.GameObject;
import com.mycompany.a1.GameObjects.FixedObjects.SpaceStation;
import com.mycompany.a1.GameObjects.MovableObjects.Asteroids;
import com.mycompany.a1.GameObjects.MovableObjects.MissileLauncher;
import com.mycompany.a1.GameObjects.MovableObjects.Missiles;
import com.mycompany.a1.GameObjects.MovableObjects.SteerableMissileLauncher;
import com.mycompany.a1.GameObjects.MovableObjects.Ships.NonPlayerShip;
import com.mycompany.a1.GameObjects.MovableObjects.Ships.PlayerShip;

public class GameWorld{
	Vector<GameObject> storage = new Vector<GameObject>();
	private int height;
	private int width;
	
	private int ticks;
	private int score;
	
	private final int ASTEROID_SCORE = 10;
	private final int NPS_SCORE = 30;
	
	public void init(){
		ticks = 0;
		score = 0;
	}
	
	public void addAsteroid() {
		//create Asteroid object
		Asteroids asteroid = new Asteroids();
		//add asteroid to storage vector
		storage.add(asteroid);
		//feedback for creation
		System.out.println("An ASTEROID has been created.");	
		System.out.println(asteroid);
	}
	
	public void addNPS() {
		/*create NonPlayerShip object*/
		NonPlayerShip nps = new NonPlayerShip();
		//add NonPlayerShip to storage vector
		storage.add(nps);
		//feedback for creation
		System.out.println("A new NON-PLAYERSHIP has been created.");
		System.out.println(nps);
	}
	
	public void addSpaceStation() {
		SpaceStation bs = new SpaceStation(); //blinking space station
		storage.add(bs);
		System.out.println("A new SPACE STATION has been created.");
		System.out.println(bs);
	}
	
	public void addPS() {
		if(!psExists()) {
			PlayerShip ps = new PlayerShip();
			storage.add(ps);
			//SteerableMissileLauncher sml = ps.getSteerableMissileLauncher();
			//storage.add(sml) //steerableMissileLauncher;
			System.out.println("A new PLAYER SHIP has been created.");
			System.out.println(ps);
		} else {
			System.err.println("A PLAYERSHIP already exists");
		}
	}
	
	/* Increases PLAYERSHIP's Speed*/
	public void increaseSpeed() {
		if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    int currSpeed = ((PlayerShip) ps).getSpeed();
                    if (currSpeed > 10) {
                        System.out.println("Already at MAX PLAYERSHIP speed.");
                    } else {
                        ((PlayerShip) ps).increaseSpeed();
                        System.out.println("\nPLAYERSHIP speed has INCREASED");
                        System.out.println(ps);
                    }
                }
            }
        }
        else {System.err.println("PLAYERSHIP speed has not increased.");}
    	}
	
	/* Decreases PLAYERSHIP's Speed*/
	public void decreaseSpeed() {
		if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    int currentSpeed = ((PlayerShip) ps).getSpeed();
                    if (currentSpeed < 0) {
                        System.out.println("Already at MIN PLAYERSHIP speed.");
                    } else {
                        ((PlayerShip) ps).decreaseSpeed();
                        System.out.println("\nPLAYERSHIP speed has DECREASED");
                        System.out.println(ps);
                    }
                }
            }
        }
        else
            System.err.println("Cannot decrease PLAYERSHIP speed");
	}
	public void turnLeft(){
		if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    ((PlayerShip) ps).changeHeading(-15); //changeHeading from Steerable
                    System.out.println("PLAYERSHIP LEFT");
                }
            }
        }
        else
            System.err.println("Cannot turn PLAYERSHIP LEFT");
    	}
	
	public void turnRight() {
		if(psExists()) {
            for (GameObject ps : storage) {
                if (ps instanceof PlayerShip) {
                    ((PlayerShip) ps).changeHeading(15); //changeHeading from Steerable
                    System.out.println("PLAYERSHIP RIGHT");
                }
            }
        }
        else
            System.err.println("Cannot turn PLAYERSHIP RIGHT");
    	}
	
	/* Aim Missile Launcher*/
	public void aimML() {
		if (psExists()) {
			for(GameObject ps : storage) {
				if(ps instanceof PlayerShip) {
					SteerableMissileLauncher sml = ((PlayerShip) ps).getPSML();
					sml.changeAngle(-10);
					System.out.println("PLAYERSHIP MISSILE LAUNCHER rotated");
				}
			}
		} else
			System.err.println("Cannot aim PLAYERSHIP Missile Launcher");
	}
	
	public void firePSMissile() {
		if(psExists()) {
            int index = getPlayerShipIndex();
            PlayerShip ps = (PlayerShip) storage.get(index);
            /* Gets the number of missiles and checks if there's enough to fire one*/
            int numMissiles = ps.getMissileCount();
            if (numMissiles < 1)
                System.err.println("Player Ship is out of missiles, could not fire");
            else {
            	/* decrements number of missiles*/
            	ps.setMissileCount(numMissiles - 1);
                SteerableMissileLauncher sml = ps.getPSML();
                /* creates object Missiles called missile, uses playership's params*/
                Missiles missile = new Missiles(ps.getLocation(), ps.getHeading(), ps.getSpeed());
                storage.add(missile);
                System.out.println("A PLAYER SHIP missile has been FIRED");
            }
		}
        else
            System.err.println("Could not fire a PLAYERSHIP Missile because there is no PLAYERSHIP");
		
	}
	
	public void launchNPSMissile() {
		if(nonPShipExists()) {
            int index = getNonPlayerShipIndex();
            NonPlayerShip nps = (NonPlayerShip) storage.get(index);
            int numMissiles = nps.getMissileCount();
            if (numMissiles < 1)
                System.err.println("NONPLAYERSHIP is out of missiles, cannot fire");
            else {
                nps.setMissileCount(numMissiles - 1);
                MissileLauncher ml = nps.getML();
                /* creates object Missiles called missile, uses NONplayership's params*/
                Missiles missile = new Missiles(nps.getLocation(), nps.getHeading(), nps.getSpeed());
                storage.add(missile);
                System.out.println("NONPLAYERSHIP MISSILE FIRED");
            }
        }
        else
            System.err.println("Could not fire a NONPLAYERSHIP Missile because there is no NONPLAYERSHIP");
	}
	
	public void jump() {
		Point2D point = new Point2D(512,384); /* original starting location */
		if(psExists()) {
			for(GameObject ps: storage) {
            	if(ps instanceof PlayerShip) {
            		ps.setLocation(point);
            		SteerableMissileLauncher sml = ((PlayerShip) ps).getPSML();
            		sml.setLocation(point); //gets the location of the PlayerShip to match
            		System.out.println("PLAYERSHIP has JUMPED through hyperspace");
                }
            }
		}else
            System.err.println("Could not JUMP through hyperspace");
	}
	
	public void loadMissiles() {
		if(psExists()) {
			for (GameObject ps : storage) {
				if (ps instanceof PlayerShip) {
					((PlayerShip) ps).setMissileCount(10); /* resets missile count to 10 */
					System.out.println("PLAYERSHIP missiles were reloaded");
				}
			}
		} else
			System.err.println("Cannot reload PLAYERSHIP missiles");
		}

	public void destroyAsteroid() {
		if( asteroidExists() & missileExists()) {
			removeAsteroid();
			removeMissile();
			score += 4;
			System.out.println("Asteroid was destroyed by PLAYERSHIP MISSILE");
		} else
			System.err.println("Did not hit ASTEROID with a PLAYERSHIP Missile");
	}
	
	/* PS missile hit NPS */
	public void eliminatedNPS() {
		if( psExists() & nonPShipExists()) {
			removeNPS();
			score += 8;
			System.out.println("NonPlayerShip was destroyed by PLAYERSHIP MISSILE");
		} else
			System.err.println("Did not hit NonPlayerShip with a PLAYERSHIP Missile");
	}
	
	/* NPS missile hit PS */
	public void explodePS() {
		if( psExists() & nonPShipExists()) {
			removePS();
			score += 8;
			System.out.println("EXPLOSION! NonPlayerShip hit PLAYERSHIP.");
		} else
			System.err.println("NonPlayerShip did not hit PLAYERSHIP with a missile");
	}
	
	public void crash() {
		
	}
	public void hit() {}
	public void exterminate() {}
	public void impact() {}
	public void ticked() {}
	public void printDisplay() {}
	public void map() {
		//prints out a list of all the objects
		for (GameObject go: storage)
			System.out.println(go.toString());
	}
	
	/* q */
	public void quitGW() {
		System.exit(0);
	}
	
	public String toString() {return null;}
	
	private void gameOver() {
        System.out.println("===========================================================");
        System.err.println("GAME OVER");
        System.out.println("The PLAYERSHIP ran out of lives");
        //System.out.println("\tIf you would like to quit type 'Y'");
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
	
	private boolean nonPShipExists() {
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
 * left off on launchNPS
 * 
 * 
 * 
 * 
 * */
