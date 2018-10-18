package com.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtil {
	
	public static String getProperty(String propertyName) {
		Properties prop = PropertyLoader();
		//System.out.println(prop.toString());
		return prop.getProperty(propertyName);
	}
	
	private static Properties PropertyLoader() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			ClassLoader objClassLoader = CommonUtil.class.getClassLoader();
			
			input = new FileInputStream(objClassLoader.getResource("resources/sql.properties").getFile());

			// load a properties file
			prop.load(input);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prop;

	}
	
}
