package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentBlueprint( rootLocator = "section.dod_new-events")
public class EventsListComponent extends AbstractComponent{
    public EventsListComponent(WebDriver driver) {
        super(driver);
    }

    private final By chooseEventDropdownHeaderClass = By.className("dod_new-events-dropdown__input");
    private final By chooseEventItemsClass = By.className("dod_new-events-dropdown__list-item");
    private final By loaderClass = By.className("dod_new-loader-wrapper");
    private final By loaderVisibleClass = By.className("dod_new-loader-wrapper_visible");

    public WebElement getLoader() {
        return driver.findElement(loaderClass);
    }
}
