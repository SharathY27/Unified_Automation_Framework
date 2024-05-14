package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.GlobalVariables;

import static utils.CommonUtils.*;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTestForParallelExecution extends GlobalVariables {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static WebDriver driver1;

	public static ThreadLocal<String> filePath = new ThreadLocal<String>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	@BeforeMethod
	public void beforeMethodMethod(Method testMethod) {
		filePath.set(createFolder(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "Screenshots" + File.separator
				+ testMethod.getDeclaringClass().getName() + "_" + testMethod.getName()));
		loadProperties();
		setupDriver();
		getDriver().get(app_url);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {
		getDriver().quit();
	}

	@AfterSuite
	public void afterSuiteMethod() {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		getDriver().manage().window().maximize();
		getDriver().get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "Reports" + File.separator + "AutomationReport.html");
	}

	@SuppressWarnings("deprecation")
	public void closeAllBrowsers() {
		try {
			int processId = Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
			String osName = System.getProperty("os.name").toLowerCase();
			if (osName.contains("windows")) {
				Runtime.getRuntime().exec("taskkill /F /PID " + processId);
			} else if (osName.contains("linux") || osName.contains("mac")) {
				Runtime.getRuntime().exec("kill -9 " + processId);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clearDirectory(String directoryPath) throws IOException {
		File directory = new File(directoryPath);
		if (!directory.exists() || !directory.isDirectory()) {
			throw new IllegalArgumentException("Path is not a directory or does not exist");
		}

		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				clearDirectory(file.getAbsolutePath());
			} else {
				if (!file.delete()) {
					throw new IOException("Failed to delete " + file);
				}
			}
		}
	}

	public void setupDriver() {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			if (docker) {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName(browser);
				try {
					driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else {
				driver.set(new ChromeDriver());
				System.out.println(Thread.currentThread().getId());
			}
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		}

	}

}
