package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTestForParallelExecution;
import java.lang.Class;
import java.lang.reflect.*;

public class SuiteListener implements IAnnotationTransformer, ITestListener {

	public void onTestFailure(ITestResult result) {
		String descFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "Sreenshots" + File.separator
				+ result.getMethod().getMethodName() + ".png";
		File srcFile = ((TakesScreenshot) BaseTestForParallelExecution.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(descFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
