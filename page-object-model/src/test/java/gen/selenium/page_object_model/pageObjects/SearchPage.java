package gen.selenium.page_object_model.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {

	@FindBy(name = "q")
	private WebElement searchBar;

	@FindBy(name = "btnK")
	private WebElement searchButton;

	public void search(String text) {
		searchBar.clear();
		searchBar.sendKeys(text);
		searchBar.sendKeys(Keys.ENTER);
	}
}
