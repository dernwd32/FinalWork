package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//@ComponentBlueprint( rootLocatorBy = "//p[contains(text(),'Направление')]/following::div[@class='ReactCollapse--content'][1]" )

@ComponentBlueprint( rootLocator = "//div[@class='ReactCollapse--collapse']//input[@type='checkbox']//ancestor::div[6]")
public class FilterMenuComponent extends AbstractComponent {
    public FilterMenuComponent(WebDriver driver) {
        super(driver);
    }

    private final String filterTitleXPath = ".//p[text()='%s']";
    private final String filterItemXPath = ".//following::label[text()='%s']";

    public WebElement getFilterTitle(String title) {
        return  getRootElement()
                .findElement(
                        By.xpath(
                                String.format(filterTitleXPath, title)));
    }

    public WebElement getFilterItem(String title, String item) {
        return getFilterTitle(title)
                .findElement(
                        By.xpath(
                                String.format(filterItemXPath, item)));
    }




}
