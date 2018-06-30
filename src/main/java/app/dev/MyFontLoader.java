/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev;

import ej.style.font.FontProfile;
import ej.style.font.loader.AbstractFontLoader;

/**
 *
 */
public class MyFontLoader extends AbstractFontLoader {

	@Override
	protected int getFontHeight(FontProfile fontProfile) {
		switch (fontProfile.getSize()) {
		case LARGE:
			return 50;
		case MEDIUM:
			return 30;
		default:
			return 30;
		}
	}

}
