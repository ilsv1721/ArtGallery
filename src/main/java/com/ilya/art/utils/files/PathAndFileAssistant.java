package com.ilya.art.utils.files;

import java.io.IOException;

public interface PathAndFileAssistant<R> {

	Path getPath(R resource) throws IOException;

	void saveToFile(String prefix, Path path, R resource) throws IllegalStateException, IOException;

}
