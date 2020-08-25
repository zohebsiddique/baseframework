package tests;


import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import controllers.ExcelDataProvider;
import core.BaseTest;
import pageobjects.homepage;
import utils.ExcelUtils;

public class login extends BaseTest{
	WebDriver driver; 
	
	@Test
    public void login() throws InterruptedException {
		extentTest = extent.createTest("MySecondTest");		
		this.driver = getDriver();
		driver.get("http://www.youtube.com");
		extentTest.log(Status.PASS, "This is a logging event for loginpageTests, and it passed!");
	}
	
	
	@Test(dataProvider="excelSheetNameAsMethodName",dataProviderClass=ExcelDataProvider.class)
    public void logins(String userName, String password) throws InterruptedException {
		try {
		extentTest = extent.createTest("MyThirdTest");		
		System.out.println("userName is : " + userName);
		System.out.println("password is : " + password);		
		System.out.println(ExcelUtils.readAllData("testdata/logins.xlsx", "Sheet1"));
		List<Map<String, String>> readAllData = ExcelUtils.readAllData("testdata/logins.xlsx", "Sheet1"); 
		for(Map<String, String> map : readAllData) {
			System.out.println("userName is : " + map.get("UserName"));
			System.out.println("password is : " + map.get("Password"));			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		extentTest.log(Status.PASS, "This is a logging event for loginpageTests, and it passed!");
	}
	

}
