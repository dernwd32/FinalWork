package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waiters.StandartWaiter;


public abstract class AbstractComponent {
    protected WebDriver driver = null;
    protected StandartWaiter standartWaiter = null;
    protected By rootLocator = (By) getMetaValues("rootLocator");


    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
    }

    public WebElement getRootElement() {
        if( standartWaiter.waitForElementVisible(driver.findElement(rootLocator)) ) {
            return driver.findElement(rootLocator);
        }
        return null;
    }

    public Object getMetaValues(String metaName) {
        Class clazz = this.getClass();
        if(clazz.isAnnotationPresent(ComponentBlueprint.class)) {
            ComponentBlueprint componentBlueprint = (ComponentBlueprint) clazz.getDeclaredAnnotation(ComponentBlueprint.class);
            switch (metaName){
                case "rootLocator" -> {
                    //standartWaiter.waitForElementVisible(driver.findElement(By.xpath(componentBlueprint.rootLocator())));
                    return By.xpath(componentBlueprint.rootLocator());
                }
            }
        }
        return "";
    }

}
