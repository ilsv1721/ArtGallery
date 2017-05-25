package com.ilya.art.config.utils;

/**
 * Local server storage properties java wrapper. All properties are final and
 * should be initialized in constructor.
 * 
 * @author ilya
 */
public class LocalStorageProps {

	/**
	 * Path to images of exhibitions java wrapper.
	 */
	private final String localStoragePathExhibs;

	public LocalStorageProps(String localStoragePathExhibs) {
		this.localStoragePathExhibs = localStoragePathExhibs;
	}

	public String getLocalStoragePathExhibs() {
		return localStoragePathExhibs;
	}

}
