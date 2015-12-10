package com.lishyWist;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileSystemDataStore implements DataStore {

	private static final String FILE_EXTENSION = ".json";

	private final String rootDirectory;

	public FileSystemDataStore(final String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	@Override
	public String getData(final String scope, final String key) {
		try {
			return FileUtils.readFileToString(getFile(scope, key));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void putData(String scope, String key, String data) {
		try {
			FileUtils.writeStringToFile(getFile(scope, key), data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private File getFile(final String scope, final String key) {
		String filePath = rootDirectory + File.separator + scope + File.separator + key + FILE_EXTENSION;
		return new File(filePath);
	}

}
