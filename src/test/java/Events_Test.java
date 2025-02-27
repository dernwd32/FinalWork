import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.EventsPage;
import webdriver.WebDriverFactory;


class Events_Test {

    private static final Logger logger = LogManager.getLogger(Events_Test.class);
    private WebDriver driver;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private AssertWithLog assertWithLog = null;
    private EventsPage eventsPage = null;

    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "firefox");
        driver = webDriverFactory.create(webDriverName, "maximize");
        assertWithLog = new AssertWithLog(driver, logger);
        eventsPage = PageFactory.initElements(driver, EventsPage.class);
        eventsPage.openPage();

    }

    @Test
    @DisplayName("Валидация дат предстоящих мероприятий")
    void testEvents() {
        eventsPage.scrollToShowFullEventsList();
        assertWithLog.assertWithLog(eventsPage.countEvents()>=1, "мероприятий в разделе: " + eventsPage.countEvents() );
        assertWithLog.assertWithLog(eventsPage.checkEventDates(), "все даты мероприятий в будущем");
    }

    @Test
    @DisplayName("Просмотр мероприятий по типу:")
    void testEventsTypes() {
        eventsPage
                .chooseEventFilter("Открытый вебинар")
                .scrollToShowFullEventsList();
        assertWithLog.assertWithLog(
                eventsPage.checkTypesOfEvents("Открытый вебинар"),
                "типы карточек соответствуют фильтру");
    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

}
