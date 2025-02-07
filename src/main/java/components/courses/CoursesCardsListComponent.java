package components.courses;

import annotations.ComponentBlueprint;
import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentBlueprint( rootLocator = "//div[contains(text(),'Каталог')]/ancestor::section" )
public class CoursesCardsListComponent extends AbstractComponent {
    public CoursesCardsListComponent(WebDriver driver) {
        super(driver);
    }


    private final By cardsInListXPath = By.xpath(".//a");
    private final By showMoreBtnXPath = By.xpath(".//button[contains(text(),'Показать')]");
    private final By searchLoader = By.xpath(".//label[contains(text(),'Поиск')]//following::div[1]//div");

    public List<WebElement> getCardsInList() {
        return getRootElement().findElements(cardsInListXPath);
    }
    public boolean getSearchLoader() {
        return standartWaiter.waitForElementNotVisible(
                getRootElement().findElement(searchLoader)
        );
    }

    public WebElement getShowMoreBtn() {
        if (standartWaiter.waitForElementLocatedAndVisible(500, showMoreBtnXPath))
            return getRootElement().findElement(showMoreBtnXPath);
        else return null;
    }

    public void clickShowMoreWhileUCan(){
        while (standartWaiter.waitToBeClickable(getShowMoreBtn())) {
            getShowMoreBtn().click();
        }
    }

}
