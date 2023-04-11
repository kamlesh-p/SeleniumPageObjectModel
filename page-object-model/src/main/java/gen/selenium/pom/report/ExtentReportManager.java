package gen.selenium.pom.report;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent = null;

	static Map<Long, ExtentTest> extentTestMap = new HashMap<>();

	private ExtentReportManager() {
		// No init
	}

	/**
	 * Get Instance of ExtentReports.
	 *
	 * @return ExtentReports
	 */
	public static synchronized ExtentReports getInstance() {
		if (extent == null) {
			String workingDir = System.getProperty("user.dir");
			String dateTime = LocalDateTime.now().toString().replaceAll("[-.:]", "_").substring(0, 19);
			// initialize SparkReporter
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
					workingDir + "/extentReport/report_" + dateTime + ".html");
			htmlReporter.config().setDocumentTitle("Automation Report");
			htmlReporter.config().setReportName("API Test report");
			// updating css for JSON string in report.
			htmlReporter.config().setCss(".jstStr{color:darkgreen}");

			// initialize ExtentReports and add reporter.
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			return extent;
		}
		return extent;

	}

	/**
	 * Start Extent Test.
	 *
	 * @param name
	 * @param desc
	 * @return ExtentTest
	 */
	public static synchronized ExtentTest startTest(final String name, final String desc) {
		// initialize report if null
		if (extent == null) {
			getInstance();
		}
		// create new test instance with name.
		ExtentTest test = extent.createTest(name, desc);
		extentTestMap.put(Thread.currentThread().getId(), test);
		return test;
	}

	/**
	 * Start Extent Test.
	 *
	 * @param name
	 * @return ExtentTest
	 */
	public static synchronized ExtentTest startTest(final String name) {
		return startTest(name, null);
	}

	/**
	 * Get ExtentTest instance.
	 *
	 * @return ExtentTest
	 */
	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(Thread.currentThread().getId());
	}

	/**
	 * End Extent Test.
	 */
	public static synchronized void endTest() {
		if (extent != null) {
			extent.flush();
		}
	}

}
