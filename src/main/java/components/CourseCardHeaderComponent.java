package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import waiters.StandartWaiter;

@ComponentBlueprint( rootLocator = "//section//" +
        "a[contains(text(),'Тестирование') and contains(@href,'categories/testing')]//ancestor::section" )
public class CourseCardHeaderComponent extends AbstractComponent{
   StandartWaiter standartWaiter = new StandartWaiter(driver);

    public CourseCardHeaderComponent(WebDriver driver) {
        super(driver);
    }

    private final By h1TitleXPath = By.xpath(".//h1");
    private final By descrSubtitleXPath = By.xpath(".//h1//following::p[1]");
    //private final By subHeaderInfoBlockXPath = By.xpath(".//p[contains(text(),'Мск')]//ancestor::div[2]");
    private final String subHeaderInfoBlockRoot = ".//p[contains(text(),'Мск')]//ancestor::div[2]";
    private final By subHeaderCourseDurationXPath = By.xpath(subHeaderInfoBlockRoot + "//div[3]//p");
    private final By subHeaderCourseFormatXPath = By.xpath(subHeaderInfoBlockRoot + "//div[4]//p");

    public By getH1TitleXPath() {        return h1TitleXPath;    }
    public By getDescrSubtitleXPath() {        return descrSubtitleXPath;   }
    public By getSubHeaderCourseDurationXPath() {        return subHeaderCourseDurationXPath;    }
    public By getSubHeaderCourseFormatXPath() {        return subHeaderCourseFormatXPath;    }

    public boolean isntEmpty(By locator) {
        return !getRootElement().findElement(locator).getText().isEmpty();
    }
}
