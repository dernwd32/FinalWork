package pages;

import components.CourseCardHeaderComponent;
import components.CoursesCardsListComponent;
import components.CoursesTypesMenuComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

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

    public HashMap<String, String> openLinksInNewTabAndCollectTexts(){

        CourseCardPage courseCardPage = new CourseCardPage(driver);
        CourseCardHeaderComponent courseCardHeaderComponent = new CourseCardHeaderComponent(driver);

        LinkedHashMap<String, String> textsInCard = new LinkedHashMap<>();


        for (WebElement thisCard : cardsInList()) {

           // System.out.println(++i + ": \n" + thisCard.getText());
            thisCard.sendKeys(Keys.CONTROL, Keys.RETURN);
            standartWaiter.waitForCondition(ExpectedConditions.numberOfWindowsToBe(2));

            ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
            driver.switchTo().window(tabs.getLast());

            if (standartWaiter.waitForElementLocatedAndVisible(courseCardHeaderComponent.getSubHeaderCourseFormatXPath())) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println(
                        courseCardPage.getCourseCardHeaderComponent().getH1Title().getText() + " \n"
                        + courseCardPage.getCourseCardHeaderComponent().getDescrSubtitle().getText() + " \n"
                                + courseCardPage.getCourseCardHeaderComponent().getSubHeaderCourseDuration().getText()+ " \n"
                                + courseCardPage.getCourseCardHeaderComponent().getSubHeaderCourseFormat().getText() );

                textsInCard.put(
                        driver.getCurrentUrl() + " - заголовок",
                        courseCardPage.getCourseCardHeaderComponent().getH1Title().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - описание",
                        courseCardPage.getCourseCardHeaderComponent().getDescrSubtitle().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - длительность",
                        courseCardPage.getCourseCardHeaderComponent().getSubHeaderCourseDuration().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - формат",
                        courseCardPage.getCourseCardHeaderComponent().getSubHeaderCourseFormat().getText()
                );
            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            driver.close(); //закрывает текущее окно
            standartWaiter.waitForCondition(ExpectedConditions.numberOfWindowsToBe(1));
            driver.switchTo().window(tabs.getFirst());
        }

        return textsInCard;
    }

}
