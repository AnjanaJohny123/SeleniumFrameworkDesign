package AnjanaJohny.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnjanaJohny.AbstractComponents.Abstractcomponents;

public class Checkoutpage extends  Abstractcomponents{
	
	WebDriver driver;
	public Checkoutpage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	private WebElement selectCountry;
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")
	private WebElement country;
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	private WebElement submit;
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	private WebElement listToAppear;
	
	
	public void selectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(selectCountry,countryName).build().perform();
		waitForWebElementToAppear(listToAppear);
		country.click();
	}
	
	public Confirmationpage submitOrder()
	{
		submit.click();
		Confirmationpage confirmation=new Confirmationpage(driver);
		return confirmation;
		
	}

}
