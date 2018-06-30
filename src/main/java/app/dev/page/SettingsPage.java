/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.page;

import app.dev.AppSingleton;
import app.dev.MainActivity;
import app.dev.provider.AppStringSelector;
import app.dev.provider.LevelsEnum;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

/**
 *
 */
public class SettingsPage extends Page {

	Split globalContainer = new Split(false, 0.2f);
	Split topContainer = new Split(true, 0.8f);
	ButtonWrapper backBtn = new ButtonWrapper();

	Split levelContainer = new Split(false, 0.5f);
	Label currentLevelTitle = new Label("Current Level");
	Label currentLevelvalue = new Label("Test");
	List levelsList = new List(false);

	ButtonWrapper easyLevelBtn = new ButtonWrapper();
	ButtonWrapper mediumLevelBtn = new ButtonWrapper();
	ButtonWrapper hardLevelBtn = new ButtonWrapper();

	LevelsEnum currentLevelsEnum;

	public SettingsPage() {

		this.addClassSelector(AppStringSelector.blackBkgClassSelect);
		this.globalContainer.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.levelsList.addClassSelector(AppStringSelector.mainBackgroundImg);

		this.topContainer.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.levelContainer.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.currentLevelTitle.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.currentLevelTitle.addClassSelector(AppStringSelector.whiteLabel);
		this.currentLevelvalue.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.currentLevelvalue.addClassSelector(AppStringSelector.whiteLabel);
		this.currentLevelvalue.setText(AppSingleton.getInstance().currentLevel.name());

		this.levelContainer.setFirst(this.currentLevelTitle);
		this.levelContainer.setLast(this.currentLevelvalue);
		this.topContainer.setFirst(this.levelContainer);
		this.topContainer.setLast(this.backBtn);

		this.topContainer.setLast(this.backBtn);
		this.backBtn.setClassSelectors(AppStringSelector.mainBackgroundImg);
		this.backBtn.setWidget(new Label("Back")); //$NON-NLS-1$
		this.backBtn.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				MainActivity.home();
			}
		});

		this.easyLevelBtn.setWidget(new Label(LevelsEnum.values()[0].name()));
		this.easyLevelBtn.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.levelsList.add(this.easyLevelBtn);
		this.mediumLevelBtn.setWidget(new Label(LevelsEnum.values()[1].name()));
		this.mediumLevelBtn.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.levelsList.add(this.mediumLevelBtn);
		this.hardLevelBtn.setWidget(new Label(LevelsEnum.values()[2].name()));
		this.hardLevelBtn.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.levelsList.add(this.hardLevelBtn);

		this.currentLevelsEnum = AppSingleton.getInstance().currentLevel;

		// this.repaint();

		this.globalContainer.setFirst(this.topContainer);
		this.globalContainer.setLast(this.levelsList);
		this.setWidget(this.globalContainer);

		this.easyLevelBtn.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				AppSingleton.getInstance().setCurrentLevel(LevelsEnum.EASY);
				SettingsPage.this.currentLevelvalue.setText(AppSingleton.getInstance().currentLevel.name());
				SettingsPage.this.repaint();
			}
		});

		this.mediumLevelBtn.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				AppSingleton.getInstance().setCurrentLevel(LevelsEnum.MEDIUM);
				SettingsPage.this.currentLevelvalue.setText(AppSingleton.getInstance().currentLevel.name());
				SettingsPage.this.repaint();
			}
		});

		this.hardLevelBtn.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				AppSingleton.getInstance().setCurrentLevel(LevelsEnum.HARD);
				SettingsPage.this.currentLevelvalue.setText(AppSingleton.getInstance().currentLevel.name());
				SettingsPage.this.repaint();
			}
		});

	}
}
