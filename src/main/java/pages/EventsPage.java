package pages;

import components.EventsListComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

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
        int i=0;
        boolean more = true;
        do {
            System.out.println(++i);

            new Actions(driver)
                .sendKeys(Keys.END)
                .perform();
        }
        while (standartWaiter.waitForElementVisible(eventsListComponent.getLoader()));
    }
}
