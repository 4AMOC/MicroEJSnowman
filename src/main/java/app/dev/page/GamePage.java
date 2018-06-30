/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.page;

import app.dev.AppSingleton;
import app.dev.MainActivity;
import app.dev.model.FlakeTypes;
//import app.dev.model.AppSingleton;
import app.dev.provider.AppStringSelector;
import app.dev.widget.MyWidget;
import ej.microui.display.Image;
import ej.widget.basic.ButtonImage;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

/**
 *
 */
public class GamePage extends Page {

	MyWidget game;
	Split globalContainer = new Split(false, 0.2f);
	ButtonWrapper bk;

	Split topContainer = new Split(true, 0.8f);
	public Label Score = new Label("0");

	Split BottomContainer = new Split(false, 0.999999f);
	Split BottomRightContainer = new Split(true, 0.8f);

	Image snowMan;
	public ButtonImage btnImg = new ButtonImage();

	public GamePage() {

		this.BottomContainer.addClassSelector(AppStringSelector.blackBkgClassSelect);
		this.globalContainer.addClassSelector(AppStringSelector.blackBkgClassSelect);
		this.btnImg.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.btnImg.addClassSelector(AppStringSelector.gamePageSnowManSelect);
		this.Score.addClassSelector(AppStringSelector.mainBackgroundImg);

		this.Score.addClassSelector(AppStringSelector.gamePageLabelsSelect);
		this.Score.setText("" + AppSingleton.getInstance().getCurrentScore()); //$NON-NLS-1$
		this.Score.repaint();

		this.BottomRightContainer.addClassSelector(AppStringSelector.mainBackgroundImg);

		this.bk = new ButtonWrapper();
		this.bk.setWidget(new Label("Back")); //$NON-NLS-1$
		this.bk.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.bk.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				MainActivity.home();
			}
		});

		// TOP LAYOUT
		this.topContainer.setFirst(this.Score);
		this.topContainer.setLast(this.bk);
		this.globalContainer.setFirst(this.topContainer);

		// MIDDLE LAYOUT
		this.game = new MyWidget(this);

		this.globalContainer.setLast(this.game);
		this.setWidget(this.globalContainer);

	}

	public void updateScore(int value, FlakeTypes flakeType) {
		if (flakeType == FlakeTypes.BASIC) {
			AppSingleton.getInstance().incrementScore(value);
		} else {
			AppSingleton.getInstance().incrementScore(value * 2);
		}

		this.Score.setText("" + AppSingleton.getInstance().getCurrentScore());
		this.Score.repaint();
	}

	public void decrementScore(int value) {
		AppSingleton.getInstance().decrementScore(value);
	}

}
