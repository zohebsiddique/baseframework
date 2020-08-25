package core;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Browser {
	public WebDriver driver;
    
    public void setDriver(String browser) {
//		String browser = System.getenv("Browser").toLowerCase();
        System.setProperty("headless", "false"); // You can set this property elsewhere
        boolean headless = Boolean.parseBoolean(System.getProperty("headless"));

        if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();  
			options.setHeadless(headless); //Set Chrome option
			driver = new ChromeDriver(options);
			
		} 
		else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "driver/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}
        driver.manage().window().maximize();
		
    }

    public void quitDriver() {
        if(null != driver) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }	

}
