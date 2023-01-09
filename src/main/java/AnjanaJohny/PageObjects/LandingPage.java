package AnjanaJohny.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnjanaJohny.AbstractComponents.Abstractcomponents;

public class LandingPage extends Abstractcomponents {
	WebDriver driver;
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
    @FindBy(id="userEmail")
    private WebElement userEmail;
    
    @FindBy(id="userPassword")
    private WebElement userPassword;
    
    @FindBy(id="login")
    private WebElement submit;
    
    @FindBy(css="div[class*='flyInOut']")
    private WebElement errorMessage;
    
    public ProductCatalogue loginApplication(String email,String password)
    {
    	userEmail.sendKeys(email);
    	userPassword.sendKeys(password);
    	submit.click();
    	ProductCatalogue productCatalogue=new ProductCatalogue(driver);
    	return productCatalogue;
    }
    
    public void goTo()
    {
    	driver.get("https://rahulshettyacademy.com/client");
    }
    
    public String getMeErrorMessage()
    {
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
    }
	}


