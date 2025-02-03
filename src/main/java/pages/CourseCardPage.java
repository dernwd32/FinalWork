package pages;

import components.CourseCardHeaderComponent;
import org.openqa.selenium.WebDriver;

public class CourseCardPage extends AbstractPage{
    String pageUrl = BASE_URL + "/lessons/$1/";

    public CourseCardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() { //сюда параметр конкретной карточки докинуть?
       // driver.get(pageUrl);
    }


    private CourseCardHeaderComponent courseCardHeaderComponent = new CourseCardHeaderComponent(driver);
    public CourseCardHeaderComponent getCourseCardHeaderComponent() {
        return courseCardHeaderComponent;
    }

}
