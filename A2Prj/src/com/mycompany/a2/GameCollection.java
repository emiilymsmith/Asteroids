package com.mycompany.a2;

import java.util.Vector;

/**
 * @author Emily Smith
 * @version 2.0
 * 
 * The Game Collection implements a collection of Game Objects
 * It uses a vector as the structure but does NOT expose
 * the structure to other classes. It provides an iterator for 
 * accessing the objects in the collection.
 * 
 */

public class GameCollection implements ICollection{
	private Vector theCollection;
	
	public GameCollection() {
		theCollection = new Vector();
	}
	
	public void add(Object newObject) {
		theCollection.addElement(newObject);
	}
	
	public void remove(Object object) {
		theCollection.removeElement(object);
	}
	
	public IIterator getIterator() {
		return new GameVectorIterator();
	}
	
	private class GameVectorIterator implements IIterator{
		private int currElementIndex;
		
		public GameVectorIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (theCollection.size() <= 0)
				return false;
			if(currElementIndex == theCollection.size() - 1)
				return false;
			return true;
		}
		
		public Object getNext() {
			currElementIndex ++;
			return(theCollection.elementAt(currElementIndex));
		}
		
		public Object checkNext() {
			return(theCollection.elementAt(currElementIndex));
		}
		
	}
	
}
