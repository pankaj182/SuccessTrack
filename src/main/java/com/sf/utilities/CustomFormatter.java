package com.sf.utilities;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class CustomFormatter {
	
	public static byte[] getBytesFromPart(Part partFile) {
		InputStream pdfFileBytes = null;
		byte[] bytes = null;
		try {
			pdfFileBytes = partFile.getInputStream();
			bytes = new byte[pdfFileBytes.available()];
            pdfFileBytes.read(bytes);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		} 
		return bytes;
	}
}
