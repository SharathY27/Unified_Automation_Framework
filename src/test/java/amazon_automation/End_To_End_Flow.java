package amazon_automation;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTestForParallelExecution;
import utils.ExtentReportListener;
import webpages.Amazon_HomePage;

@Listeners(ExtentReportListener.class)
public class End_To_End_Flow extends BaseTestForParallelExecution {

	@Test
	public static void selecting_product() {
		try {
			Amazon_HomePage amazon_HomePage = new Amazon_HomePage();
			Thread.sleep(3000);
			amazon_HomePage.productSearchBox.sendKeys("water bottles");
			amazon_HomePage.searchButton.click();
			Thread.sleep(2000);
			amazon_HomePage.selectElement();
			Thread.sleep(2000);
			amazon_HomePage.cartButton.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
