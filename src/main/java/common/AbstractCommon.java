package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waiters.StandartWaiter;

import java.util.ArrayList;

public abstract class AbstractCommon {

    protected WebDriver driver = null;
    protected StandartWaiter standartWaiter = null;

    protected AbstractCommon(WebDriver driver){
        this.driver = driver;
        this.standartWaiter = new StandartWaiter(driver);
    }

    public void killFilthyPopups(){
        final By bannerCloseBtnClass = By.className("js-sticky-banner-close");
        final By cookieCloseBtnXPath = By.xpath("//a[contains(@href, '/legal/cookie')]//following::button[1]");

        ArrayList<WebElement> popups = new ArrayList<>();
        if (standartWaiter.waitForElementLocatedAndVisible(500, bannerCloseBtnClass))
            popups.add(driver.findElement(bannerCloseBtnClass));
        if (standartWaiter.waitForElementLocatedAndVisible(500, cookieCloseBtnXPath))
            popups.add(driver.findElement(cookieCloseBtnXPath));

        popups.forEach(element -> {
            if (standartWaiter.waitToBeClickable(element))
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        });

    }
}


