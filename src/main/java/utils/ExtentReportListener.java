package utils;

import java.io.File;
import java.net.UnknownHostException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static utils.CommonUtils.*;

public class ExtentReportListener implements ITestListener {

	public static ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
			System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator + "Reports" + File.separator + "AutomationReport.html");
	public static ExtentReports extentReports = new ExtentReports();
	public static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();

	public void onStart(ITestContext context) {
		String hostname = "";
		try {
			hostname = java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String username = System.getProperty("user.name");
		extentReports.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extentReports.setSystemInfo("Hostname", hostname);
		extentReports.setSystemInfo("Username", username);
		sparkReporter.config().setDocumentTitle("Automation Reports");
		sparkReporter.config().setReportName("Automation Test Results by Sharath");
	}

	public void onTestStart(ITestResult result) {
		synchronized (extentReports) {
			logger.set(extentReports.createTest(result.getMethod().getMethodName()));
		}
	}

	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.get().log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case PASS ", ExtentColor.GREEN));
		}
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.get().log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed ", ExtentColor.RED));
			logger.get().log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + "", ExtentColor.RED));
			logger.get().log(Status.FAIL,
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShotAndReturnPath()).build());
		}
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			logger.get().log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped ", ExtentColor.ORANGE));
		}
	}

	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
