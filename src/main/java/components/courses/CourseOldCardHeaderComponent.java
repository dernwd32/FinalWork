package components.courses;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@ComponentBlueprint( rootLocator = "//main")
public class CourseOldCardHeaderComponent extends AbstractCourseCardHeaderComponent {

    public CourseOldCardHeaderComponent(WebDriver driver) {
        super(driver);
        //передаем значения полей наследника в поля абстрактного класса для методов, реализованных в абстрактном классе
        setTitleStrLoc(getRootLocatorString() + cutRelativePoints(titleStr));
        setDescrStrLoc(getRootLocatorString() + cutRelativePoints(descrStr));
        setDurationStrLoc(getRootLocatorString() + cutRelativePoints(durationStr));
        setFormatStrLoc(getRootLocatorString() + cutRelativePoints(formatStr));
    }
    private String titleStr = ".//h3[1]//following::div[1]//ul//preceding::h3";
    private String descrStr = ".//h3[1]//following::div[1]//ul";
    private String durationStr = ".//div[@src='/_next/static/images/img/one-7074d31f3a086caefed9ae2a24ebd55f.svg']//following::div[1]";
    private String formatStr = ".//div[@src='/_next/static/images/img/two-422e0ae7d6f2e605cb8d07cc3fb03926.svg']//following::div[1]";



}