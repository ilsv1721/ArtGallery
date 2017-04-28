package com.ilya.art.utils.files;

/**
 * Path implementation used in this application.
 * 
 * @author ilya
 *
 */

public final class BasicPathImplementation implements Path {
	private final String filename;
	private final String pathToFile;

	/**
	 * The only way to construct file object.
	 * 
	 * @param filename
	 * 
	 * @param pathToFile
	 *            This path should end with separator.
	 */
	public BasicPathImplementation(String filename, String pathToFile) {
		super();
		this.filename = filename;
		this.pathToFile = pathToFile;
	}

	@Override
	public String getFilename() {
		return filename;
	}

	@Override
	public String getPathToFile() {
		return pathToFile;
	}

}
