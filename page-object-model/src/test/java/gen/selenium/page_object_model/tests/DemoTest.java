package gen.selenium.page_object_model.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import gen.selenium.page_object_model.common.Init;
import gen.selenium.page_object_model.constants.Browser;
import gen.selenium.page_object_model.pageObjects.SearchPage;
import gen.selenium.page_object_model.pageObjects.SearchResultPage;

public class DemoTest extends Init {

	public DemoTest() {
		super(Browser.GOOGLE_CHROME);
	}

	@Test
	public void search() {
		SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
		SearchResultPage searchResultPage = searchPage.search("hello");
		assertTrue(driver.getTitle().contains("hello"), "Title does not contain search keyword");
		assertTrue(driver.getPageSource().contains("hello"), "Page does not contain search keyword");
	}
}
