package pages;

import components.courses.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class CoursesRootPage extends AbstractPage{


    public CoursesRootPage(WebDriver driver) {
        super(driver);
        setPageUrl("/catalog/courses");
    }


    private CoursesTypesMenuComponent typesMenu = new CoursesTypesMenuComponent(driver);
    private CoursesCardsListComponent cardsList = new CoursesCardsListComponent(driver);



    public CoursesCardsListComponent getCardsList() {
        return cardsList;
    }
    public CoursesTypesMenuComponent getTypesMenu() {
        return typesMenu;
    }


    public void chooseCoursesType() {
        typesMenu.getLabelTesting().click();
    }
    public void clickHideMoreTypes(){
        typesMenu.getSpanHide().click();
    }

    public List<WebElement> cardsInList() {
           // System.out.println(cardsList.getCardsInList().size());
            return cardsList.getCardsInList();
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
        CourseCardHeaderComponent cardHeader = new CourseCardHeaderComponent(driver);

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

            if (standartWaiter.waitForElementLocatedAndVisible(
                    cardHeader.getFormatXPath(),
                    cardHeader.getDescrXPath(),
                    cardHeader.getTitleXPath(),
                    cardHeader.getDurationXPath()
            )) {

                System.out.println(
                        cardHeader.getTitle().getText() + " \n"
                        + cardHeader.getDescr().getText() + " \n"
                        + cardHeader.getDuration().getText() + " \n"
                        + cardHeader.getFormat().getText());

                textsInCard.put(
                        driver.getCurrentUrl() + " - заголовок",
                        cardHeader.getTitle().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - описание",
                        cardHeader.getDescr().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - длительность",
                        cardHeader.getDuration().getText()
                );
                textsInCard.put(
                        driver.getCurrentUrl() + " - формат",
                        cardHeader.getFormat().getText()
                );
            }
            else textsInCard.put(driver.getCurrentUrl() + " - элемент не найден по локатору", "");


            driver.close(); //закрывает текущее окно/вкладку
            standartWaiter.waitForCondition(ExpectedConditions.numberOfWindowsToBe(1));
            driver.switchTo().window(tabs.getFirst());
        }

        return textsInCard;
    }

}

