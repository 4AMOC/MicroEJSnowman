/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.page;

import app.dev.MainActivity;
import app.dev.provider.AppStringSelector;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

/**
 *
 */
public class Home extends Page {

	Split container = new Split(false, 0.15f);
	List btnList = new List(false);

	ButtonWrapper game = new ButtonWrapper();
	ButtonWrapper score = new ButtonWrapper();
	ButtonWrapper settings = new ButtonWrapper();
	ButtonWrapper exit = new ButtonWrapper();

	public Home() {

		this.container.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.game.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.score.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.settings.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.exit.addClassSelector(AppStringSelector.mainBackgroundImg);

		this.game.setWidget(new Label("Play"));
		Label scoreLabel = new Label("Scores");
		this.score.setWidget(scoreLabel);
		Label settingsLabel = new Label("Settings");
		this.settings.setWidget(settingsLabel);
		this.exit.setWidget(new Label("Exit"));

		this.btnList.add(this.game);
		this.btnList.add(this.score);
		this.btnList.add(this.settings);
		this.btnList.add(this.exit);

		this.container.setLast(this.btnList);
		this.setWidget(this.container);

		this.game.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				MainActivity.show(new GamePage());
			}
		});

		this.settings.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				MainActivity.show(new SettingsPage());
			}
		});

		this.score.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				MainActivity.show(new Scores());
			}
		});

		this.exit.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				ExitHandler exithandler = ServiceLoaderFactory.getServiceLoader().getService(ExitHandler.class);
				if (exithandler != null) {
					exithandler.exit();
				}
			}
		});
	}

}