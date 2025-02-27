import asserts.AssertWithLog;
import components.courses.CourseCardHeaderComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CoursesRootPage;
import webdriver.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.*;


public class CoursesList_Test {

    private static final Logger logger = LogManager.getLogger(CoursesList_Test.class);
    private WebDriver driver;
    private final WebDriverFactory webDriverFactory = new WebDriverFactory();
    private AssertWithLog assertWithLog = null;
    private CoursesRootPage coursesPage = null;

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

    }

    @Test
    @DisplayName("Проверка количества курсов в разделе «Тестирование»")
    public void testCoursesList() {
        int counter = coursesPage.countCardsInList();
        assertWithLog.assertWithLog(
                counter == 10,
                "курсов на странице = " + counter
        );
    }




    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
