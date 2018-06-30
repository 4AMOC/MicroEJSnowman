/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev;

import java.util.ArrayList;
import java.util.Random;

import app.dev.model.Flake;
import app.dev.model.FlakeTypes;
import app.dev.provider.LevelsEnum;

/**
 *
 */
public class AppSingleton {
	private static AppSingleton instance;
	private int currentScore = 0;

	private final Random random = new Random();
	private ArrayList<Flake> appFlakes = new ArrayList<>();
	private final ArrayList<FlakeTypes> flakeTypes = new ArrayList<>();
	private final int[] flakesX = { 20, 50, 80, 110, 140, 170, 200 };
	private final int[] flakesY = { 0, 10, 20, 30, 40 };

	public LevelsEnum currentLevel = LevelsEnum.EASY;

	public static int snowManCurrentXPos;

	public void setSnowmanCurrentX(int current) {
		this.snowManCurrentXPos = current;
	}

	public int getSnowmanCurrentX() {
		return this.snowManCurrentXPos;
	}

	public static AppSingleton getInstance() {
		if (instance == null) {
			instance = new AppSingleton();
		}
		return instance;
	}

	private int randomFlakesNumber() {
		return this.random.nextInt(5) + 1;
	}

	private FlakeTypes randomFlakeType() {
		this.flakeTypes.add(FlakeTypes.BASIC);
		this.flakeTypes.add(FlakeTypes.ICE_CUBE);
		int flakeIndex = this.random.nextInt(this.flakeTypes.size()) + 0;
		return this.flakeTypes.get(flakeIndex);
	}

	private int randomX() {
		int index = this.random.nextInt(this.flakesX.length) + 0;
		return this.flakesX[index];
	}

	private int randomY() {
		int index = this.random.nextInt(this.flakesY.length) + 0;
		return this.flakesY[index];
	}

	public void incrementScore(int value) {
		if (this.currentScore < 0) {
			this.currentScore = Math.abs(this.currentScore) - value;
		} else {
			this.currentScore += value;
		}

	}

	public void decrementScore(int value) {
		this.currentScore -= value;
	}

	public void setCurrentLevel(LevelsEnum level) {
		this.currentLevel = level;
	}

	public LevelsEnum getCurrentLevel() {
		return this.currentLevel;
	}

	/**
	 * Gets the currentScore.
	 *
	 * @return the currentScore.
	 */
	public int getCurrentScore() {
		return this.currentScore;
	}

	/**
	 * Sets the currentScore.
	 *
	 * @param currentScore
	 *            the currentScore to set.
	 */
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	/**
	 * Gets the appFlakes.
	 *
	 * @return the appFlakes.
	 */
	public ArrayList<Flake> getAppFlakes() {
		return this.appFlakes;
	}

	/**
	 * Sets the appFlakes.
	 *
	 * @param appFlakes
	 *            the appFlakes to set.
	 */
	public void setAppFlakes(ArrayList<Flake> appFlakes) {
		this.appFlakes = appFlakes;
	}

}
