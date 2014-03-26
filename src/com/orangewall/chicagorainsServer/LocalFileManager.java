package com.orangewall.chicagorainsServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class LocalFileManager {
	
	public static String readOnelineFile(String fileName) throws Exception {
		BufferedReader br = null;
		String retVal = "";
		
		File theFile = new File(fileName);
		if (false == theFile.exists()) {
			return null;
		}
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(fileName));
 
			while ((sCurrentLine = br.readLine()) != null) {
				retVal += sCurrentLine;
			}
 
		} catch (IOException e) {
			throw new Exception(ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				throw new Exception(ExceptionUtils.getStackTrace(ex));
			}
		}

		return retVal;

	}
	
	public static void writeOnelineFile(String content, String fileName) {
		File file = new File(fileName);
		if (true == file.exists()) {
			file.delete();
		}
		
		try {
			file.createNewFile();
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
