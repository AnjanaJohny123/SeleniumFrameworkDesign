package AnjanaJohny.TestComponents;

import java.io.IOException;

import org.apache.log4j.helpers.ThreadLocalMap;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AnjanaJohny.resources.ExtentReporterNG;

public class Listeners extends Basetest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest>extendTest= new ThreadLocal<ExtentTest>();//Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extendTest.set(test); //unique thread id
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extendTest.get().log(Status.PASS, "Passed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		extendTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		} 
		String filePath=null;
		
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extendTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	

}
