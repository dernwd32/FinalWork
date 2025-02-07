package components.courses;

import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractCourseCardHeaderComponent extends AbstractComponent {


    public AbstractCourseCardHeaderComponent(WebDriver driver) {
        super(driver);
    }

    protected By h1TitleXPath;
    protected By descrSubtitleXPath;
    protected By subHeaderCourseFormatXPath;
    protected By subHeaderCourseDurationXPath;


    public void setH1TitleXPath(By h1TitleXPath) {        this.h1TitleXPath = h1TitleXPath;    }
    public void setDescrSubtitleXPath(By descrSubtitleXPath) {        this.descrSubtitleXPath = descrSubtitleXPath;    }
    public void setSubHeaderCourseFormatXPath(By subHeaderCourseFormatXPath) {        this.subHeaderCourseFormatXPath = subHeaderCourseFormatXPath;    }
    public void setSubHeaderCourseDurationXPath(By subHeaderCourseDurationXPath) {        this.subHeaderCourseDurationXPath = subHeaderCourseDurationXPath;    }

    public WebElement getH1Title() {
        standartWaiter.waitForElementLocatedAndVisible(h1TitleXPath);
        return getRootElement().findElement(h1TitleXPath); }
    public WebElement getDescrSubtitle() {
        standartWaiter.waitForElementLocatedAndVisible(descrSubtitleXPath);
        return getRootElement().findElement(descrSubtitleXPath);   }
    public WebElement getSubHeaderCourseDuration() {
        standartWaiter.waitForElementLocatedAndVisible(subHeaderCourseDurationXPath);
        return getRootElement().findElement(subHeaderCourseDurationXPath);    }
    public WebElement getSubHeaderCourseFormat() {
        standartWaiter.waitForElementLocatedAndVisible(subHeaderCourseFormatXPath);
        return getRootElement().findElement(subHeaderCourseFormatXPath);    }

    public By getSubHeaderCourseFormatXPath() {        return subHeaderCourseFormatXPath;    }
    public By getH1TitleXPath() {        return h1TitleXPath;    }
    public By getDescrSubtitleXPath() {        return descrSubtitleXPath;    }
    public By getSubHeaderCourseDurationXPath() {        return subHeaderCourseDurationXPath;    }

    public boolean isntEmpty(WebElement element) {
        return !element.getText().isEmpty();
    }

}
