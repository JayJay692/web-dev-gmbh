package de.abas.custom.owspart.spart.utils.esdk;

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
			throw new RuntimeException("Could not find file " + file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readCurrentContentFrom(File file, String encoding)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		String currentContent = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		
		String line = reader.readLine();
		while (line != null) {
			currentContent = currentContent + line + System.lineSeparator();
			line = reader.readLine();
		}
		
		reader.close();
		return currentContent;
	}	
}
