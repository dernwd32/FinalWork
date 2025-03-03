package components.courses;

import annotations.ComponentBlueprint;
import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CoursesRootPage;

import java.util.List;

@ComponentBlueprint( rootLocator = "//div[contains(text(),'Каталог')]/ancestor::section" )
public class CoursesCardsListComponent extends AbstractComponent {
    public CoursesCardsListComponent(WebDriver driver) {
        super(driver);
    }


    private final By cardsInListXPath = By.xpath(".//a");
    private final By showMoreBtnXPath = By.xpath(".//button[contains(text(),'Показать')]");
    private final By searchLoader = By.xpath(".//input[@type='search']//following::div[1]//div");
    private final By preloaderCard = By.xpath(".//a[not(@href)]");

    public List<WebElement> getCardsInList() {
        return getRootElement().findElements(cardsInListXPath);
    }
    public CoursesRootPage getSearchLoader() {
        //ожидание загрузки элементов каталога через лоадер внутри поиска
        standartWaiter.waitForElementNotVisible(
                getRootElement().findElement(searchLoader)
        );
        return new CoursesRootPage(driver);
    }

    public WebElement getShowMoreBtn() {
        if (standartWaiter.waitForElementLocatedAndVisible(500, showMoreBtnXPath))
            return getRootElement().findElement(showMoreBtnXPath);
        else return null;
    }


    public CoursesRootPage clickShowMoreWhileUCan(){
        while (standartWaiter.waitForElementLocatedAndVisible(showMoreBtnXPath)) {
            if ( standartWaiter.waitToBeClickable(getShowMoreBtn())
                    && !standartWaiter.waitForElementLocatedAndVisible(2000, preloaderCard))
                getShowMoreBtn().click();
        }
        return new CoursesRootPage(driver);
    }

}
