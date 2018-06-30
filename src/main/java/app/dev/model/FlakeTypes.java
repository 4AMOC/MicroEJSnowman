/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.model;

/**
 *
 */
public enum FlakeTypes {

	BASIC("basic", "/images/flocon.png"), ICE_CUBE("ice_cube", "/images/ice.png");
	public String type;
	public String imgUrl;

	FlakeTypes(String type, String url) {
		this.type = type;
		this.imgUrl = url;
	}

	public String nameToString() {
		return this.type;
	}
}
