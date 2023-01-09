package AnjanaJohny.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AnjanaJohny.PageObjects.Cartpage;
import AnjanaJohny.PageObjects.Checkoutpage;
import AnjanaJohny.PageObjects.Confirmationpage;
import AnjanaJohny.PageObjects.LandingPage;
import AnjanaJohny.PageObjects.OrderPage;
import AnjanaJohny.PageObjects.ProductCatalogue;
import AnjanaJohny.TestComponents.Basetest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Submitorder extends Basetest{
	
	String productName=	"ZARA COAT 3";
		@Test(dataProvider="getData", groups= {"Purchase"})
		public void Standalonetest(HashMap<String,String> input) throws IOException, InterruptedException
		
		{

ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
List<WebElement> products=productCatalogue.getProductList();
productCatalogue.addProductToCart(input.get("product"));
Cartpage cartPage=productCatalogue.goToCartPage();
Boolean match=cartPage.verifyProductDisplay(input.get("product"));
Assert.assertTrue(match);
Checkoutpage CheckoutPage=cartPage.goToCheckout();
CheckoutPage.selectCountry("India");
CheckoutPage.submitOrder();
Confirmationpage confirmation=CheckoutPage.submitOrder();
String message=confirmation.verifyText();
Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
}
		
		@Test(dependsOnMethods= {"Standalonetest"})
		public void OrderHistory()
		{
			ProductCatalogue productCatalogue=landingPage.loginApplication("amal@gmail.com","AmalJohn1@");
			OrderPage orderPage=productCatalogue.goToOrdersPage();
			Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}
		
		@DataProvider
		public Object[][] getData() throws IOException 
		{
			/*HashMap<String,String> map =new HashMap<String, String>();
			map.put("email","amal@gmail.com");
			map.put("password","AmalJohn1@");
			map.put("product","ZARA COAT 3");
			HashMap<String,String> map1 =new HashMap<String, String>();
			map1.put("email","jacob123@gmail.com");
			map1.put("password","Jacob12345!");
			map1.put("product","ADIDAS ORIGINAL");
			return new Object[][] {{map},{map1}};*/

			List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AnjanaJohny\\Data\\PurchaseOrder.json") ;
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
		//@DataProvider
		//public Object[][] getData()
		//{
		//return new Object[][] {{"amal@gmail.com","AmalJohn1@","ZARA COAT 3"},{"jacob123@gmail.com","Jacob12345!","ADIDAS ORIGINAL"}};
			
		//}
		//HashMap<String,String> map =new HashMap<String, String>();
		//map.put("email","amal@gmail.com");
		//map.put("password","AmalJohn1@");
		//map.put("product","ZARA COAT 3");
		//HashMap<String,String> map1 =new HashMap<String, String>();
		//map1.put("email","jacob123@gmail.com");
		//map1.put("password","Jacob12345!");
		//map1.put("product","ADIDAS ORIGINAL");

}
