package gen.selenium.page_object_model.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import gen.selenium.page_object_model.constants.Browser;
import gen.selenium.page_object_model.constants.Constants;

/**
 * This class is used to initialize different browsers.
 * 
 * @author kamlesh
 *
 */
public class Init {

	private Browser browerName = null;

	public static WebDriver driver;

	public Init(Browser browerName) {
		this.browerName = browerName;
	}

	/**
	 * TODO: method to initialize browser
	 */
	@BeforeSuite
	public void start() {
		if (Browser.GOOGLE_CHROME.equals(browerName)) {

			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(Constants.LANDING_PAGE);
		} else if (Browser.EDGE.equals(browerName)) {

		} else if (Browser.MOZILLA_FIREFOX.equals(browerName)) {

		} else {
			/* initialize default browser IE */

		}
	}

	@AfterSuite
	public void stop() {
		driver.close();
		driver.quit();
	}
}
