package pages;

import annotations.ComponentBlueprint;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import waiters.StandartWaiter;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractPage {
    String BASE_URL = System.getProperty("base.url");

    abstract void openPage();

    protected WebDriver driver = null;
    protected StandartWaiter standartWaiter = null;


    private final By bannerCloseBtnClass = By.className("js-sticky-banner-close");
    private final By cookieCloseBtnXPath = By.xpath("//a[contains(@href, '/legal/cookie')]//following::button[1]"); // //ancestor::div[1]

//    public WebElement getBannerCloseBtn() {
//        return driver.findElement(bannerCloseBtnClass);
//    }
//
//    public WebElement getCookieCloseBtn() {
//        return driver.findElement(cookieCloseBtnXPath);
//    }

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
    }

    public void killFilthyPopups(){

        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        Actions builder = new Actions(driver);

        ArrayList<WebElement> popups = new ArrayList<>();

        if (standartWaiter.waitForElementLocatedAndVisible(bannerCloseBtnClass))
           popups.add(driver.findElement(bannerCloseBtnClass));
        if (standartWaiter.waitForElementLocatedAndVisible(cookieCloseBtnXPath))
           popups.add(driver.findElement(cookieCloseBtnXPath));

        popups.forEach(element -> {
            if (standartWaiter.waitToBeClickable(element)) {
               //element.click();
                //System.out.println(element);
               // jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
                //System.out.println(element);
                jsExecutor.executeScript("arguments[0].click();", element);
               // builder.moveToElement(element).click().perform();
            }
        });


    }
}
