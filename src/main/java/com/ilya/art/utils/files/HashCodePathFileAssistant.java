package com.ilya.art.utils.files;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * HashCode implementation of PathAssistnat, which allows easy no duplicate
 * resource saving in a file system.
 * 
 * Resource hashcode calculated using
 * {@link HashCodePathFileAssistant#hashingAlgorithm} hashing algorithm (any of
 * {@link MessageDigest}) and translated to 16 radix system. However , it is
 * highly recommended to use algorithms which is declared as static public
 * fields within this class (e.g {@link HashCodePathFileAssistant#MD_5})
 * 
 * 
 * Path is builded upon this hash string, where any folder contain exactly
 * {@link HashCodePathFileAssistant#numSymbolsInFolderNames} symbols.
 * 
 * File name may contain between 1 and
 * {@link HashCodePathFileAssistant#numSymbolsInFolderNames} symbols.
 * 
 * @author ilya
 *
 */
public class HashCodePathFileAssistant implements PathAndFileAssistant<MultipartFile> {

	private static final Logger logger = LogManager.getLogger(HashCodePathFileAssistant.class);

	public final String MD_5 = "MD5";

	private final int numSymbolsInFolderNames;
	private final String hashingAlgorithm;

	public HashCodePathFileAssistant(int numSymbolsInFolderNames) {
		this.hashingAlgorithm = MD_5;
		if (numSymbolsInFolderNames <= 0) {
			logger.warn(" Illegal argument numSymbolsInFolderNames. Default was used. ");
			this.numSymbolsInFolderNames = 2;
		} else {
			this.numSymbolsInFolderNames = numSymbolsInFolderNames;
		}
	}

	public HashCodePathFileAssistant(int numSymbolsInFolderNames, String hashingAlgorithm) {
		if (numSymbolsInFolderNames <= 0) {
			logger.warn(" Illegal argument numSymbolsInFolderNames. Default was used. ");
			throw new IllegalStateException();
		} else {
			this.numSymbolsInFolderNames = numSymbolsInFolderNames;
		}
		this.hashingAlgorithm = hashingAlgorithm;
	}

	@Override
	public Path getPath(MultipartFile resource) throws IOException {
		byte[] resourceAsBytes = resource.getBytes();
		byte[] md5Byted = null;
		try {
			md5Byted = MessageDigest.getInstance(this.hashingAlgorithm).digest(resourceAsBytes);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getClass().getName() + " while trying invike path utility. ");
		}
		String hashString = new BigInteger(1, md5Byted).toString(16);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (i = 0; i < hashString.length() - numSymbolsInFolderNames; i = i + numSymbolsInFolderNames) {
			sb.append(hashString.substring(i, i + numSymbolsInFolderNames));
			sb.append(File.separator);
		}
		Path path = new BasicPathImplementation(hashString.substring(i, hashString.length()), sb.toString());
		sb = null;
		return path;
	}

	@Override
	public void saveToFile(String prefix, Path path, MultipartFile resource) throws IllegalStateException, IOException {
		new File(prefix + path.getPathToFile()).mkdirs();
		File fileToSaveIn = new File(prefix + path.getPathToFile() + path.getFilename());
		if (!fileToSaveIn.exists()) {
			resource.transferTo(fileToSaveIn);
		}

	}

	@Override
	public void deleteFile(java.nio.file.Path path) {
		try {
			Files.delete(path);
		} catch (NoSuchFileException e) {
			logger.error(e.getClass().getName() + " while trying delete painting from hard drive. ");
		} catch (DirectoryNotEmptyException e) {
			logger.error(e.getClass().getName() + " while trying delete painting from hard drive. ");
		} catch (IOException e) {
			logger.error(e.getClass().getName() + " while trying delete painting from hard drive. ");
		}
	}
}
