/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.model;

import java.util.Random;

import app.dev.AppSingleton;
import app.dev.page.GamePage;
import app.dev.provider.AppComponentsPositions;
import ej.microui.display.Image;

/**
 *
 */
public class Flake {

	private int posX;
	private int poxY;
	private Image img;
	private FlakeTypes flakeType;

	private GamePage container;

	public static int maxY;

	public boolean hasCrashed = false;

	/**
	 * Gets the flakeType.
	 *
	 * @return the flakeType.
	 */
	public FlakeTypes getFlakeType() {
		return this.flakeType;
	}

	/**
	 * Sets the flakeType.
	 *
	 * @param flakeType
	 *            the flakeType to set.
	 */
	public void setFlakeType(FlakeTypes flakeType) {
		this.flakeType = flakeType;

	}

	/**
	 * Gets the img.
	 *
	 * @return the img.
	 */
	public Image getImg() {
		return this.img;
	}

	/**
	 * Sets the img.
	 *
	 * @param img
	 *            the img to set.
	 */
	public void setImg(Image img) {
		this.img = img;
	}

	public Flake() {
	}

	public Flake(int x, int y, FlakeTypes flakeType, GamePage container) {
		this.posX = x;
		this.poxY = y;
		this.flakeType = flakeType;
		this.container = container;
	}

	public void moveForward() {

		int randomJum = new Random().nextInt(14) + 0;

		if (this.hasCrashed) {
			this.hasCrashed = false;
			this.poxY = 0;
		}
		if ((this.posX == AppSingleton.getInstance().getSnowmanCurrentX())
				|| ((this.poxY >= 200 - 10) && (this.poxY <= 200 - 10))) {
			this.hasCrashed = true;
			this.container.updateScore(10, FlakeTypes.BASIC);
		}

		if (!this.hasCrashed) {
			if (this.poxY > AppComponentsPositions.playgroundYEnd) {
				this.poxY = 0;
				this.container.decrementScore(5);
			} else {
				this.poxY += randomJum;
			}
		}

	}

	/**
	 * Gets the posX.
	 *
	 * @return the posX.
	 */
	public int getPosX() {
		return this.posX;
	}

	/**
	 * Sets the posX.
	 *
	 * @param posX
	 *            the posX to set.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Gets the poxY.
	 *
	 * @return the poxY.
	 */
	public int getPoxY() {
		return this.poxY;
	}

	/**
	 * Sets the poxY.
	 *
	 * @param poxY
	 *            the poxY to set.
	 */
	public void setPoxY(int poxY) {
		this.poxY = poxY;
	}
}
