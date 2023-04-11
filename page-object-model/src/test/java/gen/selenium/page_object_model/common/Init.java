package gen.selenium.page_object_model.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import gen.selenium.page_object_model.constants.Browser;
import gen.selenium.page_object_model.constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;

/**
 * This class is used to initialize different browsers.
 *
 * @author kamlesh
 *
 */
@Log4j2
public class Init {

	private Browser browerName = null;

	public static WebDriver driver;

	public Init(final Browser browerName) {
		this.browerName = browerName;
	}

	/**
	 * TODO: method to initialize browser
	 */
	@BeforeSuite
	public void start() {
		if (Browser.GOOGLE_CHROME.equals(browerName)) {
			log.info("Initializing chrome browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Browser.EDGE.equals(browerName)) {
			log.info("Initializing edge browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (Browser.MOZILLA_FIREFOX.equals(browerName)) {
			log.info("Initializing firefox browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			/* initialize default browser IE */
			log.error("browsername does not match with valid values");
		}
		//
		driver.manage().window().maximize();
		driver.get(Constants.LANDING_PAGE);
	}

	@AfterSuite
	public void stop() {
		driver.close();
		driver.quit();
	}
}
