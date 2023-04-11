package gen.selenium.pom.report;

import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public final class LoggerManager {

	private LoggerManager() {
		// No init.
	}

	/**
	 * Log message to console as well as Extent report.
	 *
	 * @param status
	 * @param log
	 * @param logMessage
	 */
	public static void log(final Status status, final Logger log, final String logMessage) {
		switch (status) {
			case WARNING :
				log.warn(logMessage);
				ExtentReportManager.getTest().log(status, logMessage);
				break;
			case FAIL :
				log.error(logMessage);
				ExtentReportManager.getTest().log(status, logMessage);
				break;
			default :
				log.info(logMessage);
				ExtentReportManager.getTest().log(status, logMessage);
				break;
		}
	}

	/**
	 * Log message to console as well as Extent report as JSON code block.
	 * NOTE: uses log.info for console logging.
	 *
	 * @param logStatus
	 * @param log
	 * @param logMessage
	 */
	public static void logCodeBlock(final Status logStatus, final Logger log, final String logMessage) {
		log.info(logMessage);
		ExtentReportManager.getTest().log(logStatus, MarkupHelper.createCodeBlock(logMessage, CodeLanguage.JSON));
	}
}
