import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CourseCardPage;
import pages.CoursesRootPage;
import webdriver.WebDriverFactory;

public class CourseCard_Test {

    private static final Logger logger = LogManager.getLogger(CoursesList_Test.class);
    private WebDriver driver;
    private final WebDriverFactory webDriverFactory = new WebDriverFactory();
    private AssertWithLog assertWithLog = null;
    private CoursesRootPage coursesPage = null;
    private CourseCardPage courseCardPage = null;

    @BeforeEach
    void beforeEach() {
        String webDriverName = System.getProperty("browser", "firefox");
        driver = webDriverFactory.create(webDriverName, "maximize");
        assertWithLog = new AssertWithLog(driver, logger);

        coursesPage = PageFactory.initElements(driver, CoursesRootPage.class);

        coursesPage
                .openPage()
                .killFilthyPopups();

        coursesPage
                .chooseFilterCheckboxByTitleAndValue("Направление", "Тестирование")
                .getCardsList()
                .getSearchLoader();

        courseCardPage = new CourseCardPage(driver);

    }

    @Test
    @DisplayName("Проверка содержимого карточек курсов (по ссылкам)")
    public void testCoursesCard() {
        courseCardPage.assertCardsHeaders(
                courseCardPage.getCardsHeaders(
                        coursesPage
                                .getCardsList()
                                .clickShowMoreWhileUCan()
                                .hrefsOfCardsInList()
                ),
                logger
        );
    }



    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
