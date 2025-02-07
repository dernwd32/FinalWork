package pages;

import components.AbstractComponent;
import components.courses.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public LinkedHashMap<String, String> openLinksInNewTabAndCollectTexts(){

        LinkedHashMap<String, String> textsInCard = new LinkedHashMap<>();

        for (WebElement thisCard : cardsInList()) {

           // System.out.println(++i + ": \n" + thisCard.getText());
            thisCard.sendKeys(Keys.CONTROL, Keys.RETURN);
            standartWaiter.waitForCondition(ExpectedConditions.numberOfWindowsToBe(2));

            ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
            driver.switchTo().window(tabs.getLast());

            AbstractCourseCardHeaderComponent courseCardHeaderComponent = null;
            if (Objects.equals(driver.getCurrentUrl(), "https://otus.ru/online/manualtesting"))
                courseCardHeaderComponent = new CourseOldCardHeaderComponent(driver);
            else
                courseCardHeaderComponent = new CourseCardHeaderComponent(driver);



            if (standartWaiter.waitForElementLocatedAndVisible(
                    courseCardHeaderComponent.getSubHeaderCourseFormatXPath(),
                    courseCardHeaderComponent.getDescrSubtitleXPath(),
                    courseCardHeaderComponent.getH1TitleXPath(),
                    courseCardHeaderComponent.getSubHeaderCourseDurationXPath()
            )) {

                System.out.println(
                        courseCardHeaderComponent.getH1Title().getText() + " \n"
                                + courseCardHeaderComponent.getDescrSubtitle().getText() + " \n"
                                + courseCardHeaderComponent.getSubHeaderCourseDuration().getText() + " \n"
                                + courseCardHeaderComponent.getSubHeaderCourseFormat().getText());

                textsInCard.put(
                        driver.getCurrentUrl() + " - заголовок",
                        courseCardHeaderComponent.getH1Title().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - описание",
                        courseCardHeaderComponent.getDescrSubtitle().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - длительность",
                        courseCardHeaderComponent.getSubHeaderCourseDuration().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - формат",
                        courseCardHeaderComponent.getSubHeaderCourseFormat().getText()
                );
            } else textsInCard.put(driver.getCurrentUrl() + " - элемент не найден по локатору", "");


            driver.close(); //закрывает текущее окно
            standartWaiter.waitForCondition(ExpectedConditions.numberOfWindowsToBe(1));
            driver.switchTo().window(tabs.getFirst());
        }

        return textsInCard;
    }

}
