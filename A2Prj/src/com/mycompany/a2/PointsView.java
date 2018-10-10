package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer{
	private Label pointsValueLabel;
	
	public PointsView() {
		// Instantiate text Labels
		Label pointsTextLabel = new Label("Points: ");
		
		// Instantiating value labels
		pointsValueLabel = new Label("XXX");
		
		// Set Colors
		pointsValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		// Adding a container with a boxlayout
		Container myContainer = new Container();
		myContainer.setLayout( new BoxLayout(BoxLayout.X_AXIS));
		
		// Adding all labels in order
		myContainer.add(pointsTextLabel);
		this.add(myContainer);
	}
	
	// updates the label text based on GameWorld state variables
	// update is called whenever the observable is updates
	public void update(Observable o, Object arg) {
		// casting arg as a GameWorld (proxy = arg)
		IGameWorld gw = (IGameWorld) arg;
		
		//Getting Player Score
		int score = gw.getPlayerScore();
		pointsValueLabel.setText(""+ (score > 99 ? "" : "0") + (score > 9 ? "" : "0") + score );
		this.repaint();
	}
}
//TODO RENAME CONTAINER