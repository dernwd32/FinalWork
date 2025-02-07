package components.courses;

import annotations.ComponentBlueprint;
import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentBlueprint( rootLocator = "//p[contains(text(),'Направление')]/following::div[@class='ReactCollapse--content'][1]" )
public class CoursesTypesMenuComponent extends AbstractComponent {
    public CoursesTypesMenuComponent(WebDriver driver) {
        super(driver);
    }

    private final By labelTestingXPath =
            By.xpath(".//label[contains(text(),'Тестирование')]");
    private final By spanHideXPath =
            By.xpath(".//span[contains(text(),'Свернуть')]");

    public WebElement getLabelTesting() {
        return getRootElement().findElement(labelTestingXPath);
    }

    public WebElement getSpanHide() {
        return getRootElement().findElement(spanHideXPath);
    }


}
