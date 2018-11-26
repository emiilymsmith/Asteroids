package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {
	/* mark the object as selected or not */
	public void setSelected(boolean select);
	
	/* a way to test if the object is selected */
	public boolean isSelected();
	
	/* a way to determine if a pointer is "in" an object
	 * pPtrRelPrnt is a pointer position relative to the parent origin
	 * pCpmRelPrnt is the component position relative to the parent origin
	 *  */
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	/* a way to "draw" the object that knows about drawing 
	 * different ways depending on isSelected */
	public void draw(Graphics g, Point pCmpRelPrnt);
}
