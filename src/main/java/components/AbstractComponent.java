package components;

import annotations.ComponentBlueprint;
import common.AbstractCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public abstract class AbstractComponent extends AbstractCommon {
    protected By rootLocatorBy = (By) getMetaValues("rootLocatorBy");
    protected String rootLocatorString = (String) getMetaValues("rootLocatorString");

    protected AbstractComponent(WebDriver driver){
        super(driver);
    }

    public String getRootLocatorString() {
        return rootLocatorString;
    }
    public By getRootLocatorBy() {
        return rootLocatorBy;
    }




    public WebElement getRootElement() {
        standartWaiter.waitForElementLocatedAndVisible(rootLocatorBy);
        return driver.findElement(rootLocatorBy);
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
                case "rootLocatorBy" -> {
                    return getByFromString(componentBlueprint.rootLocator());
                }
                case "rootLocatorString" -> {
                    return componentBlueprint.rootLocator();
                }
            }
        }
        return "";
    }


}
