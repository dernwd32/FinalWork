package pages;

import components.events.EventsListComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class EventsPage extends AbstractPage{
    String pageUrl = BASE_URL + "/events/";


    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.get(pageUrl);
    }

    EventsListComponent eventsListComponent = new EventsListComponent(driver);

    public void scrollToShowFullEventsList() {
        //int i=0;
        do {
            standartWaiter.waitForElementNotVisible(eventsListComponent.getLoader());
            //System.out.println(++i);

            new Actions(driver)
                .sendKeys(Keys.END)
                .perform();
        }
        while (standartWaiter.waitForElementVisible(eventsListComponent.getLoader()));
    }
    public String convertDateToString (LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }
    public LocalDate convertStringToDate (String strDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, new Locale("ru"));
        return LocalDate.parse(strDate, formatter);
    }
    public int countEvents() {
        return eventsListComponent.getSpansWithEventDates().size();
    }

    public boolean checkEventDates() {
        LocalDate now = LocalDate.now();
        boolean checker = true;
        List<WebElement> eventDates = eventsListComponent.getSpansWithEventDates();

        for (WebElement thisDate : eventDates) {
            String textDate = thisDate.getText();
            if (textDate.split(" ").length < 3)
                textDate = textDate + " " + now.getYear();
            LocalDate dateFormatted = convertStringToDate(textDate, "d MMMM yyyy");
            if (dateFormatted.isBefore(now))
                checker = false;
        }
        return checker;
    }

    public void chooseEventFilter(String value){
        eventsListComponent.getEventTypesDropdown().click();

        for (WebElement thisFilter : eventsListComponent.getChooseEventFilter()) {
            standartWaiter.waitForElementVisible(thisFilter);
            if (thisFilter.getText().equals(value)) {
                thisFilter.click();
                break;
            }
        }
    }

    public boolean checkTitlesOfEventCards(String pattern){
        List<WebElement> titles = eventsListComponent.getEventCardTitles();

        for (WebElement thisTitle : titles)
            if (!thisTitle.getText().equals(pattern))
                return false;

        return true;
    }

}
