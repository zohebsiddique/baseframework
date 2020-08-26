package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import controllers.ExcelDataProvider;
import core.Browser;
import core.BaseTest;
import pageobjects.homepage;

public class homepagetest extends BaseTest  {

	WebDriver driver; 
	
	@Test
    public void homepageTests() throws InterruptedException {
		try {
		extentTest = extent.createTest("MyFirstTest");
		driver = getDriver();
		driver.get(url);
//		extentTest.log(Status.PASS, "This is a logging event for homepageTests, and it passed!");
		assertEquals(40,40);
		}
		catch(Exception e){
			System.out.println("Something went wrong");
		}
    }


}
