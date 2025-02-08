package components.courses;

import annotations.ComponentBlueprint;
import com.github.javafaker.HowIMetYourMother;
import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//сначала ищется левая часть от OR в xpath, если не находится - ищется по правой части засчёт условия [not(//*[...])
@ComponentBlueprint( rootLocator = "xpath>>>(//*[local-name()='svg' and @width='7' and @height='10']//ancestor::section)[1] | " +
        "//main[not(//*[local-name()='svg' and @width='7' and @height='10']//ancestor::section)]")
public class CourseCardHeaderComponent  extends AbstractComponent {

    public CourseCardHeaderComponent(WebDriver driver) {
        super(driver);
    }

    private String subHeaderInfoBlockRoot = ".//div[contains(@class,'galmep')]";
    private String svgDurationD = "M17.1604 2.76862L17.1614";
    private String svgFormatD = "M3.90039 10.3178C3.90039";

    private String newTitle = ".//h1";
    private String oldTitle = "(.//h3)[1]";
    private String newDescr = ".//h1//following::p[1]";
    private String oldDescr = "(.//h3)[1]//following::div[1]";
    private String newDuration = subHeaderInfoBlockRoot + "//*[local-name()='path' and contains(@d, '" + svgDurationD + "')]//following::p[1]";
    private String oldDuration = "//div[@src='/_next/static/images/img/one-7074d31f3a086caefed9ae2a24ebd55f.svg']//following::div[1]";
    private String newFormat = subHeaderInfoBlockRoot + "//*[local-name()='path'  and contains(@d, '" + svgFormatD + "')]//following::p[1]";
    private String oldformat = "//div[@src='/_next/static/images/img/two-422e0ae7d6f2e605cb8d07cc3fb03926.svg']//following::div[1]";

    private final By titleXPath = By.xpath(newTitle + "|" + oldTitle);
    private final By descrXPath = By.xpath(newDescr + "|" + oldDescr );
    private final By durationXPath = By.xpath(newDuration + "|" + oldDuration);
    private final By formatXPath = By.xpath(newFormat + "|" + oldformat);

    public By getFormatXPath() {        return formatXPath;    }
    public By getTitleXPath() {        return titleXPath;    }
    public By getDescrXPath() {        return descrXPath;    }
    public By getDurationXPath() {        return durationXPath;    }

    public WebElement getTitle() {
        standartWaiter.waitForElementLocatedAndVisible(titleXPath);
        return getRootElement().findElement(titleXPath); }
    public WebElement getDescr() {
        standartWaiter.waitForElementLocatedAndVisible(descrXPath);
        return getRootElement().findElement(descrXPath);   }
    public WebElement getDuration() {
        standartWaiter.waitForElementLocatedAndVisible(durationXPath);
        return getRootElement().findElement(durationXPath);    }
    public WebElement getFormat() {
        standartWaiter.waitForElementLocatedAndVisible(formatXPath);
        return getRootElement().findElement(formatXPath);    }

}
