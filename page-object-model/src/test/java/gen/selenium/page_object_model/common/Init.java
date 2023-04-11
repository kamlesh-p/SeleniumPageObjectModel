package gen.selenium.page_object_model.common;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import gen.selenium.page_object_model.constants.Browser;
import gen.selenium.page_object_model.constants.Constants;
import gen.selenium.pom.listner.TestListner;
import gen.selenium.pom.report.ExtentReportManager;
import gen.selenium.pom.report.LoggerManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;

/**
 * This class is used to initialize different browsers.
 *
 * @author kamlesh
 *
 */
@Log4j2
@Listeners({TestListner.class})
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
		ExtentReportManager.startTest(this.getClass().getSimpleName());
		if (Browser.GOOGLE_CHROME.equals(browerName)) {
			LoggerManager.log(Status.INFO, log, "Initializing chrome browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Browser.EDGE.equals(browerName)) {
			LoggerManager.log(Status.INFO, log, "Initializing edge browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (Browser.MOZILLA_FIREFOX.equals(browerName)) {
			LoggerManager.log(Status.INFO, log, "Initializing firefox browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			/* initialize default browser IE */
			LoggerManager.log(Status.FAIL, log, "browsername does not match with valid values");
		}
		//
		driver.manage().window().maximize();
		driver.get(Constants.LANDING_PAGE);
	}

	@BeforeMethod
	public void beforeMethod(final Method method) {
		String desc = method.getAnnotation(Test.class).description();
		// start extent test.
		ExtentReportManager.startTest(this.getClass().getSimpleName() + "-" + method.getName(), desc);
	}

	@AfterClass
	public void afterMethod() {
		// write extent test to report.
		ExtentReportManager.endTest();
	}

	@AfterSuite
	public void stop() {
		driver.close();
		driver.quit();
		log.info("===== ===== ===== =====");

	}
}
