package components.courses;

import components.AbstractComponent;
import org.openqa.selenium.WebDriver;

public abstract class AbstractCourseCardHeaderComponent extends AbstractComponent {


    public AbstractCourseCardHeaderComponent(WebDriver driver) {
        super(driver);
    }

    private String titleStrLoc;
    private String descrStrLoc ;
    private String durationStrLoc;
    private String formatStrLoc;
    public void setTitleStrLoc(String titleStrLoc) {
        this.titleStrLoc = titleStrLoc;
    }
    public void setDescrStrLoc(String descrStrLoc) {
        this.descrStrLoc = descrStrLoc;
    }
    public void setDurationStrLoc(String durationStrLoc) {
        this.durationStrLoc = durationStrLoc;
    }
    public void setFormatStrLoc(String formatStrLoc) {
        this.formatStrLoc = formatStrLoc;
    }
    public String getTitleStrLoc() {
        return titleStrLoc;
    }
    public String getDescrStrLoc() {
        return descrStrLoc;
    }
    public String getDurationStrLoc() {
        return durationStrLoc;
    }
    public String getFormatStrLoc() {
        return formatStrLoc;
    }


    public String cutRelativePoints(String str) {
        String regex = "^(\\()?\\.(?=\\)|)";
        return str.replaceFirst(regex, "$1");
    }

}