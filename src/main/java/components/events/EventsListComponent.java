package components.events;

import annotations.ComponentBlueprint;
import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@ComponentBlueprint( rootLocator = "//section[@class='dod_new-events']")
public class EventsListComponent extends AbstractComponent {
    public EventsListComponent(WebDriver driver) {
        super(driver);
    }

    private final By eventTypesDropdownClass = By.xpath("(.//span[contains(@class,'dod_new-events-dropdown__input')])[1]");
    private final By chooseEventFilterClass = By.xpath("(.//div[contains(@class,'dod_new-events-dropdown__list')])[1]//a");
    private final By loaderClass = By.className("dod_new-loader-wrapper");
    private final By loaderVisibleClass = By.className("dod_new-loader-wrapper_visible");
    private final By divEventCardClass = By.className("dod_new-event-content");
    private final By spanEventDateXPath = By.xpath(".//span[@class='dod_new-event__icon dod_new-event__calendar-icon']//following::span[1]");
    private final By eventCardTitlesClass = By.className("dod_new-type__text");

    public WebElement getLoader() {
        return getRootElement().findElement(loaderClass);
    }
    public List<WebElement> getSpansWithEventDates() {
        return getRootElement().findElements(spanEventDateXPath);
    }

    public WebElement getEventTypesDropdown() {
        return getRootElement().findElement(eventTypesDropdownClass);
    }

    public List<WebElement> getChooseEventFilter() {
        return getRootElement().findElements(chooseEventFilterClass);
    }

    public List<WebElement> getEventCardTitles() {
        return getRootElement().findElements(eventCardTitlesClass);
    }

}
