package core;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import pageobjects.homepage;

public class BaseTest extends Browser  {

	WebDriver driver; 
	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter spark;
	public static ExtentTest extentTest;
	public static ExtentTest node;
	public static String url;
	
	@Parameters({"environment"})
	@BeforeSuite
	public void reportSetup(String environment) {
		try {
		spark = new ExtentSparkReporter("reports/Spark.html");
		spark.config().setEncoding("utf-8");
		spark.config().setTheme(Theme.DARK);		
		extent.attachReporter(spark);

			Properties prop = readPropertiesFile("environment-"+environment+".properties");
			url = prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	@Parameters({"browser"})
	@BeforeClass
	public void setup(String browser) {
		setDriver(browser.toLowerCase());
	
	}	
	
	@AfterClass
	public void cleanup() {
		quitDriver();
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		try {
			if(result.getStatus()==ITestResult.FAILURE) {
				driver = getDriver();
					node = extentTest.createNode("FailedStep")
					.fail("Failed", MediaEntityBuilder.createScreenCaptureFromPath(takeSnapShot(driver)).build())
					.fail(MarkupHelper.createLabel(result.getName() + " Test Case Failed!!!", ExtentColor.RED));
	
			}
			else if (result.getStatus()==ITestResult.SUCCESS) {
				extentTest.pass(MarkupHelper.createLabel(result.getName() + " Test Case Passed!!!", ExtentColor.GREEN));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@AfterSuite() 
	public void tearDown() {
		extent.flush();		
	}
	
	public static String takeSnapShot(WebDriver driver) throws Exception{
        TakesScreenshot scrShot =(TakesScreenshot)driver;
        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/Screenshots/"+System.currentTimeMillis();
        File destFile=new File(path);
        FileUtils.copyFile(srcFile, destFile);
		return path;

    }
	
	   public static Properties readPropertiesFile(String fileName) throws IOException {
		      FileInputStream fis = null;
		      Properties prop = null;
		      try {
		         fis = new FileInputStream(fileName);
		         prop = new Properties();
		         prop.load(fis);
		      } catch(FileNotFoundException fnfe) {
		         fnfe.printStackTrace();
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      } finally {
		         fis.close();
		      }
		      return prop;
		   }

}
