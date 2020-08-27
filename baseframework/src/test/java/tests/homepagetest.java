package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	
	@Test
    public void getElements() throws Exception {
		driver = getDriver();
		driver.get(url);
		extentTest = extent.createTest("MyFirstTest");		
//		Thread.sleep(5000);
//		List<WebElement> el = driver.findElements(By.cssSelector("*"));
//		List<String> el1 = new ArrayList();
//
//		for ( WebElement e : el ) {
//			if(e.getTagName().equals("a")) {
//				System.out.println(e.getTagName() +" "+ e.getText());
//			}	
//		}
	
		//		String s = JOptionPane.showInputDialog("Enter name: ");
    }

}
