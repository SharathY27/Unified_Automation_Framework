package ui_testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTestForParallelExecution;
import utils.ExtentReportListener;
import webpages.HomePage;
import webpages.LoginPage;

//@Listeners(ExtentReportListener.class)
public class Testcase extends BaseTestForParallelExecution {

	public void login() {
		LoginPage loginPage = new LoginPage();
		loginPage.enterUsername();
		loginPage.enterPassword();
		loginPage.clickLoginButton();
	}

	public void buzzPosting() {
		HomePage homePage = new HomePage();
		homePage.clickBuzzButton();
		homePage.enterWhatsOnYourMind();
	}

//	@Test
	public void loginTest() {
		login();
	}

//	@Test
	public void postBuzz() {
		login();
		buzzPosting();
	}

}
