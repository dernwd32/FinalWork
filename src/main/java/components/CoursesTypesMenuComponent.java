package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@ComponentBlueprint( rootLocator = "//p[contains(text(),'Направление')]/following::div[@class='ReactCollapse--content'][1]" )
public class CoursesTypesMenuComponent extends AbstractComponent{
    public CoursesTypesMenuComponent(WebDriver driver) {
        super(driver);
    }

    private final By labelTestingXPath =
            By.xpath(".//label[contains(text(),'Тестирование')]");
    private final By spanHideXPath =
            By.xpath(".//span[contains(text(),'Свернуть')]");


    public void chooseCoursesType() {
        getRootElement().findElement(labelTestingXPath).click();
    }

    public void clickHideMoreTypes(){
        getRootElement().findElement(spanHideXPath).click();
    }

}
