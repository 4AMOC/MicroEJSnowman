/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.provider;

/**
 *
 */
public enum LevelsEnum {
	EASY("EASY", 300), MEDIUM("MEDIUM", 200), HARD("HARD", 100);

	public String tag;
	public int speed;

	/**
	 *
	 */
	private LevelsEnum(String level, int speed) {
		this.tag = level;
		this.speed = speed;

	}

	@Override
	public String toString() {
		return this.tag;
	}

	public LevelsEnum fromString(String value) {
		return LevelsEnum.valueOf(value);
	}
}
