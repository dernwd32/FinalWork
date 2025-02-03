import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.CourseCardPage;
import pages.CoursesRootPage;
import pages.EventsPage;
import waiters.StandartWaiter;
import webdriver.WebDriverFactory;

public class Events_Test {

    private static final Logger logger = LogManager.getLogger(Events_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    //AssertWithLog assertWithLog = null;
    EventsPage eventsPage = null;

    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "edge").toLowerCase();
        driver = webDriverFactory.create(webDriverName, "maximize");
       // assertWithLog = new AssertWithLog(driver, logger);

        eventsPage = new EventsPage(driver);
        eventsPage.openPage();
    }

    @Test
    @DisplayName("Проверка мероприятий")
    public void testEvents() {
        eventsPage.scrollToShowFullEventsList();
    }
    @AfterEach
    void tearDown() {
       if (driver != null) driver.close();
    }

}
