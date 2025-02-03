package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentBlueprint( rootLocator = "//div[contains(text(),'Каталог')]/ancestor::section" )
public class CoursesCardsListComponent extends AbstractComponent{
    public CoursesCardsListComponent(WebDriver driver) {
        super(driver);
    }


//div[contains(text(),'Каталог')]/following::a[starts-with(@href, '/less')]
    private final By cardsInListXPath = By.xpath(".//a");
    private final By showMoreBtnXPath = By.xpath(".//button[contains(text(),'Показать')]");
    private final By searchLoader = By.xpath(".//label[contains(text(),'Поиск')]//following::div[1]//div");
    //div[contains(text(),'Каталог')]/ancestor::section//label[contains(text(),'Поиск')]//following::div[1]//div
    //label[contains(text(),'Поиск')]//following::*[local-name()='circle' and @cx='11.767']




    public List<WebElement> getCardsInList() {
        return getRootElement().findElements(cardsInListXPath);
    }
    public boolean getSearchLoader() {
        //return getRootElement().findElement(searchLoader);
        return standartWaiter.waitForElementNotVisible(
                getRootElement().findElement(searchLoader)
        );
    }

    public WebElement getShowMoreBtn() {
        if (standartWaiter.waitForElementLocatedAndVisible(showMoreBtnXPath))
            return getRootElement().findElement(showMoreBtnXPath);
        else return null;
    }

    public void clickShowMoreWhileUCan(){

//        WebElement showBtn = getRootElement().findElement(showMoreBtnXPath);
//        builder.moveToElement(showBtn).click().perform();
//        WebElement showBtn = driver.findElement(showMoreBtnXPath);
//        //jsExecutor.executeScript("arguments[0].scrollIntoView();", showBtn);
        while (standartWaiter.waitToBeClickable(getShowMoreBtn())) {
            getShowMoreBtn().click();
        }
    }

}
