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

import java.time.LocalDate;

public class Events_Test {

    private static final Logger logger = LogManager.getLogger(Events_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    AssertWithLog assertWithLog = null;
    EventsPage eventsPage = null;

    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "edge").toLowerCase();
        driver = webDriverFactory.create(webDriverName, "maximize");
        assertWithLog = new AssertWithLog(driver, logger);

        eventsPage = new EventsPage(driver);
        eventsPage.openPage();
    }

    @Test
    @DisplayName("Валидация дат предстоящих мероприятий")
    public void testEvents() {
        eventsPage.scrollToShowFullEventsList();
        assertWithLog.assertWithLog(eventsPage.countEvents()>=1, "мероприятий в разделе: " + eventsPage.countEvents() );
        assertWithLog.assertWithLog(eventsPage.checkEventDates(), "все даты мероприятий в будущем");
    }


    @Test
    @DisplayName("Просмотр мероприятий по типу:")
    public void testEventsTypes() {
        eventsPage.chooseEventFilter("Открытый вебинар");
        eventsPage.scrollToShowFullEventsList();
        assertWithLog.assertWithLog(eventsPage.checkTitlesOfEventCards("Открытый вебинар"), "заголовки карточек соответствуют фильтру");
    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.close();
    }

}
