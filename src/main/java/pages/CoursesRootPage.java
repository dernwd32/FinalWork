package pages;

import components.CoursesCardsListComponent;
import components.CoursesTypesMenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


/**
логика далее
 */
    public void chooseCoursesType() {
        coursesTypesMenuComponent.getLabelTesting().click();
    }
    public void clickHideMoreTypes(){
        coursesTypesMenuComponent.getSpanHide().click();
    }

    public List<WebElement> cardsInList() {
           // System.out.println(coursesCardsListComponent.getCardsInList().size());
            return coursesCardsListComponent.getCardsInList();
    }

    public Set<String> hrefsOfCardsInList() {
        Set<String> hrefs = new HashSet<>();
        cardsInList().forEach(
                card -> {
                    hrefs.add(card.getAttribute("href"));
                    System.out.println(card.getAttribute("href"));
                }
        );
        return hrefs;
    }

    public int countCardsInList() {
        return cardsInList().size();
    }
}
