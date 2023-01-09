package AnjanaJohny.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AnjanaJohny.PageObjects.Cartpage;
import AnjanaJohny.PageObjects.Checkoutpage;
import AnjanaJohny.PageObjects.Confirmationpage;
import AnjanaJohny.PageObjects.LandingPage;
import AnjanaJohny.PageObjects.ProductCatalogue;
import AnjanaJohny.TestComponents.Basetest;
import AnjanaJohny.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends Basetest{

	
		@Test(groups= {"Errorhandling"})
		public void loginErrorValidation() throws IOException, InterruptedException
		
		{

landingPage.loginApplication("amal@gmail.com","amlJohn1@");
Assert.assertEquals("Incorrect email or password.",landingPage.getMeErrorMessage());
}
		
		@Test(retryAnalyzer=Retry.class)
		public void productErrorValidation() throws IOException, InterruptedException
		
		{
String productName=	"ZARA COAT 3";
ProductCatalogue productCatalogue=landingPage.loginApplication("amal@gmail.com","AmalJohn1@");
List<WebElement> products=productCatalogue.getProductList();
productCatalogue.addProductToCart(productName);
Cartpage cartPage=productCatalogue.goToCartPage();
Boolean match=cartPage.verifyProductDisplay(productName);
Assert.assertTrue(match);

}

}
