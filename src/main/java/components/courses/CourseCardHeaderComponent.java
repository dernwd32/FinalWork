package components.courses;

import annotations.ComponentBlueprint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//сначала ищется левая часть от OR в xpath, если не находится - ищется по правой части засчёт условия [not(//*[...])
@ComponentBlueprint( rootLocator = "(//*[local-name()='svg' and @width='7' and @height='10']//ancestor::section)[1]")
public class CourseCardHeaderComponent extends AbstractCourseCardHeaderComponent {

    public CourseCardHeaderComponent(WebDriver driver) {
        super(driver);
        //передаем значения полей наследника в поля абстрактного класса для методов, реализованных в абстрактном классе
        setTitleStrLoc(titleStrLoc);
        setDescrStrLoc(descrStrLoc);
        setDurationStrLoc(durationStrLoc);
        setFormatStrLoc(formatStrLoc);
    }

    private String subHeaderInfoBlockRoot = ".//div[contains(@class,'galmep')]";
    private String svgDurationD = "M17.1604 2.76862L17.1614";
    private String svgFormatD = "M3.90039 10.3178C3.90039";

    private String titleStr = ".//h1";
    private String descrStr = ".//h1//following::p[1]";
    private String durationStr = subHeaderInfoBlockRoot + "//*[local-name()='path' and contains(@d, '" + svgDurationD + "')]//following::p[1]";
    private String formatStr = subHeaderInfoBlockRoot + "//*[local-name()='path'  and contains(@d, '" + svgFormatD + "')]//following::p[1]";

    private final String titleStrLoc = getRootLocatorString() + cutRelativePoints(titleStr);
    private final String descrStrLoc = getRootLocatorString() + cutRelativePoints(descrStr) ;
    private final String durationStrLoc = getRootLocatorString() + cutRelativePoints(durationStr) ;
    private final String formatStrLoc = getRootLocatorString() + cutRelativePoints(formatStr);






}
