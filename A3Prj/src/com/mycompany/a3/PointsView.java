package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer{
	private GameWorld gw;
	
	/* Instantiate value Labels v- for value*/
	private Label vpoints;
	private Label vmissileCount;
	private Label velapsedTime;
	private Label vsound;
	private Label vlives;
	
	public PointsView(GameWorld gw) {
		this.gw = gw;
		
		Container pvContainer = new Container();
		pvContainer.setLayout( new BoxLayout(BoxLayout.X_AXIS));
		pvContainer.getAllStyles().setBgTransparency(100);
		pvContainer.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		/* Text Labels for values */
		Label lpoints = new Label("Points: ");
		Label lmissileCount = new Label("Missile Count: ");
		Label lelapsedTime = new Label("Elapsed Time: ");
		Label lsound = new Label("Sound: ");
		Label llives = new Label("Lives: ");
		
		/* Style */
		lpoints.getAllStyles().setBgTransparency(100);
		lmissileCount.getAllStyles().setBgTransparency(100);
		lelapsedTime.getAllStyles().setBgTransparency(100);
		lsound.getAllStyles().setBgTransparency(100);
		llives.getAllStyles().setBgTransparency(100);
		
		lpoints.getAllStyles().setBgColor(ColorUtil.BLUE);
		lmissileCount.getAllStyles().setBgColor(ColorUtil.BLUE);
		lelapsedTime.getAllStyles().setBgColor(ColorUtil.BLUE);
		lsound.getAllStyles().setBgColor(ColorUtil.BLUE);
		llives.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		lpoints.getAllStyles().setFgColor(ColorUtil.WHITE);
		lmissileCount.getAllStyles().setFgColor(ColorUtil.WHITE);
		lelapsedTime.getAllStyles().setFgColor(ColorUtil.WHITE);
		lsound.getAllStyles().setFgColor(ColorUtil.WHITE);
		llives.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		lpoints.getAllStyles().setPadding(LEFT,1);
		lmissileCount.getAllStyles().setPadding(LEFT,1);
		lelapsedTime.getAllStyles().setPadding(LEFT,1);
		lsound.getAllStyles().setPadding(LEFT,1);
		llives.getAllStyles().setPadding(LEFT,1);
		
		lpoints.getAllStyles().setPadding(RIGHT,1);
		lmissileCount.getAllStyles().setPadding(RIGHT,1);
		lelapsedTime.getAllStyles().setPadding(RIGHT,1);
		lsound.getAllStyles().setPadding(RIGHT,1);
		llives.getAllStyles().setPadding(RIGHT,1);
		
		
		/* Instantiating value labels v - for value */
		vpoints = new Label("XXX");
		vmissileCount = new Label("XXX");
		velapsedTime = new Label("XXX");
		vsound = new Label("XXX");
		vlives = new Label("XXX");

		/* Style */
		vpoints.getAllStyles().setBgTransparency(100);
		vmissileCount.getAllStyles().setBgTransparency(100);
		velapsedTime.getAllStyles().setBgTransparency(100);
		vsound.getAllStyles().setBgTransparency(100);
		vlives.getAllStyles().setBgTransparency(100);
		
		vpoints.getAllStyles().setBgColor(ColorUtil.BLUE);
		vmissileCount.getAllStyles().setBgColor(ColorUtil.BLUE);
		velapsedTime.getAllStyles().setBgColor(ColorUtil.BLUE);
		vsound.getAllStyles().setBgColor(ColorUtil.BLUE);
		vlives.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		vpoints.getAllStyles().setFgColor(ColorUtil.WHITE);
		vmissileCount.getAllStyles().setFgColor(ColorUtil.WHITE);
		velapsedTime.getAllStyles().setFgColor(ColorUtil.WHITE);
		vsound.getAllStyles().setFgColor(ColorUtil.WHITE);
		vlives.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		vpoints.getAllStyles().setPadding(LEFT,1);
		vmissileCount.getAllStyles().setPadding(LEFT,1);
		velapsedTime.getAllStyles().setPadding(LEFT,1);
		vsound.getAllStyles().setPadding(LEFT,1);
		vlives.getAllStyles().setPadding(LEFT,1);
		
		vpoints.getAllStyles().setPadding(RIGHT,1);
		vmissileCount.getAllStyles().setPadding(RIGHT,1);
		velapsedTime.getAllStyles().setPadding(RIGHT,1);
		vsound.getAllStyles().setPadding(RIGHT,1);
		vlives.getAllStyles().setPadding(RIGHT,1);
		
		// Adding all labels in order
		pvContainer.add(lpoints);
		pvContainer.add(vpoints);
		pvContainer.add(lmissileCount);
		pvContainer.add(vmissileCount);
		pvContainer.add(lelapsedTime);
		pvContainer.add(velapsedTime);
		pvContainer.add(lsound);
		pvContainer.add(vsound);
		pvContainer.add(llives);
		pvContainer.add(vlives);
		this.add(pvContainer);
	}
	
	// updates the label text based on GameWorld state variables
	// update is called whenever the observable is updates
	public void update(Observable o, Object arg) {
		// casting arg as a GameWorld (proxy = arg)
		IGameWorld gw = (IGameWorld) o;
		
		/* Getting Player Score */
		int score = gw.getPlayerScore();
		vpoints.setText(""+ (score > 99 ? "" : "0") + (score > 9 ? "" : "0") + score );
		
		/* Getting and displaying missile count */
		int mCount = gw.getMissileCount();
		vmissileCount.setText(""+mCount+"");
		
		/*Getting and displaying elapsed time/clock ticks */
		int ticks = gw.getTime();
		velapsedTime.setText(""+ticks+"");
		
		/* Getting and Displaying sound */
		boolean sound = gw.getSound();
		
		if(sound = true) {
			vsound.setText("ON");
		} else
			vsound.setText("OFF");
		
		/* Getting and Displaying lives */
		int lives = gw.getLives();
		vlives.setText(""+lives+"");
		this.getParent().revalidate();
		
		/*update*/
		this.repaint();
	}
}
//TODO RENAME CONTAINER