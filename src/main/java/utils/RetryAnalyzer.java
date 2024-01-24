package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count = 0, retryCount = 1;

	public boolean retry(ITestResult iTestResult) {
		if (count < retryCount) {
			count++;
			return true;
		}
		return false;
	}

}
