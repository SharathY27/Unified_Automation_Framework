package webpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTestForParallelExecution;
import utils.GlobalVariables;

import static utils.CommonUtils.attachScreenshotInReport;

public class LoginPage {

	@FindBy(name = "username")
	public WebElement username;

	@FindBy(name = "password")
	public WebElement password;

	@FindBy(xpath = "//button[text()[normalize-space()='Login']]")
	public WebElement loginButton;

	public LoginPage() {
		PageFactory.initElements(BaseTestForParallelExecution.getDriver(), this);
	}

	public void enterUsername() {
		username.sendKeys(GlobalVariables.username);
		attachScreenshotInReport(false, "Entering Username");
	}

	public void enterPassword() {
		password.sendKeys(GlobalVariables.password);
		attachScreenshotInReport(false, "Enter Password");
	}

	public void clickLoginButton() {
		loginButton.click();
		attachScreenshotInReport(false, "Clicking Login Button");
	}

}
