package pages;

import components.CoursesCardsListComponent;
import components.CoursesTypesMenuComponent;
import org.openqa.selenium.WebDriver;

public class CoursesRootPage extends AbstractPage{
    String pageUrl = BASE_URL + "/catalog/courses";

    public CoursesRootPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
         driver.get(pageUrl);
    }

    private CoursesTypesMenuComponent coursesTypesMenuComponent = new CoursesTypesMenuComponent(driver);
    private CoursesCardsListComponent coursesCardsListComponent = new CoursesCardsListComponent(driver);

    public CoursesCardsListComponent getCoursesCardsListComponent() {
        return coursesCardsListComponent;
    }

    public CoursesTypesMenuComponent getCoursesTypesMenuComponent() {
        return coursesTypesMenuComponent;
    }

}
