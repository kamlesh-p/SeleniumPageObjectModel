package gen.selenium.page_object_model.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import gen.selenium.pom.report.LoggerManager;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchPage {

	private WebDriver driver;

	@FindBy(name = "q")
	private WebElement searchBar;

	@FindBy(name = "btnK")
	private WebElement searchButton;

	/**
	 *
	 */
	public SearchPage(final WebDriver driver) {
		this.driver = driver;
	}

	public SearchResultPage search(final String text) {
		LoggerManager.log(Status.INFO, log, "Search for text: " + text);
		searchBar.clear();
		searchBar.sendKeys(text);
		searchBar.sendKeys(Keys.ENTER);
		return PageFactory.initElements(driver, SearchResultPage.class);
	}
}
