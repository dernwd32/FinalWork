package components;

import annotations.ComponentBlueprint;
import common.AbstractCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waiters.StandartWaiter;


public abstract class AbstractComponent extends AbstractCommon {
    protected By rootLocator = (By) getMetaValues("rootLocator");

    public AbstractComponent(WebDriver driver){
        super(driver);
    }

    public WebElement getRootElement() {
        standartWaiter.waitForElementLocatedAndVisible(rootLocator);
        return driver.findElement(rootLocator);
    }

    public By getByFromString(String someLocator) {
        String[] parsed = someLocator.split(">>>");

        //если не указан тип в строке, считаем, что это xpath
        if (parsed.length==1) return By.xpath(parsed[0]);

        switch (parsed[0]) {
            case "css" -> {
                return By.cssSelector(parsed[1]);
            }
            case "id" -> {
                return By.id(parsed[1]);
            }
            case "class" -> {
                return By.className(parsed[1]);
            }
            case "name" -> {
                return By.name(parsed[1]);
            }
            default -> {
                return By.xpath(parsed[1]);
            }
        }

    }

    public Object getMetaValues(String metaName) {
        Class clazz = this.getClass();
        if(clazz.isAnnotationPresent(ComponentBlueprint.class)) {
            ComponentBlueprint componentBlueprint = (ComponentBlueprint) clazz.getDeclaredAnnotation(ComponentBlueprint.class);
            switch (metaName){
                case "rootLocator" -> {
                    //standartWaiter.waitForElementVisible(driver.findElement(By.xpath(componentBlueprint.rootLocator())));
                    //return By.xpath(componentBlueprint.rootLocator());
                    return getByFromString(componentBlueprint.rootLocator());
                }
            }
        }
        return "";
    }


}
