package components;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waiters.StandartWaiter;

@ComponentBlueprint( rootLocator = "//*[local-name()='svg' and @width='7' and @height='10']//ancestor::section")
//section//a[contains(text(),'Тестирование') and contains(@href,'categories/testing')]//ancestor::section
public class CourseCardHeaderComponent extends AbstractComponent{
   StandartWaiter standartWaiter = new StandartWaiter(driver);

    public CourseCardHeaderComponent(WebDriver driver) {
        super(driver);
    }

    private final By h1TitleXPath = By.xpath(".//h1");
    private final By descrSubtitleXPath = By.xpath(".//h1//following::p[1]");
    //private final By subHeaderInfoBlockXPath = By.xpath(".//p[contains(text(),'Мск')]//ancestor::div[2]");
    private final String subHeaderInfoBlockRoot = ".//div[contains(@class,'galmep')]";
    //p[contains(text(),'Мск')]//ancestor::div[2]
    // *[local-name()='svg' and @width='24' and @height='24']//parent::div[1]//parent::div[@size='24']
    private final By subHeaderCourseDurationXPath = By.xpath(subHeaderInfoBlockRoot + "//div[3]//p");
    private final By subHeaderCourseFormatXPath = By.xpath(subHeaderInfoBlockRoot + "//div[4]//p");

    public WebElement getH1Title() {        return getRootElement().findElement(h1TitleXPath);    }
    public WebElement getDescrSubtitle() {        return getRootElement().findElement(descrSubtitleXPath);   }
    public WebElement getSubHeaderCourseDuration() {        return getRootElement().findElement(subHeaderCourseDurationXPath);    }
    public WebElement getSubHeaderCourseFormat() {        return getRootElement().findElement(subHeaderCourseFormatXPath);    }

    public boolean isntEmpty(WebElement element) {
        return !element.getText().isEmpty();
    }
}
