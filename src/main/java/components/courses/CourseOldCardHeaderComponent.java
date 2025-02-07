package components.courses;

import annotations.ComponentBlueprint;
import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentBlueprint( rootLocator = "xpath>>>//main")
public class CourseOldCardHeaderComponent extends AbstractCourseCardHeaderComponent {

    public CourseOldCardHeaderComponent(WebDriver driver) {
        super(driver);
        //передаем значения полей наследника в поля абстрактного класса для методов, реализованных в абстрактном классе
        setH1TitleXPath(h1TitleXPath);
        setDescrSubtitleXPath(descrSubtitleXPath);
        setSubHeaderCourseDurationXPath(subHeaderCourseDurationXPath);
        setSubHeaderCourseFormatXPath(subHeaderCourseFormatXPath);
    }

    private final By h1TitleXPath = By.xpath(".//h3[1]");
    private final By descrSubtitleXPath = By.xpath(".//h3[1]//following::div[1]");
    private final By subHeaderCourseDurationXPath = By.xpath( ".//div[@src='/_next/static/images/img/one-7074d31f3a086caefed9ae2a24ebd55f.svg']//following::div[1]");
    private final By subHeaderCourseFormatXPath = By.xpath(".//div[@src='/_next/static/images/img/two-422e0ae7d6f2e605cb8d07cc3fb03926.svg']//following::div[1]");



}
