package webpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTestForParallelExecution;

public class Amazon_HomePage {

	@FindBy(id = "twotabsearchtextbox")
	public WebElement productSearchBox;

	@FindBy(id = "a-autoid-1-announce")
	public WebElement selectingElement;

	@FindBy(id = "nav-search-submit-button")
	public WebElement searchButton;
	
	@FindBy(id="nav-cart")
	public WebElement cartButton;

	public void selectElement() {
		this.selectingElement.click();
	}
	

	public Amazon_HomePage() {
		PageFactory.initElements(BaseTestForParallelExecution.getDriver(), this);
	}
}
