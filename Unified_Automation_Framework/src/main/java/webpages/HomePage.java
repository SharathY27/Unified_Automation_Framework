package webpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTestForParallelExecution;
import utils.CommonUtils;

public class HomePage {

	@FindBy(xpath = "//span[text()='Recruitment']/parent::a")
	public WebElement recruitmentButton;

	@FindBy(xpath = "//a//span[text()='Buzz']")
	public WebElement buzzButton;

	@FindBy(xpath = "//textarea[@rows='1']")
	public WebElement postingTextBox;

	@FindBy(xpath = "//span[text()='My Info']/ancestor::a")
	public WebElement myInfoButton;

	@FindBy(xpath = "//button[normalize-space()='Add]")
	public WebElement attachmentAddButton;

	@FindBy(xpath = "//form//div[text()='Browse']")
	public WebElement browseButton;

	public HomePage() {
		PageFactory.initElements(BaseTestForParallelExecution.getDriver(), this);
	}

	public void clickBuzzButton() {
		CommonUtils.moveToElementWithActionsClass(buzzButton);
		buzzButton.click();
		CommonUtils.attachScreenshotInReport(false, "Buzz button is clicked");
	}

	public void enterWhatsOnYourMind() {
		postingTextBox.sendKeys("Hey folks, Welcome All");
		CommonUtils.attachScreenshotInReport(true, "Posting Buzz");
	}
}
