package AnjanaJohny.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnjanaJohny.AbstractComponents.Abstractcomponents;

public class Confirmationpage extends Abstractcomponents{
	
	WebDriver driver;
	public Confirmationpage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(css=".hero-primary")
	private WebElement message;
	
	public String verifyText()
	{
		return message.getText();
	}
}
