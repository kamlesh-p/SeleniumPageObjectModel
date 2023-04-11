package gen.selenium.pom.listner;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import gen.selenium.pom.report.ExtentReportManager;
import gen.selenium.pom.report.LoggerManager;

public class TestListner implements ITestListener {

	private static final Logger log = LogManager.getLogger(TestListner.class);
	private static final String PRE_START_TAG = "<pre>";
	private static final String PRE_END_TAG = "</pre>";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFinish(final ITestContext iTestContext) {
		ExtentReportManager.getInstance().flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStart(final ITestContext iTestContext) {
		ExtentReportManager.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(final ITestResult iTestResult) {
		// not implemented
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onTestFailure(final ITestResult iTestResult) {
		log.error(iTestResult.getThrowable());
		// log formatted stacktrace
		ExtentReportManager.getTest().log(Status.FAIL,
				"Error: " + PRE_START_TAG + formatStackTrace(iTestResult.getThrowable()) + PRE_END_TAG);
		// log using throwable to track exceptions in report.
		ExtentReportManager.getTest().log(Status.FAIL, iTestResult.getThrowable());
		LoggerManager.log(Status.FAIL, log, "Test Failed - " + iTestResult.getName());

	}

	/**
	 * format stacktrace from throwable.
	 *
	 * @param throwable
	 * @return String
	 */
	private String formatStackTrace(final Throwable throwable) {
		String stackTrace = ExceptionUtils.getStackTrace(throwable);
		if (StringUtils.isNotEmpty(stackTrace) && stackTrace.contains("gen.selenium")) {
			try {
				String pattern = "(.*)at gen.selenium(.*)";
				stackTrace = stackTrace.replaceAll(pattern, "<span style='color:blue'>$0</span>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stackTrace;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onTestSkipped(final ITestResult iTestResult) {
		log.error(iTestResult.getThrowable());
		// log formatted stacktrace
		ExtentReportManager.getTest().log(Status.SKIP,
				"Error: " + PRE_START_TAG + formatStackTrace(iTestResult.getThrowable()) + PRE_END_TAG);
		// log using throwable to track exceptions in report.
		ExtentReportManager.getTest().log(Status.SKIP, iTestResult.getThrowable());
		LoggerManager.log(Status.SKIP, log, "Test skipped - " + iTestResult.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onTestStart(final ITestResult iTestResult) {
		// Log
		LoggerManager.log(Status.INFO, log, "Test Started - " + iTestResult.getName());
		// test parameters
		Object[] parameters = iTestResult.getParameters();
		if (ArrayUtils.isNotEmpty(parameters)) {
			LoggerManager.log(Status.INFO, log,
					"Test Data: " + PRE_START_TAG + Arrays.toString(parameters) + PRE_END_TAG);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onTestSuccess(final ITestResult iTestResult) {
		LoggerManager.log(Status.PASS, log, "Test completed successfully.");
	}

}
