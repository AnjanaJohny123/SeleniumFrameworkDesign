package AnjanaJohny.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AnjanaJohny.AbstractComponents.Abstractcomponents;

public class Cartpage extends Abstractcomponents {

	WebDriver driver;
	public Cartpage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy (css=".totalRow button")
	private WebElement checkoutbutton;
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(s->s.getText().contains(productName));
		return match;
	}
	
	public Checkoutpage goToCheckout()
	{
		
		checkoutbutton.click();
		Checkoutpage CheckoutPage=new Checkoutpage(driver);
		return CheckoutPage;
	    
		
		
	}
}
