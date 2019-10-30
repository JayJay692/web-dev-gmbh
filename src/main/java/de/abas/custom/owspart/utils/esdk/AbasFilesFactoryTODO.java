package de.abas.custom.owspart.utils.esdk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class AbasFilesFactoryTODO {

	public void replaceContentInFile(File file, String oldValue, String newValue, String encoding){
		try {
			String currentContent = readCurrentContentFrom(file, encoding);
			String newContent = currentContent.replaceAll(oldValue, newValue);

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			writer.write(newContent);
			writer.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Could not find file " + file.getAbsolutePath(), e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String readCurrentContentFrom(File file, String encoding)
			throws IOException {
		StringBuilder currentContent = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		
		String line;
		while ((line = reader.readLine()) != null) {
			currentContent.append(line).append(System.lineSeparator());
		}
		
		reader.close();
		return currentContent.toString();
	}	
}
