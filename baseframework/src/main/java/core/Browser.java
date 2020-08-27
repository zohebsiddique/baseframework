package core;



import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.browserstack.local.Local;

public class Browser {
	protected ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	DesiredCapabilities caps = new DesiredCapabilities();
	public static final String USERNAME = "zohebsiddique2";
	public static final String AUTOMATE_KEY = "uezgd4qFfyFsPuqJqan7";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	protected Local bsLocal;
	public static Map<String, String> bsLocalArgs = new HashMap<String, String>();   
	protected String platforms;

	
	public Browser() {
		bsLocal =  new Local();
		bsLocalArgs = new HashMap<String, String>();   
    	bsLocalArgs.put("key", AUTOMATE_KEY);  

	}
   
   
    public void setDriver(String browser, String platform, String os) {
	
  
    	try {
			bsLocal.start(bsLocalArgs);
					
	        System.setProperty("headless", "false"); // You can set this property elsewhere
	        boolean headless = Boolean.parseBoolean(System.getProperty("headless"));
	        platforms = platform; 

        if(os.equals("windows") && platform.equals("jenkins")) {
	        if(browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();  
				options.setHeadless(headless); //Set Chrome option
				driver.set(new ChromeDriver(options));
				
			} 
			else if(browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
				driver.set(new FirefoxDriver());
			}
			else if(browser.equals("edge")) {
				System.setProperty("webdriver.edge.driver", "driver/MicrosoftWebDriver.exe");
				driver.set(new EdgeDriver());
			}
        }
        else if(os.equals("windows") && platform.equals("browserstack")) {
	        if(browser.equals("edge")) {
	        	try {
		        	caps.setCapability("os", os);
		        	caps.setCapability("os_version", "10");
		        	caps.setCapability("browser", browser);
		        	caps.setCapability("browser_version", "81");
		        	caps.setCapability("name", "baseframework test");
		        	driver.set(new RemoteWebDriver(new java.net.URL(URL), caps));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
	        }
        }
        else if(os.equals("os x") &&  platform.equals("browserstack")){
	        if(browser.equals("safari")) {        	
        	try {        	
	        	caps.setCapability("os", os);
	        	caps.setCapability("os_version", "Catalina");
	        	caps.setCapability("browser", browser);
	        	caps.setCapability("browser_version", "13");
	        	caps.setCapability("name", "baseframework test");	
	        	driver.set(new RemoteWebDriver(new java.net.URL(URL), caps));    
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}        	
	        }
        }
		else {
			System.out.println("Browser combination incorrect. Running test on Windows Chrome");
			System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");			
			driver.set(new ChromeDriver());
		}
        driver.get().manage().window().maximize();
		
    	} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
    }

    public void quitDriver() {
		try {        if(null != driver) {
        	driver.get().quit();
        	driver.remove();
	        	if(platforms.equals("browserstack")) {
					bsLocal.stop();
        		}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	        	
    }

    public WebDriver getDriver() {
        return driver.get();
    }	

}
