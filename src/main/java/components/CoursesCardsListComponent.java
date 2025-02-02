package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ComponentBlueprint( rootLocator = "//div[contains(text(),'Каталог')]/ancestor::section" )
public class CoursesCardsListComponent extends AbstractComponent{
    public CoursesCardsListComponent(WebDriver driver) {
        super(driver);
    }
    protected JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
    Actions builder = new Actions(driver);

//div[contains(text(),'Каталог')]/following::a[starts-with(@href, '/less')]
    private final By cardsInListXPath = By.xpath(".//a");
    private final By showMoreBtnXPath = By.xpath(".//button[contains(text(),'Показать')]");
//    private final By bannerCloseBtnClass = By.className("js-sticky-banner-close");
//    private final By cookieCloseBtnXPath = By.xpath("//a[contains(text(),'cookie-файл')]/following::button[1]"); // //ancestor::div[1]

    public void killFilthyPopups(){

//
//        Actions builder = new Actions(driver);
//
//        WebElement[] popups = {
//                getRootElement().findElement(bannerCloseBtnClass),
//                getRootElement().findElement(cookieCloseBtnXPath),
//        };
//        Arrays.stream(popups).forEach(element -> {
//            if (standartWaiter.waitToBeClickable(element)) {
//                System.out.println(element);
//               // jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
//               // jsExecutor.executeScript("arguments[0].click();", element);
//               // builder.moveToElement(element).click().perform();
//            }
//        });
//

    }


    public List<WebElement> cardsInList() {
        if(standartWaiter.waitForElementVisible(driver.findElement(cardsInListXPath)))
            return getRootElement().findElements(cardsInListXPath);
        else return List.of();
    }

    public Set<String> hrefsOfCardsInList() {
        Set<String> hrefs = new HashSet<>();
        cardsInList().forEach(
                card -> {
                    hrefs.add(card.getAttribute("href"));
                    System.out.println(card.getAttribute("href"));
                }
        );
        System.out.println();
        return hrefs;


    }

    public int countCardsInList() {
        return cardsInList().size();
    }

    public void clickEachCardIfItsCorrect() {

    }



    public void clickShowMoreWhileUCan(){
//        System.out.println("блять");
//        WebElement showBtn = getRootElement().findElement(showMoreBtnXPath);
//        builder.moveToElement(showBtn).click().perform();
//        System.out.println("блять");
//        WebElement showBtn = driver.findElement(showMoreBtnXPath);
//        //jsExecutor.executeScript("arguments[0].scrollIntoView();", showBtn);
//        while (standartWaiter.waitToBeClickable(showBtn)) {
//            showBtn.click();
//            //if (standartWaiter.waitForElementNotVisible(showBtn)) break;
//        }
    }

}
