package pages;

import components.AbstractComponent;
import components.courses.CourseCardHeaderComponent;
import components.courses.CourseOldCardHeaderComponent;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class CourseCardPage extends AbstractPage{
    //String pageUrl = BASE_URL + "/lessons/$1/";

    public CourseCardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() { //сюда параметр конкретной карточки докинуть?
       // driver.get(pageUrl);
    }



    /***
     * вот сюда вероятно можно подбросить компонент старого шаблона страницы
     *
     */

    public AbstractComponent getCourseCardHeaderComponent() {
        CourseCardHeaderComponent courseCardHeaderComponent = new CourseCardHeaderComponent(driver);
        CourseOldCardHeaderComponent courseOldCardHeaderComponent = new CourseOldCardHeaderComponent(driver);

        if (Objects.equals(driver.getCurrentUrl(), "https://otus.ru/online/manualtesting"))
            return courseOldCardHeaderComponent;
        else
            return courseCardHeaderComponent;
    }

}
