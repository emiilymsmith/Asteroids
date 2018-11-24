package com.mycompany.a3;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class SoundForm extends Form implements ActionListener{
	/* Used for Background Sound */
	private Sound sound;
	private boolean pauseB = false; //pause button
	public SoundForm() {
		sound = new Sound("alarm.wav"); //TODO find a looped sound thats good
		sound.pause();
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		pauseB = !pauseB;
		if (pauseB)
			sound.pause();
		else
			sound.play();
	}

}
