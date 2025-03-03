package pages;

import components.FilterMenuComponent;
import components.courses.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class CoursesRootPage extends AbstractPage{


    public CoursesRootPage(WebDriver driver) {
        super(driver);
        setPageUrl("/catalog/courses");
    }


    private FilterMenuComponent filterMenu = new FilterMenuComponent(driver);
    private CoursesCardsListComponent cardsList = new CoursesCardsListComponent(driver);



    public CoursesCardsListComponent getCardsList() {
        return cardsList;
    }



    public CoursesRootPage chooseFilterCheckboxByTitleAndValue(String title, String item) {
        filterMenu.getFilterItem(title, item).click();
        return this;
    }


    public List<WebElement> cardsInList() {
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



}

