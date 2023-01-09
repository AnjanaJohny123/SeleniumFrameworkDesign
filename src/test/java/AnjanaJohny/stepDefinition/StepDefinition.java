package AnjanaJohny.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AnjanaJohny.PageObjects.Cartpage;
import AnjanaJohny.PageObjects.Checkoutpage;
import AnjanaJohny.PageObjects.Confirmationpage;
import AnjanaJohny.PageObjects.LandingPage;
import AnjanaJohny.PageObjects.ProductCatalogue;
import AnjanaJohny.TestComponents.Basetest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends Basetest {
public LandingPage landingPage;
public ProductCatalogue productCatalogue;
public Cartpage cartPage;
public Checkoutpage CheckoutPage;
public Confirmationpage confirmation;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	 @Given("^Logged in with username (.+) and password(.+)$")
	    public void logged_in_with_username_and_password(String name, String password) throws Throwable {
		 productCatalogue=landingPage.loginApplication(name,password);
	    }

	    @When("^I add the product (.+)  to cart$")
	    public void i_add_the_product_to_cart(String productname) throws Throwable {
	    	List<WebElement> products=productCatalogue.getProductList();
			productCatalogue.addProductToCart(productname);
	    }

	    @Then("^\"([^\"]*)\" message is displayed on confirmation page$")
	    public void something_message_is_displayed_on_confirmation_page(String strArg1) throws Throwable {
	    	String message=confirmation.verifyText();
			 Assert.assertTrue(message.equalsIgnoreCase(strArg1));
			 driver.close();
	    }

	    @And("^checkout (.+) and submit the order$")
	    public void checkout_and_submit_the_order(String productname) throws Throwable {
	    	cartPage=productCatalogue.goToCartPage();
			 Boolean match=cartPage.verifyProductDisplay(productname);
			 Assert.assertTrue(match);
			 CheckoutPage=cartPage.goToCheckout();
			 CheckoutPage.selectCountry("India");
			 CheckoutPage.submitOrder();
			 confirmation=CheckoutPage.submitOrder();
	    }
	    @Then("^\"([^\"]*)\" message is displayed$")
	    public void something_message_is_displayed(String strArg1) throws Throwable {
	    	Assert.assertEquals(strArg1,landingPage.getMeErrorMessage());
	    	driver.close();
	    }


}
