package gen.selenium.page_object_model.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;


import gen.selenium.page_object_model.common.Init;
import gen.selenium.page_object_model.constants.Browser;
import gen.selenium.page_object_model.pageObjects.SearchPage;

public class DemoTest extends Init {

	public DemoTest() {
		super(Browser.GOOGLE_CHROME);
	}

	@Test
	public void search() {
		SearchPage page = PageFactory.initElements(driver, SearchPage.class);
		page.search("hello");
	}
}
