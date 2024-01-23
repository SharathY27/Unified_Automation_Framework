package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalVariables {

	public static String app_url;
	public static String username;
	public static String password;
	public static boolean base64;
	public static String environment;
	public static boolean highlightElement;
	public static String browser;

	public void loadProperties() {
		try {
			FileReader fileReader = new FileReader(
					System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
							+ "resources" + File.separator + "Config" + File.separator + "config.properties");
			Properties properties = new Properties();
			properties.load(fileReader);
			app_url = properties.getProperty("app_url");
			browser = properties.getProperty("browser");
			environment = properties.getProperty("environment");
			base64 = Boolean.parseBoolean(properties.getProperty("base64"));
			highlightElement = Boolean.parseBoolean(properties.getProperty("highlightElement"));
			username = CommonUtils.decodeString(properties.getProperty("username"));
			password = CommonUtils.decodeString(properties.getProperty("password"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
