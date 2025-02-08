package pages;

import components.courses.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
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

    public LinkedHashMap<String, String> openLinksInNewTabAndCollectTexts() {

        LinkedHashMap<String, String> textsInCard = new LinkedHashMap<>();

        for (WebElement thisCard : cardsInList()) {

            // System.out.println(++i + ": \n" + thisCard.getText());
            //thisCard.sendKeys(Keys.CONTROL, Keys.RETURN);
            new Actions(driver)
                    .moveToElement(thisCard)
                    .keyDown(Keys.CONTROL)
                    .click()
                    .perform();
            standartWaiter.waitForCondition(ExpectedConditions.numberOfWindowsToBe(2));

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.getLast());


            CourseCardHeaderComponent courseCardHeaderComponent = new CourseCardHeaderComponent(driver);

            if (standartWaiter.waitForElementLocatedAndVisible(
                    courseCardHeaderComponent.getFormatXPath(),
                    courseCardHeaderComponent.getDescrXPath(),
                    courseCardHeaderComponent.getTitleXPath(),
                    courseCardHeaderComponent.getDurationXPath()
            )) {

                System.out.println(
                        courseCardHeaderComponent.getTitle().getText() + " \n"
                                + courseCardHeaderComponent.getDescr().getText() + " \n"
                                + courseCardHeaderComponent.getDuration().getText() + " \n"
                                + courseCardHeaderComponent.getFormat().getText());

                textsInCard.put(
                        driver.getCurrentUrl() + " - заголовок",
                        courseCardHeaderComponent.getTitle().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - описание",
                        courseCardHeaderComponent.getDescr().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - длительность",
                        courseCardHeaderComponent.getDuration().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - формат",
                        courseCardHeaderComponent.getFormat().getText()
                );
            } else textsInCard.put(driver.getCurrentUrl() + " - элемент не найден по локатору", "");


            driver.close(); //закрывает текущее окно
            standartWaiter.waitForCondition(ExpectedConditions.numberOfWindowsToBe(1));
            driver.switchTo().window(tabs.getFirst());
        }

        return textsInCard;
    }

}

