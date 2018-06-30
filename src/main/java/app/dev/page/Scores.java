/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.page;

import java.util.Random;

import app.dev.AppSingleton;
import app.dev.MainActivity;
import app.dev.provider.AppStringSelector;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Scroll;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

/**
 *
 */
public class Scores extends Page {

	Split globalContainer = new Split(false, 0.2f);
	Split topContainer = new Split(true, 0.8f);
	ButtonWrapper backBtn = new ButtonWrapper();

	private final Scroll scroll;
	List scoresList;
	Label test = new Label("TEST");

	public Scores() {

		this.scroll = new Scroll(false, false);
		this.scroll.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.scoresList = new List(false);
		this.scoresList.addClassSelector(AppStringSelector.blackBkgClassSelect);

		this.addClassSelector(AppStringSelector.blackBkgClassSelect);
		this.globalContainer.addClassSelector(AppStringSelector.blackBkgClassSelect);
		this.topContainer.addClassSelector(AppStringSelector.mainBackgroundImg);

		Label currentScoreLabel = new Label(String.valueOf(AppSingleton.getInstance().getCurrentScore()));
		currentScoreLabel.addClassSelector(AppStringSelector.scoreLabelsSelect);
		this.scoresList.add(currentScoreLabel);

		Random rand = new Random();
		for (int i = 0; i < 3; i++) {
			Label test1 = new Label(String.valueOf(rand.nextInt(500) + 0));
			test1.addClassSelector(AppStringSelector.scoreLabelsSelect);
			this.scoresList.add(test1);
		}

		this.backBtn.setClassSelectors(AppStringSelector.mainBackgroundImg);
		this.backBtn.setWidget(new Label("Back")); //$NON-NLS-1$
		this.backBtn.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				MainActivity.home();
			}
		});

		this.topContainer.setLast(this.backBtn);
		this.globalContainer.setFirst(this.topContainer);
		this.globalContainer.setLast(this.scoresList);
		this.setWidget(this.globalContainer);
	}
}
