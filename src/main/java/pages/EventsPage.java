package pages;

import org.openqa.selenium.WebDriver;

public class EventsPage extends AbstractPage{
    String pageUrl = BASE_URL + "/events/";

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.get(pageUrl);
    }

}
