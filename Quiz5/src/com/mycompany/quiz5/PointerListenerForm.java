package com.mycompany.quiz5;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class PointerListenerForm extends Form {
	//constructor
	public PointerListenerForm() {
		// ...[set the form layout to borderlayout, generate and style buttons
		setLayout(new BorderLayout());
		// and
		// add them to on north and south containers]
		// have an empty container in the center and add a pointer pressed
		// listener to it
		PointerContainer leftContainer = new PointerContainer();
		PointerContainer centerContainer = new PointerContainer();
		PointerContainer rightContainer = new PointerContainer();
		
		leftContainer.getAllStyles().setPadding(Component.TOP,50);
		centerContainer.getAllStyles().setPadding(Component.TOP,50);
		rightContainer.getAllStyles().setPadding(Component.TOP,50);
		
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.CYAN));

		this.add(BorderLayout.WEST, leftContainer);
		this.add(BorderLayout.CENTER, centerContainer);
		this.add(BorderLayout.EAST, rightContainer);
		show();
		
	}
}
